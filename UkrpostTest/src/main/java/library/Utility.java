package library;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;

public class Utility {

	@BeforeSuite 
	public static void setVariables ()
	{
		try {
	Properties prop = new Properties();	
	FileInputStream fis = new FileInputStream("./UkrpostTest/src/main/java/library/Variables.properties");
	prop.load(fis);
		  } 
		catch (Exception e) {
			System.out.println("Error during exception is"+e.getMessage());
	
	}
	
	}
	
	public static void CaptureScreenshot (WebDriver wd, String screenshotName) 
	{
		try {
			TakesScreenshot ts = (TakesScreenshot)wd;
			File source = ts.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source, new File("./Screenshot/"+ screenshotName+".png"));
			System.out.println("Screnshot taken");
		    } 
		catch (Exception e) {
			System.out.println("Error during exception is"+e.getMessage());
		}	
	}

	
		
	}
