package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	private WebDriver driver;
	private By logoutLink = By.linkText("Logout");
	private By SearchBox = By.name("search");
	private By accHeader = By.xpath("//div[@id='content']/h2");
	private ElementUtil eleUtil;
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	public String getAccPageTitle() {
		String accTitle=eleUtil.waitForTitleIsAndFetch(AppConstants.DEFAULT_MEDIUM_IMEOUT, AppConstants.ACCOUNTS_PAGE_TITLE_VALUE);
		
		System.out.println("Title for accounts page: "+ accTitle);
		return accTitle;
}
	public boolean checkLogoutLink() {
		return eleUtil.waitForElementVisible(logoutLink, AppConstants.DEFAULT_SHORT_IMEOUT).isDisplayed();
	}
	
	public boolean isSearchExist() {
		return eleUtil.waitForElementVisible(SearchBox, AppConstants.DEFAULT_MEDIUM_IMEOUT).isDisplayed();
	}
	
	public List<String> getAccountHeadersList() {
		List<WebElement> accHeaderList= driver.findElements(accHeader);
		accHeaderList.size();
		List<String> accHeaderValList = new ArrayList<String>();
		
		for(WebElement e:accHeaderList) {
			String text = e.getText();
			accHeaderValList.add(text); 
		}
		return accHeaderValList;
	}
}
