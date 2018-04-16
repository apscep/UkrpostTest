package ukrpostTestingMozilla;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import library.Utility;
public class CheckCalculatorMozila {
	WebDriver wd;
	String ukrpostUrl = Utility.setVariables().getProperty("mainUrl");
    
  	@BeforeClass (description = "Start Browser")
  	public void RunBrowser () {
  		System.setProperty("webdriver.gecko.driver", "./Drivers/chromedriver.exe");
  		wd = new FirefoxDriver();
  		wd.manage().window().maximize();
  		wd.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
	}
  	
	@Test (description = "This test will check condition of web site")
	public void Loadsite () {
		wd.get(ukrpostUrl);	
		String currentUrl = wd.getCurrentUrl();
		Assert.assertEquals(currentUrl, "http://ukrposhta.ua/");
		wd.findElement(By.xpath("//*[@id=\"main-wrap\"]/div[3]/div/div/div[1]/a[1]")).click();
		String currentUrl2 = wd.getCurrentUrl();
		Assert.assertEquals(currentUrl2, "https://ukrposhta.ua/kalkulyator-forma-rozraxunku/");
	}
	
	@Test (dependsOnMethods="Loadsite", description = "This test will calculate express shipment From Kyiv to Lviv")
	public void CalculateShipment()  {
		WebDriverWait wait = new WebDriverWait(wd, 8);
		//Select type of shipment
		Select typeSelect = new Select(wd.findElement(By.cssSelector("select[name='type_of_departure']")));
		typeSelect.selectByValue("express");
		//Select sender's type
		Select departureTypeSelect = new Select(wd.findElement(By.cssSelector("select[name='departure_type']")));
		departureTypeSelect.selectByValue("physical");
		//Select destination of shipment
		Select destinationSelect = new Select(wd.findElement(By.cssSelector("select[name='destination']")));
		destinationSelect.selectByValue("within_Ukraine");
		//Select delivery method
		Select deliveryMethodSelect = new Select(wd.findElement(By.cssSelector("select[name='delivery_method']")));
		deliveryMethodSelect.selectByValue("storage-storage");
		wd.findElement(By.cssSelector("input[name='declared_value_grn']")).sendKeys("5");
		//Chose shipment weight in kg
		wd.findElement(By.cssSelector("input[name='mass_kg']")).sendKeys("5");
		//Chose shipment weight in g
		wd.findElement(By.cssSelector("input[name='mass_c']")).sendKeys("1");
		//Chose shipment lenght in cm
		wd.findElement(By.cssSelector("input[name='side']")).sendKeys("31");
		//Calculate shipment
		wd.findElement(By.xpath("//*[@id=\"col_2\"]/div[4]/div[7]/button")).click();
		//Wait-check element presence
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("result")));
		Assert.assertTrue(wd.findElement(By.id("result")).isDisplayed());
		//Validate Shipment price Expected - 48
		Assert.assertEquals(wd.findElement(By.xpath("//*[@id=\"sum_result\"]")).getText(), "Загальна сума: 48 грн.");
	}
	 @AfterMethod 
	 public void takeScreenShotOnFailure(ITestResult testResult) throws IOException { 
		if (testResult.getStatus() == ITestResult.FAILURE) { 
		Utility.CaptureScreenshot(wd, "Calculator failed");	
			}
		}
	@AfterClass 
	public void CloseBrowser() 	{
		wd.quit();
	}
}
