package com.ferret.pom;

import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class LandingPage {
	private WebDriver driver;
	private Map<String, By> locators;
	
	public LandingPage(WebDriver driver) {
		this.driver = driver;
		ObjectRepositoryParser or = new ObjectRepositoryParser();
		try {
			locators = or.parseRepository("locators.properties");
		}
		catch (IOException e) {
			System.out.println("Could not find property file");
		}
	}

	public String getPageTitle() {
		return driver.getTitle();
	}

	public String getMainHeader() {
		return driver.findElement(locators.get("landingPage.mainTitle")).getText();
	}

	public String getButtonLabel() {
		return driver.findElement(locators.get("landingPage.button")).getAttribute("value");
	}

	public String getHiddenValue() {
		return driver.findElement(locators.get("landingPage.hiddenValue")).getAttribute("value");
	}

	public boolean isSizePresent() {
		return isElementPresent(locators.get("landingPage.size"));
	}

	public boolean isRangePresent() {
		return isElementPresent(locators.get("landingPage.range"));
	}

	public void setSizeValue(String value) {
		WebElement element = driver.findElement(locators.get("landingPage.size"));
		element.clear();
		element.sendKeys(value);
	}

	public void setRangeValue(String value) {
		WebElement element = driver.findElement(locators.get("landingPage.range"));
		element.clear();
		element.sendKeys(value);
	}

	public void clickLuckyNumbersButton() {
		driver.findElement(locators.get("landingPage.button")).click();
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
}
