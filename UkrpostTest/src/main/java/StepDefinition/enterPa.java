package StepDefinition;


import static library.Utility.loadProperties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import library.BrowsersSettings;
import library.Utility;
import objectRepository.LoginPage;
import objectRepository.MainPage;
import objectRepository.PersonalAccountMainPage;

public class enterPa {

    @BeforeClass
    public static void setUp() {
    	loadProperties();
    }	

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
	@When("^user input ([^\"]*)$") 
	public void enterLogin (String whenCond) {
		if(whenCond.equals("correct")) {
			lp.inputLoginId().sendKeys(Utility.loadPropertiesCucumber().getProperty("loginAbraam"));
			lp.inputPasswordId().sendKeys(Utility.loadPropertiesCucumber().getProperty("passwordAbraam"));
			lp.submitButtonId().click();
			
			
		}
		if (whenCond.equals("incorrect")) {
			lp.inputLoginId().sendKeys(Utility.loadPropertiesCucumber().getProperty("loginAbraam"));
			lp.inputPasswordId().sendKeys(Utility.loadPropertiesCucumber().getProperty("passwordAbraamIncorrect"));
			lp.submitButtonId().click();
			
		}
	}
	@Then("^user  ([^\"]*)$")
	public void checkEntering  (String thenCond) {
		if(thenCond.equals("enter")) {
			pamp.headerId().getText().equals("Особистий кабінет");
	}
		if (thenCond.equals("user can't")) {
			lp.loginForm().getText().equals("Логін або пароль не вірні!");
			}
		}

	
		@AfterClass
			public void CloseBrowser() {
				wd.quit();
	}
	
}
