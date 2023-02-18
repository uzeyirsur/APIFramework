package cucumber.Options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

//To test with maven in cmd use mvn test -Dcucumber.options="--tags @AddPlace etc."
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features", plugin = "json:target/jsonReports/cucumber-report.json", glue = {"stepDefinitions"})
public class TestRunner {

}
