package com.ferret.core;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InvalidSizeTest {
   private WebDriver driver;
   private String baseUrl;
   private boolean acceptNextAlert = true;

   @BeforeEach
   public void setUp() {
      driver = SeleniumTestUtilities.getChromeDriver();
      baseUrl = "http://localhost:8080/Lottery";
      driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
   }

   @Test
   public void testInvalidSize() {
      driver.get(baseUrl + "/");
      driver.findElement(By.id("size")).clear();
      driver.findElement(By.id("size")).sendKeys("0");
      driver.findElement(By.id("range")).clear();
      driver.findElement(By.id("range")).sendKeys("12");
      driver.findElement(By.cssSelector("input[type='submit']")).click();
      assertEquals("Lottery Error", driver.getTitle());
      assertTrue(driver.findElement(By.cssSelector("h3.center"))
    		  .getText()
    		  .matches("^The value supplied for the Number to pick is invalid!$"));
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
