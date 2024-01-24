package webAppTestPackeg;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import groovy.transform.builder.InitializerStrategy.SET;
import groovyjarjarantlr4.v4.codegen.model.Action;
import resources.Base;
import webRepositery.*;

public class AnotestionTest extends Base {
	
	 WebDriver driver;	 	 
	 TestRepositeryClass tr;
	 @BeforeTest
	 public void launchBrowser() throws IOException {
		 
		driver=initializeDriver();
		 
		tr=new TestRepositeryClass(driver);
	 }

	 @Test
	 public void hdfcbankTest() throws InterruptedException {
		 
		 driver.get("https://www.hdfcbank.com/");
		 
		
		
		 tr.getImageCloserbutton().click();
		 
		 tr.getSelectProductType().click();
		 
		tr.getCardsOption().click();
		tr.getSelectproduct().click();
		tr.getCreditCardsElement().click();
		
		tr.getCheckOfferbutton().click();
		
		Set<String> refStrings=driver.getWindowHandles();
		
		for(String tem:refStrings) {
			
				driver.switchTo().window(tem);
				System.out.println(driver.getTitle());
			
		}
		driver.close();
		
		Thread.sleep(3000);
		 	 
	
	 }
	 
	 
	 /*
	 
	 @Test
	 public void start() throws InterruptedException {
		 
		 driver.get("https://www.google.com/");
		 
		 
		for(WebElement tep:tr.getLangweges()) {
			System.out.println(tep.getAttribute("href"));
		 }
		
		
		
		Thread.sleep(3000);
	 }*/
	 
	 @AfterClass
	 public void closeBrowser() {
		 driver.quit();
	 }
	 

}
