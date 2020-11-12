package utility.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.utils.AppUtil;

public abstract class BasePage {

	private static int TIMEOUT;
	protected WebDriver driver;
	protected WebDriverWait wait;

	@FindBy(xpath = "//*[@id='brand']")
	private WebElement lblPageHeader;

	BasePage(WebDriver driver) {
		if (AppUtil.getProperty("app.explicitwait") != null)
			TIMEOUT = Integer.parseInt(AppUtil.getProperty("app.explicitwait"));
		else {
			System.out.println("setting explicitwait : 5s");
			TIMEOUT = 5;
		}

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Checks if element is visible or not
	public boolean waitForElementVisibility(WebElement element) {

		wait = new WebDriverWait(driver, TIMEOUT);
		wait.until(ExpectedConditions.visibilityOf(element));

		if (element.isDisplayed())
			return true;
		else
			return false;
	}

	public boolean waitForElementToBeClickable(WebElement element) {

		wait = new WebDriverWait(driver, TIMEOUT);
		wait.until(ExpectedConditions.elementToBeClickable(element));

		if (element.isEnabled())
			return true;
		else
			return false;
	}

	public int getTimeout() {
		return TIMEOUT;
	}

	// Checks if element is enabled or not
	public boolean isElementEnabled(String xpath) {
		wait = new WebDriverWait(driver, TIMEOUT);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		WebElement element = driver.findElement(By.xpath(xpath));
		if (element.isEnabled())
			return true;
		else
			return false;
	}

	public String getPageHeader() {
		return lblPageHeader.getText();
	}

}
