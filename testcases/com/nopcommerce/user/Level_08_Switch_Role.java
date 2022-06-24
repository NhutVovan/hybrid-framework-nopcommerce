package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObject.nopCommerce.user.AddressPageObject;
import pageObject.nopCommerce.user.CustomerInfoPageObject;
import pageObject.nopCommerce.user.HomePageObject;
import pageObject.nopCommerce.user.LoginPageObject;
import pageObject.nopCommerce.user.MyProductReviewsPageObject;
import pageObject.nopCommerce.user.RegisterPageObject;
import pageObject.nopCommerce.user.RewardPointPageObject;

public class Level_08_Switch_Role extends BaseTest {
	
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
		
		email = "Johnterry" + GetRandomNumber() + "@gmail.com";
		password = "123456";
		
		System.out.println("Pre-condition - Step 01: Click to register link");
		registerPage =  homePage.clickToRegisterLink();
		
		System.out.println("Pre-condition - Step 02: Input to required fields");
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(email);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		
		System.out.println("Pre-condition - Step 03: Click to register button");
		registerPage.clickToRegisterButton();
		
		System.out.println("Pre-condition - Step 04: Check registration completed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		
		System.out.println("Pre-condition - Step 05: Click to logout link");
		homePage = registerPage.clickToLogoutLink();

	}

	@Test
	public void Role_01_User() {
		
		loginPage = homePage.clicktoLoginLink();
		
		homePage = loginPage.loginAsUser(email, password);
		
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
	}
	
	@Test
	public void Role_02_Admin() {
		homePage.openPageUrl(driver, GlobalConstants.ADMIN_PAGE_URL);
		
	}
	
	
	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

	

}
