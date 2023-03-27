package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;


public class DriverFactory {
	public WebDriver  driver;
	public Properties prop;
	public OptionsManager optMgr;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	String envPath = System.getProperty("env");
		
	
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
		tlDriver.set(new ChromeDriver(optMgr.getChromeOptions()));
		//driver = new ChromeDriver(optMgr.getChromeOptions());
	}
	else if(browserName.equalsIgnoreCase("firefox")) {
		tlDriver.set(new FirefoxDriver(optMgr.getFirefoxOptions()));
	//	driver = new FirefoxDriver(optMgr.getFirefoxOptions());
	}
	else if(browserName.equalsIgnoreCase("edge")) {
	//	driver = new EdgeDriver(optMgr.getEdgeOptions());
		tlDriver.set(new EdgeDriver(optMgr.getEdgeOptions()));
	}
	else {
		System.out.println("plz pass the correct browser name: "+ browserName);
	}
	getDriver().manage().deleteAllCookies();
	getDriver().manage().window().maximize();
	getDriver().get(prop.getProperty("url"));
	return getDriver();
}

/**thread copy of the driver
 * gives a local 
 * @return
 */
public synchronized static WebDriver getDriver() {
	return tlDriver.get();
}

public  static String getScreenshot() {
	File srcFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
	String path =System.getProperty("user.dir")+"\\screenshot\\"+System.currentTimeMillis()+".png";
	File destination  = new File(path);
	try {
		FileHandler.copy(srcFile, destination);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return path;
}
/**
 * this method is reading the properties from .properties file
 * @return- Does not return anything
 */
public Properties initProp(){
	prop = new Properties();
	FileInputStream ip = null;
	String envName = System.getProperty("env");
	System.out.println("Running test cases on Env: " + envName);

	try {
	if (envName == null) {
		System.out.println("no env is passed....Running tests on QA env...");
		ip = new FileInputStream(".src\\test\\resources\\Config\\Config.properties");
	} else {
		switch (envName.toLowerCase().trim()) {
		case "qa":
			ip = new FileInputStream("src\\test\\resources\\Config\\qa.config.properties");
			break;
		case "prod":
			ip = new FileInputStream(".src\\test\\resources\\Config\\Config.properties");
			break;

		default:
			System.out.println("....Wrong env is passed....No need to run the test cases....");
			break;
		}

	}
} catch (FileNotFoundException e) {

}

try {
	prop.load(ip);
} catch (IOException e) {
	e.printStackTrace();
}
	return prop;
}
}