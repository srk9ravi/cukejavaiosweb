package com.companyName.projectName.ios.screen;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.PageFactory;

import com.companyName.projectName.AbstractScreen;
import com.companyName.projectName.AppiumIOSDriver;
import com.companyName.projectName.ios.steps.CucumberHooks;

import cucumber.api.Scenario;

public class ScreenUtils extends AbstractScreen {

	public ScreenUtils(IOSDriver<MobileElement> iosDriver) {
		super(iosDriver);
		PageFactory.initElements(new AppiumFieldDecorator(iosDriver, 30, TimeUnit.SECONDS), this);	
	}
	
	/**
	 * To generate the cucumber sandwich reports
	 * @throws IOException 
	 */
	public static void cucumberSandwichReports() throws IOException
	{
		String cucumberSandwichJarpath = System.getProperty("user.dir")+ "/src/test/resources/cucumber-sandwich.jar";
		String reportsFolder = System.getProperty("user.dir")+ "/Reports/";
		Runtime.getRuntime().exec("java -jar "+cucumberSandwichJarpath+"-n "+"-f"+ reportsFolder+"-o"+reportsFolder);

	}
	
	/**
	 * To capture the screenshot
	 * and save with current date and time in the project Reports folder under Common
	 * @param path_screenshot
	 * @throws Exception 
	 * @throws WebDriverException 
	 */
	public static void captureScreenshot(Scenario scenario) throws WebDriverException, Exception{
		if(scenario.getStatus().equalsIgnoreCase("Failed")){
		SimpleDateFormat format = new SimpleDateFormat("M:dd:yyyy HH-mm z");
		String path_screenshot = System.getProperty("user.dir") + "/Reports/iOS/Screenshots/";
	    File srcFile=AppiumIOSDriver.getIOSDriver().getScreenshotAs(OutputType.FILE);
	    String screenShotName = CucumberHooks.scenarioName+"--"+ format.format(new Date());
	    File targetFile=new File(path_screenshot+screenShotName+".jpg");
	    final byte[]  screenshotStream= AppiumIOSDriver.getIOSDriver().getScreenshotAs(OutputType.BYTES);
	    scenario.embed(screenshotStream,"image/jpeg");
	    FileUtils.copyFile(srcFile,targetFile);
		}
	}
}
