package autoTests.pages.main;

import autoTests.ConfigurationVariables;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.testng.Assert.assertTrue;

/**
 * Created by Slame on 25.02.2016.
 */
public class StatusPage {
	WebDriver driver;
	ConfigurationVariables configVariables = ConfigurationVariables.getInstance();
	// Variables

	@FindBy(id = "code")
	public WebElement refIdField;          // поле ввода реф

	@FindBy(xpath = "//a[contains(.,'Переглянути')]")
	public WebElement viewStatusButton;    //кнопка просмотра статуса


	// Methods

	public StatusPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	public StatusPage enterReferenceNumber(String number) {
		refIdField.sendKeys(number);
		return this;
	}

	public StatusPage clickViewStatusButton() {
		viewStatusButton.click();
		return this;
	}

	public void verifyStatus(String status) {
		assertTrue(driver.findElement(By.xpath("//td[contains(.,'" + status + "')]")).isDisplayed(),
				"Status: ".concat(status).concat(" has not displayed on the page"));
	}
}
