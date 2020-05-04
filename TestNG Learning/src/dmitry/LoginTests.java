package dmitry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTests {
	private WebDriver driver;

	@Parameters({ "browser" })
	@BeforeMethod(alwaysRun = true)
	
	//if private void setUp(@Optional("chrome") String browser) then case "chrome"
	//if private void setUp(@OptionalString browser) then default
	private void setUp(@Optional("chrome") String browser) {
		// Create driver
		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "D:\\Selenium JARS and Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			break;

		case "IE":
			System.setProperty("webdriver.ie.driver", "D:\\Selenium JARS and Drivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			break;

		default:
			System.out.println("Do not know how to start " + browser + ", starting chrome instead");
			System.setProperty("webdriver.chrome.driver", "D:\\Selenium JARS and Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			break;
		}

		// sleep for 3 seconds
		sleep(3000);

		// maximize browser window
		driver.manage().window().maximize();
	}

	@Test(priority = 1, groups = { "positiveTests", "smokeTests" })
	public void positiveLoginTest() {
		System.out.println("Starting loginTest");

		// open test page
		String url = "http://the-internet.herokuapp.com/login";
		driver.get(url);
		System.out.println("Page is opened.");

		// enter username
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("tomsmith");

		// enter password
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("SuperSecretPassword!");

		// click login button
		WebElement logInButton = driver.findElement(By.tagName("button"));
		logInButton.click();

		sleep(3000);

		// verifications:
		// new url
		String expectedUrl = "http://the-internet.herokuapp.com/secure";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "Actual page url is not the same as expected");

		// logout button is visible
		WebElement logOutButton = driver.findElement(By.xpath("//a[@class='button secondary radius']"));
		Assert.assertTrue(logOutButton.isDisplayed(), "Log Out button is not visible");

		// succesful login message
		// WebElement successMessage = driver.findElement(By.cssSelector("#flash"));
		WebElement successMessage = driver.findElement(By.xpath("//div[@id='flash']"));
		String expectedMessage = "You logged into a secure area!";
		String actualMessage = successMessage.getText();
		// Assert.assertEquals(actualMessage, expectedMessage, "Actual message is not
		// the same as expected");
		Assert.assertTrue(actualMessage.contains(expectedMessage),
				"Actual message does not contain expected message.\nActual Message: " + actualMessage
						+ "\nExpected Message: " + expectedMessage);
	}

	@Parameters({ "username", "password", "expectedMessage" })
	@Test(priority = 2, groups = { "negativeTests", "smokeTests" })
	public void negativeLoginTest(String username, String password, String expectedErrorMessage) {
		System.out.println("Starting negativeLoginTest with " + username + " and " + password);

		// open test page
		String url = "http://the-internet.herokuapp.com/login";
		driver.get(url);
		System.out.println("Page is opened.");

		// enter username
		WebElement usernameElement = driver.findElement(By.id("username"));
		usernameElement.sendKeys(username);

		// enter password
		WebElement passwordElement = driver.findElement(By.name("password"));
		passwordElement.sendKeys(password);

		// click login button
		WebElement logInButton = driver.findElement(By.tagName("button"));
		logInButton.click();

		sleep(3000);

		// Verifications
		WebElement errorMessage = driver.findElement(By.id("flash"));
		String actualErrorMessage = errorMessage.getText();

		Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage),
				"Actual error message does not contain expected. \nActual: " + actualErrorMessage + "\nExpected: "
						+ expectedErrorMessage);
	}

	private void sleep(long m) {
		try {
			Thread.sleep(m);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterMethod(alwaysRun = true)
	private void tearDown() {
		// Close browser
		driver.quit();
	}

}





/*<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">
<suite name="NegativeTestsSuite" verbose="1">

	<test name="PositiveTests">
		<groups>
			<run>
				<include name="positiveTests"></include>
			</run>
		</groups>
		<classes>
			<class name="dmitry.LoginTests" />
		</classes>
	</test>

	<test name="NegativeUsernameTests">
		<parameter name="browser" value="chrome" />
		<parameter name="username" value="incorrectUsername" />
		<parameter name="password" value="SuperSecretPassword!" />
		<parameter name="expectedMessage"
			value="Your username is invalid!" />
		<classes>
			<class name="dmitry.LoginTests">
				<methods>
					<include name="negativeLoginTest"></include>
				</methods>
			</class>
		</classes>
	</test>

	<test name="NegativePasswordTests">
		<parameter name="browser" value="IE" />
		<parameter name="username" value="tomsmith" />
		<parameter name="password" value="incorrectPassword!" />
		<parameter name="expectedMessage"
			value="Your password is invalid!" />
		<classes>
			<class name="dmitry.LoginTests">
				<methods>
					<include name="negativeLoginTest"></include>
				</methods>
			</class>
		</classes>
	</test>

</suite>

---------------------------------------------------------------------------------------------------
Output:
	
	[RemoteTestNG] detected TestNG version 6.14.3
	Starting ChromeDriver 81.0.4044.69 (6813546031a4bc83f717a2ef7cd4ac6ec1199132-refs/branch-heads/4044@{#776}) on port 44320
	Only local connections are allowed.
	Please protect ports used by ChromeDriver and related test frameworks to prevent access by malicious code.
	log4j:WARN No appenders could be found for logger (org.apache.http.client.protocol.RequestAddCookies).
	log4j:WARN Please initialize the log4j system properly.
	log4j:WARN See http://logging.apache.org/log4j/1.2/faq.html#noconfig for more info.
	Starting loginTest
	[1588562658.072][SEVERE]: Timed out receiving message from renderer: 0.100
	[1588562658.174][SEVERE]: Timed out receiving message from renderer: 0.100
	[1588562658.277][SEVERE]: Timed out receiving message from renderer: 0.100
	[1588562658.380][SEVERE]: Timed out receiving message from renderer: 0.100
	[1588562658.484][SEVERE]: Timed out receiving message from renderer: 0.100
	
	Page is opened.
	[1588562663.771][SEVERE]: Timed out receiving message from renderer: 0.100
	Starting ChromeDriver 81.0.4044.69 (6813546031a4bc83f717a2ef7cd4ac6ec1199132-refs/branch-heads/4044@{#776}) on port 33589
	Only local connections are allowed.
	Please protect ports used by ChromeDriver and related test frameworks to prevent access by malicious code.
	Starting negativeLoginTest with incorrectUsername and SuperSecretPassword!
	[1588562676.355][SEVERE]: Timed out receiving message from renderer: 0.100
	[1588562676.458][SEVERE]: Timed out receiving message from renderer: 0.100
	[1588562676.563][SEVERE]: Timed out receiving message from renderer: 0.100
	[1588562676.665][SEVERE]: Timed out receiving message from renderer: 0.100
	
	Page is opened.
	Started InternetExplorerDriver server (64-bit)
	3.9.0.0
	Listening on port 9852
	Only local connections are allowed

	===============================================
	NegativeTestsSuite
	Total tests run: 3, Failures: 0, Skips: 1
	Configuration Failures: 2, Skips: 0
	===============================================*/