package com.ferret.core;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class LandingPageScreenshotTest {
   private WebDriver driver;
   private String baseUrl;
   private boolean acceptNextAlert = true;
   private StringBuffer errors = new StringBuffer();

   @BeforeEach
   public void setUp() {
      driver = SeleniumTestUtilities.getChromeDriver();
      baseUrl = "http://localhost:8080/Lottery";
      driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
   }

   @Test
   public void testLandingPage() throws Exception{
      driver.get(baseUrl);
      TakesScreenshot screenshot =((TakesScreenshot)driver);
      File source=screenshot.getScreenshotAs(OutputType.FILE);
      Path sourcePath = source.toPath();
      Path destination=(new File("C:\\StudentWork\\code\\Test\\temp.png")).toPath();
      Files.copy(sourcePath, destination);
   }

   private boolean isElementPresent(By by) {
      try {
         driver.findElement(by);
         return true;
      } catch (NoSuchElementException e) {
         return false;
      }
   }

   @AfterEach
   public void tearDown() {
      driver.quit();
      String errorsString = errors.toString();
      if(!"".contentEquals(errorsString)) {
        fail(errorsString);
      }
   }

   private boolean isAlertPresent() {
      try {
         driver.switchTo().alert();
         return true;
      } catch (NoAlertPresentException e) {
         return false;
      }
   }

   private String closeAlertAndGetItsText() {
      try {
         Alert alert = driver.switchTo().alert();
         String alertText = alert.getText();
         if (acceptNextAlert) {
            alert.accept();
         } else {
            alert.dismiss();
         }
         return alertText;
      } finally {
         acceptNextAlert = true;
      }
   }
}
