package com.lucasdevx.todo_list.dto.request;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public record TaskRequestDTO(
		String title, 
		String description,
		@JsonFormat(pattern = "dd/MM/yyyy HH:mm") LocalDateTime dueDate, 
		String status,	
		Long userId,
		Long categoryId) {

}
