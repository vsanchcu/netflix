/*
 * Chapter's Service Interface
 * 
 * @author: VSANCHCU
 * @version: 1.0
 */
package com.vsanchcu.netflix.service;

import java.util.List;

import com.vsanchcu.netflix.entity.Chapter;
import com.vsanchcu.netflix.entity.Season;
import com.vsanchcu.netflix.entity.TvShow;
import com.vsanchcu.netflix.model.ChapterRestModel;

/**
 * The Interface ChapterServiceI.
 */
public interface ChapterServiceI {

	List<ChapterRestModel> getChaptersByTvShowsAndSeason(final TvShow tvShow, final Season season);
	ChapterRestModel getChapterByTvShowAndSeasonAndNumber(final TvShow tvShow, final Season season, final int number);
	Chapter findByTvShowAndSeasonAndNumber(final TvShow tvShow, final Season season, final int number);
	ChapterRestModel updateChapter(final Chapter chapter);

}
