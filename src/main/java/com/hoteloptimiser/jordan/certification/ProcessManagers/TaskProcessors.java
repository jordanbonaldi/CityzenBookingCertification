package com.hoteloptimiser.jordan.certification.ProcessManagers;

import lombok.Data;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Data
public class TaskProcessors {

    @Data
    private class TaskProcesses implements Callable<TaskProcess> {

        private final TaskProcess taskProcess;

        @Override
        @SneakyThrows
        public TaskProcess call() {
            this.taskProcess.run();
            return this.taskProcess;
        }
    }

    private List<TaskProcesses> taskProcesses;

    private ExecutorService executorService;

    @SneakyThrows
    private TaskProcess apply(Future<TaskProcess> future) {
        return future.get();
    }

    public TaskProcessors(TaskProcess ... taskProcesses) {
        this.taskProcesses = Arrays.stream(taskProcesses).map(TaskProcesses::new).collect(Collectors.toList());
        this.executorService = Executors.newFixedThreadPool(5);
    }

    public void add(TaskProcess process) {
        this.taskProcesses.add(new TaskProcesses(process));
    }

    public void invokeAllQueued() {
        this.taskProcesses.forEach(e -> this.apply(this.executorService.submit(e)).action());
    }

    public void shutDown() {
        this.executorService.shutdown();
    }

}
