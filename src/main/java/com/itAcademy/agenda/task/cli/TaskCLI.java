package com.itAcademy.agenda.task.cli;

import com.itAcademy.agenda.common.exception.InvalidInputException;
import com.itAcademy.agenda.common.utils.ConsoleInputUtils;

public class TaskCLI {
    private final TaskCLIActions actions;
    private final TaskCLIMenus menus;

    public TaskCLI(TaskCLIActions actions, TaskCLIMenus menus) {
        this.actions = actions;
        this.menus = menus;
    }

    public void taskMenu() {
        int option = -1;
        while (option != 0) {
            menus.displayMainMenu();
            try {
                option = ConsoleInputUtils.readInt("Choose an option number : ");
                switch (option) {
                    case 1 -> actions.createNewTask();
                    case 2 -> actions.updateTaskStatusCLI();
                    case 3 -> listTasksMenu();
                    case 4 -> listUpdateOptions();
                    case 5 -> actions.askTaskToDelete();
                    case 0 -> System.out.println("Going back to Main Menu");
                    default -> System.out.println("Invalid option. Try again");
                }
            } catch (InvalidInputException e) {
                System.err.println("Input error, please introduce a number between 1 and 5!");
            }
        }
    }

    public void listTasksMenu() {
        int option = -1;
        while (option != 0) {
            menus.displayListTasksMenu();
            try {
                option = ConsoleInputUtils.readInt("Choose an option number: ");
                switch (option) {
                    case 1 -> actions.listAllTasksCLI();
                    case 2 -> actions.listCompletedTasksCLI();
                    case 3 -> actions.listPendentTasksCLI();
                    case 0 -> System.out.println("Going back to Task Menu");
                    default -> System.out.println("Invalid option. Try again");
                }
            } catch (InvalidInputException e) {
                System.err.println("Input error, please introduce a number between 1 and 3!");
            }
        }
    }

    public void listUpdateOptions() {
        int option = -1;
        while (option != 0) {
            menus.displayUpdateTaskMenu();
            try {
                option = ConsoleInputUtils.readInt("Choose an option number: ");
                switch (option) {
                    case 1 -> actions.updateTaskTextCLI();
                    case 2 -> actions.updateTaskPriorityCLI();
                    case 3 -> actions.updateTaskExpirationDateCLI();
                    case 0 -> System.out.println("Going back to Task Menu");
                    default -> System.out.println("Invalid option. Try again");
                }
            } catch (InvalidInputException e) {
                System.err.println("Input error, please introduce a number between 1 and 3!");
            }
        }
    }
}