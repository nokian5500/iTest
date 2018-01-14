package autoTests.TestSiute;

import autoTests.CustomMethods;
import autoTests.pages.main.TemplatePage;
import org.junit.Test;

/**
 * Created by Privat24 on 09.09.2016.
 */
public class Test_Example_Fill_Field extends CustomMethods {

    //<editor-fold desc="Тестовый пример заполнение полей">
    @Test
    public void Test_Example_Fill_Field() throws Exception {
        /**
         * ***************************************объявляем элементы страниц******************************************
         */
        TemplatePage o = new TemplatePage();
        //  Вносим в переменные название услуги и почту
        String sBP = "_test_fields_bankid";
        String email = "v-i-d-o-k@mail.ru";

        openURLservice(getBaseUrl() + "/service/720/general");

        // 2016-11-23 Убираем проверку на открытие нужной услуги.
        // Считаем, что мы ее правильно по прямому пути выше 
        // _step("2. Проверить, что открылась нужная услуга");
        // assertThis(driver, o.usluga, "_test_fields_bankid");
        o.selectRegion("Дніпропетровська");
        o.selectCity("Дніпро (Дніпропетровськ");

        o.mokAuthorization();

        setFieldValue("tooltiptext", "test");
        setFieldValue("email", email);

        click(o.buttonSendingForm);

        o.checkMessageSuccess("Шановний(-а) MockUser MockUser!\n"
                + "Ваше звернення х-хххххххх успішно зареєстровано\n"
                + "(номер також відправлено Вам електронною поштою на Ваш e-mail " + email + ") Результати будуть спрямовані також на email.\n"
                + "Звертаємо увагу, що Іноді листи потрапляють у спам або у розділ \"Реклама\" (для Gmail).");

        click(o.buttonLogOut);
    }
    //</editor-fold>
}
