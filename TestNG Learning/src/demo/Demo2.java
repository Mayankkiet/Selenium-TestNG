package demo;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class Demo2 {
	
	WebDriver driver;
	System.setProperty("webdriver.chrome.driver","D:\\Selenium JARS and Drivers\\chromedriver.exe");
	@BeforeSuite	
	//driver = new ChromeDriver();
		
		@Parameters({ "browsername", "url" })
		public void setup(@Optional("Firefox")String browsername,String url) {	
			switch (browsername) {
			case "IE":
			//Code to initialize webdriver instance
				driver.get(url);
				break;
			case "Firefox":
				//Code to initialize webdriver instance
				driver.get(url);
				break;
			case "chrome":
				//Code to initialize webdriver instance
				driver.get(url);
				break;	
			}	
		}

	}
	
