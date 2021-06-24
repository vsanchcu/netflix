/*
 * Award's Repository
 * 
 * @author: VSANCHCU
 * @version: 1.0
 */
package com.vsanchcu.netflix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vsanchcu.netflix.entity.Award;

/**
 * The Interface AwardRepository.
 */
@Repository
public interface AwardRepository extends JpaRepository<Award, Long>, AwardRepositoryCustom {

//	/**
//	 * Find by tv-show's id.
//	 *
//	 * @param tvShowId: tv-show's id
//	 */
//	List<Award> findByTvShowAwardColTvShowId(final Long tvShowId);

}
