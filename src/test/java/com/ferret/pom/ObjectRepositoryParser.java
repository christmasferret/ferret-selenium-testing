package com.ferret.pom;

import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;

public class ObjectRepositoryParser {
	
	public Map<String, By> parseRepository(String repositoryFile) throws IOException{
		Properties props = new Properties();
		ClassLoader classLoader = getClass().getClassLoader();
		
		props.load(classLoader.getResourceAsStream(repositoryFile));
		
		Enumeration propNames = props.propertyNames();
		Map<String, By> locatorsMap = new HashMap<>();
		
		while(propNames.hasMoreElements()) {
			String name = propNames.nextElement().toString();
			String locator = props.getProperty(name);

			By by = parseLocator(locator);
			locatorsMap.put(name, by);
		}
		
		
		return locatorsMap;
	}
	
	private By parseLocator(String locator) {
		String locatorType = locator.split(":")[0];
		String locatorValue = locator.split(":")[1];
		
		locatorType = locatorType.toLowerCase();
		switch (locatorType) {
		case "id": return By.id(locatorValue);
			
		case "css": return By.cssSelector(locatorValue);
			
		case "xpath": return By.xpath(locatorValue);
			
		}
		return null;
	}

}
