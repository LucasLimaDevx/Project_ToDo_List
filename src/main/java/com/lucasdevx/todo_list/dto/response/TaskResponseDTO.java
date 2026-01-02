package com.lucasdevx.todo_list.dto.response;

public record TaskResponseDTO(
		Long id, 
		String title, 
		String description,
		String dueDate, 
		String status,
		CategoryResponseDTO category) {

}
