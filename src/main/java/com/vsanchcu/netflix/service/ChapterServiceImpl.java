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
import org.springframework.stereotype.Service;

import com.vsanchcu.netflix.entity.Chapter;
import com.vsanchcu.netflix.entity.Season;
import com.vsanchcu.netflix.entity.TvShow;
import com.vsanchcu.netflix.model.ChapterRestModel;
import com.vsanchcu.netflix.repository.ChapterRepository;

/**
 * The Class ChapterServiceImpl.
 */
@Service
public class ChapterServiceImpl implements ChapterServiceI {

	/** The chapter repository. */
	@Autowired
	private ChapterRepository chapterRepository;
	
	/** The season service. */
//	@Autowired
//	private SeasonServiceI seasonService;
	
	/** The model mapper. */
	private ModelMapper modelMapper = new ModelMapper();

	/**
	 * Gets the chapters by tv shows and season.
	 *
	 * @param tvShow the tv show
	 * @param season the season
	 * @return the chapters by tv shows and season
	 */
	@Override
	public List<ChapterRestModel> getChaptersByTvShowsAndSeason(TvShow tvShow, Season season) {
//		season = seasonService.findByTvShowAndNumber(tvShow, season.getNumber());
//		return chapterRepository.findBySeason(season).stream()
//				.map(chapter -> modelMapper.map(chapter, ChapterRestModel.class))
//				.collect(Collectors.toList());
		return chapterRepository.findByTvShowIdAndSeasonNumber(tvShow.getId(), season.getNumber()).stream()
				.map(chapter -> modelMapper.map(chapter, ChapterRestModel.class))
				.collect(Collectors.toList());
	}

	/**
	 * Gets the chapter by tv show and season and number.
	 *
	 * @param tvShow the tv show
	 * @param season the season
	 * @param number the number
	 * @return the chapter by tv show and season and number
	 */
	@Override
	public ChapterRestModel getChapterByTvShowAndSeasonAndNumber(TvShow tvShow, Season season, int number) {
//		season = seasonService.findByTvShowAndNumber(tvShow, season.getNumber());
//		return chapterRepository.findBySeasonAndNumber(season, number)
//				.map(chapter -> modelMapper.map(chapter, ChapterRestModel.class))
//				.orElse(null);
		return chapterRepository.findByTvShowIdAndSeasonNumberAndNumber(
				tvShow.getId(), season.getNumber(), number)
				.map(chapter -> modelMapper.map(chapter, ChapterRestModel.class))
				.orElse(null);
	}

	@Override
	public Chapter findByTvShowAndSeasonAndNumber(TvShow tvShow, Season season, int number) {
		return chapterRepository.findByTvShowIdAndSeasonNumberAndNumber(
				tvShow.getId(), season.getNumber(), number).orElse(null);
	}

	@Override
	public ChapterRestModel updateChapter(Chapter chapter) {
		return modelMapper.map(chapterRepository.save(chapter), ChapterRestModel.class);
	}

}
