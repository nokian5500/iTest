package pages.main;

import common.ApplicationManager;
import common.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class BankIdAuthorizationPage extends ApplicationManager {

    // Variables

    public WebDriver driver;

    @FindBy(xpath = "//span[contains(.,'BankID')]")
    public WebElement BankID;          // button for authorization through BankID

    @FindBy(id = "privatBank")
    public WebElement privatBank;      // PrivatBank selection

    @FindBy(id = "loginLikePhone")
    public WebElement phone;           // login field for Privat24

    @FindBy(id = "passwordLikePassword")
    public WebElement password;        // password field for Privat24

    @FindBy(id = "confirmButton")
    public WebElement confirm;         // button to open ОТР step

    @FindBy(id = "first-section")
    public WebElement otpOne;         // the first field after entering ОТР

    @FindBy(id = "second-section")
    public WebElement otpTwo;         // the second field after entering ОТР

    @FindBy(id = "third-section")
    public WebElement otpThree;       // the third field after entering ОТР

    @FindBy(id = "signInButton")
    public WebElement signIn;         //  sign in button after entering ОТР

    @FindBy(xpath = "//span[contains(.,'Дмитро Олександрович Дубілет')]")
    public WebElement fio;            //  full name of client after login

    @FindBy(xpath = "//span[contains(.,'Вийти')]")
    public WebElement logOutButton;   //  logout button


    // Methods

    public BankIdAuthorizationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    // Method for logging out
    public void logOut() {
        logOutButton.click();
    }

    // Method for FIO verification after login
    public void verifyFIO() {
        Assert.assertEquals(fio.getText(), Constants.TestData.PersonalInfo.FIO_UA);
    }

    // Method for entering ОТР
    public void typeOTP() {
        otpOne.sendKeys(Constants.TestData.BankIDprivatBank.OTP1);
        otpTwo.sendKeys(Constants.TestData.BankIDprivatBank.OTP2);
        otpThree.sendKeys(Constants.TestData.BankIDprivatBank.OTP3);
        confirm.click();
    }

    // Method for selecting BankID authorization method
    public void selectMethodBankID() {
        BankID.click();
    }

    // Method for selecting PrivatBank
    public void selectPrivatBankBankID() {
        privatBank.click();
    }

    // Method for entering login and password
    public void typeLoginPassword() {
        phone.clear();
        phone.sendKeys(Constants.TestData.BankIDprivatBank.LOGIN);
        password.sendKeys(Constants.TestData.BankIDprivatBank.PASSWORD);
        signIn.click();
    }

    // Method for authorization through PrivatBank
    public void privatBankAuthorization() {
        selectMethodBankID();
        selectPrivatBankBankID();
        typeLoginPassword();
        typeOTP();
        verifyFIO();
    }
}