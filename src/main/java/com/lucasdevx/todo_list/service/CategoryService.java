package com.lucasdevx.todo_list.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasdevx.todo_list.dto.CategoryDTO;
import com.lucasdevx.todo_list.exception.DatabaseException;
import com.lucasdevx.todo_list.exception.ObjectNotFoundException;
import com.lucasdevx.todo_list.model.Category;
import com.lucasdevx.todo_list.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repositoryCategory;
	
	public Category insert(Category category) {
		
		return repositoryCategory.save(category);
	}
	
	public Category findById(Long id) {
		
		return repositoryCategory.findById(id).orElseThrow(() -> new ObjectNotFoundException("Invalid id"));
	}
	
	public List<Category> findAll(){
		return repositoryCategory.findAll();
	}
	
	public Category update(Category category) {
		Category currentCategory = findById(category.getId());
		
		currentCategory.setName(category.getName());
		currentCategory.setColor(category.getColor());
		
		
		return repositoryCategory.save(currentCategory);
			
	}
	
	public void delete(Long id) {
		Category category =  findById(id);
		
		if(!category.getTasks().isEmpty()) {
			throw new DatabaseException("Integrity database");
		}
		
		repositoryCategory.deleteById(id);
	
	}
	
	public Category parseToEntity(CategoryDTO categoryDTO) {
		Category Category = new Category();
		
		Category.setId(categoryDTO.id());
		Category.setName(categoryDTO.name());
		Category.setColor(categoryDTO.color());
	
		
		return Category;
	}
	
	public CategoryDTO parseToDTO(Category category) {
		
		
		return new CategoryDTO(
				category.getId(),
				category.getName(),
				category.getColor(),
				category.getTasks());
	}
}
