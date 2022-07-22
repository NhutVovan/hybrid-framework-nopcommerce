package commons;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.Assert;
import org.testng.Reporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	private WebDriver driverBaseTest;
	protected final Log log;
	
	protected BaseTest() {
		log = LogFactory.getLog(getClass());
	}
	
	protected WebDriver getBrowserDriver(String browserName) {
		BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
		
		if(browserList == BrowserList.FIREFOX)
		{
			WebDriverManager.firefoxdriver().setup();
			System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, GlobalConstants.PROJECT_PATH + "\\browserLogs\\FirefoxLog.log");
			driverBaseTest = new FirefoxDriver();
		}
		else if(browserList == BrowserList.H_FIREFOX)
		{
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("headless"); 
			options.addArguments("window-size=1920x1080");
			driverBaseTest = new FirefoxDriver(options);
		}
		else if (browserList == BrowserList.CHROME)
		{
			WebDriverManager.chromedriver().setup();
			System.setProperty("webdriver.chrome.args","--disable-logging");
			System.setProperty("webdriver.chrome.silentOutput","true");
			driverBaseTest = new ChromeDriver();
		}
		else if (browserList == BrowserList.H_CHROME)
		{
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");
			options.addArguments("window-size=1920x1080");
			driverBaseTest = new ChromeDriver(options);
		}
		else if (browserList == BrowserList.EDGE)
		{
			WebDriverManager.edgedriver().setup();
			driverBaseTest = new EdgeDriver();
		}
		else if (browserList == BrowserList.OPERA)
		{
			WebDriverManager.operadriver().setup();
			driverBaseTest = new OperaDriver();
		}
		else if (browserList == BrowserList.IE)
		{
			WebDriverManager.iedriver().arch32().setup();
			driverBaseTest = new InternetExplorerDriver();
		}
		else
		{
			throw new RuntimeException("Browser name invalid.");
		}
		
		driverBaseTest.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		driverBaseTest.manage().window().maximize();
		driverBaseTest.get(GlobalConstants.USER_PAGE_URL);
		return driverBaseTest;
	}
	
	protected WebDriver getBrowserDriver(String browserName, String appUrl) {
		
		if(browserName.equalsIgnoreCase("firefox"))
		{
			//System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
			WebDriverManager.firefoxdriver().setup();
			driverBaseTest = new FirefoxDriver();
		}
		else if (browserName.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driverBaseTest = new ChromeDriver();
		}
		else if (browserName.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driverBaseTest = new EdgeDriver();
		}
		else if (browserName.equalsIgnoreCase("opera"))
		{
			WebDriverManager.operadriver().setup();
			driverBaseTest = new OperaDriver();
		}
		else if (browserName.equalsIgnoreCase("ie"))
		{
			WebDriverManager.iedriver().arch32().setup();
			driverBaseTest = new InternetExplorerDriver();
		}
		else
		{
			throw new RuntimeException("Browser name invalid.");
		}
		
		driverBaseTest.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		driverBaseTest.manage().window().maximize();
		driverBaseTest.get(appUrl);
		return driverBaseTest;
	}
	
	protected String getInvironmentUrl(String serverName) {
		String envUrl = null;
		EnvironmentList environment = EnvironmentList.valueOf(serverName.toUpperCase());
		if(environment == EnvironmentList.DEV) {
			envUrl = "https://dev.nopcommerce.com";
		}else if(environment == EnvironmentList.TESTING) {
			envUrl = "https://demo.nopcommerce.com";
		}else if(environment == EnvironmentList.STAGING) {
		envUrl = "https://staging.nopcommerce.com";
		}else if (environment == EnvironmentList.PRODUCTION) {
		envUrl = "https://production.nopcommerce.com";
		}else {
			//
		}
		return envUrl;
	}
	
	public WebDriver getDriverInstance() {
		return this.driverBaseTest;
	}

	public int GetRandomNumber() {
		Random Rand = new Random();
		return Rand.nextInt(999);

	}

	protected boolean verifyTrue(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertTrue(condition);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			log.info(" -------------------------- FAILED -------------------------- ");
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertFalse(condition);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			log.info(" -------------------------- FAILED -------------------------- ");
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			pass = false;
			log.info(" -------------------------- FAILED -------------------------- ");
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected void closeBrowserAndDriver() {
		String cmd = "";
		try {
			String osName = System.getProperty("os.name").toLowerCase();
			log.info("OS name = " + osName);

			String driverInstanceName = driverBaseTest.toString().toLowerCase();
			log.info("Driver instance name = " + driverInstanceName);

			if (driverInstanceName.contains("chrome")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
				} else {
					cmd = "pkill chromedriver";
				}
			} else if (driverInstanceName.contains("internetexplorer")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
				}
			} else if (driverInstanceName.contains("firefox")) {
				if (osName.contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
				} else {
					cmd = "pkill geckodriver";
				}
			} else if (driverInstanceName.contains("edge")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq msedgedriver*\"";
				} else {
					cmd = "pkill msedgedriver";
				}
			} else if (driverInstanceName.contains("opera")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq operadriver*\"";
				} else {
					cmd = "pkill operadriver";
				}
			} else if (driverInstanceName.contains("safari")) {
				if (osName.contains("mac")) {
					cmd = "pkill safaridriver";
				}
			}

			if (driverBaseTest != null) {
				driverBaseTest.manage().deleteAllCookies();
				driverBaseTest.quit();
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		} finally {
			try {
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
