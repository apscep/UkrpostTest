package library;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowsersSettings {
	public static  WebDriver  wd;
	public static WebDriver inizializeDriver () {
  		Properties prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream("./src/main/java/library/Browser.properties");
			prop.load(fis);
			String browserName = prop.getProperty("browser");
			if(browserName.equals("chrome")) {
				System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
				ChromeOptions chromeOptions = new ChromeOptions();
			   	chromeOptions.addArguments("--start-maximized");
			   	chromeOptions.addArguments("--disable-notifications");
			   	wd = new ChromeDriver(chromeOptions);
			   	wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			   	wd.manage().deleteAllCookies();
			   	}
			else if (browserName.equals("mozila")) {
				System.setProperty("webdriver.gecko.driver", "./Drivers/geckodriver.exe");
			    wd = new FirefoxDriver();
		   		wd.manage().window().maximize();
		   		wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		   		wd.manage().deleteAllCookies();
		   		}
 		    }  
		catch (Exception e) {
			System.out.println("Error during exception is"+e.getMessage());
		}
		return wd;
	}
}

