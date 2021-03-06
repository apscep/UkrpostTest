package library;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeRunner {
	
	public static WebDriver  wd;
	public static WebDriver setChromeDriver () {
		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		ChromeOptions chromeOptions = new ChromeOptions();
	   	chromeOptions.addArguments("--start-maximized");
	   	wd = new ChromeDriver(chromeOptions);
	   	wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	   	wd.manage().deleteAllCookies();
		return wd;
	 	 }
	
}
