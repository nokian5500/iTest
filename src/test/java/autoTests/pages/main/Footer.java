package autoTests.pages.main;

import autoTests.ConfigurationVariables;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Slame on 23.02.2016.
 */
public class Footer {
	WebDriver driver;
	ConfigurationVariables configVariables = ConfigurationVariables.getInstance();
	// Variables

	@FindBy(xpath = "//a[@href='https://igov.org.ua/ecp'][text()[contains(.,'Перевірити електронно-')] and text()[contains(.,'цифровий підпис')]]")
	public WebElement checkSignatureLink;

	@FindBy(xpath = "//a[@href='https://www.facebook.com/igov.org.ua'][text()[contains(.,'Новини порталу')] and text()[contains(.,'на Facebook')]]")
	public WebElement portalsNewsOnFacebookLink;

	@FindBy(xpath = "//a[@href='https://docs.google.com/forms/d/1ueU6PQa-OSA2Tsisxx2RbRWRJ9rLsFlPBlHsr7W-4gE/viewform'][text()='Повідомити про помилку на порталі']")
	public WebElement errorOrABugOnThePortalLink;

	@FindBy(xpath = "//a[@href='https://github.com/e-government-ua/i/wiki/%D0%AF%D0%BA-%D0%BF%D0%BE%D1%87%D0%B0%D1%82%D0%B8-%D1%80%D0%BE%D0%B1%D0%BE%D1%82%D1%83'][text()[contains(.,'Приєднатись')] and text()[contains(.,'на GitHub!')]]")
	public WebElement joinOnGitHubLink;

	@FindBy(xpath = "//a[@href='https://docs.google.com/forms/d/1w-BR01CSicvhWSXb36CiRjHCwvp-vyPuTHBaWw1iW4U/viewform'][text()[contains(.,'Станьте')] and text()[contains(.,'волонтером iGov!')]]")
	public WebElement volunteerIGov;

	@FindBy(xpath = "//a[contains(.,'Додати свою послугу')]")
	public WebElement addYourServiceLink;


	// Methods

	public Footer(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
}
