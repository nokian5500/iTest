package pages.service.taxes;

import common.ApplicationManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import pages.service.BaseServicePage;

import static org.testng.Assert.assertTrue;

public class CertificateFromUnifiedRegisterByReferentPage extends BaseServicePage {
    public CertificateFromUnifiedRegisterByReferentPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@on='property.type']/input[@name='bankIdfirstName']")
    private WebElement referentFirstNameField;

    @FindBy(xpath = "//div[@on='property.type']/input[@name='bankIdlastName']")
    private WebElement referentLastNameField;

    @FindBy(xpath = "//div[@on='property.type']/input[@name='bankIdmiddleName']")
    private WebElement referentMiddleNameField;

    @FindBy(xpath = "//div[@on='property.type']/input[@name='bankIdPassport']")
    private WebElement referentPassportField;

    @FindBy(xpath = "//div[@on='property.type']/input[@name='bankIdinn']")
    private WebElement referentIPNCodeField;

    @FindBy(xpath = "//select[@name='area']")
    private WebElement fiscalBranchDropDown;

    @FindBy(xpath = "//select[@name='subekt']")
    private WebElement subjectCertificateDropDown;

    @FindBy(xpath = "//input[@name='nomer']")
    private WebElement edrpouORIpnField;

    @FindBy(xpath = "//input[@name='naimenov']")
    private WebElement companyNameField;

    @FindBy(xpath = "//input[@name='organ']")
    private WebElement reasonForCertificateField;

    @FindBy(xpath = "(//input[@type='file'])")
    private WebElement attachDocumentButton;

    @FindBy(xpath = "//label[normalize-space(text())='Заповнення референтом']")
    private WebElement fillByReferentLabel;




    public CertificateFromUnifiedRegisterByReferentPage typeInEmailField(String email) {
        emailField.sendKeys(email); //email
        return this;
    }

    public CertificateFromUnifiedRegisterByReferentPage selectFiscalBranchField(String fiscalBranch) {
        new Select(fiscalBranchDropDown).selectByVisibleText(fiscalBranch);
        return this;
    }

    public CertificateFromUnifiedRegisterByReferentPage typeInFirstNameField(String firstName) {
        referentFirstNameField.clear();
        referentFirstNameField.sendKeys(firstName);
        return this;
    }

    public CertificateFromUnifiedRegisterByReferentPage typeInLastNameField(String lastName) {
        referentLastNameField.clear();
        referentLastNameField.sendKeys(lastName);
        return this;
    }

    public CertificateFromUnifiedRegisterByReferentPage typeInMiddleNameField(String middleName) {
        referentMiddleNameField.clear();
        referentMiddleNameField.sendKeys(middleName);
        return this;
    }

    public CertificateFromUnifiedRegisterByReferentPage typeInPassportField(String passport) {
        referentPassportField.clear();
        referentPassportField.sendKeys(passport);
        return this;
    }

    public CertificateFromUnifiedRegisterByReferentPage typeInIPNField(String ipnCode) {
        referentIPNCodeField.clear();
        referentIPNCodeField.sendKeys(ipnCode);
        return this;
    }

    public CertificateFromUnifiedRegisterByReferentPage typeInPhoneField(String phone) {
        phoneField.clear();
        phoneField.sendKeys(phone);
        return this;
    }

    public CertificateFromUnifiedRegisterByReferentPage typeInEdrpouORIpnField(String edrpouOrIpn) {
        edrpouORIpnField.clear();
        edrpouORIpnField.sendKeys(edrpouOrIpn);
        return this;
    }

    public CertificateFromUnifiedRegisterByReferentPage typeInCompanyNameField(String companyName) {
        companyNameField.clear();
        companyNameField.sendKeys(companyName);
        return this;
    }

    public CertificateFromUnifiedRegisterByReferentPage clickOnFillByReferentField(){
        fillByReferentLabel.click();
        return this;
    }

    public CertificateFromUnifiedRegisterByReferentPage selectCertificateSubject(String certificateSubject) {
        new Select(subjectCertificateDropDown).selectByVisibleText(certificateSubject);
        return this;
    }

    public CertificateFromUnifiedRegisterByReferentPage typeInReasonForCertificateField(String reasonForCertificate) {
        reasonForCertificateField.clear();
        reasonForCertificateField.sendKeys(reasonForCertificate);
        return this;
    }

    public CertificateFromUnifiedRegisterByReferentPage attachFile(ApplicationManager app, String filePath) {
        app.attachDocument(attachDocumentButton, filePath);
        return this;
    }

    public CertificateFromUnifiedRegisterByReferentPage verifyServiceSuccessCreated() {
        assertTrue(successText.isDisplayed(), "Success text is not present");
        return this;
    }

    @Override
    public String saveReferenceNumber() {
        return super.saveReferenceNumber();
    }

    @Override
    public CertificateFromUnifiedRegisterByReferentPage clickConfirmButton() {
        super.clickConfirmButton();
        return this;
    }

}
