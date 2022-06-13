package pageObject;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.HomePageUI;
import pageUIs.LoginPageUI;

public class LoginPageObject extends BasePage {
	
	private WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		
		this.driver = driver;
	}

	public void clickToLoginButton() {
		waitForClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		
	}

	public String getErrorMessageAtEmailTextbox() {
		waitForElementVisible(driver, LoginPageUI.EMAIL_ERROR_TEXBOX);
		return getElementText(driver, LoginPageUI.EMAIL_ERROR_TEXBOX);
		
	}

	public String getErrorMessageUnSeccessfull() {
		waitForAllElementVisible(driver, LoginPageUI.UNSUCCESSFULL_ERROR_MESSAGE);
		return getElementText(driver, LoginPageUI.UNSUCCESSFULL_ERROR_MESSAGE);
	}


	public void inputToEmailTextbox(String invalidEmail) {
		waitForAllElementVisible(driver, LoginPageUI.EMAIL_TEXBOX);
		sendkeyToElement(driver, LoginPageUI.EMAIL_TEXBOX, invalidEmail);
	}

	public void inputToPasswordTextbox(String invalidEmail) {
		waitForAllElementVisible(driver, LoginPageUI.PASSWORD_TEXBOX);
		sendkeyToElement(driver, LoginPageUI.PASSWORD_TEXBOX, invalidEmail);
		
	}

	
	

}
