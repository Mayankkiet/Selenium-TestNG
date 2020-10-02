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

-------------------------------------------------------------------------------------------------------------
List<WebElement> all=driver.findElements(By.xpath("//*[@id='customers']//td"));
System.out.println(all.size());
for(int i=0; i<all.size();i++)
		{
			String text=all.get(i).getText();
			System.out.println(text);
		}
		

//get the table
    WebElement statusTable = browser.findElement(By.id("projectstatus"));

    //Get all the rows in the table
    List<WebElement> allRows = statusTable.findElements(By.tagName("tr"));

    //Get the size(row no) of allRows
    int rowSize = allRows.size();
    System.out.println(rowSize);


    // locate the test xls file             
  File file = new File("e:\\Testing_emi.xls");              
  // create input stream                
  FileInputStream fis = new FileInputStream(file);              
  // create workbook                
  HSSFWorkbook wb = new HSSFWorkbook(fis);              
  // get sheet              
  HSSFSheet sheet1 = wb.getSheet("Sheet1");             
  // get rows               


   HSSFRow row;             

    for (int i=0; i<rowSize; i++) 
    {
        WebElement webRow = allRows.get(i);
        //Get all cell values in each row
        List<WebElement> allCells = webRow.findElements(By.tagName("td"));
        //System.out.println(allCells.size());

        if(allCells.size() > 1)
        {
            HSSFRow excelRow = sheet1.createRow(i);

            for (int j=0; j<allCells.size(); j++) 
            {
                WebElement webCell = allCells.get(j);
                String text = webCell.getText();
                if(text.length()>2)
                {
                    Cell excelCell = excelRow.createCell();
                    excelCell.setValue(webCell.getText());
                }                   
            }
        }
    }

  sheet1.close();			
