package objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShipmentRegistrationPage {
	WebDriver  wd;	
		public ShipmentRegistrationPage (WebDriver wd) {
			this.wd = wd; 
 }
 
 By header = By.xpath("//*[@id=\"main-wrap\"]/div[2]/div/div/div[2]/div[1]/h3");
 By logoutButton = By.xpath("//*[@id=\"main-wrap\"]/div[1]/div/ul/li[6]/a");	
 
 public WebElement headerId() {
	return wd.findElement(header);
  }
	
 public WebElement logoutButtonId() {
		return wd.findElement(logoutButton);
	  }
	
}
