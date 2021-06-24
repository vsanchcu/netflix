/*
 * Award's Service Interface
 * 
 * @author: VSANCHCU
 * @version: 1.0
 */
package com.vsanchcu.netflix.service;

import java.util.List;

import com.vsanchcu.netflix.entity.Award;

/**
 * The Interface AwardServiceI.
 */
public interface AwardServiceI {

	List<Award> getAwardsByTvShowId(final Long tvShowId);

}
