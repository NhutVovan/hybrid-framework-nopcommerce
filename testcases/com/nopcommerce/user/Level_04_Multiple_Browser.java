package com.nopcommerce.user;

import java.util.Random;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObject.HomePageObject;
import pageObject.RegisterPageObject;

public class Level_04_Multiple_Browser extends BaseTest {
	
	private WebDriver driver;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	

	private String firstName, lastName, email, wrongEmail, password, wrongPassword;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
					
		driver = getBrowserDriver(browserName);
		
		homePage = new HomePageObject(driver);
		registerPage = new RegisterPageObject(driver);
		
		firstName = "John";
		lastName = "Terry";
		email = "Johnterry" + GetRandomNumber() + "@gmail.com";
		wrongEmail = "12546@4585#";
		password = "123456";
		wrongPassword = "123";
	}

	@Test
	public void Register_01_Empty_Data() {
		System.out.println("Register_01 - Step 01: Click to register link");
		homePage.clickToRegisterLink();
		
		System.out.println("Register_01 - Step 02: Click to register button");
		registerPage.clickToRegisterButton();
		
		System.out.println("Register_01 - Step 03: Check textbox message");
		Assert.assertEquals(registerPage.getErrorMessageAtFirstnameTextbox(), "First name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtLastnameTextbox(), "Last name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Email is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "Password is required.");
	}

	@Test
	public void Register_02_Invalid_Email() {
		System.out.println("Register_02 - Step 01: Click to register link");
		homePage.clickToRegisterLink();
		
		System.out.println("Register_02 - Step 02: Input to textbox");
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(wrongEmail);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		
		System.out.println("Register_02 - Step 03: Click to register button");
		registerPage.clickToRegisterButton();
		
		System.out.println("Register_02 - Step 04: Check textbox message");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Wrong email");
	}

	@Test
	public void Register_03_Success() {
		System.out.println("Register_03 - Step 01: Click to register link");
		homePage.clickToRegisterLink();
		
		System.out.println("Register_03 - Step 02: Input to textbox");
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(email);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		
		System.out.println("Register_03 - Step 03: Click to register button");
		registerPage.clickToRegisterButton();

		System.out.println("Register_03 - Step 04: Check textbox message");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		
		registerPage.clickToLogoutLink();
	}

	@Test
	public void Register_04_With_Existing_Email() {
		System.out.println("Register_04 - Step 01: Click to register link");
		homePage.clickToRegisterLink();
		
		System.out.println("Register_04 - Step 02: Input to textbox");
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(email);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		
		System.out.println("Register_04 - Step 03: Click to register button");
		registerPage.clickToRegisterButton();

		System.out.println("Register_04 - Step 04: Check textbox message");
		Assert.assertEquals(registerPage.getErrorExistingEmailMessage(), "The specified email already exists");
	}

	@Test
	public void Register_05_Password_Less_Than_Six_Characters() {
		System.out.println("Register_05 - Step 01: Click to register link");
		homePage.clickToRegisterLink();
		
		System.out.println("Register_05 - Step 02: Input to textbox");
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(email);
		registerPage.inputToPasswordTextbox(wrongPassword);
		registerPage.inputToConfirmPasswordTextbox(wrongPassword);
		
		System.out.println("Register_05 - Step 03: Click to register button");
		registerPage.clickToRegisterButton();

		System.out.println("Register_05 - Step 04: Check textbox message");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password must meet the following rules:\nmust have at least 6 characters");

	}

	@Test
	public void Register_06_Password_Confirmation_Does_Not_Match() {
		System.out.println("Register_06 - Step 01: Click to register link");
		homePage.clickToRegisterLink();
		
		System.out.println("Register_06 - Step 02: Input to textbox");
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(email);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(wrongPassword);

		System.out.println("Register_06 - Step 03: Click to register button");
		registerPage.clickToRegisterButton();

		System.out.println("Register_06 - Step 04: Check textbox message");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "The password and confirmation password do not match.");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int GetRandomNumber() {
		Random Rand = new Random();
		return Rand.nextInt(999);
	}

}
