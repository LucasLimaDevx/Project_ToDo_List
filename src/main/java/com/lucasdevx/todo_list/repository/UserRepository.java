package com.lucasdevx.todo_list.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucasdevx.todo_list.model.User;

public interface UserRepository  extends JpaRepository<User, Long>{

}
