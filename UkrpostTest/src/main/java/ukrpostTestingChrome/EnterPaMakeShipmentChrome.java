package ukrpostTestingChrome;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class EnterPaMakeShipmentChrome {
	WebDriver wd;
	String loginAbraam = "ukrpost@i.ua";
	String passwordAbraam = "446655";
    String ukrpostUrl = "http://ukrposhta.ua/";
    
   	@BeforeClass (description = "Start Browser")
    public void RunBrowser () {
   		System.setProperty("webdriver.chrome.driver", "C:\\dev\\Selenium\\chromedriver.exe");
   		ChromeOptions chromeOptions = new ChromeOptions();
   		chromeOptions.addArguments("--start-maximized");
   		wd = new ChromeDriver(chromeOptions);
   		wd.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
	}
   	
	@Test (description = "This test will check condition of web site")
	public void Loadsite () {
		wd.get(ukrpostUrl);	
		wd.findElement(By.xpath("//*[@id=\"main-wrap\"]/div[1]/div/ul/li[6]/a")).click();
		String currentUrl2 = wd.getCurrentUrl();
		Assert.assertEquals(currentUrl2, "https://ukrposhta.ua/login/");
	}
	
	@Test (dependsOnMethods="Loadsite", description = "This test will login personal account")
	public void LoginToPa()	{
		wd.findElement(By.xpath("//*[@id=\"login-form\"]/form/div[1]/div/input")).sendKeys(loginAbraam);
		wd.findElement(By.xpath(".//*[@id=\"login-form\"]/form/div[2]/div/input")).sendKeys(passwordAbraam);
		wd.findElement(By.xpath("//*[@id=\"login-submit\"]")).click();
		wd.findElement(By.xpath("//*[@id=\"main-wrap\"]/div[2]/div/div/div[2]/div[1]/h3")).getText().equals("��������� ������");
	}
	
	@Test (dependsOnMethods="LoginToPa", description = "Test to create shipment Group")
	public void CreateShipmentGroup () throws InterruptedException {
		Thread.sleep(3000);
		wd.findElement(By.xpath("//*[@id=\"main-wrap\"]/div[2]/div/div/div[2]/div[2]/div[1]/div[1]/div/div[2]/div/button")).click();
		Assert.assertTrue(wd.findElement(By.cssSelector("input[name='shipmentgroupname']")).isDisplayed());
		wd.findElement(By.cssSelector("input[name='shipmentgroupname']")).sendKeys("FirstGroup");
		wd.findElement(By.xpath("//*[@id=\"main-wrap\"]/div[2]/div/div/div[2]/div[2]/div[1]/div[1]/div/div[3]/div/button")).click();
		wd.findElement(By.xpath("//*[@id=\"main-wrap\"]/div[2]/div/div/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/button")).click();
		wd.findElement(By.xpath("//*[@id=\"main-wrap\"]/div[2]/div/div/div[2]/div[2]/div/form/fieldset/div[1]/div/h3")).getText().equals("��������� ������ �����������");
	}
	
	@Test (dependsOnMethods="CreateShipmentGroup", description = "Test to create shipment")
	public void CreateShipment () {
		wd.findElement(By.cssSelector("input[id='dropOffPostcode']")).sendKeys("04080");
		wd.findElement(By.cssSelector("input[id='surname']")).sendKeys("������");
		wd.findElement(By.cssSelector("input[id='name']")).sendKeys("����");
		wd.findElement(By.cssSelector("input[id='phone']")).sendKeys("633075463");
		Select dropdownDelType = new Select(wd.findElement(By.id("delivery-method")));
		dropdownDelType.selectByValue("D2D");
		Select dropdownRegion = new Select(wd.findElement(By.id("region")));
		dropdownRegion.selectByValue("���������");
		wd.findElement(By.cssSelector("input[id='street']")).sendKeys("�����������");
		wd.findElement(By.cssSelector("input[id='house']")).sendKeys("15");
		wd.findElement(By.cssSelector("input[id='city']")).sendKeys("�����");
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
		Assert.assertEquals(actualShipmentStatus, "��������");
		String actualShipmentPrice =  wd.findElement(By.xpath("//*[@class='modal fade ng-scope in']/div/div/div[2]/table/tbody/tr[10]/td/div/div")).getText();
		Assert.assertEquals(actualShipmentPrice, "63.9���., ������ 5% ���������");
	}
	
	@AfterClass
	public void CloseBrowser() {
		wd.quit();
	}
}