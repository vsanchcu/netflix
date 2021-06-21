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

import com.vsanchcu.netflix.exception.NetflixException;
import com.vsanchcu.netflix.exception.NetflixNotFoundException;
import com.vsanchcu.netflix.model.CategoryRestModel;
import com.vsanchcu.netflix.repository.CategoryRepository;
import com.vsanchcu.netflix.util.ConstException;

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
	 * @param id: category's id
	 * @return the category or null
	 * @throws NetflixNotFoundException 
	 */
	@Override
	public CategoryRestModel getCategoryById(Long id) 
			throws NetflixException {
		return categoryRepository.findById(id)
				.map(category -> modelMapper.map(category, CategoryRestModel.class))
				.orElseThrow(() -> new NetflixNotFoundException(ConstException.MSG_NON_EXIST_CATEGORY));
	}

}
