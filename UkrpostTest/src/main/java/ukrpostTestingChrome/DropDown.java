package ukrpostTestingChrome;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import library.Utility;
public class DropDown {
	WebDriver wd;
	String loginAbraam = "ukrpost@i.ua";
	String passwordAbraam = "446655";
	String ukrpostUrl = Utility.setVariables().getProperty("mainUrl");
   	@BeforeClass (description = "Start Browser")
   	
    public void RunBrowser () {
   		System.setProperty("webdriver.gecko.driver", "C:\\dev\\Selenium\\geckodriver.exe");
   		wd = new FirefoxDriver();
   		wd.manage().window().maximize();
   		wd.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
	}
   	
	@Test (description = "This test will check condition of web site")
		public void Loadsite ()	{
		wd.get(ukrpostUrl);	
		String currentUrl = wd.getCurrentUrl();
		Assert.assertEquals(currentUrl, "http://ukrposhta.ua/");
   	}
	
	@Test (dependsOnMethods="Loadsite", description = "This test will check dropdown page") 
		public void CheckElement () {
		Actions a = new Actions(wd);
		WebElement xpath1  = wd.findElement(By.xpath("//*[@id=\"main-wrap\"]/header/div/nav/ul/li[1]/a"));
		//WebElement xpath2  = wd.findElement(By.xpath("//*[@id=\"main-wrap\"]/header/div/nav/ul/li[1]/ul/li[5]/a"));
		a.moveToElement(xpath1).build().perform();
		WebDriverWait wait = new WebDriverWait(wd,5); 
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"main-wrap\"]/header/div/nav/ul/li[1]/ul/li[5]/a")));
		WebElement menuOption = wd.findElement(By.xpath("//*[@id=\"main-wrap\"]/header/div/nav/ul/li[1]/ul/li[5]/a"));
		menuOption.click();
		Assert.assertEquals("https://ukrposhta.ua/vakansii/", wd.getCurrentUrl());
	}
	 @AfterMethod 
	 public void takeScreenShotOnFailure(ITestResult testResult) throws IOException { 
		if (testResult.getStatus() == ITestResult.FAILURE) { 
		Utility.CaptureScreenshot(wd, "Dropdown failed");	
			}
		}
	
	@AfterClass
	public void CloseBrowser()
	{
		wd.quit();
	}
}
