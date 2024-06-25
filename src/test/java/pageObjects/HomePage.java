package pageObjects;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage{									
	
	public HomePage(WebDriver driver) {						//constructor for this POM		
		super(driver);
	}
	
	@FindBy(xpath="//input[@name='ss']")					//Search box's xpath, Here we give  Nairobi
	WebElement search_box;
	
	@FindBy(xpath="//div[@class='b43bdecede']//button")		//Popup's close button
	WebElement close_popup;
	
	By offerDiv = By.className("b43bdecede");				//Popup's div id
	
	
	@FindBy(xpath="//button[@data-testid='date-display-field-start']")	//Date picker button 
	WebElement date_picker_button;
	
	By calendar_box = By.xpath("//div[@data-testid = 'searchbox-datepicker']");		//It appears after date picker is clicked
	
	@FindBy(xpath="//div[@data-testid = 'searchbox-datepicker-calendar']//table//td/span")	//Getting dates using this webelement
	List<WebElement> dates;
	
	@FindBy(xpath="//button[@data-testid = 'occupancy-config']")			//Occupancy button
	WebElement occupancy;
	
	By occupancy_popup = By.xpath("//div[@data-testid='occupancy-popup']");		//popup appears when the occupancy is clicked
	
	@FindBy(xpath="//div[@data-testid='occupancy-popup']/descendant::button[2]")	//plus button in the popup
	WebElement add_adult_by_one;
	
	@FindBy(xpath="//div[@data-testid='occupancy-popup']/descendant::span[3]")		//It refers to the count in occupancy popup
	WebElement adult_count;
	
	@FindBy(xpath="//button[normalize-space() = 'Done']")							//Done button in the occupancy popup
	WebElement done_button;
	
	
	@FindBy(xpath="//button[normalize-space() = 'Search']")							//Search button in homepage of booking.com
	WebElement search_button;
			
//	Action methods
	
//	passing city name
	public void giveSearchInput(String city) {
		try {
		handleOfferPopup();
		}
		catch(Exception e) {
			System.out.println("There is no popup..");
		}
		finally {		
			search_box.click();
			search_box.clear();
			search_box.sendKeys(city);
		}
		
	}
	
//	passing check-in and check-out dates
	public void giveDateInput() {
		date_picker_button.click();
		waitForElement(calendar_box);
		String tomorrow_date = getTomorrowDate();
		String after_fivedays = getDateAfterFiveDays();
		for(WebElement a:dates) {
			if(a.getAttribute("data-date").equals(tomorrow_date)) {
				a.click();
			}
			
			if((a.getAttribute("data-date").equals(after_fivedays))) {
				a.click();
				break;
			}
		}
		
	}
	
//	set no. of adults
	public void giveOccupancyDetails() {
		occupancy.click();
		waitForElement(occupancy_popup);
		while(!adult_count.getText().equals("4")) {
			add_adult_by_one.click();
		}
		done_button.click();
		
	}
	
//	search 
	public void clickSearchButton() {
		search_button.click();
	}
	
//	explicit wait
	private void waitForElement(By elem) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(7));
		wait.until(ExpectedConditions.presenceOfElementLocated(elem));
	}
	
//	tomorrow's date
	private String getTomorrowDate() {
		LocalDate tomorrow = LocalDate.now().plusDays(1);
		DateTimeFormatter pattern =	DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return tomorrow.format(pattern);
	}
	
//	after 5 days date
	private String getDateAfterFiveDays() {
		LocalDate d = LocalDate.now().plusDays(6);
		DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return d.format(pattern);
	}
	
	private void handleOfferPopup() {
		waitForElement(offerDiv);
		close_popup.click();
	}
}
