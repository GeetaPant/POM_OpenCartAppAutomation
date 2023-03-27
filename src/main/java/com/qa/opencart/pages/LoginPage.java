package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By emailid = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@type = 'submit']");	
	private By forgotPwd= By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		
	}
	@Step("Getting login page title")
	public String loginPageTitle() {
		String title = eleUtil.waitForTitleIsAndFetch(AppConstants.DEFAULT_SHORT_IMEOUT, AppConstants.LOGIN_PAGE_TITLE_VALUE);
		System.out.println("Title of the page : "+ title);
		return title;
	}
	@Step("Getting the login page URL")
	public String loginPageURL() {
		String url=eleUtil.waitForURLContainsAndFetch(AppConstants.DEFAULT_MEDIUM_IMEOUT,AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE);
		System.out.println("Current Page URL: "+ url);
		return url;
	}
	@Step("Checking the forget password link")
	public boolean isForgetPasswordExist() {
		return eleUtil.waitForElementVisible(forgotPwd, AppConstants.DEFAULT_MEDIUM_IMEOUT).isDisplayed();
	}
	@Step("Login with username:{0} and password: {1}")
	public AccountsPage doLogin(String un, String pwd) {
	eleUtil.waitForElementPresence(emailid, AppConstants.DEFAULT_MEDIUM_IMEOUT).sendKeys(un);;
	eleUtil.doSendKeys(password, pwd);
	eleUtil.doClick(loginBtn);
	return new AccountsPage(driver);
	
	}
	@Step("Navigating to register page")
	public RegisterPage navigateToRegisterPage()
	{
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}
}
