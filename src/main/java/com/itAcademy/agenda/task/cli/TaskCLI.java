package com.itAcademy.agenda.task.cli;

import com.itAcademy.agenda.common.exception.InvalidInputException;
import com.itAcademy.agenda.common.utils.ConsoleInputUtils;

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
                    case 1 -> actions.createNewTask();
                    case 2 -> actions.listAllTasksCLI();
                    case 3 -> actions.listCompletedTasksCLI();
                    case 4 -> actions.listPendentTasksCLI();
                    case 5 -> actions.askTaskToDelete();
                    case 0 -> System.out.println("Going back to Main Menu");
                    default -> System.out.println("Invalid option. Try again");
                }
            } catch (InvalidInputException e) {
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
                + "3. List completed tasks\n"
                + "4. List not completed Tasks\n"
                + "5. Delete a Task\n"
                + "0. Go back to Main Menu\n"
                + "----------------------------------");
    }
}
