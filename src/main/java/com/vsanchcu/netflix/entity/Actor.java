/*
 * Actor's Entity
 * 
 * @author: VSANCHCU
 * @version: 1.0
 */
package com.vsanchcu.netflix.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Class Actor.
 */
@Entity
@Table(name = "ACTORS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Actor implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1505133102108357075L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NAME")
	@NotBlank
	private String name;
	
	@Column(name = "BIRTHDAY")
	@NotNull
	private Date birthday;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "actors")
	private Set<TvShow> tvShows;

}
