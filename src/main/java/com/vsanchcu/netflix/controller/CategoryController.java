/*
 * Category's RestController
 * 
 * @author: VSANCHCU
 * @version: 1.0
 */
package com.vsanchcu.netflix.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vsanchcu.netflix.exception.NetflixException;
import com.vsanchcu.netflix.exception.NetflixNotFoundException;
import com.vsanchcu.netflix.model.CategoryRestModel;
import com.vsanchcu.netflix.response.NetflixResponse;
import com.vsanchcu.netflix.service.CategoryServiceI;
import com.vsanchcu.netflix.util.ConstCommon;
import com.vsanchcu.netflix.util.ConstRest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * The Class CategoryController.
 */
@Api(tags = "Category's Controller")
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
	@ApiOperation(value = "Consultar todas las categorías")
	@ApiResponses({@ApiResponse(code = 200, message = "OK. La consulta se ha realizado correctamente.")})
	@GetMapping
	public NetflixResponse<List<CategoryRestModel>> getCategories() {
		return new NetflixResponse<List<CategoryRestModel>>(ConstCommon.SUCCESS, HttpStatus.OK, ConstCommon.OK, 
				categoryService.getCategories());
	}
	
	/**
	 * Gets the category by id.
	 *
	 * @param categoryId: category's id
	 * @return the category
	 * @throws NetflixNotFoundException Category doesn't exist
	 */
	@ApiOperation(value = "Consultar categoría por Id")
	@ApiResponses({@ApiResponse(code = 200, message = "OK. La consulta se ha realizado correctamente."), 
					@ApiResponse(code = 404, message = "La categoría no está registrada en BD")})
	@GetMapping(ConstRest.PATH_VAR_CATEGORY_ID)
	public NetflixResponse<CategoryRestModel> getCategoryById(
			@ApiParam(name = "categoryId", type = "Long", value = "Category's Id", example = "1", required = true) 
			@PathVariable Long categoryId) 
			throws NetflixException {
		return new NetflixResponse<CategoryRestModel>(ConstCommon.SUCCESS, HttpStatus.OK, ConstCommon.OK, 
				categoryService.getCategoryById(categoryId));
	}

}
