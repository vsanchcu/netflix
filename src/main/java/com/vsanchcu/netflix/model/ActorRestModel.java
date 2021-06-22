/*
 * Actor's RestModel
 * 
 * @author: VSANCHCU
 * @version: 1.0
 */
package com.vsanchcu.netflix.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class ActorRestModel.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class ActorRestModel implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4625940586819833721L;

	@JsonProperty(value = "id")
	private Long id;
	
	@JsonProperty(value = "name")
	private String name;
	
	@JsonProperty(value = "birthday")
	private Date birthday;

}
