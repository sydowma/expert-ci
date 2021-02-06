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
package dev.magaofei.ci.runner;

import dev.magaofei.ci.runner.config.ActionConfig;
import dev.magaofei.ci.runner.parser.YamlConfigParser;
import dev.magaofei.ci.runner.run.DockerExecutor;
import dev.magaofei.ci.runner.run.Executor;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.*;

/**
 * @author magaofei
 * @date 2021/2/6
 */
public class Main {

    public static void main(String[] args) throws TimeoutException, ExecutionException, IOException {
        Executor executor = new DockerExecutor();
        String path = "test/ActionTest.yml";
//        for (String arg : args) {
//            if (arg.equals("path")) {
//                path = arg;
//            }
//        }
//        if (path == null) {
//            System.exit(1);
//        }
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);

        YamlConfigParser yamlConfigParser = new YamlConfigParser();
        ActionConfig actionConfig = yamlConfigParser.parser(inputStream);
        executor.init(actionConfig);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(executor);
        executorService.shutdown();

    }

}
