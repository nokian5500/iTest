package test.portal.services.taxes;

import common.Constants;
import org.testng.annotations.Test;
import pages.service.taxes.PersonalIncomeCertificatePage;
import test.TestBase;

import static org.testng.Assert.assertEquals;

public class PersonalIncomeCertificate extends TestBase {

    //@Test(priority = 10)
    public void personalIncomeCertificateTest() {

        //------------------- Test Data -------------------//
        String service = Constants.Services.Taxes.PERSONAL_INCOME_CERTIFICATE;
        String region = Constants.Areas.Region.DNIPROPETROVSKA;
        String startOfPeriod = ""; //TEMP, until interaction with Calendar dropdown will be implemented
        String endOfPeriod = ""; //TEMP, until interaction with Calendar dropdown will be implemented
        String fiscalBranch = "Дніпропетровськ - АНД (пров. Універсальний, 12)|0461";
        String phone = Constants.TestData.PersonalInfo.PHONE;
        String email = Constants.TestData.PersonalInfo.E_MAIL;
        String placeOfLiving = "Дніпропетровськ, вул. Поля, 1";
        String aim = "ТЕСТ для подання за вимогою";

        //------------------- Test Case -------------------//
        app.mainPage.typeInSearchField(service);
        app.mainPage.clickService(service);
        assertEquals(app.selectAreaPage.serviceName.getText(), service);
        app.selectAreaPage.selectRegion(region);
        app.bankIdPage.loginByPrivatBankBankID();
        app.personalIncomeCertificatePage
                .selectFiscalBranchField(fiscalBranch)
                .typeInPhoneField(phone)
                .typeInEmailField(email)
                .typeInPlaceLivingField(placeOfLiving)
                .selectStartOfPeriodField(startOfPeriod)
                .selectEndOfPeriodField(endOfPeriod)
                .typeInAimField(aim)
                .clickConfirmButton()
                .verifyServiceSuccessCreated()
                .saveReferenceNumber();
        app.navHelper.openStatusesPage();
        app.statusPage.enterReferenceNumber(PersonalIncomeCertificatePage.referenceNumber)
                .clickViewStatusButton()
                .verifyStatus(Constants.Status.SUCCESS_STATUS3);
    }
}