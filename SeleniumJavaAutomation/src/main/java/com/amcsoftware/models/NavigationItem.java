package com.amcsoftware.models;

import com.google.gson.annotations.SerializedName;

public class NavigationItem{

	@SerializedName("expectedUrlContains")
	private String expectedUrlContains;

	@SerializedName("expectedPageTitle")
	private String expectedPageTitle;

	@SerializedName("description")
	private String description;

	@SerializedName("linkText")
	private String linkText;

	@SerializedName("testCaseId")
	private String testCaseId;

	public String getExpectedUrlContains(){
		return expectedUrlContains;
	}

	public String getExpectedPageTitle(){
		return expectedPageTitle;
	}

	public String getDescription(){
		return description;
	}

	public String getLinkText(){
		return linkText;
	}

	public String getTestCaseId(){
		return testCaseId;
	}
}