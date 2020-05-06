package guru99;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ParameterByITestContextInDataprovider {
	WebDriver driver;
	@BeforeTest(groups={"A","B"})
	public void setup(){
		System.setProperty("webdriver.chrome.driver", "D:\\Selenium JARS and Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get("https://google.com");
	}
	
	@Test(dataProvider="SearchProvider",groups="A")
	public void testMethodA(String author,String searchKey) throws InterruptedException{
		{
		  //search google textbox
			WebElement searchText = driver.findElement(By.name("q"));
			//search a value on it
			searchText.sendKeys(searchKey);
			System.out.println("Welcome ->"+author+" Your search key is->"+searchKey);
			Thread.sleep(3000);
			String testValue = searchText.getAttribute("value");
			System.out.println(testValue +"::::"+searchKey);
			searchText.clear();
			//verify correct value in searchbox
			Assert.assertTrue(testValue.equalsIgnoreCase(searchKey));
	}
	}
	
	@Test(dataProvider="SearchProvider",groups="B")
	public void testMethodB(String searchKey) throws InterruptedException{
		{
		  //find google search box
			WebElement searchText = driver.findElement(By.name("q"));
			//search a value on it
			searchText.sendKeys(searchKey);
			System.out.println("Welcome ->Unknown user Your search key is->"+searchKey);
			Thread.sleep(3000);
			String testValue = searchText.getAttribute("value");
			System.out.println(testValue +"::::"+searchKey);
			searchText.clear();
			//verify correct value in searchbox
			Assert.assertTrue(testValue.equalsIgnoreCase(searchKey));
	}
	}
	
	/**
	 * Here the DAtaProvider will provide Object array on the basis on ITestContext
	 * @param c
	 * @return
	 */
	@DataProvider(name="SearchProvider")
	public Object[][] getDataFromDataprovider(ITestContext c){
	Object[][] groupArray = null;
		for (String group : c.getIncludedGroups()) {
		if(group.equalsIgnoreCase("A")){
			groupArray = new Object[][] { 
					{ "Guru99", "India" }, 
					{ "Krishna", "UK" }, 
					{ "Bhupesh", "USA" } 
				};
		break;	
		}
			else if(group.equalsIgnoreCase("B"))
			{
			groupArray = new Object[][] { 
						{  "Canada" }, 
						{  "Russia" }, 
						{  "Japan" } 
					};
			}
		break;
	}
	return groupArray;		
	}
	
	@AfterTest(groups={"A","B"})
	private void tearDown() {
		// Close browser
		driver.quit();
	}
}



/*<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">
<suite name="test-parameter">

  <test name="example1">

    <groups>
        <run>
            <include name="A" />
        </run>
    </groups>

    <classes>
       <class
        name="guru99.ParameterByITestContextInDataprovider" />
    </classes>

  </test>


  <test name="example2">

    <groups>
        <run>
            <include name="B" />
        </run>
    </groups>

    <classes>
       <class
        name="guru99.ParameterByITestContextInDataprovider" />
    </classes>

  </test>

</suite>

[RemoteTestNG] detected TestNG version 6.14.3
Starting ChromeDriver 81.0.4044.69 (6813546031a4bc83f717a2ef7cd4ac6ec1199132-refs/branch-heads/4044@{#776}) on port 25179

Welcome ->Guru99 Your search key is->India
India::::India
Welcome ->Krishna Your search key is->UK
UK::::UK
Welcome ->Bhupesh Your search key is->USA
USA::::USA

Starting ChromeDriver 81.0.4044.69 (6813546031a4bc83f717a2ef7cd4ac6ec1199132-refs/branch-heads/4044@{#776}) on port 13912
Only local connections are allowed.
Please protect ports used by ChromeDriver and related test frameworks to prevent access by malicious code.
Welcome ->Unknown user Your search key is->Canada
Canada::::Canada
Welcome ->Unknown user Your search key is->Russia
Russia::::Russia
Welcome ->Unknown user Your search key is->Japan
Japan::::Japan

===============================================
test-parameter
Total tests run: 6, Failures: 0, Skips: 0
===============================================*/

