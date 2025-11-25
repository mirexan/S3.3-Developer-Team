package com.itAcademy.agenda.application.menu;

import com.itAcademy.agenda.common.exception.InvalidInputException;
import com.itAcademy.agenda.common.utils.ConsoleInputUtils;
import com.itAcademy.agenda.task.cli.TaskCLI;

public class AppMainMenu {
	private TaskCLI taskCLI;
	public void initializeApp() {
		this.taskCLI = TaskModule.createModule();
	}
	public void menuSelector(){
		initializeApp();
		int option = -1;
		while(option != 0){
			DisplayMainMenu.displayMenu();
			try{
				option = ConsoleInputUtils.readInt("Choose an option: ");
				switch(option){
					case 1 -> {
						System.out.println("Accessing to Task manager...");
						taskCLI.taskMenu();
					}
					case 2 -> System.out.println("Accessing to Notes manager...");
					case 3 -> System.out.println("Accessing to Event manager...");
					case 0 -> System.out.println("Leaving Agenda Application.See you soon!");
					default -> System.out.println("Invalid option. Try again");
				}
			}
			catch (InvalidInputException e){
					System.err.println("Error: " + e.getMessage());
				}
		}
		ConsoleInputUtils.closeScanner();
	}


}
