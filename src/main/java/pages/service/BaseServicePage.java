package pages.service;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class BaseServicePage extends BasePage {

    // Variables

    @FindBy(xpath = "//div[@class='service-name ng-binding']")
    private WebElement serviceName; // название услуги

    @FindBy(xpath = "//button[@class='btn btn-info']")
    private WebElement confirmButton; // кнопка подтверждения создания услуги

    @FindBy(xpath = "//div[@class='text-center ng-binding']")
    private WebElement referenceNumberField; //поле референс заявки

    @FindBy(name = "phone")
    protected WebElement phoneField; // поле ввода телефона

    @FindBy(xpath = "//input[@name='email']")
    protected WebElement emailField; // email

    @FindBy(xpath = "//div[@class='text-center ng-binding ng-scope']")
    protected WebElement successText;

    private static final String DATE_FORMAT = "dd/MM/yyyy";

    private String referenceNumber;


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

    protected void checkDateFormat(String dateToPArse) {
        SimpleDateFormat date = new SimpleDateFormat(DATE_FORMAT);
        try {
            date.parse(dateToPArse);
        } catch (ParseException e) {
            log.info("Check if " + dateToPArse + " complies to expected date format " + DATE_FORMAT);
        }
    }
}