package lex;


import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Log4jXml {

	static Logger logger = Logger.getLogger(Log4jXml.class);
	
	WebDriver driver;
	

	@BeforeClass
	public void setup() {
		DOMConfigurator.configure("log4j.xml");
		//PropertyConfigurator.configure("log4j.xml");
		// Set the key/value property according to the browser you are using
		System.setProperty("webdriver.chrome.driver", "D:\\Selenium JARS and Drivers\\chromedriver.exe");

		// Open browser instance
		driver = new ChromeDriver();
		
		
		
		String url = "http://the-internet.herokuapp.com/";
		// Open AUT
		driver.get(url);
		//Below  came in log so un-commenting
		logger.info("Opening Home Page");
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	}

	@Test
	public void test() {
		String pageTitle = driver.getTitle();
		System.out.println(pageTitle);
		WebElement loginUrl = driver.findElement(By.linkText("Form Authentication"));
		loginUrl.click();
		driver.findElement(By.xpath("//input[@name ='username']")).sendKeys("tomsmith");
		logger.info("Entering Username");
		driver.findElement(By.xpath("//input[@name ='password']")).sendKeys("SuperSecretPassword!");
		logger.info("Entering Password");
		driver.findElement(By.xpath("//button[@class ='radius']")).click();
		logger.info("Click on Login button");
		String expectedMessage = "You logged into a secure area!";
	
		WebElement successMessage = driver.findElement(By.xpath("//div[@id='flash']"));
		String actualMessage = successMessage.getText();
		logger.info("Actual message fetched " + actualMessage);
		
		Assert.assertTrue(actualMessage.contains(expectedMessage),
				"Actual message does not contain expected message.\nActual Message: " + actualMessage
						+ "\nExpected Message: " + expectedMessage);
	}

	@AfterClass
	public void teardown() {
		// Close the browser
		driver.close();
		logger.info("Driver Closed");

	}
	
}
