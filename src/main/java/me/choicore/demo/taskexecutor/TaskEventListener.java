package me.choicore.demo.taskexecutor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class TaskEventListener {
    private final TaskJpaRepository taskJpaRepository;

    @Async
    @Transactional
    @EventListener
    public void onTaskExecutionFailure(TaskExecutionFailureEvent event) {
        log.error("Retrieve Task Execution Failure Event: {}, {}", event.task().getId(), event.getThrowable().getMessage());
        taskJpaRepository.findById(event.task().getId()).ifPresent(taskEntity -> {
            taskEntity.failed(event.getThrowable().getMessage());
            taskJpaRepository.save(taskEntity);
        });
    }

    @Async
    @Transactional
    @EventListener
    public void onTaskExecutionSuccess(TaskExecutionSuccessEvent event) {
        log.info("Retrieve Task Execution Success Event: {}", event.task().getId());
        taskJpaRepository.findById(event.task().getId()).ifPresent(taskEntity -> {
            taskEntity.completed();
            taskJpaRepository.save(taskEntity);
        });
    }
}
