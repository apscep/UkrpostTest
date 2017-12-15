package ukrpostTesting;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class DropDown {
	WebDriver wd;
	String loginAbraam = "ukrpost@i.ua";
	String passwordAbraam = "446655";
    String ukrpostUrl = "http://ukrposhta.ua";
    
	@BeforeClass (description = "Start Browser")
    public void RunBrowser () {
	System.setProperty("webdriver.gecko.driver", "C:\\dev\\Selenium\\geckodriver.exe");
	wd = new FirefoxDriver();
	wd.manage().window().maximize();
		}
	@Test (description = "This test will check condition of web site")
	public void Loadsite () throws InterruptedException {
	wd.get(ukrpostUrl);	
	Thread.sleep(2000);
	String currentUrl = wd.getCurrentUrl();
	Assert.assertEquals(currentUrl, "http://ukrposhta.ua/");
	wd.findElement(By.xpath("//*[@id=\'main-wrap\']/header/div/nav/ul/li[5]/span")).click();
	Select oSelect = new Select(wd.findElement(By.xpath("//*[@id=\'main-wrap\']/header/div/nav/ul/li[5]/span")));
	oSelect.deselectByVisibleText("Укрпошта Експрес");

	Thread.sleep(1200);
		}
	@Test (dependsOnMethods="Loadsite", description = "This test will check dropdown page") 
	public void CheckElement () throws InterruptedException {
	String ExpectedText = wd.findElement(By.xpath("//*[@id=\"main-wrap\"]/div[2]/div[1]/h1")).getText();
	String ActualText = "Вінницька дирекція";
	Assert.assertEquals(ExpectedText, ActualText);
	}
	@AfterClass
	public void CloseBrowser(){
		wd.quit();
	}
}
