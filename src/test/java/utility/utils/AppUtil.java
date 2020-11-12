package utility.utils;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class AppUtil {
	public static final String expandScript = "while(document.querySelectorAll('a.jsexpands').length>0){document.querySelectorAll('a.jsexpands').forEach(function(expander){expander.click()})}";
	private static final Logger logger = LoggerFactory.getLogger(AppUtil.class);
	public static Map<String, String> configMap;
	public static WebDriver driver;

	public AppUtil() {
		this.loadProperty();
	}

	public static String getProperty(String key) {
		if (configMap.containsKey(key))
			return configMap.get(key);
		else
			System.out.println(key + " not found in config.properties file");
		return null;
	}

	public static WebDriver getDriver() {
		return driver;
	}

	public static Logger getLogger(Class<?> klass) {
		return LoggerFactory.getLogger(klass.getClass());
	}

	public void loadProperty() {
		configMap = new HashMap<String, String>();
		Properties prop = new Properties();

		try {
			FileInputStream inputStream = new FileInputStream("config.properties");
			prop.load(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error in loading config.properties...! " + e.getMessage());

		}
		for (final Map.Entry<Object, Object> entry : prop.entrySet()) {
			configMap.put((String) entry.getKey(), (String) entry.getValue());
			// System.out.println(entry.getKey() + " " + entry.getValue());
		}

	}

	// Setup function
	public WebDriver setup() {

		if (getProperty("test.browser").equalsIgnoreCase("chrome")) {
			ChromeOptions chromeOptions = new ChromeOptions();
			System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
			LoggingPreferences logPrefs = new LoggingPreferences();
			logPrefs.enable(LogType.BROWSER, Level.ALL);
			logPrefs.enable(LogType.PERFORMANCE, Level.INFO);
			chromeOptions.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

			if (getProperty("headless").equalsIgnoreCase("true")) {
				//This will start headless chrome browser
				chromeOptions.addArguments("--headless");
				driver = new ChromeDriver(chromeOptions);
			} else
				driver = new ChromeDriver(chromeOptions);

		} else if (getProperty("test.browser").equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "driver/geckodriver.exe");
			driver = new FirefoxDriver();
		} else {
			System.out.println("Check browser settings in config file!");
		}

		Assert.assertNotNull(driver, "Driver object is not initialized properly");
		if (getProperty("app.implicitwait") != null)
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(getProperty("app.implicitwait")),
					TimeUnit.SECONDS);

		else
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		driver.manage().window().maximize();
		driver.get(getProperty("app.url"));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return driver;
	}

	// Cleanup function
	public void cleanup() {
		driver.close();
		driver.quit();
	}
}
