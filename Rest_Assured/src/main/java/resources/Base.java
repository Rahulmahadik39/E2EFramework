package resources;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.github.dockerjava.api.model.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.asm.Advice.This;

public class Base {
	
	public WebDriver driver;
    public Properties prop;
	
	public WebDriver initializeDriver() throws IOException {
		String path="./src/main/java/resources/data.properties";
		FileInputStream file=new FileInputStream(path);
		prop=new Properties();
		prop.load(file);
		String browserName=prop.getProperty("Browser");
		
		if(browserName.contains("Chrome")) {
				
		   ChromeOptions chromeOptions=new ChromeOptions();
		   chromeOptions.addArguments("--remote-allow-origins=*");
		   driver=WebDriverManager.chromedriver().capabilities(chromeOptions).create();
		   driver.manage().window().maximize();
	       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	       
		   return driver;
		
		}else if(browserName.contains("Firefox")) {
			
			driver=WebDriverManager.firefoxdriver().create();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			return driver;
			
		}else if(browserName.contains("Edge")) {
			driver=WebDriverManager.edgedriver().create();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			return driver;
		}
		else {
			return null;
		}
	}
	
	public void getScreenShot(String name, WebDriver driver) throws IOException {
		 
		     File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		     String destination="./Screenshots/"+name+"screenshot.png";
		     FileUtils.copyFile(src, new File(destination));
		
	}
	
/*	
	public static void main(String[] args) throws IOException, InterruptedException {
		Base base=new Base();
		WebDriver driver= base.initializeDriver();
		driver.get("https://www.google.com/");
		String name=driver.getTitle();
		System.out.println(name);
		base.getScreenShot(name, driver);
		Thread.sleep(3000);
		driver.quit();
		
	}
*/

}
