package test.portal.services.authorities.interaction;

import common.Constants;
import org.testng.annotations.Test;
import pages.service.authorities.interaction.AssignSocialAssistanceForChildBirthPage;
import test.TestBase;

import java.awt.*;

import static org.testng.Assert.assertEquals;

public class AssignSocialAssistanceForChildBirth extends TestBase {

    @Test(description = "Призначення соціальної допомоги при народженні дитини через національного оператора поштового зв'язку", priority = 10)
    public void successMessagesServicesByPostOffice() {

        //------------------- Тестовые данные -------------------//
        String service = Constants.Services.InteractionWithPublicAuthorities.ASSIGN_SOCIAL_ASSISTANCE_FOR_CHILD_BIRTH;
        String region = Constants.Areas.Region.DNIPROPETROVSKA;
        String address1 = "проспект карла маркса 22";
        String address2 = "проспект карла маркса 22";
        String phone = Constants.TestData.PersonalInfo.PHONE;
        String email = Constants.TestData.PersonalInfo.E_MAIL;
        String innScanDocument = "src/test/resources/files/test.jpg";
        String birthScanDocument = "src/test/resources/files/test.jpg";
        String area = "Самарський, м.Дніпропетровськ";
        String transferType = "через національного оператора поштового зв'язку";
        String numberPostOffice = "12345";
        String status = "Заявка подана";

        //------------------- Тест-кейс -------------------//
        app.mainPage.typeInSearchField(service);
        app.mainPage.clickService(service);
        assertEquals(app.selectAreaPage.serviceName.getText(), service);
        app.selectAreaPage.selectRegion(region);
        app.bankIdPage.loginByPrivatBankBankID();
        assertEquals(app.assignSocialAssistanceForChildBirthPage.serviceName.getText(), service);
        app.assignSocialAssistanceForChildBirthPage
                .typeInAdress1Field(address1)
                .typeInAdress2Field(address2)
                .typeInEmailField(email)
                .typeInPhoneField(phone);
        app.attachDocument(app.assignSocialAssistanceForChildBirthPage.attachInnScanDocument, innScanDocument);
        app.attachDocument(app.assignSocialAssistanceForChildBirthPage.attachBirthScanDocument, birthScanDocument);
        app.assignSocialAssistanceForChildBirthPage
               // .selectArea(area)
                .selectTransferTypeField(transferType)
                .typeInNumberPostOfficeField(numberPostOffice)
                .clickConfirmButton()
                .verifyServiceSuccessCreated(email)
                .saveReferenceNumber();
        app.bankIdPage.logOut();
        app.navHelper.openStatusesPage();
        app.statusPage.enterReferenceNumber(AssignSocialAssistanceForChildBirthPage.referenceNumber)
                .clickViewStatusButton()
                .verifyStatus(status);
    }

    @Test(description = "Призначення соціальної допомоги при народженні дитини на рахунок у банку", priority = 20)
    public void successMessagesServicesByAccountBank() throws AWTException {

        //------------------- Тестовые данные -------------------//
        String service = Constants.Services.InteractionWithPublicAuthorities.ASSIGN_SOCIAL_ASSISTANCE_FOR_CHILD_BIRTH;
        String region = Constants.Areas.Region.DNIPROPETROVSKA;
        String address1 = "проспект карла маркса 22";
        String address2 = "проспект карла маркса 22";
        String phone = Constants.TestData.PersonalInfo.PHONE;
        String email = Constants.TestData.PersonalInfo.E_MAIL;
        String innScanDocument = "src/test/resources/files/test.jpg";
        String birthScanDocument = "src/test/resources/files/test.jpg";
        String area = "Самарський, м.Дніпропетровськ";
        String transferType = "на рахунок у банку";
        String bankName = Constants.TestData.TestBankDetails.BANK_NAME;
        String bankMFO = Constants.TestData.TestBankDetails.BANK_MFO;
        String bankOKPO = Constants.TestData.TestBankDetails.BANK_OKPO;
        String bankAccount = Constants.TestData.TestBankDetails.BANK_ACCOUNT;
        String bankTicket = "src/test/resources/test.jpg";
        String status = "Заявка подана";

        //------------------- Тест-кейс -------------------//
        app.mainPage.typeInSearchField(service);
        app.mainPage.clickService(service);
        assertEquals(app.selectAreaPage.serviceName.getText(), service);
        app.selectAreaPage.selectRegion(region);
        app.bankIdPage.loginByPrivatBankBankID();
        assertEquals(app.assignSocialAssistanceForChildBirthPage.serviceName.getText(), service);
        app.assignSocialAssistanceForChildBirthPage
                .typeInAdress1Field(address1)
                .typeInAdress2Field(address2)
                .typeInEmailField(email)
                .typeInPhoneField(phone);
        app.attachDocument(app.assignSocialAssistanceForChildBirthPage.attachInnScanDocument, innScanDocument);
        app.attachDocument(app.assignSocialAssistanceForChildBirthPage.attachBirthScanDocument, birthScanDocument);
        app.assignSocialAssistanceForChildBirthPage
                //.selectArea(area)
                .selectTransferTypeField(transferType)
                .typeInBankNameField(bankName)
                .typeInBankMFOField(bankMFO)
                .typeInBankOKPOField(bankOKPO)
                .typeInBankAccountField(bankAccount);
        app.attachDocument(app.assignSocialAssistanceForChildBirthPage.attachBankTicket, bankTicket);
        app.assignSocialAssistanceForChildBirthPage
                .clickConfirmButton()
                .verifyServiceSuccessCreated(email)
                .saveReferenceNumber();
        app.bankIdPage.logOut();
        app.navHelper.openStatusesPage();
        app.statusPage.enterReferenceNumber(AssignSocialAssistanceForChildBirthPage.referenceNumber)
                .clickViewStatusButton()
                .verifyStatus(status);
    }
}