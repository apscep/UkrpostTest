package ukrpostWebTesting;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import library.BrowsersSettings;
import library.Utility;
import objectRepository.LoginPage;
import objectRepository.MainPage;
public class EnterPaNegativeTest {
	
	WebDriver wd = BrowsersSettings.inizializeDriver();
	//Page Obj pattern
    MainPage mp = new MainPage(wd);
    LoginPage lp = new LoginPage(wd);
     	
	@Test (description = "This test will check condition of web site")
	public void Loadsite () {
		wd.get(Utility.getVariables().getProperty("mainUrl"));	
		String currentUrl = wd.getCurrentUrl();
		Assert.assertTrue(currentUrl.matches("^(http|https)://ukrposhta.ua/"));
		mp.personalAccountId().click();
		String currentUrl2 = wd.getCurrentUrl();
		Assert.assertTrue(currentUrl2.matches("^(http|https)://ukrposhta.ua/login/"));
		}
	
	@Test (dependsOnMethods="Loadsite", description = "This test will login personal account")
	public void LoginToPa()  {
		lp.inputLoginId().sendKeys(Utility.getVariables().getProperty("loginAbraam"));
		lp.inputPasswordId().sendKeys(Utility.getVariables().getProperty("passwordAbraamIncorrect"));
		lp.submitButtonId().click();
		lp.loginForm().getText().equals("Логін або пароль не вірні!");
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
