/*
 * TvShow's Controller
 * 
 * @author: VSANCHCU
 * @version: 1.0
 */
package com.vsanchcu.netflix.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vsanchcu.netflix.entity.Category;
import com.vsanchcu.netflix.exception.NetflixException;
import com.vsanchcu.netflix.exception.NetflixNotFoundException;
import com.vsanchcu.netflix.response.NetflixResponse;
import com.vsanchcu.netflix.service.TvShowServiceI;
import com.vsanchcu.netflix.util.ConstCommon;
import com.vsanchcu.netflix.util.ConstRest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(ConstRest.RES_TVS_HOW)
public class TvShowController {

	@Autowired
	private TvShowServiceI tvShowService;

	/**
	 * Gets the tv shows (optional: by categories id).
	 *
	 * @param categoriesId: categories' id
	 * @return the tv shows
	 */
	@ApiOperation(value = "Consultar todas las series o por categorías")
	@ApiResponses({@ApiResponse(code = 200, message = "OK. La consulta se ha realizado correctamente.")})
	@GetMapping()
	NetflixResponse getTvShows(
			@ApiParam(name = "categoriesId", type = "List<Long>", value = "List of Category's Id", example = "{1, 2}", required = true) 
			@RequestParam(required = false) List<Long> categoriesId) {
		NetflixResponse response;
		if (categoriesId != null) {
			final List<Category> categories = categoriesId.stream().map(category -> new Category(category)).collect(Collectors.toList());
			response = new NetflixResponse(ConstCommon.SUCCESS, HttpStatus.OK.value(), ConstCommon.OK, 
					tvShowService.getTvShowsByCategoriesIn(categories));
		} else {
			response = new NetflixResponse(ConstCommon.SUCCESS, HttpStatus.OK.value(), ConstCommon.OK, 
					tvShowService.getTvShows());
		}
		return response;
	}
	
	/**
	 * Gets the tv-show by id.
	 *
	 * @param tvShowId: tv-show's id
	 * @return the tv show
	 * @throws NetflixNotFoundException Tv-show doesn't exist
	 */
	@ApiOperation(value = "Consultar serie")
	@ApiResponses({@ApiResponse(code = 200, message = "OK. La consulta se ha realizado correctamente."),
					@ApiResponse(code = 404, message = "La serie no está registrada en BD")})
	@GetMapping(ConstRest.PATH_VAR_TV_SHOW_ID)
	NetflixResponse getTvShowById(
			@ApiParam(name = "tvShowId", type = "Long", value = "Tv show's Id", example = "1", required = true) 
			@PathVariable Long tvShowId) 
			throws NetflixNotFoundException {
		return new NetflixResponse(ConstCommon.SUCCESS, HttpStatus.OK.value(), ConstCommon.OK, 
				tvShowService.getTvShowById(tvShowId));
	}
	
	/**
	 * Adds the categories to tv-show.
	 *
	 * @param categoriesId: list of category's id
	 * @param tvShowId: tv-show's id
	 * @return the updated tv-show
	 * @throws 	NetflixNotFoundException Tv-Show or category doesn't exist
	 * 			NetflixException Error
	 */
	@PatchMapping(ConstRest.PATH_VAR_TV_SHOW_ID + ConstRest.RES_CATEGORY)
	NetflixResponse addTvShowCategories(
			@RequestParam List<Long> categoriesId, @PathVariable Long tvShowId) 
			throws NetflixException {
		return new NetflixResponse(ConstCommon.SUCCESS, HttpStatus.OK.value(), ConstCommon.OK, 
				tvShowService.updateTvShowCategories(tvShowId, categoriesId));
	}
	
	/**
	 * Update tv-show's name.
	 *
	 * @param name: name
	 * @param tvShowId: tv-show's id
	 * @return the updated tv-show
	 * @throws 	NetflixNotFoundException Tv-show doesn't exist
	 * 			NetflixException Error
	 */
	@PatchMapping(ConstRest.PATH_VAR_TV_SHOW_ID)
	NetflixResponse updateTvShowName(@RequestParam String name, @PathVariable Long tvShowId) 
			throws NetflixException {
		return new NetflixResponse(ConstCommon.SUCCESS, HttpStatus.OK.value(), ConstCommon.OK, 
				tvShowService.updateTvShowName(tvShowId, name));
	}

	/**
	 * Delete tv-show.
	 *
	 * @param tvShowId: tv-show's id
	 * @return response
	 * @throws 	NetflixNotFoundException Tv-show doesn't exist
	 * 			NetflixException Error
	 */
	@DeleteMapping(ConstRest.PATH_VAR_TV_SHOW_ID)
	NetflixResponse deleteTvShow(@PathVariable Long tvShowId) throws NetflixException {
		tvShowService.deleteTvShow(tvShowId);
		return new NetflixResponse(ConstCommon.SUCCESS, HttpStatus.OK.value(), ConstCommon.OK);
	}

}
