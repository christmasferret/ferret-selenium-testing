package com.ferret.pom;

import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LotteryErrorPage {
	private WebDriver driver;
	private Map<String, By> locators;

	public LotteryErrorPage(WebDriver driver) {
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
		return driver.findElement(locators.get("errorPage.mainTitle")).getText();
	}
	
	public String getError() {
		return driver.findElement(locators.get("errorPage.error")).getText();
	}
	
	public void clickTryAgainButton() {
		driver.findElement(locators.get("errorPage.button")).click();
	}

}
