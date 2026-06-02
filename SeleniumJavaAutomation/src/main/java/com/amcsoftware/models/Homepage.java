package com.amcsoftware.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Homepage{

	@SerializedName("navigation")
	private List<NavigationItem> navigation;

	@SerializedName("pageContent")
	private List<PageContentItem> pageContent;

	@SerializedName("login")
	private List<LoginItem> login;

	@SerializedName("homePage")
	private List<HomePageItem> homePage;

	public List<NavigationItem> getNavigation(){
		return navigation;
	}

	public List<PageContentItem> getPageContent(){
		return pageContent;
	}

	public List<LoginItem> getLogin(){
		return login;
	}

	public List<HomePageItem> getHomePage(){
		return homePage;
	}
}