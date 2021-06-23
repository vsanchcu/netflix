package com.vsanchcu.netflix.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TvShowAwardKey implements Serializable {

	private static final long serialVersionUID = -2194306984487130951L;

	@Column(name = "TV_SHOW_ID")
	private Long tvShowId;
	
	@Column(name = "AWARD_ID")
	private Long awardId;

}
