package com.itAcademy.agenda.task.cli;

import com.itAcademy.agenda.common.exception.InvalidInputException;
import com.itAcademy.agenda.common.exception.InvalidTaskException;
import com.itAcademy.agenda.common.exception.TaskNotFoundException;
import com.itAcademy.agenda.common.utils.ConsoleInputUtils;
import com.itAcademy.agenda.task.dto.TaskOutputDTO;
import com.itAcademy.agenda.task.model.Priority;
import com.itAcademy.agenda.task.service.TaskService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class TaskCLIActions {
    private final TaskService taskService;

    public TaskCLIActions(TaskService taskService) {
        this.taskService = taskService;
    }

    public void printList(List<TaskOutputDTO> tasks) {
        tasks.forEach(task -> {
            String statusIcon = task.isCompleted() ? "✅ Completed" : "⏳ In Progress";
            String dateString = (task.deadline() != null) ? task.deadline().toLocalDate().toString()
                    : "---";
            String output = "🆔 ID: " + task.id() +
                    " | 📝 title: " + task.title() +
                    " | \uD83D\uDCC5 expiration date : " + dateString +
                    " | ⚡ Prioridad: " + task.priority() +
                    " | " + statusIcon;
            System.out.println(output);
        });
    }

    public void createNewTask() {
        try {
            System.out.println("\n--- New Task ---");
            String text = ConsoleInputUtils.readString("Insert task title : ");
            String tempExpDate = ConsoleInputUtils.readString("Type the expiration date of the task in days/month/year Hours:minutes (Leave Blank if you don't want to enter a date) :");
            LocalDateTime expirationDate = LocalDateTime.of(9999, 12, 31, 23, 59);

            if (tempExpDate != null && !tempExpDate.trim().isEmpty()){
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm.");
                expirationDate = LocalDateTime.parse(tempExpDate, dateFormat);
            }

            taskService.createTask(text, expirationDate);
            System.out.println("✅ Task has been created.");
        } catch (RuntimeException e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }

    }

    public void askTaskToDelete() {
        try {
            int id = ConsoleInputUtils.readInt("Please enter the task ID you want to delete : ");
            String confirmation = ConsoleInputUtils.readString("Are you sure that you want " +
                    "to eliminate task with ID : " + id + "? (Y/N): ");
            if (confirmation.equalsIgnoreCase("Y")) {
                taskService.deleteTask(id);
                System.out.println("Task Deleted Successfully");
                return;
            }
            System.out.println("The Operation was cancelled, Task hasn't been deleted");
        } catch (TaskNotFoundException e) {
            System.out.printf("Error : " + e.getMessage());
        } catch (InvalidInputException e) {
            System.out.println("Input error, please introduce an id number");
        }
    }

    public void listAllTasksCLI() {
        try {
            List<TaskOutputDTO> tasks = taskService.listAllTasks();
            System.out.print("\n --- All Tasks ---\n");
            printList(tasks);
        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void listCompletedTasksCLI() {
        try {
            List<TaskOutputDTO> tasks = taskService.listCompletedTasks();
            System.out.print("\n --- Completed Tasks ---\n");
            printList(tasks);
        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void listPendentTasksCLI() {
        System.out.print("\n --- Not completed Tasks ---\n");
        List<TaskOutputDTO> tasks = taskService.listPendentTasks();
        if (tasks.isEmpty()) {
            System.out.println("There are no tasks currently in progress or pending");
            return;
        }
        printList(tasks);
    }

    public void markCompletedTaskCLI() {
        try {
            int id = ConsoleInputUtils.readInt("Type the id of the task that you want to modify");

            System.out.println("1. Mark a task as completed");
            System.out.println("2. Mark a task as not completed");
            int option = ConsoleInputUtils.readInt("Choose an option");

            switch (option){
                case 1 -> taskService.markCompleteTask(id);
                case 2 -> taskService.notCompleteTask(id);
                default -> System.out.println("Choose an available option");
            }
        } catch (InvalidInputException | TaskNotFoundException e) {
            System.err.println("Error : " + e.getMessage());
        }
    }

    public void updateTaskTextCLI() {
        try {
            int id = ConsoleInputUtils.readInt("Type the id of the task that you want to update");
            //checkear id
            String newText = ConsoleInputUtils.readString("Please insert new text");
            taskService.updateTaskText(id, newText);
        } catch (TaskNotFoundException | InvalidInputException e) {
            System.err.println("Error : " + e.getMessage());
        }
    }

    public void updateTaskPriorityCLI() {
        try {
            int id = ConsoleInputUtils.readInt("Type the id of the task that you want to update");
            String tempPriority = ConsoleInputUtils.readString("type the new priority: LOW, MEDIUM or HIGH. Default is MEDIUM");
            tempPriority = tempPriority.toUpperCase();
            boolean isValid = tempPriority.equals("LOW") || tempPriority.equals("MEDIUM") || tempPriority.equals("HIGH");
            if (!isValid) {
                tempPriority = "MEDIUM";
            }
            Priority newPriority = Priority.valueOf(tempPriority);
            taskService.updateTaskPriority(id, newPriority);
        } catch (InvalidInputException e) {
            throw new RuntimeException(e);
        }


    }

    public void updateTaskExpirationDateCLI() {
        try {
            int id = ConsoleInputUtils.readInt("Type the id of the task that you want to update");
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            String tempExpDate = ConsoleInputUtils.readString("Type the new expiration date of the task in days/month/year Hours:minutes :");
            LocalDateTime expirationDate = LocalDateTime.parse(tempExpDate, dateFormat);
            taskService.updateTaskExpirationDate(id, expirationDate);
        } catch (InvalidInputException e) {
            throw new RuntimeException(e);
        } catch (InvalidTaskException e) {
            throw new RuntimeException(e);
        }
    }
}

