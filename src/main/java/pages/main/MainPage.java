package pages.main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.BasePage;

import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class MainPage extends BasePage {

    // Variables

    @FindBy(css = ".ng-scope>p")
    public WebElement services;            // name of services on the Main page

    @FindBy(xpath = "//input[@ng-change='search()']")
    public WebElement searchField;         // search field

    @FindBy(xpath = "//a[contains(text(), 'Всі послуги')] ")
    private WebElement expandMoreServices; // to display all found services when more than 4 services are found


    // Methods

    public MainPage() {
        PageFactory.initElements(driver, this);
    }

    // Method for service selection
    public void clickService(String service) {
        driver.findElement(By.xpath("//a[contains(.,'" + service + "')]")).click();
        app.pause(2000);
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
        app.pause(5000);
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
}