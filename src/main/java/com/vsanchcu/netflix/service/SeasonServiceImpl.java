/*
 * Season's Service Implementation
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

import com.vsanchcu.netflix.entity.TvShow;
import com.vsanchcu.netflix.model.SeasonRestModel;
import com.vsanchcu.netflix.repository.SeasonRepository;

/**
 * The Class SeasonServiceImpl.
 */
@Service
public class SeasonServiceImpl implements SeasonServiceI {

	/** The season repository. */
	@Autowired
	private SeasonRepository seasonRepository;
	
	/** The model mapper. */
	private ModelMapper modelMapper = new ModelMapper();

	/**
	 * Gets the seasons by tv show.
	 *
	 * @param tvShow the tv show
	 * @return the seasons by tv show
	 */
	@Override
	public List<SeasonRestModel> getSeasonsByTvShow(final TvShow tvShow) {
		return seasonRepository.findByTvShow(tvShow).stream()
				.map(season -> modelMapper.map(season, SeasonRestModel.class))
				.collect(Collectors.toList());
	}

	/**
	 * Gets the season by tv show and number.
	 *
	 * @param tvShow the tv show
	 * @param number the number
	 * @return the season by tv show and number
	 */
	@Override
	public SeasonRestModel getSeasonByTvShowAndNumber(final TvShow tvShow, final int number) {
		return seasonRepository.findByTvShowAndNumber(tvShow, number)
				.map(season -> modelMapper.map(season, SeasonRestModel.class))
				.orElse(null);
	}

}
