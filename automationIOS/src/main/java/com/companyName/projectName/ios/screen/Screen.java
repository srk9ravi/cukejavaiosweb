package com.companyName.projectName.ios.screen;
/**
 * Represents Different Screens in techNucleus ios app 
 *
 */
public enum Screen {
	
	LOGIN_SCREEN					("Login - projectName"),
	HOME_SCREEN					("Home - projectName");

	private final String title;
	
	private Screen(String title){
		this.title=title;
	}
	
	public String getTitle(){
		return title;
	}
}