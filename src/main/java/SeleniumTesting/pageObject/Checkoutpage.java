package SeleniumTesting.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import SeleniumTesting.abstractcomponent.Abstractcomponents;

public class Checkoutpage extends Abstractcomponents {

	WebDriver driver;

	// creating constructor
	public Checkoutpage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".totalRow button")
	WebElement checkout;

	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement dropdown;

	By dropdownResult = By.cssSelector(".ta-results");

	@FindBy(xpath = "//button[contains(@class,'ta-item')][2]")
	WebElement country;

	@FindBy(css = ".actions a")
	WebElement placeOrder;
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartItem;

	public void checkout() {

		windowScrolling();
		checkout.click();
	}

	public Orderconfirmation placeOrder() throws InterruptedException {

		Actions a = new Actions(driver);
		a.sendKeys(dropdown, "India").build().perform();
		waitForElementToAppear(dropdownResult);
		country.click();
		windowScrolling();
		Thread.sleep(2000);
		placeOrder.click();
		Orderconfirmation response=new Orderconfirmation(driver);
		return response;
	}
	
	public List<WebElement> getCartItem() {
		
		return cartItem;
	}
	
	public Boolean verifyCartItem(String productName) {
		
		Boolean match=getCartItem().stream().anyMatch(cartlist->cartlist.getText().equalsIgnoreCase(productName));
		return match;
	}

}
