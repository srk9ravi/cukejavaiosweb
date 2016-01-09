package com.companyName.projectName.ios.screen;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;

import com.companyName.projectName.AbstractScreen;

public class HomeScreen extends AbstractScreen {
	
	public HomeScreen(IOSDriver<MobileElement> iosDriver) {
		super(iosDriver);
		PageFactory.initElements(new AppiumFieldDecorator(iosDriver, 30, TimeUnit.SECONDS), this);
	}
	
	@iOSFindBy(name = "mainMenuName")
	private MobileElement mainMenu;
	
	public void clickmainMenu(){
		System.out.println("Inside clickmainMenu method of homescreen");
	}
}
