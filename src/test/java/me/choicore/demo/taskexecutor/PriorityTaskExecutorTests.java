package me.choicore.demo.taskexecutor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class PriorityTaskExecutorTests {

    @Autowired
    private PriorityQueueTaskExecutor priorityTaskExecutor;

    @Test
    void t1() {
        List<Task> tasks = Arrays.asList(
                new SimpleTask(1L, 1, 1000),
                new SimpleTask(2L, 1, 2000),
                new SimpleTask(3L, 2, 3000),
                new SimpleTask(4L, 3, 1000)
        );
        priorityTaskExecutor.execute(tasks);

        // wait for the tasks to complete
        try {
            Thread.sleep(8000);
        } catch (InterruptedException ignored) {
        }
    }
}