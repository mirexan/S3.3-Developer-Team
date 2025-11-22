package com.itAcademy.agenda.task.repository;

//TODO: Es possible k tenga k cambiar esta referencia
import com.itAcademy.agenda.task.cli.Task;
import com.itAcademy.agenda.task.dto.TaskDTO;

import java.util.List;
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
                .priority(task.getPriority())
                .completed(task.isCompleted())
                .build();

        dao.save(dto);
    }

    public Task getTask(int id) {
        TaskDTO idDto = new TaskDTO.Builder()
                .id(id)
                .build();

        TaskDTO dto = dao.findById(idDto);

        return new Task(
                dto.getId(),
                dto.getMainText(),
                dto.getDate(),
                dto.getCompleted(),
                dto.getCreationDate(),
                dto.getPriority());
    }

    public List<Task> getAllTasks() {
        return dao.findAll().stream()
                .map(dto -> new Task(
                        dto.getId(),
                        dto.getMainText(),
                        dto.getDate(),
                        dto.getCompleted(),
                        dto.getCreationDate(),
                        dto.getPriority()
                ))
                .collect(Collectors.toList());
    }


    public void completeTask(Task task) {
        TaskDTO dto = new TaskDTO.Builder()
                .id(task.getId())
                .completed(task.isCompleted())
                .build();

        dao.update(dto);
    }

    public void updateTask(Task task) {
        TaskDTO dto = new TaskDTO.Builder()
                .id(task.getId())
                .mainText(task.getMainText())
                .date(task.getDate())
                .creationDate(task.getCreationDate())
                .priority(task.getPriority())
                .completed(task.isCompleted())
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
