package com.itAcademy.agenda.task.repository.mappers;


import com.itAcademy.agenda.task.model.*;
import com.itAcademy.agenda.task.dto.TaskPersistenceDTO;
import com.itAcademy.agenda.task.service.TaskBuilder;

public class TaskRepoMapper {
    public Task toTask(TaskPersistenceDTO dto) {
        TaskBuilder builder = new TaskBuilder()
                .id(dto.getId())
                .text(dto.getMainText())
                .expirationDate(dto.getDate())
                .priority(Priority.valueOf(dto.getPriority()))
                .completed(dto.getCompleted())
                .creationDate(dto.getCreationDate());

        return new Task(builder);
    }


    public TaskPersistenceDTO toDto(Task task) {
        return new TaskPersistenceDTO(
                task.getId(),
                task.getText(),
                task.getExpirationDate(),
                task.getCreationDate(),
                task.getPriority(),
                task.isCompleted());
    }
}
