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
     * Locators
     * **************************************************************
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

    @FindBy(xpath = "//button[@ng-hide='bSending(form)']")
    public WebElement buttonSendingForm;

    @FindBy(xpath = "(//input[@type='file'])")
    public WebElement attachDocumentButton;

    @FindBy(xpath = "//a[@class='ng-binding']")
    public WebElement orderID;




    /**
     * ********************** Метод авторизации *************************
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
        configVariables.orderId.add(orderID.getText().substring(2,orderID.getText().length()));
        System.out.println(configVariables.orderId);

    	String textForAssert = cm.getText(driver, resultMsgText.get(0));
    	String firstPart = textForAssert.substring(0, 46);
    	String secondPart;
    	if (textForAssert.substring(57, 58).equals(" ")) {
    		secondPart = textForAssert.substring(58, textForAssert.length());
    	}
    	else {
    		secondPart = textForAssert.substring(59, textForAssert.length());
    	}
    	Assert.assertEquals(firstPart,message.substring(0, 46));
       	Assert.assertEquals(secondPart,message.substring(58, message.length()));
    }

    // Method
    public void selectAutocomplete(String name, String value) {
        driver.findElement(By.name(name)).click();
        driver.findElement(By.name(name)).sendKeys(value);
        driver.findElement(By.linkText(value)).click();
    }
    
    
    
 }

