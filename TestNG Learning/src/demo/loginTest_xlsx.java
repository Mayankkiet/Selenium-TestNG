/** (1) Open URL
 *  (2) Give Implicit wait for 10 seconds
 *  (2) Fetch User name , Password from xlsx in the text boxes and click Submit
 *  (3) Give Explicit Wait of 2 seconds and observe the success message
 *  (4) Take screenshot of success message
 *  (5) Print the success message in the sheet (Column C) and close workbook*
 *  (4) Close URL
 *  
 */

package demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class loginTest_xlsx {

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
	public void test() throws IOException {
		WebElement loginUrl = driver.findElement(By.linkText("Form Authentication"));
		loginUrl.click();

		// Path from where the excel file has to be read
		String filePath = System.getProperty("user.dir") + "\\SeleniumCredentials2.xlsx";

		// File input stream which needs the input as the file location
		FileInputStream fileStream = new FileInputStream(filePath);

		// Workbook reference of the excel file
		XSSFWorkbook workbook = new XSSFWorkbook(fileStream);

		// Sheet which needs to be accessed from within the workbook
		XSSFSheet sheet = workbook.getSheet("Credentials");

		// Count the number of columns
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();

		for (int i = 1; i <= rowCount; i++) {
			// Pass the row number and the cell number from where the value has to be
			// fetched

			driver.findElement(By.xpath("//input[@name ='username']"))
					.sendKeys(sheet.getRow(i).getCell(0).getStringCellValue());
			driver.findElement(By.xpath("//input[@name ='password']"))
					.sendKeys(sheet.getRow(i).getCell(1).getStringCellValue());
			WebDriverWait wait = new WebDriverWait(driver, 10);
			WebElement loginButton = driver.findElement(By.xpath("//button[@class='radius']"));
			wait.until(ExpectedConditions.elementToBeClickable(loginButton));
			loginButton.click();

			String expectedMessage = "You logged into a secure area!";

			String actualMessage = driver.findElement(By.xpath("//div[@class='flash success']")).getText();
			Assert.assertTrue(actualMessage.contains(expectedMessage),
					"Actual message does not contain expected message (Checked by AssertTrue).\nActual Message: "
							+ actualMessage + "\nExpected Message: " + expectedMessage);

			// Get the current row where the data has to be written
			Row newRow = sheet.getRow(i);

			// Create a new cell with reference to the row
			newRow.createCell(2).setCellValue(actualMessage);
			FileOutputStream fileOutputStream = new FileOutputStream(filePath);

			// Write the workbook
			workbook.write(fileOutputStream);
			workbook.close();

			// Type cast the driver reference variable with TakesScreenshot for access the
			// methods from TakesScreenshot interface
			// getScreenshotAs method will take argument for the output type of the file
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			// Using the FileUtils class copy the generated screenshot file to any location
			FileUtils.copyFile(scrFile, new File("C:\\Users\\Mayank\\git\\Test1\\TestNG Learning\\Message1.jpg"));

			// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ctl00_ctl00_divWelcome']")));
			driver.findElement(By.xpath("//i[@class='icon-2x icon-signout']")).click();
		}

	}

	@AfterClass
	public void teardown() {
		// Close the browser
		driver.quit();
	}
}
