package com.lucasdevx.todo_list.exception;

public class ObjectIsNullException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ObjectIsNullException() {
		super("Object is null");
	}
}
