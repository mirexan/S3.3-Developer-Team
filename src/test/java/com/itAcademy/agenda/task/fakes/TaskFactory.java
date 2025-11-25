package com.itAcademy.agenda.task.fakes;

import com.itAcademy.agenda.task.model.Priority;
import com.itAcademy.agenda.task.model.Task;
import com.itAcademy.agenda.task.service.TaskBuilder;

import java.time.LocalDateTime;

public class TaskFactory {
	public static Task createFakeTask(Integer id, String text, boolean completed) {
		TaskBuilder builder = new TaskBuilder();
		builder.text(text);
		builder.completed(completed);
		builder.priority(Priority.MEDIUM);
		builder.expirationDate(LocalDateTime.now().plusDays(1));
		if (id != null) {
			builder.id(id);
			return builder.build();
		}
		builder.id(0);
		return builder.build();
	}
}
