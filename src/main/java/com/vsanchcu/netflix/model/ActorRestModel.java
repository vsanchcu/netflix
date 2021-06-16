package com.vsanchcu.netflix.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ActorRestModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4625940586819833721L;

	@JsonProperty(value = "id")
	private Long id;
	
	@JsonProperty(value = "name")
	private String name;
	
	@JsonProperty(value = "birthday")
	private Date birthday;
	
	@JsonProperty(value = "tvShows")
	private List<TvShowRestModel> tvShows;

}
