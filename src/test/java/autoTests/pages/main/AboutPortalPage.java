package autoTests.pages.main;

import autoTests.ConfigurationVariables;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AboutPortalPage {
    WebDriver driver;
    ConfigurationVariables configVariables = ConfigurationVariables.getInstance();

    // Variables

    @FindBy(xpath = "//div[@class='col-md-12 ctrls-about']/p[1]")
    public WebElement infoBlockOne;          // блок инфо1

    @FindBy(xpath = "//div[@class='col-md-12 ctrls-about']/p[2]")
    public WebElement infoBlockTwo;          // блок инфо2

    @FindBy(xpath = "//div[@class='col-md-12 ctrls-about']/p[3]")
    public WebElement infoBlockThree;        // блок инфо3

    @FindBy(xpath = "//div[@class='col-md-12 ctrls-about']/p[4]")
    public WebElement infoBlockFour;         // блок инфо4

    // Methods

    public AboutPortalPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void openAboutPortalPage() {
        if(!driver.getCurrentUrl().contentEquals(configVariables.baseUrl + "about")) {
            driver.get(configVariables.baseUrl);
            Header header = new Header(driver);
            header.aboutPortalLink.click();
            new WebDriverWait(driver, 2).until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//p[1][contains(.,'На цьому порталі зібрано послуги')]")));
        }
    }
}