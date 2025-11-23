package com.itAcademy.agenda.task.repository;

import com.itAcademy.agenda.task.cli.Priority;
import com.itAcademy.agenda.task.model.Task;

public class TaskRepoMapper {
    public Task toTask(TaskDTO dto) {
        return new Task(
                dto.getId(),
                dto.getMainText(),
                dto.getDate(),
                dto.getCompleted(),
                dto.getCreationDate(),
                Priority.valueOf(dto.getPriority()));
    }

    public TaskDTO toDto(Task task) {
        return new TaskDTO(
                task.getId(),
                task.getMainText(),
                task.getDate(),
                task.getCreationDate(),
                task.getPriority().name(),
                task.isCompleted());
    }
}
