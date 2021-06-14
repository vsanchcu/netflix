/*
 * TvShow's Repository
 * 
 * @author: VSANCHCU
 * @version: 1.0
 */
package com.vsanchcu.netflix.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vsanchcu.netflix.entity.Category;
import com.vsanchcu.netflix.entity.TvShow;

/**
 * The Interface TvShowRepository.
 */
@Repository
public interface TvShowRepository extends JpaRepository<TvShow, Long> {

	List<TvShow> findByCategory(final Category category);

}
