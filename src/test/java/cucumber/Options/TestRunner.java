package cucumber.Options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features="C:\\Users\\Sharath\\IdeaProjects\\CucumberRestAPIE2E\\src\\test\\java\\features\\GoogleMapsScenarios.feature",
        glue={"StepDefinitions"},plugin= "json:target/jsonReports/cucumber-report.json")
//,tags= "@DeletePlace"

public class TestRunner {



}
