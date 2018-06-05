package ukrpostWebTesting;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import library.BrowsersSettings;
import library.Utility;
import objectRepository.LoginPage;
import objectRepository.MainPage;
import objectRepository.PersonalAccountMainPage;
import objectRepository.ShipmentRegistrationPage;
public class EnterPaMakeShipmentTest {
	WebDriver wd = BrowsersSettings.inizializeDriver();
	WebDriverWait wait = new WebDriverWait(wd, 10);
	   
  
    MainPage mp = new MainPage(wd);
    LoginPage lp = new LoginPage(wd);
    PersonalAccountMainPage pamp = new PersonalAccountMainPage(wd);
    ShipmentRegistrationPage srp = new ShipmentRegistrationPage(wd);
   
	@Test (description = "This test will check condition of web site")
	public void Loadsite () {
		wd.get(Utility.getVariables().getProperty("mainUrl"));	
		mp.personalAccountId().click();
		String currentUrl = wd.getCurrentUrl();
		Assert.assertTrue(currentUrl.matches("^(http|https)://ukrposhta.ua/login/"));
	}
	
	@Test (dependsOnMethods="Loadsite", description = "This test will login personal account")
	public void LoginToPa()	{
		lp.inputLoginId().sendKeys(Utility.getVariables().getProperty("loginAbraam"));
		lp.inputPasswordId().sendKeys(Utility.getVariables().getProperty("passwordAbraam"));
		lp.submitButtonId().click();
		pamp.headerId().getText().equals("Особистий кабінет");
	}
	
	@Test (dependsOnMethods="LoginToPa", description = "Test to create shipment Group")
	public void CreateShipmentGroup () throws InterruptedException {
		Thread.sleep(3000);
		pamp.addGroupButton().click();
		wait.until(ExpectedConditions.visibilityOf(pamp.inputGroupName()));
		pamp.inputGroupName().sendKeys("FirstGroup");
		pamp.createGroupButton().click();
		pamp.createShipmentButton().click();
		wait.until(ExpectedConditions.visibilityOf(pamp.registerShipmentHeader()));
		pamp.registerShipmentHeader().getText().equals("Реєстрація нового відправлення");
	}

	@Test (dependsOnMethods="CreateShipmentGroup", description = "Test to create shipment")
	public void CreateShipment () {
		wait.until(ExpectedConditions.elementToBeClickable(srp.inputDropOfPostcode()));
		srp.inputDropOfPostcode().sendKeys(Utility.getVariables().getProperty("dropOfPostcode"));
		srp.inputSurName().sendKeys(Utility.getVariables().getProperty("recipientSurname"));
		srp.inputName().sendKeys(Utility.getVariables().getProperty("recipientName"));
		srp.inputPhone().sendKeys(Utility.getVariables().getProperty("recipientPhone"));
		Select dropdownDelType = new Select(srp.selectDeliveryMethod());
		dropdownDelType.selectByValue(Utility.getVariables().getProperty("deliveryType"));
		Select dropdownRegion = new Select(srp.selectRegion());
		dropdownRegion.selectByValue("Херсонська");
		srp.inputStreet().sendKeys(Utility.getVariables().getProperty("recipientStreet"));
		srp.inputHouse().sendKeys(Utility.getVariables().getProperty("recipientHouse"));
	    srp.inputCity().sendKeys(Utility.getVariables().getProperty("recipientCity"));
	    srp.inputApartment().sendKeys(Utility.getVariables().getProperty("apartamentNumber"));
	    srp.inputIndex().sendKeys(Utility.getVariables().getProperty("recipientIndex"));
		srp.inputWeight().sendKeys(Utility.getVariables().getProperty("shipmentWeight"));
		srp.inputDeclaredPrice().sendKeys(Utility.getVariables().getProperty("declaredPriceSum"));
        srp.inputLenght().sendKeys(Utility.getVariables().getProperty("shipmentLenght"));
	    srp.inputPostpay().sendKeys(Utility.getVariables().getProperty("postPaySum"));
		//Check default radio button is selected
		Assert.assertTrue(srp.radioButtonReturn().isSelected());
		srp.radioButtonAfterFreeStorage().click();
		srp.radioButtonRecommended().click();
		srp.radioButtonSms().click();
		srp.submitButton().click();
	}
	
	@Test (dependsOnMethods="CreateShipment", description = "Test to check crreated shipment data")
	public void CheckShipmentData () {
		wd.findElement(By.xpath("//*[@id=\"main-wrap\"]/div[2]/div/div/div[2]/div[2]/div[2]/div/div[1]/table/tbody/tr/td[7]/button/i")).click();
		String actualShipmentStatus =  wd.findElement(By.xpath("//*[@class='modal fade ng-scope in']/div/div/div[2]/table/tbody/tr[2]/td")).getText();
		// Check shipment status and price
		Assert.assertEquals(actualShipmentStatus, "Створене");
		String actualShipmentPrice =  wd.findElement(By.xpath("//*[@class='modal fade ng-scope in']/div/div/div[2]/table/tbody/tr[10]/td/div/div")).getText();
		Assert.assertEquals(actualShipmentPrice, "63.9 грн.");
	}
	 @AfterMethod 
	 public void takeScreenShotOnFailure(ITestResult testResult) throws IOException { 
		if (testResult.getStatus() == ITestResult.FAILURE) { 
		Utility.CaptureScreenshot(wd, "Registering shipment failed");	
			}
		}
	
	@AfterClass
	public void CloseBrowser() {
		wd.quit();
	}
}
