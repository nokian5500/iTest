package test.portal.services.authorities.interaction;

import common.Constants;
import org.testng.annotations.Test;
import pages.service.authorities.interaction.SubsidyPage;
import test.TestBase;

import static org.testng.Assert.assertEquals;

public class Subsidy extends TestBase {

    //@Test (priority = 10)
    public void dnipropetrovskSubsidyTest() {

        // ------------------- Тестовые данные -------------------//
        String service = Constants.Services.InteractionWithPublicAuthorities.SUBSIDY;
        String region = Constants.Areas.Region.DNIPROPETROVSKA;
        String area = "Амур-Нижньодніпровський район, м.Дніпропетровськ";
        String placeOfLiving = "test";
        String phone = Constants.TestData.PersonalInfo.PHONE;
        String email = Constants.TestData.PersonalInfo.E_MAIL;
        String subsidyType = "Оплата житлово-комунальних послуг";
        String electricity = "Не користуюсь";
        String houseArea = "Не користуюсь";
        String gas = "Не користуюсь";
        String coolWater = "Не користуюсь";
        String hotWater = "Не користуюсь";
        String waterBack = "Не користуюсь";
        String warming = "Не користуюсь";
        String garbage = "Не користуюсь";
        String placeType = "окремий будинок";
        String floors = "2";
        String totalPlace = "35";
        String warmingPlace = "32";
        String income = "test";
        String orgName = "test";
        String otherPeople = "Ні";
        String infoAboutoOverload = "test";

        // --------------------- Тест-кейс----------------------//
        app.mainPage.typeInSearchField(service);
        app.mainPage.clickService(service);
        assertEquals(app.selectAreaPage.serviceName.getText(), service);
        app.selectAreaPage.selectRegion(region);
        app.bankIdPage.loginByPrivatBankBankID();
        assertEquals(app.subsidyPage.getServiceName(), service);
        app.subsidyPage
                .selectArea(area)
                .typeInPlaceOfLivingField(placeOfLiving)
                .typeInPhoneField(phone)
                .typeInEmailField(email)
                .selectSubsidyType(subsidyType)
                .selectElectricity(electricity)
                .selectHouseArea(houseArea)
                .selectGas(gas)
                .selectCoolWater(coolWater)
                .selectHotWater(hotWater)
                .selectWaterBack(waterBack)
                .selectWarming(warming)
                .selectGarbage(garbage)
                .selectPlaceType(placeType)
                .typeInFloorsField(floors)
                .typeInTotalPlaceField(totalPlace)
                .typeInWarmingPlaceField(warmingPlace)
                .typeInIncomeField(income)
                .typeInOrgNameField(orgName)
                .selectOtherPeople(otherPeople)
                .typeInInfoAboutoOverloa(infoAboutoOverload)
                .clickConfirmButton()
                .verifyServiceSuccessCreated()
                .saveReferenceNumber();
        app.bankIdPage.logOut();
        app.navHelper.openStatusesPage();
        app.statusPage.enterReferenceNumber(SubsidyPage.referenceNumber)
                .clickViewStatusButton()
                .verifyStatus(Constants.Status.SUCCESS_STATUS7);
    }
}