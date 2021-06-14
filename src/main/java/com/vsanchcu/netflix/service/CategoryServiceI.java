/*
 * Category's Service Interface
 * 
 * @author: VSANCHCU
 * @version: 1.0
 */
package com.vsanchcu.netflix.service;

import java.util.List;

import com.vsanchcu.netflix.model.CategoryRestModel;

/**
 * The Interface CategoryServiceI.
 */
public interface CategoryServiceI {

	List<CategoryRestModel> getCategories();
	CategoryRestModel getCategoryById(Long id);

}
