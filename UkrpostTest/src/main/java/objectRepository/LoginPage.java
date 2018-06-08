package objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	WebDriver  wd;	
		public LoginPage (WebDriver wd) {
			this.wd = wd; 
 }
 
 By inputLogin = By.xpath("//*[@id=\"login-form\"]/form/div[1]/div/input");
 By inputPassword = By.xpath(".//*[@id=\"login-form\"]/form/div[2]/div/input");	
 By submitButton = By.id("login-submit");
 By loginForm = By.xpath("//*[@id=\"login-form\"]/div");
 
 public WebElement inputLoginId() {
	return wd.findElement(inputLogin);
  }
	
 public WebElement inputPasswordId() {
		return wd.findElement(inputPassword);
	  }
 
 public WebElement submitButtonId() {
		return wd.findElement(submitButton);
	  }
	
 public WebElement loginForm() {
		return wd.findElement(loginForm);
	  }
}
