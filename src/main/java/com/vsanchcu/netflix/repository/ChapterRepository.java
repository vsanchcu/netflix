/*
 * Chapter's Repository
 * 
 * @author: VSANCHCU
 * @version: 1.0
 */
package com.vsanchcu.netflix.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vsanchcu.netflix.entity.Chapter;
import com.vsanchcu.netflix.entity.Season;

/**
 * The Interface ChapterRepository.
 */
@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {

	/**
	 * Find by season.
	 *
	 * @param season the season
	 * @return the list
	 */
	List<Chapter> findBySeason(final Season season);
	
	/**
	 * Find by season and number.
	 *
	 * @param season the season
	 * @param number the number
	 * @return the optional
	 */
	Optional<Chapter> findBySeasonAndNumber(final Season season, final int number);
	
	/**
	 * Gets the chapters by tv show id and season number.
	 *
	 * @param tvShowId the tv show id
	 * @param seasonNumber the season number
	 * @return the chapters by tv show id and season number
	 */
	@Query(value = "SELECT c.* FROM CHAPTERS c "
			+ "INNER JOIN SEASONS s ON c.SEASON_ID = s.ID "
			+ "INNER JOIN TV_SHOWS tv ON s.TV_SHOW_ID = tv.ID "
			+ "WHERE tv.ID = :tvShowId "
			+ "AND s.NUMBER = :seasonNumber", nativeQuery = true)
	List<Chapter> findByTvShowIdAndSeasonNumber(
			@Param("tvShowId") final Long tvShowId, 
			@Param("seasonNumber") final int seasonNumber);
	
	/**
	 * Gets the chapter by tv show id and season number and number.
	 *
	 * @param tvShowId the tv show id
	 * @param seasonNumber the season number
	 * @param number the number
	 * @return the chapter by tv show id and season number and number
	 */
	@Query(value = "SELECT c.* FROM CHAPTERS c "
			+ "INNER JOIN SEASONS s ON c.SEASON_ID = s.ID "
			+ "INNER JOIN TV_SHOWS tv ON s.TV_SHOW_ID = tv.ID "
			+ "WHERE tv.ID = :tvShowId "
			+ "AND s.NUMBER = :seasonNumber "
			+ "AND c.NUMBER = :number", nativeQuery = true)
	Optional<Chapter> findByTvShowIdAndSeasonNumberAndNumber(
			@Param("tvShowId") final Long tvShowId, 
			@Param("seasonNumber") final int seasonNumber, 
			@Param("number") final int number);

}
