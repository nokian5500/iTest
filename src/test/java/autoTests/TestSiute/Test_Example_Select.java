package autoTests.TestSiute;

import autoTests.CustomMethods;
import autoTests.pages.main.TemplatePage;
import org.junit.Test;

public class Test_Example_Select extends CustomMethods {

    //<editor-fold desc="Тестовый пример выпадающее листы">
    @Test
    public void Test_Example_Select() throws Exception {
        /**
         * ***************************************объявляем элементы страниц******************************************
         */
        TemplatePage o = new TemplatePage();
        //  Вносим в переменные название услуги и почту
        String sBP = "_test_sID_UA";
        String email = "v-i-d-o-k@mail.ru";

        openURLservice(getBaseUrl() + "/service/785/general");

        // 2016-11-23 Убираем проверку на открытие нужной услуги.
        // Считаем, что мы ее правильно по прямому пути выше 
        // _step("2. Проверить, что открылась нужная услуга");
        // assertThis(driver, o.usluga, "_test_sID_UA");
        o.selectRegion("Дніпропетровська");
        o.selectCity("Дніпро (Дніпропетровськ");

        o.mokAuthorization();

        setFieldValue("sID_Place_UA", "test");
        setFieldValue("sID_UA", "test");
        setFieldValue("email", email);
        setFieldSelectByText("client", "нет");

        click(o.buttonSendingForm);

        o.checkMessageSuccess("Шановний(-а) MockUser MockUser!\n"
                + "Ваше звернення х-хххххххх успішно зареєстровано\n"
                + "(номер також відправлено Вам електронною поштою на Ваш e-mail " + email + ") Результати будуть спрямовані також на email.\n"
                + "Звертаємо увагу, що Іноді листи потрапляють у спам або у розділ \"Реклама\" (для Gmail).");

        click(o.buttonLogOut);
    }
    //</editor-fold>

}
