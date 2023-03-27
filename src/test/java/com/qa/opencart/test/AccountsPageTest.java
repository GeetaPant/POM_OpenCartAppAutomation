package com.qa.opencart.test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountsPageTest extends BaseTest {
	
	@BeforeClass
	public void accPageSetup() {
		accpage = loginpage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
	@Test
	public void getAccPageTitleTest() {
		String title = accpage.getAccPageTitle();
		Assert.assertEquals(title, AppConstants.ACCOUNTS_PAGE_TITLE_VALUE);	
	}
	@Test
	public void checkLogoutLinkTest() {
		Assert.assertTrue(accpage.checkLogoutLink());
	}
	@Test
	public void isSearchExistTest() {
		Assert.assertTrue(accpage.isSearchExist());	
	}
	@Test	
	public void getAccountHeadersListTest() {
		List<String> accHeaderList= accpage.getAccountHeadersList();
		System.out.println("Account Page Headers are: "+accHeaderList);
		Assert.assertEquals(accHeaderList.size(), AppConstants.ACCOUNTS_PAGE_HEADERS_COUNT);
		}
	@Test
	public void accPageHeadersValueTest() {
		List<String> actualAccPageHeadersList = accpage.getAccountHeadersList();
		System.out.println("Actual Acc Page Headers List: " + actualAccPageHeadersList);
		System.out.println("Expected Acc Page Headers List:" + AppConstants.EXPECTED_ACCOUNTS_PAGE_HEADERS_LIST);
		Assert.assertEquals(actualAccPageHeadersList, AppConstants.EXPECTED_ACCOUNTS_PAGE_HEADERS_LIST);
	}
	
	
	
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] {
			{"MacBook"},
			{"Samsung"},
			{"Apple"}	
		};
	}
	
	@Test(dataProvider ="getProductData")
	public void searchTest(String prodName){
		searchpage = accpage.doProductSearch(prodName);
		Assert.assertTrue(searchpage.productSearchCount()>0);
	}

	@DataProvider
	public Object[][] getProductTestData() {
		return new Object[][] {
			{"MacBook","MacBook Air"},
			{"Samsung", "Samsung Galaxy Tab 10.1"},
			{"Apple","Apple Cinema 30\""}	
		};
	}
	
	@Test(dataProvider = "getProductTestData")
	public void searchProductTest(String searchKey, String productName) {
		searchpage = accpage.doProductSearch(searchKey);
		if(searchpage.productSearchCount()>0) {
			prodInfopage = searchpage.selectProduct(productName);
			String accProdHeader = prodInfopage.getProductHeader();
			softAssert.assertEquals(accProdHeader, productName);
			softAssert.assertAll();
		}
	}
}
