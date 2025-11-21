package com.itAcademy.agenda.task.repository;

import com.itAcademy.agenda.task.cli.Task;
import java.util.List;

public interface TaskDAO {
    Task save(Task task);
    Task findById(int id);
    List<Task> findAll();
    void update(Task task);
    void delete(int id);
}
