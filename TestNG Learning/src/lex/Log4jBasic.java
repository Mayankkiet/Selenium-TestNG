package lex;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
//import org.testng.log4testng.Logger;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Log4jBasic {

	static Logger logger = Logger.getLogger(Log4jBasic.class);
	
	WebDriver driver;
	

	@BeforeClass
	public void setup() {
		
		BasicConfigurator.configure();
		Logger.getRootLogger().setLevel(Level.DEBUG);
		// Set the key/value property according to the browser you are using
		System.setProperty("webdriver.chrome.driver", "D:\\Selenium JARS and Drivers\\chromedriver.exe");

		// Open browser instance
		driver = new ChromeDriver();
		
		
		
		String url = "http://the-internet.herokuapp.com/";
		// Open AUT
		driver.get(url);
		//Below doesn't came in Console so commenting but it works for properties
		//logger.info("Opening Home Page");
		
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



//Don't use //import org.testng.log4testng.Logger;
/*<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">
<suite name="Log4j" verbose="1">

	<test name="Log4j Test">
		<classes>
			<class name="lex.Log4jBasic">
			
			</class>
		</classes>
	</test>

</suite>

Output:
16322 [main] INFO lex.Log4jBasic  - Entering Username
16597 [main] INFO lex.Log4jBasic  - Entering Password
17630 [main] INFO lex.Log4jBasic  - Click on Login button
17737 [main] INFO lex.Log4jBasic  - Actual message fetched You logged into a secure area!
×
17951 [main] INFO lex.Log4jBasic  - Driver Closed
PASSED: test

===============================================
    Default test
    Tests run: 1, Failures: 0, Skips: 0
===============================================


===============================================
Default suite
Total tests run: 1, Failures: 0, Skips: 0
===============================================*/