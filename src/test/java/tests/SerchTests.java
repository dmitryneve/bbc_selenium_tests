package tests;

import base.BasePage;
import base.BaseTest;
import pages.HomePage;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SerchTests extends BaseTest {

	@Test

	public void openWebsite() {
		String title = driver.getTitle();
		Assert.assertTrue(title.contains("BBC"), "Title does't contain BBC. Actual Title is: " + title);
		log.info("Page title: " + title);
	
		}
	@Test
	
	public void serchForBusiness() {
		HomePage home = new HomePage(driver);
		home.clickHamburgerButton();
		
		home.searchForWitSearchButton("Business");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.urlContains("search"));

		String currentUrl = driver.getCurrentUrl(); 
		Assert.assertTrue(currentUrl.contains("Business"), "Search Failed. URL: " + currentUrl);
		System.out.println(currentUrl);
			
	}
	
	@Test
	
	public void searchForSport() {
		HomePage home = new HomePage(driver);
		home.clickHamburgerButton();
		
		home.searchForWithEnter("Sport");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.urlContains("search"));
		
		String currentUrl = driver.getCurrentUrl();
		Assert.assertTrue(currentUrl.contains("Sport"), "Search Failed. URL: " + currentUrl);
		System.out.println(currentUrl);
		}
	
	@Test
	
	public void getElementsTitle() {
		HomePage home = new HomePage(driver);
		home.clickHamburgerButton();
		home.searchForWithEnter("Health");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.urlContains("Health"));
		String currentUrl = driver.getCurrentUrl();
		Assert.assertTrue(currentUrl.contains("Health"), "Search Failed. URL: " + currentUrl);
		System.out.println(currentUrl);
		List<String> titles = home.getSearchResultTitles();
		log.debug("Found: " + titles.size() + " search results");
		
		for (int i = 0; i < Math.min(5, titles.size()); i++) {
			System.out.println((i+1) + ". " + titles.get(i));
			
			Assert.assertTrue(titles.size() > 0, "No search results found"); 
		}
		
		

			
		}
	@Test
	
	public void searchForItemThatFails() {
		HomePage home = new HomePage(driver);
		home.clickHamburgerButton();
		home.searchForWitSearchButton("FailedTest");
		
		log.info("This test will fail intentionally to demonstrate screenshot");
		Assert.fail("This test is designed to fail for screenshot demonstration");
		
		
		
		
	}
	}
	


