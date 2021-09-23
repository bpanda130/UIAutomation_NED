package applicationHooks;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.Factory.DriverFactory;
import com.Utility.ConfigReader;
import com.Utility.Browser;
import com.Utility.BrowserUtility;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class ApplicationHooks {
	
	private DriverFactory driverFactory;
	private WebDriver driver;
	private ConfigReader configReader;
	Properties prop;
	BrowserUtility browserUtil;
	Logger log = Logger.getLogger(ApplicationHooks.class);
	
	@Before(order = 0)
	public void getProperty(){
		configReader = new ConfigReader();
		prop = configReader.init_prop(Constant.CONFIG_FILEPATH);
	}
	
	@Before(order = 1)
	public void lunchBrowser(){
		log.info("Driver Intialization for required Browser");
		browserUtil = new BrowserUtility();
		Browser browser = browserUtil.getBrowser(prop);
		driverFactory = new DriverFactory();
		driver = driverFactory.init_driver(prop, browser);
		
	}
	
	@Before(order = 1)
	public void launchBrowser() {
		log.info("Driver Intialization for required Browser");
		browserUtil = new BrowserUtility();
		Browser browser = browserUtil.getBrowser(prop);
		driverFactory = new DriverFactory();
		driver = driverFactory.init_driver(prop, browser);
	}
	
	@After(order = 0)
	public void quitBrowser() {
		driver.quit();
	}
	
	@After(order = 2)
	public void tearDown(Scenario scenario) {
		if(scenario.isFailed()) {
			log.error("Capturing Screenshot for Failed Test cases.");
			String screenshotName = scenario.getName().replaceAll(" ", "_");
			byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(sourcePath, "image/png", screenshotName);
		}
	}

}
