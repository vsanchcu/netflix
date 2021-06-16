/*
 * Season's Controller
 * 
 * @author: VSANCHCU
 * @version: 1.0
 */
package com.vsanchcu.netflix.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.vsanchcu.netflix.entity.TvShow;
import com.vsanchcu.netflix.model.SeasonRestModel;
import com.vsanchcu.netflix.service.SeasonServiceI;

/**
 * The Class SeasonController.
 */
@RestController
public class SeasonController {

	/** The season service. */
	@Autowired
	private SeasonServiceI seasonService;
	
	/**
	 * Gets the seasons by tv-show.
	 *
	 * @param tvShowId: tv-show's id
	 * @return the seasons by tv-show
	 */
	@GetMapping("/netflix/series/{series-id}/seasons")
	List<SeasonRestModel> getSeasonsByTvShow(@PathVariable(value = "series-id") Long tvShowId) {
		final TvShow tvShow = new TvShow();
		tvShow.setId(tvShowId);
		return seasonService.getSeasonsByTvShow(tvShow);
	}
	
	/**
	 * Gets the season by tv-show and number.
	 *
	 * @param tvShowId: tv-show's id
	 * @param number: season's number
	 * @return the season by tv-show and number
	 */
	@GetMapping("/netflix/series/{series-id}/seasons/{season-number}")
	SeasonRestModel getSeasonByTvShowAndNumber(
			@PathVariable(value = "series-id") Long tvShowId,
			@PathVariable(value = "season-number") int number) {
		final TvShow tvShow = new TvShow();
		tvShow.setId(tvShowId);
		return seasonService.getSeasonByTvShowAndNumber(tvShow, number);
	}

}
