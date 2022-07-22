package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.common.Common_01_Register;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObject.nopCommerce.user.UserAddressPageObject;
import pageObject.nopCommerce.user.UserCustomerInfoPageObject;
import pageObject.nopCommerce.user.UserHomePageObject;
import pageObject.nopCommerce.user.UserLoginPageObject;
import pageObject.nopCommerce.user.UserMyProductReviewsPageObject;
import pageObject.nopCommerce.user.UserRegisterPageObject;
import pageObject.nopCommerce.user.UserRewardPointPageObject;

public class Level_16_Share_Data extends BaseTest {
	
	private WebDriver driver;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserCustomerInfoPageObject customerInfoPage;
	
	private String email, password;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getUserHomePage(driver);
		
		email = Common_01_Register.email;
		password = Common_01_Register.password;
		
		log.info("Login - Step 06: Click to login link");
		loginPage = homePage.clicktoLoginLink();

		log.info("Login - Step 07: Input Email with: " + email);
		loginPage.inputToEmailTextbox(email);
		
		log.info("Login - Step 07: Input Password with: " + password);
		loginPage.inputToPasswordTextbox(password);
		
		log.info("Login - Step 08: Click to login button");
		loginPage.clickToLoginButton();
	}

	
	@Test
	public void Search_01_Empty_Data() {
		
		
	}
	
	@Test
	public void Search_02_Relative_Product_Name() {
		
		
	}
	
	@Test
	public void Search_03_Obsolute_Product_Name() {
		
		
	}
	
	@Test
	public void Search_04_Parent_Category() {
		
		
	}
	
	@Test
	public void Search_05_Incorrect_Manufacture() {
		
		
	}
	
	@Test
	public void Search_06_Correct_Manufacture() {
		

	}
	
	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

}