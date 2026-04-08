package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import base.BasePage;
import utils.ConfigReader;


public class HomePage extends BasePage {
	// Locators 
	private static final  Logger log = LoggerFactory.getLogger(HomePage.class);
	private By hamburgerButton = By.cssSelector("button[aria-label='Open menu']");
    private By searchField = By.cssSelector("input[data-testid='search-input-field']");     
    private By searchButton = By.cssSelector("button[data-testid='search-input-search-button']");
    private By searchResultTitle = By.cssSelector("h2[data-testid='card-headline']");

    public HomePage(WebDriver driver) {
        super(driver);
    	
    }
    public void clickHamburgerButton() {
    	driver.findElement(hamburgerButton).click();
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	wait.until(ExpectedConditions.presenceOfElementLocated(searchField));
    }
    
    public void searchForWitSearchButton(String product) {  // Verifying the button search works
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchField));
        driver.findElement(searchField).click();
        driver.findElement(searchField).clear();
        driver.findElement(searchField).sendKeys(product);
        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        driver.findElement(searchButton).click();
    
    	
    }

	public void searchForWithEnter(String product) {   // Verifying the search can be done with clicking button ENTER
		driver.findElement(searchField).click();
		driver.findElement(searchField).clear();
		driver.findElement(searchField).sendKeys(product);
		driver.findElement(searchField).sendKeys(Keys.ENTER);

      }
    

		public String getPageTitle() {
			return driver.getTitle();
		}

		public List<String> getSearchResultTitles() {
			List<String> titles = new ArrayList<>();
			List<WebElement> resultElements = driver.findElements(By.cssSelector("h2[data-testid='card-headline']"));

			for (WebElement element : resultElements) {
				titles.add(element.getText());
			
				
			}
			return titles;
			
		}
}