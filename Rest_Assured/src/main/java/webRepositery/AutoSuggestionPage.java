package webRepositery;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AutoSuggestionPage {
	WebDriver driver;
	public AutoSuggestionPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);		
	}
	
	@FindBy(name = "q")
	private WebElement searchElement;
	
	@FindBy(xpath = "//div[contains(text(),'Selenium WebDriver')and @aria-level='3']")
	private WebElement searchedElement;
	
	private By autosuggetion=By.xpath("//span[contains(normalize-space(),'selenium')]");
	
	@FindBy(xpath = "//img[contains(@src,'googlelogo_color_272x92dp.png')]")
	private WebElement googleIMElement;
	
	public WebElement getSearchElement() {
		try {
			return searchElement;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	

	public WebElement getSearchedElement() {
		try {
			return searchedElement;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}



	public WebElement getGoogleIMElement() {
		try {
			return googleIMElement;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public By getAutosuggetion() {
		try {
			return autosuggetion;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	

}
