package CucumberTests;

import static library.Utility.getProperty;
import static library.Utility.loadProperties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import cucumber.api.java.en.Given;
import library.BrowsersSettings;
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
    
	@Given("^ User has ([^\"]*)$")
	public void EnterPacheck (String str)
	{
		if(str.equals("correct"))
		{
			wd.get(getProperty("mainUrl"));	
			String currentUrl = wd.getCurrentUrl();
			Assert.assertTrue(currentUrl.matches("^(http|https)://ukrposhta.ua/"));
			mp.personalAccountId().click();
			String currentUrl2 = wd.getCurrentUrl();
			Assert.assertTrue(currentUrl2.matches("^(http|https)://ukrposhta.ua/login/"));	
		}
		
		if(str.equals("correct"))
		{
			wd.get(getProperty("mainUrl"));	
			String currentUrl = wd.getCurrentUrl();
			Assert.assertTrue(currentUrl.matches("^(http|https)://ukrposhta.ua/"));
			mp.personalAccountId().click();
			String currentUrl2 = wd.getCurrentUrl();
			Assert.assertTrue(currentUrl2.matches("^(http|https)://ukrposhta.ua/login/"));	
		}
	}
	
}
