package com.itAcademy.agenda.task.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record TaskOutputDTO(
		int id,
		String title,
		LocalDateTime deadline,
		String priority,
		boolean isCompleted
) {
}