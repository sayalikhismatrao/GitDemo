package SeleniumTesting.pageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumTesting.abstractcomponent.Abstractcomponents;

public class Orderpage extends Abstractcomponents {

	WebDriver driver;

	// creating constructor
	public Orderpage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> orderItem;
	
	public List<WebElement> getOrderItem() {
		
		return orderItem;
	}
	
	public boolean verifyOrderDisplay(String productName) {
		
		Boolean verify=getOrderItem().stream().anyMatch(cartlist->cartlist.getText().equalsIgnoreCase(productName));
		return verify;
	}

}
