/*
 * Actor's Repository
 * 
 * @author: VSANCHCU
 * @version: 1.0
 */
package com.vsanchcu.netflix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vsanchcu.netflix.entity.Actor;

/**
 * The Interface ActorRepository.
 */
@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {

}
