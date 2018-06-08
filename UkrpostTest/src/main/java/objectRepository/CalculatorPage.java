package objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CalculatorPage {
	public WebDriver  wd;	
	

 By typeOfShipment = By.cssSelector("select[name='type_of_departure']");
 By typeOfDeparture = By.cssSelector("select[name='departure_type']");	
 
 public WebElement typeOfShipment() {
	return wd.findElement(typeOfShipment);
  }
	
 public WebElement typeOfDeparture() {
		return wd.findElement(typeOfDeparture);
	  }
	
	public CalculatorPage (WebDriver wd) {
		this.wd = wd; 
}

}
