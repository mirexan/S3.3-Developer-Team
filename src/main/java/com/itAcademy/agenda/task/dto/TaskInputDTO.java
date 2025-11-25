package com.itAcademy.agenda.task.dto;

import java.time.LocalDateTime;

public record TaskInputDTO(
	String title,
	LocalDateTime deadline
	){
}
