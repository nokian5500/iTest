package pages.service.taxes;

import common.ApplicationManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import pages.service.BaseServicePage;

public class PensionAmountCertificatePage extends BaseServicePage {

    public static String referenceNumber;

    @FindBy(xpath = "//input[@name='adress']")
    private WebElement addressField;

    @FindBy(xpath = "//select[@name='area']")
    private WebElement fiscalBranchField; //fiscal branch

    @FindBy(xpath = "(//input[@type='file'])[1]")
    private WebElement attachDocumentButton;

    @FindBy(xpath = "//input[@name='date_from']")
    private WebElement startOfPeriodField; //certificate's information start of period

    @FindBy(xpath = "//input[@name='date_to']")
    private WebElement endOfPeriodField;

    public PensionAmountCertificatePage typeInPhoneField(String phone) {
        phoneField.clear();
        phoneField.sendKeys(phone);
        return this;
    }

    public PensionAmountCertificatePage() {
        PageFactory.initElements(driver, this);
    }

    public PensionAmountCertificatePage verifyServiceSuccessCreated() {
        successText.isDisplayed();
        return this;
    }

    public PensionAmountCertificatePage typeInEmailField(String email) {
        emailField.sendKeys(email); //email
        return this;
    }

    public PensionAmountCertificatePage selectFiscalBranchField(String fiscalBranch) {
        new Select(fiscalBranchField).selectByVisibleText(fiscalBranch); //fiscal branch selection
        return this;
    }

    public PensionAmountCertificatePage selectStartOfPeriodField(String startOfPeriod) {
        checkDateFormat(startOfPeriod);
        //TODO: to make it working in less dirty way
        ((JavascriptExecutor) driver).executeScript("angular.element(document.getElementsByName('date_from')[0]).removeAttr('readonly');"); // Enables the from date box
        startOfPeriodField.clear();
        startOfPeriodField.sendKeys(startOfPeriod);

        return this;
    }

    public PensionAmountCertificatePage selectEndOfPeriodField(String endOfPeriod) {
        checkDateFormat(endOfPeriod);
        //TODO: to make it working in less dirty way
        ((JavascriptExecutor) driver).executeScript("angular.element(document.getElementsByName('date_to')[0]).removeAttr('readonly');"); // Enables the from date box
        endOfPeriodField.clear();
        endOfPeriodField.sendKeys(endOfPeriod);

        return this;
    }

    public PensionAmountCertificatePage attachFile(ApplicationManager app, String filePath) {
        app.attachDocument(attachDocumentButton, filePath);
        return this;
    }

    @Override
    public PensionAmountCertificatePage clickConfirmButton() {
        super.clickConfirmButton();

        return this;
    }

    @Override
    public String saveReferenceNumber() {
        referenceNumber = super.saveReferenceNumber();
        return referenceNumber;
    }

    public PensionAmountCertificatePage typeInAddressField(String address) {
        addressField.sendKeys(address);
        return this;
    }
}
