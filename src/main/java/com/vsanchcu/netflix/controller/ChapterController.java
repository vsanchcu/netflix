/*
 * Chapter's Controller
 * 
 * @author: VSANCHCU
 * @version: 1.0
 */
package com.vsanchcu.netflix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vsanchcu.netflix.entity.Chapter;
import com.vsanchcu.netflix.exception.NetflixException;
import com.vsanchcu.netflix.exception.NetflixNotFoundException;
import com.vsanchcu.netflix.response.NetflixResponse;
import com.vsanchcu.netflix.service.ChapterServiceI;
import com.vsanchcu.netflix.util.ConstCommon;
import com.vsanchcu.netflix.util.ConstRest;

/**
 * The Class ChapterController.
 */
@RestController
@RequestMapping(ConstRest.RES_CHAPTER)
public class ChapterController {

	/** The chapter service. */
	@Autowired
	private ChapterServiceI chapterService;

	/**
	 * Gets the chapters by tv-show's id and season's number.
	 *
	 * @param tvShowId: tv-show's id
	 * @param seasonNumber: season's number
	 * @return the chapters
	 */
	@GetMapping
	public NetflixResponse getChaptersByTvShowIdAndSeasonNumber(
			@PathVariable Long tvShowId, @PathVariable int seasonNumber) {
		return new NetflixResponse(HttpStatus.OK, ConstCommon.SUCCESS, ConstCommon.OK, 
				chapterService.getChaptersByTvShowIdAndSeasonNumber(tvShowId, seasonNumber));
	}
	
	/**
	 * Gets the chapter by tv-show's id and season's number and chapter's number.
	 *
	 * @param tvShowId: tv-show's id
	 * @param seasonNumber: season's number
	 * @param chapterNumber: chapter's number
	 * @return the chapter
	 * @throws NetflixNotFoundException Chapter doesn't exist
	 */
	@GetMapping(ConstRest.PATH_VAR_CHAPTER_NUM)
	public NetflixResponse getChapterByTvShowAndSeasonAndNumber(
			@PathVariable Long tvShowId, @PathVariable int seasonNumber, @PathVariable int chapterNumber) 
					throws NetflixNotFoundException {
		return new NetflixResponse(HttpStatus.OK, ConstCommon.SUCCESS, ConstCommon.OK, 
				chapterService.getChapterByTvShowIdAndSeasonNumberAndChapterNumber(tvShowId, seasonNumber, chapterNumber));
	}
	
	/**
	 * Update chapter's name.
	 *
	 * @param name: chapter's name
	 * @param tvShowId: tv-show's id
	 * @param seasonNumber: season's number
	 * @param chapterNumber: chapter's number
	 * @return the updated chapter
	 * @throws 	NetflixNotFoundException Chapter doesn't exist
	 * 			NetflixException ERROR
	 */
	@PatchMapping(ConstRest.PATH_VAR_CHAPTER_NUM)
	public NetflixResponse updateName(@RequestParam String name, 
			@PathVariable Long tvShowId, @PathVariable int seasonNumber, @PathVariable int chapterNumber) 
					throws NetflixException {
		final Chapter chapter = chapterService
				.findByTvShowIdAndSeasonNumberAndChapterNumber(tvShowId, seasonNumber, chapterNumber);
		return new NetflixResponse(HttpStatus.OK, ConstCommon.SUCCESS, ConstCommon.OK, 
					chapterService.updateChapterName(chapter, name));
	}

}
