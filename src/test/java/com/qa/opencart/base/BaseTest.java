package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchProductPage;

public class BaseTest {
	DriverFactory df;
	protected LoginPage loginpage;
	protected AccountsPage accpage;
	protected SearchProductPage searchpage;
	protected ProductInfoPage prodInfopage;
	protected RegisterPage registerpage; 
	WebDriver driver;
	protected Properties prop;
	protected SoftAssert softAssert;
	
	@BeforeTest
	public void setup() {
		df = new DriverFactory();
		prop = df.initProp();
		driver = df.initDriver(prop);
		loginpage= new LoginPage(driver);
		
		softAssert = new SoftAssert();
	}
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
