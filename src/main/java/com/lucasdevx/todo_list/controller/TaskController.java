package com.lucasdevx.todo_list.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucasdevx.todo_list.dto.request.TaskRequestDTO;
import com.lucasdevx.todo_list.dto.response.TaskResponseDTO;
import com.lucasdevx.todo_list.exception.ObjectIsNullException;
import com.lucasdevx.todo_list.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public TaskResponseDTO insert(@RequestBody TaskRequestDTO taskRequestDTO) {
		if(taskRequestDTO == null) {
			throw new ObjectIsNullException();
		}
		
		return taskService.insert(taskRequestDTO);
	}
	
	@GetMapping(value ="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public TaskResponseDTO findById(@PathVariable Long id) {
		if(id <= 0) {
			throw new IllegalArgumentException("Invalid id");
		}
		return taskService.findById(id);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TaskResponseDTO> findAll(){
		
		return taskService.findAll();
	}
	@PutMapping(value = "/{id}" , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public TaskResponseDTO update(@RequestBody TaskRequestDTO taskRequestDTO, @PathVariable Long id) {
		if(taskRequestDTO == null) {
			throw new ObjectIsNullException();
		}
		
		if(id == null) {
			throw new NullPointerException("Id is null");
		}
		
		if(id <= 0) {
			throw new IllegalArgumentException("Invalid id");
		}
	
		
		return taskService.update(taskRequestDTO, id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		if(id <= 0) {
			throw new IllegalArgumentException("Invalid id");
		}
		taskService.delete(id);
		
		return ResponseEntity.noContent().build();
	}
}
