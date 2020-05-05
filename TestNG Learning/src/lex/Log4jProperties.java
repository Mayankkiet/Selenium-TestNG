package lex;


import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Log4jProperties {

	static Logger logger = Logger.getLogger(Log4jProperties.class);
	
	WebDriver driver;
	

	@BeforeClass
	public void setup() {
		
		PropertyConfigurator.configure("log4j.properties");
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

/*<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">
<suite name="Log4j" verbose="1">

	<test name="Log4j Test">
		<classes>
			<class name="lex.Log4jProperties">
			
			</class>
		</classes>
	</test>

</suite>

Log4j Properties

#Set level	
log4j.rootCategory=INFO,console,file	
  	
# Appender which writes to console  	
log4j.appender.console=org.apache.log4j.ConsoleAppender  	
#Appender which writes to file	
log4j.appender.file=org.apache.log4j.RollingFileAppender	
log4j.appender.file.File=.\\Logs\\AppLogs.log	
#Define pattern layout for console logs 	
log4j.appender.console.layout=org.apache.log4j.PatternLayout	
log4j.appender.console.layout.ConversionPattern=%d{MM-dd-yyyy HH:mm:ss} %F %-5p [%t] %c{2} %L - %m%n	
 	
# Defining maximum size of a log file	
log4j.appender.file.MaxFileSize=1KB  	
log4j.appender.file.MaxBackupIndex=2  	
log4j.appender.file.layout=org.apache.log4j.PatternLayout  	
log4j.appender.file.layout.ConversionPattern=%d{ISO8601} %5p %F [%t] %c{1}:%L - %m%n

AppLogs.log

2020-05-05 16:08:44,156  INFO Log4jProperties.java [main] Log4jProperties:40 - Opening Home Page
2020-05-05 16:08:47,739  INFO Log4jProperties.java [main] Log4jProperties:53 - Entering Username
2020-05-05 16:08:47,997  INFO Log4jProperties.java [main] Log4jProperties:55 - Entering Password
2020-05-05 16:08:48,899  INFO Log4jProperties.java [main] Log4jProperties:57 - Click on Login button
2020-05-05 16:08:48,958  INFO Log4jProperties.java [main] Log4jProperties:62 - Actual message fetched You logged into a secure area!
×
2020-05-05 16:08:49,258  INFO Log4jProperties.java [main] Log4jProperties:73 - Driver Closed*/