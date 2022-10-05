package com.cst438;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;

import com.cst438.domain.Student;
import com.cst438.domain.StudentRepository;

public class E2EAddStudentTest {
   public static final String CHROME_DRIVER_FILE_LOCATION = "C:\\Users\\Paola\\Downloads\\chromedriver_win32\\chromedriver.exe";
   public static final String URL = "http://localhost:3000";
   public static final int SLEEP_DURATION = 1000;
   public static final String TEST_USER_EMAIL = "teststudent@csumb.edu";
   public static final String TEST_NAME = "teststudent";
   
   @Autowired
   StudentRepository studentRepository;
   
   @Test
   public void addStudentTest() throws Exception {
      
      System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_FILE_LOCATION);
      WebDriver driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      
      try {
         
         driver.get(URL);
         Thread.sleep(SLEEP_DURATION);
         
         // click the add student button in the main page
         driver.findElement(By.xpath("//a[@id='addStudent']")).click();
         Thread.sleep(SLEEP_DURATION);
         
         // enter student name
         driver.findElement(By.xpath("//input[@id='sName']")).sendKeys(TEST_NAME);
         Thread.sleep(SLEEP_DURATION);
         
         // enter student email
         driver.findElement(By.xpath("//input[@id='sEmail']")).sendKeys(TEST_USER_EMAIL);
         Thread.sleep(SLEEP_DURATION);
         
         // click the submit button
         driver.findElement(By.xpath("//button[@id='submitNewStudent']")).click();
         
         
         
      } catch (Exception ex) {
         ex.printStackTrace();
         throw ex;
         
      } finally {
         driver.quit();
      }
   }
}