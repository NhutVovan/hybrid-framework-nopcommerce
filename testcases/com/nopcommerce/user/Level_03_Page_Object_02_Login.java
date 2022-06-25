package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObject.nopCommerce.user.UserHomePageObject;
import pageObject.nopCommerce.user.UserLoginPageObject;
import pageObject.nopCommerce.user.UserRegisterPageObject;

public class Level_03_Page_Object_02_Login {
	
	private WebDriver driver;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	
	private String projectPath = System.getProperty("user.dir");
	private String firstName, lastName, existingEmail, invalidEmail, notFoundEmail, password, wrongPassword;
	
	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com");
		//Khoi tao trang homepage
		homePage = new UserHomePageObject(driver);
		
	
		firstName = "John";
		lastName = "Terry";
		existingEmail = "Johnterry" + GetRandomNumber() + "@gmail.com";
		invalidEmail = "12546@4585#";
		notFoundEmail = "Johnterry" + GetRandomNumber() + "@hotmail.vn";
		password = "123456";
		wrongPassword = "654321";
		
		System.out.println("Pre-condition - Step 01: Click to register link");
		homePage.clickToRegisterLink();
		//Khoi tao trang register
		registerPage = new UserRegisterPageObject(driver);
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
		registerPage.clickToLogoutLink();
		//Khoi tao trang homepage
		homePage = new UserHomePageObject(driver);
	}

	@Test
	public void Login_01_Empty_Data() {
		System.out.println("Login_01 - Step 01: Click to login link");
		homePage.clicktoLoginLink();
		//Khoi tao trang login
		loginPage = new UserLoginPageObject(driver);
		
		System.out.println("Login_01 - Step 02: Click to login button");
		loginPage.clickToLoginButton();
		
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Please enter your email");
		
	}
	@Test
	public void Login_02_Invalid_Email() {
		System.out.println("Login_02 - Step 01: Click to login link");
		homePage.clicktoLoginLink();
		//Khoi tao trang login
		loginPage = new UserLoginPageObject(driver);
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
		//Khoi tao trang login
		loginPage = new UserLoginPageObject(driver);
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
		//Khoi tao trang login
		loginPage = new UserLoginPageObject(driver);
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
		//Khoi tao trang login
		loginPage = new UserLoginPageObject(driver);
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
		//Khoi tao trang login
		loginPage = new UserLoginPageObject(driver);
		System.out.println("Login_06 - Step 02: Input to textbox");
		loginPage.inputToEmailTextbox(existingEmail);
		loginPage.inputToPasswordTextbox(password);
		System.out.println("Login_06 - Step 03: Click to login button");
		loginPage.clickToLoginButton();
		
		//Login thanh cong se ve Homepage -> Khoi tao trang homepage
		homePage = new UserHomePageObject(driver);
		
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
	
	}

	

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

	public int GetRandomNumber() {
		Random Rand = new Random();
		return Rand.nextInt(999);

	}

}
