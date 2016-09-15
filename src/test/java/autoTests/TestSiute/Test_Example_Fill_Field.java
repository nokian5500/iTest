package autoTests.TestSiute;

import autoTests.pages.main.TemplatePage;
import org.testng.annotations.Test;

/**
 * Created by Privat24 on 09.09.2016.
 */
public class Test_Example_Fill_Field extends BaseTest {

    //<editor-fold desc="Тестовый пример заполнение полей">
    @Test(enabled = true, groups = {"Main", "Критический функционал"}, priority = 1)
    public void Test_Example_Fill_Field() throws Exception {
        /*****************************************объявляем элементы страниц*******************************************/
        TemplatePage o = new TemplatePage(driver);
        //  Вносим в переменные название услуги и почту
        String sBP = "_test_fields_bankid";
        String email = "v-i-d-o-k@mail.ru";

        _step("1. Вход по прямому URL на услугу");
        openURLservice(driver, CV.baseUrl + "/service/720/general");

        _step("2. Проверить, что открылась нужная услуга");
        assertThis(driver, o.usluga, "_test_fields_bankid");

        _step("3. Выбор области/города");
        o.selectRegion("Дніпропетровська");
        o.selectCity("Дніпро (Дніпропетровськ");

        _step("4. Авторизация Off AuthMock/BankID");
        o.mokAuthorization();

        _step("5. Заполняем форму услуги");
        setFieldValue(driver, sBP, "tooltiptext", "test");
        setFieldValue(driver, sBP, "email", email);

        _step("6. Отправка формы");
        click(driver, o.buttonSendingForm);

        _step("7. Проверка сообщения о успешной отправке");
        o.checkMessageSuccess("Шановний(-а) MockUser MockUser!\n" +
                "Ваше звернення х-хххххххх успішно зареєстровано\n" +
                "(номер також відправлено Вам електронною поштою на Ваш e-mail " + email + ") Результати будуть спрямовані також на email.\n" +
                "Звертаємо увагу, що Іноді листи потрапляють у спам або у розділ \"Реклама\" (для Gmail).");

        _step("8. Нажать кнопку Выйти");
        click(driver, o.buttonLogOut);
    }
    //</editor-fold>
}
