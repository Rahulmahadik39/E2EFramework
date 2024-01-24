package webAppTestPackeg;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import resources.Base;

public class WebdriverManagerTest extends Base {
	
	@Test
	public void c() throws IOException {
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
 		 WebDriver driver=WebDriverManager.chromedriver().capabilities(options).create();
	    // new ChromeDriver();
		
		//WebDriver driver=initializeDriver();
		
		driver.get("https://www.google.com/");
		System.out.println(driver.getTitle());
		System.out.println(driver.getCurrentUrl());
		//File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		//String destinationFile = "./Screenshots/screenshot.png";
		//FileUtils.copyFile(src, new File(destinationFile));
		driver.quit();
		
	}
}
