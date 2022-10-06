package SeleniymTesting.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentreporterNG {
	
	public static ExtentReports getReportObject() {
		
		String path=System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path); // this object is responsible for creating all config for your report	
		reporter.config().setReportName("Web Automation Results"); 
		reporter.config().setDocumentTitle("Test Results");
		
		ExtentReports extent = new ExtentReports(); // this object is responsible to driver all reporting execution. this is main class
		extent.attachReporter(reporter); // main class to have knowledge of object
		extent.setSystemInfo("Tester", "Sayali");
		return extent;
	}

}
