package ukrpostTesting;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EnterPaMakeShipment {
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
	
	
	}
	@Test (description = "This test will check condition of web site")
	public void Loadsite () throws InterruptedException {
	wd.get(ukrpostUrl);	
	Thread.sleep(500);
	wd.findElement(By.xpath("//*[@id=\"main-wrap\"]/div[1]/div/ul/li[6]/a")).click();
	String currentUrl2 = wd.getCurrentUrl();
	Assert.assertEquals(currentUrl2, "https://ukrposhta.ua/login/");
	Thread.sleep(500);
		}
	
	@Test (dependsOnMethods="Loadsite", description = "This test will login personal account")
	public void LoginToPa() throws InterruptedException {
	wd.findElement(By.xpath("//*[@id=\"login-form\"]/form/div[1]/div/input")).sendKeys(loginAbraam);
	wd.findElement(By.xpath(".//*[@id=\"login-form\"]/form/div[2]/div/input")).sendKeys(passwordAbraam);
	Thread.sleep(500);
	wd.findElement(By.xpath("//*[@id=\"login-submit\"]")).click();
	Thread.sleep(2500);
	Assert.assertTrue(wd.findElement(By.xpath("//*[@id=\"main-wrap\"]/div[3]/div/div/div[2]/div[1]/h3")).isDisplayed());
	wd.findElement(By.xpath("//*[@id=\"main-wrap\"]/div[3]/div/div/div[2]/div[1]/h3")).getText().equals("Особистий кабінет");
	}
	@Test (dependsOnMethods="LoginToPa", description = "Test to create shipment Group")
	public void CreateShipmentGroup () throws InterruptedException {
	Thread.sleep(1000);	
	wd.findElement(By.xpath("//*[@id=\"creategroup\"]")).click();
	Thread.sleep(500);	
	wd.findElement(By.xpath("//*[@id=\"main-wrap\"]/div[3]/div/div/div[2]/div[2]/div[1]/div/div/input")).sendKeys("FirstGroup");
	wd.findElement(By.xpath("//*[@id=\"main-wrap\"]/div[3]/div/div/div[2]/div[2]/div[1]/div/div/button")).click();
	Thread.sleep(500);
	//Check warning text presence
	//wd.findElement(By.cssSelector("div[class='warning info ng-scope']")).getText().equals("Відправлення відсутні. Для початку роботи натисніть кнопку \"Створити відправлення\"");
	wd.findElement(By.xpath("//*[@id=\"main-wrap\"]/div[3]/div/div/div[2]/div[2]/div[1]/div[2]/button[2]")).click();
	Thread.sleep(1000);
	wd.findElement(By.xpath("//*[@id=\"main-wrap\"]/div[3]/div/div/div[2]/div[2]/div/form/fieldset/div[1]/div/h3")).getText().equals("Реєстрація нового відправлення");
	}
		
	@Test (dependsOnMethods="CreateShipmentGroup", description = "Test to create shipment")
	public void CreateShipment () throws InterruptedException {
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
	wd.findElement(By.cssSelector("label[for='RETURN']")).isSelected();
	wd.findElement(By.cssSelector("label[for='RETURN_AFTER_FREE_STORAGE']")).click();
	wd.findElement(By.cssSelector("label[for='recommended']")).click();
	wd.findElement(By.cssSelector("label[for='sms']")).click();
	//Check default radio button is selected
	wd.findElement(By.cssSelector("label[for='paidByRecipientFalse']")).isSelected();
	wd.findElement(By.cssSelector("button[id='submit-button']")).click();
	Thread.sleep(5000);
	}
	@Test (dependsOnMethods="CreateShipment", description = "Check shipment creation")
	public void CheckShipmentCreation () throws InterruptedException {
	wd.findElement(By.xpath("//*[@id=\"main-wrap\"]/div[3]/div/div/div[2]/div[2]/div[2]/div/div[1]/table/tbody/tr/td[3]")).getText().equals("Створене");
	}
	@AfterClass
	public void CloseBrowser(){
	wd.quit();
	}
}
