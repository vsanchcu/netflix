/*
 * 
 * 
 * @author: VSANCHCU
 * @version: 1.0
 */
package com.vsanchcu.netflix.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vsanchcu.netflix.entity.Category;
import com.vsanchcu.netflix.entity.TvShow;
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
	
	@PatchMapping(ConstRest.PATH_VAR_TV_SHOW_ID)
	NetflixResponse addCategories(@RequestParam List<Long> categoriesId, @PathVariable Long tvShowId) {
		final TvShow tvShow = tvShowService.findById(tvShowId);
		tvShow.getCategories().addAll(
				categoriesId.stream().map(category -> new Category(category))
				.collect(Collectors.toSet()));
		return new NetflixResponse(HttpStatus.OK, ConstCommon.SUCCESS, ConstCommon.OK, 
				tvShowService.updateTvShow(tvShow));
	}
	
	/**
	 * Update the tv-show's name.
	 *
	 * @param name: new tv-show's name
	 * @param tvShowId: tv-show's id
	 * @return 	OK: the response entity with the tv-show
	 * 			KO: the response entity with error message
	 */
	@PostMapping("/netflix/tv-shows/updateName/{series-id}")
	public ResponseEntity<Object> updateName(@RequestParam String name, @PathVariable(value = "series-id") Long tvShowId) {
		ResponseEntity<Object> result;
		final TvShow tvShow = tvShowService.findById(tvShowId);
		if (tvShow != null) {
			tvShow.setName(name);
			result = ResponseEntity.status(HttpStatus.OK).body(tvShowService.updateTvShow(tvShow));
		} else {
			final StringBuilder msj = new StringBuilder();
			msj.append("La serie con id ");
			msj.append(tvShowId);
			msj.append(" no está registrada en BD");
			result = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msj.toString());
		}
		return result;
	}

	/**
	 * Delete the tv-show.
	 *
	 * @param tvShowId: tv-show's id
	 * @return 	OK: the response entity with ok message
	 * 			KO: the response entity with error message
	 */
	@PostMapping("/netflix/tv-shows/delete/{series-id}")
	public ResponseEntity<String> delete(@PathVariable(value = "series-id") Long tvShowId) {
		ResponseEntity<String> result;
		try {
			tvShowService.deleteTvShow(tvShowId);
			result = ResponseEntity.status(HttpStatus.OK).body("La serie ha sido borrada correctamente");
		} catch (EmptyResultDataAccessException e) {
			final StringBuilder msj = new StringBuilder();
			msj.append("La serie con id ");
			msj.append(tvShowId);
			msj.append(" no está registrada en BD");
			result = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msj.toString());
		}
		return result;
	}

}
