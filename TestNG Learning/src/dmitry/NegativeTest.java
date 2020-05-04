package dmitry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeTest {

	@Test(priority = 1, groups = { "negativeTests", "smokeTests" })
	public void incorrectUsernameTest() {
		System.out.println("Starting incorrectUsernameTest");

		// Create driver
		System.setProperty("webdriver.chrome.driver", "D:\\Selenium JARS and Drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		// sleep for 3 seconds
		sleep(3000);

		// maximize browser window
		driver.manage().window().maximize();

		// open test page
		String url = "http://the-internet.herokuapp.com/login";
		driver.get(url);
		System.out.println("Page is opened.");

		// enter username
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("incorrectUsername");

		// enter password
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("SuperSecretPassword!");

		// click login button
		WebElement logInButton = driver.findElement(By.tagName("button"));
		logInButton.click();

		sleep(3000);

		// Verifications
		WebElement errorMessage = driver.findElement(By.id("flash"));
		String expectedErrorMessage = "Your username is invalid!";
		String actualErrorMessage = errorMessage.getText();

		Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage),
				"Actual error message does not contain expected. \nActual: " + actualErrorMessage + "\nExpected: "
						+ expectedErrorMessage);

		// Close browser
		driver.quit();
	}

	@Test(priority = 2, groups = { "negativeTests" })
	public void incorrectPasswordTest() {
		System.out.println("Starting incorrectPasswordTest");

		// Create driver
		System.setProperty("webdriver.chrome.driver", "D:\\Selenium JARS and Drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		// sleep for 3 seconds
		sleep(3000);

		// maximize browser window
		driver.manage().window().maximize();

		// open test page
		String url = "http://the-internet.herokuapp.com/login";
		driver.get(url);
		System.out.println("Page is opened.");

		// enter username
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("tomsmith");

		// enter password
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("incorrectPassword!");

		// click login button
		WebElement logInButton = driver.findElement(By.tagName("button"));
		logInButton.click();

		sleep(3000);

		// Verifications
		WebElement errorMessage = driver.findElement(By.id("flash"));
		String expectedErrorMessage = "Your password is invalid!";
		String actualErrorMessage = errorMessage.getText();

		Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage),
				"Actual error message does not contain expected. \nActual: " + actualErrorMessage + "\nExpected: "
						+ expectedErrorMessage);

		// Close browser
		driver.quit();
	}

	private void sleep(long m) {
		try {
			Thread.sleep(m);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

/*testng.xml

<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">
<suite name="NegativeTestsSuite">
	<test thread-count="5" name="NegativeTests">

		<groups>
			<run>
				<include name="smokeTests" />
				<include name="negativeTests" />

			</run>
		</groups>
		<classes>
			<class name="dmitry.NegativeTest">

			<methods>
				<exclude name="incorrectPasswordTest" />
			</methods>
			
			</class>

		</classes>
	</test> <!-- NegativeTests -->
</suite> <!-- NegativeTestsSuite -->
*/


