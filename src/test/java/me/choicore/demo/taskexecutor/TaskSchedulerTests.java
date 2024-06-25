package me.choicore.demo.taskexecutor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class TaskSchedulerTests {
    @Autowired
    private TaskScheduler taskScheduler;

    @Test
    void t1() throws InterruptedException {

        List<Task> tasks1 = Arrays.asList(
                new SimpleTask(1L, 1, 1000),
                new SimpleTask(2L, 1, 2000),
                new SimpleTask(3L, 2, 2000),
                new SimpleTask(4L, 3, 1000)
        );

        Schedule schedule1 = new Schedule(tasks1);
        List<Task> tasks2 = Arrays.asList(
                new SimpleTask(5L, 1, 1000),
                new SimpleTask(6L, 1, 2000),
                new SimpleTask(7L, 2, 3000),
                new SimpleTask(8L, 3, 1000)
        );
        Schedule schedule2 = new Schedule(tasks2);

        taskScheduler.trigger(Arrays.asList(schedule1, schedule2));

        TimeUnit.SECONDS.sleep(8);
    }
}