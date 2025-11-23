package com.itAcademy.agenda.task.dto;

public record TaskOutputDTO(
		int id,
		String title,
		String priority,
		boolean isCompleted
) {
}