package autoTests.pages.main;

import autoTests.CustomMethods;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

import autoTests.ConfigClass;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.ByAll;

public class TemplatePage extends CustomMethods {

    //CustomMethods cm = new CustomMethods();
    /**
     * *************************************
     * Locators **************************************************************
     */
    public By usluga = By.xpath("//div[@class='service-name ng-binding']");

    public By resultMsgText = By.xpath("//div[@class='row no-margin-bottom']//div[@class='text-center ng-scope']");

    public By openOblList = By.xpath(".//*[@id='region']");

    public By openCityList = By.xpath(".//*[@id='city']");

    public By openBankList = By.xpath(".//*[@id='bank']");

    public By buttonAuthMock = By.xpath("//button[@ng-click='vm.onChange()']");

    public By spanAuthMock = By.xpath("//button[@ng-click='vm.onChange()']");

    public By buttonBankID = By.xpath("//button[@ng-click='bankIdClick()']");

    public By bankid_bank_pb = By.xpath("//a[@class='ng-binding']");

    public By buttonLogOut = By.xpath("//a[@ng-click='logout()']");

    public By buttonSendingForm = By.xpath("//button[@ng-click='processForm(form, activitiForm.formProperties, false)']");

    public By attachDocumentButton = By.xpath("(//input[@type='file'])");

    public By orderID = By.xpath("//a[@class='ng-binding']");

    /**
     * ********************** Методы авторизации *************************
     */
    public void mokAuthorization() {
        $(buttonAuthMock).waitUntil(visible, 10000);
        //выбираем Off AuthMock/BankID
        if ($(spanAuthMock).getText().equalsIgnoreCase("On AuthMock")) {
            $(buttonAuthMock).click();
        }
        $(buttonBankID).click();
        clickXpath("//li[1]/a//span[contains(.,'ПриватБанк')]");
    }

    public void testPrivat24Authorization() {
        $(By.xpath("//button[@ng-click='bankIdClick()']")).click();
        $(By.xpath("//li[1]/a//span[contains(.,'ПриватБанк')]")).click();
        //<span class="ng-binding" ng-bind="::provider.name">ПриватБанк</span>
        $(By.xpath("//span[contains(.,'ПриватБанк')]")).click();
        pause(5000);
        $(By.xpath("//input[@id='inputLogin']")).val("+380102030405");
        $(By.xpath("//input[@id='inputPassword']")).val("value");
        $(By.xpath("//button[@class='btn btn-success custom-btn']")).click();

        $(By.xpath("//input[@id='first-section']")).val("12");
        $(By.xpath("//input[@id='second-section']")).val("34");
        $(By.xpath("//input[@id='third-section']")).val("56");
        $(By.xpath("//button[@class='btn btn-success custom-btn-confirm sms']")).click();
    }

    // Method for selection of Region
    public void selectRegion(String region) {
        pause(10000);
        $(openOblList).waitUntil(visible, 10000).click();
        clickXpath("//a[contains(text(),'" + region + "')]");
    }

    // Method for selection of City
    public void selectCity(String city) {
        $(openCityList).shouldBe(visible).click();
        clickXpath("//a[contains(text(),'" + city + "')]");
    }

    public void checkMessageSuccess(String message) throws Exception {
        pause(10000);
        ConfigClass.orderId.add($(orderID).waitUntil(exist,90000).getText().substring(2, $(orderID).getText().length()));
        System.out.println(ConfigClass.orderId);

        String textForAssert = $(resultMsgText).getText();
        String firstPart = textForAssert.substring(0, 46);
        String secondPart;
        if (textForAssert.substring(57, 58).equals(" ")) {
            secondPart = textForAssert.substring(58, textForAssert.length());
        } else {
            secondPart = textForAssert.substring(59, textForAssert.length());
        }
        try {
            Assert.assertEquals(firstPart, message.substring(0, textForAssert.length()));
            Assert.assertEquals(secondPart, message.substring(58, message.length()));
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
