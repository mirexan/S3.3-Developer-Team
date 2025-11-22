package task;

import java.time.LocalDateTime;

public class TaskBuilder implements Builder<Task> {
    private Integer id;
    private String text;
    private LocalDateTime expirationDate;
    private Task.Priority priority;
    private boolean completed;
    private LocalDateTime creationDate;

    public TaskBuilder() {
        reset();
    }

    @Override
    public Task build(){
        return new Task(this);
    }

    //reset() ya coloca la fecha de creación a la actual y la prioridad en MEDIUM.
    @Override
    public void reset(){
        this.id= null; //Todavía no tiene id.
        this.text = null;
        this.expirationDate = null;
        this.priority = priority.MEDIUM;
        this.completed = false;
        this.creationDate = LocalDateTime.now();
    }

    public TaskBuilder id(int id){
        this.id = id;
        return this;
    }

    public TaskBuilder text(String text){
        this.text = text;
        return this;
    }

    public TaskBuilder expirationDate(LocalDateTime expirationDate) throws IllegalArgumentException {
        this.expirationDate = expirationDate;
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

    public Task.Priority getPriority() {
        return priority;
    }

    public boolean isCompleted() {
        return completed;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }
}






