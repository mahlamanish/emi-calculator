package WebPages;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

//import org.openqa.selenium.support.PageFactory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Browser.BrowserSetup;
import ExtentReport.ExtentReporter;
import Properties.SetProperties;
public class BaseUI {
	
	static Properties prop = SetProperties.getPropertiesFile();
	public static WebDriver driver;
	public static ExtentReports report = ExtentReporter.createReport();
	public static ExtentTest logger;
	
	public static void createWebDriver(String Browser) throws InterruptedException 
	{
		logger=report.createTest(prop.getProperty("TESTNAME"));
		driver  = BrowserSetup.getWebDriver(Browser);
		BrowserSetup.getUrl();
		logger.log(Status.INFO, "Driver initilization Sucessfull");
	}

	
	public static HomePage returnDriver() {
		return PageFactory.initElements(driver, HomePage.class);
	}
	
	public static void quitDriver() 
	{
		report.flush();
		driver.quit();
		logger.log(Status.INFO, "Driver Closed");
	}
}
