package Browser;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import Properties.SetProperties;
import io.github.bonigarcia.wdm.WebDriverManager;


public class BrowserSetup {
	public static WebDriver driver;
	static String driverPath;
	static Properties prop = SetProperties.getPropertiesFile();
	
	//Get Browser driver and return driver instance
	public static WebDriver getWebDriver(String Browser) {



		//For Chrome Browser
		if(Browser.equalsIgnoreCase("Chrome")) {
//			driverPath = System.getProperty("user.dir") + "\\src\\test\\resources\\Drivers\\chromedriver.exe";
//			System.setProperty("webdriver.chrome.driver",driverPath );
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		}//for firfox browser
		else if(Browser.equalsIgnoreCase("Firefox")) {
			driverPath = System.getProperty("user.dir") + "\\src\\test\\resources\\Drivers\\geckodriver.exe";
			System.setProperty("webdriver.gecko.driver", driverPath);
			driver = new FirefoxDriver();

		}
		else if(Browser.equalsIgnoreCase("Edge")) {
			driverPath = System.getProperty("user.dir") + "\\src\\test\\resources\\Drivers\\msedgedriver.exe";
			System.setProperty("webdriver.edge.driver", driverPath);
//			WebDriverManager.edgedriver().setup();
			EdgeOptions options = new EdgeOptions();
//	        options.setCapability("args","--guest");
	        options.addArguments("--guest");
	        options.addArguments("--remote-allow-origins=*");
	        // Disable notifications
	        options.addArguments("--disable-notifications");
	        
	        // Disable pop-up blocking
	        options.addArguments("--disable-popup-blocking");

	        // Disable Edge's tracking prevention feature
	        options.addArguments("--disable-tracking-prevention");
	        
	        // Disable Edge's SmartScreen filter
	        options.addArguments("--disable-smartscreen-filter");
	        
	        // Disable Edge's SafeSearch feature
	        options.addArguments("--safebrowsing-disable-download-protection");
			driver = new EdgeDriver(options);
			

		}
		else {
			System.out.println("invalid input ,Run again");
		}

		return driver;
	}

	//Get url for data.properties file ,manage implicit wait and window size 
	public static void getUrl() throws InterruptedException {
		String url = prop.getProperty("URL");
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
}
