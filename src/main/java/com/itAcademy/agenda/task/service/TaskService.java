package com.itAcademy.agenda.task.service;

import com.itAcademy.agenda.common.exception.InvalidTaskException;
import com.itAcademy.agenda.common.exception.TaskNotFoundException;
import com.itAcademy.agenda.task.dto.TaskOutputDTO;
import com.itAcademy.agenda.task.model.Priority;
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

    public Task findTaskById(int id) throws TaskNotFoundException {
        return taskRepository.getTask(id)
                .orElseThrow(() -> new TaskNotFoundException("Task with ID: " + id + " not found"));
    }

    public void validateText(String text) throws InvalidTaskException{
        if(text == null || text.trim().isEmpty()){
            throw new InvalidTaskException("Text can't be empty");
        }
    }

    public void validateExpirationDate(LocalDateTime expirationDate) throws InvalidTaskException{
        if (expirationDate != null && expirationDate.isBefore(LocalDateTime.now())) {
            throw new InvalidTaskException("Expiration Date can't be before the current date");
        }
    }

    public void createTask(String text, LocalDateTime expirationDate) {
        try{
            validateText(text);
            validateExpirationDate(expirationDate);
            taskBuilder.reset();
            Task task = taskBuilder
                    .text(text.trim())
                    .expirationDate(expirationDate)
                    .build();
            taskRepository.createTask(task);
        }
        catch (InvalidTaskException e){
            System.err.println("Error: " + e.getMessage());
        }

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
    public void markCompleteTask(int id) {
        try{
            Task task = findTaskById(id);

            if(task.isCompleted()){
                throw new InvalidTaskException("Task is already completed");
            }
            Task completedTask = rebuildTask(task,true);
            taskRepository.completeTask(completedTask);
        }
        catch (InvalidTaskException | TaskNotFoundException e){
            System.err.println("Error: " + e.getMessage());
        }


    }

    public void notCompleteTask(int id){
        try{
            Task task = findTaskById(id);
            if(!task.isCompleted()){
                throw new InvalidTaskException("Task is already NOT completed");
            }
            Task completedTask = rebuildTask(task,false);
            taskRepository.completeTask(completedTask);
        }
        catch (InvalidTaskException | TaskNotFoundException e){
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void updateTaskText(int id, String newText) {
        try{
            validateText(newText);
            Task original = findTaskById(id);

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
        catch (InvalidTaskException | TaskNotFoundException e){
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void updateTaskExpirationDate(int id, LocalDateTime newExpirationDate) throws InvalidTaskException {
        try{
            validateExpirationDate(newExpirationDate);
            Task original = findTaskById(id);

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
        catch(InvalidTaskException e){
           throw e;
        }


    }

    public void updateTaskPriority (int id, Priority newPriority) {
        Task original= findTaskById(id);

        taskBuilder.reset();
        Task updatedTask = taskBuilder
                .id(id)
                .text(original.getText())
                .priority(newPriority)
                .expirationDate(original.getExpirationDate())
                .completed(original.isCompleted())
                .creationDate(original.getCreationDate())
                .build();

        taskRepository.updateTask(updatedTask);
    }

    public Task rebuildTask (Task task, boolean completed){
        taskBuilder.reset();
        return taskBuilder
                .id(task.getId())
                .text(task.getText())
                .priority(task.getPriority())
                .expirationDate(task.getExpirationDate())
                .completed(completed)
                .creationDate(task.getCreationDate())
                .build();
    }


}
