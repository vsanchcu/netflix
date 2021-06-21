package com.vsanchcu.netflix.util;

public class ConstRest {

	public static final String RES_CATEGORY = "/categories";
	public static final String RES_TVS_HOW = "/tv-shows";
	public static final String RES_SEASON = "/tv-shows/{tvShowId}/seasons";
	public static final String RES_CHAPTER = "/tv-shows/{tvShowId}/seasons/{seasonNumber}/chapters";
	public static final String RES_ACTOR = "/actors";
	
	public static final String PATH_VAR_CATEGORY_ID = "/{categoryId}";
	public static final String PATH_VAR_TV_SHOW_ID = "/{tvShowId}";
	public static final String PATH_VAR_SEASON_NUM = "/{seasonNumber}";
	public static final String PATH_VAR_CHAPTER_NUM = "/{chapterNumber}";
	public static final String PATH_VAR_ACTOR_ID = "/{actorId}";

}
