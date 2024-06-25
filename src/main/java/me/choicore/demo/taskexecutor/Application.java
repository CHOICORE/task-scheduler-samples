package me.choicore.demo.taskexecutor;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@EnableAsync
@SpringBootApplication
public class Application {

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext ac = SpringApplication.run(Application.class, args);
        TaskScheduler taskScheduler = ac.getBean(TaskScheduler.class);


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

        TimeUnit.SECONDS.sleep(10);
        ac.getBean(TaskJpaRepository.class).findAll().forEach(System.out::println);
    }

    @Bean
    public ApplicationRunner runner(TaskJpaRepository taskJpaRepository) {

        return args -> {
            for (int i = 0; i < 8; i++) {
                taskJpaRepository.save(new TaskEntity("READY"));
            }
        };
    }

}
