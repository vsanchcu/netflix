package com.vsanchcu.netflix.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class TvShowAwardRestModel implements Serializable {

	private static final long serialVersionUID = 1663538617691857481L;

	@JsonProperty(value = "award")
	private AwardRestModel award;
	
	@JsonProperty(value = "year")
	@JsonFormat(pattern = "yyyy")
	private Date year;

}
