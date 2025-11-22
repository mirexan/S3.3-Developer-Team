package com.itAcademy.agenda.task.repository;

import com.itAcademy.agenda.task.cli.Priority;
import com.itAcademy.agenda.task.model.Task;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TaskRepository {
    private final TaskDAO dao;

    public TaskRepository(TaskDAO dao) {
        this.dao = dao;
    }

    public void createTask(Task task) {
        TaskDTO dto = new TaskDTO.Builder()
                .mainText(task.getMainText())
                .date(task.getDate())
                .creationDate(task.getCreationDate())
                .priority(task.getPriority().name())
                .build();

        dao.save(dto);
    }

    public Optional<Task> getTask(int id) {
        TaskDTO idDto = new TaskDTO.Builder()
                .id(id)
                .build();

        Optional<TaskDTO> dtoOpt = dao.findById(idDto);

        return dtoOpt.map(dto -> new Task(
                dto.getId(),
                dto.getMainText(),
                dto.getDate(),
                dto.getCompleted(),
                dto.getCreationDate(),
                Priority.valueOf(dto.getPriority())
        ));
    }

    public List<Task> getAllTasks() {
        return dao.findAll().stream()
                .map(dto -> new Task(
                        dto.getId(),
                        dto.getMainText(),
                        dto.getDate(),
                        dto.getCompleted(),
                        dto.getCreationDate(),
                        Priority.valueOf(dto.getPriority())))
                .collect(Collectors.toList());
    }

    public void completeTask(Task task) {
        TaskDTO dto = new TaskDTO.Builder()
                .id(task.getId())
                .completed(task.isCompleted())
                .build();

        dao.markAsCompleted(dto);
    }

    public void updateTask(Task task) {
        TaskDTO dto = new TaskDTO.Builder()
                .id(task.getId())
                .mainText(task.getMainText())
                .date(task.getDate())
                .priority(task.getPriority().name())
                .build();

        dao.update(dto);
    }

    public void deleteTask(int id) {
        TaskDTO idDto = new TaskDTO.Builder()
                .id(id)
                .build();

        dao.delete(idDto);
    }
}
