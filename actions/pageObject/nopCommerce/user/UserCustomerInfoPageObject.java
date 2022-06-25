package pageObject.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.UserCustomerInfoPageUI;

public class UserCustomerInfoPageObject extends BasePage {
	
	private WebDriver driver;
	
	public UserCustomerInfoPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToNewsletterCheckbox() {
		// TODO Auto-generated method stub
		
	}

	public boolean isCustomerInfoPageDisplayed() {
		waitForElementVisible(driver, UserCustomerInfoPageUI.CUSTOMER_INFO_TEXT);
		return isElementDisplayed(driver, UserCustomerInfoPageUI.CUSTOMER_INFO_TEXT);
	}

	

}
