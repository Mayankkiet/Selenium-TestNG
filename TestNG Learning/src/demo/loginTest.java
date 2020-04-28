package demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;

public class loginTest {
	
	WebDriver driver;
	String url = "http://the-internet.herokuapp.com/";
	
	@BeforeClass
	public void setup()
{
	System.setProperty("webdriver.chrome.driver","D:\\Selenium JARS and Drivers\\chromedriver.exe");
	driver = new ChromeDriver();
	driver.get(url);
}
}
