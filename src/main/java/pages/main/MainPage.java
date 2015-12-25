package pages.main;

import common.ApplicationManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends ApplicationManager {

    // Variables

    @FindBy(xpath = "//h4[contains(.,'Послуги')]")
    public WebElement servicesLink;

    @FindBy(xpath = "//h4[contains(.,'Документи')]")
    public WebElement documentsLink;

    // ---------------- Header ------------------//
    @FindBy(xpath = "//h4[contains(.,'Статуси')]")
    public WebElement statusesLink;

    @FindBy(xpath = "//h4[contains(.,'Мій журнал')]")
    public WebElement myJournalLink;

    @FindBy(xpath = "//h4[contains(.,'Про портал')]")
    public WebElement aboutPortalLink;

    @FindBy(xpath = "//a[contains(@href,'https://igov.org.ua/ecp')]")
    public WebElement checkElectronDigitalSignatureLink;

    @FindBy(xpath = "//footer/div/div/div[3]")
    public WebElement portalsNewsOnFacebookLink;

    // ---------------- Footer ------------------//
    @FindBy(xpath = "//a[contains(@href, 'https://docs.google.com/forms/d/1ueU6PQa-OSA2Tsisxx2RbRWRJ9rLsFlPBlHsr7W-4gE/viewform')]")
    public WebElement errorOrABugOnThePortalLink;

    @FindBy(xpath = "//a[contains(@href, 'https://github.com/e-government-ua/i/wiki/%D0%AF%D0%BA-%D0%BF%D0%BE%D1%87%D0%B0%D1%82%D0%B8-%D1%80%D0%BE%D0%B1%D0%BE%D1%82%D1%83')]")
    public WebElement joinOnGitHubLink;

    @FindBy(xpath = "//a[contains(@href,'https://docs.google.com/forms/d/1w-BR01CSicvhWSXb36CiRjHCwvp-vyPuTHBaWw1iW4U/viewform')]")
    public WebElement volunteerIGov;

    @FindBy(css = ".ng-scope>p")
    public WebElement services;            // name of services on the Main page

    @FindBy(xpath = "//input[@ng-change='search()']")
    public WebElement searchField;         // search field

    @FindBy(xpath = "//a[contains(text(), 'Всі послуги')] ")
    private WebElement expandMoreServices; // to display all found services when more than 4 services are found


    // Methods

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    // Method for service selection
    public void clickService(String service) {
        pause(2000); // временно
        driver.findElement(By.xpath("//a[contains(.,'" + service + "')]")).click();
    }

    // Method for searching the service
    public void typeInSearchField(String service) {
        searchField.clear();
        searchField.sendKeys(service);
    }

    // Method for click to service link
    public void goToServices() {
        servicesLink.click();
    }

    // Method for opening Documents page
    public void goToDocuments() {
        documentsLink.click();
    }

    // Method for opening Statuses page
    public void goToStatus() {
        statusesLink.click();
    }

    // Method for opening My journal page
    public void goToMyLog() {
        myJournalLink.click();
    }

    // Method for opening About portal page
    public void goToAboutPortal() {
        aboutPortalLink.click();
    }

    // Method for switching to test service
    public void goToTestServices(String server, String service) {
        driver.get(server + service);
    }

    public void goToService() {
        driver.get("https://test-version.igov.org.ua/service/176/general"); //временно из за бага поиска
    }

    // Method to expand the list of services
    public void clickExpandAllFoundServices() {
        expandMoreServices.click();
    }
}