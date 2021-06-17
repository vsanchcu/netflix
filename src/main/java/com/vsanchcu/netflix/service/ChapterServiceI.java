/*
 * Chapter's Service Interface
 * 
 * @author: VSANCHCU
 * @version: 1.0
 */
package com.vsanchcu.netflix.service;

import java.util.List;

import com.vsanchcu.netflix.entity.Chapter;
import com.vsanchcu.netflix.exception.NetflixException;
import com.vsanchcu.netflix.exception.NetflixNotFoundException;
import com.vsanchcu.netflix.model.ChapterRestModel;

/**
 * The Interface ChapterServiceI.
 */
public interface ChapterServiceI {

	List<ChapterRestModel> getChaptersByTvShowIdAndSeasonNumber(final Long tvShowId, final int seasonNumber);
	ChapterRestModel getChapterByTvShowIdAndSeasonNumberAndChapterNumber(final Long tvShowId, final int seasonNumber, final int chapterNumber) throws NetflixNotFoundException;
	Chapter findByTvShowIdAndSeasonNumberAndChapterNumber(final Long tvShowId, final int seasonNumber, final int chapterNumber) throws NetflixNotFoundException;
	ChapterRestModel updateChapterName(final Chapter chapter, final String name) throws NetflixException;

}
