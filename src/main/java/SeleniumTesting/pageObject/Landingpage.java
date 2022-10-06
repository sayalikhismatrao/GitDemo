package SeleniumTesting.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumTesting.abstractcomponent.Abstractcomponents;

public class Landingpage extends Abstractcomponents{
	
	WebDriver driver; //initializing variable
	
	//creating constructor
	public Landingpage(WebDriver driver) {
		
		super(driver); //sending driver variable to parent class ie. to abstractcomponents class
		this.driver=driver;	// giving access of webdriver to the local variable "driver" of this class
		PageFactory.initElements(driver, this); //to initialize all webelements it uses driver as argument and uses this driver for initialization
	}
	
	/*WebElement userId = driver.findElement(By.id("userEmail"));
	WebElement userPassword = driver.findElement(By.id("userPassword"));
	WebElement loginBtn = driver.findElement(By.name("login"));*/
	
	//PageFactory design
	@FindBy(id="userEmail")
	WebElement userId;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(name="login")
	WebElement loginBtn;
	
	@FindBy(css="[class*='inserted']")
	WebElement errorMessage;
	
	//action method
	public Productcatalog loginApplication(String email, String password)
	{
		userId.sendKeys(email);
		userPassword.sendKeys(password);
		loginBtn.click();
		Productcatalog catalogPage = new Productcatalog(driver);
		return catalogPage;
	}
	
	public String getErrorMessage() {
		
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
}
