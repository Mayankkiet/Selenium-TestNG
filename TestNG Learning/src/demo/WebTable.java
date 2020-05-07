package demo;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WebTable {

	WebDriver driver;
	String url = "https://chandanachaitanya.github.io/selenium-practice-site/";

	@BeforeClass
	public void setup() {
		// Set the key/value property according to the browser you are using
		System.setProperty("webdriver.chrome.driver", "D:\\Selenium JARS and Drivers\\chromedriver.exe");

		// Open browser instance
		driver = new ChromeDriver();
		JavascriptExecutor js = (JavascriptExecutor) driver;


		// Open AUT
		driver.get(url);
		js.executeScript("window.scrollBy(0,500)");
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	}

	@Test
	public void test() {
		List<WebElement> webTable = driver.findElements(By.xpath("//table[@id='BooksAuthorsTable']//tr"));
		
		int rowno = webTable.size();
		System.out.println(rowno);
		for (int i = 1; i < rowno; i++) {
			System.out.println(webTable.get(i).getText());
		}

		
	}

	@AfterClass
	public void teardown() {
		// Close the browser
		driver.close();

	}
}

