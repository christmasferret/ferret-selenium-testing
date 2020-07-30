package com.ferret.pom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.TimeUnit;

import com.ferret.core.SeleniumTestUtilities;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class LandingPageTest_POM {
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
   public void testLandingPage() {
      driver.get(baseUrl + "/");
      LandingPage page = new LandingPage(driver);
      
      assertEquals("Lottery", page.getPageTitle());
  
      assertEquals("Welcome to our Lottery System", page.getMainHeader());
      assertTrue(page.isSizePresent());
      assertTrue(page.isRangePresent());
 
      assertEquals("Pick My Lucky Numbers", page.getButtonLabel());
      assertEquals("yes", page.getHiddenValue());
   }

   @AfterEach
   public void tearDown() {
      driver.quit();
   }
}
