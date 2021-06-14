/*
 * TvShow's RestModel
 * 
 * @author: VSANCHCU
 * @version: 1.0
 */
package com.vsanchcu.netflix.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class TvShowRestModel.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class TvShowRestModel implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1231703172030719737L;

	@JsonProperty(value = "id")
	private Long id;
	
	@JsonProperty(value = "name")
	private String name;
	
	@JsonProperty(value = "shortDesc")
	private String shortDesc;
	
	@JsonProperty(value = "longDesc")
	private String longDesc;
	
	@JsonProperty(value = "year")
	@JsonFormat(pattern = "yyyy")
	private Date year;
	
	@JsonProperty(value = "recomAge")
	private int recommAge;
	
	@JsonProperty(value = "category")
	private CategoryRestModel category;
	
	@JsonProperty(value = "advertising")
	private String advertising;
	
	@JsonProperty(value = "seasons")
	private List<SeasonRestModel> seasons;

}
