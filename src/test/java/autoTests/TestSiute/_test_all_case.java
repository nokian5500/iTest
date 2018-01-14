package autoTests.TestSiute;

import autoTests.CustomMethods;
import autoTests.pages.main.TemplatePage;
import org.junit.Test;

/**
 * Created by Privat24 on 09.09.2016.
 */
public class _test_all_case extends CustomMethods {

    //<editor-fold desc="Тестовый пример заполнение полей">
    @Test
    // Тест 1 - проверка Электронной очереди + основных типов полей + файлов
    public void default_test1() throws Exception {
        /**
         * ***************************************объявляем элементы страниц******************************************
         */
        TemplatePage o = new TemplatePage();
        //  Вносим в переменные название услуги начиная с точки ._test_fields_bankid_--_ и до начала названия поля
        String sBP = "_test_all_case";
        String email = "autotestbeta@gmail.com";

        openURLservice(getBaseUrl() + "/service/745/general");

        // 2016-11-23 Убираем проверку на открытие нужной услуги.
        // Считаем, что мы ее правильно по прямому пути выше 
        // _step("2. Проверить, что открылась нужная услуга");
        // assertThis(driver, o.usluga, "Копії рішень міської (селищної) ради про надання дозволу на розроблення проекту відведення земельної ділянки");
        o.selectRegion("Вінницька");
        o.selectCity("Вінниця");

        o.testPrivat24Authorization();

        setFieldAutocomplete("sID_Public_SubjectOrganJoin", "_test_all_case");
        setFieldValue("phone", "+380623155533");
        setFieldValue("email", email);
        setFieldCheckBox("bMailWithSMS");
        setFieldFile(sBP, "nFileMail", "src/test/resources/files/test.jpg");

        setFieldSelectSlotDate();
        setFieldSelectSlotTime();

        click(o.buttonSendingForm);

        o.checkMessageSuccess("Шановний(-а) MockUser MockUser!\n"
                + "Ваше звернення х-хххххххх успішно зареєстровано\n"
                + "(номер також відправлено Вам електронною поштою на Ваш e-mail " + email + ") Результати будуть спрямовані також на email.\n"
                + "Звертаємо увагу, що Іноді листи потрапляють у спам або у розділ \"Реклама\" (для Gmail).");

        click(o.buttonLogOut);
    }
    //</editor-fold>
}
