package Browser;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import Properties.SetProperties;

public class BrowserSetup {
	public static WebDriver driver;
	static String driverPath;
	static Properties prop = SetProperties.getPropertiesFile();
	
	//Get Browser driver and return driver instance
	public static WebDriver getWebDriver(String Browser) {



		//For Chrome Browser
		if(Browser.equalsIgnoreCase("Chrome")) {
			driverPath = System.getProperty("user.dir") + "\\src\\test\\resources\\Drivers\\chromedriver.exe";
			System.setProperty("webdriver.chrome.driver",driverPath );
			driver = new ChromeDriver();

		}//for firfox browser
		else if(Browser.equalsIgnoreCase("Firefox")) {
			driverPath = System.getProperty("user.dir") + "\\src\\test\\resources\\Drivers\\geckodriver.exe";
			System.setProperty("webdriver.gecko.driver", driverPath);
			driver = new FirefoxDriver();

		}
		else {
			System.out.println("invalid input ,Run again");
		}

		return driver;
	}

	//Get url for data.properties file ,manage implicit wait and window size 
	public static void getUrl() throws InterruptedException {
		driver.get(prop.getProperty("URL"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
}
