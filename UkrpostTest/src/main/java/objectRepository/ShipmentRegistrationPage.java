package objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShipmentRegistrationPage {
	WebDriver  wd;	
		public ShipmentRegistrationPage (WebDriver wd) {
			this.wd = wd; 
 }
 
 By inputDropOfPostcode = By.cssSelector("input[id='dropOffPostcode']");
 By inputSurName = By.cssSelector("input[id='surname']");	
 By inputName = By.cssSelector("input[id='name']");
 By inputPhone = By.cssSelector("input[id='phone']");
 By selectDeliveryMethod = By.id("delivery-method");
 By selectRegion = By.id("region");
 By inputCity = By.cssSelector("input[id='city']");	
 By inputStreet = By.cssSelector("input[id='street']");		
 By inputHouse = By.cssSelector("input[id='house']");	
 By inputApartment = By.cssSelector("input[id='apartment']");
 By inputIndex = By.cssSelector("input[id='post-index']");
 By inputWeight = By.cssSelector("input[id='weight']");
 By inputDeclaredPrice = By.cssSelector("input[id='declared']");
 By inputLenght = By.cssSelector("input[id='biggest-size']");
 By inputPostpay = By.cssSelector("input[id='postpay']");
 By radioButtonReturn = By.id("RETURN");
 By radioButtonAfterFreeStorage = By.cssSelector("label[for='RETURN_AFTER_FREE_STORAGE']");
 By radioButtonRecommended = By.cssSelector("label[for='recommended']");
 By radioButtonSms = By.cssSelector("label[for='sms']");
 By submitButton =  By.id("submit-button");

 
 public WebElement inputDropOfPostcode() {
	return wd.findElement(inputDropOfPostcode);
  	  }
 public WebElement inputSurName() {
		return wd.findElement(inputSurName);
	  }
  public WebElement inputName() {
		return wd.findElement(inputName);
	  }
 public WebElement inputPhone() {
		return wd.findElement(inputPhone);
	  }
 public WebElement selectDeliveryMethod() {
		return wd.findElement(selectDeliveryMethod);
	  }
 public WebElement selectRegion() {
		return wd.findElement(selectRegion);
	  }
 public WebElement inputStreet() {
		return wd.findElement(inputStreet);
	  }
 public WebElement inputHouse() {
		return wd.findElement(inputHouse);
	  }
 public WebElement inputCity() {
		return wd.findElement(inputCity);
 	  }
 public WebElement inputApartment() {
		return wd.findElement(inputApartment);
	  }
 public WebElement inputIndex() {
		return wd.findElement(inputIndex);
	  }
 public WebElement inputWeight() {
		return wd.findElement(inputWeight);
	  }
 public WebElement inputLenght() {
		return wd.findElement(inputLenght);
	  }
  public WebElement inputDeclaredPrice() {
		return wd.findElement(inputDeclaredPrice);
	  }
 public WebElement inputPostpay() {
		return wd.findElement(inputPostpay);
	  }
 public WebElement radioButtonReturn() {
		return wd.findElement(radioButtonReturn);
	  }
 public WebElement radioButtonRecommended() {
		return wd.findElement(radioButtonRecommended);
	  }
 public WebElement radioButtonSms() {
		return wd.findElement(radioButtonSms);
	  }
 public WebElement submitButton() {
		return wd.findElement(submitButton);
	  }
 public WebElement radioButtonAfterFreeStorage() {
		return wd.findElement(radioButtonAfterFreeStorage);
	  }
}
