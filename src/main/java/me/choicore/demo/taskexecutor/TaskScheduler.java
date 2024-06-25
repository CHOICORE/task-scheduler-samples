package me.choicore.demo.taskexecutor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskScheduler {
    private final PriorityQueueTaskExecutor taskExecutor;
        public void trigger(List<Schedule> schedules) {

        int size = schedules.size();
        int current = 1;
        for (Schedule schedule : schedules) {
            log.info("Executing schedule {}/{}", current++, size);
            taskExecutor.execute(schedule.tasks());
        }

        log.info("Total {} schedules to be executed", size);

        log.info("=".repeat(50));
        log.info("Main Thread is done!");
        log.info("=".repeat(50));
    }
}
