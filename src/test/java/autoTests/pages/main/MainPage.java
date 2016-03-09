package autoTests.pages.main;

import autoTests.ConfigurationVariables;
import autoTests.CustomMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Slame on 24.02.16.
 */
public class MainPage {
    WebDriver driver;
    ConfigurationVariables configVariables = ConfigurationVariables.getInstance();
    CustomMethods customMethods = new CustomMethods();
    // Variables

    @FindBy(css = ".ng-scope>p")
    private WebElement services;            // name of services on the Main page

    @FindBy(xpath = "//input[@ng-change='search()']")
    private WebElement searchField;         // search field

    @FindBy(xpath = "//a[contains(text(), 'Всі послуги')] ")
    private WebElement expandMoreServices; // to display all found services when more than 4 services are found


    // Webelements: Service page elements

    @FindBy(xpath = "//button[contains(.,'Змінити')]")
    public WebElement changeRegionButton;

    @FindBy(xpath = "//input[@name='email']")
    public WebElement inputEmailAbsentMessageField;

    @FindBy(xpath = "//button[contains(.,'Зберегти')]")
    public WebElement sendAbsentMessageButton;


    // Methods

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    // Method for service selection
    public void clickService(String service) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(.,'" + service + "')]"))).click();
    }

    // Method for searching the service
    public void typeInSearchField(String service) {
        searchField.clear();
        searchField.sendKeys(service);
    }

    // Method for searching the service
    public void search(String service) {
        searchField.clear();
        searchField.sendKeys(service);
        customMethods.pause(5000);
    }

    // Method for switching to test service
    public void goToTestServices(String server, String service) {
        driver.get(server + service);
    }

    // Method to expand the list of services
    public void clickExpandAllFoundServices() {
        expandMoreServices.click();
    }

    public boolean isSearchResultContains(String service) {
        return services.getText().contentEquals(service);
    }

    // Interaction with service page elements methods

    public void typeInEmailField(String email) {
        inputEmailAbsentMessageField.clear();
        inputEmailAbsentMessageField.sendKeys(email);
    }

    public void clickSendAbsentMessageButton() {
        sendAbsentMessageButton.click();
    }

}
