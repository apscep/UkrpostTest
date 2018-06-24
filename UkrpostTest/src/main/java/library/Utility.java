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


public class Utility {
	
	private static Properties properties = new Properties();
	WebDriver wd;
	
	public static Properties getVariables () {
		return properties;
	}
	
	public static String getProperty(String name) {
		return properties.getProperty(name);
	}
	
	public static void loadProperties() {
		try {
			FileInputStream fis = new FileInputStream("./src/main/java/library/Variables.properties");
			properties.load(fis);
			System.out.println("Properties is loaded");
 		    }  
		catch (Exception e) {
			System.out.println("Error during exception is"+e.getMessage());
		}
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
	
	public static Properties loadPropertiesCucumber() {
		try {
			FileInputStream fis = new FileInputStream("./src/main/java/library/Variables.properties");
			properties.load(fis);
			System.out.println("Properties is loaded");
 		    }  
		catch (Exception e) {
			System.out.println("Error during exception is"+e.getMessage());
			
		}
		return properties;
	}

		
	}

