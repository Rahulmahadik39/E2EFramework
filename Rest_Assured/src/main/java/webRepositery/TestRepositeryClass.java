package webRepositery;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TestRepositeryClass {
	WebDriver driver;
	
	public TestRepositeryClass(WebDriver driver) {

		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//a[text()='Select Product Type']") 
	 private WebElement selectProductType;
	
	@FindBy(xpath = "//img[@class='closeSummer']")
	private WebElement imageCloserbutton;
	
	@FindBy(xpath = "//li[text()='Cards']")
	private WebElement cardsOption;
	
	@FindBy(xpath = "//li[text()='Credit Cards']")
	private WebElement creditCardsElement;
	
	@FindBy(xpath = "//a[contains(@class,'btn btn-primary btn-custom btn-primary-custom myButton exclude-me')]")
	private WebElement checkOfferbutton;
	
	@FindBy(xpath = "//a[text()='Select Product']")
	private WebElement selectproduct;
	
	

	public WebElement getSelectproduct() {
		try {
			return selectproduct;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public WebElement getCheckOfferbutton() {
		try {
			return checkOfferbutton;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public WebElement getCreditCardsElement() {
		try {
			return creditCardsElement;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public WebElement getCardsOption() {
		try {
			return cardsOption;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public WebElement getImageCloserbutton() {
		try {
			return imageCloserbutton;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public WebElement getSelectProductType() {
		try {
			return selectProductType;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	

}
