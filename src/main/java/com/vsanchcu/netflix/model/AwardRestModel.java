package com.vsanchcu.netflix.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class AwardRestModel implements Serializable {

	private static final long serialVersionUID = -2078582251720176495L;

	@JsonProperty(value = "id")
	private Long id;
	
	@JsonProperty(value = "name")
	private String name;
	
//	@JsonProperty(value = "year")
//	private Set<TvShowAwardRestModel> tvShowAwardCol;

}
