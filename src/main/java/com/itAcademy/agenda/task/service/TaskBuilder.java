package com.itAcademy.agenda.task.service;

import com.itAcademy.agenda.task.model.*;

import java.time.LocalDateTime;
import java.util.Random;

import static java.lang.Math.random;

public class TaskBuilder implements Builder<Task> {
    private int id;
    private String text;
    private LocalDateTime expirationDate;
    private Priority priority;
    private boolean completed;
    private LocalDateTime creationDate;

    public TaskBuilder() {
        reset();
    }

    @Override
    public Task build() {
        return new Task(this);
    }

    //reset() ya coloca la fecha de creación a la actual y la prioridad en MEDIUM.
    @Override
    public void reset() {
       Random random = new Random();
		this.id = random.nextInt();
        this.text = null;
        this.expirationDate = null;
        this.priority = Priority.MEDIUM;
        this.completed = false;
        this.creationDate = LocalDateTime.now();
    }

    public TaskBuilder id(int id) {
        this.id = id;
        return this;
    }

    public TaskBuilder text(String text) {
        this.text = text;
        return this;
    }

    public TaskBuilder expirationDate(LocalDateTime expirationDate) throws IllegalArgumentException {
        this.expirationDate = expirationDate;
        return this;
    }

    public TaskBuilder priority(Priority priority) {
        this.priority = priority;
        return this;
    }

    public TaskBuilder completed(Boolean completed) {
        this.completed = completed;
        return this;
    }

    public TaskBuilder creationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    //Getters

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public Priority getPriority() {
        return priority;
    }

    public boolean isCompleted() {
        return completed;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }
}