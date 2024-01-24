package webAppTestPackeg;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import resources.Base;
import webRepositery.*;


public class TestBrokenLink extends Base {
	
      WebDriver driver;
      BrokenLinkRepository blr;
       
      @BeforeTest
      public void preRequesite() throws IOException {
    	  //Nice work Good luck
    	 driver=initializeDriver();
    	  
    	 blr= new BrokenLinkRepository(driver);
    	  driver.get("https://demoqa.com/broken");
      }
      
      
      @Test
      public void testLinks() throws IOException, InterruptedException {
    	  
    	  boolean isdisplay=blr.getBrokanlinkPage().isDisplayed();
    	  
    	  Assert.assertTrue(isdisplay, "Failed: Demo app is not found");
    	  System.out.println("Step Pass: We are on the Demo app");
    	  
    	  List<WebElement> links=blr.getAvailablePageLinks();
    	  
    	 int linkCount= links.size();
    	 
    	 System.out.println("Number of link available on the page are: "+linkCount);
    	 
    	 for(WebElement temp: links) {
    		 
    		 String link=temp.getAttribute("href");
    		 
    		 URL url=new URL(link);
    		 
    		 HttpURLConnection urlConnect=(HttpURLConnection) url.openConnection();
    		 urlConnect.connect();
    		 Thread.sleep(2000);
    		 int responceCode=urlConnect.getResponseCode();
    		 System.out.println("Link ResponceCode: "+responceCode);
    		 
    		 if(responceCode>=400) {
    			 System.out.println("Broken Link: "+link);
    		 }
    		 else {
    			 System.out.println("Working link: "+ link);
    		 }
    		
    		 
    		 
    	 }
    	  
      }
      
      @AfterTest
      public void closeApp() {
    	  
    	  driver.quit();
      }

}
