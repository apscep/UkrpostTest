package objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CreatedShipmentPage {
	WebDriver  wd;	
		public CreatedShipmentPage (WebDriver wd) {
			this.wd = wd; 
 }
 
 By createdShipmentButton = By.xpath("//*[@id=\"main-wrap\"]/div[2]/div/div/div[2]/div[2]/div[2]/div/div[1]/table/tbody/tr/td[7]/button/i");
 By shipmentStatus = By.xpath("//*[@class='modal fade ng-scope in']/div/div/div[2]/table/tbody/tr[2]/td");	
 By shipmentPrice = By.xpath("//*[@class='modal fade ng-scope in']/div/div/div[2]/table/tbody/tr[10]/td/div/div");	


 
 public WebElement createdShipmentButton() {
	return wd.findElement(createdShipmentButton);
  	  }
 public WebElement shipmentStatus() {
		return wd.findElement(shipmentStatus);
	  }
  public WebElement shipmentPrice() {
		return wd.findElement(shipmentPrice);
	  }

}
