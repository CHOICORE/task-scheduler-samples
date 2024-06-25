package me.choicore.demo.taskexecutor;

import lombok.Getter;

import java.util.Objects;

@Getter
public class TaskExecutionSuccessEvent {
    private final Task task;
    private final long timestamp;

    public TaskExecutionSuccessEvent(Task task) {
        this.task = task;
        this.timestamp = System.currentTimeMillis();
    }

    public Task task() {
        return task;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (TaskExecutionSuccessEvent) obj;
        return Objects.equals(this.task, that.task);
    }

    @Override
    public int hashCode() {
        return Objects.hash(task);
    }

    @Override
    public String toString() {
        return "TaskExecutionSuccessEvent[" +
                "task=" + task + ']';
    }
}
