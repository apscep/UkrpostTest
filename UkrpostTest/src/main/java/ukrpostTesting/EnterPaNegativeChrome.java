package ukrpostTesting;

import java.util.concurrent.TimeUnit;

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
	wd.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
	}
	@Test (description = "This test will check condition of web site")
	public void Loadsite () {
	wd.get(ukrpostUrl);	
	String currentUrl = wd.getCurrentUrl();
	Assert.assertEquals(currentUrl, "http://ukrposhta.ua/");
	wd.findElement(By.xpath("//*[@id=\"main-wrap\"]/div[1]/div/ul/li[6]/a")).click();
	String currentUrl2 = wd.getCurrentUrl();
	Assert.assertEquals(currentUrl2, "https://ukrposhta.ua/login/");
	}
	@Test (dependsOnMethods="Loadsite", description = "This test will login personal account")
	public void LoginToPa()  {
	wd.findElement(By.xpath("//*[@id=\"login-form\"]/form/div[1]/div/input")).sendKeys(loginAbraam);
	wd.findElement(By.xpath(".//*[@id=\"login-form\"]/form/div[2]/div/input")).sendKeys(passwordAbraam);
	wd.findElement(By.xpath("//*[@id=\"login-submit\"]")).click();
	wd.findElement(By.xpath("//*[@id=\"login-form\"]/div")).getText().equals("Ћог≥н або пароль не в≥рн≥!");
	}
	@AfterClass
	public void CloseBrowser(){
		wd.quit();
	}
}
