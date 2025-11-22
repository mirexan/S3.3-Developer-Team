package task;

import java.time.LocalDateTime;
import java.util.Objects;

public class Task {

    private int id;
    private String text;
    private LocalDateTime expirationDate;
    private Priority priority;
    private boolean completed;
    private LocalDateTime creationDate;

    public enum Priority{
        LOW, MEDIUM, HIGH
    }

   public Task(TaskBuilder taskBuilder){
        this.id = taskBuilder.getId();
        this. text = taskBuilder.getText();
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

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
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
