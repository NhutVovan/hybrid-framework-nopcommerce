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
import pageObject.nopCommerce.admim.AdminDashboardPageObject;
import pageObject.nopCommerce.admim.AdminLoginPageObject;
import pageObject.nopCommerce.user.UserAddressPageObject;
import pageObject.nopCommerce.user.UserCustomerInfoPageObject;
import pageObject.nopCommerce.user.UserHomePageObject;
import pageObject.nopCommerce.user.UserLoginPageObject;
import pageObject.nopCommerce.user.UserMyProductReviewsPageObject;
import pageObject.nopCommerce.user.UserRegisterPageObject;
import pageObject.nopCommerce.user.UserRewardPointPageObject;

public class Level_08_Switch_Role extends BaseTest {
	
	private WebDriver driver;
	private UserHomePageObject userHomePage;
	private UserRegisterPageObject userRegisterPage;
	private UserLoginPageObject userLoginPage;
	private UserCustomerInfoPageObject userCustomerInfoPage;
	private UserAddressPageObject userAddressPage;
	private UserMyProductReviewsPageObject userMyProductReviewsPage;
	private UserRewardPointPageObject userRewardPointPage;
	private AdminLoginPageObject adminLoginPage;
	private AdminDashboardPageObject adminDashboardPage;
	
	private String firstName, lastName, userEmail, userPassword, adminEmail, adminPassword;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		
		driver = getBrowserDriver(browserName);
		
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
		firstName = "John";
		lastName = "Terry";
		userEmail = "Johnterry" + GetRandomNumber() + "@gmail.com";
		userPassword = "123456";
		adminEmail = "admin@yourstore.com";
		adminPassword = "admin";
		
		System.out.println("Pre-condition - Step 01: Click to register link");
		userRegisterPage =  userHomePage.clickToRegisterLink();
		
		System.out.println("Pre-condition - Step 02: Input to required fields");
		userRegisterPage.inputToFirstnameTextbox(firstName);
		userRegisterPage.inputToLastnameTextbox(lastName);
		userRegisterPage.inputToEmailTextbox(userEmail);
		userRegisterPage.inputToPasswordTextbox(userPassword);
		userRegisterPage.inputToConfirmPasswordTextbox(userPassword);
		
		System.out.println("Pre-condition - Step 03: Click to register button");
		userRegisterPage.clickToRegisterButton();
		
		System.out.println("Pre-condition - Step 04: Check registration completed");
		Assert.assertEquals(userRegisterPage.getRegisterSuccessMessage(), "Your registration completed");
		
		System.out.println("Pre-condition - Step 05: Click to logout link");
		userHomePage = userRegisterPage.clickToLogoutLink();

	}

	@Test
	public void Role_01_User_To_Admin() {
		//Open user login page
		userLoginPage = userHomePage.clicktoLoginLink();
		
		userHomePage = userLoginPage.loginAsUser(userEmail, userPassword);
		Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());
		userHomePage.clickToLogoutLinkAtUserPage(driver);
		
		//Open admin login page
		userHomePage.openPageUrl(driver, GlobalConstants.ADMIN_PAGE_URL);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);
		
		adminDashboardPage = adminLoginPage.loginAsAdmin(adminEmail, adminPassword);
		Assert.assertTrue(adminDashboardPage.isHeaderDashboardDisplyed());
		adminLoginPage = adminDashboardPage.clickToLogoutLinkAtAdminPage(driver);
	}
	
	@Test
	public void Role_02_Admin_To_User() {
		//Re-open user login page
		adminDashboardPage.openPageUrl(driver, GlobalConstants.USER_PAGE_URL);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
		
		userHomePage.openLoginPage(driver);
		userHomePage = userLoginPage.loginAsUser(userEmail, userPassword);
		Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());
	}
	
	
	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

	

}
