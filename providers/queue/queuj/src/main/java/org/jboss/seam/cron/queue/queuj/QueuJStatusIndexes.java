/**
 * JBoss, Home of Professional Open Source
 * Copyright 2011, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.seam.cron.queue.queuj;

import com.workplacesystems.queuj.Process;
import com.workplacesystems.queuj.Queue;
import com.workplacesystems.utilsj.collections.IterativeCallback;
import org.jboss.seam.cron.api.restriction.StatusIndexes;

/**
 * Implementation of StatusIndexes for QueuJ.
 * 
 * @author Dave Oxley
 */
public class QueuJStatusIndexes implements StatusIndexes {

    private final Queue queue;
    private final Process process;

    QueuJStatusIndexes(Queue queue, Process process) {
        this.queue = queue;
        this.process = process;
    }

    public int countOfNotRunProcesses() {
        return process.getContainingServer().getProcessIndexes().countOfNotRunProcesses(queue);
    }

    public int countOfRunningProcesses() {
        return process.getContainingServer().getProcessIndexes().countOfRunningProcesses(queue);
    }

    public int countOfWaitingToRunProcesses() {
        return process.getContainingServer().getProcessIndexes().countOfWaitingToRunProcesses(queue);
    }

    public int countOfFailedProcesses() {
        return process.getContainingServer().getProcessIndexes().countOfFailedProcesses(queue);
    }

    public <R> R iterateNotRunProcesses(IterativeCallback<Object, R> ic) {
        return process.getContainingServer().getProcessIndexes().iterateNotRunProcesses(queue, new CronIterativeCallback<R>(ic));
    }

    public <R> R iterateRunningProcesses(IterativeCallback<Object, R> ic) {
        return process.getContainingServer().getProcessIndexes().iterateRunningProcesses(queue, new CronIterativeCallback<R>(ic));
    }

    public <R> R iterateWaitingToRunProcesses(IterativeCallback<Object, R> ic) {
        return process.getContainingServer().getProcessIndexes().iterateWaitingToRunProcesses(queue, new CronIterativeCallback<R>(ic));
    }

    public <R> R iterateFailedProcesses(IterativeCallback<Object, R> ic) {
        return process.getContainingServer().getProcessIndexes().iterateFailedProcesses(queue, new CronIterativeCallback<R>(ic));
    }
}
