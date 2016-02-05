package pages.main;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.BasePage;

public class Header extends BasePage {

    // Variables

    @FindBy(xpath = "//a[contains(.,'Послуги')]")
    public WebElement servicesLink;

    @FindBy(xpath = "//a[contains(.,'Документи')]")
    public WebElement documentsLink;

    @FindBy(xpath = "//a[contains(.,'Статуси')]")
    public WebElement statusesLink;

    @FindBy(xpath = "//a[contains(.,'Мій журнал')]")
    public WebElement myJournalLink;

    @FindBy(xpath = "//a[contains(.,'Про портал')]")
    public WebElement aboutPortalLink;


    // Methods

    public Header() {
        PageFactory.initElements(driver, this);
    }
}