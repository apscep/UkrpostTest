package library;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;



public class Utility {

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

