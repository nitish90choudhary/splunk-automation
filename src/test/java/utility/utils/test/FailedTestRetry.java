package utility.utils.test;

import static utility.utils.enums.Properties.FAILED_TEST_RETRY_COUNT;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import utility.utils.AppUtil;

public class FailedTestRetry implements IRetryAnalyzer {
	private static int failedRetryCount = Integer.parseInt(
			AppUtil.getProperty(FAILED_TEST_RETRY_COUNT) == null ? "0" : AppUtil.getProperty(FAILED_TEST_RETRY_COUNT));

	private int initialCount = 0;

	@Override
	public boolean retry(ITestResult iTestResult) {
		if (initialCount < failedRetryCount)
			return true;
		else
			return false;
	}
}
