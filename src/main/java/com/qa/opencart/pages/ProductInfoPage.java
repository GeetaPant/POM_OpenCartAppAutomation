package com.qa.opencart.pages;

//import java.util.HashMap;
//import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	private By prodHeader = By.tagName("h1");
	private By prodImage = By.cssSelector("ul.thumbnails img");
	private By prodMetadata = By.xpath(" (//div[@id = 'content']//ul[@class ='list-unstyled'])[1]/li");
	private By prodPricedata = By.xpath(" (//div[@id = 'content']//ul[@class ='list-unstyled'])[2]/li");
	private By quantity = By.id("input-quantity");
	private By btnAddtoCard = By.id("button-cart");
	private By successMessage = By.cssSelector("div.alert.alert-success");
	
	private Map<String, String> prodInfoMap;
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		}

	public String getProductHeader() {
		String prodHeaderVal = eleUtil.getElementText(prodHeader);
		System.out.println("Product Header Value is:" + prodHeaderVal);
		return prodHeaderVal;
	}
	public int getProductImageCount() {
	 int imageCount = eleUtil.waitForElementsVisible(prodImage, AppConstants.DEFAULT_MEDIUM_IMEOUT).size();
	 System.out.println("Total image count:" +imageCount);
	 return imageCount;
	}
	public Map<String, String> getProductInfo() {
	//	prodInfoMap = new HashMap<String,String>(); // do not maintain order
		//prodInfoMap = new LinkedHashMap<String, String>(); // Maintain the order
		prodInfoMap = new TreeMap<String, String>(); // Sort result
		prodInfoMap.put("ProductHeader", getProductHeader());
		getProductMeta();
		getProductPrice();
		return prodInfoMap;
	
	}
		private void getProductMeta() {
			List<WebElement> metaList =eleUtil.getElements(prodMetadata);
			for(WebElement e:metaList) {
				String meta = e.getText();
				String metaInfo[]= meta.split(":");
				String key =metaInfo[0].trim();
				String value =metaInfo[1].trim();
				prodInfoMap.put(key, value);
		}
		}
		private void getProductPrice() {
			List<WebElement> priceList =eleUtil.getElements(prodPricedata);
			 String price = priceList.get(0).getText();
			 String exTax = priceList.get(1).getText();	
			 String exTaxVal = exTax.split(":")[1].trim();
			 prodInfoMap.put("productprice", price);
			 prodInfoMap.put("ExTax", exTaxVal);
				
			}
		public void enterQuantity(int qty) {
			System.out.println("Qantity of product:" + qty);
			eleUtil.doSendKeys(quantity, String.valueOf(qty));
	}
		public String addProductToCart() {
			eleUtil.doClick(btnAddtoCard);
			String successMsg = eleUtil.waitForElementVisible(successMessage, AppConstants.DEFAULT_MEDIUM_IMEOUT).getText();
			System.out.println("Cart success mesage:"+successMsg);
			return successMsg;
		}
}