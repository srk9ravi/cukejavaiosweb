package com.companyName.projectName.ios.steps;
	
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.companyName.projectName.AppiumIOSDriver;
import com.companyName.projectName.ProjectRunnerDriver;
import com.companyName.projectName.ios.screen.ScreenUtils;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
	
public class CucumberHooks {
	
	private static final Logger logger = LoggerFactory
			.getLogger(ProjectRunnerDriver.class);
	public static IOSDriver<MobileElement> iosDriver;
	public static String scenarioName;
	public static Collection<String> tagNames;
	
	/**
	 * This method executes before every scenario using @Before annotation of Cucumber
	 * @param scenario
	 * @throws Throwable
	 */
	@Before()
	public void setUpiosDriver(Scenario scenario) throws Throwable {
		scenarioName = scenario.getName();
		logger.info("Scenario Name:- " + scenarioName);
		tagNames = scenario.getSourceTagNames();
		AppiumIOSDriver.startAppiumServer();
		iosDriver = AppiumIOSDriver.getIOSDriver();	
	}
	
	/**
	 * This method executes after every scenario using @After annotation of Cucumber
	 * To unregister all the services
	 * To quit the appium driver
	 * To stop the appium server
	 */
	@After
	public void teardowniosDriver(Scenario scenario) throws Throwable {
		ScreenUtils.captureScreenshot(scenario);
		AppiumIOSDriver.quitIOSDriver();
		AppiumIOSDriver.stopAppiumServer();
		}
}