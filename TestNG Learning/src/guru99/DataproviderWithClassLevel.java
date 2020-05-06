package guru99;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataproviderWithClassLevel {
    WebDriver driver;
    
 	@BeforeTest
    public void setup(){
 		System.setProperty("webdriver.chrome.driver", "D:\\Selenium JARS and Drivers\\chromedriver.exe");
    	driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://google.com");
    }
   
    @Test(dataProvider="SearchProvider",dataProviderClass=DataProviderClass1.class)
    public void testMethod(String author,String searchKey) throws InterruptedException{
        
        WebElement searchText = driver.findElement(By.name("q"));
        //Search text in google text box
        searchText.sendKeys(searchKey);
        System.out.println("Welcome ->"+author+" Your search key is->"+searchKey);
        Thread.sleep(3000);
        //get text from search box
        String testValue = searchText.getAttribute("value");
        System.out.println(testValue +"::::"+searchKey);
        searchText.clear();
        //verify if search box has correct value
        Assert.assertTrue(testValue.equalsIgnoreCase(searchKey));
   }
    
    @AfterTest
   	private void tearDown() {
   		// Close browser
   		driver.quit();
}
}




/*Welcome ->Guru99 Your search key is->India
India::::India
Welcome ->Krishna Your search key is->UK
UK::::UK
Welcome ->Bhupesh Your search key is->USA
USA::::USA

===============================================
DataProvider
Total tests run: 3, Failures: 0, Skips: 0
===============================================


<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">
<suite name="DataProvider" verbose="1">

	<test name="DataProvider Test">
		<classes>
			<class name="guru99.DataproviderWithClassLevel">
			
			</class>
		</classes>
	</test>

</suite>*/