/*
 * TvShowAward's Repository
 * 
 * @author: VSANCHCU
 * @version: 1.0
 */
package com.vsanchcu.netflix.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vsanchcu.netflix.entity.TvShowAward;
import com.vsanchcu.netflix.entity.TvShowAwardKey;

/**
 * The Interface TvShowAwardRepository.
 */
@Repository
public interface TvShowAwardRepository extends JpaRepository<TvShowAward, TvShowAwardKey> {

	/**
	 * Find by tv-show's id.
	 *
	 * @param tvShowId: tv-show's id
	 * @return the tv-showAwards
	 */
	List<TvShowAward> findByTvShowId(final Long tvShowId);

}
