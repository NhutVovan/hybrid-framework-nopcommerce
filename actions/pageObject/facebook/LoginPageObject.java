package pageObject.facebook;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.facebook.LoginPageUI;

public class LoginPageObject extends BasePage {
	WebDriver driver;
	
	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToCreateNewAccountButton() {
		waitForElementClickable(driver, LoginPageUI.CREATE_NEW_ACCOUNT_BUTTON);
		clickToElement(driver, LoginPageUI.CREATE_NEW_ACCOUNT_BUTTON);
	}

	public boolean isEmailTextBoxDisplayed() {
		waitForElementVisible(driver, LoginPageUI.EMAIL_ADDRESS_TEXTBOX);
		return isElementDisplayed(driver, LoginPageUI.EMAIL_ADDRESS_TEXTBOX);
	}

	public void enterToEmailTextbox(String textToSend) {
		waitForElementVisible(driver, LoginPageUI.EMAIL_ADDRESS_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.EMAIL_ADDRESS_TEXTBOX, textToSend);
	}
	
	public boolean isConfirmEmailTextBoxDisplayed() {
		return isElementDisplayed(driver, LoginPageUI.CONFIRMATION_EMAIL_ADDRESS_TEXTBOX);
	}

	public void clickCLoseIconAtRegisterForm() {
		waitForElementVisible(driver, LoginPageUI.CLOSE_ICON);
		clickToElement(driver, LoginPageUI.CLOSE_ICON);
	}

	public boolean isConfirmEmailTextBoxUndisplayed() {
		waitForElementUndisplyed(driver, LoginPageUI.CONFIRMATION_EMAIL_ADDRESS_TEXTBOX);
		return isElementUndisplayed(driver, LoginPageUI.CONFIRMATION_EMAIL_ADDRESS_TEXTBOX);
	}

}
