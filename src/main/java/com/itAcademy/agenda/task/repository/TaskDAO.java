package com.itAcademy.agenda.task.repository;

import com.itAcademy.agenda.task.dto.TaskPersistenceDTO;

import java.util.List;
import java.util.Optional;

public interface TaskDAO {
    void save(TaskPersistenceDTO dto);
    Optional<TaskPersistenceDTO> findById(TaskPersistenceDTO dto);
    List<TaskPersistenceDTO> findAll();
    void update(TaskPersistenceDTO dto);
    void markAsCompleted(TaskPersistenceDTO dto);
    List<TaskPersistenceDTO> listPendent();
    List<TaskPersistenceDTO> listCompleted();
    void delete(TaskPersistenceDTO dto);
}
