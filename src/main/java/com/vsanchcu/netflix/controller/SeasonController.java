/*
 * Season's Controller
 * 
 * @author: VSANCHCU
 * @version: 1.0
 */
package com.vsanchcu.netflix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vsanchcu.netflix.exception.NetflixNotFoundException;
import com.vsanchcu.netflix.response.NetflixResponse;
import com.vsanchcu.netflix.service.SeasonServiceI;
import com.vsanchcu.netflix.util.ConstCommon;
import com.vsanchcu.netflix.util.ConstRest;

/**
 * The Class SeasonController.
 */
@RestController
@RequestMapping(ConstRest.RES_SEASON)
public class SeasonController {

	/** The season service. */
	@Autowired
	private SeasonServiceI seasonService;
	
	/**
	 * Gets the seasons by tv-show's id.
	 *
	 * @param tvShowId: tv-show id
	 * @return the seasons
	 */
	@GetMapping()
	NetflixResponse getSeasonsByTvShowId(@PathVariable Long tvShowId) {
		return new NetflixResponse(HttpStatus.OK, ConstCommon.SUCCESS, ConstCommon.OK, 
				seasonService.getSeasonsByTvShowId(tvShowId));
	}
	
	/**
	 * Gets the season by tv-show's id and season's number.
	 *
	 * @param tvShowId: tv-show's id
	 * @param seasonNumber: season's number
	 * @return the season
	 * @throws NetflixNotFoundException Season doesn't exist
	 */
	@GetMapping(ConstRest.PATH_VAR_SEASON_NUM)
	NetflixResponse getSeasonByTvShowIdAndNumber(
			@PathVariable Long tvShowId, @PathVariable int seasonNumber) 
					throws NetflixNotFoundException {
		return new NetflixResponse(HttpStatus.OK, ConstCommon.SUCCESS, ConstCommon.OK, 
				seasonService.getSeasonByTvShowIdAndNumber(tvShowId, seasonNumber));
	}

}
