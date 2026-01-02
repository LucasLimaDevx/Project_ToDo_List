package com.lucasdevx.todo_list.service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasdevx.todo_list.dto.request.TaskRequestDTO;
import com.lucasdevx.todo_list.dto.response.CategoryResponseDTO;
import com.lucasdevx.todo_list.dto.response.TaskResponseDTO;
import com.lucasdevx.todo_list.exception.ObjectNotFoundException;
import com.lucasdevx.todo_list.model.Category;
import com.lucasdevx.todo_list.model.Task;
import com.lucasdevx.todo_list.model.User;
import com.lucasdevx.todo_list.repository.CategoryRepository;
import com.lucasdevx.todo_list.repository.TaskRepository;
import com.lucasdevx.todo_list.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepository repositoryTask;
	
	@Autowired
	private UserRepository repositoryUser;
	
	@Autowired
	private CategoryRepository repositoryCategory;
	
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	
	public TaskResponseDTO insert(TaskRequestDTO taskRequestDTO) {
		Task task = parseToEntity(taskRequestDTO);
		
		return parseToDTO(repositoryTask.save(task));
	}
	
	public TaskResponseDTO findById(Long id) {
		
		return parseToDTO(repositoryTask.findById(id).orElseThrow(() -> new ObjectNotFoundException("Invalid id")));
	}
	
	public List<TaskResponseDTO> findAll(){
		List<Task> tasks = repositoryTask.findAll();
		List<TaskResponseDTO> taskDTO = tasks.stream()
				.map(task -> parseToDTO(task))
				.collect(Collectors.toList());
		
		return taskDTO;
	}
	
	@Transactional
	public TaskResponseDTO update(TaskRequestDTO taskRequestDTO, Long id) {
		Task currentTask = repositoryTask.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Invalid id"));
		
		User user = repositoryUser.findById(taskRequestDTO.userId())
				.orElseThrow(() -> new ObjectNotFoundException("Invalid id"));
		
		Category category = repositoryCategory.findById(taskRequestDTO.categoryId())
				.orElseThrow(() -> new ObjectNotFoundException("Invalid id"));
		
		currentTask.setTitle(taskRequestDTO.title());
		currentTask.setDescription(taskRequestDTO.description());
		currentTask.setDueDate(taskRequestDTO.dueDate());
		currentTask.setStatus(taskRequestDTO.status());
		currentTask.setUser(user);
		currentTask.setCategory(category);
		
		return parseToDTO(currentTask);
			
	}
	
	public void delete(Long id) {
		findById(id);
		
		repositoryTask.deleteById(id);
		
	}
	
	public Task parseToEntity(TaskRequestDTO taskRequestDTO) {
		Task task = new Task();
		User user = repositoryUser.findById(taskRequestDTO.userId())
				.orElseThrow(() -> new ObjectNotFoundException("Invalid id"));
		
		Category category = repositoryCategory.findById(taskRequestDTO.categoryId())
				.orElseThrow(() -> new ObjectNotFoundException("Invalid id"));
		
		task.setTitle(taskRequestDTO.title());
		task.setDescription(taskRequestDTO.description());
		task.setDueDate(taskRequestDTO.dueDate());
		task.setStatus(taskRequestDTO.status());
		task.setUser(user);
		task.setCategory(category);
		
		return task;
	}
	
	public static TaskResponseDTO parseToDTO(Task task) {
		CategoryResponseDTO categoryResponseDTO = CategoryService.parseToDTO(task.getCategory());
		
		return new TaskResponseDTO(
				task.getId(),
				task.getTitle(),
				task.getDescription(),
				task.getDueDate().format(formatter),
				task.getStatus(),
				categoryResponseDTO);
	}
}
