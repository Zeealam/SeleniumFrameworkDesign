package zeeshan.SeleniumFramewordDesign.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import zeeshan.SeleniumFramewordDesign.pageobjects.CartPage;
import zeeshan.SeleniumFramewordDesign.pageobjects.CheckOutPage;
import zeeshan.SeleniumFramewordDesign.pageobjects.ConfirmationPage;
import zeeshan.SeleniumFramewordDesign.pageobjects.LandingPage;
import zeeshan.SeleniumFramewordDesign.pageobjects.ProductCatalogue;
import zeeshan.SeleniumFrameworkDesign.TestComponents.BaseTest;

public class ErrorValidationsTest extends BaseTest{
	
	@Test(groups= {"ErrorHandling"})//,retryAnalyzer=Retry.class
	public void loginErrorValidation() throws IOException {
		
		String productName = "ZARA COAT 3";
		String countryName= "india";
	
		 landingPage.loginApplication("anshika@gmail.com", "Iamki000");
		Assert.assertEquals("Incorrect email password.", landingPage.getErrorMessage());
	}
	@Test
	public void productErrorValidation() {
		String productName = "ZARA COAT 3";
		String countryName= "india";
		
		ProductCatalogue productCatalogue= landingPage.loginApplication("anshika@gmail.com", "Iamking@000");
			
		List<WebElement>products=productCatalogue.getProductList();
		productCatalogue.addproductTocart(productName);
		
		CartPage cartPage= productCatalogue.goToCartPage();
		Boolean match=cartPage.VerifyProductDisplay("ZARA COAT 312");
		Assert.assertFalse(match);
	}

}
