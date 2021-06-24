/*
 * Award's Repository
 * 
 * @author: VSANCHCU
 * @version: 1.0
 */
package com.vsanchcu.netflix.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vsanchcu.netflix.entity.Award;

/**
 * The Interface AwardRepository.
 */
@Repository
public interface AwardRepository extends JpaRepository<Award, Long> {

	/**
	 * Find by tv-show's id.
	 *
	 * @param tvShowId: tv-show's id
	 */
	@Query(value = "SELECT A.*, TVA.* FROM TVSHOWS_AWARDS TVA "
			+ "INNER JOIN TV_SHOWS TV ON TVA.TV_SHOW_ID = TV.ID "
			+ "INNER JOIN AWARDS A ON TVA.AWARD_ID = A.ID "
			+ "WHERE TV.ID = :tvShowId", nativeQuery = true)
	List<Award> findByTvShowAwardColTvShowId(final Long tvShowId);

}
