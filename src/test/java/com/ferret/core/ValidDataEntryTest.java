package com.ferret.core;

/**
 * <p>
 * This component and its source code representation are copyright protected and
 * proprietary to Trivera Technologies, LLC, Worldwide D/B/A Trivera Technologies
 *
 * This component and source code may be used for instructional and evaluation
 * purposes only. No part of this component or its source code may be sold,
 * transferred, or publicly posted, nor may it be used in a commercial or
 * production environment, without the express written consent of the Trivera
 * Group, Inc.
 *
 * Copyright (c) 2020 Trivera Technologies, LLC. http://www.triveratech.com
 * 
 * </p>
 * 
 * @author Trivera Technologies Tech Team.
 */
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ValidDataEntryTest {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	
	@BeforeEach
	public void setUp() throws Exception {
	    driver = SeleniumTestUtilities.getChromeDriver();
		baseUrl = "http://localhost:8080/Lottery";

	}
	
	@ParameterizedTest
	@CsvSource({"3, 99", "3, 11", "98, 99" })
	public void testValidDatasets(String size, String range) {
		driver.get(baseUrl + "/");

		WebElement sizeField = driver.findElement(By.id("size"));
		sizeField.clear();
		sizeField.sendKeys(size);

		int sizeValue = Integer.parseInt(size);

		WebElement rangeField = driver.findElement(By.id("range"));
		rangeField.clear();
		rangeField.sendKeys(range);

		WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit']"));
		submitButton.click();

		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.titleContains("Lottery Results"));
		String expectedTitle = "Lottery Results";
		String actualTitle = driver.getTitle();
		assertEquals(expectedTitle, actualTitle);

		if (!isElementPresent(By.xpath("html/body/table/tbody/tr[2]/td[1]"))) {
			fail("First column is not found");
		}

		if (isElementPresent(By.xpath("html/body/table/tbody/tr[2]/td[2]"))) {
			fail("Second column is found");
		}

		List<WebElement> entries1 = driver.findElements(By.xpath("html/body/table/tbody/tr[2]/td[1]/span"));

		assertTrue(entries1.size() == sizeValue);

	}


	@ParameterizedTest
	@CsvSource({"2, 100", "2, 10", "99, 100", "2, 11", "3, 10", "99, 100"  })
	public void testInvalidValidDatasets(String size, String range) {
		driver.get(baseUrl + "/");

		WebElement sizeField = driver.findElement(By.id("size"));
		sizeField.clear();
		sizeField.sendKeys(size);

		int sizeValue = Integer.parseInt(size);

		WebElement rangeField = driver.findElement(By.id("range"));
		rangeField.clear();
		rangeField.sendKeys(range);

		WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit']"));
		submitButton.click();

		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.titleContains("Lottery Error"));
		String expectedTitle = "Lottery Error";
		String actualTitle = driver.getTitle();
		assertEquals(expectedTitle, actualTitle);

	}
	
	@AfterEach
	public void tearDown() throws Exception {
		driver.quit();
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
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
