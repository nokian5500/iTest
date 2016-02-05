package pages.main;

import common.ApplicationManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.BasePage;
import static org.testng.Assert.assertTrue;

public class StatusPage extends BasePage {

    // Variables

    @FindBy(id = "code")
    public WebElement refIdField;          // поле ввода реф

    @FindBy(xpath = "//a[contains(.,'Переглянути')]")
    public WebElement viewStatusButton;    //кнопка просмотра статуса


    // Methods

    public StatusPage() {
        PageFactory.initElements(driver, this);
    }

    public StatusPage enterReferenceNumber(String number) {
        refIdField.sendKeys(number);
        return this;
    }

    public StatusPage clickViewStatusButton() {
        viewStatusButton.click();
        return this;
    }

    public void verifyStatus(String status) {
        assertTrue(driver.findElement(By.xpath("//td[contains(.,'" + status + "')]")).isDisplayed(),
                "Status: ".concat(status).concat(" has not displayed on the page"));
    }
}