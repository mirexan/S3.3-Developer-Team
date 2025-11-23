package com.itAcademy.agenda.task.cli;

import com.itAcademy.agenda.common.exception.InvalidInputException;
import com.itAcademy.agenda.common.utils.ConsoleInputUtils;
import com.itAcademy.agenda.task.service.TaskService;

import java.io.Console;

public class TaskCLI {
	private final TaskService taskService;
	public TaskCLI(TaskService taskService) {
		this.taskService = taskService;
	}
	public void taskMenu() {
		int option = -1;
		while (option != 0) {
			displayMenu();
			try{
				option = ConsoleInputUtils.readInt("Choose an option number");
				switch (option) {
					case 1 -> {
						//pedir propiedades de tarea
						taskService.createTask();
					}
					//case 2 -> taskService.listPendentTasks();
					case 3 -> taskService.deleteTask();
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
		System.out.println("\n==================================\n"
				+ "     Task Management Menu    \n"
				+ "==================================\n"
				+ "1. Create new Task\n"
				+ "2. List not completed Tasks\n"
				+ "3. Delete a Task\n"
				+ "0. Go back to Main Menu\n"
				+ "----------------------------------");
	}
}
