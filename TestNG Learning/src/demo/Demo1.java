package demo;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Demo1{
    @Test
    public void test() {
        System.out.println("Inside Test Method");
    }
    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Inside Before Method");
    }
    @AfterMethod
    public void afterMethod() {
        System.out.println("Inside After Method");
    }
    @BeforeClass
    public void beforeClass() {
        System.out.println("Inside BeforeClass Method");
    }
    @AfterClass
    public void afterClass() {
        System.out.println("Inside AfterClass Method");
    }
    @BeforeTest
    public void beforeTest() {
        System.out.println("Inside BeforeTest Method");
    }
    @AfterTest
    public void afterTest() {
        System.out.println("Inside AfterTest Method");
    }
    @BeforeSuite
    public void beforeSuite() {
    System.out.println("Inside BeforeSuite Method");
    }
    @AfterSuite
    public void afterSuite() {
        System.out.println("Inside AfterSuite Method");

    }
}
