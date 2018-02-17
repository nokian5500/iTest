package autoTests.TestSiute.iGov;

import autoTests.CustomMethods;
import autoTests.pages.main.TemplatePage;
import org.junit.Test;

/**
 * Created by Privat24 on 09.09.2016.
 */
public class dnepr_dms_89 extends CustomMethods {

    //<editor-fold desc="Тестовый пример заполнение полей">
    @Test
    public void default_test() throws Exception {
        /**
         * ***************************************объявляем элементы страниц******************************************
         */
        TemplatePage o = new TemplatePage();
        //  Вносим в переменные название услуги начиная с точки ._test_fields_bankid_--_ и до начала названия поля
        String sBP = "dnepr_dms-89";
        String email = "autotestbeta@gmail.com";

        openURLservice(getBaseUrl() + "/service/29/general");

        o.selectRegion("Дніпропетровська");
        o.selectCity("Дніпро (Дніпропетровськ)");

        o.testPrivat24Authorization();

        setFieldValue("phone", "+380623155533");
        setFieldValue("email", email);
        setFieldSelectByText("asPrevName", "Ні");
        setFieldValue("sDate_of_birth", "Дата народження");
        setFieldTextArea(sBP, "sAreabirth", "Місце народження");
        setFieldValue("sNationality", "Україна");
        setFieldTextArea(sBP, "sRegistrationAddress", "Стара адреса реєстрації");
        setFieldAutocomplete("sID_Public_SubjectOrganJoin", "Соборний (Жовтневий) район");
        setFieldTextArea(sBP, "sNewAddress", "Нова адреса реєстрації");
        setFieldSelectByText("asRegistrationType", "до власного житлового приміщення");
        setFieldSelectByText("asRegistrationDoc", "Ордер");
        setFieldTextArea(sBP, "sDocRekv", "Опис документу");
        setFieldSelectByText("asMilitStatus", "Ні");
        setFieldSelectByText("asRegistr", "Ні");
        setFieldValue("sWhenCome", "2016-11-28");
        setFieldSelectSlotDate();
        setFieldSelectSlotTime();
//        setFieldSelectSlotDate(driver, sBP, "visitDay"); 
//        setFieldSelectSlotTime(driver, sBP, "visitDay");
//        pause(1000000); 
        setFieldFile(sBP, "nOrder", "src/test/resources/files/test.jpg");
        setFieldFile(sBP, "nForma3", "src/test/resources/files/test.jpg");
        setFieldValue("sMailClerk", email);

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
        setRegionTask();
        clickButton("Взяти в роботу");
        clickButton("Почати опрацювання задачі");

        // Опрацювання [Етап I] 
        SetRegionFieldInputTypeEnum("Призначити зустріч у відділенні");
        SetRegionFieldInputTypeTextArea("sDecisionComment", "Коментар до рішення");

        clickButton("Опрацювати");
        clickButton("Підтвердити");
        clickButton("Ok");

        // Опрацювання [Етап II]
        setRegionFindOrder(sBP);
        clickButton("Взяти в роботу");
        clickButton("Почати опрацювання задачі");

        SetRegionFieldInputTypeEnum("Громадянин зареєстрований");

        clickButton("Опрацювати");
        clickButton("Підтвердити");
        clickButton("Ok");

    }
    //</editor-fold>
}
