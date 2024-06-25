package me.choicore.demo.taskexecutor;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PriorityQueueTaskExecutor {
    private final ApplicationEventPublisher publisher;

    @Async
    public void execute(List<Task> tasks) {
        tasks.sort(Comparator.comparingInt(Task::getOrder));

        Map<Integer, List<Task>> taskGroup = tasks.stream().collect(Collectors.groupingBy(Task::getOrder));

        for (Map.Entry<Integer, List<Task>> entry : taskGroup.entrySet()) {
            List<Task> candidate = entry.getValue();
            CompletableFuture.allOf(candidate
                    .stream()
                    .map(task -> CompletableFuture.runAsync(task::execute)
                            .thenRun(() -> publisher.publishEvent(new TaskExecutionSuccessEvent(task)))
                            .exceptionally(e -> {
                                        publisher.publishEvent(new TaskExecutionFailureEvent(task, e));
                                        return null;
                                    }
                            )
                    )
                    .toArray(CompletableFuture[]::new)).join();
        }
    }
}
