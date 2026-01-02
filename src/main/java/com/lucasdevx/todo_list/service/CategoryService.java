package com.lucasdevx.todo_list.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasdevx.todo_list.dto.request.CategoryRequestDTO;
import com.lucasdevx.todo_list.dto.response.CategoryResponseDTO;
import com.lucasdevx.todo_list.exception.DatabaseException;
import com.lucasdevx.todo_list.exception.ObjectNotFoundException;
import com.lucasdevx.todo_list.model.Category;
import com.lucasdevx.todo_list.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repositoryCategory;
	
	public CategoryResponseDTO insert(CategoryRequestDTO categoryRequestDTO) {
		Category category = parseToEntity(categoryRequestDTO);
		
		return parseToDTO(repositoryCategory.save(category));
	}
	
	public CategoryResponseDTO findById(Long id) {
		
		return parseToDTO(repositoryCategory.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Invalid id")));
	}
	
	public List<CategoryResponseDTO> findAll(){
		List<Category> categories = repositoryCategory.findAll();
		List<CategoryResponseDTO> categoriesDTO = categories.stream()
				.map(category -> parseToDTO(category))
				.collect(Collectors.toList());
		
		return categoriesDTO;
	}
	
	public CategoryResponseDTO update(CategoryRequestDTO categoryRequestDTO, Long id) {
		Category currentCategory = repositoryCategory.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Invalid id"));
		
		currentCategory.setName(categoryRequestDTO.name());
		currentCategory.setColor(categoryRequestDTO.color());
		
		return parseToDTO(currentCategory);
			
	}
	
	public void delete(Long id) {
		Category category = repositoryCategory.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Invalid id"));
		
		if(!category.getTasks().isEmpty()) {
			throw new DatabaseException("Integrity database");
		}
		
		repositoryCategory.deleteById(id);
	
	}
	
	public Category parseToEntity(CategoryRequestDTO categoryResponseDTO) {
		Category Category = new Category();

		Category.setName(categoryResponseDTO.name());
		Category.setColor(categoryResponseDTO.color());
		return Category;
	}
	
	public static CategoryResponseDTO parseToDTO(Category category) {
		
		
		return new CategoryResponseDTO(
				category.getId(),
				category.getName(),
				category.getColor());
	}
}
