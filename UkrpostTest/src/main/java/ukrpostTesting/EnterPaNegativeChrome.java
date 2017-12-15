package ukrpostTesting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EnterPaNegativeChrome {
	WebDriver wd;
	String loginAbraam = "ukrpost@i.ua";
	String passwordAbraam = "446652";
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
	wd.findElement(By.xpath("//*[@id=\"login-form\"]/div")).getText().equals("Ћог≥н або пароль не в≥рн≥!");
	}
	
	
	@AfterClass
	public void CloseBrowser(){
		wd.quit();
	}
}
