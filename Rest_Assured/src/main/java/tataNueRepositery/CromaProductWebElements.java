package tataNueRepositery;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CromaProductWebElements {
	
	WebDriver driver;
	 
	 public CromaProductWebElements(WebDriver driver) {
		 this.driver=driver;
		 PageFactory.initElements(driver, this);
	 }
	 
	 @FindBy(xpath = "//img[contains(@src,'https://cdn.sanity.io/images/nc2ghyne/production/e12cbaf')]")
	 private WebElement homeCromaOption;
	 
	 @FindBy(xpath = "//span[normalize-space()='Explore categories']")
	 private WebElement scrollingElement;
	 
	 @FindBy(xpath = "//img[contains(@src,'https://media-ik.croma.com') and @alt='Logo']")
	 private WebElement cromaHomePageLogo;
	 
	 @FindBy(xpath = "//a[contains(text(),'OnePlus Nord CE 3 Lite 5G (8GB RAM, 256GB, Chromatic Gray)')]")
	 private WebElement onepluseCE_Lite_5G_Gray;
	 
	 @FindBy(xpath = "//button[normalize-space()='Add to Cart']")
	 private WebElement addCartButton;
	 
	 @FindBy(id = "cart_count_notification")
	 private WebElement cartElement;
	 
	 @FindBy(xpath = "//h1[@class='pd-title pd-title-normal']")
	 private WebElement productTitElementForAddingCart;
	 
	 @FindBy(xpath = "//div[@class='box-title order-summary']")
	 private WebElement cartSummaryElement;
	 
	 @FindBy(xpath = "//button[normalize-space()='Proceed to Cart']")
	 private WebElement proceedCartButton;
	 
	 @FindBy(xpath = "//button[normalize-space()='Remove']")
	 private WebElement removProductFromCartButton;
	 
	 @FindBy(xpath = "//button[@class='btn btn-default' and text()='Yes']")
	 private WebElement conformYesRemoveProductButton;
	 
	 @FindBy(xpath = "//h3[normalize-space()='Your Cart is Empty']")
	 private WebElement emtyCartTitalElement;

	public WebElement getHomeCromaOption() {
		try {
			return homeCromaOption;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public WebElement getScrollingElement() {
		try {
			return scrollingElement;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public WebElement getCromaHomePageLogo() {
		try {
			return cromaHomePageLogo;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public WebElement getOnepluseCE_Lite_5G_Gray() {
		try {
			return onepluseCE_Lite_5G_Gray;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public WebElement getAddCartButton() {
		try {
			return addCartButton;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public WebElement getCartElement() {
		try {
			return cartElement;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public WebElement getProductTitElementForAddingCart() {
		try {
			return productTitElementForAddingCart;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public WebElement getCartSummaryElement() {
		try {
			return cartSummaryElement;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public WebElement getProceedCartButton() {
		try {
			return proceedCartButton;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public WebElement getRemovProductFromCartButton() {
		try {
			return removProductFromCartButton;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public WebElement getConformYesRemoveProductButton() {
		try {
			return conformYesRemoveProductButton;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public WebElement getEmtyCartTitalElement() {
		try {
			return emtyCartTitalElement;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
			
	
	 

}
