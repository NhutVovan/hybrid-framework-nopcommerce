package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObject.nopCommerce.user.UserCustomerInfoPageObject;
import pageObject.nopCommerce.user.UserHomePageObject;
import pageObject.nopCommerce.user.UserLoginPageObject;
import pageObject.nopCommerce.user.UserRegisterPageObject;


public class Level_18_Pattern_Object extends BaseTest {
	
	private WebDriver driver;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserCustomerInfoPageObject customerInfoPage;
	
	private String firstName, lastName, email, password;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getUserHomePage(driver);
		
		firstName = "John";
		lastName = "Terry";
		email = "Johnterry" + GetRandomNumber() + "@gmail.com";
		password = "123456";

	}

	@Test
	public void User_01_Register() {
		log.info("Register - Step 01: Click to register link");
		registerPage =  homePage.clickToRegisterLink();
		
		log.info("Register - Step 02: Input First Name with: " + firstName);
		registerPage.inputToTextboxByID(driver, "FirstName", firstName);
		
		log.info("Register - Step 02: Input Last Name with: " + lastName);
		registerPage.inputToTextboxByID(driver, "LastName", lastName);
		
		log.info("Register - Step 02: Input Email Name with: " + email);
		registerPage.inputToTextboxByID(driver, "Email", email);
		
		log.info("Register - Step 02: Input Password with: " + password);
		registerPage.inputToTextboxByID(driver, "Password", password);
		
		log.info("Register - Step 02: Input Confirm Password with: " + password);
		registerPage.inputToTextboxByID(driver, "ConfirmPassword", password);
		
		log.info("Register - Step 03: Click to register button");
		registerPage.clickToButtonByText(driver, "Register");
		
		log.info("Register - Step 04: Check Register Success Message");
		verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		
		log.info("Register - Step 05: Click to logout link");
		homePage = registerPage.clickToLogoutLink();
		
	}
	
	@Test
	public void User_02_Login() {
		log.info("Login - Step 06: Click to login link");
		loginPage = homePage.clicktoLoginLink();

		log.info("Login - Step 07: Input Email with: " + email);
		loginPage.inputToTextboxByID(driver, "Email", email);
		
		log.info("Login - Step 07: Input Password with: " + password);
		loginPage.inputToTextboxByID(driver, "Password", password);
		
		log.info("Login - Step 08: Click to login button");
		loginPage.clickToButtonByText(driver, "Log in");
		homePage = PageGeneratorManager.getUserHomePage(driver);
		
		log.info("Login - Step 09: Check My Account Link is Displayed");
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
		
		log.info("Login - Step 10: Click to MyAccount link");
		customerInfoPage = homePage.clickToMyAccountLink();
		
		log.info("Login - Step 11: Check Customer Info Page is Displayed");
		Assert.assertTrue(customerInfoPage.isCustomerInfoPageDisplayed());
	}
	
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

	

}
