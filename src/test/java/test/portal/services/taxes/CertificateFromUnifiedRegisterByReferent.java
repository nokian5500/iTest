package test.portal.services.taxes;

import common.Constants;
import org.testng.annotations.Test;
import pages.service.taxes.PensionAmountCertificatePage;
import test.TestBase;

import static org.testng.Assert.assertEquals;

/**
 * Test for services in this class:
 * - "Надання витягу з реєстру платників єдиного податку"
 * (https://github.com/e-government-ua/iTest/issues/31)
 */
public class CertificateFromUnifiedRegisterByReferent extends TestBase {

    @Test(priority = 10)
    public void certificateFromURByReferentSelf(){
        //------------------- Test Data -------------------//
        String service = Constants.Services.Taxes.CERTIFICATE_FROM_UNIFIED_REGISTER;
        String region = Constants.Areas.Region.DNIPROPETROVSKA;
        String fiscalBranch = "Дніпропетровськ - Бабушкінський (вул. Героїв Сталінграду, 25)|0462";
        String firstName = "ДМИТРО";
        String lastName = "ДУБІЛЕТ";
        String middleName = "ОЛЕКСАНДРОВИЧ";
        String phone = Constants.TestData.PersonalInfo.PHONE;
        String email = Constants.TestData.PersonalInfo.E_MAIL;
        String filePath = "src/test/resources/files/test.jpg";
        String referenceNumber;

        //------------------- Test Case -------------------//
        app.mainPage.typeInSearchField(service);
        app.mainPage.clickService(service);
        assertEquals(app.selectAreaPage.serviceName.getText(), service);
        app.selectAreaPage.selectRegion(region);
        app.bankIdPage.loginByPrivatBankBankID();
        referenceNumber =  app.certificateFromUnifiedRegisterPage
                .clickOnFillByReferentField()
                .typeInLastNameField(lastName)
                .typeInFirstNameField(firstName)
                .typeInMiddleNameField(middleName)
                .typeInPassportField("TE000000ST test department from 1970")
                .typeInIPNField("1234567890")
                .typeInEmailField(email)
                .typeInPhoneField(phone)
                .selectFiscalBranchField(fiscalBranch)
                .selectCertificateSubject("по собі")
                .typeInReasonForCertificateField("test")
                .attachFile(app, filePath)
                .clickConfirmButton()
                .verifyServiceSuccessCreated()
                .saveReferenceNumber();
        app.navHelper.openStatusesPage();
        app.statusPage.enterReferenceNumber(referenceNumber)
                .clickViewStatusButton()
                .verifyStatus(Constants.Status.SUCCESS_STATUS3);
    }

    @Test(priority = 10)
    public void certificateFromURByReferentByOtherCompany(){
        //------------------- Test Data -------------------//
        String service = Constants.Services.Taxes.CERTIFICATE_FROM_UNIFIED_REGISTER;
        String region = Constants.Areas.Region.DNIPROPETROVSKA;
        String fiscalBranch = "Дніпропетровськ - Бабушкінський (вул. Героїв Сталінграду, 25)|0462";
        String firstName = "ДМИТРО";
        String lastName = "ДУБІЛЕТ";
        String middleName = "ОЛЕКСАНДРОВИЧ";
        String phone = Constants.TestData.PersonalInfo.PHONE;
        String email = Constants.TestData.PersonalInfo.E_MAIL;
        String filePath = "src/test/resources/files/test.jpg";
        String referenceNumber;

        //------------------- Test Case -------------------//
        app.mainPage.typeInSearchField(service);
        app.mainPage.clickService(service);
        assertEquals(app.selectAreaPage.serviceName.getText(), service);
        app.selectAreaPage.selectRegion(region);
        app.bankIdPage.loginByPrivatBankBankID();
        referenceNumber =  app.certificateFromUnifiedRegisterPage
                .clickOnFillByReferentField()
                .typeInLastNameField(lastName)
                .typeInFirstNameField(firstName)
                .typeInMiddleNameField(middleName)
                .typeInPassportField("TE000000ST test department from 1970")
                .typeInIPNField("1234567890")
                .typeInEmailField(email)
                .typeInPhoneField(phone)
                .selectFiscalBranchField(fiscalBranch)
                .selectCertificateSubject("по іншому підприємству")
                .typeInEdrpouORIpnField("1234567890")
                .typeInCompanyNameField("test company")
                .typeInReasonForCertificateField("test")
                .attachFile(app, filePath)
                .clickConfirmButton()
                .verifyServiceSuccessCreated()
                .saveReferenceNumber();
        app.navHelper.openStatusesPage();
        app.statusPage.enterReferenceNumber(referenceNumber)
                .clickViewStatusButton()
                .verifyStatus(Constants.Status.SUCCESS_STATUS3);
    }


}
