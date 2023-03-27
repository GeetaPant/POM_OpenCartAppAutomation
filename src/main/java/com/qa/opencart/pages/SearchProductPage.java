package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class SearchProductPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	private By searchResults = By.xpath("//div[@class = 'product-thumb']");
	//private By searchResults = By.cssSelector("div#content div.parent-layout");
   
	public SearchProductPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	public int productSearchCount() {
		int prodCount = eleUtil.waitForElementsPresence(searchResults, AppConstants.DEFAULT_MEDIUM_IMEOUT).size();
		System.out.println("Count of searched product:"+prodCount);
		return prodCount;
	}
	public ProductInfoPage selectProduct(String prodName) {
		By prodLocator = By.linkText(prodName);
		eleUtil.doActionsClick(prodLocator);
		//eleUtil.doClick(prodLocator);
		//eleUtil.waitForElementVisible(prodLocator, AppConstants.DEFAULT_MEDIUM_IMEOUT).click();
		return new ProductInfoPage(driver);
	}
}
