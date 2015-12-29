package pages.main;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.BasePage;

public class Header extends BasePage {

    // Variables

    @FindBy(xpath = "//a[@href='/']/h4[text()='Послуги']")
    public WebElement servicesLink;

    @FindBy(xpath = "//a[@href='/documents']/h4[text()='Документи']")
    public WebElement documentsLink;

    @FindBy(xpath = "//a[@href='/order/search']/h4[text()='Статуси']")
    public WebElement statusesLink;

    @FindBy(xpath = "//a[@href='/journal/bankid']/h4[text()='Мій журнал']")
    public WebElement myJournalLink;

    @FindBy(xpath = "//a[@href='/about']/h4[text()='Про портал']")
    public WebElement aboutPortalLink;


    // Methods

    public Header() {
        PageFactory.initElements(driver, this);
    }
}