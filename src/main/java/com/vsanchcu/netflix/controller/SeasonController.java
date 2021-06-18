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

import com.vsanchcu.netflix.exception.NetflixException;
import com.vsanchcu.netflix.exception.NetflixNotFoundException;
import com.vsanchcu.netflix.response.NetflixResponse;
import com.vsanchcu.netflix.service.SeasonServiceI;
import com.vsanchcu.netflix.util.ConstCommon;
import com.vsanchcu.netflix.util.ConstRest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * The Class SeasonController.
 */
@Api(tags = "Season's Controller")
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
	@ApiOperation(value = "Consultar todas las temporadas de una serie")
	@ApiResponses({@ApiResponse(code = 200, message = "OK. La consulta se ha realizado correctamente.")})
	@GetMapping()
	NetflixResponse getSeasonsByTvShowId(
			@ApiParam(name = "tvShowId", type = "Long", value = "Tv show's Id", example = "1", required = true) 
			@PathVariable Long tvShowId) {
		return new NetflixResponse(ConstCommon.SUCCESS, HttpStatus.OK.value(), ConstCommon.OK, 
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
	@ApiOperation(value = "Consultar temporada de una serie")
	@ApiResponses({@ApiResponse(code = 200, message = "OK. La consulta se ha realizado correctamente."), 
					@ApiResponse(code = 404, message = "La temporada no está registrada en BD")})
	@GetMapping(ConstRest.PATH_VAR_SEASON_NUM)
	NetflixResponse getSeasonByTvShowIdAndNumber(
			@ApiParam(name = "tvShowId", type = "Long", value = "Tv show's Id", example = "1", required = true) 
			@PathVariable Long tvShowId, 
			@ApiParam(name = "seasonNumber", type = "int", value = "Season's Number", example = "1", required = true) 
			@PathVariable int seasonNumber) 
					throws NetflixException {
		return new NetflixResponse(ConstCommon.SUCCESS, HttpStatus.OK.value(), ConstCommon.OK, 
				seasonService.getSeasonByTvShowIdAndNumber(tvShowId, seasonNumber));
	}

}
