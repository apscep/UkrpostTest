package StepDefinition;

import static library.Utility.loadProperties;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

import library.BrowsersSettings;
import library.Utility;

public class findTextOnFacebook {

	 @BeforeClass
	    public static void setUp() {
	    	loadProperties();
	    }
	   	WebDriver wd = BrowsersSettings.inizializeDriver();
	    JavascriptExecutor js = (JavascriptExecutor) wd;
	    WebDriverWait wait = new WebDriverWait(wd, 10);
	    
	    @Test
	    public void FindElementByText() throws InterruptedException {
	    	wd.get("https://www.facebook.com");
	    	wd.findElement(By.id("email")).sendKeys("apscep@gmail.com");
	    	wd.findElement(By.id("pass")).sendKeys("aps335544");
	    	wd.findElement(By.cssSelector("input[value='Вход']")).click();
	    	Thread.sleep(500);
	 	    wd.get("https://www.facebook.com/urnla");
	 	   WebElement textElement=wd.findElement(By.id("recent_capsule_container"));
	 	   String text=textElement.getText();
	 	   JavascriptExecutor Scrool = (JavascriptExecutor) wd;
	 	   for(int i=0 ;i<999999;i++) {
	 	   Scrool.executeScript("window.scrollBy(0,99999)", "");
	 	   if (text.contains("цена на нефть")) {
	 		  System.out.println("Text obtained is"+text);
	 		  Utility.CaptureScreenshot(wd, "Novikov");
	 		  
	 		  
	 	   }
	 	   
	 	   }
	 	    }
	    	
	    	
	    }
	    
	
	    

