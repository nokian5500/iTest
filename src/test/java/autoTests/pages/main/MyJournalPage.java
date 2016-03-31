package autoTests.pages.main;

import autoTests.ConfigurationVariables;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Slame on 25.02.2016.
 */
public class MyJournalPage {
	WebDriver driver;
	ConfigurationVariables configVariables = ConfigurationVariables.getInstance();
	// Variables

	@FindBy(linkText = "увійдіть через BankID")
	public WebElement bankIdAuthorizationButton; // кноки входа в BankID

	@FindBy(linkText = "електронно-цифровий підпис")
	public WebElement electronicDigitalSignatureButton;

	@FindBy(xpath = "//div[@class='row']/div[@class='text-center']/p")
	public WebElement formSignInBankId;  // форма входа в журнал

	@FindBy(xpath = "//a[@ng-click='limit = limit +10']")
	public WebElement nextLink;          // ссылка на заявки

	@FindBy(xpath = "//h1[contains(.,'Мій журнал')]")
	public WebElement myLog;             // ссылка на заявки


	// Methods

	public MyJournalPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
}
