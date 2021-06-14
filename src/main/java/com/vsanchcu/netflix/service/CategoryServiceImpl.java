/*
 * Category's Service Implementation
 * 
 * @author: VSANCHCU
 * @version: 1.0
 */
package com.vsanchcu.netflix.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vsanchcu.netflix.model.CategoryRestModel;
import com.vsanchcu.netflix.repository.CategoryRepository;

/**
 * The Class CategoryServiceImpl.
 */
@Service
public class CategoryServiceImpl implements CategoryServiceI {

	/** The category repository. */
	@Autowired
	private CategoryRepository categoryRepository;
	
	/** The model mapper. */
	private ModelMapper modelMapper = new ModelMapper();

	/**
	 * Gets the categories.
	 *
	 * @return the categories
	 */
	@Override
	public List<CategoryRestModel> getCategories() {
		return categoryRepository.findAll().stream()
				.map(category -> modelMapper.map(category, CategoryRestModel.class))
				.collect(Collectors.toList());
	}

	/**
	 * Gets the category by id.
	 *
	 * @param id the id
	 * @return the category by id
	 */
	@Override
	public CategoryRestModel getCategoryById(Long id) {
		return categoryRepository.findById(id)
				.map(category -> modelMapper.map(category, CategoryRestModel.class))
				.orElse(null);
	}

}
