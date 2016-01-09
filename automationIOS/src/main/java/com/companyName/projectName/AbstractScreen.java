package com.companyName.projectName;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;


public class AbstractScreen {
	protected IOSDriver<MobileElement> iosDriver;
	
	/**
	 * @param iosDriver	IOSDriver instance
	 */
	public AbstractScreen(IOSDriver<MobileElement> iosDriver) {
		this.iosDriver = iosDriver;
	}
}