package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObject.nopCommerce.user.AddressPageObject;
import pageObject.nopCommerce.user.CustomerInfoPageObject;
import pageObject.nopCommerce.user.HomePageObject;
import pageObject.nopCommerce.user.LoginPageObject;
import pageObject.nopCommerce.user.MyProductReviewsPageObject;
import pageObject.nopCommerce.user.RegisterPageObject;
import pageObject.nopCommerce.user.RewardPointPageObject;

public class Level_07_Switch_Page extends BaseTest {
	
	private WebDriver driver;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	private CustomerInfoPageObject customerInfoPage;
	private AddressPageObject addressPage;
	private MyProductReviewsPageObject myProductReviewsPage;
	private RewardPointPageObject rewardPointPage;
	
	private String firstName, lastName, email, password;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		
		driver = getBrowserDriver(browserName);
		
		homePage = PageGeneratorManager.getHomePage(driver);
		
		firstName = "John";
		lastName = "Terry";
		email = "Johnterry" + GetRandomNumber() + "@gmail.com";
		password = "123456";

	}

	@Test
	public void User_01_Register() {
		
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
		
	}
	@Test
	public void User_02_Login() {
		
		System.out.println("User_02 - Step 01: Click to login link");
		loginPage = homePage.clicktoLoginLink();

		System.out.println("User_02 - Step 02: Input to textbox");
		loginPage.inputToEmailTextbox(email);
		loginPage.inputToPasswordTextbox(password);
		
		System.out.println("User_02 - Step 03: Click to login button");
		loginPage.clickToLoginButton();
		
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
	}
	@Test
	public void User_03_Customer_Info() {
		
		System.out.println("User_03 - Step 01: Click to MyAccount link");
		customerInfoPage = homePage.clickToMyAccountLink();
		
		Assert.assertTrue(customerInfoPage.isCustomerInfoPageDisplayed());
	}
	@Test
	public void User_04_Switch_Page() {

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
	
	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

	

}
