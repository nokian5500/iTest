package test.portal.services.taxes;

import common.Constants;
import org.testng.annotations.Test;
import pages.service.taxes.PensionAmountCertificatePage;
import test.TestBase;

import static org.testng.Assert.assertEquals;

/**
 * Test for services in this class:
 * - " Видача довідки про доходи (розмір пенсії)"
 * (https://github.com/e-government-ua/iTest/issues/43)
 */
public class PensionAmountCertificate extends TestBase {

    @Test(priority = 10)
    public void personalIncomeCertificateTest() {

        //------------------- Test Data -------------------//
        String service = Constants.Services.Taxes.PENSION_AOUNT_CERTIFICATE;
        String region = Constants.Areas.Region.DNIPROPETROVSKA;
        String city = Constants.Areas.City.DNIPROPETROVSK;
        String startOfPeriod = "01/02/2009";
        String endOfPeriod = "01/02/2012";
        String address = "Дніпропетровськ - Соборний (вул. перемоги, 12)";
        String fiscalBranch = "Бабушкінський, вул. Героїв Сталінграда, 116-а";
        String phone = "0931234567";
        String email = Constants.TestData.PersonalInfo.E_MAIL;
        String filePath = "src/test/resources/files/test.jpg";

        //------------------- Test Case -------------------//
        app.mainPage.typeInSearchField(service);
        app.mainPage.clickService(service);
        assertEquals(app.selectAreaPage.serviceName.getText(), service);
        app.selectAreaPage.selectRegion(region);
        app.selectAreaPage.selectCity(city);
        app.bankIdPage.loginByPrivatBankBankID();
        app.pensionAmountCertificatePage
                .typeInAddressField(address)
                .typeInEmailField(email)
                .typeInPhoneField(phone)
                .selectFiscalBranchField(fiscalBranch)
                .selectStartOfPeriodField(startOfPeriod)
                .selectEndOfPeriodField(endOfPeriod)
                .attachFile(app, filePath)
                .clickConfirmButton()
                .verifyServiceSuccessCreated()
                .saveReferenceNumber();
        app.navHelper.openStatusesPage();
        app.statusPage.enterReferenceNumber(PensionAmountCertificatePage.referenceNumber)
                .clickViewStatusButton()
                .verifyStatus(Constants.Status.SUCCESS_STATUS);
    }
}
