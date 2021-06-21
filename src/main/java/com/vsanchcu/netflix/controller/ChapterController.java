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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vsanchcu.netflix.entity.Chapter;
import com.vsanchcu.netflix.exception.NetflixException;
import com.vsanchcu.netflix.exception.NetflixNotFoundException;
import com.vsanchcu.netflix.model.ChapterRestModel;
import com.vsanchcu.netflix.response.NetflixResponse;
import com.vsanchcu.netflix.service.ChapterServiceI;
import com.vsanchcu.netflix.util.ConstCommon;
import com.vsanchcu.netflix.util.ConstRest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * The Class ChapterController.
 */
@Api(tags = "Chaper's Controller")
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
	@ApiOperation(value = "Consultar todos las capítulos de una serie y una temporada")
	@ApiResponses({@ApiResponse(code = 200, message = "OK. La consulta se ha realizado correctamente.")})
	@GetMapping
	public NetflixResponse<List<ChapterRestModel>> getChaptersByTvShowIdAndSeasonNumber(
			@ApiParam(name = "tvShowId", type = "Long", value = "Tv show's Id", example = "1", required = true) 
			@PathVariable Long tvShowId, 
			@ApiParam(name = "seasonNumber", type = "int", value = "Season's Number", example = "1", required = true) 
			@PathVariable int seasonNumber) {
		return new NetflixResponse<List<ChapterRestModel>>(ConstCommon.SUCCESS, HttpStatus.OK, ConstCommon.OK, 
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
	@ApiOperation(value = "Consultar capítulo por serie y temporada")
	@ApiResponses({@ApiResponse(code = 200, message = "OK. La consulta se ha realizado correctamente."), 
					@ApiResponse(code = 404, message = "El capítulo no está registrado en BD")})
	@GetMapping(ConstRest.PATH_VAR_CHAPTER_NUM)
	public NetflixResponse<ChapterRestModel> getChapterByTvShowAndSeasonAndNumber(
			@ApiParam(name = "tvShowId", type = "Long", value = "Tv show's Id", example = "1", required = true) 
			@PathVariable Long tvShowId, 
			@ApiParam(name = "seasonNumber", type = "int", value = "Season's Number", example = "1", required = true) 
			@PathVariable int seasonNumber, 
			@ApiParam(name = "chapterNumber", type = "int", value = "Chapter's Number", example = "1", required = true) 
			@PathVariable int chapterNumber) 
					throws NetflixNotFoundException {
		return new NetflixResponse<ChapterRestModel>(ConstCommon.SUCCESS, HttpStatus.OK, ConstCommon.OK, 
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
	@ApiOperation(value = "Actualizar nombre de capítulo", notes = "Se debe indicar la serie, temporada y capítulo")
	@ApiResponses({@ApiResponse(code = 200, message = "OK. La consulta se ha realizado correctamente."), 
					@ApiResponse(code = 404, message = "El capítulo no está registrado en BD"), 
					@ApiResponse(code = 500, message = "Error al actualizar")})
	@PatchMapping(ConstRest.PATH_VAR_CHAPTER_NUM)
	public NetflixResponse<ChapterRestModel> updateName(
			@ApiParam(name = "name", type = "String", value = "New Chapter's Name", example = "The name of chapter", required = true) 
			@RequestParam String name, 
			@ApiParam(name = "tvShowId", type = "Long", value = "Tv show's Id", example = "1", required = true) 
			@PathVariable Long tvShowId, 
			@ApiParam(name = "seasonNumber", type = "int", value = "Season's Number", example = "1", required = true) 
			@PathVariable int seasonNumber, 
			@ApiParam(name = "chapterNumber", type = "int", value = "Chapter's Number", example = "1", required = true) 
			@PathVariable int chapterNumber) 
					throws NetflixException {
		final Chapter chapter = chapterService
				.findByTvShowIdAndSeasonNumberAndChapterNumber(tvShowId, seasonNumber, chapterNumber);
		return new NetflixResponse<ChapterRestModel>(ConstCommon.SUCCESS, HttpStatus.OK, ConstCommon.OK, 
					chapterService.updateChapterName(chapter, name));
	}

}
