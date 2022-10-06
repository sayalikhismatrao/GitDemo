package SeleniumTesting;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SeleniumTesting.pageObject.Checkoutpage;
import SeleniumTesting.pageObject.Orderconfirmation;
import SeleniumTesting.pageObject.Orderpage;
import SeleniumTesting.pageObject.Productcatalog;
import SeleniumTesting.testcomponents.Basetest;

public class EcommTest extends Basetest {

	// 1. end-to-end test

	String productName = "zara coat 3";

	@Test(dataProvider="getData", groups= {"purchase"})

	public void ecommTest(HashMap<String,String> input) throws IOException, InterruptedException {

		// Notes: using webdriver manager to invoke the chrome driver. it doesn't need
		// chrome driver in local
		// 1. add Maven webdrivermanager dependencies in POm.xlm file
		// 2. invoke browser in java program

		// A)Login Page

		Productcatalog catalogPage = landingPage.loginApplication(input.get("email"), input.get("password"));

		// B)Catalogpage: add item to cart
		// 1. get all the products in list
		// 2. retrieve item from that list using Stream()
		// 3. click on add to cart
		// 4.verify added to cart message and for that we need to add explicit wait
		// 5.wait until the toast message is visible and the process bar is disappearing
		// 6.verify product added in cart using stream
		List<WebElement> products = catalogPage.getProductList();
		catalogPage.addToCart(input.get("product"));
		Checkoutpage checkoutpg = catalogPage.goToCart();

		// C)CheckoutPage : click on checkout button
		// payment process
		checkoutpg.getCartItem();
		checkoutpg.checkout();
		Orderconfirmation response = checkoutpg.placeOrder();
		Assert.assertFalse(checkoutpg.verifyCartItem(input.get("product")));

		// D)Final page: order confirmation

		String message = response.confirmation();
		Assert.assertTrue(message.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		checkoutpg.windowScrollingUp();
		Thread.sleep(2000);
		checkoutpg.signOut();
		
	}

	// 2.To verify zara-coat 3 displaying in orders page
	@Test(dependsOnMethods = {"ecommTest"})
	public void orderHistoryCheck() {

		Productcatalog catalogPage = landingPage.loginApplication("sayalik1992@gmail.com", "P@ssw0rd");
		Orderpage orderPg = catalogPage.goToOrder();
		Assert.assertTrue(orderPg.verifyOrderDisplay(productName));

	}
	
	//get data from json file
	@DataProvider
	public Object[][] getData() throws IOException {
		
		/*HashMap<String,String> map= new HashMap<String,String>();
		map.put("email", "sayalik1992@gmail.com");
		map.put("password", "P@ssw0rd");
		map.put("product", "zara coat 3");
		
		HashMap<String,String> map1= new HashMap<String,String>();
		map1.put("email", "sayalikhi93@gmail.com");
		map1.put("password", "P@ssword");
		map1.put("product", "ADIDAS ORIGINAL");*/
		
		List<HashMap<String, String >> data = getJsonData(System.getProperty("user.dir")+"\\src\\test\\java\\SeleniumTesting\\data\\purchaseOrder.json");
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	/*@DataProvider
	public Object[][] getData(){
		
		return new Object[][] {{"sayalik1992@gmail.com","P@ssw0rd","zara coat 3"},{"sayalikhi93@gmail.com","P@ssword","ADIDAS ORIGINAL"}};
	}*/

}
