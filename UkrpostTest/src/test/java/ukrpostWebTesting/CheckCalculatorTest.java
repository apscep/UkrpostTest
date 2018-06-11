package ukrpostWebTesting;
import objectRepository.CalculatorPage;
import objectRepository.MainPage;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import library.BrowsersSettings;
import library.Utility;
import static library.Utility.getProperty;
import static library.Utility.loadProperties;
public class CheckCalculatorTest  {
	
    @BeforeClass
    public static void setUp() {
    	loadProperties();
    }
   	WebDriver wd = BrowsersSettings.inizializeDriver();
   	
    //Page Obj pattern
	MainPage mp = new MainPage(wd);
	CalculatorPage cp = new CalculatorPage(wd);
	
   	@Test (description = "This test will check condition of web site")
	public void Loadsite () {
		wd.get(getProperty("mainUrl"));	
		String currentUrl = wd.getCurrentUrl();
		Assert.assertTrue(currentUrl.matches("^(http|https)://ukrposhta.ua/"));
		mp.calculatorId().click();
		String currentUrl2 = wd.getCurrentUrl();
		Assert.assertTrue(currentUrl2.matches("^(http|https)://a.ukrposhta.ua/calc/s/calc.html"));
	}
	
	@Test (dependsOnMethods="Loadsite", description = "This test will calculate express shipment From Kyiv to Lviv")
	public void CalculateShipment() {
		WebDriverWait wait = new WebDriverWait(wd, 8);
		//Select type of shipment
		Select typeSelect = new Select(cp.typeOfShipment());
		typeSelect.selectByValue("express");
		//Select sender's type
		Select departureTypeSelect = new Select(cp.typeOfDeparture());
		departureTypeSelect.selectByValue("physical");
		//Select destination of shipment
		Select destinationSelect = new Select(cp.typeOfDestination());
		destinationSelect.selectByValue("within_Ukraine");
		//Select delivery method
		Select deliveryMethodSelect = new Select(cp.typeOfDeliveryMethod());
		deliveryMethodSelect.selectByValue("storage-storage");
		cp.inputOfDeclaredValue().sendKeys("5");
		//Chose shipment weight in kg
		cp.inputOfMassKg().sendKeys("5");
		//Chose shipment weight in g
		cp.inputOfMassG().sendKeys("1");
		//Chose shipment length in cm
		cp.inputOfLenght().sendKeys("31");
		//Calculate shipment
		cp.submitButton().click();
		//Wait-check element presence
		wait.until(ExpectedConditions.visibilityOf(cp.resultDisplay()));
		Assert.assertTrue(cp.sumResultDisplay().isDisplayed());
		//Validate Shipment price Expected - 48
		Assert.assertEquals(cp.sumResultDisplay().getText(), "Загальна сума: 48 грн.");

		}
	 @AfterMethod
    	 public void takeScreenShotOnFailure(ITestResult testResult) throws IOException { 
		if (testResult.getStatus() == ITestResult.FAILURE) { 
		Utility.CaptureScreenshot(wd, "Calculator failed");	
			}
		}

	@AfterClass
		public void CloseBrowser() {
		wd.quit();
	}


}
