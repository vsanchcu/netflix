/*
 * Award's Entity
 * 
 * @author: VSANCHCU
 * @version: 1.0
 */
package com.vsanchcu.netflix.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Class Award.
 */
@Entity
@Table(name = "AWARDS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Award implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5322598413428210125L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NAME")
	private String name;
	
//	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "awards")
//	private Set<TvShow> tvShows;
	
	@OneToMany(mappedBy = "award")
	private Set<TvShowAward> tvShowAwardCol;

}
