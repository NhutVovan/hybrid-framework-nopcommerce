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

public class Level_01_Register_DRY {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	String EmailAddress, StoredEmailAddress;
	
 @BeforeClass
 public void beforeClass() {
	 
	 System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		EmailAddress = "Johnterry" + GetRandomNumber() + "@gmail.com";
		StoredEmailAddress = EmailAddress;
		
 }
	 
 @BeforeMethod
	public void beforeMEhod() {
	 driver.get("https://demo.nopcommerce.com/register");
 }
	 
	
  @Test
  public void TC_01_Register_Empty_Data() {
	  driver.findElement(By.cssSelector("input#FirstName")).clear();
	  driver.findElement(By.cssSelector("input#LastName")).clear();
	  driver.findElement(By.cssSelector("input#Email")).clear();
	  driver.findElement(By.cssSelector("input#Password")).clear();
	  driver.findElement(By.cssSelector("input#Password")).clear();
	  
	  //Click register button
	  driver.findElement(By.cssSelector("button#register-button")).click();
	  
	  //Verify
	  Assert.assertEquals(driver.findElement(By.cssSelector("span#FirstName-error")).getText(), "First name is required.");
	  Assert.assertEquals(driver.findElement(By.cssSelector("span#LastName-error")).getText(), "Last name is required.");
	  Assert.assertEquals(driver.findElement(By.cssSelector("span#Email-error")).getText(), "Email is required.");
	  Assert.assertEquals(driver.findElement(By.cssSelector("span#Password-error")).getText(), "Password is required.");
	  Assert.assertEquals(driver.findElement(By.cssSelector("span#ConfirmPassword-error")).getText(), "Password is required.");
  }
  
	
  @Test
  public void TC_02_Register_Invalid_Email() {
	  driver.findElement(By.cssSelector("input#FirstName")).sendKeys("John");
	  driver.findElement(By.cssSelector("input#LastName")).sendKeys("Terry");
	  //Fill invalid email
	  driver.findElement(By.cssSelector("input#Email")).sendKeys("12546@4585#");
	  driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
	  driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
	  
	  //Click register button
	  driver.findElement(By.cssSelector("button#register-button")).click();
	  
	  //Verify
	  Assert.assertEquals(driver.findElement(By.cssSelector("span#Email-error")).getText(), "Wrong email");
  }
  
  @Test
  public void TC_03_Register_Success() throws InterruptedException {
	  driver.findElement(By.cssSelector("input#FirstName")).sendKeys("John");
	  driver.findElement(By.cssSelector("input#LastName")).sendKeys("Terry");
	
	  driver.findElement(By.cssSelector("input#Email")).sendKeys(EmailAddress);
	  driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
	  driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123456");
	  
	  //Click register button
	  driver.findElement(By.cssSelector("button#register-button")).click();
	  
	  //Verify
	  Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
	  
	  //Logout
	  driver.findElement(By.cssSelector("a.ico-logout")).click();
	  
 }

  @Test
  public void TC_04_Register_With_Existing_Email() {
	  driver.findElement(By.cssSelector("input#FirstName")).sendKeys("John");
	  driver.findElement(By.cssSelector("input#LastName")).sendKeys("Terry");
	
	  driver.findElement(By.cssSelector("input#Email")).sendKeys(StoredEmailAddress);
	  driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
	  driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123456");
	  
	  //Click register button
	  driver.findElement(By.cssSelector("button#register-button")).click();
	  
	  //Verify
	  Assert.assertEquals(driver.findElement(By.cssSelector("div.message-error li")).getText(), "The specified email already exists");
  }
  
  @Test
  public void TC_05_Register_Password_Less_Than_Six_Characters() {
	  driver.findElement(By.cssSelector("input#FirstName")).sendKeys("John");
	  driver.findElement(By.cssSelector("input#LastName")).sendKeys("Terry");
	
	  driver.findElement(By.cssSelector("input#Email")).sendKeys(StoredEmailAddress);
	  driver.findElement(By.cssSelector("input#Password")).sendKeys("123");
	  driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123");
	  
	  //Click register button
	  driver.findElement(By.cssSelector("button#register-button")).click();
	  
	//Verify
	  Assert.assertEquals(driver.findElement(By.cssSelector("span#Password-error")).getText(), "Password must meet the following rules:\nmust have at least 6 characters");
	  
  }
  
  @Test
  public void TC_06_Register_Password_Confirmation_Does_Not_Match() {
	  driver.findElement(By.cssSelector("input#FirstName")).sendKeys("John");
	  driver.findElement(By.cssSelector("input#LastName")).sendKeys("Terry");
	
	  driver.findElement(By.cssSelector("input#Email")).sendKeys(StoredEmailAddress);
	  driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
	  driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123");
	  
	  //Click register button
	  driver.findElement(By.cssSelector("button#register-button")).click();
	  
	//Verify
	  Assert.assertEquals(driver.findElement(By.cssSelector("span#ConfirmPassword-error")).getText(), "The password and confirmation password do not match.");
	  
  }

  @AfterClass
  public void afterClass() {
  }
  
	public int GetRandomNumber() {
		Random Rand = new Random();
		return Rand.nextInt(999);
		
	}

}
