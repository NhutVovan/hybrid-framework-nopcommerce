package commons;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	//private String projectPath = System.getProperty("user.dir");
	private WebDriver driverBaseTest;
	
	
	protected WebDriver getBrowserDriver(String browserName) {
		
		if(browserName.equalsIgnoreCase("firefox"))
		{
			//System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
			WebDriverManager.firefoxdriver().setup();
			driverBaseTest = new FirefoxDriver();
		}
		else if (browserName.equalsIgnoreCase("chrome"))
		{
			//System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			driverBaseTest = new ChromeDriver();
		}
		else if (browserName.equalsIgnoreCase("edge"))
		{
			//System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
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
		
		driverBaseTest.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driverBaseTest.manage().window().maximize();
		driverBaseTest.get("https://demo.nopcommerce.com");
		return driverBaseTest;
	}
	
	public int GetRandomNumber() {
		Random Rand = new Random();
		return Rand.nextInt(999);

	}

}
