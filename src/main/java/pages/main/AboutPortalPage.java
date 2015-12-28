package pages.main;

import common.ApplicationManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.BasePage;

public class AboutPortalPage extends BasePage {

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

    public AboutPortalPage() {
        PageFactory.initElements(driver, this);
    }
}