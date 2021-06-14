/*
 * TvShow's Service Interface
 * 
 * @author: VSANCHCU
 * @version: 1.0
 */
package com.vsanchcu.netflix.service;

import java.util.List;

import com.vsanchcu.netflix.entity.Category;
import com.vsanchcu.netflix.model.TvShowRestModel;

/**
 * The Interface TvShowServiceI.
 */
public interface TvShowServiceI {

	List<TvShowRestModel> getTvShows();
	List<TvShowRestModel> getTvShowsByCategory(final Category category);
	TvShowRestModel getTvShowById(final Long id);

}
