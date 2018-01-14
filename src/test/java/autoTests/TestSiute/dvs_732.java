package autoTests.TestSiute;

import autoTests.CustomMethods;
import autoTests.pages.main.TemplatePage;
import org.junit.Test;

/**
 * Created by Privat24 on 09.09.2016.
 */
public class dvs_732 extends CustomMethods {

    //<editor-fold desc="Тестовый пример заполнение полей">
    @Test
    public void default_test() throws Exception {
        /**
         * ***************************************объявляем элементы
         * страниц******************************************
         */
        TemplatePage o = new TemplatePage();
        //  Вносим в переменные название услуги начиная с точки ._test_fields_bankid_--_ и до начала названия поля
        String sBP = "dvs_732";
        String email = "autotestbeta@gmail.com";

        openURLservice(getBaseUrl() + "/service/112/general");

        // 2016-11-23 Убираем проверку на открытие нужной услуги.
        // Считаем, что мы ее правильно по прямому пути выше 
        // _step("2. Проверить, что открылась нужная услуга");
        // assertThis(driver, o.usluga, "Видача розрахунку заборгованості зі сплати аліментів");
        o.selectRegion("Дніпропетровська");

        o.mokAuthorization();

        setFieldAutocomplete("sID_Public_SubjectOrganJoin", "Васильківський районний відділ ДВС ГТУЮ у Дніпропетровській обл.");
        setFieldValue("phone", "+380999999999");
        setFieldValue("email", email);
        setFieldValue("sDebtorLastname", "Прізвище боржника");
        setFieldValue("sDebtorFirstname", "Ім'я боржника");
        setFieldValue("sDebtorMiddlename", "По батькові боржника");
        setFieldSelectByText("anAmount", "1");
        setFieldValue("sChildName1", "ПІБ дитини (1)");
        setFieldValue("sChildBirth1", "1/01/2010");
        setFieldValue("sDocName", "Назва документа");
        setFieldValue("sDocNumber", "№1 від 01/01/2011");
        setFieldValue("sDocOrgan", "Найменування органу");
        setFieldCalendar(sBP, "dStartDate", "2016/12/25");

        click(o.buttonSendingForm);

        o.checkMessageSuccess("Шановний(-а) MockUser MockUser!\n"
                + "Ваше звернення х-хххххххх успішно зареєстровано\n"
                + "(номер також відправлено Вам електронною поштою на Ваш e-mail " + email + ") Результати будуть спрямовані також на email.\n"
                + "Звертаємо увагу, що Іноді листи потрапляють у спам або у розділ \"Реклама\" (для Gmail).");

        click(o.buttonLogOut);
    }
    //</editor-fold>
}
