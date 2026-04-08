package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public abstract class BasePage {
	protected WebDriver driver;
	protected WebDriverWait wait; 
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
	}
	// Waiting of visibility of the element
	protected void waitForVisibility(By locator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		
	}
	// Waiting when the element become clickable 
	protected void waitForClickable(By locator) {
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		
	}
	// Click with wait
	protected void click(By locator) {
		waitForClickable(locator);
		driver.findElement(locator).click();
		
	}
	// Text input
	protected void type(By locator, String text) {
		waitForVisibility(locator);
		WebElement element = driver.findElement(locator);
		element.clear();
		element.sendKeys(text);
		
		
	}
	// Getting text
	protected String getText(By locator) {
		waitForVisibility(locator);
		return driver.findElement(locator).getText();
		
	}
	// Element is Displayed
	protected boolean isDisplayed(By locator) {
		try {
			return driver.findElement(locator).isDisplayed();
			
		}catch (Exception e) {
			return false;
		}
	}
	
}
