package zeeshan.SeleniumFramewordDesign.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import zeeshan.SeleniumFramewordDesign.pageobjects.CartPage;
import zeeshan.SeleniumFramewordDesign.pageobjects.CheckOutPage;
import zeeshan.SeleniumFramewordDesign.pageobjects.ConfirmationPage;
import zeeshan.SeleniumFramewordDesign.pageobjects.LandingPage;
import zeeshan.SeleniumFramewordDesign.pageobjects.OrderPage;
import zeeshan.SeleniumFramewordDesign.pageobjects.ProductCatalogue;
import zeeshan.SeleniumFrameworkDesign.TestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";
	String countryName = "india";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws IOException {

		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));

		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addproductTocart(input.get("product"));

		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);

		CheckOutPage checkoutpage = cartPage.goTocheckOut();
		checkoutpage.selectCountry(countryName);
		//((JavascriptExecutor)driver).executeScript("windw.scrollBy(0.500)");
		ConfirmationPage confirmationpage = checkoutpage.submitOrder();
		String confirmMessage = confirmationpage.getConfirmMessage();
		Assert.assertTrue(confirmMessage.equals("THANKYOU FOR THE ORDER."));
	}

	// check the product is present in order history page
	@Test(dependsOnMethods = { "submitOrder" })
	public void orderHistoryTest() {
		ProductCatalogue productCatalogue = landingPage.loginApplication("zeeshan.alam7@gmail.com", "Bangalore2016@");
		OrderPage ordersPage = productCatalogue.goToOrderPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")
				+ "\\src\\test\\java\\zeeshan\\SeleniumFrameworkDesign\\data\\PurchasedOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };

//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "zeeshan.alam7@gmail.com");
//		map.put("password", "Bangalore2016@");
//		map.put("product", "ZARA COAT 3");
//
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "anshika@gmail.com");
//		map1.put("password", "Iamking@000");
//		map1.put("product", "ADIDAS ORIGINAL");

	}

}
