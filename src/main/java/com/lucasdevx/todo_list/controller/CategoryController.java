package com.lucasdevx.todo_list.controller;

import java.util.List;
import java.util.stream.Collectors;

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

import com.lucasdevx.todo_list.dto.CategoryDTO;
import com.lucasdevx.todo_list.exception.ObjectIsNullException;
import com.lucasdevx.todo_list.model.Category;
import com.lucasdevx.todo_list.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CategoryDTO insert(@RequestBody CategoryDTO categoryDTO) {
		if(categoryDTO == null) {
			throw new ObjectIsNullException();
		}
		
		Category category = categoryService.parseToEntity(categoryDTO);
		
		return categoryService.parseToDTO(categoryService.insert(category));
	}
	
	@GetMapping("/{id}")
	public CategoryDTO findById(@PathVariable Long id) {
		if(id <= 0) {
			throw new IllegalArgumentException("Invalid id");
		}
		
		return categoryService.parseToDTO(categoryService.findById(id));
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CategoryDTO> findAll(){
		
		List<Category> categories = categoryService.findAll();
		List<CategoryDTO> categoriesDTO = categories.stream()
				.map(category -> categoryService.parseToDTO(category))
				.collect(Collectors.toList());
		
		return categoriesDTO;
	}
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CategoryDTO update(@RequestBody CategoryDTO categoryDTO) {
		if(categoryDTO == null) {
			throw new ObjectIsNullException();
		}
		
		if(categoryDTO.id() == null) {
			throw new NullPointerException("Id is null");
		}
		
		if(categoryDTO.id() <= 0) {
			throw new IllegalArgumentException("Invalid id");
		}
		Category category = categoryService.parseToEntity(categoryDTO);
		
		return categoryService.parseToDTO(categoryService.update(category));
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
