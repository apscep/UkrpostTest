package library;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class MozillaRunner {
	
	public static WebDriver  wd;
	public static WebDriver setMozillaDriver () {
		System.setProperty("webdriver.gecko.driver", "./Drivers/chromedriver.exe");
  		wd = new FirefoxDriver();
  		wd.manage().window().maximize();
  		wd.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		return wd;
	 	 }
	
}
