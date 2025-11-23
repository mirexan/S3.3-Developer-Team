package com.itAcademy.agenda.task.repository;

import com.itAcademy.agenda.task.model.Task;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TaskRepository {
    private final TaskDAO dao;
    private final TaskRepoMapper mapper = new TaskRepoMapper();

    public TaskRepository(TaskDAO dao) {
        this.dao = dao;
    }

    public void createTask(Task task) {
        dao.save(mapper.toDto(task));
    }

    public Optional<Task> getTask(int id) {
        return dao.findById(new TaskDTO(id)).map(mapper::toTask);
    }

    public List<Task> getAllTasks() {
        return dao.findAll().stream()
                .map(mapper::toTask)
                .collect(Collectors.toList());
    }

    public void completeTask(Task task) {
        dao.markAsCompleted(new TaskDTO(task.getId(), task.isCompleted()));
    }

    public void updateTask(Task task) {
        dao.update(mapper.toDto(task));
    }

    public List<Task> listPendentTasks() {
        return dao.listPendent().stream()
                .map(mapper::toTask)
                .collect(Collectors.toList());
    }

    public List<Task> listCompletedTasks() {
        return dao.listCompleted().stream()
                .map(mapper::toTask)
                .collect(Collectors.toList());
    }

    public void deleteTask(int id) {
        dao.delete(new TaskDTO(id));
    }
}
