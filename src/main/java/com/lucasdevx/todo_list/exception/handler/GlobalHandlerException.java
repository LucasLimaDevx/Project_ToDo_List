package com.lucasdevx.todo_list.exception.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.lucasdevx.todo_list.exception.ObjectIsNullException;
import com.lucasdevx.todo_list.exception.ObjectNotFoundException;
import com.lucasdevx.todo_list.exception.ResponseException;

@ControllerAdvice
public class GlobalHandlerException {
	

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ResponseException> handlerIllegalArgumentException(IllegalArgumentException exception, WebRequest request){
		
		ResponseException response = new ResponseException(
				LocalDateTime.now(), 
				exception.getMessage(), 
				request.getDescription(false));
		
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<ResponseException> handlerbjectNotFoundException(ObjectNotFoundException exception, WebRequest request){
		ResponseException response = new ResponseException(
				LocalDateTime.now(), 
				exception.getMessage(), 
				request.getDescription(false));
		
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ObjectIsNullException.class)
	public ResponseEntity<ResponseException> handlerObjectNotFoundException(ObjectIsNullException exception, WebRequest request){
		ResponseException response = new ResponseException(
				LocalDateTime.now(), 
				exception.getMessage(), 
				request.getDescription(false));
		
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<ResponseException> handlerNullPointerException(NullPointerException exception, WebRequest request){
		ResponseException response = new ResponseException(
				LocalDateTime.now(), 
				exception.getMessage(), 
				request.getDescription(false));
		
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}
