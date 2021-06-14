/*
 * Season's Service Interface
 * 
 * @author: VSANCHCU
 * @version: 1.0
 */
package com.vsanchcu.netflix.service;

import java.util.List;

import com.vsanchcu.netflix.entity.TvShow;
import com.vsanchcu.netflix.model.SeasonRestModel;

/**
 * The Interface SeasonServiceI.
 */
public interface SeasonServiceI {

	List<SeasonRestModel> getSeasonsByTvShow(final TvShow tvShow);
	SeasonRestModel getSeasonByTvShowAndNumber(final TvShow tvShow, final int number);

}
