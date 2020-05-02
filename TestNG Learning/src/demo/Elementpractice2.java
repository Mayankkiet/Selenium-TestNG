/** (1) Open URL
 *  (2) Give Implicit wait for 10 seconds
 *  (3) Practice on Drag and Drop and Frames
 *  (4) Practice on File Upload
 *  (5) Practice on Menus bar selection
 *  (6) Practice on Increment & Decrement Inputs and Horizontal Slider
 *  (7) Practice on Web Table
 *  
 */

package demo;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Elementpractice2 {

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
		//Practice on Drag and Drop and Frames
		
		driver.findElement(By.linkText("Drag and Drop")).click();
		
		//Fetch the element property which should be dragged	
		WebElement fromElement = driver.findElement(By.xpath("//div[@id='column-a']"));	
			
		//Fetch the element property where the dragged element should get released	
		WebElement toElement = driver.findElement(By.xpath("//div[@id='column-b']"));	
			
		//Create a reference for Actions class	
		Actions action = new Actions(driver);	
			
		//Use dragAndDrop method and provide arguements as the from element and to element	
		action.dragAndDrop(fromElement,toElement).perform();	
			
		
		// Type cast the driver reference variable with TakesScreenshot for access the methods from TakesScreenshot interface
					// getScreenshotAs method will take argument for the output type of the file
					File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

					// Using the FileUtils class copy the generated screenshot file to any location
					FileUtils.copyFile(scrFile, new File("C:\\Users\\Mayank\\git\\Test1\\TestNG Learning\\Drag and Drop.jpg"));

		
	}

	@AfterClass
	public void teardown() {
		// Close the browser
		driver.quit();
	}
}
