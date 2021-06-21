/*
 * Season's Repository
 * 
 * @author: VSANCHCU
 * @version: 1.0
 */
package com.vsanchcu.netflix.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vsanchcu.netflix.entity.Season;
import com.vsanchcu.netflix.entity.TvShow;

/**
 * The Interface SeasonRepository.
 */
@Repository
public interface SeasonRepository extends JpaRepository<Season, Long> {

	/**
	 * Find by tv-show.
	 *
	 * @param tvShow: tv-show
	 * @return the seasons
	 */
	List<Season> findByTvShow(final TvShow tvShow);
	
	/**
	 * Find by tv-show and season's number.
	 *
	 * @param tvShow: tv show
	 * @param number: season's number
	 * @return the season
	 */
	Optional<Season> findByTvShowAndNumber(final TvShow tvShow, final int number);

}
