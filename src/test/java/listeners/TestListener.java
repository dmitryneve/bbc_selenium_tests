package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;
import org.bouncycastle.util.Exceptions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;


public class TestListener implements ITestListener {
	
	private  WebDriver driver;
	
	public TestListener(WebDriver driver) { 
		this.driver = driver;
	}
	@Override
	public void onTestFailure(ITestResult result) { 
		System.out.println("Test failed: " + result.getName());
		
		if (driver != null) { 
			try {
				File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				String screenshotName = result.getName() + "_" + System.currentTimeMillis() + ".png";
				
				FileUtils.copyFile(screenshot, new File("screenshots/" + screenshotName));
				System.out.println("Screenshot save: " + screenshotName);
			} catch (Exception e) { 
				System.out.println("Failed to take a screenshot: " + e.getMessage());
			}
		}
		
	}

	@Override
	public void onTestSuccess(ITestResult result) { 
		System.out.println("Test success: " + result.getName());
		
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("Test skipped" + result.getName());
	}
	
}
