package com.itAcademy.agenda.task.repository;

import java.util.List;
import java.util.Optional;

public interface TaskDAO {
    void save(TaskDTO dto);
    Optional<TaskDTO> findById(TaskDTO dto);
    List<TaskDTO> findAll();
    void update(TaskDTO dto);
    void markAsCompleted(TaskDTO dto);
    void delete(TaskDTO dto);
}
