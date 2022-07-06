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

public class Level_12_Assert_Verify extends BaseTest {
	
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
	public void User_01_Register_Login_Customer_Info() {
		
		System.out.println("User_01 - Step 01: Click to register link");
		registerPage =  homePage.clickToRegisterLink();
		
		System.out.println("User_01 - Step 02: Input to required fields");
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(email);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		
		System.out.println("User_01 - Step 03: Click to register button");
		registerPage.clickToRegisterButton();
		
		System.out.println("User_01 - Step 04: Check registration completed");
		verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		
		System.out.println("User_01 - Step 05: Click to logout link");
		homePage = registerPage.clickToLogoutLink();
		
		System.out.println("User_01 - Step 06: Click to login link");
		loginPage = homePage.clicktoLoginLink();

		System.out.println("User_01 - Step 07: Input to textbox");
		loginPage.inputToEmailTextbox(email);
		loginPage.inputToPasswordTextbox(password);
		
		System.out.println("User_01 - Step 08: Click to login button");
		loginPage.clickToLoginButton();
		
		verifyFalse(homePage.isMyAccountLinkDisplayed());
		
		System.out.println("User_01 - Step 09: Click to MyAccount link");
		customerInfoPage = homePage.clickToMyAccountLink();
		
		verifyFalse(customerInfoPage.isCustomerInfoPageDisplayed());
	}
	
	
	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

	

}
