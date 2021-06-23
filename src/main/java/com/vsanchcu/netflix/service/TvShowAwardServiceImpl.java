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

import com.vsanchcu.netflix.entity.TvShowAward;
import com.vsanchcu.netflix.repository.TvShowAwardRepository;

/**
 * The Class AwardServiceImpl.
 */
@Service
public class TvShowAwardServiceImpl implements TvShowAwardServiceI {

	/** The award repository. */
	@Autowired
	private TvShowAwardRepository awardRepository;

	/**
	 * Gets the awards by tv-show's id.
	 *
	 * @param tvShowId: tv-show's id
	 * @return the awards
	 */
	@Override
	public List<TvShowAward> getAwardsByTvShowId(final Long tvShowId) {
		return awardRepository.findByTvShowId(tvShowId);
	}

}
