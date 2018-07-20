package StepDefinition;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import library.BrowsersSettings;
import library.Utility;
import objectRepository.LoginPage;
import objectRepository.MainPage;
import objectRepository.PersonalAccountMainPage;

public class enterPa {

	
 
    WebDriver wd = BrowsersSettings.inizializeDriver();
	//Page Obj pattern
    MainPage mp = new MainPage(wd);
    LoginPage lp = new LoginPage(wd);
    PersonalAccountMainPage pamp = new PersonalAccountMainPage(wd);
    
	@Given("^User go to the main page$")
	public void EnterPacheck ()
	{		
			wd.get(Utility.loadPropertiesCucumber().getProperty("mainUrl"));	
			String currentUrl = wd.getCurrentUrl();
			Assert.assertTrue(currentUrl.matches("^(http|https)://ukrposhta.ua/"));
			mp.personalAccountId().click();
			String currentUrl2 = wd.getCurrentUrl();
			Assert.assertTrue(currentUrl2.matches("^(http|https)://ukrposhta.ua/login/"));	
				
	}
	@When("^user input correct login and correct password in the password frame, click enter button$") 
	public void enterPaCorrectData () {
			lp.inputLoginId().sendKeys(Utility.loadPropertiesCucumber().getProperty("loginAbraam"));
			lp.inputPasswordId().sendKeys(Utility.loadPropertiesCucumber().getProperty("passwordAbraam"));
			lp.submitButtonId().click();
	}
	
	@Then("^user enter personal account")
	public void enteringAccount  () {
			pamp.headerId().getText().equals("Особистий кабінет");
	     	}
	
	@Then("^user logging out")
	public void loggingOut  () {
		pamp.logoutButtonId().click();
	     	}
	
	@When("^user input incorrect login  in the login frame, enter incorrect password in the password frame, click enter button$")
	public void enterPaIncorrectData ()  {
		mp.personalAccountId().click();
		String currentUrl2 = wd.getCurrentUrl();
		Assert.assertTrue(currentUrl2.matches("^(http|https)://ukrposhta.ua/login/"));	
		lp.inputLoginId().sendKeys(Utility.loadPropertiesCucumber().getProperty("loginAbraam"));
		lp.inputPasswordId().sendKeys(Utility.loadPropertiesCucumber().getProperty("passwordAbraamIncorrect"));
		lp.submitButtonId().click();
	   	}
		 
	@Then("^user can't enter personal account, got error message$")
	public void checkerrorMessage()  {
		lp.loginForm().getText().equals("Логін або пароль не вірні!");
	    ;
	}
	@Then("^close browser$")
	public void CloseBrowser()  {
		wd.quit();
	}
		
}
