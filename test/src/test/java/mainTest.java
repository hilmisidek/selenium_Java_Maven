//package com.selenium_maven;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.text.html.HTMLDocument.Iterator;
import java.util.TreeMap;

import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;


public class mainTest {
  
    
    @BeforeClass
    public void setUp() {
    System.setProperty("webdriver.chrome.driver", "\\repor\selenium_java_maven\\test\\src\\test\\java\\chromedriver.exe");
    
            
    }
    WebDriver driver= new ChromeDriver();
    // code that will be invoked when this test is instantiated

    @Test
    public void writeExcel(){
        
        XSSFWorkbook workbook= new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Employee Data");
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
    data.put("1", new Object[] {"ID", "NAME", "LASTNAME"});
    data.put("2", new Object[] {1, "Amit", "Shukla"});
    data.put("3", new Object[] {2, "Lokesh", "Gupta"});
    data.put("4", new Object[] {3, "John", "Adwards"});
    data.put("5", new Object[] {4, "Brian", "Schultz"});
    
    //Iterate over data and write to sheet

        Set<String> keyset = data.keySet();
        int rownum = 0;
        for (String key : keyset) {

        Row row = sheet.createRow(rownum++);
        Object [] objArr = data.get(key);
        int cellnum = 0;
        for (Object obj : objArr)
        {
            Cell cell = row.createCell(cellnum++);
            if(obj instanceof String)
                cell.setCellValue((String)obj);
            else if(obj instanceof Integer)
                cell.setCellValue((Integer)obj);
        }
        }


    try {
      FileOutputStream out = new FileOutputStream(new File("howtodoinjava_demo.xlsx"));
      workbook.write(out);
      out.close();
      System.out.println("howtodoinjava_demo.xlsx written successfully on disk.");
    } 
    catch (Exception e) {
      e.printStackTrace();
    }
        
    }

    @Test
    public void readExcel() throws IOException{
    FileInputStream file = new FileInputStream(new File("howtodoinjava_demo.xlsx"));
        //Create Workbook instance holding reference to .xlsx file
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        //Get first/desired sheet from the workbook
        XSSFSheet sheet = workbook.getSheetAt(0);
        Row row = sheet.getRow(0);
        Cell cell = row.getCell(0);
        System.out.println(sheet.getRow(0).getCell(0));
        Row row1 = sheet.getRow(1);
        Cell cell1 = row1.getCell(1);
        System.out.println(sheet.getRow(0).getCell(1));
        Row row2 = sheet.getRow(1);
        Cell cell2 = row2.getCell(1);
        System.out.println(sheet.getRow(1).getCell(0));
        Row row3 = sheet.getRow(1);
        Cell cell3 = row3.getCell(1);
        System.out.println(sheet.getRow(1).getCell(1));
        
        System.out.println("");
        file.close();
        
        

    }


    
    
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

