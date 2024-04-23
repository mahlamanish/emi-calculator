package WebPages;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;

import Excel.WriteExcel;
//import org.openqa.selenium.support.PageFactory;
import Properties.SetProperties;
import SccreenShots.TakeScreenShot;

public class HomePage extends BaseUI{
	
	public HomePage(WebDriver driver){
		HomePage.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	Properties prop = SetProperties.getPropertiesFile() ;
	
	String principal = null;
	String Interest = null;
	
	//All the webelements are declared as variable to abide by POM
	WebElement carLoanLink = driver.findElement(By.xpath(prop.getProperty("carLoanLinkXpath")));
	WebElement carLoanAmountTextbox = driver.findElement(By.xpath(prop.getProperty("carLoanAmountXpath")));
	WebElement interestRateTextbox = driver.findElement(By.xpath(prop.getProperty("interestRateXpath")));
	WebElement loanTenureTextbox = driver.findElement(By.xpath(prop.getProperty("loanTenureXpath")));
	
	WebElement firstMonthPrincipal = null;
	WebElement firstMonthInterest = null;
	WebElement firstMonth = null;
	
	
	//Method to click on car Loan option in home page
	public void clickCarLoan() throws Exception 
	{
//		carLoanLink.click();
		carLoanLink = driver.findElement(By.xpath(prop.getProperty("carLoanLinkXpath")));
		 carLoanAmountTextbox = driver.findElement(By.xpath(prop.getProperty("carLoanAmountXpath")));
		 interestRateTextbox = driver.findElement(By.xpath(prop.getProperty("interestRateXpath")));
		 loanTenureTextbox = driver.findElement(By.xpath(prop.getProperty("loanTenureXpath")));
		carLoanLink.click();
		
		logger.log(Status.INFO, "Clicked on CarLoan Link");
	}
	
	//Method to first clear the field and then fill car loan amount in text box
	public void fillCarLoanAmount(String carLoanAmount) throws Exception 
	{
		carLoanAmountTextbox.click();
		carLoanAmountTextbox.clear();
		carLoanAmountTextbox.sendKeys(carLoanAmount);
		logger.log(Status.INFO, "Car Loan Amount Filled");
	}
	
	//Method to first select all and press backspace then  fill car Interest Rate in text box
	public void fillInterestRate(String interestRate) throws Exception 
	{
		interestRateTextbox.sendKeys(Keys.chord(Keys.CONTROL,"a"));
		Actions a = new Actions(driver);
		a.sendKeys(Keys.BACK_SPACE).build().perform();
		interestRateTextbox.sendKeys(interestRate);
		
		logger.log(Status.INFO, "Interest Rate Filled");
	}
	
	//Method to first select all and press backspace then  fill car loanTenure in text box
	public void fillLoanTenure(String loanTenure) throws Exception
	{
		loanTenureTextbox.sendKeys(Keys.chord(Keys.CONTROL,"a"));
		Actions a = new Actions(driver);
		a.sendKeys(Keys.BACK_SPACE).build().perform();
		loanTenureTextbox.sendKeys(loanTenure);
		
		logger.log(Status.INFO, "Loan Tenure Filled");
	}
	
	//Pressing enter to ensure that result is calculated
	public void pressEnter() throws IOException 
	{
		Actions a = new Actions(driver);
		a.sendKeys(Keys.ENTER).build().perform();
		
		logger.addScreenCaptureFromPath(TakeScreenShot.takeScreenshot(driver));
		logger.log(Status.INFO, "Final Result Calculated");	
	}
	
	public void firstMonthClick() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		firstMonth = driver.findElement(By.xpath(prop.getProperty("firstMonthXpath")));
		firstMonth.click();
		
	}
	
	

	//Method to display first Month principal 
	public void displayPricipal() throws Exception
	{
		
		firstMonthPrincipal = driver.findElement(By.xpath(prop.getProperty("firstMonthPrincipalXpath")));
		
		principal = firstMonthPrincipal.getText();
		
		System.out.println(principal);
	}
	
	//Method to display first Month Interest
	public void displayInterest() throws Exception
	{
		
		firstMonthInterest = driver.findElement(By.xpath(prop.getProperty("firstMonthInterestXpath")));
		Interest =firstMonthInterest.getText();
		
		System.out.println(Interest);
		
		writeToExcel();
		
	}
	
	public void writeToExcel() throws IOException {
		WriteExcel.setDataToExcel(principal, Interest);
	}
	
	
}
