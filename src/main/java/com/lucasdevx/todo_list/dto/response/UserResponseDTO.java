package com.lucasdevx.todo_list.dto.response;

import java.util.Set;

public record UserResponseDTO(
		Long id, 
		String name,
		String email,
		Set<TaskResponseDTO> tasks) {

}
