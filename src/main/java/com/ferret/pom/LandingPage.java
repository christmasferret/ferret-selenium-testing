package com.ferret.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPage {
	private WebDriver driver;

	public LandingPage(WebDriver driver) {
		this.driver = driver;
	}

	public String getPageTitle() {
		return driver.getTitle();
	}

	public String getMainHeader() {
		return driver.findElement(By.cssSelector("h1")).getText();
	}

	public String getButtonLabel() {
		return driver.findElement(By.cssSelector("input[type=\"submit\"]")).getAttribute("value");
	}

	public String getHiddenValue() {
		return driver.findElement(By.xpath("html/body/div[2]/form/input[3]")).getAttribute("value");
	}

	public boolean isSizePresent() {
		return isElementPresent(By.id("size"));
	}

	public boolean isRangePresent() {
		return isElementPresent(By.id("range"));
	}

	public void setSizeValue(String value) {
		WebElement element = driver.findElement(By.id("size"));
		element.clear();
		element.sendKeys(value);
	}

	public void setRangeValue(String value) {
		WebElement element = driver.findElement(By.id("range"));
		element.clear();
		element.sendKeys(value);
	}

	public void clickLuckyNumbersButton() {
		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
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
