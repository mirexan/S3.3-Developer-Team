package com.itAcademy.agenda.task.service;

import com.itAcademy.agenda.common.exception.InvalidTaskException;
import com.itAcademy.agenda.common.exception.TaskNotFoundException;
import com.itAcademy.agenda.task.dto.TaskOutputDTO;
import com.itAcademy.agenda.task.model.Task;
import com.itAcademy.agenda.task.repository.TaskRepository;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
//este import esta de relleno, es la importación del TaskRepository
//import task.TaskRepository;

public class TaskService {

    private TaskRepository taskRepository;
    private TaskBuilder taskBuilder;

    public TaskService(TaskRepository taskRepository, TaskBuilder taskBuilder) {
        this.taskRepository = taskRepository;
        this.taskBuilder = taskBuilder;
        }

    //Crea una nueva tarea, parametros minimos: texto obligatorio y fecha de caducidad
    public boolean createTask (String text, LocalDateTime expirationDate) throws InvalidTaskException, SQLException {

        //El texto no puede entrar vacio
        if(text == null || text.trim().isEmpty() || text.trim().isBlank()){
            throw new InvalidTaskException("Text can't be empty");
        }

        //La fecha de caducidad no puede ser anterior a la actual

        if(expirationDate != null && expirationDate.isBefore(LocalDateTime.now())){
            throw new InvalidTaskException("Expiration Date can't be before the current date");
        }

        //Ponemos a cero los valores del taskBuilder
        taskBuilder.reset();
        //Generamos la task.
        taskBuilder.text(text.trim()).expirationDate(expirationDate).build();

        //Eliminar la linea siguiente en version final
        return true;

        //return TaskRepository.save(task);
    }

    public List<TaskOutputDTO> listPendentTasks() {
        // 1. Obtenemos las entidades del repositorio (que vienen del DAO)
        List<Task> tasks = taskRepository.listPendentTasks();
        // 2. Convertimos de Entidad a DTO para proteger el dominio
        return tasks.stream()
                .map(task -> new TaskOutputDTO(
                        task.getId(),
                        task.getText(),
                        task.getPriority(),
                        false
                ))
                .toList();
    }

    public void deleteTask(int id){
        Optional<Task> optionalTask = taskRepository.getTask(id);
        if(optionalTask.isEmpty()){
            throw new TaskNotFoundException("Task with ID: " + id + " not found");
        }
        taskRepository.deleteTask(id);
    }
/*
    public Task getTaskById (int id) throws SQLException {
    return taskRepository.findbyId(id);
    }
    */

}
