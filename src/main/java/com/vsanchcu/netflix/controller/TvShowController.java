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
	@GetMapping()
	NetflixResponse getTvShows(@RequestParam(required = false) List<Long> categoriesId) {
		NetflixResponse response;
		if (categoriesId != null) {
			final List<Category> categories = categoriesId.stream().map(category -> new Category(category)).collect(Collectors.toList());
			response = new NetflixResponse(HttpStatus.OK, ConstCommon.SUCCESS, ConstCommon.OK, 
					tvShowService.getTvShowsByCategoriesIn(categories));
		} else {
			response = new NetflixResponse(HttpStatus.OK, ConstCommon.SUCCESS, ConstCommon.OK, 
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
	@GetMapping(ConstRest.PATH_VAR_TV_SHOW_ID)
	NetflixResponse getTvShowById(@PathVariable Long tvShowId) 
			throws NetflixNotFoundException {
		return new NetflixResponse(HttpStatus.OK, ConstCommon.SUCCESS, ConstCommon.OK, 
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
	@PatchMapping(ConstRest.PATH_VAR_TV_SHOW_ID)
	NetflixResponse addTvShowCategories(
			@RequestParam List<Long> categoriesId, @PathVariable Long tvShowId) 
			throws NetflixException {
		return new NetflixResponse(HttpStatus.OK, ConstCommon.SUCCESS, ConstCommon.OK, 
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
		return new NetflixResponse(HttpStatus.OK, ConstCommon.SUCCESS, ConstCommon.OK, 
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
		return new NetflixResponse(HttpStatus.OK, ConstCommon.SUCCESS, ConstCommon.OK);
	}

}
