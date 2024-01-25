package tataNeuTest;

import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import resources.Base;
import tataNueRepositery.CromaProductWebElements;

public class AddProductIntoCromaCart extends Base {
	
	WebDriver driver;
	CromaProductWebElements cpe;
	WebDriverWait wait;
	
	@BeforeTest
	public void lounchBrowserGetElementsAsses() throws IOException {
		
		driver=initializeDriver();
		wait=new WebDriverWait(driver, Duration.ofSeconds(30));
		cpe=new CromaProductWebElements(driver);
		driver.get("https://www.tatadigital.com/v2/homepage");
	}
	
	@Test
	public void cromaProductAddIntoCartTest() throws InterruptedException, IOException {
		
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView()",cpe.getScrollingElement());
		
	     WebElement cromaElement=wait.until(ExpectedConditions.visibilityOf(cpe.getHomeCromaOption()));
	     cromaElement.click();
		
	     Set<String> windowsList=driver.getWindowHandles();
	     Iterator<String> itrat=windowsList.iterator();
	     
	     while(itrat.hasNext()) {
	    	 String windowid=itrat.next();
	    	 driver.switchTo().window(windowid);
	    	 String title=driver.getTitle();
	    	 if(title.contains("Croma Electronics")) { break; }	    	 
	     }
	     
	     boolean homelodIsDisplay=cpe.getCromaHomePageLogo().isDisplayed();
	     Assert.assertTrue(homelodIsDisplay,"StepFail: Croma page not loading");
	     System.out.println("StepPass: We are on the Croma page");
	     
	     WebElement onePlusMobailElement=cpe.getOnepluseCE_Lite_5G_Gray();
	     
	     onePlusMobailElement.click();
	     
	     windowsList=driver.getWindowHandles();
	     itrat=windowsList.iterator();
	     
	     while(itrat.hasNext()) {
	    	 String window=itrat.next();
	    	 driver.switchTo().window(window);
	    	 String title= driver.getTitle();
	    	 if(title.contains("Buy OnePlus")) { break;}
	     }
	     
	     
	     js.executeScript("arguments[0].scrollIntoView()",cpe.getProductTitElementForAddingCart());
	     WebElement addCartButton=cpe.getAddCartButton();
	     addCartButton.click();
	     System.out.println("Product Successfully added into cart");
	     
	     cpe.getProceedCartButton().click();
	     
	     Thread.sleep(5000);
	     
	     //cpe.getCartElement().click();
	     System.out.println((cpe.getCartSummaryElement()).getText());
	     
	     WebElement removProductFC=cpe.getRemovProductFromCartButton();
	     removProductFC.click();
	     
	     WebElement conformRemoveProductYesElement=cpe.getConformYesRemoveProductButton();
	     conformRemoveProductYesElement.click();
	     
	     WebElement EmptyCartStatus=cpe.getEmtyCartTitalElement();
	     boolean isCartEmty=EmptyCartStatus.isDisplayed();
	     Assert.assertTrue(isCartEmty,"StepFail: Still Product in cart");
	     String cartStatus=EmptyCartStatus.getText();
	     System.out.println("StepPass: "+cartStatus);
	     //getScreenShot(cartStatus, driver);
	     //My comment
	}
	
    @AfterTest
    public void closeDriver() {
    	driver.quit();
    }

}
