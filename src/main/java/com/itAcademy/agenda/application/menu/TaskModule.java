package com.itAcademy.agenda.application.menu;

import com.itAcademy.agenda.task.cli.TaskCLI;
import com.itAcademy.agenda.task.cli.TaskCLIActions;
import com.itAcademy.agenda.task.cli.TaskCLIMenus;
import com.itAcademy.agenda.task.repository.MySQLTaskDAOAdapter;
import com.itAcademy.agenda.task.repository.TaskRepository;
import com.itAcademy.agenda.task.service.TaskBuilder;
import com.itAcademy.agenda.task.service.TaskService;

public class TaskModule {
	public static TaskCLI createModule() {

		MySQLTaskDAOAdapter dao = new MySQLTaskDAOAdapter();
		TaskRepository repo = new TaskRepository(dao);
		TaskBuilder builder = new TaskBuilder();
		TaskService service = new TaskService(repo, builder);
		TaskCLIActions actions = new TaskCLIActions(service);
        TaskCLIMenus menus = new TaskCLIMenus();
		return new TaskCLI(actions, menus);
	}
}
