package me.choicore.demo.taskexecutor;

import java.util.List;

public record Schedule(
        List<Task> tasks
) {
}
