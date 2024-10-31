package zeeshan.SeleniumFramewordDesign.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import zeeshan.SeleniumFramewordDesign.abstractcomponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@placeholder=\"Select Country\"]")
	WebElement country;

	@FindBy(css=".action__submit")
	WebElement submit;
	
	@FindBy(xpath="//button[@class=\"ta-item list-group-item ng-star-inserted\"]")
	List <WebElement> countryList;
	
	public void selectCountry(String countryName) {
		country.sendKeys(countryName);
		List<WebElement> count= countryList;
		count.stream().filter(country->country.getText().equalsIgnoreCase(countryName)).findFirst().ifPresent(WebElement:: click );
		
	}
	public ConfirmationPage submitOrder() {
		submit.click();
		return new ConfirmationPage(driver);
	}
	
	

}
