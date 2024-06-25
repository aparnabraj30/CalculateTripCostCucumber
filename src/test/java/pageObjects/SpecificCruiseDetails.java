package pageObjects;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import excelmanager.ExcelUtils;
import factory.BaseForSteps;

public class SpecificCruiseDetails  extends BasePage{
	
	public SpecificCruiseDetails(WebDriver driver) {
		super(driver);
	}
	
//	ship details element
	@FindBy(xpath="//a[@id='expandCollapse_ship']")
	WebElement ship_details_button;
	
//	list of p tags containing the details
	@FindBy(xpath="//div[@id='expandCollapse_ship_Content']//p")
	List<WebElement> details;
	
	@FindBy(xpath = "(//div[@id='expandCollapse_ship_Content']//p)[1]")
	WebElement first_detail;
	
	@FindBy(xpath="//span[contains(text(), 'Language') or contains(text(), 'language')]")
	WebElement languages_of_cruise;
	
	By details_waiter = By.xpath("//div[@id='expandCollapse_ship_Content']//p");
	
//	getting the whole details
	public void getCompleteDet() throws IOException {
		clickshipDeatils();
		waitForElement(details_waiter);
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true)", first_detail);
		try {
			System.out.println(languages_of_cruise.getText());
		}
		catch(Exception e) {
			System.out.println("There are no details about languages.\n");
		}
		for(int i =0;i<details.size();i++) {
			System.out.println(details.get(i).getText());
			ExcelUtils.setCellData(BaseForSteps.xlfile1, "Sheet1", i+1, 0, details.get(i).getText());
		}
		
		
	}
	
	private void clickshipDeatils() {
		ship_details_button.click();
	}
	
	private void waitForElement(By elem) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(7));
		wait.until(ExpectedConditions.presenceOfElementLocated(elem));
	}


}
