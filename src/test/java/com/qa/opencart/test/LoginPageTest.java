package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class LoginPageTest extends BaseTest {
	
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
	
	@Test
	public void isForgetPasswordExistTest() {
	Assert.assertTrue(loginpage.isForgetPasswordExist());
	}
	@Test(priority =1)
    public void doLoginTest() {
		accpage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(accpage.checkLogoutLink());
	
	}
    
	
}
