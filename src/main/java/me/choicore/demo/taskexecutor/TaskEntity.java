package me.choicore.demo.taskexecutor;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Entity
@Table(name = "task")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String status;
    private String errorMessage;
    private int priority;

    public TaskEntity(String status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public TaskEntity(String status) {
        this.status = status;
    }

    public void completed() {
        this.status = "COMPLETED";
    }

    public void failed(String errorMessage) {
        this.status = "FAILED";
        this.errorMessage = errorMessage;
    }
}
