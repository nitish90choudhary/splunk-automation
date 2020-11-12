package utility.pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import utility.utils.AppUtil;

public class SearchPage extends BasePage {

	@FindBy(css = ".search-input textarea")
	private WebElement txtBxSearch;

	@FindBy(css = ".search-button > a")
	private WebElement btnSearch;

	@FindBy(css = "table[id^='view']")
	private WebElement divResultContainer;

	@FindBy(css = ".search-bar-wrapper.shared-searchbar .search-timerange")
	private WebElement rangeSelectDropdown;

	@FindBy(css = "div[id^='presets_view'] div.accordion-inner a")
	private List<WebElement> presetTimeRangeLinks;

	public SearchPage() {
		super(AppUtil.getDriver());

	}

	private String getBaseDynamicSelector(int index) {
		Assert.assertTrue(index >= 1, "Selector can't be created for negative index");
		return "table[id^='view'] > tbody > tr:nth-child(" + index +
				") > td.event > div.shared-eventsviewer-shared-rawfield";
	}

	public void searchSplunkQuery(String query) {
		txtBxSearch.clear();
		txtBxSearch.sendKeys(query);
		clickSearchButton();
		//check if there any error then return early
		verifyNoErrorDisplayed();
		Assert.assertTrue(divResultContainer.isDisplayed(), "Result container is not visible!");
	}

	public void setTimeRangePresets(String presets) {
		rangeSelectDropdown.click();
		waitForElementVisibility(presetTimeRangeLinks.get(0));
		presetTimeRangeLinks.stream().filter(element -> element.getText().equals(presets)).collect(Collectors.toList())
				.get(0).click();
	}

	public void verifyNoErrorDisplayed() {
		WebElement errorMessageResult = null;
		try {
			errorMessageResult = driver
					.findElement(By.cssSelector("div.message-single > div.alert.alert-error"));
			waitForElementVisibility(errorMessageResult);
			Assert.assertFalse(errorMessageResult.isDisplayed(),
					"Result not displayed : " + errorMessageResult.getText());
		} catch (NoSuchElementException e) {
			System.out.println("Error message element not present");
		}
	}

	private void clickSearchButton() {
		if (btnSearch.isDisplayed() && btnSearch.isEnabled())
			btnSearch.click();
		else
			System.out.println("Search button is not displayed or disabled");
	}

	public String getResultAsRawText(int index) {
		//Create dynamic xpath and get result
		WebElement jsonContainer = driver.findElement(By.cssSelector(getBaseDynamicSelector(index) + " > a"));
		Assert.assertTrue(jsonContainer.isDisplayed(), "Container with Result json is not displayed");
		jsonContainer.click();

		WebElement rawJsonElement = driver
				.findElement(By.cssSelector(getBaseDynamicSelector(index) + " > div.raw-event.normal.wrap"));

		Assert.assertTrue(rawJsonElement.isDisplayed(), "Container with Result json is not displayed");
		System.out.println("Raw Text for node " + index + ": " + rawJsonElement.getText());
		return rawJsonElement.getText();
	}
}
