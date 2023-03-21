package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By emailid = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@type = 'submit']");	
	private By forgotPwd= By.linkText("Forgotten Password");
	
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		
	}
	public String loginPageTitle() {
		String title = eleUtil.waitForTitleIsAndFetch(AppConstants.DEFAULT_SHORT_IMEOUT, AppConstants.LOGIN_PAGE_TITLE_VALUE);
		System.out.println("Title of the page : "+ title);
		return title;
	}
	public String loginPageURL() {
		String url=eleUtil.waitForURLContainsAndFetch(AppConstants.DEFAULT_MEDIUM_IMEOUT,AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE);
		System.out.println("Current Page URL: "+ url);
		return url;
	}
	
	public boolean isForgetPasswordExist() {
		return eleUtil.waitForElementVisible(forgotPwd, AppConstants.DEFAULT_MEDIUM_IMEOUT).isDisplayed();
	}
	
	public AccountsPage doLogin(String un, String pwd) {
	eleUtil.waitForElementPresence(emailid, AppConstants.DEFAULT_MEDIUM_IMEOUT).sendKeys(un);;
	eleUtil.doSendKeys(password, pwd);
	eleUtil.doClick(loginBtn);
	return new AccountsPage(driver);
	
	}
}
