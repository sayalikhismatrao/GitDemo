package SeleniumTesting.abstractcomponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SeleniumTesting.pageObject.Checkoutpage;
import SeleniumTesting.pageObject.Orderpage;

public class Abstractcomponents {
	
	WebDriver driver;
	//creating constructor to catch the variable sent by child class i.e by loginpage class
	public Abstractcomponents(WebDriver driver) {
		
		this.driver=driver;
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
	}

	@FindBy(css="[routerlink*='cart']")
	WebElement cart;
	
	@FindBy(css="button.btn.btn-custom[routerlink='/dashboard/myorders']")
	WebElement order;
	
	@FindBy(xpath="//ul/li[5]/button[@class='btn btn-custom']")
	WebElement signout;
	
	public void waitForElementToAppear(By findBy) {
	
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement findBy) {
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
		}
	
	public void waitForElementToDisappear(WebElement disappear) {
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(disappear));
	}
	
	public void windowScrolling() {
		
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,1000)");
	}
	
	public void windowScrollingUp() {
		
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,-1000)");
	}
	
	public Checkoutpage goToCart() {
		
		cart.click();
		Checkoutpage checkoutpg = new Checkoutpage(driver);
		return checkoutpg;
	}
	
	public Orderpage goToOrder() {
		order.click();
		Orderpage orderPg= new Orderpage(driver);
		return orderPg;
	}
	
	public void signOut() {
		
		signout.click();
	}
}