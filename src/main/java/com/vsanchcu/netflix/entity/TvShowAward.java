package com.vsanchcu.netflix.entity;

import java.io.Serializable;
import java.time.Year;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TVSHOWS_AWARDS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TvShowAward implements Serializable {

	private static final long serialVersionUID = 8544833149653441490L;

	@EmbeddedId
	private TvShowAwardKey id;
	
	@ManyToOne
	@MapsId("tvShowId")
	@JoinColumn(name = "TV_SHOW_ID")
	private TvShow tvShow;
	
	@ManyToOne
	@MapsId("awardId")
	@JoinColumn(name = "AWARD_ID")
	private Award award;
	
	@Column(name = "YEAR")
	private Year year;

}
