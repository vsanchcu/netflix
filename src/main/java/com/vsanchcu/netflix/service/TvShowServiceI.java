/*
 * TvShow's Service Interface
 * 
 * @author: VSANCHCU
 * @version: 1.0
 */
package com.vsanchcu.netflix.service;

import java.util.List;

import com.vsanchcu.netflix.entity.Category;
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
	TvShowRestModel updateTvShowCategories(final Long tvShowId, final List<Long> categoriesId) throws NetflixException;
	TvShowRestModel updateTvShowName(final Long tvShowId, final String name) throws NetflixException;
	void deleteTvShow(final Long id) throws NetflixException;

}
