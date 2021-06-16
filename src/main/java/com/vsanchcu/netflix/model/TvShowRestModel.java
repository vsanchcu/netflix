/*
 * TvShow's RestModel
 * 
 * @author: VSANCHCU
 * @version: 1.0
 */
package com.vsanchcu.netflix.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class TvShowRestModel.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@ToString
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
	
	@JsonProperty(value = "categories")
	private Set<CategoryRestModel> category;
	
	@JsonProperty(value = "advertising")
	private String advertising;
	
//	@JsonProperty(value = "seasons")
//	@JsonManagedReference
//	private List<SeasonRestModel> seasons;

}
