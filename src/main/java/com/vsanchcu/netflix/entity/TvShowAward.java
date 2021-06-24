package com.vsanchcu.netflix.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TVSHOWS_AWARDS")
@IdClass(TvShowAwardKey.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TvShowAward implements Serializable {

	private static final long serialVersionUID = 8544833149653441490L;

	@Id
	@ManyToOne
	@JoinColumn(name = "TV_SHOW_ID", referencedColumnName = "ID")
	private TvShow tvShow;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "AWARD_ID", referencedColumnName = "ID")
	private Award award;
	
	@JoinColumn(name = "YEAR")
	private Date year;

}
