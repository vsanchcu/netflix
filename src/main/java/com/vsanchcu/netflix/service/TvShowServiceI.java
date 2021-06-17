/*
 * TvShow's Service Interface
 * 
 * @author: VSANCHCU
 * @version: 1.0
 */
package com.vsanchcu.netflix.service;

import java.util.List;

import com.vsanchcu.netflix.entity.Category;
import com.vsanchcu.netflix.entity.TvShow;
import com.vsanchcu.netflix.exception.NetflixException;
import com.vsanchcu.netflix.exception.NetflixNotFoundException;
import com.vsanchcu.netflix.model.TvShowRestModel;

/**
 * The Interface TvShowServiceI.
 */
public interface TvShowServiceI {

	List<TvShowRestModel> getTvShows();
	List<TvShowRestModel> getTvShowsByCategoriesIn(final List<Category> categories);
	TvShowRestModel getTvShowById(final Long id) throws NetflixNotFoundException;
	TvShowRestModel updateTvShow(final TvShow tvShow) throws NetflixException;
	TvShow findById(final Long id) throws NetflixNotFoundException;
	void deleteTvShow(final Long id);

}
