/**
 * Copyright 2017 Pivotal Software, Inc.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micrometer.core.instrument.internal;

import java.util.concurrent.Executor;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Timer;

/**
 * An {@link Executor} that is timed
 */
public class TimedExecutor implements Executor {
    private final Executor delegate;
    private final Timer timer;

    public TimedExecutor(MeterRegistry registry, Executor delegate, String name, Iterable<Tag> tags) {
        this.delegate = delegate;
        this.timer = registry.timer(name, tags);
    }

    @Override
    public void execute(Runnable command) {
        delegate.execute(timer.wrap(command));
    }

}
