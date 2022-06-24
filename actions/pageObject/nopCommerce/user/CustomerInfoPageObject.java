package pageObject.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.CustomerInfoPageUI;

public class CustomerInfoPageObject extends BasePage {
	
	private WebDriver driver;
	
	public CustomerInfoPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToNewsletterCheckbox() {
		// TODO Auto-generated method stub
		
	}

	public boolean isCustomerInfoPageDisplayed() {
		waitForElementVisible(driver, CustomerInfoPageUI.CUSTOMER_INFO_TEXT);
		return isElementDisplayed(driver, CustomerInfoPageUI.CUSTOMER_INFO_TEXT);
	}

	

}
