/*
 * TvShowChapter's RestModel
 * 
 * @author: VSANCHCU
 * @version: 1.0
 */
package com.vsanchcu.netflix.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * The Class TvShowChapterRestModel.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@AllArgsConstructor
public class TvShowChapterRestModel implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 955176705887986387L;

	@JsonProperty(value = "tv-shows")
	private List<TvShowRestModel> tvShows;
	
	@JsonProperty(value = "chapters")
	private List<ChapterRestModel> chapters;

}
