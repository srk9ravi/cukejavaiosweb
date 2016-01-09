package com.companyName.projectName;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.companyName.utils.Utils;

import cucumber.api.junit.Cucumber;
import cucumber.api.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
tags = {"@debugios"},
features = {"src/main/resources/Common", "src/main/resources/iOS"},
glue = {"com.companyName.projectName.ios.steps"},		
plugin = {"pretty", "json:Reports/iOS/cucumber.json"}
				     )
public class IOSRunner {
	
	private static final Logger logger = LoggerFactory.getLogger(IOSRunner.class);
	
	@BeforeClass
	public static void beforeRun(){
		logger.debug("Starting iOS Tests");
	}
	
	@AfterClass
	public static void afterRun() throws Exception{
		Utils.ioscucumberSandwichReports();
		logger.debug("Done with iOS Tests");
	}
}


