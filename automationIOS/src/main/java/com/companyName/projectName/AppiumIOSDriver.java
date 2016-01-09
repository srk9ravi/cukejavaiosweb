package com.companyName.projectName;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.companyName.utils.TestProperties;

/**
 * This class is to define and instantiate the Appium server and Appium ios driver
 */
public class AppiumIOSDriver {
	private static final Logger logger = LoggerFactory
			.getLogger(ProjectRunnerDriver.class);
	private static DesiredCapabilities capabilities = null;
	public static IOSDriver<MobileElement> iosDriver = null;
	private static String testProperties = "test.properties";
	private static AppiumDriverLocalService appiumServer = null;
	
	/**
	 * This method is to start the Appium server as a background process
	 */
	public static void startAppiumServer() throws Exception{
		if (iosDriver == null) {
			try {
				TestProperties.loadProperties(testProperties);
				
				appiumServer = AppiumDriverLocalService
				.buildService(new AppiumServiceBuilder()
				.usingDriverExecutable(new File("/usr/local/bin/node"))
				.withAppiumJS(
				new File(TestProperties.get("appium_JsonFilePath")))
				.withIPAddress(TestProperties.get("appium_serverIP")).usingPort(Integer.parseInt(TestProperties.get("appium_serverPort"))));
				appiumServer.start();
				if(appiumServer.isRunning()){
					logger.info("Started **Appium server**");	
				}
				else{
					logger.error("Failed to start **Appium server**");
				} 
			} catch (Exception e) {
				logger.error("test.Properties not loaded", e);
			}
		}
	}
	
	/**
	 * This method is to stop the Appium server running background
	 */
	public static void stopAppiumServer() throws Exception{
		appiumServer.stop();
		logger.error("**Appium server** Stopped");
	}
	
	/**
	 * This method is to setup the appium iOS driver
	 */
	public static IOSDriver<MobileElement> getIOSDriver() throws Exception {
		// initiating the IOSDriver
		if (iosDriver == null) {
			try {
				TestProperties.loadProperties(testProperties);
			capabilities = new DesiredCapabilities();
			capabilities.setCapability(
					MobileCapabilityType.AUTOMATION_NAME,
					TestProperties.get("automationName"));
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,
					TestProperties.get("platformName"));
			capabilities.setCapability(
					MobileCapabilityType.PLATFORM_VERSION,
					TestProperties.get("platformVersion"));
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,
					TestProperties.get("deviceName"));
			
			if(TestProperties.get("deviceName").equalsIgnoreCase("iPhone Simulator")){
				
				capabilities.setCapability(MobileCapabilityType.APP,
						TestProperties.get("simulatorappPath"));
				
			}else{
				
				capabilities.setCapability(MobileCapabilityType.UDID,
						TestProperties.get("udid"));
				capabilities.setCapability(MobileCapabilityType.APP,
						TestProperties.get("phoneappPath"));
				
			}

			capabilities.setCapability("bundleId",
					TestProperties.get("bundleId"));
			capabilities.setCapability(
					"locationServicesEnabled",
					true);
			capabilities.setCapability(
					"locationServicesAuthorized",
					true);
			
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("test.Properties not loaded",e);
			}	
			iosDriver = new IOSDriver<MobileElement>(new URL(
					TestProperties.get("ios_serverPort")), capabilities);
			iosDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			logger.info("Started Test with **IOSDriver**");
		}
		return iosDriver;
	  }
		
	/**
	 * This method is to quit the appium iOS driver
	 */
		public static void quitIOSDriver() {
			iosDriver.quit();
			iosDriver = null;
			logger.info("**IOSDriver** quit");
		}
}
