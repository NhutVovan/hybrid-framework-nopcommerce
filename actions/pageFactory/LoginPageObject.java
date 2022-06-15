package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BasePageFactory;

public class LoginPageObject extends BasePageFactory {
	private WebDriver driver;
	
	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Page UI
	@FindBy(xpath = "//input[@id='Email']")
	private WebElement emailTextbox;
	
	@FindBy(xpath = "//input[@id='Password']")
	private WebElement passwordTextbox;
	
	@FindBy(xpath = "//button[text()='Log in']")
	private WebElement loginButton;
	
	@FindBy(xpath = "//span[@id='Email-error']")
	private WebElement emailErrorTextbox;
	
	@FindBy(xpath = "//div[contains(@class,'message-error')]")
	private WebElement unseccessfullErrorMessage;

	//Page Object
	public void clickToLoginButton() {
		waitForClickable(driver, loginButton);
		clickToElement(driver, loginButton);
		
	}

	public String getErrorMessageAtEmailTextbox() {
		waitForElementVisible(driver, emailErrorTextbox);
		return getElementText(driver, emailErrorTextbox);
		
	}

	public String getErrorMessageUnSeccessfull() {
		waitForElementVisible(driver, unseccessfullErrorMessage);
		return getElementText(driver, unseccessfullErrorMessage);
	}


	public void inputToEmailTextbox(String invalidEmail) {
		waitForElementVisible(driver, emailTextbox);
		sendkeyToElement(driver, emailTextbox, invalidEmail);
	}

	public void inputToPasswordTextbox(String invalidEmail) {
		waitForElementVisible(driver, passwordTextbox);
		sendkeyToElement(driver, passwordTextbox, invalidEmail);
		
	}

}
