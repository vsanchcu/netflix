/*
 * Season's Service Interface
 * 
 * @author: VSANCHCU
 * @version: 1.0
 */
package com.vsanchcu.netflix.service;

import java.util.List;

import com.vsanchcu.netflix.exception.NetflixNotFoundException;
import com.vsanchcu.netflix.model.SeasonRestModel;

/**
 * The Interface SeasonServiceI.
 */
public interface SeasonServiceI {

	List<SeasonRestModel> getSeasonsByTvShowId(final Long tvShowId);
	SeasonRestModel getSeasonByTvShowIdAndNumber(final Long tvShowId, final int number) throws NetflixNotFoundException;

}
