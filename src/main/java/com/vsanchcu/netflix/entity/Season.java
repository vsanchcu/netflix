package com.vsanchcu.netflix.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SEASONS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Season implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9129602107139586553L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NUMBER")
	private int number;
	
	@Column(name = "NAME")
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TV_SHOW_ID", referencedColumnName = "ID")
	private TvShow tvShow;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "season")
	private List<Chapter> chapters;

}
