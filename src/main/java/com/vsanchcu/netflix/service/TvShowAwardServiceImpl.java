/*
 * TvShowAward's Service Implementation
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
 * The Class TvShowAwardServiceImpl.
 */
@Service
public class TvShowAwardServiceImpl implements TvShowAwardServiceI {

	/** The tv-show award repository. */
	@Autowired
	private TvShowAwardRepository tvShowAwardRepository;

	/**
	 * Gets the tv-show awards by tv show id.
	 *
	 * @param tvShowId: tv-show's id
	 * @return the tv-show awards
	 */
	@Override
	public List<TvShowAward> getTvShowAwardsByTvShowId(final Long tvShowId) {
		return  tvShowAwardRepository.findByTvShowId(tvShowId);
	}

}
