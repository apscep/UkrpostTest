package ukrpostTesting;

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
		}
	
	@Test (description = "This test will check condition of web site")
	public void Loadsite () throws InterruptedException {
	wd.get(ukrpostUrl);	
	Thread.sleep(1000);
	String currentUrl = wd.getCurrentUrl();
	Assert.assertEquals(currentUrl, "http://ukrposhta.ua/");
	Thread.sleep(500);
	wd.findElement(By.xpath("//*[@id=\"main-wrap\"]/div[1]/div/ul/li[6]/a")).click();
		String currentUrl2 = wd.getCurrentUrl();
	Assert.assertEquals(currentUrl2, "http://ukrposhta.ua/login");
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
	@Test (dependsOnMethods="LoginToPa", description = "This test will log out from personal account")
	public void LogoutPa () throws InterruptedException {
	Thread.sleep(1000);	
	wd.findElement(By.xpath("//*[@id=\"main-wrap\"]/div[1]/div/ul/li[6]/a")).click();
	
	}
	

	
	@AfterClass
	public void CloseBrowser(){
		wd.quit();
	}
}
