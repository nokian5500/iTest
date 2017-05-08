package autoTests.pages.main;

import autoTests.ConfigurationVariables;
import autoTests.CustomMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import autoTests.SetupAndTeardown;

import java.util.List;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.Select;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertEquals;

public class TemplatePage {

    WebDriver driver;

    public TemplatePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    ConfigurationVariables configVariables = ConfigurationVariables.getInstance();
    CustomMethods cm = new CustomMethods();

    /**
     * *************************************
     * Locators **************************************************************
     */
    @FindBy(xpath = "//div[@class='service-name ng-binding']")
    public WebElement usluga;

    @FindBy(xpath = "//div[@class='row no-margin-bottom']//div[@class='text-center ng-scope']")
    public List<WebElement> resultMsgText;

    @FindBy(xpath = ".//*[@id='region']")
    public WebElement openOblList;

    @FindBy(xpath = ".//*[@id='city']")
    public WebElement openCityList;

    @FindBy(xpath = ".//*[@id='bank']")
    public WebElement openBankList;

    @FindBy(xpath = "//button[@ng-click='vm.onChange()']")
    public WebElement buttonAuthMock;

    @FindBy(xpath = "//button[@ng-click='vm.onChange()']")
    public WebElement spanAuthMock;

    @FindBy(xpath = "//button[@ng-click='bankIdClick()']")
    public WebElement buttonBankID;

    @FindBy(xpath = "//a[@class='ng-binding']")
    public WebElement bankid_bank_pb;

    @FindBy(xpath = "//a[@ng-click='logout()']")
    public WebElement buttonLogOut;

    @FindBy(xpath = "//button[@ng-click='processForm(form, activitiForm.formProperties, false)']")
    public WebElement buttonSendingForm;

    @FindBy(xpath = "(//input[@type='file'])")
    public WebElement attachDocumentButton;

    @FindBy(xpath = "//a[@class='ng-binding']")
    public WebElement orderID;

    /**
     * ********************** Методы авторизации *************************
     */
    public void mokAuthorization() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(buttonAuthMock));
        //выбираем Off AuthMock/BankID
        if (spanAuthMock.getText().equalsIgnoreCase("On AuthMock")) {
            cm.click(driver, buttonAuthMock);
        }
        cm.click(driver, buttonBankID);
        cm.clickXpath(driver, "//li[1]/a//span[contains(.,'ПриватБанк')]");

    }

    public void ecpAuthorization() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(buttonAuthMock));
//        cm.click(driver, buttonAuthMock);
        cm.click(driver, buttonBankID);
        cm.clickXpath(driver, "//li[1]/a//span[contains(.,'ПриватБанк')]");
        cm.click(driver, driver.findElement(By.xpath(".//legend[text()='ЕЦП']")));
        // находим элемент <input type="file">
        WebElement element = driver.findElement(By.cssSelector(".filePath"));
        element.sendKeys("src/test/resources/files/Key-6.dat");

    }

    public void testPrivat24Authorization(String URLservice) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(buttonAuthMock));
        cm.click(driver, buttonBankID);
        cm.clickXpath(driver, "//li[1]/a//span[contains(.,'ПриватБанк')]");
        cm.clickXpath(driver, ".//*[@id='container']/div/div[2]/div[1]/ul/li[1]/a/span[2]");
        WebElement privat24Login = driver.findElement(By.xpath(".//*[@id='loginLikePhone']"));
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(privat24Login));
        privat24Login.clear();
        privat24Login.sendKeys("+380102030405");
        WebElement privat24password = driver.findElement(By.xpath(".//*[@id='passwordLikePassword']"));
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(privat24password));
        privat24password.sendKeys("value");
        WebElement privat24button = driver.findElement(By.xpath(".//*[@id='signInButton']"));
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(privat24button));
        privat24button.click();
        cm.pause(3000);
        //.//*[@id='third-section']
        WebElement firstSection = driver.findElement(By.xpath(".//*[@id='first-section']"));
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(firstSection));
        firstSection.clear();
        firstSection.sendKeys("12");
        WebElement secondSection = driver.findElement(By.xpath(".//*[@id='second-section']"));
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(secondSection));
        secondSection.clear();
        secondSection.sendKeys("34");
        WebElement thirdSection = driver.findElement(By.xpath(".//*[@id='third-section']"));
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(thirdSection));
        thirdSection.clear();
        thirdSection.sendKeys("56");
        //.//*[@id='confirmButton']
        WebElement confirmButton = driver.findElement(By.xpath(".//*[@id='confirmButton']"));
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(confirmButton));
        confirmButton.click();
        cm.pause(3000);
        cm.openURLservice(driver, configVariables.baseUrl + URLservice);
    }
//     Method for selection of Bankid_bank

    public void selectBank(String bank) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(openBankList));
        openBankList.click();
        cm.clickXpath(driver, "//a[contains(text(),'" + bank + "')]");
    }

    // Method for selection of Region
    public void selectRegion(String region) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(openOblList));
        openOblList.click();
        cm.clickXpath(driver, "//a[contains(text(),'" + region + "')]");
    }

    // Method for selection of City
    public void selectCity(String city) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(openCityList));
        openCityList.click();
        cm.clickXpath(driver, "//a[contains(text(),'" + city + "')]");
    }

    public void checkMessageSuccess(String message) throws Exception {
        configVariables.orderId.add(orderID.getText().substring(2, orderID.getText().length()));
        System.out.println(configVariables.orderId);

        String textForAssert = cm.getText(driver, resultMsgText.get(0));
        String firstPart = textForAssert.substring(0, 46);
        String secondPart;
        if (textForAssert.substring(57, 58).equals(" ")) {
            secondPart = textForAssert.substring(58, textForAssert.length());
        } else {
            secondPart = textForAssert.substring(59, textForAssert.length());
        }
        Assert.assertEquals(firstPart, message.substring(0, 46));
        Assert.assertEquals(secondPart, message.substring(58, message.length()));
    }

    // Method
    public void selectAutocomplete(String name, String value) {
        driver.findElement(By.name(name)).click();
        driver.findElement(By.name(name)).sendKeys(value);
        driver.findElement(By.linkText(value)).click();
    }
}
