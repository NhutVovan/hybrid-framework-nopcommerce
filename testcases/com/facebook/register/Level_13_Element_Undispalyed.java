package com.facebook.register;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObject.facebook.LoginPageObject;
import pageObject.facebook.PageGeneratorManager;



public class Level_13_Element_Undispalyed extends BaseTest {
	private WebDriver driver;
	private LoginPageObject loginPage;

	@Parameters( {"browser", "url"} )
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		loginPage = PageGeneratorManager.getLoginPage(driver);
	}

	@Test
	public void TC_01_Element_Displayed() {
		loginPage.clickToCreateNewAccountButton();
		
		Assert.assertTrue(loginPage.isEmailTextBoxDisplayed());
		
	}
	
	@Test
	public void TC_02_Element_Unisplayed_In_DOM() {
		loginPage.enterToEmailTextbox("automationtest@gmail.com");
		loginPage.sleepInSecond(2);
		Assert.assertTrue(loginPage.isConfirmEmailTextBoxDisplayed());
		
		loginPage.enterToEmailTextbox("");
		loginPage.sleepInSecond(2);
		Assert.assertFalse(loginPage.isConfirmEmailTextBoxDisplayed());
		
	}
	
	@Test
	public void TC_03_Element_Unisplayed_Not_In_DOM() {
		loginPage.clickCLoseIconAtRegisterForm();
		loginPage.sleepInSecond(2);
		
		Assert.assertTrue(loginPage.isConfirmEmailTextBoxUndisplayed());
	}
	
	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

	

}
