package webRepositery;

import java.util.List;

import javax.xml.xpath.XPath;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class BrokenLinkRepository {
	
	WebDriver driver;
	
	public BrokenLinkRepository(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "//a[@href]")
	private List<WebElement> availablePageLinks;
	
	@FindBy(xpath = "//div[@class='main-header']")
	private WebElement brokanlinkPage;
	
	public List<WebElement> getAvailablePageLinks() {
		try {
			return availablePageLinks;
		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
		}
	}

	public WebElement getBrokanlinkPage() {
		try {
			return brokanlinkPage;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	

}
