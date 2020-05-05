package dmitry;


import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class NoSuchElementException {
	private WebDriver driver;

	@Parameters({ "browser" })
	@BeforeMethod(alwaysRun = true)
	private void setUp(@Optional("chrome") String browser) {
//		Create driver
		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "D:\\Selenium JARS and Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			break;

		case "IE":
			System.setProperty("webdriver.ie.driver", "D:\\Selenium JARS and Drivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			break;

		default:
			System.out.println("Do not know how to start " + browser + ", starting chrome instead");
			System.setProperty("webdriver.chrome.driver", "D:\\Selenium JARS and Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			break;
		}


		// sleep for 3 seconds
		sleep(3000);

		// maximize browser window
		driver.manage().window().maximize();

		// implicit wait
		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test(priority = 1)
	public void notVisibleTest() {
		// open the page http://the-internet.herokuapp.com/dynamic_loading/1
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/1");

		// Find locator for startButton and click on it
		WebElement startButton = driver.findElement(By.xpath("//div[@id='start']/button"));
		startButton.click();

		// Then get finish element text
		WebElement finishElement = driver.findElement(By.id("finish"));

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(finishElement));

		String finishText = finishElement.getText();

		// compare actual finish element text with expected "Hello World!" using Test NG
		// Assert class
		Assert.assertTrue(finishText.contains("Hello World!"), "Finish text: " + finishText);

		// startButton.click();

	}

	@Test(priority = 2)
	public void timeoutTest() {
		// open the page http://the-internet.herokuapp.com/dynamic_loading/1
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/1");

		// Find locator for startButton and click on it
		WebElement startButton = driver.findElement(By.xpath("//div[@id='start']/button"));
		startButton.click();

		// Then get finish element text
		WebElement finishElement = driver.findElement(By.id("finish"));

		WebDriverWait wait = new WebDriverWait(driver, 2);
		try {
			wait.until(ExpectedConditions.visibilityOf(finishElement));
		} catch (TimeoutException exception) {
			System.out.println("Exception catched: " + exception.getMessage());
			sleep(3000);
		}

		String finishText = finishElement.getText();

		// compare actual finish element text with expected "Hello World!" using Test NG
		// Assert class
		Assert.assertTrue(finishText.contains("Hello World!"), "Finish text: " + finishText);

		// startButton.click();

	}

	@Test(priority = 3)
	public void noSuchElementTest() {
		// open the page
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

		// Find locator for startButton and click on it
		WebElement startButton = driver.findElement(By.xpath("//div[@id='start']/button"));
		startButton.click();

		WebDriverWait wait = new WebDriverWait(driver, 20);
		Assert.assertTrue(
				wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("finish"), "Hello World!")),
				"Couldn't verify expected text 'Hello World!'");
//Below code is also fine
//		WebElement finishElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("finish")));
//    since presenceOfElementLocated returns web element so assigning that in finishElement
//		String finishText = finishElement.getText();
//
//		// compare actual finish element text with expected "Hello World!" using Test NG
//		// Assert class
//		Assert.assertTrue(finishText.contains("Hello World"), "Finish text: " + finishText);
	}

	private void sleep(long m) {
		try {
			Thread.sleep(m);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@AfterMethod(alwaysRun = true)
	private void tearDown() {
		// Close browser
		driver.quit();
	}

}


/*<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">
<suite name="Exceptions Tests Suite" verbose="1">

	<test name="Exceptions Test">
		<parameter name="browser" value="chrome"></parameter>
		<classes>
			<class name="dmitry.NoSuchElementException">
				<methods>
					<include name="noSuchElementTest"></include>
				</methods>
			</class>
		</classes>
	</test>

</suite>

if expected - Hello Worrld!

org.openqa.selenium.TimeoutException: Timed out after 20 seconds waiting for text ('Hello Worrld!') to be present in element found by By.id: finish
Build info: version: '2.51.0', revision: '1af067d', time: '2016-02-05 19:11:55'
System info: host: 'Mayank-PC', ip: '192.168.0.103', os.name: 'Windows 7', os.arch: 'x86', os.version: '6.1', java.version: '1.8.0_201'
Driver info: org.openqa.selenium.chrome.ChromeDriver
Capabilities [{mobileEmulationEnabled=false, timeouts={implicit=0, pageLoad=300000, script=30000}, hasTouchScreen=false, platform=XP, acceptSslCerts=false, goog:chromeOptions={debuggerAddress=localhost:52316}, acceptInsecureCerts=false, webStorageEnabled=true, browserName=chrome, takesScreenshot=true, javascriptEnabled=true, setWindowRect=true, unexpectedAlertBehaviour=ignore, applicationCacheEnabled=false, rotatable=false, networkConnectionEnabled=false, chrome={chromedriverVersion=81.0.4044.69 (6813546031a4bc83f717a2ef7cd4ac6ec1199132-refs/branch-heads/4044@{#776}), userDataDir=C:\Windows\TEMP\scoped_dir4696_920101741}, takesHeapSnapshot=true, pageLoadStrategy=normal, strictFileInteractability=false, databaseEnabled=false, handlesAlerts=true, version=81.0.4044.129, browserConnectionEnabled=false, proxy={}, nativeEvents=true, locationContextEnabled=true, cssSelectorsEnabled=true, webauthn:virtualAuthenticators=true}]
Session ID: cad0aacfb151d3228c46ce7d95b45d21
*/