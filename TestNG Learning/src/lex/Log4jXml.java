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


/*<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE log4j:configuration SYSTEM "log4j.dtd">
<log4j:configuration>
  <appender name="ConsoleAppender" class="org.apache.log4j.ConsoleAppender">
    <layout class="org.apache.log4j.PatternLayout">
      <param name="ConversionPattern" value="%d{MM/dd HH:mm:ss} %-5p %30.30c %x - %m\n"/>
    </layout>
  </appender>
  <appender name="FileAppender" class="org.apache.log4j.RollingFileAppender">
    <param name="File" value=".\\Logs\\AppLogs.log"/>
    <param name="MaxFileSize" value="10MB"/>
    <layout class="org.apache.log4j.PatternLayout">
      <param name="ConversionPattern" value="%d{MM/dd HH:mm:ss} %-5p %30.30c %x - %m\n"/>
    </layout>
  </appender>
  <logger name="com.digitalsanctum.builder.web" additivity="false">
    <level value="debug"/>
    <appender-ref ref="ConsoleAppender"/>
  </logger>
  <root>
    <level value="info"/>
    <appender-ref ref="ConsoleAppender"/>
    <appender-ref ref="FileAppender"/>
  </root>
</log4j:configuration>
    
    
    05/05 19:40:46 INFO                    lex.Log4jXml  - Opening Home Page
    The Internet
    [1588687848.882][SEVERE]: Timed out receiving message from renderer: 0.100
    [1588687848.985][SEVERE]: Timed out receiving message from renderer: 0.100
    05/05 19:40:49 INFO                    lex.Log4jXml  - Entering Username
    05/05 19:40:49 INFO                    lex.Log4jXml  - Entering Password
    05/05 19:40:50 INFO                    lex.Log4jXml  - Click on Login button
    05/05 19:40:50 INFO                    lex.Log4jXml  - Actual message fetched You logged into a secure area!
    ×
    05/05 19:40:50 INFO                    lex.Log4jXml  - Driver Closed

    ===============================================
    Log4j
    Total tests run: 1, Failures: 0, Skips: 0
    ===============================================
----------------------------------------------------------------------------------------
    		05/05 19:40:46 INFO                    lex.Log4jXml  - Opening Home Page
    		05/05 19:40:49 INFO                    lex.Log4jXml  - Entering Username
    		05/05 19:40:49 INFO                    lex.Log4jXml  - Entering Password
    		05/05 19:40:50 INFO                    lex.Log4jXml  - Click on Login button
    		05/05 19:40:50 INFO                    lex.Log4jXml  - Actual message fetched You logged into a secure area!
    		×
    		05/05 19:40:50 INFO                    lex.Log4jXml  - Driver Closed  */  
 