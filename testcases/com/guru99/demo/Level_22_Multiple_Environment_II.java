package com.guru99.demo;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.data.UserData;
import com.nopcommerce.data.UserDataMapper;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObject.nopCommerce.user.UserCustomerInfoPageObject;
import pageObject.nopCommerce.user.UserHomePageObject;
import pageObject.nopCommerce.user.UserLoginPageObject;
import pageObject.nopCommerce.user.UserRegisterPageObject;
import environmentConfig.Environment;


public class Level_22_Multiple_Environment_II extends BaseTest {
	
	private WebDriver driver;
	Environment environment;
		
	@Parameters({"browser"})
	@BeforeClass
	public void beforeClass(String browserName) {
		
		environment = ConfigFactory.create(Environment.class);
		driver = getBrowserDriver(browserName, environment.appUrl());
				
		System.out.println(environment.osName());
		System.out.println(driver.getCurrentUrl());

	}

	@Test
	public void User_01_Register() {
				
	}
	
	@Test
	public void User_02_Login() {
		
	}
	
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

	

}
