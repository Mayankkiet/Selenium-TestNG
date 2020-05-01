/** (1) Open URL and provide implicit wait of 5 seconds
 *  (2) Give User name , Password and click Submit
 *  (3) *Give Explicit Wait of 2 seconds  and observe the success message and verify it 
 *  (4) Close URL
 *  
 */

package demo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class loginTest_normal {

	WebDriver driver;
	String url = "http://the-internet.herokuapp.com/";

	@BeforeClass
	public void setup() {
		// Set the key/value property according to the browser you are using
		System.setProperty("webdriver.chrome.driver", "D:\\Selenium JARS and Drivers\\chromedriver.exe");

		// Open browser instance
		driver = new ChromeDriver();

		// Open AUT
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	}

	@Test
	public void test() {
		String pageTitle = driver.getTitle();
		System.out.println(pageTitle);
		WebElement loginUrl = driver.findElement(By.linkText("Form Authentication"));
		loginUrl.click();
		driver.findElement(By.xpath("//input[@name ='username']")).sendKeys("tomsmith");
		driver.findElement(By.xpath("//input[@name ='password']")).sendKeys("SuperSecretPassword!");
		driver.findElement(By.xpath("//button[@class ='radius']")).click();
		String expectedMessage = "You logged into a secure area!";
		WebElement successMessage = driver.findElement(By.xpath("//div[@id='flash']"));
		String actualMessage = successMessage.getText();
		System.out.println(actualMessage);
		
		Assert.assertTrue(actualMessage.contains(expectedMessage),
				"Actual message does not contain expected message.\nActual Message: " + actualMessage
						+ "\nExpected Message: " + expectedMessage);
	}

	@AfterClass
	public void teardown() {
		// Close the browser
		driver.close();

	}
}
