/*
 * Award's Repository
 * 
 * @author: VSANCHCU
 * @version: 1.0
 */
package com.vsanchcu.netflix.repository;

import java.util.List;

import com.vsanchcu.netflix.entity.Award;

/**
 * The Interface AwardRepository.
 */
public interface AwardRepositoryCustom {

	/**
	 * Find by tv-show's id.
	 *
	 * @param tvShowId: tv-show's id
	 */
	List<Award> findByTvShowAwardColTvShowId(final Long tvShowId);

}
