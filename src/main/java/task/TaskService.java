package task;

import com.itAcademy.agenda.common.exception.InvalidTaskException;

import java.sql.SQLException;
import java.time.LocalDateTime;
import com.itAcademy.agenda.common.exception.*;
import task.Task;
import task.TaskBuilder;
//este import esta de relleno, es la importación del TaskRepository
//import task.TaskRepository;

import java.sql.SQLException;
import java.util.List;
public class TaskService {

    //private TaskRepository taskRepository;
    private TaskBuilder taskBuilder;

    public TaskService(){
        this.taskBuilder = new TaskBuilder();
        //this.taskRepository = new TaskRepository();

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

    /*
    public List<Task> getAllTasks() throws SQLException {
        return taskRepository.findAll();
    }

    public Task getTaskById (int id) throws SQLException {
    return taskRepository.findbyId(id);
    }
    */

}
