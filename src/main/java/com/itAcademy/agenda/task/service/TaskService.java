package com.itAcademy.agenda.task.service;

import com.itAcademy.agenda.common.exception.InvalidTaskException;
import com.itAcademy.agenda.common.exception.TaskNotFoundException;
import com.itAcademy.agenda.task.dto.TaskOutputDTO;
import com.itAcademy.agenda.task.model.Task;
import com.itAcademy.agenda.task.repository.TaskRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class TaskService {

    private TaskRepository taskRepository;
    private TaskBuilder taskBuilder;

    public TaskService(TaskRepository taskRepository, TaskBuilder taskBuilder) {
        this.taskRepository = taskRepository;
        this.taskBuilder = taskBuilder;
    }

    public void createTask(String text, LocalDateTime expirationDate) throws InvalidTaskException {

        if (text == null || text.trim().isEmpty() || text.trim().isBlank()) {
            throw new InvalidTaskException("Text can't be empty");
        }

        if (expirationDate != null && expirationDate.isBefore(LocalDateTime.now())) {
            throw new InvalidTaskException("Expiration Date can't be before the current date");
        }
        taskBuilder.reset();
        Task task = taskBuilder.text(text.trim()).expirationDate(expirationDate).build();
        taskRepository.createTask(task);
    }

    public List<TaskOutputDTO> listAllTasks() throws TaskNotFoundException {
        List<Task> tasks = taskRepository.getAllTasks();

        if (!tasks.isEmpty()) {
            return tasks.stream()
                    .map(task -> new TaskOutputDTO(
                            task.getId(),
                            task.getText(),
                            task.getExpirationDate(),
                            task.getPriority().name(),
                            task.isCompleted()
                    ))
                    .toList();
        } else {
            throw new TaskNotFoundException("There are no tasks.");
        }
    }

    public List<TaskOutputDTO> listCompletedTasks() throws TaskNotFoundException {
        List<Task> tasks = taskRepository.getCompletedTasks();

        if (!tasks.isEmpty()) {
            return tasks.stream()
                    .map(task -> new TaskOutputDTO(
                            task.getId(),
                            task.getText(),
                            task.getExpirationDate(),
                            task.getPriority().name(),
                            task.isCompleted()
                    ))
                    .toList();
        } else {
            throw new TaskNotFoundException("There are no completed tasks.");
        }
    }

    public List<TaskOutputDTO> listPendentTasks() {
        List<Task> tasks = taskRepository.getPendentTasks();
        return tasks.stream()
                .map(task -> new TaskOutputDTO(
                        task.getId(),
                        task.getText(),
                        task.getExpirationDate(),
                        task.getPriority().name(),
                        task.isCompleted()
                ))
                .toList();
    }

    public void deleteTask(int id) throws TaskNotFoundException{
        Optional<Task> optionalTask = taskRepository.getTask(id);
        if (optionalTask.isEmpty()) {
            throw new TaskNotFoundException("Task with ID: " + id + " not found");
        }
        taskRepository.deleteTask(id);
    }
    public void markCompleteTask(int id) throws InvalidTaskException {
        Optional<Task> optionalTask = taskRepository.getTask(id);
        if (optionalTask.isEmpty()) {
            throw new TaskNotFoundException("Task with ID: " + id + " not found");
        }
        Task task = optionalTask.get();
        if (task.isCompleted()) {
            throw new InvalidTaskException("Task is already completed");
        }
        taskBuilder.reset();
        Task completedTask = taskBuilder
                .id(task.getId())
                .text(task.getText())
                .expirationDate(task.getExpirationDate())
                .priority(task.getPriority())
                .completed(true)
                .creationDate(task.getCreationDate())
                .build();

        taskRepository.completeTask(completedTask);
    }
    public void updateTaskText(int id, String newText) throws InvalidTaskException, TaskNotFoundException {
        Optional<Task> optionalTask = taskRepository.getTask(id);
        if (optionalTask.isEmpty()) {
            throw new TaskNotFoundException("Task with ID: " + id + " not found");
        }

        if (newText == null || newText.trim().isEmpty()) {
            throw new InvalidTaskException("Text can't be empty");
        }
        Task original = optionalTask.get();
        taskBuilder.reset();
        Task updatedTask = taskBuilder
                .id(id)
                .text(newText.trim())
                .priority(original.getPriority())
                .expirationDate(original.getExpirationDate())
                .completed(original.isCompleted())
                .creationDate(original.getCreationDate())
                .build();

        taskRepository.updateTask(updatedTask);
    }

    public void updateTaskExpirationDate(int id, LocalDateTime newExpirationDate) throws InvalidTaskException {

        Optional<Task> optionalTask = taskRepository.getTask(id);
        if (optionalTask.isEmpty()) {
            throw new TaskNotFoundException("Task with ID: " + id + " not found");
        }

        if (newExpirationDate != null && newExpirationDate.isBefore(LocalDateTime.now())) {
            throw new InvalidTaskException("Expiration Date can't be before the current date");
        }
        Task original = optionalTask.get();
        taskBuilder.reset();
        Task updatedTask = taskBuilder
                .id(id)
                .text(original.getText())
                .priority(original.getPriority())
                .expirationDate(newExpirationDate)
                .completed(original.isCompleted())
                .creationDate(original.getCreationDate())
                .build();

        taskRepository.updateTask(updatedTask);
    }
    public void notCompletedTask (int id) throws InvalidTaskException{
        Optional<Task> optionalTask = taskRepository.getTask(id);
        if (optionalTask.isEmpty()) {
            throw new TaskNotFoundException("Task with ID: " + id + " not found");
        }

        Task task = optionalTask.get();
        if (!task.isCompleted()) {
            throw new InvalidTaskException("Task is already NOT completed");
        }

        taskBuilder.reset();
        Task completedTask = taskBuilder
                .id(task.getId())
                .text(task.getText())
                .expirationDate(task.getExpirationDate())
                .priority(task.getPriority())
                .completed(false)
                .creationDate(task.getCreationDate())
                .build();

        taskRepository.completeTask(completedTask);
    }
}
