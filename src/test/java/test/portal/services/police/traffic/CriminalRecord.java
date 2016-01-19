package test.portal.services.police.traffic;

import common.Constants;
import org.testng.annotations.Test;
import pages.service.police.traffic.CriminalRecordPage;
import test.TestBase;

import static org.testng.Assert.assertEquals;

public class CriminalRecord extends TestBase {

    @Test (priority = 10)
    public void DnipropetrovskCriminalRecord() {

        // ------------------- Тестовые данные -------------------//
        String service = Constants.Services.MVD.CRIMINAL_RECORD;
        String region = Constants.Areas.Region.DNIPROPETROVSKA;
        String city = Constants.Areas.City.DNIPROPETROVSK;
        String birthDate = Constants.TestData.PersonalInfo.BIRTH_DAY;
        String birthLoc = "Украина";
        String country = "Україна";
        String goal = "Оформлення візи для виїзду за кордон.";
        String phone = Constants.TestData.PersonalInfo.PHONE;
        String resType = "Прошу надати довідку в паперовому вигляді";
        String email = Constants.TestData.PersonalInfo.E_MAIL;
        String status = "Днепропетровск - Надання довідки про відсутність або наявніcть судимості";
        // --------------------- Тест-кейс----------------------//
        app.mainPage.typeInSearchField(service);
        app.mainPage.clickService(service);
        assertEquals(app.selectAreaPage.serviceName.getText(), service);
        app.selectAreaPage.selectRegion(region);
        app.selectAreaPage.selectCity(city);
        app.bankIdPage.loginByPrivatBankBankID();
        assertEquals(app.criminalRecordPage.getServiceName(), service);

        app.criminalRecordPage.typeInBirthDateField(birthDate)
                .typeInBirthLocField(birthLoc)
                .selectСountry(country)
                .selectGoal(goal)
                .typeInPhoneField(phone)
                .selectResType(resType)
                .typeInEmailField(email)
                .clickConfirmButton()
                .verifyServiceSuccessCreated()
                .saveReferenceNumber();

        app.bankIdPage.logOut();
        app.navHelper.openStatusesPage();
        app.statusPage.enterReferenceNumber(CriminalRecordPage.referenceNumber)
                .clickViewStatusButton()
                .verifyStatus(status);
    }
}