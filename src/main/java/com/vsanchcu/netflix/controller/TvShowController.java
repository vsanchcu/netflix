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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vsanchcu.netflix.entity.Category;
import com.vsanchcu.netflix.entity.TvShow;
import com.vsanchcu.netflix.model.TvShowRestModel;
import com.vsanchcu.netflix.service.TvShowServiceI;

@RestController
public class TvShowController {

	@Autowired
	private TvShowServiceI tvShowService;

	/**
	 * Gets all tv-shows or the tv-shows by categories.
	 *
	 * @param categoriesId: categories' ids (non required)
	 * @return all tv-shows or the tv-shows by categories
	 */
	@GetMapping("/netflix/tv-shows")
	public List<TvShowRestModel> getTvShowsByCategory(@RequestParam(value = "categories", required = false) List<Long> categoriesId) {
		List<TvShowRestModel> listTvShows;
		if (categoriesId != null) {
			final List<Category> categories = categoriesId.stream().map(category -> new Category(category, null, null)).collect(Collectors.toList());
			listTvShows = tvShowService.getTvShowsByCategoriesIn(categories);
		} else {
			listTvShows = tvShowService.getTvShows();
		}
		return listTvShows;
	}
	
	/**
	 * Gets the tv-shows by id.
	 *
	 * @param id: tv-show's id
	 * @return the tv-shows by id
	 */
	@GetMapping("/netflix/tv-shows/{tv-show-id}")
	public TvShowRestModel getTvShowsById(@PathVariable(value = "tv-show-id") Long id) {
		return tvShowService.getTvShowById(id);
	}
	
	/**
	 * Adds the categories to tv-shows.
	 *
	 * @param categories: categories
	 * @param tvShowId: tv-show's id
	 * @return the response entity
	 */
	@PostMapping("/netflix/tv-shows/addCategories/{series-id}")
	public ResponseEntity<Object> addCategories(@RequestBody Set<Category> categories, @PathVariable(value = "series-id") Long tvShowId) {
		ResponseEntity<Object> result;
		try {
			final TvShow tvShow = tvShowService.findById(tvShowId);
			if (tvShow != null) {
				tvShow.getCategories().addAll(categories);
				result = ResponseEntity.status(HttpStatus.OK).body(tvShowService.updateTvShow(tvShow));
			} else {
				final StringBuilder msj = new StringBuilder();
				msj.append("La serie con id ");
				msj.append(tvShowId);
				msj.append(" no está registrada en BD");
				result = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msj.toString());
			}
		} catch (JpaObjectRetrievalFailureException e) {
			final StringBuilder msj = new StringBuilder();
			msj.append("La categoría con id ");
			msj.append(e.getMessage().substring(e.getMessage().indexOf("id")+3, e.getMessage().indexOf(';')));
			msj.append(" no está registrada en BD");
			result = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msj.toString());
		}
		return result;
	}
	
	@PostMapping("/netflix/tv-shows/updateName/{series-id}")
	public ResponseEntity<Object> updateName(@RequestParam String name, @PathVariable(value = "series-id") Long seriesId) {
		ResponseEntity<Object> result;
		final TvShow tvShow = tvShowService.findById(seriesId);
		if (tvShow != null) {
			tvShow.setName(name);
			result = ResponseEntity.status(HttpStatus.OK).body(tvShowService.updateTvShow(tvShow));
		} else {
			final StringBuilder msj = new StringBuilder();
			msj.append("La serie con id ");
			msj.append(seriesId);
			msj.append(" no está registrada en BD");
			result = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msj.toString());
		}
		return result;
	}

	@PostMapping("/netflix/tv-shows/delete/{series-id}")
	public ResponseEntity<String> delete(@PathVariable(value = "series-id") Long seriesId) {
		ResponseEntity<String> result;
		try {
			tvShowService.deleteTvShow(seriesId);
			result = ResponseEntity.status(HttpStatus.OK).body("La serie ha sido borrada correctamente");
		} catch (EmptyResultDataAccessException e) {
			final StringBuilder msj = new StringBuilder();
			msj.append("La serie con id ");
			msj.append(seriesId);
			msj.append(" no está registrada en BD");
			result = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msj.toString());
		}
		return result;
	}

}
