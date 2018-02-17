package autoTests.TestSiute.iGov;

import autoTests.CustomMethods;
import autoTests.pages.main.TemplatePage;
import org.junit.Test;

/**
 * Created by Privat24 on 09.09.2016.
 */
public class socialhelp_kidsUkraine extends CustomMethods {

    //<editor-fold desc="Тестовый пример заполнение полей">
    @Test
    public void default_test() throws Exception {
        /**
         * ***************************************объявляем элементы страниц******************************************
         */
        TemplatePage o = new TemplatePage();
        //  Вносим в переменные название услуги начиная с точки ._test_fields_bankid_--_ и до начала названия поля
        String sBP = "socialhelp_kidsUkraine";
        String email = "autotestbeta@gmail.com";

        openURLservice(getBaseUrl() + "/service/752/general");

        o.selectRegion("Дніпропетровська");

        o.mokAuthorization();

        setFieldAutocomplete("sID_Public_SubjectOrganJoin", "Бабушкінській район, м.Дніпропетровськ");
        setFieldValue("phone", "+380623155533");
        setFieldValue("email", email);
        setFieldValue("adress1", "Ваша адреса реєстрації");
        setFieldValue("adress2", "Ваша адреса проживання");
        setFieldFile(sBP, "nNoHelp", "src/test/resources/files/test.jpg");
        setFieldFile(sBP, "inn_scan", "src/test/resources/files/test.jpg");
        setFieldFile(sBP, "birthdocument_scan", "src/test/resources/files/test.jpg");
        setFieldSelectByText("transfer_type", "через національного оператора поштового зв'язку");
        setFieldSelectByText("ECP", "без ЕЦП (погоджуєтесь на особистий візит після перевірки правильності даних)");
        setFieldValue("post_office", "12000");

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
        SetRegionFieldInputTypeEnum("Заповнені дані вірні, інформація направлена на розгляд, очікується рішення");
        SetRegionFieldInputTypeTextArea("comment1", "Коментар до рішення");

        clickButton("Опрацювати");
        clickButton("Підтвердити");
        clickButton("Ok");

        // Опрацювання [Етап II]
//        setRegionTask(driver, sBP);
        clickButton("Опрацювати");
        clickButton("Почати опрацювання задачі");

        SetRegionFieldInputTypeTextArea("comment2", "Коментар до заяви на етапі 2");
        SetRegionFieldInputTypeEnum("Рішення отримане, допомогу призначено");
        SetRegionFieldInputTypeFile("src/test/resources/files/test.jpg");
//
        clickButton("Опрацювати");
        clickButton("Підтвердити");
        clickButton("Ok");

    }
    //</editor-fold>
}
