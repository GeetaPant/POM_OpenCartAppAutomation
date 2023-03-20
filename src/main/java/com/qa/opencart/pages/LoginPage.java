package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

	private WebDriver driver;
	
	private By emailid = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@type = 'submit']");	
	private By forgotPwd= By.linkText("Forgotten Password");
	
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	public String loginPageTitle() {
		String title = driver.getTitle();
		System.out.println("Title of the page : "+ title);
		return title;
	}
	public String loginPageURL() {
		String url = driver.getCurrentUrl();
		System.out.println("Current Page URL: "+ url);
		return url;
	}
	
	public boolean isForgetPasswordExist() {
	return driver.findElement(forgotPwd).isDisplayed();	

	}
	
	public AccountsPage doLogin(String un, String pwd) {
	driver.findElement(emailid).sendKeys(un);
	driver.findElement(password).sendKeys(pwd);
	driver.findElement(loginBtn).click();
	return new AccountsPage(driver);
	
	}
}
