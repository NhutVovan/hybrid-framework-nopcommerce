package javaBasic;

import org.openqa.selenium.WebDriver;

public class Topic_02_Data_Type {

	// Global variable
	static int number;
	static String browserName = "Chrome";

	public static void main(String[] agr) {
		// System.out.println(number);
		// System.out.println(number);

		if (browserName.equalsIgnoreCase("chrome")){
			 System.out.println("chrome");
		}
		else if (browserName.equalsIgnoreCase("firefox")){
			 System.out.println("firefox");
		}
		else{
		}

	}

	WebDriver driver;

}
