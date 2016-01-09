package com.companyName.projectName.ios.steps;

import com.companyName.projectName.ProjectRunnerDriver;
import com.companyName.projectName.ios.screen.LoginScreen;
import com.companyName.projectName.ios.screen.Screen;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class LoginSteps {
	
	@Given("^I am testing the sample iOS$")
	public void i_am_testing_the_sample_iOS() throws Throwable {
		LoginScreen lscreen = (LoginScreen) ProjectRunnerDriver.getInstance().selectScreen(Screen.LOGIN_SCREEN);
		lscreen.verifyloginScreenIcon();
	}

	@Then("^I should see it is success$")
	public void i_should_see_it_is_success() throws Throwable {
		System.out.println("The test is successfull");
	}
	
}
