package applicationHooks;

import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.qa.Factory.DriverFactory;
import com.qa.Utility.ConfigReader;
import com.qa.Utility.ReportUtility;

import io.cucumber.java.Before;

public class ApplicationHooks {
	
	
	private DriverFactory driverfactory;
	private WebDriver driver;
	private ConfigReader configReader;
	Properties prop;
	ReportUtility reportUtility;
	
	@Before(order = 0)
	public void getProperty(){
		configReader = new ConfigReader();
		prop = configReader.init_prop(Constant.CONFIG_FILEPATH);
	}
	
	@Before(order = 1)
	public void lunchBrowser(){
		String browserName = prop.getProperty("browser");
		driverfactory = new DriverFactory();
		driver = driverfactory.init_driver(browserName);
		reportUtility = new ReportUtility(driver);
		
	}

}
