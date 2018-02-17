package autoTests.TestSiute.iGov;

import autoTests.CustomMethods;
import autoTests.pages.main.TemplatePage;
import org.junit.Test;

/**
 * Created by Privat24 on 09.09.2016.
 */
public class rada_0024_dovidka_live_place_2_step_Dnepr extends CustomMethods {

    //<editor-fold desc="Тестовый пример заполнение полей">
    @Test
    public void default_test() throws Exception {
        /**
         * ***************************************объявляем элементы страниц******************************************
         */
        TemplatePage o = new TemplatePage();
        //  Вносим в переменные название услуги начиная с точки ._test_fields_bankid_--_ и до начала названия поля
        String sBP = "rada_0024_dovidka_live_place_2_step_Dnepr";
        String email = "autotestbeta@gmail.com";

        openURLservice(getBaseUrl() + "/service/24/general");

        o.selectRegion("Дніпропетровська");
        o.selectCity("Дніпро (Дніпропетровськ)");

        o.mokAuthorization();

        setFieldAutocomplete("sID_Public_SubjectOrganJoin", "Соборний (Жовтневий) район");
        setFieldSelectByText("asEnumTypeDocument", "ID-карта (паспорт)");
        setFieldTextArea(sBP, "sBankIdPassport", "Документ, що посвідчує заявника");
        setFieldFile(sBP, "nFile_CopyPassportZ", "src/test/resources/files/test.jpg");
        setFieldValue("phone", "+380673155533");
        setFieldValue("email", email);
        setFieldSelectByText("asForMySelf", "Так, для себе");
        setFieldSelectByText("asTargetGetDovidka", "Вперше оформлення паспорта (ID-картки)");

        setFieldValue("sDate_of_birth", "9 липня 2000");
        setFieldValue("sAreabirth", "Місце народження");
        setFieldTextArea(sBP, "sRegistrationAddress", "Адреса реєстрації");
        setFieldValue("sNationality", "Україна");
        setFieldValue("sMailClerk", email);
//        setFieldFile(driver, sBP, "nFile_SvidoztvoNarodzennya", "src/test/resources/files/test.jpg");

        click(o.buttonSendingForm);

        o.checkMessageSuccess("Шановний(-а) MockUser MockUser!\n"
                + "Ваше звернення х-хххххххх успішно зареєстровано\n"
                + "(номер також відправлено Вам електронною поштою на Ваш e-mail " + email + ") Результати будуть спрямовані також на email.\n"
                + "Звертаємо увагу, що Іноді листи потрапляють у спам або у розділ \"Реклама\" (для Gmail).");

        click(o.buttonLogOut);

        openURLdashboard(getRegionUrl());

        AuthorizationBySetLoginPassword("tester", "tester");
        clickButton("Увійти");

        // Опрацювання в табі "В необроблені"    
        setRegionFindOrder(sBP);
        clickButton("Взяти в роботу");
        clickButton("Почати опрацювання задачі");

        // Опрацювання [Етап I] 
        SetRegionFieldInputTypeEnum("Дані громадянина вірні, починаємо формування довідки");
        SetRegionFieldInputTypeTextArea("sDecisionComment", "Коментар до рішення");

        clickButton("Опрацювати");
        clickButton("Підтвердити");
        clickButton("Ok");

        // Опрацювання [Етап II]
        setRegionFindOrder(sBP);
        clickButton("Взяти в роботу");
        clickButton("Почати опрацювання задачі");

        SetRegionFieldInputTypeEnum("Довідка виготовлена, призначаємо зустріч у відділенні");
        SetRegionFieldInputTypeTextArea("sDecisionComment", "Коментар до рішення");

        clickButton("Опрацювати");
        clickButton("Підтвердити");
        clickButton("Ok");

    }
    //</editor-fold>
}
