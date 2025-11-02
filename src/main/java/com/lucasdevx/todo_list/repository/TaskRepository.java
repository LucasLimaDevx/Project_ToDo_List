package com.lucasdevx.todo_list.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucasdevx.todo_list.model.Task;

public interface TaskRepository   extends JpaRepository<Task, Long>{

}
