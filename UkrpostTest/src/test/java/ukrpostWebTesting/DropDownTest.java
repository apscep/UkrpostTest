package ukrpostWebTesting;
import static library.Utility.loadProperties;
import static library.Utility.getProperty;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import library.BrowsersSettings;
import library.Utility;
import objectRepository.MainPage;
public class DropDownTest {
   
	@BeforeClass
    public static void setUp() {
    	loadProperties();
    }
	
	WebDriver wd = BrowsersSettings.inizializeDriver();
    //Page Obj pattern
	MainPage mp = new MainPage(wd);
    
	@Test (description = "This test will check condition of web site")
		public void Loadsite ()	{
		wd.get(getProperty("mainUrl"));	
		String currentUrl = wd.getCurrentUrl();
		Assert.assertTrue(currentUrl.matches("^(http|https)://ukrposhta.ua/"));
   	}
	
	@Test (dependsOnMethods="Loadsite", description = "This test will check dropdown page") 
		public void CheckElement () {
		WebDriverWait wait = new WebDriverWait(wd,5); 
		Actions a = new Actions(wd);
		WebElement xpath1  = mp.header();
		a.moveToElement(xpath1).build().perform();
		wait.until(ExpectedConditions.visibilityOf(mp.dropDownVacancy()));
		WebElement menuOption = mp.dropDownVacancy();
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
