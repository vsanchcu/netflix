/*
 * Chapter's Service Implementation
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
import org.springframework.stereotype.Service;

import com.vsanchcu.netflix.entity.Chapter;
import com.vsanchcu.netflix.exception.NetflixErrorException;
import com.vsanchcu.netflix.exception.NetflixException;
import com.vsanchcu.netflix.exception.NetflixNotFoundException;
import com.vsanchcu.netflix.model.ChapterRestModel;
import com.vsanchcu.netflix.repository.ChapterRepository;
import com.vsanchcu.netflix.util.ConstException;

/**
 * The Class ChapterServiceImpl.
 */
@Service
public class ChapterServiceImpl implements ChapterServiceI {

	/** The chapter repository. */
	@Autowired
	private ChapterRepository chapterRepository;
	
	/** The model mapper. */
	private ModelMapper modelMapper = new ModelMapper();

	/**
	 * Gets the chapters by tv-show's id and season's number.
	 *
	 * @param tvShowId: tv-show's id
	 * @param seasonNumber: season's number
	 * @return the chapters
	 */
	@Override
	public List<ChapterRestModel> getChaptersByTvShowIdAndSeasonNumber(
			final Long tvShowId, final int seasonNumber) {
		return chapterRepository.findByTvShowIdAndSeasonNumber(tvShowId, seasonNumber).stream()
				.map(chapter -> modelMapper.map(chapter, ChapterRestModel.class))
				.collect(Collectors.toList());
	}

	/**
	 * Gets the chapter by tv-show's id and season's number and chapter number.
	 *
	 * @param tvShowId: tv-show's id
	 * @param seasonNumber: season's number
	 * @param chapterNumber: chapter's number
	 * @return the chapter
	 * @throws NetflixNotFoundException Chapter doesn't exist
	 */
	@Override
	public ChapterRestModel getChapterByTvShowIdAndSeasonNumberAndChapterNumber(
			final Long tvShowId, final int seasonNumber, final int chapterNumber) 
					throws NetflixNotFoundException {
		return chapterRepository.findByTvShowIdAndSeasonNumberAndNumber(tvShowId, seasonNumber, chapterNumber)
				.map(chapter -> modelMapper.map(chapter, ChapterRestModel.class))
				.orElseThrow(() -> new NetflixNotFoundException(ConstException.MSG_NON_EXIST_CHAPTER));
	}

	/**
	 * Find by tv-show's id and season's number and chapter's number.
	 *
	 * @param tvShowId: tv-show's id
	 * @param seasonNumber: season's number
	 * @param chapterNumber: chapter's number
	 * @return the chapter
	 * @throws NetflixNotFoundException Chapter doesn't exist
	 */
	@Override
	public Chapter findByTvShowIdAndSeasonNumberAndChapterNumber(
			final Long tvShowId, final int seasonNumber, final int chapterNumber) 
					throws NetflixNotFoundException {
		return chapterRepository.findByTvShowIdAndSeasonNumberAndNumber(
				tvShowId, seasonNumber, chapterNumber)
				.orElseThrow(() -> new NetflixNotFoundException(ConstException.MSG_NON_EXIST_CHAPTER));
	}

	/**
	 * Update chapter name.
	 *
	 * @param chapter: the chapter
	 * @param name: the name
	 * @return the updated chapter
	 * @throws NetflixException
	 */
	@Override
	public ChapterRestModel updateChapterName(final Chapter chapter, final String name) 
			throws NetflixException {
		chapter.setName(name);
		try {
			return modelMapper.map(chapterRepository.save(chapter), ChapterRestModel.class);
		} catch (Exception e) {
			throw new NetflixErrorException(HttpStatus.INTERNAL_SERVER_ERROR, ConstException.ERROR);
		}
	}

	/**
	 * Gets the chapters by actor.
	 *
	 * @param actorId: actor's id
	 * @return the chapters
	 */
	@Override
	public List<Chapter> getChaptersByActor(final Long actorId) {
		return chapterRepository.findBySeasonTvShowActorsId(actorId);
	}

}
