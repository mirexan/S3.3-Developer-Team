package com.itAcademy.agenda.task.repository;

import com.itAcademy.agenda.task.cli.Task;
import com.itAcademy.agenda.task.dto.TaskDTO;

import java.util.List;

public interface TaskDAO {
    void save(TaskDTO dto);
    TaskDTO findById(TaskDTO dto);
    List<TaskDTO> findAll();
    void update(TaskDTO dto);
    void delete(TaskDTO dto);
}
