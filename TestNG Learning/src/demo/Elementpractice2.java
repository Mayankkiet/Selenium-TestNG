/** (1) Open URL
 *  (2) Give Implicit wait for 10 seconds
 *  (3) *Practice on Drag and Drop and Frames
 *  (4) Practice on File Upload
 *  (5) Practice on Menus bar selection
 *  (6) Practice on Increment & Decrement Inputs and Horizontal Slider
 *  (7) Practice on Web Table
 *  
 */

package demo;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
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
		
      driver.manage().window().maximize();

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
			
		//Use dragAndDrop method and provide arguments as the from element and to element	
		action.dragAndDrop(fromElement,toElement).build().perform();
		
//----------------------------------------------------------------------------------------------
	
		//Switch to the frame using the index number
		//Frames can be switched using either Index, Name, Id
		
		driver.findElement(By.linkText("Frames")).click();
		driver.findElement(By.linkText("Nested Frames")).click();		

		driver.switchTo().frame("frame-middle");

		//Post successful try to fetch some element from the corresponding frame
		String message1 = driver.findElement(By.xpath("//frame[@name='frame-middle']")).getText();
		System.out.println(message1);

		//Switch back to the parent frame
		driver.switchTo().parentFrame();

		//Try to switch to another frame using frame name
		driver.switchTo().frame("frame-right");

		//Post succesful try to fetch some element from the corresponding frame
		String message2 = driver.findElement(By.xpath("//frame[@name='frame-right']")).getText();
		System.out.println(message2);
	
	}

	@AfterClass
	public void teardown() {
		// Close the browser
		driver.quit();
	}
}
