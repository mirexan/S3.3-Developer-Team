package com.itAcademy.agenda.task.dto;

import com.itAcademy.agenda.task.cli.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TaskDTO {
    private final Integer id;
    private final String mainText;
    private final LocalDate date;
    private final Boolean completed;
    private final LocalDateTime creationDate;
    private final Task.Priority priority;

    private TaskDTO(Builder builder) {
        this.creationDate = builder.creationDate;
        this.id = builder.id;
        this.mainText = builder.mainText;
        this.completed = builder.completed;
        this.date = builder.date;
        this.priority = builder.priority;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDate getDate() {
        return date;
    }

    public Integer getId() {
        return id;
    }

    public String getMainText() {
        return mainText;
    }

    public Task.Priority getPriority() {
        return priority;
    }

    public static class Builder {
        private Integer id;
        private String mainText;
        private LocalDate date;
        private Boolean completed = false;
        private LocalDateTime creationDate;
        private Task.Priority priority;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder mainText(String mainText) {
            this.mainText = mainText;
            return this;
        }

        public Builder date(LocalDate date) {
            this.date = date;
            return this;
        }

        public Builder completed(Boolean completed) {
            this.completed = completed;
            return this;
        }

        public Builder creationDate(LocalDateTime creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public Builder priority(Task.Priority priority) {
            this.priority = priority;
            return this;
        }

        public TaskDTO build() {
            return new TaskDTO(this);
        }
    }
}
