package com.itAcademy.agenda.task.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TaskDTO {
    private Integer id;
    private String mainText;
    private LocalDate date;
    private Boolean completed;
    private LocalDateTime creationDate;
    private String priority;

    public TaskDTO(int id, String mainText, LocalDate date, LocalDateTime creationDate,
                    String priority, boolean completed) {
        this.creationDate = creationDate;
        this.id = id;
        this.mainText = mainText;
        this.completed = completed;
        this.date = date;
        this.priority = priority;
    }

    public TaskDTO(int id) {
        this.id = id;
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

    public String getPriority() {
        return priority;
    }
}
