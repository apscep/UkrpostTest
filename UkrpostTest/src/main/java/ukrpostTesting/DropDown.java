package ukrpostTesting;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
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
	wd.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		}
	@Test (description = "This test will check condition of web site")
	public void Loadsite () {
	wd.get(ukrpostUrl);	
	String currentUrl = wd.getCurrentUrl();
	Assert.assertEquals(currentUrl, "http://ukrposhta.ua/");
    Actions a = new Actions(wd);
    a.moveToElement(wd.findElement(By.xpath("//*[@id=\"main-wrap\"]/header/div/nav/ul/li[1]/a"))).build().perform();
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
