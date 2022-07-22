package com.nopcommerce.common;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObject.nopCommerce.user.UserAddressPageObject;
import pageObject.nopCommerce.user.UserCustomerInfoPageObject;
import pageObject.nopCommerce.user.UserHomePageObject;
import pageObject.nopCommerce.user.UserLoginPageObject;
import pageObject.nopCommerce.user.UserMyProductReviewsPageObject;
import pageObject.nopCommerce.user.UserRegisterPageObject;
import pageObject.nopCommerce.user.UserRewardPointPageObject;

public class Common_01_Register extends BaseTest {
	
	private WebDriver driver;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserCustomerInfoPageObject customerInfoPage;
	
	private String firstName, lastName;
	public static String email, password;
	

	@Parameters("browser")
	@BeforeTest(description = "Create new user for all classes test")
	public void Register(String browserName) {
		driver = getBrowserDriver(browserName);
		
		homePage = PageGeneratorManager.getUserHomePage(driver);
		
		firstName = "John";
		lastName = "Terry";
		email = "Johnterry" + GetRandomNumber() + "@gmail.com";
		password = "123456";
		
		log.info("Register - Step 01: Click to register link");
		registerPage =  homePage.clickToRegisterLink();
		
		log.info("Register - Step 02: Input First Name with: " + firstName);
		registerPage.inputToFirstnameTextbox(firstName);
		
		log.info("Register - Step 02: Input Last Name with: " + lastName);
		registerPage.inputToLastnameTextbox(lastName);
		
		log.info("Register - Step 02: Input Email Name with: " + email);
		registerPage.inputToEmailTextbox(email);
		
		log.info("Register - Step 02: Input Password with: " + password);
		registerPage.inputToPasswordTextbox(password);
		
		log.info("Register - Step 02: Input Confirm Password with: " + password);
		registerPage.inputToConfirmPasswordTextbox(password);
		
		log.info("Register - Step 03: Click to register button");
		registerPage.clickToRegisterButton();
		
		log.info("Register - Step 04: Check Register Success Message");
		verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		
		log.info("Register - Step 05: Click to logout link");
		homePage = registerPage.clickToLogoutLink();
		
		driver.quit();
		
	}
	

}