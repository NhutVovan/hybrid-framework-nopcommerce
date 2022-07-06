package com.jquery;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObject.jQuery.uploadFile.HomePageObject;
import pageObject.jQuery.uploadFile.PageGeneratorManager;


public class Level_11_Upload_Files extends BaseTest {
	private WebDriver driver;
	private HomePageObject homePage;
	String CsharpFileName = "Csharp.jpg";
	String JavaFileName = "Java.jpg";
	String PythonFileName = "Python.jpg";
	String RubyFileName = "Ruby.jpg";
	String[] multipleFileName = {CsharpFileName, JavaFileName, PythonFileName, RubyFileName};

	@Parameters( {"browser", "url"} )
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		homePage = PageGeneratorManager.getHomePage(driver);
	}

	@Test
	public void Upload_01_One_File() {
		homePage.uploadMultipleFiles(driver, CsharpFileName);
		Assert.assertTrue(homePage.isFileLoadedByName(CsharpFileName));
		
		homePage.clickToStartButton();
		Assert.assertTrue(homePage.isFileLinkUpoadedByName(CsharpFileName));
		Assert.assertTrue(homePage.isFileImageUpoadedByName(CsharpFileName));
		
	}
	
	@Test
	public void Upload_02_Multiple_File() {
		homePage.refreshCurrentPage(driver);
		
		homePage.uploadMultipleFiles(driver, multipleFileName);
		Assert.assertTrue(homePage.isFileLoadedByName(CsharpFileName));
		Assert.assertTrue(homePage.isFileLoadedByName(JavaFileName));
		Assert.assertTrue(homePage.isFileLoadedByName(PythonFileName));
		Assert.assertTrue(homePage.isFileLoadedByName(RubyFileName));
		
		homePage.clickToStartButton();
		Assert.assertTrue(homePage.isFileLinkUpoadedByName(CsharpFileName));
		Assert.assertTrue(homePage.isFileImageUpoadedByName(CsharpFileName));
		
		Assert.assertTrue(homePage.isFileLinkUpoadedByName(JavaFileName));
		Assert.assertTrue(homePage.isFileImageUpoadedByName(JavaFileName));
		
		Assert.assertTrue(homePage.isFileLinkUpoadedByName(PythonFileName));
		Assert.assertTrue(homePage.isFileImageUpoadedByName(PythonFileName));
		
		Assert.assertTrue(homePage.isFileLinkUpoadedByName(RubyFileName));
		Assert.assertTrue(homePage.isFileImageUpoadedByName(RubyFileName));
	}
	
	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

	

}
