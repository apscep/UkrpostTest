package ukrpostTestingChrome;
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

import library.ChromeRunner;
import library.Utility;
import objectRepository.LoginPage;
import objectRepository.MainPage;
public class EnterPaMakeShipmentChromeTest {
	WebDriver wd = ChromeRunner.setChromeDriver();
	WebDriverWait wait = new WebDriverWait(wd, 10);
	String loginAbraam = Utility.getVariables().getProperty("loginAbraam");
	String passwordAbraam = Utility.getVariables().getProperty("passwordAbraam");
    String ukrpostUrl = Utility.getVariables().getProperty("mainUrl");
    MainPage mp = new MainPage(wd);
    LoginPage lp = new LoginPage(wd);
   
   	   	
	@Test (description = "This test will check condition of web site")
	public void Loadsite () {
		wd.get(ukrpostUrl);	
		mp.personalAccountId().click();
		String currentUrl = wd.getCurrentUrl();
		Assert.assertTrue(currentUrl.matches("^(http|https)://ukrposhta.ua/login/"));
	}
	
	@Test (dependsOnMethods="Loadsite", description = "This test will login personal account")
	public void LoginToPa()	{
		lp.inputLoginId().sendKeys(loginAbraam);
		lp.inputPasswordId().sendKeys(passwordAbraam);
		lp.submitButtonId().click();
		wd.findElement(By.xpath("//*[@id=\"main-wrap\"]/div[2]/div/div/div[2]/div[1]/h3")).getText().equals("Особистий кабінет");
	}
	
	@Test (dependsOnMethods="LoginToPa", description = "Test to create shipment Group")
	public void CreateShipmentGroup () throws InterruptedException {
		Thread.sleep(3000);
		wd.findElement(By.xpath("//*[@id=\"main-wrap\"]/div[2]/div/div/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/button")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='shipmentgroupname']")));
		wd.findElement(By.cssSelector("input[name='shipmentgroupname']")).sendKeys("FirstGroup");
		wd.findElement(By.xpath("//*[@id=\"main-wrap\"]/div[2]/div/div/div[2]/div[2]/div[1]/div[2]/div/div[3]/div/button")).click();
		wd.findElement(By.xpath("//*[@id=\"main-wrap\"]/div[2]/div/div/div[2]/div[2]/div[1]/div[3]/div/div[2]/div/button")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"main-wrap\"]/div[2]/div/div/div[2]/div[2]/div/form/fieldset/div[1]/div/h3")));
		wd.findElement(By.xpath("//*[@id=\"main-wrap\"]/div[2]/div/div/div[2]/div[2]/div/form/fieldset/div[1]/div/h3")).getText().equals("Реєстрація нового відправлення");
	}
	
	@Test (dependsOnMethods="CreateShipmentGroup", description = "Test to create shipment")
	public void CreateShipment () {
		WebDriverWait wait = new WebDriverWait(wd, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[id='dropOffPostcode']")));
		wd.findElement(By.cssSelector("input[id='dropOffPostcode']")).sendKeys("04080");
		wd.findElement(By.cssSelector("input[id='surname']")).sendKeys("Іванов");
		wd.findElement(By.cssSelector("input[id='name']")).sendKeys("Іван");
		wd.findElement(By.cssSelector("input[id='phone']")).sendKeys("633075463");
		Select dropdownDelType = new Select(wd.findElement(By.id("delivery-method")));
		dropdownDelType.selectByValue("D2D");
		Select dropdownRegion = new Select(wd.findElement(By.id("region")));
		dropdownRegion.selectByValue("Волинська");
		wd.findElement(By.cssSelector("input[id='street']")).sendKeys("Будівельників");
		wd.findElement(By.cssSelector("input[id='house']")).sendKeys("15");
		wd.findElement(By.cssSelector("input[id='city']")).sendKeys("Луцьк");
		wd.findElement(By.cssSelector("input[id='apartment']")).sendKeys("20");
		wd.findElement(By.cssSelector("input[id='post-index']")).sendKeys("43026");
		wd.findElement(By.cssSelector("input[id='weight']")).sendKeys("2000");
		wd.findElement(By.cssSelector("input[id='declared']")).sendKeys("500");
		wd.findElement(By.cssSelector("input[id='biggest-size']")).sendKeys("50");
		wd.findElement(By.cssSelector("input[id='postpay']")).sendKeys("60");
		//Check default radio button is selected
		Assert.assertTrue( wd.findElement(By.id("RETURN")).isSelected());
		wd.findElement(By.cssSelector("label[for='RETURN_AFTER_FREE_STORAGE']")).click();
		wd.findElement(By.cssSelector("label[for='recommended']")).click();
		wd.findElement(By.cssSelector("label[for='sms']")).click();
		//Check checkBox  is selected
		Assert.assertTrue( wd.findElement(By.id("recommended")).isSelected());
		Assert.assertTrue( wd.findElement(By.id("sms")).isSelected());
		wd.findElement(By.cssSelector("button[id='submit-button']")).click();
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
