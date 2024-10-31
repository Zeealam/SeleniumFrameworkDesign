package zeeshan.SeleniumFramewordDesign.tests;

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

import zeeshan.SeleniumFramewordDesign.pageobjects.LandingPage;

public class StandAloneTest2 {

	public static void main(String[] args) throws InterruptedException {
		
		String productName = "ZARA COAT 3";
		String countryName= "india";
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		LandingPage landingPage = new LandingPage(driver);
		driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys("zeeshan.alam7@gmail.com");
		driver.findElement(By.xpath("//input[@id='userPassword']")).sendKeys("Bangalore2016@");
		driver.findElement(By.xpath("//input[@id='login']")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".mb-3")));
		
		List <WebElement> products=driver.findElements(By.cssSelector(".mb-3"));	
		WebElement prod= products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cartProducts= driver.findElements(By.xpath("//div[@class=\"cartSection\"]/h3"));
		Boolean match= cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		
		driver.findElement(By.xpath("//li[@class=\"totalRow\"]/button")).click();
		
		driver.findElement(By.xpath("//input[@placeholder=\"Select Country\"]")).sendKeys(countryName);
		
		List<WebElement> count= driver.findElements(By.xpath("//button[@class=\"ta-item list-group-item ng-star-inserted\"]"));
		
		count.stream().filter(country->country.getText().equalsIgnoreCase(countryName)).findFirst().ifPresent(WebElement:: click );
		
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String confirmMessage= driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equals("THANKYOU FOR THE ORDER"));
		
	}

}
