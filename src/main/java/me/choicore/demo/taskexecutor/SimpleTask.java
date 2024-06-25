package me.choicore.demo.taskexecutor;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
@Getter
public class SimpleTask implements Task {
    private Long id;
    private int order;
    private int throttle;

    public SimpleTask(Long id, int order, int throttle) {
        this.id = id;
        this.order = order;
        this.throttle = throttle;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public int getOrder() {
        return this.order;
    }

    @Override
    public void execute() {
        long start = System.currentTimeMillis();
        log.info("SimpleTask started with order {} and throttle {}ms", order, throttle);
        if (throttle == 3000) {
            throw new RuntimeException("error occurred! please retry!");
        }
        try {
            TimeUnit.MILLISECONDS.sleep(throttle);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long end = System.currentTimeMillis();
        log.info(" ==> SimpleTask executed took {}ms", end - start);
    }
}
