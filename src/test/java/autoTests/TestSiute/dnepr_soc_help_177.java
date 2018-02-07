package autoTests.TestSiute;

import autoTests.CustomMethods;
import autoTests.pages.main.TemplatePage;
import org.junit.Test;

/**
 * Created by Privat24 on 09.09.2016.
 */
public class dnepr_soc_help_177 extends CustomMethods {

    //<editor-fold desc="Тестовый пример заполнение полей">
    @Test
    public void default_test() throws Exception {
        /**
         * ***************************************объявляем элементы страниц******************************************
         */
        TemplatePage o = new TemplatePage();
        //  Вносим в переменные название услуги начиная с точки ._test_fields_bankid_--_ и до начала названия поля
        String sBP = "dnepr_soc_help_177";
        String email = "autotestbeta@gmail.com";

        openURLservice(getBaseUrl() + "/service/786/general");

        // 2016-11-23 Убираем проверку на открытие нужной услуги.
        // Считаем, что мы ее правильно по прямому пути выше 
        // _step("2. Проверить, что открылась нужная услуга");
        // assertThis(driver, o.usluga, "Видача довідок про отримання/неотримання усіх видів державних соціальних допомог");
        o.selectRegion("Дніпропетровська");

        o.mokAuthorization();

        setFieldAutocomplete("sID_Public_SubjectOrganJoin", "Бабушкінській район, м.Дніпропетровськ");

        setFieldValue("sAddressRegistered", "Дніпро");
        setFieldFile(sBP, "nFileScanPassport", "src/test/resources/files/testDocumentForECP.pdf");
        //setFieldValue("adress2", "Дніпро");
        setFieldValue("phone", "+380622030405");
        setFieldValue("email", "autotestbeta@gmail.com");
        setFieldSelectByText("asSubekt", "особисто");
        setFieldSelectByText("asContent", "призначення допомоги на дітей одиноким матерям");
//         setFieldValue(driver, sBP, "sMailClerk", "autotestbeta@gmail.com");

        click(o.buttonSendingForm);

        o.checkMessageSuccess("Шановний(-а) MockUser MockUser!\n"
                + "Ваше звернення х-хххххххх успішно зареєстровано\n"
                + "(номер також відправлено Вам електронною поштою на Ваш e-mail " + email + ") Результати будуть спрямовані також на email.\n"
                + "Звертаємо увагу, що Іноді листи потрапляють у спам або у розділ \"Реклама\" (для Gmail).");

        click(o.buttonLogOut);
    }
    //</editor-fold>
}
