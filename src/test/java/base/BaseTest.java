package base;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;
import utils.DriverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseTest {

    protected WebDriver driver;
    protected Logger log = LoggerFactory.getLogger(getClass());
    public WebDriver getDriver() {
        return driver;
    }
    @BeforeMethod
    public void setUp() {
        DriverFactory.createDriver(ConfigReader.getProperty("browser"));
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.getProperty("baseUrl"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {

        if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println("FAIL detected in BaseTest");
        }

        DriverFactory.quitDriver();
    }
}
