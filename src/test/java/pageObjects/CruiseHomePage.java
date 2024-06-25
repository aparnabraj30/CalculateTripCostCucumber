package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CruiseHomePage extends BasePage{
	
	
	public CruiseHomePage(WebDriver driver) {
		super(driver);
	}
	
//	cruise line button
	@FindBy(xpath = "//button[@id='hp_searchFilterCruiseline']")
	WebElement cruiseline;
	
//	contents of cruise line box
	By cruiseline_box = By.id("hp_searchCruiselineFilterItems");
	
//	getting first 
	@FindBy(xpath="(//div[@id='hp_searchCruiselineFilterItems']//button)[1]")
	WebElement cruise_line_1;
	
	@FindBy(id="hp_searchCruiselineFilterClose")
	WebElement close_box;
	
	//Optional (closing button)
	By c_box_waiter = By.id("hp_searchCruiselineFilterClose");
	
			
	@FindBy(id="hp_searchContinue")
	WebElement view_cruises;
	
	
	By popup  = By.id("om-oveyuxwr24rpyl42hdfp-optin");
	
	@FindBy(xpath="//button[@class='CloseButton__ButtonElement-sc-79mh24-0 dXqkKJ fallsview-CloseButton fallsview-close fallsview-ClosePosition--top-right']")
	WebElement close_popup;
	
	
	@FindBy(id="ajaxSpinner")
	WebElement loading_spinner;
	
	
	public void setCruiseDetails() {
		try {
			HandlePopup();
		}
		catch(Exception e) {
			System.out.println("There is no popup in cruise homepage too.. Haha !");
		}
		finally {
		clickCruiseLine();
		waitForElement(cruiseline_box);
		clickCruiseOption();
		waitForElement(c_box_waiter);
		waitForAttrToBe(loading_spinner);
//		wait3.until(ExpectedConditions.elementToBeClickable(close_box));
		clickClose();
		clickViewCruises();
		}
	}
	
	private void clickCruiseLine() {
		cruiseline.click();
	}
	
	private void clickCruiseOption() {
		cruise_line_1.click();
	}
	
	private void clickClose() {
		close_box.click();
	}

	
	
	private void clickViewCruises() {
		view_cruises.click();
	}
	private void waitForElement(By elem) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.presenceOfElementLocated(elem));
	}
	
	
	private void HandlePopup() {
		waitForElement(popup);
		close_popup.click();
		
		
	}
	
	private void waitForAttrToBe(WebElement elem) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.attributeToBeNotEmpty(elem, "style"));
	}

}
