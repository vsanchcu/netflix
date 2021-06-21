/*
 * Actor's Service Interface
 * 
 * @author: VSANCHCU
 * @version: 1.0
 */
package com.vsanchcu.netflix.service;

import java.util.List;

import com.vsanchcu.netflix.entity.Actor;
import com.vsanchcu.netflix.exception.NetflixException;
import com.vsanchcu.netflix.model.ActorRestModel;

/**
 * The Interface ActorServiceI.
 */
public interface ActorServiceI {

	List<ActorRestModel> getActors();
	ActorRestModel getActorById(final Long id) throws NetflixException;
	ActorRestModel addActor(final Actor actor) throws NetflixException;

}
