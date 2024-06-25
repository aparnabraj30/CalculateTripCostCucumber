package stepDefinitions;

import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.HomePage;
import pageObjects.ResultsPage;
import screenshotmanager.ScreenShot;

import java.io.IOException;
import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.*;
import factory.BaseForSteps;

public class HolidayHomesSteps {
	
	public static HomePage hp = new HomePage(BaseForSteps.getDriver());;
	public static ResultsPage rp= new ResultsPage(BaseForSteps.getDriver());

	
	@AfterStep
	public void screenShot(Scenario scenario) throws IOException {
		byte[] screenshot = ((TakesScreenshot)BaseForSteps.getDriver()).getScreenshotAs(OutputType.BYTES);
		scenario.attach(screenshot, "image/png", scenario.getName());
		ScreenShot.fullScreenshot(BaseForSteps.getDriver());
		
	}
	
	
//	setting up the initial steps: getting url, maximizing, delete cookies & wait
	@Given("the booking.com website homepage")
	public void the_website_homepage() {
		BaseForSteps.getDriver().get(BaseForSteps.p.getProperty("url1"));
		BaseForSteps.getLogger().info("Website opened");
		BaseForSteps.getDriver().manage().window().maximize();
		BaseForSteps.getLogger().info("Window maximized");
		BaseForSteps.getDriver().manage().deleteAllCookies();
		BaseForSteps.getLogger().info("Deleted all cookies");
		BaseForSteps.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

//	providing destination
	@When("the location is given as Nairobi")
	public void the_location_is_given_as() {
		hp.giveSearchInput(BaseForSteps.p.getProperty("cityName"));
		BaseForSteps.getLogger().info("Search input is given");
	}
	
//	providing check in and check out date
	@When("dates are given five days from tomorrow")
	public void dates_are_given_five_days_from_tomorrow() {
		hp.giveDateInput();
		BaseForSteps.getLogger().info("Date input is given");
	}

//	set no. of adults and click search button
	@When("Setting the adult count to four")
	public void setting_the_adult_count_to_four() {
		hp.giveOccupancyDetails();
		BaseForSteps.getLogger().info("Occupancy details are given");
		hp.clickSearchButton();
		
	}

//	check if the result page is displayed or not
	@Then("the result with search criteria appears")
	public void the_result_with_search_criteria_appears() {
		BaseForSteps.getLogger().info("Verifying if the search appears");
		Assert.assertEquals(rp.isResultPageHere(), true);
	}

//	setting preferences - vacation homes
	@When("the vacation homes is given as property type")
	public void the_vacation_homes_is_given_as_property_type() {
		rp.setHolidayHomeasPreference();
		BaseForSteps.getLogger().info("Holiday homes are set as preferences");
	}

//	sort as highest to lowest rating
	@When("sort the result with rating in descending order")
	public void sort_the_result_with_rating_in_descending_order() {
		rp.setSortToHighestRating();
		BaseForSteps.getLogger().info("Sorted according to the highest priority");
	}
	
//	check if any elevator availability is there
	@When("checks and chooses the elevator availability")
	public void checks_and_chooses_the_elevator_availability() {
		rp.setElevatorAccess();
		BaseForSteps.getLogger().info("Trying to set elevator access option");
	}

//	printing output to console
	@Then("the result is provided with given criteria")
	public void the_result_is_provided_with_given_criteria() throws IOException {
		rp.getAllHotelNamesWithPrice();
		BaseForSteps.getLogger().info("Got hotel names with rent fee printed on console and stored on excel sheet");
		Assert.assertEquals(rp.getNunberOfHotels()>=3, true);
	}

}
