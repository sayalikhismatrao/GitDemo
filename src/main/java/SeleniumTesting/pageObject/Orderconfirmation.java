package SeleniumTesting.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import SeleniumTesting.abstractcomponent.Abstractcomponents;

public class Orderconfirmation extends Abstractcomponents {

	WebDriver driver;

	// creating constructor
	public Orderconfirmation(WebDriver driver) {
		super(driver);
		this.driver=driver;	
		PageFactory.initElements(driver, this); 
	}
	
	@FindBy(css=".hero-primary")
	WebElement confirm;
	
	public String confirmation() {
		
		return confirm.getText();
	}
}
