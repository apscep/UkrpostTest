package StepDefinition;


import static library.Utility.loadProperties;

import org.junit.runner.RunWith;
import org.testng.annotations.BeforeClass;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(features="src/main/java/featureRepositories/personalAccount.feature",glue={"StepDefinition"})

public class testRunner {
    @BeforeClass
    public static void setUp() {
    	loadProperties();
    }	

	
	
	
	
}
