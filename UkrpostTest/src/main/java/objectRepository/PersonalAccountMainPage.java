package objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PersonalAccountMainPage {
	WebDriver  wd;	
		public PersonalAccountMainPage (WebDriver wd) {
			this.wd = wd; 
 }
 
 By header = By.xpath("//*[@id=\"main-wrap\"]/div[2]/div/div/div[2]/div[1]/h3");
 By logoutButton = By.xpath("//*[@id=\"main-wrap\"]/div[1]/div/ul/li[6]/a");
 By addGroupButton = By.xpath("//*[@id=\"main-wrap\"]/div[2]/div/div/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/button");
 By inputGroupName = By.cssSelector("input[name='shipmentgroupname']");
 By createGroupButton = By.xpath("//*[@id=\"main-wrap\"]/div[2]/div/div/div[2]/div[2]/div[1]/div[2]/div/div[3]/div/button");
 By createShipmentButton = By.xpath("//*[@id=\"main-wrap\"]/div[2]/div/div/div[2]/div[2]/div[1]/div[3]/div/div[2]/div/button");
 By registerShipmentHeader = By.xpath("//*[@id=\"main-wrap\"]/div[2]/div/div/div[2]/div[2]/div/form/fieldset/div[1]/div/h3");
 
 	public WebElement headerId() {
 		return wd.findElement(header);
      }
	
 	public WebElement logoutButtonId() {
		return wd.findElement(logoutButton);
	  }
	
 	public WebElement addGroupButton() {
		return wd.findElement(addGroupButton);
	  }
 	public WebElement inputGroupName() {
		return wd.findElement(inputGroupName);
	  }	
 	public WebElement createGroupButton() {
		return wd.findElement(createGroupButton);
	  }
	public WebElement createShipmentButton() {
		return wd.findElement(createShipmentButton);
	  }
	public WebElement registerShipmentHeader() {
		return wd.findElement(registerShipmentHeader);
	  }
}
