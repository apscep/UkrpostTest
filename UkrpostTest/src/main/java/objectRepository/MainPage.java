package objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {
	public WebDriver  wd;
		public MainPage (WebDriver wd) {
			this.wd = wd; 
}

 By calculator = By.xpath("//*[@id=\"main-wrap\"]/div[3]/div/div/div[1]/a[1]");
 By personalAccount = By.xpath("//*[@id=\"main-wrap\"]/div[1]/div/ul/li[6]/a");
 By header = By.xpath("//*[@id=\"main-wrap\"]/header/div/nav/ul/li[1]/a");
 By dropDownVacancy = By.xpath("//*[@id=\"main-wrap\"]/header/div/nav/ul/li[1]/ul/li[5]/a");
 
 public WebElement calculatorId() {
	return wd.findElement(calculator);
  }
	
 public WebElement personalAccountId() {
		return wd.findElement(personalAccount);
	  }
 public WebElement header() {
		return wd.findElement(header);
	  }
	
 public WebElement dropDownVacancy() {
		return wd.findElement(dropDownVacancy);
	  }


}
