package com.itAcademy.agenda.task.dto;

import java.time.LocalDateTime;

public class TaskPersistenceDTO {
    private final int id;
    private String mainText;
    private LocalDateTime date;
    private boolean completed;
    private LocalDateTime creationDate;
    private String priority;

    public TaskPersistenceDTO(int id, String mainText, LocalDateTime date, LocalDateTime creationDate,
                              String priority, boolean completed) {
        this.creationDate = creationDate;
        this.id = id;
        this.mainText = mainText;
        this.completed = completed;
        this.date = date;
        this.priority = priority;
    }

    public TaskPersistenceDTO(int id) {
        this.id = id;
    }

    public TaskPersistenceDTO(int id, boolean completed) {
        this.id = id;
        this.completed = completed;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Integer getId() {
        return id;
    }

    public String getMainText() {
        return mainText;
    }

    public String getPriority() {
        return priority;
    }
}
