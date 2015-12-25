package pages.main;

import common.ApplicationManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SelectAreaPage extends ApplicationManager {

    // Variables

    public WebDriver driver;

    @FindBy(xpath = "//div[@class='service-name ng-binding']")
    public WebElement serviceName;      // название услуги

    @FindBy(css = "button.btn.btn-default")
    public WebElement regions;         // выпадающий список региона

    @FindBy(xpath = "(//button[@type='button'])[3]")
    public WebElement cities;          // выпадающий список города


    // Methods

    public SelectAreaPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    // Method for selection of Region
    public void selectRegion(String region) {
        regions.click();
        driver.findElement(By.xpath("//a[contains(text(),'" + region + "')]")).click();
    }

    // Method for selection of City
    public void selectCity(String city) {
        cities.click();
        driver.findElement(By.linkText(city)).click();
    }

    // Method for selection of service tab
    public void clickServiceTab(String serviceTab) {
        driver.findElement(By.xpath("//a[contains(.,'" + serviceTab + "')]")).click();
    }
}