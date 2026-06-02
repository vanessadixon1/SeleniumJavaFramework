package com.amcsoftware.models;

import com.google.gson.annotations.SerializedName;

public class LoginItem{

	@SerializedName("expectedErrorMessage")
	private String expectedErrorMessage;

	@SerializedName("password")
	private String password;

	@SerializedName("description")
	private String description;

	@SerializedName("expectedLoggedIn")
	private boolean expectedLoggedIn;

	@SerializedName("testCaseId")
	private String testCaseId;

	@SerializedName("username")
	private String username;

	@SerializedName("expectedWelcomeMessage")
	private String expectedWelcomeMessage;

	public String getExpectedErrorMessage(){
		return expectedErrorMessage;
	}

	public String getPassword(){
		return password;
	}

	public String getDescription(){
		return description;
	}

	public boolean isExpectedLoggedIn(){
		return expectedLoggedIn;
	}

	public String getTestCaseId(){
		return testCaseId;
	}

	public String getUsername(){
		return username;
	}

	public String getExpectedWelcomeMessage(){
		return expectedWelcomeMessage;
	}
}