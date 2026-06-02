package com.amcsoftware.models;

import com.google.gson.annotations.SerializedName;

public class HomePageItem{

	@SerializedName("expectedMinResults")
	private int expectedMinResults;

	@SerializedName("searchTerm")
	private String searchTerm;

	@SerializedName("description")
	private String description;

	@SerializedName("expectedResultsVisible")
	private boolean expectedResultsVisible;

	@SerializedName("testCaseId")
	private String testCaseId;

	public int getExpectedMinResults(){
		return expectedMinResults;
	}

	public String getSearchTerm(){
		return searchTerm;
	}

	public String getDescription(){
		return description;
	}

	public boolean isExpectedResultsVisible(){
		return expectedResultsVisible;
	}

	public String getTestCaseId(){
		return testCaseId;
	}
}