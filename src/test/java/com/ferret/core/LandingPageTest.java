package com.ferret.core;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LandingPageTest {
   private WebDriver driver;
   private String baseUrl;
   private boolean acceptNextAlert = true;

   @BeforeEach
   public void setUp() {
      driver = SeleniumTestUtilities.getHtmlUnitDriver();
      baseUrl = "http://localhost:8080/Lottery";
      driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
   }

   @Test
   public void testLandingPage() {
      driver.get(baseUrl + "/");
      assertEquals("Lottery", driver.getTitle());
  
      assertEquals("Welcome to our Lottery System", driver.findElement(By.cssSelector("h1")).getText());
      assertTrue(isElementPresent(By.id("size")));
      assertTrue(isElementPresent(By.id("range")));
 
      assertEquals("Pick My Lucky Numbers", driver.findElement(By.cssSelector("input[type=\"submit\"]")).getAttribute("value"));
      assertEquals("yes", driver.findElement(By.xpath("html/body/div[2]/form/input[3]")).getAttribute("value"));
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
