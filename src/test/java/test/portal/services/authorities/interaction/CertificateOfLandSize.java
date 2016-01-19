package test.portal.services.authorities.interaction;

import common.Constants;
import org.testng.annotations.Test;
import pages.service.authorities.interaction.LandSizeAndExistencePage;
import test.TestBase;

import static org.testng.Assert.assertEquals;

/**
 * Test for services in this class:
 * - " Надання довідки про наявність та розмір земельної частки (паю)"
 *   (https://github.com/e-government-ua/iTest/issues/30)
 */
public class CertificateOfLandSize extends TestBase {

    @Test(priority = 10)
    public void certificateOfLandSizeTest() {

        String service = Constants.Services.InteractionWithPublicAuthorities.CERTIFICATE_OF_LAND_SIZE;
        String region = Constants.Areas.Region.DNIPROPETROVSKA;
        String district = "ЦНАП Вільногірської міськради";
        String address = "Дніпропетровськ (Центральний), вул. Поля, 1";
        String landAddress = "Вільногірськ, вул. Вільногірська, 1";
        String phone = Constants.TestData.PersonalInfo.PHONE;
        String email = Constants.TestData.PersonalInfo.E_MAIL;
        String applicant = "Особа, в інтересах якої встановлене обмеження";
        String filePath = "src/test/resources/files/test.jpg";
        String landRegisterNumber = "1234567890:45:456:1234";
        String status = "";

        app.mainPage.typeInSearchField(service);
        app.mainPage.clickService(service);

        assertEquals(app.selectAreaPage.serviceName.getText(), service);
        app.selectAreaPage.selectRegion(region);

        app.bankIdPage.loginByPrivatBankBankID();

        app.landSizeAndExistencePage
                .selectDistrict(district)
                .enterAddress(address)
                .typeInPhoneField(phone)
                .typeInEmailField(email)
                .selectApplicant(applicant)
                .typeInLandAddressField(landAddress)
                .typeInLandRegisterNumberField(landRegisterNumber)
                .attachFile(app, filePath)
                .clickConfirmButton()
                .verifyServiceSuccessCreated()
                .saveReferenceNumber();
        app.bankIdPage.logOut();
        app.navHelper.openStatusesPage();
        app.statusPage.enterReferenceNumber(LandSizeAndExistencePage.referenceNumber)
                .clickViewStatusButton()
                .verifyStatus(status);
    }
}