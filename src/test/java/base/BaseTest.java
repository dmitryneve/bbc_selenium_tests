package base;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.WebDriver;
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
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
	

}
