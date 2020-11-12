package utility.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	@FindBy(xpath = "//*[@id='username']")
	private WebElement txtBxLogin;
	@FindBy(xpath = ".//*[@id='password']")
	private WebElement txtBxPassword;
	@FindBy(xpath = "//input[@type='submit']")
	private WebElement btnLogin;

	public LoginPage(WebDriver driver) {
		super(driver);

	}

	public void enterUsername(String username) {
		txtBxLogin.clear();
		txtBxLogin.sendKeys(username);
	}

	public void enterPassword(String password) {
		txtBxPassword.clear();
		txtBxPassword.sendKeys(password);
	}

	public void clickLogin() {
		if (btnLogin.isDisplayed() && btnLogin.isEnabled())
			btnLogin.click();
		else
			System.out.println("Login button is not displayed on disabled");
	}
}
