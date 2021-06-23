/*
 * Award's Service Interface
 * 
 * @author: VSANCHCU
 * @version: 1.0
 */
package com.vsanchcu.netflix.service;

import java.util.List;

import com.vsanchcu.netflix.entity.TvShowAward;

/**
 * The Interface AwardServiceI.
 */
public interface TvShowAwardServiceI {

	List<TvShowAward> getAwardsByTvShowId(final Long tvShowId);

}
