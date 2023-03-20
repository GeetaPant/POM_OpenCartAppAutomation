package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class LoginPageTest extends BaseTest {
	
	@Test
	public void loginPageTitleTest() {
	String actualTitle=	loginpage.loginPageTitle();
	Assert.assertEquals(actualTitle, "Account Login");
	}
	@Test
	public void loginPageURLTest() {
	String actualURL = loginpage.loginPageURL();
	Assert.assertEquals(actualURL, "https://naveenautomationlabs.com/opencart/index.php?route=account/login");
	}
	
	@Test
	public void isForgetPasswordExistTest() {
	Assert.assertTrue(loginpage.isForgetPasswordExist());
	}
	@Test(priority =1)
    public void doLoginTest() {
		accpage = loginpage.doLogin("qatestertest@gmail.com", "Test@123");
		Assert.assertTrue(accpage.checkLogoutLink());
	
	}
    
	
}
