package com.lucasdevx.todo_list.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasdevx.todo_list.dto.TaskDTO;
import com.lucasdevx.todo_list.exception.ObjectNotFoundException;
import com.lucasdevx.todo_list.model.Task;
import com.lucasdevx.todo_list.repository.TaskRepository;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepository repositoryTask;
	
	public Task insert(Task task) {
		
		return repositoryTask.save(task);
	}
	
	public Task findById(Long id) {
		
		return repositoryTask.findById(id).orElseThrow(() -> new ObjectNotFoundException("Invalid id"));
	}
	
	public List<Task> findAll(){
		return repositoryTask.findAll();
	}
	
	public Task update(Task task) {
		Task currentTask = findById(task.getId());
		
		currentTask.setTitle(task.getTitle());
		currentTask.setDescription(task.getDescription());
		currentTask.setDueDate(task.getDueDate());
		currentTask.setStatus(task.getStatus());
		currentTask.setUser(task.getUser());
		currentTask.setCategory(task.getCategory());
		
		return repositoryTask.save(currentTask);
			
	}
	
	public void delete(Long id) {
		repositoryTask.deleteById(id);
	}
	
	public Task parseToEntity(TaskDTO taskDTO) {
		Task task = new Task();
		
		task.setId(taskDTO.id());
		task.setTitle(taskDTO.title());
		task.setDescription(taskDTO.description());
		task.setDueDate(taskDTO.dueDate());
		task.setStatus(taskDTO.status());
		task.setUser(taskDTO.user());
		task.setCategory(taskDTO.category());
		
		return task;
	}
	
	public TaskDTO parseToDTO(Task task) {
		
		
		return new TaskDTO(
				task.getId(),
				task.getTitle(),
				task.getDescription(),
				task.getDueDate(),
				task.getStatus(),
				task.getUser(),
				task.getCategory()
				);
	}
}
