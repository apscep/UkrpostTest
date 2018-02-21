package ukrpostTesting;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EnterPaFirefox {
	WebDriver wd;
	String loginAbraam = "ukrpost@i.ua";
	String passwordAbraam = "446655";
    String ukrpostUrl = "http://ukrposhta.ua/";
    
	@BeforeClass (description = "Start Browser")
    public void RunBrowser () {
	System.setProperty("webdriver.gecko.driver", "C:\\dev\\Selenium\\geckodriver.exe");
	wd = new FirefoxDriver();
	wd.manage().window().maximize();
	wd.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
	}
	@Test (description = "This test will check condition of web site")
	public void Loadsite ()  {
	wd.get(ukrpostUrl);	
	String currentUrl = wd.getCurrentUrl();
	Assert.assertEquals(currentUrl, "http://ukrposhta.ua/");
	wd.findElement(By.xpath("//*[@id=\"main-wrap\"]/div[1]/div/ul/li[6]/a")).click();
	String currentUrl2 = wd.getCurrentUrl();
	Assert.assertEquals(currentUrl2, "https://ukrposhta.ua/login/");
	}
	@Test (dependsOnMethods="Loadsite", description = "This test will login personal account")
	public void LoginToPa() {
	wd.findElement(By.xpath("//*[@id=\"login-form\"]/form/div[1]/div/input")).sendKeys(loginAbraam);
	wd.findElement(By.xpath(".//*[@id=\"login-form\"]/form/div[2]/div/input")).sendKeys(passwordAbraam);
	wd.findElement(By.xpath("//*[@id=\"login-submit\"]")).click();
	wd.findElement(By.xpath("//*[@id=\"main-wrap\"]/div[2]/div/div/div[2]/div[1]/h3")).getText().equals("Особистий кабінет");
	}
	@Test (dependsOnMethods="LoginToPa", description = "This test will log out from personal account")
	public void LogoutPa () {
	wd.findElement(By.xpath("//*[@id=\"main-wrap\"]/div[1]/div/ul/li[6]/a")).click();
	}
	@AfterClass
	public void CloseBrowser(){
		wd.quit();
	}
}
