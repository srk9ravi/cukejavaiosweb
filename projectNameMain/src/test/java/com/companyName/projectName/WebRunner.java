package com.companyName.projectName;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
tags = {"@debugweb"},
features = {"src/main/resources/Common", "src/main/resources/Web"},
glue = {"com.companyName.projectName.web.steps"},		
plugin = {"pretty", "json:Reports/Web/cucumber.json"}
				     )
public class WebRunner {
	
	@BeforeClass
	public static void beforeRun(){

	}
	
	@AfterClass
	public static void afterRun(){

	}
}


