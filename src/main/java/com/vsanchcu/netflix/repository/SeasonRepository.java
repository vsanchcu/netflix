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

	List<Season> findByTvShow(final TvShow tvShow);
	Optional<Season> findByTvShowAndNumber(final TvShow tvShow, final int number);

}
