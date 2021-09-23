package com.Utility;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.Scenario;


public class ReportUtility {
	WebDriver driver;
	
	public ReportUtility(WebDriver driver) {
		this.driver = driver;
	}
	
	public void CaptureScreenShot(Scenario scenario) {
		String screenshotName = scenario.getName().replace(" ", "_");
		byte[] sourcePath = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
		scenario.attach(sourcePath, "image/png", screenshotName);
	}

}
