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
	private TaskCLIActions actions;
    public TaskCLI(TaskCLIActions actions) {
		this.actions = actions;
    }

    public void taskMenu() {
        int option = -1;
        while (option != 0) {
            displayMenu();
            try {
                option = ConsoleInputUtils.readInt("Choose an option number : ");
                switch (option) {
                    case 1 -> {
                        actions.createNewTask();
                    }
                    case 2 -> actions.listAllTasksCLI();
                    case 3 -> actions.listPendentTasksCLI();
                    case 4 -> actions.askTaskToDelete();
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

}
