/*
 * Actor's Service Implementation
 * 
 * @author: VSANCHCU
 * @version: 1.0
 */
package com.vsanchcu.netflix.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.vsanchcu.netflix.entity.Actor;
import com.vsanchcu.netflix.exception.NetflixErrorException;
import com.vsanchcu.netflix.exception.NetflixException;
import com.vsanchcu.netflix.exception.NetflixNotFoundException;
import com.vsanchcu.netflix.model.ActorRestModel;
import com.vsanchcu.netflix.model.ChapterRestModel;
import com.vsanchcu.netflix.model.TvShowChapterRestModel;
import com.vsanchcu.netflix.model.TvShowRestModel;
import com.vsanchcu.netflix.repository.ActorRepository;
import com.vsanchcu.netflix.util.ConstException;

/**
 * The Class ActorServiceImpl.
 */
@Service
public class ActorServiceImpl implements ActorServiceI {

	/** The actor repository. */
	@Autowired
	private ActorRepository actorRepository;
	
	/** The tv show service. */
	@Autowired
	private TvShowServiceI tvShowService;
	
	/** The chapter service. */
	@Autowired
	private ChapterServiceI chapterService;

	/** The model mapper. */
	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public List<ActorRestModel> getActors() {
		return actorRepository.findAll().stream()
				.map(actor -> modelMapper.map(actor, ActorRestModel.class))
				.collect(Collectors.toList());
	}

	/**
	 * Gets the actor by id.
	 *
	 * @param id: id
	 * @return the actor
	 * @throws NetflixNotFoundException Actor doesn't exist
	 */
	@Override
	public ActorRestModel getActorById(final Long id) 
			throws NetflixException {
		return actorRepository.findById(id)
				.map(actor -> modelMapper.map(actor, ActorRestModel.class))
				.orElseThrow(() -> new NetflixNotFoundException(ConstException.MSG_NON_EXIST_ACTOR));
	}

	/**
	 * Adds the actor.
	 *
	 * @param actor: actor
	 * @return the actor
	 * @throws NetflixErrorException Error
	 */
	@Override
	public ActorRestModel addActor(final Actor actor) 
			throws NetflixException {
		try {
			actor.setId(null);
			return modelMapper.map(actorRepository.save(actor), ActorRestModel.class);
		} catch (Exception e) {
			throw new NetflixErrorException(HttpStatus.INTERNAL_SERVER_ERROR, ConstException.ERROR);
		}
	}

	/**
	 * Update actor.
	 *
	 * @param actorId: actor's id
	 * @param actor the actor
	 * @return the actor rest model
	 * @throws NetflixNotFoundException Actor doesn't exist
	 * 			NetflixErrorException Error
	 */
	@Override
	public ActorRestModel updateActor(final Long actorId, final Actor actor) 
			throws NetflixException {
		actorRepository.findById(actorId).orElseThrow(
				() -> new NetflixNotFoundException(ConstException.MSG_NON_EXIST_ACTOR));
		try {
			actor.setId(actorId);
			return modelMapper.map(actorRepository.save(actor), ActorRestModel.class);
		} catch (Exception e) {
			throw new NetflixErrorException(HttpStatus.INTERNAL_SERVER_ERROR, ConstException.ERROR);
		}
	}

	/**
	 * Delete actor.
	 *
	 * @param actorId: actor's id
	 * @throws NetflixNotFoundException Actor doesn't exist
	 * 			NetflixErrorException Error
	 */
	@Override
	public void deleteActor(final Long actorId) 
			throws NetflixException {
		try {
			actorRepository.deleteById(actorId);
		} catch (EmptyResultDataAccessException e) {
			throw new NetflixNotFoundException(ConstException.MSG_NON_EXIST_ACTOR);
		} catch (Exception e) {
			throw new NetflixErrorException(HttpStatus.INTERNAL_SERVER_ERROR, ConstException.ERROR);
		}
	}

	/**
	 * Gets the tv-shows and chapters by actor.
	 *
	 * @param actorId: actor's id
	 * @return the tv-shows and chapters
	 */
	@Override
	public TvShowChapterRestModel getTvShowsAndChaptersByActor(final Long actorId) {
		return new TvShowChapterRestModel(
				tvShowService.getTvShowsByActor(actorId).stream()
				.map(tvShow -> modelMapper.map(tvShow, TvShowRestModel.class))
				.collect(Collectors.toList()), 
				chapterService.getChaptersByActor(actorId).stream()
				.map(chapter -> modelMapper.map(chapter, ChapterRestModel.class))
				.collect(Collectors.toList()));

	}

}
