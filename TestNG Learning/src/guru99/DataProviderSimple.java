package guru99;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderSimple {
    WebDriver driver;
    
    @BeforeTest
    public void setup(){
        //Create chrome driver object
    	System.setProperty("webdriver.chrome.driver", "D:\\Selenium JARS and Drivers\\chromedriver.exe");
    	driver = new ChromeDriver();

         driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
         driver.get("https://google.com");
    }
 
    /** Test case to verify google search box
     * @param author
     * @param searchKey
     * @throws InterruptedException
     */

    @Test(dataProvider="SearchProvider")
    public void testMethod(String author,String searchKey) throws InterruptedException{
   
        WebElement searchText = driver.findElement(By.name("q"));
        //search value in google searchbox
        searchText.sendKeys(searchKey);
        System.out.println("Welcome ->"+author+" Your search key is->"+searchKey);
        Thread.sleep(3000);
        String testValue = searchText.getAttribute("value");
        System.out.println(testValue +"::::"+searchKey);
        searchText.clear();
        //Verify if the value in google search box is correct
        Assert.assertTrue(testValue.equalsIgnoreCase(searchKey));
    }
  
    /**
     * @return Object[][] where first column contains 'author'
     * and second column contains 'searchKey'
     */

    @DataProvider(name="SearchProvider")
    public Object[][] getDataFromDataprovider(){
    return new Object[][] 
    	{
            { "Guru99", "India" },
            { "Krishna", "UK" },
            { "Bhupesh", "USA" }
        };

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
PASSED: testMethod("Guru99", "India")
PASSED: testMethod("Krishna", "UK")
PASSED: testMethod("Bhupesh", "USA")

===============================================
    Default test
    Tests run: 3, Failures: 0, Skips: 0
===============================================


===============================================
Default suite
Total tests run: 3, Failures: 0, Skips: 0
===============================================

<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">
<suite name="Log4j" verbose="1">

	<test name="Log4j Test">
		<classes>
			<class name="guru99.DataProviderSimple">
			
			</class>
		</classes>
	</test>

</suite>*/