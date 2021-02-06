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

import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.model.Frame;

/**
 * @author magaofei
 * @date 2021/2/6
 */
public class LogContainerCallback extends ResultCallback.Adapter<Frame>{

    protected final StringBuilder log = new StringBuilder();

    @Override
    public void onNext(Frame frame) {
        log.append(new String(frame.getPayload()));
    }

    @Override
    public String toString() {
        return log.toString();
    }

}
