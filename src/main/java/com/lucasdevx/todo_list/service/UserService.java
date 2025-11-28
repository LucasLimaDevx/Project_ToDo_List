package com.lucasdevx.todo_list.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasdevx.todo_list.dto.UserDTO;
import com.lucasdevx.todo_list.exception.ObjectNotFoundException;
import com.lucasdevx.todo_list.model.User;
import com.lucasdevx.todo_list.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repositoryUser;
	
	public User insert(User user) {
		
		return repositoryUser.save(user);
	}
	
	public User findById(Long id) {
		
		return repositoryUser.findById(id).orElseThrow(() -> new ObjectNotFoundException("Invalid id"));
	}
	
	public List<User> findAll(){
		return repositoryUser.findAll();
	}
	
	public User update(User user){
		User currentUser = findById(user.getId());
		
		currentUser.setName(user.getName());
		currentUser.setEmail(user.getEmail());
		
		return repositoryUser.save(currentUser);
			
	}
	
	public void delete(Long id) {
		repositoryUser.deleteById(id);
	}
	
	public User parseToEntity(UserDTO userDTO) {
		User user = new User();
		
		user.setId(userDTO.id());
		user.setName(userDTO.name());
		user.setEmail(userDTO.email());
		
		return user;
	}
	
	public UserDTO parseToDTO(User user) {
		
		
		return new UserDTO(
				user.getId(),
				user.getName(),
				user.getEmail(),
				user.getTasks());
	}
}
