package com.qa.opencart.test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountsPageTest extends BaseTest {
	
	@BeforeClass
	public void accPageSetup() {
		accpage = loginpage.doLogin("qatestertest@gmail.com", "Test@123");
	}
	@Test
	public void getAccPageTitleTest() {
		String title = accpage.getAccPageTitle();
		Assert.assertEquals(title, "My Account");	
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

}
