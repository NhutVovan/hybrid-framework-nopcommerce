package pageObject.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.nopCommerce.user.UserHomePageUI;


public class UserHomePageObject extends BasePage{
	
	private WebDriver driver;
	
	public UserHomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public UserRegisterPageObject clickToRegisterLink() {
		waitForClickable(driver, UserHomePageUI.REGISTER_LINK);
		clickToElement(driver, UserHomePageUI.REGISTER_LINK);
		//2
		//return new RegisterPageObject(driver);
		//3
		return PageGeneratorManager.getUserRegisterPage(driver);
	}

	public UserLoginPageObject clicktoLoginLink() {
		waitForClickable(driver, UserHomePageUI.LOGIN_LINK);
		clickToElement(driver, UserHomePageUI.LOGIN_LINK);
		//2
		//return  new LoginPageObject(driver);
		//3
		return PageGeneratorManager.getUserLoginPage(driver);
	}

	public boolean isMyAccountLinkDisplayed() {
		waitForClickable(driver, UserHomePageUI.MY_ACCOUNT_LINK);
		return isElementDisplayed(driver, UserHomePageUI.MY_ACCOUNT_LINK);
	}

	public UserCustomerInfoPageObject clickToMyAccountLink() {
		waitForClickable(driver, UserHomePageUI.MY_ACCOUNT_LINK);
		clickToElement(driver, UserHomePageUI.MY_ACCOUNT_LINK);
		return PageGeneratorManager.getUserCustomerInfoPage(driver);
	}


}
