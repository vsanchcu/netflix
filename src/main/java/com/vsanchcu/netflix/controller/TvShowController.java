package com.vsanchcu.netflix.controller;

import java.util.List;
import java.util.Set;

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

	@GetMapping("/netflix/tv-shows")
	public List<TvShowRestModel> getTvShowsByCategory(@RequestParam(value = "categories", required = false) Long categoryId) {
		List<TvShowRestModel> listTvShows;
		if (categoryId!=null) {
			final Category category = new Category();
			category.setId(categoryId);
			listTvShows = tvShowService.getTvShowsByCategoryIn(List.of(category));
		} else {
			listTvShows = tvShowService.getTvShows();
		}
		return listTvShows;
	}
	
	@GetMapping("/netflix/tv-shows/{tv-show-id}")
	public TvShowRestModel getTvShowsById(@PathVariable(value = "tv-show-id") Long id) {
		return tvShowService.getTvShowById(id);
	}
	
	@PostMapping("/netflix/tv-shows/addCategories/{series-id}")
	public ResponseEntity<Object> addCategories(@RequestBody Set<Category> categories, @PathVariable(value = "series-id") Long seriesId) {
		ResponseEntity<Object> result;
		try {
			final TvShow tvShow = tvShowService.findById(seriesId);
			if (tvShow != null) {
				tvShow.getCategories().addAll(categories);
				result = ResponseEntity.status(HttpStatus.OK).body(tvShowService.updateTvShow(tvShow));
			} else {
				final StringBuilder msj = new StringBuilder();
				msj.append("La serie con id ");
				msj.append(seriesId);
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
