package resources;


import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Base {
	
	WebDriver driver;
	public WebDriver initializeDriver() throws IOException {
		
		 System.setProperty("webdriver.chrome.driver", "C:\\Users\\Rahul\\OneDrive\\Documents\\Driver\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		   ChromeOptions chromeOptions=new ChromeOptions();
		   chromeOptions.setBinary("C:\\Users\\Rahul\\OneDrive\\Documents\\Driver\\chrome-win64\\chrome-win64\\chrome.exe");
		   chromeOptions.addArguments("--remote-allow-origins=*");
		   driver=new ChromeDriver(chromeOptions);
		   driver.manage().window().maximize();
	       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}


}
