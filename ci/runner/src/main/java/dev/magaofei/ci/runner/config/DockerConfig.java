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
package dev.magaofei.ci.runner.config;

import java.util.*;

/**
 * @author magaofei
 * @date 2021/2/5
 */
public class DockerConfig {
    private Map<String, String> volume;
    private String image;
    private Set<String> tag;
    private String dockerfile;
    private String path;
    private List<String> runs;

    public DockerConfig() {
        this.runs = new ArrayList<>();
        this.volume = new HashMap<>();
    }

    public Map<String, String> getVolume() {
        return volume;
    }

    public void setVolume(Map<String, String> volume) {
        this.volume = volume;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<String> getTag() {
        return tag;
    }

    public void setTag(Set<String> tag) {
        this.tag = tag;
    }

    public String getDockerfile() {
        return dockerfile;
    }

    public void setDockerfile(String dockerfile) {
        this.dockerfile = dockerfile;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<String> getRuns() {
        return runs;
    }

    public void setRuns(List<String> runs) {
        this.runs = runs;
    }
}
