package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


	@Epic("EPIC_01_DESIGN LOGIN FOR OPENCART")
	@Story("US-LOGIN- Design Login feature for open cart")
	
	public class LoginPageTest extends BaseTest {
	@Severity (SeverityLevel.TRIVIAL)
	@Description("Verifying the Login page title")
	@Test
	public void loginPageTitleTest() {
	String actualTitle=	loginpage.loginPageTitle();
	Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE_VALUE);
	}
	@Test
	public void loginPageURLTest() {
	String actualURL = loginpage.loginPageURL();
	Assert.assertTrue(actualURL.contains(AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE));
	//Assert.assertTrue(actualURL.contains(AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE));
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@Description("Veriying the Forgot Password link is present in the app")
	@Test
	public void isForgetPasswordExistTest() {
	Assert.assertTrue(loginpage.isForgetPasswordExist());
	}
	
	@Severity(SeverityLevel.BLOCKER)
	@Description("Verifying the user's login to the open cart site")
	@Test(priority =1)
    public void doLoginTest() {
		accpage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(accpage.checkLogoutLink());
	
	}
    
	
}
