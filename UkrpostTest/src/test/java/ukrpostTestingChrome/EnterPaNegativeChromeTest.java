package ukrpostTestingChrome;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import library.ChromeRunner;
import library.Utility;
public class EnterPaNegativeChromeTest {
	
	WebDriver wd = ChromeRunner.setChromeDriver();
	String loginAbraam = Utility.setVariables().getProperty("loginAbraam");
	String passwordAbraam = Utility.setVariables().getProperty("passwordAbraamIncorrect");
    String ukrpostUrl = Utility.setVariables().getProperty("mainUrl");     
     	
	@Test (description = "This test will check condition of web site")
	public void Loadsite () {
		wd.get(ukrpostUrl);	
		String currentUrl = wd.getCurrentUrl();
		Assert.assertTrue(currentUrl.matches("^(http|https)://ukrposhta.ua/"));
		wd.findElement(By.xpath("//*[@id=\"main-wrap\"]/div[1]/div/ul/li[6]/a")).click();
		String currentUrl2 = wd.getCurrentUrl();
		Assert.assertTrue(currentUrl2.matches("^(http|https)://ukrposhta.ua/login/"));
		}
	
	@Test (dependsOnMethods="Loadsite", description = "This test will login personal account")
	public void LoginToPa()  {
		wd.findElement(By.xpath("//*[@id=\"login-form\"]/form/div[1]/div/input")).sendKeys(loginAbraam);
		wd.findElement(By.xpath(".//*[@id=\"login-form\"]/form/div[2]/div/input")).sendKeys(passwordAbraam);
		wd.findElement(By.xpath("//*[@id=\"login-submit\"]")).click();
		wd.findElement(By.xpath("//*[@id=\"login-form\"]/div")).getText().equals("Ћог≥н або пароль не в≥рн≥!");
	}
	 @AfterMethod 
	 public void takeScreenShotOnFailure(ITestResult testResult) throws IOException { 
		if (testResult.getStatus() == ITestResult.FAILURE) { 
		Utility.CaptureScreenshot(wd, "Negative entering personal account failed");	
			}
		}
	
	@AfterClass
	public void CloseBrowser() {
		wd.quit();
	}
}
