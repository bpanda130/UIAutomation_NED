package com.Factory;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.Factory.OptionsManager;
import com.Utility.Browser;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver driver;
	public static String highlight = "true";
	private static Browser browserName;
	private OptionsManager optionsManager;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

	/**
	 * This method is used to initialize the threadlocal driver on the basis of
	 * given browser
	 * 
	 * @param browser
	 * @return this will return tlDriver
	 */
	public WebDriver init_driver(Properties prop, Browser broserName) {
		highlight = prop.getProperty("highlight");
		optionsManager = new OptionsManager(prop);

		switch (broserName) {
		case FIREFOX:
			WebDriverManager.firefoxdriver().setup();
			//tlDriver.set(new FirefoxDriver());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			break;
		case CHROME:
			WebDriverManager.chromedriver().setup();
			//tlDriver.set(new ChromeDriver());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			break;
		case EDGE:
			WebDriverManager.edgedriver().setup();
			tlDriver.set(new EdgeDriver());
			break;
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		return getDriver();
	}

	/**
	 * this is used to get the driver with threadLocal
	 * 
	 * @return
	 */
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

}
