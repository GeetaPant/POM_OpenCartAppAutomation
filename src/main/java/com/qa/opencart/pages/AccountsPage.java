package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AccountsPage {
	private WebDriver driver;
	private By logoutLink = By.linkText("Logout");
	private By SearchBox = By.name("search");
	private By accHeader = By.xpath("//div[@id='content']/h2");
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
	}
	public String getAccPageTitle() {
		String accTitle= driver.getTitle();
		System.out.println("Title for accounts page: "+ accTitle);
		return accTitle;
}
	public boolean checkLogoutLink() {
		return driver.findElement(logoutLink).isDisplayed();
	}
	
	public boolean isSearchExist() {
	 return driver.findElement(SearchBox).isDisplayed();
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
