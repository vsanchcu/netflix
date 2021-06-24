/*
 * TvShow's Controller
 * 
 * @author: VSANCHCU
 * @version: 1.0
 */
package com.vsanchcu.netflix.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vsanchcu.netflix.entity.Category;
import com.vsanchcu.netflix.exception.NetflixException;
import com.vsanchcu.netflix.exception.NetflixNotFoundException;
import com.vsanchcu.netflix.model.AwardRestModel;
import com.vsanchcu.netflix.model.TvShowRestModel;
import com.vsanchcu.netflix.response.NetflixResponse;
import com.vsanchcu.netflix.service.TvShowServiceI;
import com.vsanchcu.netflix.util.ConstCommon;
import com.vsanchcu.netflix.util.ConstRest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * The Class TvShowController.
 */
@Api(tags = "Tv Show's Controller")
@RestController
@RequestMapping(ConstRest.RES_TVS_HOW)
public class TvShowController {

	/** The tv show service. */
	@Autowired
	private TvShowServiceI tvShowService;

	/**
	 * Gets the tv shows (optional: by categories id).
	 *
	 * @param categoriesId: categories' id
	 * @return the tv shows
	 */
	@ApiOperation(value = "Consultar todas las series o por categorías")
	@ApiResponses({@ApiResponse(code = 200, message = "OK. La consulta se ha realizado correctamente.")})
	@GetMapping()
	NetflixResponse<List<TvShowRestModel>> getTvShows(
			@ApiParam(name = "categoriesId", type = "List<Long>", value = "List of Category's Id", example = "{1, 2}", required = false) 
			@RequestParam(required = false) List<Long> categoriesId) {
		NetflixResponse<List<TvShowRestModel>> response;
		if (categoriesId != null) {
			final List<Category> categories = categoriesId.stream().map(category -> new Category(category)).collect(Collectors.toList());
			response = new NetflixResponse<List<TvShowRestModel>>(ConstCommon.SUCCESS, HttpStatus.OK, ConstCommon.OK, 
					tvShowService.getTvShowsByCategoriesIn(categories));
		} else {
			response = new NetflixResponse<List<TvShowRestModel>>(ConstCommon.SUCCESS, HttpStatus.OK, ConstCommon.OK, 
					tvShowService.getTvShows());
		}
		return response;
	}
	
	/**
	 * Gets the tv-show by id.
	 *
	 * @param tvShowId: tv-show's id
	 * @return the tv show
	 * @throws NetflixNotFoundException Tv-show doesn't exist
	 */
	@ApiOperation(value = "Consultar serie")
	@ApiResponses({@ApiResponse(code = 200, message = "OK. La consulta se ha realizado correctamente."),
					@ApiResponse(code = 404, message = "La serie no está registrada en BD")})
	@GetMapping(ConstRest.PATH_VAR_TV_SHOW_ID)
	NetflixResponse<TvShowRestModel> getTvShowById(
			@ApiParam(name = "tvShowId", type = "Long", value = "Tv-show's Id", example = "1", required = true) 
			@PathVariable Long tvShowId) 
					throws NetflixNotFoundException {
		return new NetflixResponse<TvShowRestModel>(ConstCommon.SUCCESS, HttpStatus.OK, ConstCommon.OK, 
				tvShowService.getTvShowById(tvShowId));
	}
	
