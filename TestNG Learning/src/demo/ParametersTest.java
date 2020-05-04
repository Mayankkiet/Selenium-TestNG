package demo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParametersTest {

	WebDriver driver;
	String url = "http://the-internet.herokuapp.com/";

	@BeforeClass
	public void setup() {
		// Set the key/value property according to the browser you are using
		System.setProperty("webdriver.chrome.driver", "D:\\Selenium JARS and Drivers\\chromedriver.exe");

		// Open browser instance
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		
		// Open AUT
		driver.get(url);
		
		WebElement loginUrl = driver.findElement(By.linkText("Form Authentication"));
		loginUrl.click();
		
		System.out.println("Page is opened.");
		


		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
	
	
	@Parameters({"username","password","expectedMessage" })
	@Test(priority = 1, groups = { "negativeTests", "smokeTests" })
	public void negativeLoginTest(String username, String password, String expectedErrorMessage) {
		System.out.println("Starting negativeLoginTest with " + username + " and " + password);
	

//		enter username
		WebElement usernameElement = driver.findElement(By.id("username"));
		usernameElement.sendKeys(username);
		

//		enter password
		WebElement passwordElement = driver.findElement(By.name("password"));
		passwordElement.sendKeys(password);

//		click login button
		WebElement logInButton = driver.findElement(By.tagName("button"));
		logInButton.click();

		sleep(3000);

		// Verifications
		WebElement errorMessage = driver.findElement(By.id("flash"));
		String actualErrorMessage = errorMessage.getText();

		Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage),
				"Actual error message does not contain expected. \nActual: " 
						+ actualErrorMessage + "\nExpected: "
						+ expectedErrorMessage);
		
	}

	private void sleep(long m) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@AfterClass
	public void teardown() {
		// Close the browser
		driver.quit();
	}
	
	
}

/*testng.xml

<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">
<suite name="NegativeTestsSuite" verbose="1">

	<test name="NegativeUsernameTests">
		<parameter name="username" value="incorrectUsername" />
		<parameter name="password" value="SuperSecretPassword!" />
		<parameter name="expectedMessage" value="Your username is invalid!" />
		<classes>
			<class name="demo.ParametersTest">
			</class>
		</classes>
	</test>
	
	<test name="NegativePasswordTests">
		<parameter name="username" value="tomsmith" />
		<parameter name="password" value="incorrectPassword!" />
		<parameter name="expectedMessage" value="Your password is invalid!" />
		<classes>
			<class name="demo.ParametersTest">
			</class>
		</classes>
	</test>

</suite>
----------------------------------------------------------------------------------------
Output:

	[RemoteTestNG] detected TestNG version 6.14.3
	Starting ChromeDriver 81.0.4044.69 (6813546031a4bc83f717a2ef7cd4ac6ec1199132-refs/branch-heads/4044@{#776}) on port 22669
	Only local connections are allowed.
	Please protect ports used by ChromeDriver and related test frameworks to prevent access by malicious code.
	log4j:WARN No appenders could be found for logger (org.apache.http.client.protocol.RequestAddCookies).
	log4j:WARN Please initialize the log4j system properly.
	log4j:WARN See http://logging.apache.org/log4j/1.2/faq.html#noconfig for more info.
	[1588560930.636][SEVERE]: Timed out receiving message from renderer: 0.100
	[1588560930.741][SEVERE]: Timed out receiving message from renderer: 0.100
	[1588560930.844][SEVERE]: Timed out receiving message from renderer: 0.100
	[1588560930.946][SEVERE]: Timed out receiving message from renderer: 0.100
	
	Page is opened.
	Starting negativeLoginTest with incorrectUsername and SuperSecretPassword!
	Starting ChromeDriver 81.0.4044.69 (6813546031a4bc83f717a2ef7cd4ac6ec1199132-refs/branch-heads/4044@{#776}) on port 7401
	Only local connections are allowed.
	Please protect ports used by ChromeDriver and related test frameworks to prevent access by malicious code.
	
	[1588560953.045][SEVERE]: Timed out receiving message from renderer: 0.100
	[1588560953.206][SEVERE]: Timed out receiving message from renderer: 0.100
	[1588560953.308][SEVERE]: Timed out receiving message from renderer: 0.100
	[1588560953.410][SEVERE]: Timed out receiving message from renderer: 0.100
	Page is opened.
	Starting negativeLoginTest with tomsmith and incorrectPassword!

	===============================================
	NegativeTestsSuite
	Total tests run: 2, Failures: 0, Skips: 0
	===============================================
*/