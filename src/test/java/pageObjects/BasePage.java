package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	WebDriver driver;
	WebDriverWait wait;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
