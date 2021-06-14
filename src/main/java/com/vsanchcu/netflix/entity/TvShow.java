/*
 * TvShow's Entity
 * 
 * @author: VSANCHCU
 * @version: 1.0
 */
package com.vsanchcu.netflix.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Class TvShow.
 */
@Entity
@Table(name = "TV_SHOWS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TvShow implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 275330178620787369L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "SHORT_DESC")
	private String shortDesc;
	
	@Column(name = "LONG_DESC")
	private String longDesc;
	
	@Column(name = "YEAR")
	private Date year;
	
	@Column(name = "RECOMMENDED_AGE")
	private int recommAge;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ID")
	private Category category;
	
	@Column(name = "ADVERTISING")
	private String advertising;

}
