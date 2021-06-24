/*
 * Award's Service Implementation
 * 
 * @author: VSANCHCU
 * @version: 1.0
 */
package com.vsanchcu.netflix.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vsanchcu.netflix.entity.Award;
import com.vsanchcu.netflix.repository.AwardRepository;

/**
 * The Class AwardServiceImpl.
 */
@Service
public class AwardServiceImpl implements AwardServiceI {

	/** The award repository. */
	@Autowired
	private AwardRepository awardRepository;

	/**
	 * Gets the awards by tv-show's id.
	 *
	 * @param tvShowId: tv-show's id
	 * @return the awards
	 */
	@Override
	public List<Award> getAwardsByTvShowId(final Long tvShowId) {
		return awardRepository.findByTvShowAwardColTvShowId(tvShowId);
	}

}
