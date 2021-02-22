/*
 * Copyright 2021 magaofei
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dev.magaofei.ci.runner.run;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.command.CreateContainerCmd;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.WaitContainerResultCallback;
import com.github.dockerjava.api.exception.NotModifiedException;
import com.github.dockerjava.api.model.*;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;
import dev.magaofei.ci.runner.parser.ActionToDocker;
import dev.magaofei.ci.runner.config.ActionConfig;
import dev.magaofei.ci.runner.config.DockerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author magaofei
 * @date 2021/1/24
 */
public class DockerExecutor implements Executor {

    private static final Logger logger = LoggerFactory.getLogger(DockerExecutor.class);

    private DockerConfig dockerConfig;

    private DockerClient dockerClient;

    private CreateContainerResponse containerResponse;

    private String imageId;

    @Override
    public void init(ActionConfig actionConfig) {

        DockerClientConfig standard = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
        DockerHttpClient httpClient = new ApacheDockerHttpClient.Builder().dockerHost(URI.create("tcp://127.0.0.1:2376")).sslConfig(standard.getSSLConfig()).build();
        this.dockerClient = DockerClientImpl.getInstance(standard, httpClient);
        this.dockerConfig = ActionToDocker.convert(actionConfig);
    }

    @Override
    public void run() {
        try {
            this.runContainer();
        } catch (IOException e) {
            logger.warn(" e", e);
        }
    }

    /**
     *
     */
    public void runContainer() throws IOException {

        Path dockerfilePath = this.initDockerfile();
        this.buildImage(dockerfilePath);
        CreateContainerResponse containerResponse;
        HostConfig hostConfig = new HostConfig();
        List<Bind> bindList = new ArrayList<>();
        for (Map.Entry<String, String> entry : this.dockerConfig.getVolume().entrySet()) {
            String host = entry.getKey();
            String containerVolume = entry.getValue();
            bindList.add(new Bind(host, new Volume(containerVolume)));
        }
        hostConfig.withBinds(bindList);
        try (CreateContainerCmd createContainerCmd = this.dockerClient.createContainerCmd(this.imageId)) {
            containerResponse = createContainerCmd
                    .withHostConfig(hostConfig)
                    .withTty(true)
                    .exec();
            this.dockerClient.startContainerCmd(containerResponse.getId()).exec();
            this.containerResponse = containerResponse;

            showLog(dockerClient, containerResponse.getId(), true, 1, new LogContainerCallback() {
                @Override
                public void onNext(Frame frame) {
                    String message = new String(frame.getPayload());
                    logger.info("{}", message);
                    super.onNext(frame);
                }
            });

            logger.info("wait container cmd");
            try (WaitContainerResultCallback waitContainerResultCallback = new WaitContainerResultCallback()) {
                int exitCode = dockerClient.waitContainerCmd(containerResponse.getId()).exec(waitContainerResultCallback)
                        .awaitStatusCode(30, TimeUnit.MINUTES);
                logger.info("exitCode = {}", exitCode);
                if (exitCode != 0) {
                    throw new IllegalArgumentException("exit = " + exitCode);
                }
            }
        } finally {
            this.stop();
        }
    }

    private void buildImage(Path dockerfilePath) {
        SampleBuildImageResultCallback sampleBuildImageResultCallback = new SampleBuildImageResultCallback();
        try {
            this.imageId = this.dockerClient.buildImageCmd()
                    .withTags(this.dockerConfig.getTag())
                    .withPull(true)
                    .withBaseDirectory(dockerfilePath.getParent().toFile())
                    .withDockerfile(dockerfilePath.toFile())
                    .exec(sampleBuildImageResultCallback)
                    .awaitImageId();
        } catch (Exception e) {
            logger.warn("build image error ", e);
            throw new IllegalArgumentException(e);
        }
    }

    private Path initDockerfile() throws IOException {

        Path build = Paths.get("build");
        Path dockerfilePath = Paths.get("build", "Dockerfile");
        try {
            Files.createDirectory(build);
        } catch (FileAlreadyExistsException ignore) {

        }

        Files.write(dockerfilePath, this.dockerConfig.getDockerfile().getBytes(StandardCharsets.UTF_8));
        return dockerfilePath;

    }

    /**
     * 终止任务
     */
    public void stop() {
        try {
            if (this.containerResponse != null) {
                logger.info("start stop container");
                try {
                    dockerClient.stopContainerCmd(this.containerResponse.getId()).exec();
                } catch (NotModifiedException e) {
                    logger.warn("Container already stopped");
                }

                logger.info("end stop container");

                logger.info("start remove container id = {}", this.containerResponse.getId());
                dockerClient.removeContainerCmd(this.containerResponse.getId()).exec();
            }
            logger.info("start remove image id = {}", this.imageId);
            dockerClient.removeImageCmd(this.imageId).exec();
        } catch (Exception e) {
            logger.warn(" e", e);
        }
    }

    public static void showLog(DockerClient dockerClient, String containerId, boolean follow, int numberOfLines, ResultCallback<Frame> logCallback) {
        dockerClient.logContainerCmd(containerId).withStdOut(true).withStdErr(true).withFollowStream(follow).withTail(numberOfLines).exec(logCallback);
    }


    public DockerConfig getDockerConfig() {
        return dockerConfig;
    }

    public void setDockerConfig(DockerConfig dockerConfig) {
        this.dockerConfig = dockerConfig;
    }
}
