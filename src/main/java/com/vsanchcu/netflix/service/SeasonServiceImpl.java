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
import com.vsanchcu.netflix.exception.NetflixException;
import com.vsanchcu.netflix.exception.NetflixNotFoundException;
import com.vsanchcu.netflix.model.SeasonRestModel;
import com.vsanchcu.netflix.repository.SeasonRepository;
import com.vsanchcu.netflix.util.ConstException;

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
	 * Gets the seasons by tv-show's id.
	 *
	 * @param tvShowId: tv-show's id
	 * @return the seasons
	 */
	@Override
	public List<SeasonRestModel> getSeasonsByTvShowId(final Long tvShowId) {
		return seasonRepository.findByTvShow(new TvShow(tvShowId)).stream()
				.map(season -> modelMapper.map(season, SeasonRestModel.class))
				.collect(Collectors.toList());
	}

	/**
	 * Gets the season by tv-show's id and number.
	 *
	 * @param tvShowId: tv-show's id
	 * @param number: number
	 * @return the season
	 * @throws NetflixNotFoundException Season doesn't exist
	 */
	@Override
	public SeasonRestModel getSeasonByTvShowIdAndNumber(final Long tvShowId, final int number) 
			throws NetflixException {
		return seasonRepository.findByTvShowAndNumber(new TvShow(tvShowId), number)
				.map(season -> modelMapper.map(season, SeasonRestModel.class))
				.orElseThrow(() -> new NetflixNotFoundException(ConstException.MSG_NON_EXIST_SEASON));
	}

}
