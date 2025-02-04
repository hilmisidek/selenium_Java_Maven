//package com.selenium_maven;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class mainTest {
  
    
    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "\\repor\\selenium_java_maven\\test\\src\\test\\java\\chromedriver.exe");
       
        
    };
    WebDriver driver= new ChromeDriver();
    // code that will be invoked when this test is instantiated

    
    
    @Test
    public void tryTest(){ 
       
        driver.get("https://www.google.com");
    
        //System.out.println("Hello world!");


    }

    @Test
    public void test_Assert()
    {
        Integer x = 1;
        Assert.assertEquals(x, 1);

    }

    @AfterClass
    public void end(){
        System.out.println("Test End");
        driver.close();
    }


}

