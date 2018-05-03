package objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {
	WebDriver  wd;	
		public MainPage (WebDriver wd) {
			this.wd = wd; 
 }
 
 By calculator = By.xpath("//*[@id=\"main-wrap\"]/div[3]/div/div/div[1]/a[1]");	
 
 public WebElement calculatorId() {
	return wd.findElement(calculator);
  }
	
	
}
