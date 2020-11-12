package utility.utils.test;

import static utility.utils.AppUtil.getProperty;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestListener;
import org.testng.ITestResult;

import utility.utils.AppUtil;

public class TestListener implements ITestListener {
	public void takeScreenShot(String filename, WebDriver driver) {
		try {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			//copying the file into /screenshots directory
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("_yyyyMMdd_HH-mm-ss");
			String name = filename + LocalDateTime.now().format(dateTimeFormatter) + ".png";

			File destDirectory = new File(getProperty("screenshotPath"));
			if (!destDirectory.exists())
				destDirectory.mkdir();
			File destFile = new File(destDirectory.getAbsolutePath() + "/" + name);
			FileHandler.copy(scrFile, destFile);
			AppUtil.getLogger(TestListener.class).info("Screenshot is present :" + destFile.getCanonicalFile());
		} catch (IOException e) {
			AppUtil.getLogger(TestListener.class).error("Screenshot capture error!");
			e.printStackTrace();
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("***** Error " + result.getName() + " test has failed *****");
		String methodName = result.getName().trim();
		String testName = result.getTestClass().getName().trim();
		this.takeScreenShot(testName + "." + methodName, AppUtil.getDriver());
	}
}
