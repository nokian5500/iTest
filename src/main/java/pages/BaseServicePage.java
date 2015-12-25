package pages;

import common.ApplicationManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BaseServicePage extends ApplicationManager {

    // Variables

    @FindBy(xpath = "//div[@class='service-name ng-binding']")
    protected WebElement serviceName; // название услуги

    @FindBy(xpath = "//button[@class='btn btn-info']")
    protected WebElement confirmButton; // кнопка подтверждения создания услуги

    @FindBy(xpath = "//div[@class='text-center ng-binding']")
    protected WebElement referenceNumberField; //поле референс заявки

    @FindBy(name = "phone")
    protected WebElement phoneField; // поле ввода телефона

    @FindBy(xpath = "//input[@name='email']")
    protected WebElement emailField; // email

    @FindBy(xpath = "//div[@class='text-center ng-binding ng-scope']")
    protected WebElement successText;

    protected static final String DATE_FORMAT = "dd/MM/yyyy";
    
    public String referenceNumber;


    // Methods

    public BaseServicePage clickConfirmButton() {
        confirmButton.click(); //нажать конпку подтверждения создания услуги
        return this;
    }

    public String getServiceName() {
        return serviceName.getText();
    }

    public String saveReferenceNumber() {
        String refField = referenceNumberField.getText();
        this.referenceNumber = refField.substring(16, 23);
        return referenceNumber;
    }
}