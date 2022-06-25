package pageObject.nopCommerce.admim;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.nopCommerce.admin.AdminLoginPageUI;

public class AdminLoginPageObject extends BasePage {

	private WebDriver driver;

	public AdminLoginPageObject(WebDriver driver) {
		
		this.driver = driver;
	}

	public void inputToUsernameTextbox(String email) {
		sendkeyToElement(driver, AdminLoginPageUI.EMAIL_TEXTBOX, email);
	}
	
	public void inputToPasswordTextbox(String password) {
		sendkeyToElement(driver, AdminLoginPageUI.PASSWORD_TEXTBOX, password);
	}

	public AdminDashboardPageObject clickToLoginButton() {
		waitForClickable(driver, AdminLoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, AdminLoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getAdminDashboardPage(driver);
	}

	public AdminDashboardPageObject loginAsAdmin(String email, String password) {
		inputToUsernameTextbox(email);
		inputToPasswordTextbox(password);
		return clickToLoginButton();
	}
}
