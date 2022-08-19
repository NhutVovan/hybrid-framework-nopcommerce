package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.data.UserData;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObject.nopCommerce.user.UserCustomerInfoPageObject;
import pageObject.nopCommerce.user.UserHomePageObject;
import pageObject.nopCommerce.user.UserLoginPageObject;
import pageObject.nopCommerce.user.UserRegisterPageObject;


public class Level_20_Manage_Data_Test extends BaseTest {
	
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
		
		firstName = UserData.FIRST_NAME;
		lastName = UserData.LAST_NAME;
		email = UserData.EMAIL_NAME + GetRandomNumber() + "@gmail.com";
		password = UserData.PASSWORD;

	}

	@Test
	public void User_01_Register() {
		log.info("Register - Step 01: Click to register link");
		registerPage =  homePage.clickToRegisterLink();
		
		log.info("Register - Step 02: Input First Name with: " + firstName);
		registerPage.inputToTextboxByID(driver, "FirstName", firstName);
		
		log.info("Register - Step 03: Input Last Name with: " + lastName);
		registerPage.inputToTextboxByID(driver, "LastName", lastName);
		
		log.info("Register - Step 04: Input Email Name with: " + email);
		registerPage.inputToTextboxByID(driver, "Email", email);
		
		log.info("Register - Step 05: Input Password with: " + password);
		registerPage.inputToTextboxByID(driver, "Password", password);
		
		log.info("Register - Step 06: Input Confirm Password with: " + password);
		registerPage.inputToTextboxByID(driver, "ConfirmPassword", password);
		
		log.info("Register - Step 07: Click to register button");
		registerPage.clickToButtonByText(driver, "Register");
		
		log.info("Register - Step 08: Check Register Success Message");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		
		log.info("Register - Step 09: Click to logout link");
		homePage = registerPage.clickToLogoutLink();
		
	}
	
	@Test
	public void User_02_Login() {
		log.info("Login - Step 01: Click to login link");
		loginPage = homePage.clicktoLoginLink();

		log.info("Login - Step 02: Input Email with: " + email);
		loginPage.inputToTextboxByID(driver, "Email", email);
		
		log.info("Login - Step 03: Input Password with: " + password);
		loginPage.inputToTextboxByID(driver, "Password", password);
		
		log.info("Login - Step 04: Click to login button");
		loginPage.clickToButtonByText(driver, "Log in");
		homePage = PageGeneratorManager.getUserHomePage(driver);
		
		log.info("Login - Step 05: Check My Account Link is Displayed");
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
		
		log.info("Login - Step 06: Click to MyAccount link");
		customerInfoPage = homePage.clickToMyAccountLink();
		
		log.info("Login - Step 07: Check Customer Info Page is Displayed");
		Assert.assertTrue(customerInfoPage.isCustomerInfoPageDisplayed());
	}
	
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

	

}
