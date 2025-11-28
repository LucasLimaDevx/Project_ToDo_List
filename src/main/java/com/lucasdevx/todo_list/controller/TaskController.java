package com.lucasdevx.todo_list.controller;

import java.util.List;
import java.util.stream.Collectors;

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

import com.lucasdevx.todo_list.dto.TaskDTO;
import com.lucasdevx.todo_list.exception.ObjectIsNullException;
import com.lucasdevx.todo_list.model.Task;
import com.lucasdevx.todo_list.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public TaskDTO insert(@RequestBody TaskDTO taskDTO) {
		if(taskDTO == null) {
			throw new ObjectIsNullException();
		}
		Task task = taskService.parseToEntity(taskDTO);
		
		return taskService.parseToDTO(taskService.insert(task));
	}
	
	@GetMapping("/{id}")
	public TaskDTO findById(@PathVariable Long id) {
		if(id <= 0) {
			throw new IllegalArgumentException("Invalid id");
		}
		return taskService.parseToDTO(taskService.findById(id));
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TaskDTO> findAll(){
		
		List<Task> tasks = taskService.findAll();
		List<TaskDTO> taskDTO = tasks.stream()
				.map(task -> taskService.parseToDTO(task))
				.collect(Collectors.toList());
		
		return taskDTO;
	}
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public TaskDTO update(@RequestBody TaskDTO taskDTO) {
		if(taskDTO == null) {
			throw new ObjectIsNullException();
		}
		
		if(taskDTO.id() == null) {
			throw new NullPointerException("Id is null");
		}
		
		if(taskDTO.id() <= 0) {
			throw new IllegalArgumentException("Invalid id");
		}
		
		Task task = taskService.parseToEntity(taskDTO);
		
		return taskService.parseToDTO(taskService.update(task));
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
