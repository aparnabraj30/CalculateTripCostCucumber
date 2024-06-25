package stepDefinitions;

import java.io.IOException;
import java.time.Duration;

import org.junit.Assert;
import factory.BaseForSteps;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.CruiseHomePage;
import pageObjects.CruiseResultsPage;
import pageObjects.SpecificCruiseDetails;

public class CruiseBooking {
	
	CruiseHomePage cp;
	CruiseResultsPage cr;
	SpecificCruiseDetails scd;
	

	
	@Before
	public void setupDriver() throws IOException {
		BaseForSteps.initializeWebDriver();
	}
	
	@After 
	public void tearDown() {
		BaseForSteps.closeDriver();
	}
	
	@Given("the cruise.booking.com website homepage for cruise booking")
	public void the_website_homepage() {
		BaseForSteps.getDriver().get(BaseForSteps.p.getProperty("url2"));
		BaseForSteps.getLogger().info("Got into the Cruise booking website");
		BaseForSteps.getDriver().manage().window().maximize();
		BaseForSteps.getLogger().info("Window maximized");
		BaseForSteps.getDriver().manage().deleteAllCookies();
		BaseForSteps.getLogger().info("Cookies deleted");
		BaseForSteps.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@When("I give cruise line as input and click VIEW CRUISES")
	public void i_give_cruise_line_as_input() {
		cp = new CruiseHomePage(BaseForSteps.getDriver());
		cp.setCruiseDetails();
		BaseForSteps.getLogger().info("Cruise line is set");
	}


	@Then("It gives the result of cruises")
	public void it_gives_the_result_of_cruises() throws IOException {
		cr = new CruiseResultsPage(BaseForSteps.getDriver());
		cr.getCruiseDetails();
		BaseForSteps.getLogger().info("Uniques Cruises are printed and stored in excel");
		Assert.assertEquals(cr.isResultDisplayed(), true);
	}

	@When("I click the link of the first cruise and click Ship details")
	public void i_click_the_link_of_the_first_cruise() {
		cr = new CruiseResultsPage(BaseForSteps.getDriver());
		cr.getFirstCruiseFullDetails();
		BaseForSteps.getLogger().info("First cruise is clicked");
	}


	@Then("There is a detail about the ship")
	public void there_is_a_detail_about_the_ship() throws IOException {
		scd = new SpecificCruiseDetails(BaseForSteps.getDriver());
		BaseForSteps.getLogger().info("Checking for languages and gets complete details of the cruise and the same is printed and stored");
		scd.getCompleteDet();
	}

}
