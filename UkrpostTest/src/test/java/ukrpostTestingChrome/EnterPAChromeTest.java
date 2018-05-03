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
public class EnterPAChromeTest  {
	
	WebDriver wd = ChromeRunner.setChromeDriver();
	String loginAbraam = Utility.getVariables().getProperty("loginAbraam");
	String passwordAbraam = Utility.getVariables().getProperty("passwordAbraam");
    String ukrpostUrl = Utility.getVariables().getProperty("mainUrl");
    //Page object patterns
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
	public void LoginToPa() {
		lp.inputLoginId().sendKeys(loginAbraam);
		lp.inputPasswordId().sendKeys(passwordAbraam);
		lp.submitButtonId().click();
		wd.findElement(By.xpath("//*[@id=\"main-wrap\"]/div[2]/div/div/div[2]/div[1]/h3")).getText().equals("Особистий кабінет");
	}
	
	@Test (dependsOnMethods="LoginToPa", description = "This test will log out from personal account")
	public void LogoutPa () {
		wd.findElement(By.xpath("//*[@id=\"main-wrap\"]/div[1]/div/ul/li[6]/a")).click();
	}
	 @AfterMethod 
	 public void takeScreenShotOnFailure(ITestResult testResult) throws IOException { 
		if (testResult.getStatus() == ITestResult.FAILURE) { 
		Utility.CaptureScreenshot(wd, "Entering personal account failed");	
			}
		}
	
	@AfterClass
	public void CloseBrowser() {
		wd.quit();
	}
}
