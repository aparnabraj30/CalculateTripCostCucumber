package factory;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
public class BaseForSteps {
	public static WebDriver driver;
	public static Logger logger;
	public static Properties p;
	public static String xlfile1 = System.getProperty("user.dir")+"\\src\\test\\resources\\ExcelFiles\\Cruise Outputs.xlsx";
	public static String xlfile2 = System.getProperty("user.dir")+"\\src\\test\\resources\\ExcelFiles\\VacHomes Outputs.xlsx";
	
	
	public static void initializeWebDriver() throws IOException {
		/* Properties object got created and loaded to get
		 * data from the properties file	*/
		FileReader file = new FileReader("./src//test//resources//config.properties");
		p = new Properties();
		p.load(file);
		
		// Setting up the driver based on the browser type like chrome, edge and platform type like local or remote	
		String exec_env = p.getProperty("execution_env");
		String browser_type = p.getProperty("browser");
		String os = p.getProperty("operating_system");
		
		if(exec_env.toLowerCase().equals("local")) {
			switch(browser_type.toLowerCase()) {
			case "chrome":
				ChromeOptions co = new ChromeOptions();
				co.setPageLoadStrategy(PageLoadStrategy.NORMAL);
				driver = new ChromeDriver(co);
				break;
			case "edge":
				EdgeOptions eo = new EdgeOptions();
				eo.setPageLoadStrategy(PageLoadStrategy.NORMAL);
				driver = new EdgeDriver(eo);
				break;
			default:
				System.out.println("Invalid browser type !!");
				return;
			}
		}
		else if(exec_env.toLowerCase().equals("remote")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			if(os.toLowerCase().equals("windows")) {
				 capabilities.setPlatform(Platform.WIN11);
			}
			else if(os.toLowerCase().equals("mac")) {
				capabilities.setPlatform(Platform.MAC);
			}
			else {
				System.out.println("Wrong OS name !");
				return;
			}
			
			switch(browser_type.toLowerCase()) {
			case "chrome":
				capabilities.setBrowserName("chrome");
				break;
			case "edge":
				capabilities.setBrowserName("MicrosoftEdge");
				break;
			default:
				System.out.println("Invalid browser type !!");
				return;
			}
			
			driver = new RemoteWebDriver(new URL("http://localhost:4444/"),capabilities);
			
		}
		
		else {
			System.out.println("Invalid execution environment !");
			return;
		}
		
		
		
		
	}
	
	public static Logger getLogger() 
	{		 
		//Setting up the logger and getter method for Logger object 
		logger=LogManager.getLogger(); //Log4j
		return logger;
	}
	
	public static WebDriver getDriver() {
		
		//getter method for WebDriver object
		return driver;
	}
	
	
	public static void closeDriver() {
		//Method to close the driver
		driver.quit();
	}

}
