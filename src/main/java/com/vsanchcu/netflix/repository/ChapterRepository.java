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

/**
 * The Interface ChapterRepository.
 */
@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {

	Optional<Chapter> findByIdAndNumber(final Long id, final int number);
	
	/**
	 * Find by tv-show's id and season's number.
	 *
	 * @param tvShowId: tv-show's id
	 * @param seasonNumber: season's number
	 * @return the chapters
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
	 * Find by tv-show's id and season's number and chapter's number.
	 *
	 * @param tvShowId: tv-show's id
	 * @param seasonNumber: season's number
	 * @param number: chapter's number
	 * @return the chapter
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

	/**
	 * Find by actors.
	 *
	 * @param actorId: actor's id
	 * @return the chapters
	 */
	List<Chapter> findBySeasonTvShowActorsId(final Long actorId);

}
