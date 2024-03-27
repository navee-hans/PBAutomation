package Hooks;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "E:\\M2IAutomation\\src\\test\\java\\Feature",
        glue = {"stepDefinition","Hooks"}
)
public class TestRunner {
}
