package com.jquery;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObject.jQuery.dataTable.HomePageObject;
import pageObject.jQuery.dataTable.PageGeneratorManager;


public class Level_10_DatTable_DataGrid extends BaseTest {
	private WebDriver driver;	
	HomePageObject homePage;
	List<String> actualAllCountryValues;
	List<String> expectedAllCountryValues;
	
	@Parameters( {"browser", "url"} )
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		homePage = PageGeneratorManager.getHomePage(driver);
	}

	
	public void Table_01_Paging() {
		homePage.openPagingByPageNumber("10");
		homePage.sleepInSecond(2);
		Assert.assertTrue(homePage.isPageNumberActive("10"));
		
		homePage.openPagingByPageNumber("1");
		homePage.sleepInSecond(2);
		Assert.assertTrue(homePage.isPageNumberActive("1"));
		
		homePage.openPagingByPageNumber("5");
		homePage.sleepInSecond(2);
		Assert.assertTrue(homePage.isPageNumberActive("5"));
		
		homePage.openPagingByPageNumber("18");
		homePage.sleepInSecond(2);
		Assert.assertTrue(homePage.isPageNumberActive("18"));
		
	}
	
	
	public void Table_02_Enter_To_Header() {
		homePage.refreshCurrentPage(driver);
		
		homePage.enterToHeaderTexboxByLabel("Females", "11727960");
		homePage.enterToHeaderTexboxByLabel("Country", "India");
		homePage.enterToHeaderTexboxByLabel("Males", "13060130");
		homePage.enterToHeaderTexboxByLabel("Total", "24788090");
		homePage.sleepInSecond(3);
		
		homePage.enterToHeaderTexboxByLabel("Females", "276880");
		homePage.enterToHeaderTexboxByLabel("Country", "Angola");
		homePage.enterToHeaderTexboxByLabel("Males", "276472");
		homePage.enterToHeaderTexboxByLabel("Total", "553353");
		homePage.sleepInSecond(3);
	}
	
	
	public void Table_03_Enter_To_Header() {
		//Doc du lieu tu file country.txt
		//Luu vao List<Sting> expectedAllCountryValues
		actualAllCountryValues = homePage.getValueEachRowAtAllPage();
		
		//So sanh actualAllCountryValues = expectedAllCountryValues
	}
	
	@Test
	public void Table_03_Enter_To_Textbox_At_Any_Row() {
		homePage.clickToLoadDataButton();
		homePage.sleepInSecond(2);
		
		homePage.enterToTextboxByColumnNameAtRowNumber("Album", "2", "My Album");
		
		homePage.enterToTextboxByColumnNameAtRowNumber("Artist", "4", "New Artist");
		
		homePage.enterToTextboxByColumnNameAtRowNumber("Year", "3", "New Year");
		
		homePage.enterToTextboxByColumnNameAtRowNumber("Price", "1", "1234");
		
		homePage.selectDropdownByColumnNameAtRowNumber("Origin", "5", "Japan");
		homePage.sleepInSecond(1);
		
		homePage.selectDropdownByColumnNameAtRowNumber("Origin", "2", "Hong Kong");
		homePage.sleepInSecond(1);
		
		homePage.selectDropdownByColumnNameAtRowNumber("Origin", "1", "US");
		homePage.sleepInSecond(1);
		
		homePage.checkToCheckboxByColumnNameAtRowNumber("With Poster?", "3");
		homePage.checkToCheckboxByColumnNameAtRowNumber("With Poster?", "5");
		
		homePage.unCheckToCheckboxByColumnNameAtRowNumber("With Poster?", "1");
		homePage.unCheckToCheckboxByColumnNameAtRowNumber("With Poster?", "2");
		homePage.unCheckToCheckboxByColumnNameAtRowNumber("With Poster?", "4");
		
		homePage.clickToIconByRowNumber("1", "Remove Current Row");
		homePage.sleepInSecond(1);
		homePage.clickToIconByRowNumber("1", "Insert Row Above");
		homePage.sleepInSecond(1);
		homePage.clickToIconByRowNumber("3", "Move Up");
		homePage.sleepInSecond(1);
		
		homePage.clickToIconByRowNumber("5", "Remove Current Row");
		homePage.sleepInSecond(1);
		homePage.clickToIconByRowNumber("4", "Remove Current Row");
		homePage.sleepInSecond(1);
		homePage.clickToIconByRowNumber("3", "Remove Current Row");
		homePage.sleepInSecond(1);
		homePage.clickToIconByRowNumber("2", "Remove Current Row");
		homePage.sleepInSecond(1);
		homePage.clickToIconByRowNumber("1", "Remove Current Row");
	}
	
	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

	

}
