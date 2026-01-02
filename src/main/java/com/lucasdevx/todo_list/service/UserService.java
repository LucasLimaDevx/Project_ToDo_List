package com.lucasdevx.todo_list.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasdevx.todo_list.dto.request.UserRequestDTO;
import com.lucasdevx.todo_list.dto.response.TaskResponseDTO;
import com.lucasdevx.todo_list.dto.response.UserResponseDTO;
import com.lucasdevx.todo_list.exception.DatabaseException;
import com.lucasdevx.todo_list.exception.ObjectNotFoundException;
import com.lucasdevx.todo_list.model.User;
import com.lucasdevx.todo_list.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repositoryUser;
	
	public UserResponseDTO insert(UserRequestDTO userRequestDTO) {
		User user = parseToEntity(userRequestDTO);
		
		return parseToDTO(repositoryUser.save(user));
	}
	
	public UserResponseDTO findById(Long id) {
		
		return parseToDTO(repositoryUser.findById(id).orElseThrow(() -> new ObjectNotFoundException("Invalid id")));
	}
	
	public List<UserResponseDTO> findAll(){
		List<User> users = repositoryUser.findAll();
		List<UserResponseDTO> usersDTO = users.stream()
				.map(user -> parseToDTO(user))
				.collect(Collectors.toList());
		
		return usersDTO;
	}
	
	public UserResponseDTO update(UserRequestDTO userRequestDTO, Long id){
		User currentUser = repositoryUser.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Invalid id"));
		
		currentUser.setName(userRequestDTO.name());
		currentUser.setEmail(userRequestDTO.email());
		
		return parseToDTO(repositoryUser.save(currentUser));
			
	}
	
	public void delete(Long id) {
		User user =  repositoryUser.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Invalid id"));
	
		if(!user.getTasks().isEmpty()) {
			throw new DatabaseException("Integrity database");
		}
		repositoryUser.deleteById(id);
		
	}
	
	public static User parseToEntity(UserRequestDTO userRequestDTO) {
		User user = new User();
		
		user.setName(userRequestDTO.name());
		user.setEmail(userRequestDTO.email());
		
		return user;
	}
	
	public static UserResponseDTO parseToDTO(User user) {
		Set<TaskResponseDTO> tasks = user.getTasks().stream()
				.map((task) -> TaskService.parseToDTO(task))
				.collect(Collectors.toSet());
		
		return new UserResponseDTO(
				user.getId(),
				user.getName(),
				user.getEmail(),
				tasks);
	}
}
