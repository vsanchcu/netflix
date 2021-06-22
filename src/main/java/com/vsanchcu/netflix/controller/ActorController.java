/*
 * Actor's RestController
 * 
 * @author: VSANCHCU
 * @version: 1.0
 */
package com.vsanchcu.netflix.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vsanchcu.netflix.entity.Actor;
import com.vsanchcu.netflix.exception.NetflixException;
import com.vsanchcu.netflix.model.ActorRestModel;
import com.vsanchcu.netflix.model.TvShowChapterRestModel;
import com.vsanchcu.netflix.response.NetflixResponse;
import com.vsanchcu.netflix.service.ActorServiceI;
import com.vsanchcu.netflix.util.ConstCommon;
import com.vsanchcu.netflix.util.ConstRest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * The Class ActorController.
 */
@Api(tags = "Actor's Controller")
@RestController
@RequestMapping(ConstRest.RES_ACTOR)
public class ActorController {

	/** The actor service. */
	@Autowired
	private ActorServiceI actorService;

	/**
	 * Gets the actors.
	 *
	 * @return the actors
	 */
	@ApiOperation(value = "Consultar todos los actores")
	@ApiResponses({@ApiResponse(code = 200, message = "OK. La consulta se ha realizado correctamente.")})
	@GetMapping
	NetflixResponse<List<ActorRestModel>> getActors() {
		return new NetflixResponse<List<ActorRestModel>>(ConstCommon.SUCCESS, HttpStatus.OK, ConstCommon.OK, 
				actorService.getActors());
	}
	
	/**
	 * Gets the actor by id.
	 *
	 * @param actorId: actor's id
	 * @return the actor
	 * @throws NetflixException Actor doesn't exist
	 */
	@ApiOperation(value = "Consultar actor por Id")
	@ApiResponses({@ApiResponse(code = 200, message = "OK. La consulta se ha realizado correctamente."), 
					@ApiResponse(code = 404, message = "El actor no está registrado en BD")})
	@GetMapping(ConstRest.PATH_VAR_ACTOR_ID)
	NetflixResponse<ActorRestModel> getActorById(
			@ApiParam(name = "actorId", type = "Long", value = "Actor's Id", example = "1", required = true) 
			@PathVariable Long actorId) 
			throws NetflixException {
		return new NetflixResponse<ActorRestModel>(ConstCommon.SUCCESS, HttpStatus.OK, ConstCommon.OK, 
				actorService.getActorById(actorId));
	}
	
	@ApiOperation(value = "Añadir actor")
	@ApiResponses({@ApiResponse(code = 201, message = "OK. Actor registrado correctamente"),
					@ApiResponse(code = 500, message = "Error al registrar")})
	@PostMapping()
	NetflixResponse<ActorRestModel> addActor(
			@ApiParam(name ="actor", type="Actor", value = "New Actor", example = "", required = true) 
			@RequestBody Actor actor) 
					throws NetflixException {
		return new NetflixResponse<ActorRestModel>(ConstCommon.SUCCESS, HttpStatus.CREATED, ConstCommon.OK, 
				actorService.addActor(actor));
	}
	
	@ApiOperation(value = "Actualizar actor")
	@ApiResponses({@ApiResponse(code = 200, message = "OK. Actor actualizado correctamente"),
					@ApiResponse(code = 404, message = "El actor no está registrado en BD"), 
					@ApiResponse(code = 500, message = "Error al actualizar")})
	@PutMapping(ConstRest.PATH_VAR_ACTOR_ID)
	NetflixResponse<ActorRestModel> updateActor(
			@ApiParam(name = "actorId", type = "Long", value = "Actor's Id", example = "1", required = true) 
			@PathVariable Long actorId, 
			@ApiParam(name ="actor", type="Actor", value = "Updated Actor", example = "", required = true) 
			@RequestBody Actor actor) 
					throws NetflixException {
		return new NetflixResponse<ActorRestModel>(ConstCommon.SUCCESS, HttpStatus.OK, ConstCommon.OK, 
				actorService.updateActor(actorId, actor));
	}
	
	@ApiOperation(value = "Eliminar actor")
	@ApiResponses({@ApiResponse(code = 200, message = "OK. Actor eliminado correctamente"), 
					@ApiResponse(code = 404, message = "El actor no está registrado en BD"), 
					@ApiResponse(code = 500, message = "Error al eliminar")})
	@DeleteMapping(ConstRest.PATH_VAR_ACTOR_ID)
	NetflixResponse<?> deleteActor(
			@ApiParam(name = "actorId", type = "Long", value = "Actor's Id", example = "1", required = true) 
			@PathVariable Long actorId) 
					throws NetflixException {
		actorService.deleteActor(actorId);
		return new NetflixResponse<>(ConstCommon.SUCCESS, HttpStatus.OK, ConstCommon.OK);
	}
	
	@ApiOperation(value = "Consultar series y capítulos de un actor")
	@ApiResponses({@ApiResponse(code = 200, message = "OK. La consulta se ha realizado correctamente"), 
					@ApiResponse(code = 404, message = "El acto no está registrado en BD"), 
					@ApiResponse(code = 500, message = "Error")})
	@GetMapping(ConstRest.PATH_VAR_ACTOR_ID + ConstRest.RES_TVS_HOW)
	NetflixResponse<List<TvShowChapterRestModel>> getTvShowsAndChaptersByActor(
			@ApiParam(name = "actorId", type = "Long", value = "Actor's Id", example = "1", required = true) 
			@PathVariable Long actorId) {
		return new NetflixResponse<List<TvShowChapterRestModel>>(ConstCommon.SUCCESS, HttpStatus.OK, ConstCommon.OK, 
				actorService.getTvShowsAndChaptersByActor(actorId));
	}
}
