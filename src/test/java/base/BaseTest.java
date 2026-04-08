package base;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;
import utils.DriverFactory;


public class BaseTest {
	protected WebDriver driver;
	protected Logger log = LoggerFactory.getLogger(getClass());
	
	@BeforeMethod
	public void setUp() {
		
		String browser = ConfigReader.getProperty("browser");
		driver = DriverFactory.createDriver(browser);
		driver.manage().window().maximize();
		
		String url = ConfigReader.getProperty("baseUrl");
		driver.get(url);
		

	}
	@AfterMethod
	public void tearDown(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			try {
				File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				String screenshotName = result.getName() + "_" + System.currentTimeMillis() + ".png";
				FileUtils.copyFile(screenshot, new File("screenshots/" + screenshotName));
				log.info("Screenshot saved: " + screenshotName);
			} catch (Exception e) { 
				log.error("Failed to take screenshot: " + e.getMessage());
				
			}
		}
		
		if (driver != null) {
			driver.quit();
		}
	}
	

}
