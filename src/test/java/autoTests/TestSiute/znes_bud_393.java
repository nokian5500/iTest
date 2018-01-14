package autoTests.TestSiute;

import autoTests.CustomMethods;
import autoTests.pages.main.TemplatePage;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import org.junit.Test;
import org.openqa.selenium.By;

/**
 * Created by Privat24 on 09.09.2016.
 */
public class znes_bud_393 extends CustomMethods {

    //<editor-fold desc="Тестовый пример заполнение полей">
    @Test
    public void default_test() throws Exception {
        /**
         * ***************************************объявляем элементы страниц******************************************
         */
        TemplatePage o = new TemplatePage();
        //  Вносим в переменные название услуги начиная с точки ._test_fields_bankid_--_ и до начала названия поля
        String sBP = "znes_bud_393";
        String email = "autotestbeta@gmail.com";

        openURLservice("https://delta.test.igov.org.ua/service/788/general");

        // 2016-11-23 Убираем проверку на открытие нужной услуги.
        // Считаем, что мы ее правильно по прямому пути выше 
        // _step("2. Проверить, что открылась нужная услуга");
        // assertThis(driver, o.usluga, "Надання дозволу на знесення аварійних будівель");
        o.selectRegion("Київська");
        o.selectCity("Колонщина");

        o.mokAuthorization();

        setFieldAutocomplete("sID_Public_SubjectOrganJoin", "ЦНАП Колонщинської сільради");
        setFieldValue("sAdressBuilding", "Адреса будівлі");
        setFieldFile(sBP, "fSvidoztvoNaPravoSobstvennosti", "src/test/resources/files/test.jpg");
        setFieldValue("email", email);
        setFieldValue("phone", "+380623155533");
        setFieldValue("sMailClerk", email);
        //setFieldValue(driver, sBP, "sID_Public_SubjectOrganJoin", "ЦНАП м. Ірпінь");

        click(o.buttonSendingForm);

        /*o.checkMessageSuccess("Шановний(-а) MockUser MockUser!\n" +
                "Ваше звернення х-хххххххх успішно зареєстровано\n" +
                "(номер також відправлено Вам електронною поштою на Ваш e-mail "+email+") Результати будуть спрямовані також на email.\n" +
                "Звертаємо увагу, що Іноді листи потрапляють у спам або у розділ \"Реклама\" (для Gmail).");
         */
        click(o.buttonLogOut);
    }
}
