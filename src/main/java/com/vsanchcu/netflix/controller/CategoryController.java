/*
 * Category's RestController
 * 
 * @author: VSANCHCU
 * @version: 1.0
 */
package com.vsanchcu.netflix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vsanchcu.netflix.response.NetflixResponse;
import com.vsanchcu.netflix.service.CategoryServiceI;
import com.vsanchcu.netflix.util.ConstCommon;
import com.vsanchcu.netflix.util.ConstRest;

/**
 * The Class CategoryController.
 */
@RestController
@RequestMapping(ConstRest.RES_CATEGORY)
public class CategoryController {

	/** The category service. */
	@Autowired
	private CategoryServiceI categoryService;

	/**
	 * Gets the categories.
	 *
	 * @return the categories
	 */
	@GetMapping
	public NetflixResponse getCategories() {
		return new NetflixResponse(HttpStatus.OK, ConstCommon.SUCCESS, ConstCommon.OK, 
				categoryService.getCategories());
	}
	
	/**
	 * Gets the category by id.
	 *
	 * @param categoryId: category's id
	 * @return the category
	 */
	@GetMapping(ConstRest.PATH_VAR_CATEGORY_ID)
	public NetflixResponse getCategoryById(@PathVariable Long categoryId) {
		return new NetflixResponse(HttpStatus.OK, ConstCommon.SUCCESS, ConstCommon.OK, 
				categoryService.getCategoryById(categoryId));
	}

}
