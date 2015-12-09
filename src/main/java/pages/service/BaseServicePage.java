package pages.service;

import common.ApplicationManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BaseServicePage extends ApplicationManager {

    @FindBy(xpath = "//div[@class='service-name ng-binding']")
    protected WebElement serviceName; // название услуги

    @FindBy(xpath = "//button[@class='btn btn-info']")
    private WebElement confirmButton; // кнопка подтверждения создания услуги

    protected BaseServicePage clickConfirmButton() {
        confirmButton.click(); //нажать конпку подтверждения создания услуги
        return this;
    }
}
