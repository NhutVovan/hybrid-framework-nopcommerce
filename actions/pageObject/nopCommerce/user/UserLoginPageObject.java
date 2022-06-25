package pageObject.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.nopCommerce.user.UserHomePageUI;
import pageUIs.nopCommerce.user.UserLoginPageUI;

public class UserLoginPageObject extends BasePage {
	
	private WebDriver driver;

	public UserLoginPageObject(WebDriver driver) {
		
		this.driver = driver;
	}

	public UserHomePageObject clickToLoginButton() {
		waitForClickable(driver, UserLoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, UserLoginPageUI.LOGIN_BUTTON);
		//2
		//return new HomePageObject(driver);
		//3
		return PageGeneratorManager.getUserHomePage(driver);
	}

	public String getErrorMessageAtEmailTextbox() {
		waitForElementVisible(driver, UserLoginPageUI.EMAIL_ERROR_TEXBOX);
		return getElementText(driver, UserLoginPageUI.EMAIL_ERROR_TEXBOX);
		
	}

	public String getErrorMessageUnSeccessfull() {
		waitForElementVisible(driver, UserLoginPageUI.UNSUCCESSFULL_ERROR_MESSAGE);
		return getElementText(driver, UserLoginPageUI.UNSUCCESSFULL_ERROR_MESSAGE);
	}


	public void inputToEmailTextbox(String email) {
		waitForElementVisible(driver, UserLoginPageUI.EMAIL_TEXBOX);
		sendkeyToElement(driver, UserLoginPageUI.EMAIL_TEXBOX, email);
	}

	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, UserLoginPageUI.PASSWORD_TEXBOX);
		sendkeyToElement(driver, UserLoginPageUI.PASSWORD_TEXBOX, password);
		
	}

	public UserHomePageObject loginAsUser(String email, String password) {
		inputToEmailTextbox(email);
		inputToPasswordTextbox(password);
		return clickToLoginButton();
		
	}

	
	

}
