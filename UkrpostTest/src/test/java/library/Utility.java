package library;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
public class Utility {
WebDriver wd;

	@BeforeSuite
	public static Properties getVariables () {
  		Properties prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream("./src/test/java/library/Variables.properties");
			prop.load(fis);
 		    }  
		catch (Exception e) {
			System.out.println("Error during exception is"+e.getMessage());
		}
		return prop;
	}
	 
	public static void CaptureScreenshot (WebDriver wd, String screenshotName) 	{
       
			try {
			TakesScreenshot ts = (TakesScreenshot)wd;
			File source = ts.getScreenshotAs(OutputType.FILE);
			String time =  new SimpleDateFormat("yyyyMMddhhmmss'.txt'").format(new Date());
			FileUtils.copyFile(source, new File("./Screenshot/"+ screenshotName +time+".png"));
			System.out.println("Screnshot taken");
		    } 
		catch (Exception e) {
			System.out.println("Error during exception is"+e.getMessage());
		}	
	}
	

		
	}

