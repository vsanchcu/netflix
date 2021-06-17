/*
 * TvShow's Service Implementation
 * 
 * @author: VSANCHCU
 * @version: 1.0
 */
package com.vsanchcu.netflix.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import com.vsanchcu.netflix.entity.Category;
import com.vsanchcu.netflix.entity.TvShow;
import com.vsanchcu.netflix.exception.NetflixException;
import com.vsanchcu.netflix.exception.NetflixNotFoundException;
import com.vsanchcu.netflix.model.TvShowRestModel;
import com.vsanchcu.netflix.repository.TvShowRepository;
import com.vsanchcu.netflix.util.ConstException;

/**
 * The Class TvShowServiceImpl.
 */
@Service
public class TvShowServiceImpl implements TvShowServiceI {

	/** The tv show repository. */
	@Autowired
	private TvShowRepository tvShowRepository;
	
	/** The model mapper. */
	private ModelMapper modelMapper = new ModelMapper();

	/**
	 * Gets the tv-shows.
	 *
	 * @return the tv-shows
	 */
	@Override
	public List<TvShowRestModel> getTvShows() {
		return tvShowRepository.findAll().stream()
				.map(tvShow -> modelMapper.map(tvShow, TvShowRestModel.class))
				.collect(Collectors.toList());
	}

	/**
	 * Gets the tv-shows by categories.
	 *
	 * @param categories: the categories
	 * @return the tv-shows
	 */
	@Override
	public List<TvShowRestModel> getTvShowsByCategoriesIn(final List<Category> categories) {
		return tvShowRepository.findDistinctByCategoriesIn(categories).stream()
				.map(tvShow -> modelMapper.map(tvShow, TvShowRestModel.class))
				.collect(Collectors.toList());
	}

	/**
	 * Gets the tv-show by id.
	 *
	 * @param id: tv-show's id
	 * @return the tv show
	 * @throws NetflixNotFoundException Tv-show doesn't exist
	 */
	@Override
	public TvShowRestModel getTvShowById(final Long id) 
			throws NetflixNotFoundException {
		return tvShowRepository.findById(id)
				.map(tvShow -> modelMapper.map(tvShow, TvShowRestModel.class))
				.orElseThrow(() -> new NetflixNotFoundException(ConstException.MSG_NON_EXIST_TV_SHOW));
	}

	/**
	 * Update tv-show.
	 *
	 * @param tvShow: the tv-show
	 * @return the updated tv-show
	 * @throws 	NetflixNotFoundException Category doesn't exist
	 * 			NetflixException Error
	 */
	@Override
	public TvShowRestModel updateTvShow(final TvShow tvShow) 
			throws NetflixException {
		try {
			return modelMapper.map(tvShowRepository.save(tvShow), TvShowRestModel.class);
		} catch (JpaObjectRetrievalFailureException e) { 
			throw new NetflixNotFoundException(ConstException.MSG_NON_EXIST_CATEGORY);
		} catch (Exception e) {
			throw new NetflixException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ConstException.ERROR);
		}
	}

	/**
	 * Find tv-show by id.
	 *
	 * @param id: tv-show's id
	 * @return the tv-show
	 * @throws NetflixNotFoundException Tv-show doesn't exist
	 */
	@Override
	public TvShow findById(final Long id) throws NetflixNotFoundException {
		return tvShowRepository.findById(id)
				.orElseThrow(() -> new NetflixNotFoundException(ConstException.MSG_NON_EXIST_TV_SHOW));
	}

	/**
	 * Delete tv-show.
	 *
	 * @param id: tv-show's id
	 */
	@Override
	public void deleteTvShow(final Long id) {
		tvShowRepository.deleteById(id);
	}

}
