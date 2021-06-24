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
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.vsanchcu.netflix.entity.Award;
import com.vsanchcu.netflix.entity.Category;
import com.vsanchcu.netflix.entity.TvShow;
import com.vsanchcu.netflix.entity.TvShowAward;
import com.vsanchcu.netflix.exception.NetflixErrorException;
import com.vsanchcu.netflix.exception.NetflixException;
import com.vsanchcu.netflix.exception.NetflixNotFoundException;
import com.vsanchcu.netflix.model.AwardRestModel;
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
	
	/** The award service. */
	@Autowired
	private AwardServiceI tvShowAwardService;
	
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
	 * Update tv-show's categories.
	 *
	 * @param tvShowId: tv-show's id
	 * @param categoriesId: list of category's id
	 * @return the updated tv-show
	 * @throws 	NetflixNotFoundException Tv-show or category doesn't exist
	 * 			NetflixException Error
	 */
	@Override
	public TvShowRestModel updateTvShowCategories(final Long tvShowId, final List<Long> categoriesId) 
			throws NetflixException {
		try {
			final TvShow tvShow = tvShowRepository.findById(tvShowId)
					.orElseThrow(() -> new NetflixNotFoundException(ConstException.MSG_NON_EXIST_TV_SHOW));
			tvShow.getCategories().addAll(categoriesId.stream()
					.map(category -> new Category(category))
					.collect(Collectors.toSet()));
			return modelMapper.map(tvShowRepository.save(tvShow), TvShowRestModel.class);
		} catch (JpaObjectRetrievalFailureException e) { 
			throw new NetflixNotFoundException(ConstException.MSG_NON_EXIST_CATEGORY);
		} catch (Exception e) {
			throw new NetflixErrorException(HttpStatus.INTERNAL_SERVER_ERROR, ConstException.ERROR);
		}
	}

	/**
	 * Update tv-show's name.
	 *
	 * @param tvShowId: tv-show's id
	 * @param name: name
	 * @return the updated tv-show
	 * @throws 	NetflixNotFoundException Tv-show doesn't exist
	 * 			NetflixException Error
	 */
	@Override
	public TvShowRestModel updateTvShowName(final Long tvShowId, final String name) 
			throws NetflixException {
		try {
			final TvShow tvShow = tvShowRepository.findById(tvShowId)
					.orElseThrow(() -> new NetflixNotFoundException(ConstException.MSG_NON_EXIST_TV_SHOW));
			tvShow.setName(name);
			return modelMapper.map(tvShowRepository.save(tvShow), TvShowRestModel.class);
		} catch (Exception e) {
			throw new NetflixErrorException(HttpStatus.INTERNAL_SERVER_ERROR, ConstException.ERROR);
		}
	}

	/**
	 * Delete tv-show.
	 *
	 * @param id: tv-show's id
	 * @throws 	NetflixNotFoundException Tv-show doesn't exist
	 * 			NetflixException Error
	 */
	@Override
	public void deleteTvShow(final Long id) throws NetflixException {
		try {
			tvShowRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new NetflixNotFoundException(ConstException.MSG_NON_EXIST_TV_SHOW);
		} catch (Exception e) {
			throw new NetflixErrorException(HttpStatus.INTERNAL_SERVER_ERROR, ConstException.ERROR);
		}
	}

	/**
	 * Gets the tv-shows by actor.
	 *
	 * @param actorId: actor's id
	 * @return the tv-shows
	 */
	@Override
	public List<TvShow> getTvShowsByActor(Long actorId) {
		return tvShowRepository.findByActorsId(actorId);
	}

	@Override
	public List<AwardRestModel> getAwardsByTvShow(final Long tvShowId) {
		final List<Award> awards = tvShowAwardService.getAwardsByTvShowId(tvShowId);
//		for (Award award : awards) {
//			final List<TvShowAward> tvShowAwards = Lists.newArrayList(award.getTvShowAwardCol());
//			for (TvShowAward tvShowAward : tvShowAwards) {
//				if (tvShowAward.getTvShow().getId() != tvShowId) {
//					tvShowAwards.remove(tvShowAward);
//				}
//			}
//			award.setTvShowAwardCol(Sets.newHashSet(tvShowAwards));
//		}
		return awards.stream()
				.map(award -> modelMapper.map(award, AwardRestModel.class))
				.collect(Collectors.toList());
	}

}
