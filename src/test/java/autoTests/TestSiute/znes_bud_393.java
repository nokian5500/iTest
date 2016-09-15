package autoTests.TestSiute;

import autoTests.pages.main.TemplatePage;
import org.testng.annotations.Test;

/**
 * Created by Privat24 on 09.09.2016.
 */
public class znes_bud_393 extends BaseTest {

    //<editor-fold desc="Тестовый пример заполнение полей">
    @Test(enabled = true, groups = {"Main", "Критический функционал"}, priority = 1)
    public void default_test() throws Exception {
        /*****************************************объявляем элементы страниц*******************************************/
        TemplatePage o = new TemplatePage(driver);
        //  Вносим в переменные название услуги начиная с точки ._test_fields_bankid_--_ и до начала названия поля
        String sBP = "znes_bud_393";
        String email = "smoktii.igov@gmail.com";

        _step("1. Вход по прямому URL на услугу");
        openURLservice(driver, CV.baseUrl + "/service/788/general");

        _step("2. Проверить, что открылась нужная услуга");
        assertThis(driver, o.usluga, "Надання дозволу на знесення аварійних будівель");

        _step("3. Выбор области/города");
        o.selectRegion("Київська");
        o.selectCity("Колонщина");

        _step("4. Авторизация Off AuthMock/BankID");
        o.mokAuthorization();

        _step("5. Заполняем форму услуги");
        setFieldAutocomplete(driver, "sID_Public_SubjectOrganJoin", "ЦНАП Колонщинської сільради");
        setFieldValue(driver, sBP, "sAdressBuilding", "Адреса будівлі");
        setFieldFile(driver, sBP, "fSvidoztvoNaPravoSobstvennosti", "src/test/resources/files/test.jpg");
        setFieldValue(driver, sBP, "email", email);
        setFieldValue(driver, sBP, "phone", "+380623155533");
        //setFieldValue(driver, sBP, "sID_Public_SubjectOrganJoin", "ЦНАП м. Ірпінь");

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
