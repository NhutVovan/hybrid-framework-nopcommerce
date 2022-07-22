package pageObject.sauceLab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.sauceLab.ProductPageUI;

public class ProductPageObject extends BasePage {
private WebDriver driver;
	
	public ProductPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void selectIemInProductSortDropdown(String typeOfSort) {
		waitForElementClickable(driver, ProductPageUI.PRODUCT_CONTAINER_DROPDOWN);
		selectItemInDefaultDropdown(driver, ProductPageUI.PRODUCT_CONTAINER_DROPDOWN, typeOfSort);
	}

	public boolean isProductPageSortByAscending() {
		//Khai bao ArrayList de chua tat ca cac product name tren UI
		ArrayList<String> productUIList = new ArrayList<>();
		
		//Lay ra tat ca cac product name text
		List<WebElement> productText = getListWebElement(driver, ProductPageUI.PRODUCT_NAME_TEXT);
		
		//Get text add vao ArrayList
		for (WebElement item : productText) {
			productUIList.add(item.getText());
		}
		
		//Tao ra 1 ArrayList moi de sort du lieu
		ArrayList<String> productSortList = new ArrayList<>();
		for (String UIitem : productUIList) {
			productSortList.add(UIitem);
		}
		
		//Sort du lieu cua ArrayList moi
		Collections.sort(productSortList);
		
		//So sanh 2 ArrayList bang nhau
		return productSortList.equals(productUIList);
	}

	public boolean isProductPageSortByDescending() {
		//Khai bao ArrayList de chua tat ca cac product name tren UI
				ArrayList<String> productUIList = new ArrayList<>();
				
				//Lay ra tat ca cac product name text
				List<WebElement> productText = getListWebElement(driver, ProductPageUI.PRODUCT_NAME_TEXT);
				
				//Get text add vao ArrayList
				for (WebElement item : productText) {
					productUIList.add(item.getText());
				}
				
				//Tao ra 1 ArrayList moi de sort du lieu
				ArrayList<String> productSortList = new ArrayList<>();
				for (String UIitem : productUIList) {
					productSortList.add(UIitem);
				}
				
				//Sort du lieu cua ArrayList moi
				Collections.sort(productSortList);
				
				//Reverse du lieu theo Descending
				Collections.reverse(productSortList);
				
				//So sanh 2 ArrayList bang nhau
				return productSortList.equals(productUIList);
	}

	public boolean isProductPageSortByLowToHigh() {
		// Khai bao ArrayList de chua tat ca cac product price tren UI
		ArrayList<Float> productUIList = new ArrayList<>();

		// Lay ra tat ca cac product price text
		List<WebElement> productText = getListWebElement(driver, ProductPageUI.PRODUCT_PRICE_TEXT);

		// Get text add vao ArrayList
		for (WebElement item : productText) {
			productUIList.add(Float.parseFloat(item.getText().replace("$","").trim()));
		}

		// Tao ra 1 ArrayList moi de sort du lieu
		ArrayList<Float> productSortList = new ArrayList<>();
		for (Float UIitem : productUIList) {
			productSortList.add(UIitem);
		}

		// Sort du lieu cua ArrayList moi
		Collections.sort(productSortList);

		// So sanh 2 ArrayList bang nhau
		return productSortList.equals(productUIList);
	}

	public boolean isProductPageSortByHighToLow() {
		// Khai bao ArrayList de chua tat ca cac product price tren UI
				ArrayList<Float> productUIList = new ArrayList<>();

				// Lay ra tat ca cac product price text
				List<WebElement> productText = getListWebElement(driver, ProductPageUI.PRODUCT_PRICE_TEXT);

				// Get text add vao ArrayList
				for (WebElement item : productText) {
					productUIList.add(Float.parseFloat(item.getText().replace("$","").trim()));
				}

				// Tao ra 1 ArrayList moi de sort du lieu
				ArrayList<Float> productSortList = new ArrayList<>();
				for (Float UIitem : productUIList) {
					productSortList.add(UIitem);
				}

				// Sort du lieu cua ArrayList moi
				Collections.sort(productSortList);
				
				//Reverse du lieu theo Descending
				Collections.reverse(productSortList);

				// So sanh 2 ArrayList bang nhau
				return productSortList.equals(productUIList);
	}
	

}
