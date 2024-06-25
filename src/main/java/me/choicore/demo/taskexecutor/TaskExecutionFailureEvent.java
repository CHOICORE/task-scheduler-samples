package me.choicore.demo.taskexecutor;

import lombok.Getter;

import java.util.Objects;

@Getter
public class TaskExecutionFailureEvent {
    private final Task task;
    private final Throwable throwable;
    private final long timestamp;

    public TaskExecutionFailureEvent(Task task, Throwable throwable) {
        this.task = task;
        this.throwable = throwable;
        this.timestamp = System.currentTimeMillis();
    }

    public Task task() {
        return task;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (TaskExecutionFailureEvent) obj;
        return Objects.equals(this.task, that.task);
    }

    @Override
    public int hashCode() {
        return Objects.hash(task);
    }

    @Override
    public String toString() {
        return "TaskExecutionFailureEvent[" +
                "task=" + task + ']';
    }
}
