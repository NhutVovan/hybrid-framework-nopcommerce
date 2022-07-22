package com.saucelab.sort;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObject.sauceLab.PageGeneratorManager;

import pageObject.sauceLab.LoginPageObject;
import pageObject.sauceLab.ProductPageObject;


public class Level_19_Sort_Asc_Desc extends BaseTest {
	
	private WebDriver driver;
	LoginPageObject loginPage;
	ProductPageObject productPage;
	
	@Parameters({"browser", "appUrl"})
	@BeforeClass
	public void beforeClass(String browserName, String Url) {
		
		driver = getBrowserDriver(browserName, Url);
		loginPage = PageGeneratorManager.getLoginPage(driver);
		
		loginPage.enterToUsernameTextbox("standard_user");
		loginPage.enterToPasswordTextbox("secret_sauce");
		productPage = loginPage.clickToLoginButton();

	}

	@Test
	public void Sort_01_Name() {
		//Ascending
		productPage.selectIemInProductSortDropdown("Name (A to Z)");
		productPage.sleepInSecond(3);
		Assert.assertTrue(productPage.isProductPageSortByAscending());
		
		//Descending
		productPage.selectIemInProductSortDropdown("Name (Z to A)");
		productPage.sleepInSecond(3);
		Assert.assertTrue(productPage.isProductPageSortByDescending());
	}
	
	@Test
	public void Sort_02_Price() {
		// Low to high
		productPage.selectIemInProductSortDropdown("Price (low to high)");
		productPage.sleepInSecond(3);
		Assert.assertTrue(productPage.isProductPageSortByLowToHigh());
		
		// High to low
		productPage.selectIemInProductSortDropdown("Price (high to low)");
		productPage.sleepInSecond(3);
		Assert.assertTrue(productPage.isProductPageSortByHighToLow());
		
	}
	
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

	

}
