/*
 * Category's RestController
 * 
 * @author: VSANCHCU
 * @version: 1.0
 */
package com.vsanchcu.netflix.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.vsanchcu.netflix.model.CategoryRestModel;
import com.vsanchcu.netflix.service.CategoryServiceI;

/**
 * The Class CategoryController.
 */
@RestController
public class CategoryController {

	/** The category service. */
	@Autowired
	private CategoryServiceI categoryService;

	/**
	 * Gets the categories.
	 *
	 * @return the categories
	 */
	@GetMapping("/netflix/categories")
	public List<CategoryRestModel> getCategories() {
		return categoryService.getCategories();
	}
	
	/**
	 * Gets the category by id.
	 *
	 * @param id: category's id
	 * @return the category by id
	 */
	@GetMapping("/netflix/categories/{category-id}")
	public CategoryRestModel getCategoryById(@PathVariable(value = "category-id") Long id) {
		return categoryService.getCategoryById(id);
	}

}
