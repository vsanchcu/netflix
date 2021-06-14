package com.vsanchcu.netflix.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vsanchcu.netflix.entity.Category;
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
			listTvShows = tvShowService.getTvShowsByCategory(category);
		} else {
			listTvShows = tvShowService.getTvShows();
		}
		return listTvShows;
	}
	
	@GetMapping("/netflix/tv-shows/{tv-show-id}")
	public TvShowRestModel getTvShowsById(@PathVariable(value = "tv-show-id") Long id) {
		return tvShowService.getTvShowById(id);
	}

}
