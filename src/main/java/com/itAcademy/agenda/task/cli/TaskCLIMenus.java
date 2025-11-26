package com.itAcademy.agenda.task.cli;

public class TaskCLIMenus {
    public void displayMainMenu() {
        System.out.println("""
                :::::::::::::::::::::::::::::::::
                   \uD83D\uDCD2  TASK - Menu  \uD83D\uDCD2
                :::::::::::::::::::::::::::::::::
                1. Create new Task
                2. Update task status
                3. List tasks
                4. Update task
                5. Delete a Task
                0. Go back to Main Menu
                ---------------------------------""");
    }

    public void displayListTasksMenu() {
        System.out.println("""
                :::::::::::::::::::::::::::::::::
                   \uD83D\uDCD2  LIST TASKS - Menu  \uD83D\uDCD2
                :::::::::::::::::::::::::::::::::
                1. List all tasks
                2. List completed tasks
                3. List not completed Tasks
                0. Go back
                """);
    }

    public void displayUpdateTaskMenu() {
        System.out.println("""
                :::::::::::::::::::::::::::::::::
                   \uD83D\uDCD2  UPDATE TASK - Menu  \uD83D\uDCD2
                :::::::::::::::::::::::::::::::::
                1. Update the text of a Task
                2. Update the priority of a Task
                3. Update the expiration date of a Task
                0. Go back
                """);
    }
}