	/**
	 * Adds the categories to tv-show.
	 *
	 * @param categoriesId: list of category's id
	 * @param tvShowId: tv-show's id
	 * @return the updated tv-show
	 * @throws 	NetflixNotFoundException Tv-Show or category doesn't exist
	 * 			NetflixErrorException Error
	 */
	@ApiOperation(value = "Añadir categorías a una serie")
	@ApiResponses({@ApiResponse(code = 200, message = "OK. La serie ha sido actualizada."), 
					@ApiResponse(code = 404, message = "La serie no está registrada en BD."), 
					@ApiResponse(code = 500, message = "Error al actualizar.")})
	@PatchMapping(ConstRest.PATH_VAR_TV_SHOW_ID + ConstRest.RES_CATEGORY)
	NetflixResponse<TvShowRestModel> addTvShowCategories(
			@ApiParam(name = "categoriesId", type = "List<Long>", value = "List of Category's Id", example = "{1, 2}", required = true) 
			@RequestParam List<Long> categoriesId, 
			@ApiParam(name = "tvShowId", type = "Long", value = "Tv-show's Id", example = "1", required = true) 
			@PathVariable Long tvShowId) 
					throws NetflixException {
		return new NetflixResponse<TvShowRestModel>(ConstCommon.SUCCESS, HttpStatus.OK, ConstCommon.OK, 
				tvShowService.updateTvShowCategories(tvShowId, categoriesId));
	}
	
	/**
	 * Update tv-show's name.
	 *
	 * @param name: name
	 * @param tvShowId: tv-show's id
	 * @return the updated tv-show
	 * @throws 	NetflixNotFoundException Tv-show doesn't exist
	 * 			NetflixErrorException Error
	 */
	@ApiOperation(value = "Actualizar nombre de una serie")
	@ApiResponses({@ApiResponse(code = 200, message = "OK. La serie se ha actualizado correctamente."), 
					@ApiResponse(code = 404, message = "La serie no está registrada en BD."), 
					@ApiResponse(code = 500, message = "Error al actualizar")})
	@PatchMapping(ConstRest.PATH_VAR_TV_SHOW_ID)
	NetflixResponse<TvShowRestModel> updateTvShowName(
			@ApiParam(name = "name", type = "String", value = "New Tv-shows's Name", example = "The name of tv-show", required = true) 
			@RequestParam String name, 
			@ApiParam(name = "tvShowId", type = "Long", value = "Tv-show's Id", example = "1", required = true) 
			@PathVariable Long tvShowId) 
					throws NetflixException {
		return new NetflixResponse<TvShowRestModel>(ConstCommon.SUCCESS, HttpStatus.OK, ConstCommon.OK, 
				tvShowService.updateTvShowName(tvShowId, name));
	}

	/**
	 * Delete tv-show.
	 *
	 * @param tvShowId: tv-show's id
	 * @return response
	 * @throws 	NetflixNotFoundException Tv-show doesn't exist
	 * 			NetflixErrorException Error
	 */
	@ApiOperation(value = "Eliminar serie")
	@ApiResponses({@ApiResponse(code = 200, message = "OK. La serie se ha eliminado correctamente."), 
					@ApiResponse(code = 404, message = "La serie no está registrada en BD."), 
					@ApiResponse(code = 500, message = "Error al eliminar")})
	@DeleteMapping(ConstRest.PATH_VAR_TV_SHOW_ID)
	NetflixResponse<?> deleteTvShow(
			@ApiParam(name = "tvShowId", type = "Long", value = "Tv-show's Id", example = "1", required = true) 
			@PathVariable Long tvShowId) 
					throws NetflixException {
		tvShowService.deleteTvShow(tvShowId);
		return new NetflixResponse<>(ConstCommon.SUCCESS, HttpStatus.OK, ConstCommon.OK);
	}
	
	/**
	 * Gets the awards by tv show id.
	 *
	 * @param tvShowId: tv-show's id
	 * @return the awards
	 */
	@ApiOperation(value = "Consultar premios de una serie")
	@ApiResponses(@ApiResponse(code = 200, message = "OK. La consulta se ha realizado correctamente."))
	@GetMapping(ConstRest.PATH_VAR_TV_SHOW_ID + ConstRest.RES_AWARD)
	NetflixResponse<List<AwardRestModel>> getAwardsByTvShowId(
			@ApiParam(name = "tvShowId", type = "Long", value = "Tv show's Id", example = "1", required = true) 
			@PathVariable Long tvShowId) {
		return new NetflixResponse<List<AwardRestModel>>(ConstCommon.SUCCESS, HttpStatus.OK, ConstCommon.OK, 
				tvShowService.getAwardsByTvShow(tvShowId));
	}

}
