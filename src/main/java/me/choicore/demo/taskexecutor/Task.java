package me.choicore.demo.taskexecutor;

public interface Task {
    Long getId();

    int getOrder();

    void execute();
}
