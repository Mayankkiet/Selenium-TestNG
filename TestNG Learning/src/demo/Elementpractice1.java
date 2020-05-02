/** (1) Open URL
 *  (2) Give Implicit wait for 10 seconds
 *  (3) Practice on Drop Down
 *  (4) Practice on Check boxes and Radio button
 *  (5) Practice on different pop-ups i.e. JavaScript Alerts
 *  (6) Practice on Multiple Windows
 */

package demo;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Elementpractice1 {

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

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void test() throws IOException, InterruptedException {
		// Practice on Drop Down
		driver.findElement(By.linkText("Dropdown")).click();
		WebElement dropDown = driver.findElement(By.xpath("//select[@id='dropdown']"));
		Select select = new Select(dropDown);
		select.selectByIndex(2);
		Thread.sleep(2000);
		select.selectByValue("1");
		Thread.sleep(2000);
		select.selectByVisibleText("Option 2");
		Thread.sleep(2000);

		// Practice on Check boxes and Radio button

		driver.findElement(By.xpath("//a[@href='/checkboxes']")).click();
		Thread.sleep(3000);
		List<WebElement> checkBoxes = driver.findElements(By.xpath("//input[@type='checkbox']"));

		for (int i = 0; i < checkBoxes.size(); i++) {
			checkBoxes.get(i).click();
		}

		// --------------------------------------------------------------------------------------------------

		// Practice on different pop-ups i.e. JavaScript Alerts

		// 1. Dealing with Alert buttons

		driver.findElement(By.linkText("JavaScript Alerts")).click();
		// Click on Alert button
		driver.findElement(By.xpath("//button[.='Click for JS Alert']")).click();

		// Using the driver reference variable switch to the alert box and click on OK
		// button using accept method
		driver.switchTo().alert().accept();

		System.out.println(driver.findElement(By.xpath("//p[@id='result']")).getText());

		// 2. Dealing with Confirmation prompts

		// Click on Confirmation button
		driver.findElement(By.xpath("//button[.='Click for JS Confirm']")).click();

		// Using the driver reference variable switch to the alert box and click on OK
		// button using accept method
		driver.switchTo().alert().accept();

		System.out.println(driver.findElement(By.xpath("//p[@id='result']")).getText());

		// Click on Confirmation button
		driver.findElement(By.xpath("//button[.='Click for JS Confirm']")).click();

		// Using the driver reference variable switch to the alert box and click on
		// CANCEL button using accept method
		driver.switchTo().alert().dismiss();

		System.out.println(driver.findElement(By.xpath("//p[@id='result']")).getText());

		// 3. Dealing with prompts

		// Click on Prompt button
		driver.findElement(By.xpath("//button[.='Click for JS Prompt']")).click();

		// Using the driver reference variable switch to the alert box and send some
		// values
		driver.switchTo().alert().sendKeys("Mayank");

		// Now click on OK button
		driver.switchTo().alert().accept();

		System.out.println(driver.findElement(By.xpath("//p[@id='result']")).getText());
		// --------------------------------------------------------------------------------------------------
		// Practice on Multiple Windows
		driver.findElement(By.linkText("Multiple Windows")).click();
		driver.findElement(By.linkText("Click Here")).click();

		// Fetch the number of opened windows
		Set<String> windowHandles = driver.getWindowHandles();
		System.out.println("Number of opened windows: " + windowHandles.size());

		// Iterate through all the available windows
		for (String string : windowHandles) {
			// Switch between windows using the string reference variable
			driver.switchTo().window(string);

			// Fetch the url of the page post successful switch
			String currentUrl = driver.getCurrentUrl();
			System.out.println(currentUrl);

		}

		System.out.println(driver.findElement(By.xpath("//div[@class='example']")).getText());
	}

	@AfterClass
	public void teardown() {
		// Close the browser
		driver.quit();
	}
}
