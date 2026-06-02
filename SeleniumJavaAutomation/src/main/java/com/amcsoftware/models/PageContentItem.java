package com.amcsoftware.models;

import com.google.gson.annotations.SerializedName;

public class PageContentItem{

	@SerializedName("expectedText")
	private String expectedText;

	@SerializedName("description")
	private String description;

	@SerializedName("section")
	private String section;

	@SerializedName("testCaseId")
	private String testCaseId;

	@SerializedName("expectedItemsVisible")
	private boolean expectedItemsVisible;

	@SerializedName("expectedMinItems")
	private int expectedMinItems;

	@SerializedName("expectedHeaderText")
	private String expectedHeaderText;

	@SerializedName("expectedBannerVisible")
	private boolean expectedBannerVisible;

	@SerializedName("expectedTitle")
	private String expectedTitle;

	public String getExpectedText(){
		return expectedText;
	}

	public String getDescription(){
		return description;
	}

	public String getSection(){
		return section;
	}

	public String getTestCaseId(){
		return testCaseId;
	}

	public boolean isExpectedItemsVisible(){
		return expectedItemsVisible;
	}

	public int getExpectedMinItems(){
		return expectedMinItems;
	}

	public String getExpectedHeaderText(){
		return expectedHeaderText;
	}

	public boolean isExpectedBannerVisible(){
		return expectedBannerVisible;
	}

	public String getExpectedTitle(){
		return expectedTitle;
	}
}