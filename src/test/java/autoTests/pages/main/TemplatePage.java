package autoTests.pages.main;


import autoTests.ConfigurationVariables;
import autoTests.CustomMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class TemplatePage {
	WebDriver driver;

	public TemplatePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
    CustomMethods cm = new CustomMethods();

	/****************************************
	 * Locators
	 ****************************************************************/

	@FindBy(xpath = "//div[@class='service-name ng-binding']")
	public WebElement usluga;

    @FindBy(xpath = "//div[@class='row no-margin-bottom']//div[@class='text-center ng-scope']")
	public List<WebElement> resultMsgText;

    @FindBy(xpath = ".//*[@id='region']")
	public WebElement openOblList;

    @FindBy(xpath = ".//*[@id='city']")
    public WebElement openCityList;

    @FindBy(xpath = "html/body/div[1]/div[2]/div/div[1]/div[4]/div/div[2]/button-create-cookie/div/label")
    public WebElement buttonAuthMock;

    @FindBy(xpath = "html/body/div[1]/div[2]/div/div[1]/div[4]/div/div[2]/button-create-cookie/div/label/span")
    public WebElement spanAuthMock;

    @FindBy(xpath = "//a[contains(.,' BankID')]")
    public WebElement buttonBankID;

    @FindBy(xpath = "//a[@ng-click='logout()']")
    public WebElement buttonLogOut;

    @FindBy(xpath = "//button[@ng-hide='bSending(form)']")
    public WebElement buttonSendingForm;

    @FindBy(xpath = "(//input[@type='file'])")
    public WebElement attachDocumentButton;

    /************************* Метод авторизации **************************/
    public void mokAuthorization(){
        //выбираем Off AuthMock/BankID
        if (spanAuthMock.getText().equalsIgnoreCase("On AuthMock")) {
            cm.click(driver, buttonAuthMock);
        }
        cm.click(driver, buttonBankID);
    }
}

