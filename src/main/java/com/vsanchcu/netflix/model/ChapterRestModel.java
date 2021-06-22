/*
 * Chapter's RestModel
 * 
 * @author: VSANCHCU
 * @version: 1.0
 */
package com.vsanchcu.netflix.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class ChapterRestModel.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class ChapterRestModel implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 228652500206711650L;

	@JsonProperty(value = "id")
	private Long id;
	
	@JsonProperty(value = "number")
	private int number;
	
	@JsonProperty(value = "name")
	private String name;
	
	@JsonProperty(value = "duration")
	private int duration;

}
