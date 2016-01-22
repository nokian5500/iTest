package test.portal.services.identity.citizenship.residense;

import common.Constants;
import org.testng.annotations.Test;
import pages.service.identity.citizenship.residense.UnregisterFromLocationPage;
import test.TestBase;

import static org.testng.Assert.assertEquals;

/**
 * Покрытые в этом классе услуги:
 * - Зняття з реєстрації місця проживання (https://github.com/e-government-ua/iTest/issues/5)
 */
public class Registration extends TestBase {

    @Test (priority = 10)
    public void unregisterFromCurrentAddressTest() {

        String service = Constants.Services.InteractionWithPublicAuthorities.REGISTRATION;
        String region = Constants.Areas.Region.DNIPROPETROVSKA;
        String city = Constants.Areas.City.DNIPROPETROVSK;
        String birthDate = Constants.TestData.PersonalInfo.BIRTH_DAY;
        String birthLocation = "Україна,Дніпропетровська,Дніпропетровськ";
        String nationality = "Україна";
        String district = "Амур-Нижньодніпровський РВ у м.Дніпропетровську";
        String phone = Constants.TestData.PersonalInfo.PHONE;
        String email = Constants.TestData.PersonalInfo.E_MAIL;
        String area = "Дніпропетровськ (Центральний), вул. Поля, 1";
        String surnameChanged = "Ні";
        String militStatus = "Ні";
        String kids = "Ні";
        String status = "";

        app.mainPage.typeInSearchField(service);
        app.mainPage.clickService(service);
        assertEquals(app.selectAreaPage.serviceName.getText(), service);
        app.selectAreaPage.selectRegion(region);
        app.selectAreaPage.selectCity(city);
        app.bankIdPage.loginByPrivatBankBankID();
        assertEquals(app.unregisterFromLocationPage.getServiceName(), service);

        app.unregisterFromLocationPage
                .selectDistrict(district)
                .typeInPhoneField(phone)
                .typeInEmailField(email)
                .selectSurnameChanged(surnameChanged)
                .typeInBirthDateField(birthDate)
                .typeInBirthLocField(birthLocation)
                .selectNationality(nationality)
                .typeInCurrentAddress(birthLocation)
                .typeInNewAddress(area)
                .selectMilitaryStatus(militStatus)
                .selectIfKidsUnderFourteen(kids)
                .selectDay()
                .selectTime()
                .clickConfirmButton()
                .verifyServiceSuccessCreated()
                .saveReferenceNumber();

        app.bankIdPage.logOut();
        app.navHelper.openStatusesPage();
        app.statusPage.enterReferenceNumber(UnregisterFromLocationPage.referenceNumber)
                .clickViewStatusButton()
                .verifyStatus(status);
    }
}