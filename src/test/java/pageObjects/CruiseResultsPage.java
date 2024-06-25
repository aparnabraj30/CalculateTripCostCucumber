package pageObjects;

import java.io.IOException;
import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import excelmanager.ExcelUtils;
import factory.BaseForSteps;

public class CruiseResultsPage extends BasePage{
	
	public CruiseResultsPage(WebDriver driver) {
		super(driver);
	}
	
//	get the names of the cruise lines
	@FindBy(xpath="//ul[@id='cruiselist']/li//h3[@class='wth2-shipName']")
	List<WebElement> cruises_name;
	
	By c_list_waiter = By.id("cruiselist");
	
	@FindBy(id="ajaxSpinnerOuter")
	WebElement loading_spinner;
	
//	Action methods 
//	get the details of all the cruises
	public void getCruiseDetails() throws IOException {
		Set<String> unique_cruises = new HashSet<>();
		waitForElement(c_list_waiter);
		System.out.println("Available Cruises : ");
		for(WebElement a: cruises_name) {
			unique_cruises.add(a.getText());
		}
		int i = 0;
		for(String b: unique_cruises) {
			System.out.println("-> "+b);
			ExcelUtils.setCellData(BaseForSteps.xlfile1, "Sheet2", ++i, 0,b);
		}
		System.out.println();
	}
	
//	clicking on the  first cruise
	public void getFirstCruiseFullDetails() {
		waitForAttrToBe(loading_spinner);
		WebElement f_cruise = cruises_name.get(0);
		f_cruise.click();
	}
	
	public boolean isResultDisplayed() {
		return cruises_name.size()>0;
	}
	private void waitForElement(By elem) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(7));
		wait.until(ExpectedConditions.presenceOfElementLocated(elem));
	}
	
	private void waitForAttrToBe(WebElement elem) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.attributeToBeNotEmpty(elem, "style"));
	}
	 
	

}
