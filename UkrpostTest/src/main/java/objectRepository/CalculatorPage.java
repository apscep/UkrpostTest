package objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CalculatorPage {
	public WebDriver  wd;	
		public CalculatorPage (WebDriver wd) {
			this.wd = wd; 
}	

 By typeOfShipment = By.cssSelector("select[name='type_of_departure']");
 By typeOfDeparture = By.cssSelector("select[name='departure_type']");	
 By typeOfDestination = By.cssSelector("select[name='destination']");
 By typeOfDeliveryMethod = By.cssSelector("select[name='delivery_method']");
 By inputOfDeclaredValue = By.cssSelector("input[name='declared_value_grn']");
 By inputOfMassKg = By.cssSelector("input[name='mass_kg']");
 By inputOfMassG = By.cssSelector("input[name='mass_c']");
 By inputOfLenght = By.cssSelector("input[name='side']");
 By submitButton = By.xpath("//*[@id=\"submit-button\"]");
 By resultDisplay = By.id("result");
 By sumResultDisplay = By.id("sum_result");
 
 public WebElement typeOfShipment() {
	return wd.findElement(typeOfShipment);
  }
	
 public WebElement typeOfDeparture() {
		return wd.findElement(typeOfDeparture);
	  }
	
 public WebElement typeOfDestination() {
		return wd.findElement(typeOfDestination);
	  }
 
 public WebElement typeOfDeliveryMethod() {
		return wd.findElement(typeOfDeliveryMethod);
	  }
 
 public WebElement inputOfMassKg() {
		return wd.findElement(inputOfMassKg);
	  }

 public WebElement inputOfMassG() {
		return wd.findElement(inputOfMassG);
	  }
 
 public WebElement inputOfLenght() {
		return wd.findElement(inputOfLenght);
	  }
 
 public WebElement inputOfDeclaredValue() {
		return wd.findElement(inputOfDeclaredValue);
	  }
 
 public WebElement submitButton() {
		return wd.findElement(submitButton);
	  }
 public WebElement sumResultDisplay() {
		return wd.findElement(sumResultDisplay);
	  }
 
 public WebElement resultDisplay() {
		return wd.findElement(resultDisplay);
	  }

}
