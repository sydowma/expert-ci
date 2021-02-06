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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import dev.magaofei.ci.runner.config.ActionConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author magaofei
 * @date 2021/2/6
 */
public class YamlConfigParser implements ConfigParser {

    private static final Logger logger = LoggerFactory.getLogger(YamlConfigParser.class);

    private static final ObjectMapper OM = new ObjectMapper(new YAMLFactory());

    @Override
    public ActionConfig parser(InputStream inputStream) throws IOException {
        ActionConfig actionConfig = new ActionConfig();

        actionConfig = OM.readValue(inputStream, ActionConfig.class);

        logger.info("load action dev.magaofei.config = {}", actionConfig);

        return actionConfig;
    }
}
