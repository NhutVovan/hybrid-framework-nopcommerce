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
import pageObject.nopCommerce.user.UserRegisterPageObject;

public class Level_03_Page_Object_01_Register {
	
	private WebDriver driver;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	
	private String projectPath = System.getProperty("user.dir");
	private String firstName, lastName, email, storedEmail, wrongEmail, password, wrongPassword;
	
	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com");
		
		homePage = new UserHomePageObject(driver);
		registerPage = new UserRegisterPageObject(driver);
		
		firstName = "John";
		lastName = "Terry";
		email = "Johnterry" + GetRandomNumber() + "@gmail.com";
		wrongEmail = "12546@4585#";
		password = "123456";
		wrongPassword = "123";
	}

	@Test
	public void Register_01_Empty_Data() {
		homePage.clickToRegisterLink();

		registerPage.clickToRegisterButton();
		
		Assert.assertEquals(registerPage.getErrorMessageAtFirstnameTextbox(), "First name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtLastnameTextbox(), "Last name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Email is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "Password is required.");
		
	}

	@Test
	public void Register_02_Invalid_Email() {
		homePage.clickToRegisterLink();
		
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(wrongEmail);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);

		registerPage.clickToRegisterButton();
		
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Wrong email");
	}

	@Test
	public void Register_03_Success() {

		homePage.clickToRegisterLink();

		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(email);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		
		storedEmail = email;

		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		
		registerPage.clickToLogoutLink();
	}

	@Test
	public void Register_04_With_Existing_Email() {
		homePage.clickToRegisterLink();
		
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(storedEmail);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);

		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getErrorExistingEmailMessage(), "The specified email already exists");
	}

	@Test
	public void Register_05_Password_Less_Than_Six_Characters() {
		homePage.clickToRegisterLink();
		
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(email);
		registerPage.inputToPasswordTextbox(wrongPassword);
		registerPage.inputToConfirmPasswordTextbox(wrongPassword);

		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password must meet the following rules:\nmust have at least 6 characters");

	}

	@Test
	public void Register_06_Password_Confirmation_Does_Not_Match() {
homePage.clickToRegisterLink();
		
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(email);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(wrongPassword);

		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "The password and confirmation password do not match.");

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
