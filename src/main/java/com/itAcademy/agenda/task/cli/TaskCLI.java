package com.itAcademy.agenda.task.cli;

import com.itAcademy.agenda.common.exception.InvalidInputException;
import com.itAcademy.agenda.common.exception.InvalidTaskException;
import com.itAcademy.agenda.common.exception.TaskNotFoundException;
import com.itAcademy.agenda.common.utils.ConsoleInputUtils;
import com.itAcademy.agenda.task.dto.TaskOutputDTO;
import com.itAcademy.agenda.task.service.TaskService;

import java.time.LocalDateTime;
import java.util.List;


public class TaskCLI {
    private final TaskService taskService;

    public TaskCLI(TaskService taskService) {
        this.taskService = taskService;
    }

    public void taskMenu() {
        int option = -1;
        while (option != 0) {
            displayMenu();
            try {
                option = ConsoleInputUtils.readInt("Choose an option number : ");
                switch (option) {
                    case 1 -> {
                        createNewTask();
                    }
                    case 2 -> listAllTasksCLI();
                    case 3 -> listPendentTasksCLI();
                    case 4 -> askTaskToDelete();
					case 0 -> System.out.println("Going back to Main Menu");
					default -> System.out.println("Invalid option. Try again");
				}
			}
			 catch (InvalidInputException e){
				System.err.println("Input error, please introduce a number between 1 and 3!");
			 }
		}
	}
	public void displayMenu() {
		System.out.println("\n:::::::::::::::::::::::::::::::::\n"
				+ "   \uD83D\uDCD2  TASK - Menu  \uD83D\uDCD2  \n"
				+ ":::::::::::::::::::::::::::::::::\n"
				+ "1. Create new Task\n"
				+ "2. List all tasks\n"
				+ "3. List not completed Tasks\n"
				+ "4. Delete a Task\n"
				+ "0. Go back to Main Menu\n"
				+ "----------------------------------");
	}


    private void createNewTask() {
        try {
            System.out.println("\n--- Nueva Tarea ---");
            String text = ConsoleInputUtils.readString("Descripción de la tarea: ");
            // Simplificación de fecha para el ejemplo
            LocalDateTime expiration = LocalDateTime.now().plusDays(1);

			taskService.createTask(text, expiration);
			System.out.println("✅ Tarea creada correctamente.");
		} catch (InvalidTaskException e) {
			System.err.println("Error de validación: " + e.getMessage());
		}
	}
	private void askTaskToDelete() {
		try{
			int id = ConsoleInputUtils.readInt("Please enter the task ID you want to delete : ");
			String confirmation = ConsoleInputUtils.readString("Are you sure that you want " +
					"to eliminate task with ID : " + id + "? (Y/N): ");
			if(confirmation.equalsIgnoreCase("Y")) {
				taskService.deleteTask(id);
				System.out.println("Task Deleted Successfully");
				return;
			}
			System.out.println("The Operation was cancelled, Task hasn't been deleted");
		}
		catch (TaskNotFoundException e){
			System.out.printf("Error : " + e.getMessage());
		}
		catch (InvalidInputException e){
			System.out.println("Input error, please introduce an id number");
		}
	}
	public void listAllTasksCLI() {
		System.out.print("\n --- All Tasks ---\n");
		try {
			List<TaskOutputDTO> tasks = taskService.listAllTasks();
			tasks.forEach(taskOutputDTO -> System.out.println(taskOutputDTO.toString()));
		} catch (TaskNotFoundException e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
	public void listPendentTasksCLI() {
		System.out.print("\n --- Not completed Tasks ---\n");
		List<TaskOutputDTO> tasks = taskService.listPendentTasks();
		if(tasks.isEmpty()){
			System.out.println("No Tasks Found");
			return ;
		}
		tasks.forEach(taskOutputDTO -> System.out.println(taskOutputDTO.toString()));
	}
}
