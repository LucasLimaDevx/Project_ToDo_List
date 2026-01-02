package com.lucasdevx.todo_list.controller;

import java.util.List;

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

import com.lucasdevx.todo_list.dto.request.CategoryRequestDTO;
import com.lucasdevx.todo_list.dto.response.CategoryResponseDTO;
import com.lucasdevx.todo_list.exception.ObjectIsNullException;
import com.lucasdevx.todo_list.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CategoryResponseDTO insert(@RequestBody CategoryRequestDTO categoryRequestDTO) {
		if(categoryRequestDTO == null) {
			throw new ObjectIsNullException();
		}
		
		
		return categoryService.insert(categoryRequestDTO);
	}
	
	@GetMapping("/{id}")
	public CategoryResponseDTO findById(@PathVariable Long id) {
		if(id <= 0) {
			throw new IllegalArgumentException("Invalid id");
		}
		
		return categoryService.findById(id);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CategoryResponseDTO> findAll(){
		return categoryService.findAll();
	}
	
	@PutMapping(value = "/{id}" ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CategoryResponseDTO update(@RequestBody CategoryRequestDTO categoryRequestDTO,@PathVariable Long id) {
		if(categoryRequestDTO == null) {
			throw new ObjectIsNullException();
		}
		
		if(id == null) {
			throw new NullPointerException("Id is null");
		}
		
		if(id <= 0) {
			throw new IllegalArgumentException("Invalid id");
		}

		
		return categoryService.update(categoryRequestDTO, id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		if(id <= 0) {
			throw new IllegalArgumentException("Invalid id");
		}
		
		categoryService.delete(id);
		
		return ResponseEntity.noContent().build();
	}
}
