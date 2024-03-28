package Hooks;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

import java.text.Format;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "E:\\M2IAutomation\\src\\test\\java\\Feature",
        glue = {"stepDefinition","Hooks",},
        plugin = {"pretty", "html:target/cucumber-html-report", "json:target/cucumber-json-report.json"}
)
public class TestRunner {
}
