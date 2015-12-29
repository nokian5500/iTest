package pages.main;

import common.ApplicationManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.BasePage;

public class SelectAreaPage extends BasePage {

    // Variables

    @FindBy(xpath = "//div[@class='service-name ng-binding']")
    public WebElement serviceName;      // название услуги

    @FindBy(css = "button.btn.btn-default")
    public WebElement regions;         // выпадающий список региона

    @FindBy(xpath = "(//button[@type='button'])[3]")
    public WebElement cities;          // выпадающий список города


    // Methods

    public SelectAreaPage() {
        PageFactory.initElements(driver, this);
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

    // Method for opening Statistic tab
    public void openStatisticTab() {
        driver.findElement(By.xpath("//a[text()='Статистика']")).click();
    }

    public boolean isServiceName(String service) {
        return serviceName.getText().contentEquals(service);
    }
}