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

public class Level_09_Dynamic_Locator extends BaseTest {
	
	private WebDriver driver;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserCustomerInfoPageObject customerInfoPage;
	private UserAddressPageObject addressPage;
	private UserMyProductReviewsPageObject myProductReviewsPage;
	private UserRewardPointPageObject rewardPointPage;
	
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
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		
		System.out.println("User_01 - Step 05: Click to logout link");
		homePage = registerPage.clickToLogoutLink();
		
		System.out.println("User_01 - Step 06: Click to login link");
		loginPage = homePage.clicktoLoginLink();

		System.out.println("User_01 - Step 07: Input to textbox");
		loginPage.inputToEmailTextbox(email);
		loginPage.inputToPasswordTextbox(password);
		
		System.out.println("User_01 - Step 08: Click to login button");
		loginPage.clickToLoginButton();
		
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
	}
	
	@Test
	public void User_02_Customer_Info() {
		
		System.out.println("User_02 - Step 01: Click to MyAccount link");
		customerInfoPage = homePage.clickToMyAccountLink();
		
		Assert.assertTrue(customerInfoPage.isCustomerInfoPageDisplayed());
	}
	
	@Test
	public void User_03_Switch_Page() {

		//Customer info -> Address
		addressPage = customerInfoPage.openAddressPage(driver);
		
		//Address -> My product reviews
		myProductReviewsPage = addressPage.openMyProductReviewsPage(driver);
		
		//My product reviews -> myProductReviewsPagemyProductReviewsPage
		rewardPointPage = myProductReviewsPage.openRewardPointPage(driver);
		
		//myProductReviewsPage -> Address
		addressPage = rewardPointPage.openAddressPage(driver);
		
		//Address -> Reward Point
		rewardPointPage = addressPage.openRewardPointPage(driver);
		
		//Reward Point -> My product reviews
		myProductReviewsPage = rewardPointPage.openMyProductReviewsPage(driver);
		
		//My product reviews -> Address
		addressPage = rewardPointPage.openAddressPage(driver);
		
		//Address -> Customer info
		customerInfoPage = addressPage.openCustomerInfoPage(driver);
	}
	
	@Test
	public void User_04_Switch_Page_By_Dynamic_Locator_1() {
		
		// Customer info -> Address
		addressPage = (UserAddressPageObject) customerInfoPage.openPagesAtMyAccountByName(driver, "Addresses");

		// Address -> My product reviews
		myProductReviewsPage = (UserMyProductReviewsPageObject) addressPage.openPagesAtMyAccountByName(driver, "My product reviews");

		// My product reviews -> Reward Point
		rewardPointPage = (UserRewardPointPageObject) myProductReviewsPage.openPagesAtMyAccountByName(driver, "Reward points");

		// myProductReviewsPage -> Address
		addressPage = (UserAddressPageObject) rewardPointPage.openPagesAtMyAccountByName(driver, "Addresses");

		// Address -> Reward Point
		rewardPointPage = (UserRewardPointPageObject) addressPage.openPagesAtMyAccountByName(driver, "Reward points");

		// Reward Point -> My product reviews
		myProductReviewsPage = (UserMyProductReviewsPageObject) rewardPointPage.openPagesAtMyAccountByName(driver, "My product reviews");

		// My product reviews -> Address
		addressPage = (UserAddressPageObject) rewardPointPage.openPagesAtMyAccountByName(driver, "Addresses");

		// Address -> Customer info
		customerInfoPage = (UserCustomerInfoPageObject) addressPage.openPagesAtMyAccountByName(driver, "Customer info");
	}
	
	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

	

}
