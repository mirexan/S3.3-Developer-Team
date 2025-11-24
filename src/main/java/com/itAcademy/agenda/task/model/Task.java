package com.itAcademy.agenda.task.model;

import com.itAcademy.agenda.task.service.TaskBuilder;

import java.time.LocalDateTime;
import java.util.Objects;

public class Task {

    private final int id;
    private final String text;
    private final LocalDateTime expirationDate;
    private final Priority priority;
    private final boolean completed;
    private final LocalDateTime creationDate;

    public Task(TaskBuilder taskBuilder) {
        this.id = taskBuilder.getId();
        this.text = taskBuilder.getText();
        this.expirationDate = taskBuilder.getExpirationDate();
        this.priority = taskBuilder.getPriority();
        this.completed = taskBuilder.isCompleted();
        this.creationDate = taskBuilder.getCreationDate();
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getPriority() {
        return this.priority.toString();
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Task task = (Task) object;
        return id == task.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", expirationDate=" + expirationDate +
                ", priority=" + priority +
                ", completed=" + completed +
                ", creationDate=" + creationDate +
                '}';
    }
}
