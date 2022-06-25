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

public class Level_06_Page_Generator_Manager_III extends BaseTest {
	
	private WebDriver driver;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserCustomerInfoPageObject myAccountPage;
	
	private String projectPath = System.getProperty("user.dir");
	private String firstName, lastName, existingEmail, invalidEmail, notFoundEmail, password, wrongPassword;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		
		driver = getBrowserDriver(browserName);
		
		homePage = PageGeneratorManager.getUserHomePage(driver);
		
		firstName = "John";
		lastName = "Terry";
		existingEmail = "Johnterry" + GetRandomNumber() + "@gmail.com";
		invalidEmail = "12546@4585#";
		notFoundEmail = "Johnterry" + GetRandomNumber() + "@hotmail.vn";
		password = "123456";
		wrongPassword = "654321";
		
		System.out.println("Pre-condition - Step 01: Click to register link");
		registerPage =  homePage.clickToRegisterLink();
		
		System.out.println("Pre-condition - Step 02: Input to required fields");
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(existingEmail);
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
	public void Login_01_Empty_Data() {
		
		System.out.println("Login_01 - Step 01: Click to login link");
		loginPage = homePage.clicktoLoginLink();
		
		System.out.println("Login_01 - Step 02: Click to login button");
		loginPage.clickToLoginButton();
		
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Please enter your email");
		
	}
	@Test
	public void Login_02_Invalid_Email() {
		
		System.out.println("Login_02 - Step 01: Click to login link");
		homePage.clicktoLoginLink();
		
		System.out.println("Login_02 - Step 02: Input to textbox");
		loginPage.inputToEmailTextbox(invalidEmail);
		
		System.out.println("Login_02 - Step 03: Click to login button");
		loginPage.clickToLoginButton();
		
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Wrong email");
	}
	@Test
	public void Login_03_Email_Not_Found() {
		
		System.out.println("Login_03 - Step 01: Click to login link");
		homePage.clicktoLoginLink();
		
		System.out.println("Login_03 - Step 02: Input to textbox");
		loginPage.inputToEmailTextbox(notFoundEmail);
		
		System.out.println("Login_03 - Step 03: Click to login button");
		loginPage.clickToLoginButton();
		
		Assert.assertEquals(loginPage.getErrorMessageUnSeccessfull(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
		
	}
	@Test
	public void Login_04_Existing_Email_Empty_Password() {
		
		System.out.println("Login_04 - Step 01: Click to login link");
		homePage.clicktoLoginLink();
		
		System.out.println("Login_04 - Step 02: Input to textbox");
		loginPage.inputToEmailTextbox(existingEmail);
		loginPage.inputToPasswordTextbox("");
		
		System.out.println("Login_04 - Step 03: Click to login button");
		loginPage.clickToLoginButton();
		
		Assert.assertEquals(loginPage.getErrorMessageUnSeccessfull(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
		
	}
	@Test
	public void Login_05_Existing_Email_Incorrect_Password() {
		
		System.out.println("Login_05 - Step 01: Click to login link");
		homePage.clicktoLoginLink();
		
		System.out.println("Login_05 - Step 02: Input to textbox");
		loginPage.inputToEmailTextbox(existingEmail);
		loginPage.inputToPasswordTextbox(wrongPassword);
		
		System.out.println("Login_05 - Step 03: Click to login button");
		loginPage.clickToLoginButton();
		
		Assert.assertEquals(loginPage.getErrorMessageUnSeccessfull(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
		
	}
	@Test
	public void Login_06_Valid_Email_Password() {
		
		System.out.println("Login_06 - Step 01: Click to login link");
		homePage.clicktoLoginLink();
		
		System.out.println("Login_06 - Step 02: Input to textbox");
		loginPage.inputToEmailTextbox(existingEmail);
		loginPage.inputToPasswordTextbox(password);
		
		System.out.println("Login_06 - Step 03: Click to login button");
		homePage = loginPage.clickToLoginButton();
		
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
		
		myAccountPage = homePage.clickToMyAccountLink();	
		myAccountPage.clickToNewsletterCheckbox();
		
	}


	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

	

}
