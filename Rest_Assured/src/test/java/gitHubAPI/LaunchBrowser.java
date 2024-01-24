package gitHubAPI;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LaunchBrowser {
	
	
	public static void main(String []args) {
		WebDriver driver;
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\Rahul\\OneDrive\\Documents\\geckodriver-v0.33.0-win64\\geckodriver.exe");
		driver=new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://www.google.com/");
		
		
		
		
		driver.findElement(By.xpath("//textarea[@name='q']")).sendKeys("selenium");
	}

}
