package com.itAcademy.agenda.application.menu;

import com.itAcademy.agenda.common.exception.InvalidInputException;
import com.itAcademy.agenda.common.utils.ConsoleInputUtils;


public class AppMainMenu {
	public void menuSelector(){
		int option = -1;
		while(option != 0){
			DisplayMainMenu.displayMenu();
			try{
				option = ConsoleInputUtils.readInt("Choose an option: ");
				switch(option){
					case 1 -> System.out.println("Accessing to Task manager...");
					//llamada al futuro menu de task.cli.displayMenu();
					case 2 -> System.out.println("Accessing to Notes manager...");
					//llamada a menu note.cli.displayMenu();
					case 3 -> System.out.println("Accessing to Event manager...");
					//event.cli.displayMenu();
					case 0 -> System.out.println("Leaving Agenda Application.See you soon!");
					default -> System.out.println("Invalid option. Try again");
				}
			}
			catch (InvalidInputException e){
					System.err.println("Error: " + e.getMessage());
				}
			catch(Exception e){
				System.err.println("Error: " + e.getMessage());
			}
		}
		ConsoleInputUtils.closeScanner();
	}

}
