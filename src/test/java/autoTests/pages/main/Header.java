package autoTests.pages.main;

import autoTests.ConfigurationVariables;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Header {
    WebDriver driver;
    ConfigurationVariables configVariables = ConfigurationVariables.getInstance();
    // Variables

    @FindBy(xpath = "//a[@href='/'][text()='Послуги']")
    public WebElement servicesLink;

    @FindBy(xpath = "//a[@href='/documents'][text()='Документи']")
    public WebElement documentsLink;

    @FindBy(xpath = "//a[@href='/order/search'][text()='Статуси']")
    public WebElement statusesLink;

    @FindBy(xpath = "//a[@href='/journal/auth'][text()='Мій журнал']")
    public WebElement myJournalLink;

    @FindBy(xpath = "//a[@href='/about'][text()='Про портал']")
    public WebElement aboutPortalLink;


    // Methods

    public Header(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

}