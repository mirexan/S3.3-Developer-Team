package com.itAcademy.agenda.application.menu;

import com.itAcademy.agenda.common.exception.InvalidInputException;
import com.itAcademy.agenda.common.utils.ConsoleInputUtils;
import com.itAcademy.agenda.task.cli.TaskCLI;
import com.itAcademy.agenda.task.cli.TaskCLIActions;
import com.itAcademy.agenda.task.repository.MySQLTaskDAOAdapter;
import com.itAcademy.agenda.task.repository.TaskRepository;
import com.itAcademy.agenda.task.service.TaskBuilder;
import com.itAcademy.agenda.task.service.TaskService;


public class AppMainMenu {
	private TaskCLI taskCLI;
	// private NoteCLI noteCLI;
	// private EventCLI eventCLI;
	public void initializeApp(){
		MySQLTaskDAOAdapter mySQLDAOAdapter = new MySQLTaskDAOAdapter();
		TaskRepository taskRepository = new TaskRepository(mySQLDAOAdapter);
		TaskBuilder taskBuilder = new TaskBuilder();
		TaskService taskService = new TaskService(taskRepository, taskBuilder);
		TaskCLIActions taskActions = new TaskCLIActions(taskService);
		this.taskCLI =  new TaskCLI(taskActions);
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
