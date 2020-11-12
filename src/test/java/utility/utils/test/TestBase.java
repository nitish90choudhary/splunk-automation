package utility.utils.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import utility.utils.AppUtil;
import utility.utils.SplunkUtil;

public class TestBase {
	private static final Logger logger = LoggerFactory.getLogger(TestBase.class);
	protected SplunkUtil splunkUtil;
	AppUtil appUtil;

	@BeforeSuite
	public void setup() {
		appUtil = new AppUtil();
		splunkUtil = new SplunkUtil();
		logger.info("Test Application started!");
		//Start browsers
		appUtil.setup();
		logger.info("Web browser launched!");

		//Login to splunk
		splunkUtil.loginToSplunk();
		logger.info("Login completed");
	}

	@AfterSuite
	public void cleanUp() {
		appUtil.cleanup();
		logger.info("Test application closing");
		// close report
	}

}
