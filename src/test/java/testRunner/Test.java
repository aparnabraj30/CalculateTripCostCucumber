package testRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;

import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
					features= {".//featureFiles/TC001_HolidayHomes.feature",".//featureFiles/TC002_Cruise Booking.feature"},
					glue={"stepDefinitions"},
					plugin= {
								"pretty", "html:reports/myreport.html",
								"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
					},
					dryRun=false,    // checks mapping between scenario steps and step definition methods
					monochrome=true,    // to avoid junk characters in output
					publish=true  
							)
public class Test {

}
