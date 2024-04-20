/*package Test;

import java.util.Properties;

import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;


import WebPages.BaseUI;
import WebPages.HomePage;
import Properties.SetProperties;


public class MainTest{
	
	Properties prop = SetProperties.getPropertiesFile();

	
	@BeforeTest
	public void initialize() throws InterruptedException 
	{
	BaseUI.createWebDriver(prop.getProperty("Browser"));
	}
	
	@Test
	public void Test() throws Exception 
	{
		
		HomePage home = new HomePage();
		home.clickCarLoan();
		home.fillCarLoanAmount("850005");
		home.fillInterestRate("7");
		home.fillLoanTenure("5");
		home.pressEnter();
		//Thread.sleep(2000);
		//Using to see results for test examination later it will be removed
		//Thread.sleep(5000);
		home.firstMonthClick();
		//Thread.sleep(2000);
		home.displayPricipal();
		home.displayInterest();
	
	}
	
	@AfterTest
	public void closeDriver() 
	{
		
		BaseUI.quitDriver();
	}
	

}*/



package Test;

import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Excel.ReadExcel;
import Properties.SetProperties;
import WebPages.BaseUI;
import WebPages.HomePage;


public class MainTest extends BaseUI {
	
	Properties prop = SetProperties.getPropertiesFile();

	
	@BeforeTest
	public void initialize() throws InterruptedException 
	{
	BaseUI.createWebDriver(prop.getProperty("Browser"));
	}
	
	@Test(dataProvider = "thedata")
	public void Test(String amount, String rate, String time) throws Exception 
	{
		
		HomePage home = BaseUI.returnDriver();
		home.clickCarLoan();
		home.fillCarLoanAmount(amount);
		home.fillInterestRate(rate);
		home.fillLoanTenure(time);
		home.pressEnter();
		home.firstMonthClick();
		home.displayPricipal();
		home.displayInterest();
	}
	
	
	@DataProvider(name = "thedata")
	public String[][] data() throws Exception
	{
		return ReadExcel.ReadExcelSheet();
	}
	
	
	@AfterTest
	public void closeDriver() 
	{
		
		BaseUI.quitDriver();
	}
	

}

