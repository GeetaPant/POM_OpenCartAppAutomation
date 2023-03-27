package com.qa.opencart.test;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest{
	
	@BeforeClass
	public void regSetup() {
		registerpage =loginpage.navigateToRegisterPage();
	}
	public String getRandomEmailID() {
		Random random =new Random();
		String email = "Automation"+ random.nextInt(1000)+"@gmail.com";
		return email;
	}
	@DataProvider
	public Object[][] getRegTestData() {
		Object regData[][] = ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
		return regData;
		
	}
	@Test(dataProvider = "getRegTestData")
	public void userRegisterTest(String fn, String ln, String phone, String pwd, String subscribe) {
		Assert.assertTrue(registerpage.registerUser(fn, ln ,getRandomEmailID(), phone, pwd, subscribe));
		
	}
}
