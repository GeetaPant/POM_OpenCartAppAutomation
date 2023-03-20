package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
	public WebDriver  driver;
	public Properties prop;
	public OptionsManager optMgr;
/**
 * This method is initailising the driver on the basis of given browser	
 * @param browserName
 * @return -It returns the driver
 */
public WebDriver initDriver(Properties prop) {
	optMgr = new OptionsManager(prop);
	String browserName = prop.getProperty("browser").trim().toLowerCase();
	System.out.println("Browser name is : "+browserName);
	if(browserName.equalsIgnoreCase("chrome")) {
		driver = new ChromeDriver(optMgr.getChromeOptions());
	}
	else if(browserName.equalsIgnoreCase("firefox")) {
		driver = new FirefoxDriver(optMgr.getFirefoxOptions());
	}
	else if(browserName.equalsIgnoreCase("edge")) {
		driver = new EdgeDriver(optMgr.getEdgeOptions());
	}
	else {
		System.out.println("plz pass the correct browser name: "+ browserName);
	}
	driver.manage().deleteAllCookies();
	driver.manage().window().maximize();
	driver.get(prop.getProperty("url"));
	return driver;
}
/**
 * this method is reading the properties from .properties file
 * @return- Does not return anything
 */
public Properties initProp(){
	prop = new Properties();
	try {
		FileInputStream ip = new FileInputStream(".\\src\\test\\resources\\Config\\Config.properties");
		prop.load(ip);
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	return prop;
}
}