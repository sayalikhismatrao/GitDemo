package SeleniumTesting;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import SeleniumTesting.pageObject.Checkoutpage;
import SeleniumTesting.pageObject.Landingpage;
import SeleniumTesting.pageObject.Orderconfirmation;
import SeleniumTesting.pageObject.Productcatalog;
import SeleniumTesting.testcomponents.Basetest;

public class Errorvalidation extends Basetest {

	@Test

	public void test1() throws IOException, InterruptedException {

		// A)Login Page with invalid ID

		Productcatalog catalogPage = landingPage.loginApplication("sayalikhi93@gmail.com", "P@ssw0rd");
		Assert.assertEquals("Incorrect email password.", landingPage.getErrorMessage());
	}
	
	@Test
	public void test2() {
		
		// B) Login page with invalid password
		
		Productcatalog catalogPage = landingPage.loginApplication("sayalik1992@gmail.com", "Password");
		Assert.assertEquals("*Enter Valid Email", landingPage.getErrorMessage());
	}

}
