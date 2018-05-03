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
import objectRepository.LoginPage;
import objectRepository.MainPage;
public class EnterPaNegativeChromeTest {
	
	WebDriver wd = ChromeRunner.setChromeDriver();
	String loginAbraam = Utility.getVariables().getProperty("loginAbraam");
	String passwordAbraam = Utility.getVariables().getProperty("passwordAbraamIncorrect");
    String ukrpostUrl = Utility.getVariables().getProperty("mainUrl"); 
    MainPage mp = new MainPage(wd);
    LoginPage lp = new LoginPage(wd);
     	
	@Test (description = "This test will check condition of web site")
	public void Loadsite () {
		wd.get(ukrpostUrl);	
		String currentUrl = wd.getCurrentUrl();
		Assert.assertTrue(currentUrl.matches("^(http|https)://ukrposhta.ua/"));
		mp.personalAccountId().click();
		String currentUrl2 = wd.getCurrentUrl();
		Assert.assertTrue(currentUrl2.matches("^(http|https)://ukrposhta.ua/login/"));
		}
	
	@Test (dependsOnMethods="Loadsite", description = "This test will login personal account")
	public void LoginToPa()  {
		lp.inputLoginId().sendKeys(loginAbraam);
		lp.inputPasswordId().sendKeys(passwordAbraam);
		lp.submitButtonId().click();
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
