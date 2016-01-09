package com.companyName.projectName;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;

import com.companyName.projectName.ios.screen.HomeScreen;
import com.companyName.projectName.ios.screen.LoginScreen;
import com.companyName.projectName.ios.screen.Screen;

public class ProjectRunnerDriver {
	
	private static ProjectRunnerDriver instance;
	private AbstractScreen currentScreen;
	
	public AbstractScreen doLogin() throws Exception {
		IOSDriver<MobileElement> iosDriver = AppiumIOSDriver.getIOSDriver();
		LoginScreen lScreen = new LoginScreen(iosDriver);
		lScreen.verifyloginScreenIcon();
		return new HomeScreen(iosDriver);
	}
	
	/**
	 * factory for creating screen
	 * 
	 * @param screen
	 * @return
	 * @throws Exception
	 */
	public AbstractScreen selectScreen(Screen screen) throws Exception {

		IOSDriver<MobileElement> iosDriver = AppiumIOSDriver.getIOSDriver();

		switch (screen) {

		case LOGIN_SCREEN:
			currentScreen = new LoginScreen(iosDriver);
			break;

		default:
			throw new IllegalArgumentException("Invalid Screen : "
					+ currentScreen);
		}
		return currentScreen;
	}

	/**
	 * techNuclues app instance
	 * 
	 * @return
	 */
	public static ProjectRunnerDriver getInstance() {
		if (null == instance) {
			instance = new ProjectRunnerDriver();
		}
		return instance;
	}

	/**
	 * return current selected screen
	 * 
	 * @return
	 */
	public AbstractScreen getCurrentScreen() {
		return currentScreen;
	}
}
