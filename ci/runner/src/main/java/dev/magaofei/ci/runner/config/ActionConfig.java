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

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author magaofei
 * @date 2021/2/6
 */
public class ActionConfig {
    private String name;
    private On on;
    private Map<String, String> env;
    private LinkedHashMap<String /* build name*/, Job> jobs;

    public static class On {
        private Branch push;

        @JsonProperty("pull_request")
        private Branch pullRequest;

        public Branch getPush() {
            return push;
        }

        public void setPush(Branch push) {
            this.push = push;
        }

        public Branch getPullRequest() {
            return pullRequest;
        }

        public void setPullRequest(Branch pullRequest) {
            this.pullRequest = pullRequest;
        }

        @Override
        public String toString() {
            return "On{" +
                    "push=" + push +
                    ", pullRequest=" + pullRequest +
                    '}';
        }
    }
    public static class Branch {

        private List<String> branches;

        public List<String> getBranches() {
            return branches;
        }

        public void setBranches(List<String> branches) {
            this.branches = branches;
        }

        @Override
        public String toString() {
            return "Branch{" +
                    "branches=" + branches +
                    '}';
        }
    }

    public static class Job {
        private String name;
        private List<String> needs;

        /* runs-on  equals image name*/
        @JsonProperty("runs-on")
        private String runsOn;
        private Map<String, String> environment;
        private List<Step> steps;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getNeeds() {
            return needs;
        }

        public void setNeeds(List<String> needs) {
            this.needs = needs;
        }

        public String getRunsOn() {
            return runsOn;
        }

        public void setRunsOn(String runsOn) {
            this.runsOn = runsOn;
        }

        public Map<String, String> getEnvironment() {
            return environment;
        }

        public void setEnvironment(Map<String, String> environment) {
            this.environment = environment;
        }

        public List<Step> getSteps() {
            return steps;
        }

        public void setSteps(List<Step> steps) {
            this.steps = steps;
        }

        @Override
        public String toString() {
            return "Job{" +
                    "name='" + name + '\'' +
                    ", needs=" + needs +
                    ", runsOn='" + runsOn + '\'' +
                    ", environment=" + environment +
                    ", steps=" + steps +
                    '}';
        }
    }

    public static class Step {
        private String name;
        private List<String> run;
        /* environment with step */
        private Map<String, String> with;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getRun() {
            return run;
        }

        public void setRun(List<String> run) {
            this.run = run;
        }

        public Map<String, String> getWith() {
            return with;
        }

        public void setWith(Map<String, String> with) {
            this.with = with;
        }

        @Override
        public String toString() {
            return "Step{" +
                    "name='" + name + '\'' +
                    ", dev.magaofei.run=" + run +
                    ", with=" + with +
                    '}';
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public On getOn() {
        return on;
    }

    public void setOn(On on) {
        this.on = on;
    }

    public Map<String, String> getEnv() {
        return env;
    }

    public void setEnv(Map<String, String> env) {
        this.env = env;
    }

    public LinkedHashMap<String, Job> getJobs() {
        return jobs;
    }

    public void setJobs(LinkedHashMap<String, Job> jobs) {
        this.jobs = jobs;
    }

    @Override
    public String toString() {
        return "ActionConfig{" +
                "name='" + name + '\'' +
                ", on=" + on +
                ", env=" + env +
                ", jobs=" + jobs +
                '}';
    }
}
