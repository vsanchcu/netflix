/*
 * Chapter's Controller
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

import com.vsanchcu.netflix.entity.Season;
import com.vsanchcu.netflix.entity.TvShow;
import com.vsanchcu.netflix.model.ChapterRestModel;
import com.vsanchcu.netflix.service.ChapterServiceI;

/**
 * The Class ChapterController.
 */
@RestController
public class ChapterController {

	/** The chapter service. */
	@Autowired
	private ChapterServiceI chapterService;

	/**
	 * Gets the chapters by tv show and season.
	 *
	 * @param seasonId the season id
	 * @param seasonNum the season num
	 * @return the chapters by tv show and season
	 */
	@GetMapping("/netflix/series/{series-id}/seasons/{season-number}/chapters")
	public List<ChapterRestModel> getChaptersByTvShowAndSeason(
			@PathVariable(value = "series-id") Long seasonId, 
			@PathVariable(value = "season-number") int seasonNum) {
		final TvShow tvShow = new TvShow();
		tvShow.setId(seasonId);
		final Season season = new Season();
		season.setNumber(seasonNum);
		return chapterService.getChaptersByTvShowsAndSeason(tvShow, season);
	}
	
	/**
	 * Gets the chapter by tv show and season and number.
	 *
	 * @param seasonId the season id
	 * @param seasonNum the season num
	 * @param number the number
	 * @return the chapter by tv show and season and number
	 */
	@GetMapping("/netflix/series/{series-id}/seasons/{season-number}/chapters/{chapter-number}")
	public ChapterRestModel getChapterByTvShowAndSeasonAndNumber(
			@PathVariable(value = "series-id") Long seasonId, 
			@PathVariable(value = "season-number") int seasonNum, 
			@PathVariable(value = "chapter-number") int number) {
		final TvShow tvShow = new TvShow();
		tvShow.setId(seasonId);
		final Season season = new Season();
		season.setNumber(seasonNum);
		return chapterService.getChapterByTvShowAndSeasonAndNumber(tvShow, season, number);
	}

}
