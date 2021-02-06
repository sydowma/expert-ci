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
package dev.magaofei.ci.runner.parser;

import dev.magaofei.ci.runner.config.ActionConfig;
import dev.magaofei.ci.runner.config.DockerConfig;

import java.util.*;

/**
 * @author magaofei
 * @date 2021/2/6
 */
public class ActionToDocker {


    public static DockerConfig convert(ActionConfig actionConfig) {
        DockerConfig dockerConfig = new DockerConfig();
        Set<String> tag = new HashSet<>();

        dockerConfig.setTag(tag);
        for (Map.Entry<String, ActionConfig.Job> jobEntry : actionConfig.getJobs().entrySet()) {
            String buildName = jobEntry.getKey();
            tag.add(buildName);

            ActionConfig.Job job = jobEntry.getValue();
            if (job.getSteps().isEmpty()) {
                return dockerConfig;
            }
            dockerConfig.setImage(job.getRunsOn().replace("-", ":"));

            for (ActionConfig.Step step : job.getSteps()) {
                dockerConfig.getRuns().addAll((step.getRun()));
            }
        }
        dockerConfigConvertToDockerfile(dockerConfig);
        return dockerConfig;
    }

    public static void dockerConfigConvertToDockerfile(DockerConfig dockerConfig) {
        List<String> runs = dockerConfig.getRuns();
        String cmd = String.join(" && ", runs);
        String dockerfile = String.format(
                "FROM %s\n" +
                        "CMD /bin/sh -c \" %s \"", dockerConfig.getImage(), cmd);
        dockerConfig.setDockerfile(dockerfile);
    }
}
