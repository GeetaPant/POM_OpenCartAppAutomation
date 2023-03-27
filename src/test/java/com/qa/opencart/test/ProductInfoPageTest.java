package com.qa.opencart.test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductInfoPageTest extends BaseTest {

	@BeforeClass
	public void prodPageSetup() {
		accpage = loginpage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
	@DataProvider
	public Object[][] getProdDetails() {
		return new Object[][] {
			{"MacBook","MacBook Pro", 4},
			{"iMac", "iMac", 1}
		};	
	}
	@Test
	public void getProdImageCountTest(String prodKey, String prodName, int imgCount) {
		searchpage= accpage.doProductSearch(prodKey);
		prodInfopage = searchpage.selectProduct(prodName);
		int actImageCount = prodInfopage.getProductImageCount();
		Assert.assertEquals(actImageCount, imgCount);
	}
	@Test
	public void getProductInfoTest() {
		searchpage= accpage.doProductSearch("MacBook");
		prodInfopage = searchpage.selectProduct("MacBook Pro");
		Map<String, String> accProdDetails = prodInfopage.getProductInfo();
		System.out.println(accProdDetails);
		softAssert.assertEquals(accProdDetails.get("Brand"),"Apple");
		softAssert.assertEquals(accProdDetails.get("productprice"),"$2000.00");
		
		softAssert.assertAll();
	}
	@Test
	public void addToCartTest() {
		searchpage= accpage.doProductSearch("MacBook");
		prodInfopage = searchpage.selectProduct("MacBook Pro");
		prodInfopage.enterQuantity(2);
		String accSuccessMsg = prodInfopage.addProductToCart();
		softAssert.assertEquals(accSuccessMsg, "Success");
		
	}
}
