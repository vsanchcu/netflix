package com.vsanchcu.netflix.entity;

import java.io.Serializable;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CHAPTERS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Chapter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9204769977123137747L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NUMBER")
	private int number;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "DURATION")
	private int duration;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SEASON_ID", referencedColumnName = "ID")
	private Season season;

}
