package tests;

import TestServicePages.TestFieldsBankidPage;
import appLogic.Constants;
import driverLogic.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;

public class AssignSocialAssistanceForChildbirth extends TestBase {


    @Test
    public void successMessagesServicesByPostOffice() throws AWTException {
        //------------------- Тестовые данные -------------------//
        String service = Constants.Settings.InteractionWithPublicAuthorities.AssignSocialAssistanceForChildbirth;
        String region = Constants.Settings.Region.Dnipropetrovska;
        String address1 = "проспект карла маркса 22";
        String address2 = "проспект карла маркса 22";
        String phone = "039 123 4567";
        String email = "test@gmail.com";
        String innScanDocument = "/Users/apple/IdeaProjects/iTest/src/resources/test.jpg";
        String birthScanDocument = "/Users/apple/IdeaProjects/iTest/src/resources/test.jpg";
        String area = "Самарський, м.Дніпропетровськ";
        String transferType = "через національного оператора поштового зв'язку";
        String numberPostOffice = "12345";
        String status = "Заявка подана";

        //------------------- Тест-кейс -------------------//
        mainPage.typeInSearchField(service);
        app.pause(4000);
        mainPage.clickService(service);
        Assert.assertEquals(selectAreaPage.serviceName.getText(), service);
        selectAreaPage.selectRegion(region);
        authorizationPage.privatBankAuthorization();
        Assert.assertEquals(assignSocialAssistanceForChildbirthPage.serviceName.getText(), service);
        assignSocialAssistanceForChildbirthPage
                .typeInAdress1Field(address1)
                .typeInAdress2Field(address2)
                .typeInEmailField(email)
                .typeInPhoneField(phone)
                .attachDocument(assignSocialAssistanceForChildbirthPage.attachInnScanDocument, innScanDocument)
                .attachDocument(assignSocialAssistanceForChildbirthPage.attachBirthScanDocument, birthScanDocument)
                .selectArea(area)
                .selectTransferTypeField(transferType)
                .typeInNumberPostOfficeField(numberPostOffice)
                .clickConfirmButton()
                .verifyServiceSuccessCreated(email)
                .saveReferenceNumber();
        mainPage.goToStatus();
        statusPage.inputReferenceNumberForTest_fields_bankid()
                .clickViewStatusButton()
                .verifyStatus(status);
    }

    @Test
    public void successMessagesServicesByAccountBank() throws AWTException {
        //------------------- Тестовые данные -------------------//
        String service = Constants.Settings.InteractionWithPublicAuthorities.AssignSocialAssistanceForChildbirth;
        String region = Constants.Settings.Region.Dnipropetrovska;
        String address1 = "проспект карла маркса 22";
        String address2 = "проспект карла маркса 22";
        String phone = "039 123 4567";
        String email = "test@gmail.com";
        String innScanDocument = "/Users/apple/IdeaProjects/iTest/src/resources/test.jpg";
        String birthScanDocument = "/Users/apple/IdeaProjects/iTest/src/resources/test.jpg";
        String area = "Самарський, м.Дніпропетровськ";
        String transferType = "на рахунок у банку";
        String bankName = "";
        String bankMFO = "";
        String bankOKPO = "";
        String bankAccount = "";
        String bankTicket = "/Users/apple/IdeaProjects/iTest/src/resources/test.jpg";
        String status = "Заявка подана";

        //------------------- Тест-кейс -------------------//
        mainPage.typeInSearchField(service);
        app.pause(4000);
        mainPage.clickService(service);
        Assert.assertEquals(selectAreaPage.serviceName.getText(), service);
        selectAreaPage.selectRegion(region);
        authorizationPage.privatBankAuthorization();
        Assert.assertEquals(assignSocialAssistanceForChildbirthPage.serviceName.getText(), service);
        assignSocialAssistanceForChildbirthPage
                .typeInAdress1Field(address1)
                .typeInAdress2Field(address2)
                .typeInEmailField(email)
                .typeInPhoneField(phone)
                .attachDocument(assignSocialAssistanceForChildbirthPage.attachInnScanDocument, innScanDocument)
                .attachDocument(assignSocialAssistanceForChildbirthPage.attachBirthScanDocument, birthScanDocument)
                .selectArea(area)
                .selectTransferTypeField(transferType)
                //.typeInNumberPostOfficeField()
                .clickConfirmButton()
                .verifyServiceSuccessCreated(email)
                .saveReferenceNumber();
        mainPage.goToStatus();
        statusPage.inputReferenceNumberForTest_fields_bankid()
                .clickViewStatusButton()
                .verifyStatus(status);
    }
}
