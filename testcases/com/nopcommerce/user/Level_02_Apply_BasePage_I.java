package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commons.BasePage;

public class Level_02_Apply_BasePage_I {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	BasePage basePage;
	String emailAddress, storedEmailAddress;
	
 @BeforeClass
 public void beforeClass() {
	 
	 System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		basePage = new BasePage();
		emailAddress = "Johnterry" + GetRandomNumber() + "@gmail.com";
		storedEmailAddress = emailAddress;
 }
	 
 @BeforeMethod
	public void beforeMEhod() {
	 driver.get("https://demo.nopcommerce.com");
 }
	 
	
  @Test
  public void TC_01_Register_Empty_Data() {
	  basePage.waitForElementVisible(driver, "//a[@class='ico-register']");
	  basePage.clickToElement(driver, "//a[@class='ico-register']");
	  
	  basePage.waitForElementVisible(driver, "//button[@id='register-button']");
	  basePage.clickToElement(driver, "//button[@id='register-button']");
	  
	  
	  //Verify
	  Assert.assertEquals(basePage.getElementText(driver, "//span[@id='FirstName-error']"), "First name is required.");
	  Assert.assertEquals(basePage.getElementText(driver, "//span[@id='LastName-error']"), "Last name is required.");
	  Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Email-error']"), "Email is required.");
	  Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Password-error']"), "Password is required.");
	  Assert.assertEquals(basePage.getElementText(driver, "//span[@id='ConfirmPassword-error']"), "Password is required.");
  }
  
	
  @Test
  public void TC_02_Register_Invalid_Email() {
	  basePage.waitForElementVisible(driver, "//a[@class='ico-register']");
	  basePage.clickToElement(driver, "//a[@class='ico-register']");
	  
	  basePage.sendkeyToElement(driver, "//input[@id='FirstName']", "John");
	  basePage.sendkeyToElement(driver, "//input[@id='LastName']", "Terry");
	  
	  //Fill invalid email
	  basePage.sendkeyToElement(driver, "//input[@id='Email']", "12546@4585#");
	  basePage.sendkeyToElement(driver, "//input[@id='Password']", "123456");
	  basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
	
	  
	  //Click register button
	  basePage.waitForElementVisible(driver, "//button[@id='register-button']");
	  basePage.clickToElement(driver, "//button[@id='register-button']");
	  
	  //Verify
	  Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Email-error']"), "Wrong email");
  }
  
  @Test
  public void TC_03_Register_Success() {
	  basePage.waitForElementVisible(driver, "//a[@class='ico-register']");
	  basePage.clickToElement(driver, "//a[@class='ico-register']");
	  
	  basePage.sendkeyToElement(driver, "//input[@id='FirstName']", "John");
	  basePage.sendkeyToElement(driver, "//input[@id='LastName']", "Terry");
	  
	  //Fill invalid email
	  basePage.sendkeyToElement(driver, "//input[@id='Email']", emailAddress);
	  basePage.sendkeyToElement(driver, "//input[@id='Password']", "123456");
	  basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
	
	  
	  //Click register button
	  basePage.waitForElementVisible(driver, "//button[@id='register-button']");
	  basePage.clickToElement(driver, "//button[@id='register-button']");
	  
	  //Verify
	  Assert.assertEquals(basePage.getElementText(driver, "//div[@class='result']"), "Your registration completed");
	  
	  //Logout
	  basePage.clickToElement(driver, "//a[@class='ico-logout']");
	 	  
 }

  @Test
  public void TC_04_Register_With_Existing_Email() {
	  basePage.waitForElementVisible(driver, "//a[@class='ico-register']");
	  basePage.clickToElement(driver, "//a[@class='ico-register']");
	  
	  basePage.sendkeyToElement(driver, "//input[@id='FirstName']", "John");
	  basePage.sendkeyToElement(driver, "//input[@id='LastName']", "Terry");
	  
	  //Fill invalid email
	  basePage.sendkeyToElement(driver, "//input[@id='Email']", storedEmailAddress);
	  basePage.sendkeyToElement(driver, "//input[@id='Password']", "123456");
	  basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
	
	  
	  //Click register button
	  basePage.waitForElementVisible(driver, "//button[@id='register-button']");
	  basePage.clickToElement(driver, "//button[@id='register-button']");
	  
	  //Verify
	  Assert.assertEquals(basePage.getElementText(driver, "//div[contains(@class,'message-error')]//li"), "The specified email already exists");
  }
  
  @Test
  public void TC_05_Register_Password_Less_Than_Six_Characters() {
	  basePage.waitForElementVisible(driver, "//a[@class='ico-register']");
	  basePage.clickToElement(driver, "//a[@class='ico-register']");
	  
	  basePage.sendkeyToElement(driver, "//input[@id='FirstName']", "John");
	  basePage.sendkeyToElement(driver, "//input[@id='LastName']", "Terry");
	  
	  //Fill invalid email
	  basePage.sendkeyToElement(driver, "//input[@id='Email']", storedEmailAddress);
	  basePage.sendkeyToElement(driver, "//input[@id='Password']", "123");
	  basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123");
	
	  
	  //Click register button
	  basePage.waitForElementVisible(driver, "//button[@id='register-button']");
	  basePage.clickToElement(driver, "//button[@id='register-button']");
	  
	  //Verify
	  Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Password-error']"), "Password must meet the following rules:\nmust have at least 6 characters");
	  
  }
  
  @Test
  public void TC_06_Register_Password_Confirmation_Does_Not_Match() {
	  basePage.waitForElementVisible(driver, "//a[@class='ico-register']");
	  basePage.clickToElement(driver, "//a[@class='ico-register']");
	  
	  basePage.sendkeyToElement(driver, "//input[@id='FirstName']", "John");
	  basePage.sendkeyToElement(driver, "//input[@id='LastName']", "Terry");
	  
	  //Fill invalid email
	  basePage.sendkeyToElement(driver, "//input[@id='Email']", storedEmailAddress);
	  basePage.sendkeyToElement(driver, "//input[@id='Password']", "123456");
	  basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123");
	
	  
	  //Click register button
	  basePage.waitForElementVisible(driver, "//button[@id='register-button']");
	  basePage.clickToElement(driver, "//button[@id='register-button']");
	  
	//Verify
	  Assert.assertEquals(basePage.getElementText(driver, "//span[@id='ConfirmPassword-error']"), "The password and confirmation password do not match.");
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
