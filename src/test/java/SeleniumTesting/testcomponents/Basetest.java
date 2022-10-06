package SeleniumTesting.testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import SeleniumTesting.pageObject.Landingpage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Basetest {

	public WebDriver driver;
	public Landingpage landingPage;

	public WebDriver initializeDriver() throws IOException {

		// properties class can read the global properties. so first create global
		// properties
		// for global properties create new file under main/java. list out all the
		// global properties and extract that file in this class

		Properties prop = new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\SeleniymTesting\\resources\\GlobalData.properties");
		prop.load(file);
		//to check if browser value set in mcn command. solve this using java ternary operator
		String browserName =System.getProperty("browser")!=null ? System.getProperty("browser"):prop.getProperty("browser");
		//String browserName = prop.getProperty("browser"); //read browser value from globaldata file

		if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup(); // replaces system.setProperty();
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			// System.setProperty("webdriver.firefox.driver",
			// "D:\\Selenium\\Driver\\firefox-win64\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			// System.setProperty("webdriver.edge.driver",
			// "D:\\Selenium\\Driver\\edgedriver_win64\\msedgedriver.exe");
			driver = new EdgeDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	
	public static List<HashMap<String, String>> getJsonData(String filePath) throws IOException {
		// read json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		// convert string to hashmap Jackson Databind --> it is a framework which will help to convert string to hashmap
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data= mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
		return data;

	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		
		
		TakesScreenshot ts= (TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
		
	}

	@BeforeTest
	public Landingpage launchApplication() throws IOException {

		driver = initializeDriver();
		landingPage = new Landingpage(driver);
		landingPage.goTo();
		return landingPage;

	}

}
