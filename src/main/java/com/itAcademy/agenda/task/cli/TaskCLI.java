package com.itAcademy.agenda.task.cli;

import com.itAcademy.agenda.common.exception.InvalidInputException;
import com.itAcademy.agenda.common.utils.ConsoleInputUtils;

public class TaskCLI {
    private final TaskCLIActions actions;

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
                    case 1 -> actions.createNewTask();
                    case 2 -> actions.markCompletedTaskCLI();
                    case 3 -> actions.listAllTasksCLI();
                    case 4 -> actions.listCompletedTasksCLI();
                    case 5 -> actions.listPendentTasksCLI();
                    case 6 -> actions.askTaskToDelete();
                    case 0 -> System.out.println("Going back to Main Menu");
                    default -> System.out.println("Invalid option. Try again");
                }
            } catch (InvalidInputException e) {
                System.err.println("Input error, please introduce a number between 1 and 3!");
            }
        }
    }

    public void displayMenu() {
        System.out.println("""

				:::::::::::::::::::::::::::::::::
				   \uD83D\uDCD2  TASK - Menu  \uD83D\uDCD2 \s
				:::::::::::::::::::::::::::::::::
				1. Create new Task
				2. Mark Task as completed
				3. List all tasks
				4. List completed tasks
				5. List not completed Tasks
				6. Delete a Task
				0. Go back to Main Menu
				----------------------------------""");
    }
}
