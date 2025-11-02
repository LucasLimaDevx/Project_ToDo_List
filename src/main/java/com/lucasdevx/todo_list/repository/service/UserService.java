package com.lucasdevx.todo_list.repository.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		
		return repositoryUser.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid id"));
	}
	
	public List<User> findAll(){
		return repositoryUser.findAll();
	}
}
