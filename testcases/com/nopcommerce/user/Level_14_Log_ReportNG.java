package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
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

public class Level_14_Log_ReportNG extends BaseTest {
	
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
		
	}
	
	@Test
	public void User_02_Login() {
		log.info("Login - Step 06: Click to login link");
		loginPage = homePage.clicktoLoginLink();

		log.info("Login - Step 07: Input Email with: " + email);
		loginPage.inputToEmailTextbox(email);
		
		log.info("Login - Step 07: Input Password with: " + password);
		loginPage.inputToPasswordTextbox(password);
		
		log.info("Login - Step 08: Click to login button");
		loginPage.clickToLoginButton();
		
		log.info("Login - Step 09: Check My Account Link is Displayed");
		verifyTrue(homePage.isMyAccountLinkDisplayed());
		
		log.info("Login - Step 10: Click to MyAccount link");
		customerInfoPage = homePage.clickToMyAccountLink();
		
		log.info("Login - Step 11: Check Customer Info Page is Displayed");
		verifyFalse(customerInfoPage.isCustomerInfoPageDisplayed());
	}
	
	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

	

}
