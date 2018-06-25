package StepDefinition;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;



@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/main/java/featureRepositories/personalAccount.feature",
		glue={"StepDefinition"},
		plugin={ "pretty", "html:target/cucumber-reports" })

public class testRunner {
	

}

	
	
	


