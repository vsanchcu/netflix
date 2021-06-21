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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.vsanchcu.netflix.entity.Actor;
import com.vsanchcu.netflix.exception.NetflixErrorException;
import com.vsanchcu.netflix.exception.NetflixException;
import com.vsanchcu.netflix.exception.NetflixNotFoundException;
import com.vsanchcu.netflix.model.ActorRestModel;
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
	
	/** The model mapper. */
	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public List<ActorRestModel> getActors() {
		return actorRepository.findAll().stream()
				.map(actor -> modelMapper.map(actor, ActorRestModel.class))
				.collect(Collectors.toList());
	}

	@Override
	public ActorRestModel getActorById(final Long id) 
			throws NetflixException {
		return actorRepository.findById(id)
				.map(actor -> modelMapper.map(actor, ActorRestModel.class))
				.orElseThrow(() -> new NetflixNotFoundException(ConstException.MSG_NON_EXIST_ACTOR));
	}

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

}
