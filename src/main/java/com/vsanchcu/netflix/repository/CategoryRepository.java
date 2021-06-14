/*
 * Category's Repository
 * 
 * @author: VSANCHCU
 * @version: 1.0
 */
package com.vsanchcu.netflix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vsanchcu.netflix.entity.Category;

/**
 * The Interface CategoryRepository.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
