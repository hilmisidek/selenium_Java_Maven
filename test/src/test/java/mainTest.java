//package com.selenium_maven;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
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

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;



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
        
        Row Header = sheet.getRow(0);
        //Cell Id = Header.getCell(0);
        //Cell name = Header.getCell(1);
        System.out.println(Header.getCell(0) + "|" + Header.getCell(1));
        
        Row row1 = sheet.getRow(1);
        //Cell cell1 = row1.getCell(1);
        System.out.println(row1.getCell(0) + "|" + row1.getCell(1));
        
        Row row2 = sheet.getRow(2);
        //Cell cell2 = row2.getCell(1);
        System.out.println(row2.getCell(0) + "|" + row2.getCell(1));
        
        Row row3 = sheet.getRow(3);
        //Cell cell3 = row3.getCell(1);
        System.out.println(row3.getCell(0) + "|" + row3.getCell(1));
        
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

    @Test
    public void test_get(){
        RestAssured.baseURI="https://demoqa.com/BookStore/v1/Books";
        RequestSpecification httpRequest=RestAssured.given();
        Response response = httpRequest.get("");
        System.out.println("Response body = " + response.asPrettyString());
        int status_code = response.getStatusCode();
        Assert.assertEquals(status_code, 200, "CORRECT STATUS CODE");
        
        //System.out.println("Response = " + response.prettyPrint());

    }

    @Test
    public void test_get_fail(){
        RestAssured.baseURI =  "https://demoqa.com/Account/v1/User/";
        RequestSpecification httpRequest=RestAssured.given();
        Response response = httpRequest.get("test");
        int status_code = response.getStatusCode();
        Assert.assertEquals(status_code, 401);

    }

    @Test
    public void get_header(){
        RestAssured.baseURI =  "https://demoqa.com/BookStore/v1/Books";
        RequestSpecification httpRequest=RestAssured.given();
        Response response = httpRequest.get("");

        Headers allHeaders = response.headers();

        for (Header header : allHeaders){
            System.out.println("Key : " + header.getName() + " Value : " + header.getValue());
        }

    }
    @Test
    public void get_specific_header(){
        RestAssured.baseURI = "https://demoqa.com/BookStore/v1/Books";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("");

        String content_type = response.header("Content-Type");
        System.out.println ("Content Type : " + content_type);
        Assert.assertEquals(content_type, "application/json; charset=utf-8", "content_type is correct");

        String server_type = response.header ("Server");
        System.out.println ("Server : " + server_type);
        Assert.assertEquals(server_type, "nginx/1.17.10 (Ubuntu)");

        String acceptlang = response.header ("Content-Encoding");
        System.out.println ("Content-Encoding : " + acceptlang);
        Assert.assertEquals(acceptlang, null);

    }    

    @Test
    public void read_body(){
        RestAssured.baseURI="https://demoqa.com/BookStore/v1/Books";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get();

        ResponseBody body = response.getBody();
        System.out.println(body.asPrettyString());
        
    }

    @Test
    public void check_body_column(){
        RestAssured.baseURI="https://demoqa.com/BookStore/v1/Books";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get();

        ResponseBody body = response.getBody();
        
        String body_content = body.asString();

        Assert.assertEquals(body_content.contains( "Speaking JavaScript"),true);

    }

    @Test
    public void check_single_body_node(){
        RestAssured.baseURI="https://apichallenges.eviltester.com/sim/entities";
        RequestSpecification httpRequest = RestAssured.given();
        ResponseBody response = httpRequest.get("1");

        JsonPath jsonBodyPath = response.jsonPath();

        System.out.println ("name : " + jsonBodyPath.get("name"));


    }




    @AfterClass
    public void end(){
        System.out.println("Test End");
        driver.close();
    }




}

