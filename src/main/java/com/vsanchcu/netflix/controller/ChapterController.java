/*
 * Chapter's Controller
 * 
 * @author: VSANCHCU
 * @version: 1.0
 */
package com.vsanchcu.netflix.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vsanchcu.netflix.entity.Chapter;
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
	 * Gets the chapters by tv-show and season.
	 *
	 * @param tvShowId: tv-show's id
	 * @param seasonNum: season's number
	 * @return the chapters by tv-show and season
	 */
	@GetMapping("/netflix/series/{series-id}/seasons/{season-number}/chapters")
	public List<ChapterRestModel> getChaptersByTvShowAndSeason(
			@PathVariable(value = "series-id") Long tvShowId, 
			@PathVariable(value = "season-number") int seasonNum) {
		final TvShow tvShow = new TvShow();
		tvShow.setId(tvShowId);
		final Season season = new Season();
		season.setNumber(seasonNum);
		return chapterService.getChaptersByTvShowsAndSeason(tvShow, season);
	}
	
	/**
	 * Gets the chapter by tv-show and season and number.
	 *
	 * @param tvShowId: tv-show's id
	 * @param seasonNum: season's number
	 * @param number: chapter's number
	 * @return the chapter by tv-show and season and number
	 */
	@GetMapping("/netflix/series/{series-id}/seasons/{season-number}/chapters/{chapter-number}")
	public ChapterRestModel getChapterByTvShowAndSeasonAndNumber(
			@PathVariable(value = "series-id") Long tvShowId, 
			@PathVariable(value = "season-number") int seasonNum, 
			@PathVariable(value = "chapter-number") int number) {
		final TvShow tvShow = new TvShow();
		tvShow.setId(tvShowId);
		final Season season = new Season();
		season.setNumber(seasonNum);
		return chapterService.getChapterByTvShowAndSeasonAndNumber(tvShow, season, number);
	}
	
	/**
	 * Update chapter's name by tv-show and season and number.
	 *
	 * @param name: new chapter's name
	 * @param tvShowId: tv-show's id
	 * @param seasonNum: season's number
	 * @param number: chapter's number
	 * @return 	OK: the response entity with the chapter by tv-show and season and number
	 * 			KO: the response entity with error message
	 */
	@PostMapping("/netflix/series/{series-id}/seasons/{season-number}/chapters/{chapter-number}")
	public ResponseEntity<Object> updateName(@RequestParam String name, 
			@PathVariable(value = "series-id") Long tvShowId, 
			@PathVariable(value = "season-number") int seasonNum, 
			@PathVariable(value = "chapter-number") int number) {
		ResponseEntity<Object> result;
		final TvShow tvShow = new TvShow();
		tvShow.setId(tvShowId);
		final Season season = new Season();
		season.setNumber(seasonNum);
		final Chapter chapter = chapterService.findByTvShowAndSeasonAndNumber(tvShow, season, number);
		if (chapter != null) {
			chapter.setName(name);
			result = ResponseEntity.status(HttpStatus.OK).body(chapterService.updateChapter(chapter));
		} else {
			final StringBuilder msj = new StringBuilder();
			msj.append("El capítulo número ");
			msj.append(number);
			msj.append(" de la temporada ");
			msj.append(seasonNum);
			msj.append(" de la serie con id ");
			msj.append(tvShowId);
			msj.append(" no está registrado en BD");
			result = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msj.toString());
		}
		return result;
	}

}
