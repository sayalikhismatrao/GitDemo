package SeleniumTesting.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import SeleniumTesting.abstractcomponent.Abstractcomponents;

public class Productcatalog extends Abstractcomponents {

	WebDriver driver;

	// creating constructor
	public Productcatalog(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	List<WebElement> products;

	@FindBy(css = ".ng-animating")
	WebElement disappear;

	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");

	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;
	}

	public WebElement getProductByName(String productName) {

		WebElement item = getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst()
				.orElse(null);
		return item;
	}

	public void addToCart(String productName) {

		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(disappear);
	}

}
