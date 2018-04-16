package library;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeRunner {
	 public static WebDriver SetChromeDriver (WebDriver wd1) {
		    System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
	    	wd1 = new ChromeDriver();
	 		return wd1;	
	 }
	
	 public static void RunBrowser () {
		    System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
	    	WebDriver wd = new ChromeDriver();
	   		ChromeOptions chromeOptions = new ChromeOptions();
	   		chromeOptions.addArguments("--start-maximized");
	   		wd = new ChromeDriver(chromeOptions);
	   		wd.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
			
	 }
}
