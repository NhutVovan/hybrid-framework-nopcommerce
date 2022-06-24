package pageObject.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.HomePageUI;
import pageUIs.LoginPageUI;

public class LoginPageObject extends BasePage {
	
	private WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		
		this.driver = driver;
	}

	public HomePageObject clickToLoginButton() {
		waitForClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		//2
		//return new HomePageObject(driver);
		//3
		return PageGeneratorManager.getHomePage(driver);
	}

	public String getErrorMessageAtEmailTextbox() {
		waitForElementVisible(driver, LoginPageUI.EMAIL_ERROR_TEXBOX);
		return getElementText(driver, LoginPageUI.EMAIL_ERROR_TEXBOX);
		
	}

	public String getErrorMessageUnSeccessfull() {
		waitForElementVisible(driver, LoginPageUI.UNSUCCESSFULL_ERROR_MESSAGE);
		return getElementText(driver, LoginPageUI.UNSUCCESSFULL_ERROR_MESSAGE);
	}


	public void inputToEmailTextbox(String email) {
		waitForElementVisible(driver, LoginPageUI.EMAIL_TEXBOX);
		sendkeyToElement(driver, LoginPageUI.EMAIL_TEXBOX, email);
	}

	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXBOX);
		sendkeyToElement(driver, LoginPageUI.PASSWORD_TEXBOX, password);
		
	}

	public HomePageObject loginAsUser(String email, String password) {
		inputToEmailTextbox(email);
		inputToPasswordTextbox(password);
		return clickToLoginButton();
		
	}

	
	

}
