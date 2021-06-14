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
import org.springframework.stereotype.Service;

import com.vsanchcu.netflix.entity.Category;
import com.vsanchcu.netflix.model.TvShowRestModel;
import com.vsanchcu.netflix.repository.TvShowRepository;

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
	 * Gets the tv shows.
	 *
	 * @return the tv shows
	 */
	@Override
	public List<TvShowRestModel> getTvShows() {
		return tvShowRepository.findAll().stream()
				.map(tvShow -> modelMapper.map(tvShow, TvShowRestModel.class))
				.collect(Collectors.toList());
	}

	/**
	 * Gets the tv shows by category.
	 *
	 * @param category the category
	 * @return the tv shows by category
	 */
	@Override
	public List<TvShowRestModel> getTvShowsByCategory(final Category category) {
		return tvShowRepository.findByCategory(category).stream()
				.map(tvShow -> modelMapper.map(tvShow, TvShowRestModel.class))
				.collect(Collectors.toList());
	}

	/**
	 * Gets the tv show by id.
	 *
	 * @param id the id
	 * @return the tv show by id
	 */
	@Override
	public TvShowRestModel getTvShowById(final Long id) {
		return tvShowRepository.findById(id)
				.map(tvShow -> modelMapper.map(tvShow, TvShowRestModel.class))
				.orElse(null);
	}

}
