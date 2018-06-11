package ukrpostWebTesting;
import static library.Utility.getProperty;
import static library.Utility.loadProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import library.BrowsersSettings;
import objectRepository.LoginPage;
import objectRepository.MainPage;
import objectRepository.PersonalAccountMainPage;
import objectRepository.ShipmentRegistrationPage;
public class EnterPaFillShipmentDataTest {

    @BeforeClass
    public static void setUp() {
    	loadProperties();
    }
    
	WebDriver wd = BrowsersSettings.inizializeDriver();
	WebDriverWait wait = new WebDriverWait(wd, 10);
	   
    MainPage mp = new MainPage(wd);
    LoginPage lp = new LoginPage(wd);
    //Page Obj pattern
    PersonalAccountMainPage pamp = new PersonalAccountMainPage(wd);
    ShipmentRegistrationPage srp = new ShipmentRegistrationPage(wd);
   

     
	@Test (description = "This test will check condition of web site")
	public void Loadsite () {
		wd.get(getProperty("mainUrl"));	
		mp.personalAccountId().click();
		String currentUrl = wd.getCurrentUrl();
		Assert.assertTrue(currentUrl.matches("^(http|https)://ukrposhta.ua/login/"));
	}
	
	@Test (dependsOnMethods="Loadsite", description = "This test will login personal account")
	public void LoginToPa()	{
		lp.inputLoginId().sendKeys(getProperty("loginAbraam"));
		lp.inputPasswordId().sendKeys(getProperty("passwordAbraam"));
		lp.submitButtonId().click();
		pamp.headerId().getText().equals("Особистий кабінет");
	}
	
	@Test (dependsOnMethods="LoginToPa", description = "Test to create shipment Group")
	public void CreateShipmentGroup () throws InterruptedException {
		Thread.sleep(3000);
		pamp.addGroupButton().click();
		wait.until(ExpectedConditions.visibilityOf(pamp.inputGroupName()));
		pamp.inputGroupName().sendKeys("FirstGroup");
		pamp.createGroupButton().click();
		pamp.createShipmentButton().click();
		wait.until(ExpectedConditions.visibilityOf(pamp.registerShipmentHeader()));
		pamp.registerShipmentHeader().getText().equals("Реєстрація нового відправлення");
	}

	@Test (dependsOnMethods="CreateShipmentGroup", description = "Test to create shipment")
	public void CreateShipment () {
		wait.until(ExpectedConditions.elementToBeClickable(srp.inputDropOfPostcode()));
		srp.inputDropOfPostcode().sendKeys(getProperty("dropOfPostcode"));
		srp.inputSurName().sendKeys(getProperty("recipientSurname"));
		srp.inputName().sendKeys(getProperty("recipientName"));
		srp.inputPhone().sendKeys(getProperty("recipientPhone"));
		Select dropdownDelType = new Select(srp.selectDeliveryMethod());
		dropdownDelType.selectByValue(getProperty("deliveryType"));
		Select dropdownRegion = new Select(srp.selectRegion());
		dropdownRegion.selectByValue("Херсонська");
		srp.inputStreet().sendKeys(getProperty("recipientStreet"));
		srp.inputHouse().sendKeys(getProperty("recipientHouse"));
	    srp.inputCity().sendKeys(getProperty("recipientCity"));
	    srp.inputApartment().sendKeys(getProperty("apartamentNumber"));
	    srp.inputIndex().sendKeys(getProperty("recipientIndex"));
		srp.inputWeight().sendKeys(getProperty("shipmentWeight"));
		srp.inputDeclaredPrice().sendKeys(getProperty("declaredPriceSum"));
        srp.inputLenght().sendKeys(getProperty("shipmentLenght"));
	    srp.inputPostpay().sendKeys(getProperty("postPaySum"));
		//Check default radio button is selected
		Assert.assertTrue(srp.radioButtonReturn().isSelected());
		srp.radioButtonAfterFreeStorage().click();
		srp.radioButtonRecommended().click();
		srp.radioButtonSms().click();

	}

	}
	

