package autoTests.TestSiute;

import autoTests.CustomMethods;
import autoTests.pages.main.TemplatePage;
import org.junit.Test;

/**
 * Created by Privat24 on 09.09.2016.
 */
public class dnepr_cnap_39 extends CustomMethods {

    //<editor-fold desc="Тестовый пример заполнение полей">
    @Test
    public void default_test() throws Exception {
        /**
         * ***************************************объявляем элементы страниц******************************************
         */
        TemplatePage o = new TemplatePage();
        //  Вносим в переменные название услуги начиная с точки ._test_fields_bankid_--_ и до начала названия поля
        String sBP = "dnepr_cnap_39";
        String email = "autotestbeta@gmail.com";

        openURLservice(getBaseUrl() + "/service/139/general");

        o.selectRegion("Київська");
        o.selectCity("Ірпінь");

        o.mokAuthorization();

        setFieldAutocomplete("sID_Public_SubjectOrganJoin", "ЦНАП м. Ірпінь");
        setFieldValue("phone", "+380623155533");
        setFieldValue("email", email);
        setFieldValue("sObjName", "номер, дату та назву рішення ради");
        setFieldValue("sObjAdress", "Місцезнаходження (адреса) об’єкта");
        setFieldValue("sDavName", "повне найменування юридичної особи");
        setFieldValue("kved", "11.11");
        setFieldValue("edrpou_inn", "12345678");
        setFieldValue("sRukov", "П.І.Б. керівника юридичної особи");
        setFieldValue("sOrgAdress", "Місцезнаходження юридичної особи");
        setFieldValue("sMailClerk", email);
        //setFieldValue(driver, sBP, "sID_Public_SubjectOrganJoin", "ЦНАП м. Ірпінь");

        click(o.buttonSendingForm);

        o.checkMessageSuccess("Шановний(-а) MockUser MockUser!\n"
                + "Ваше звернення х-хххххххх успішно зареєстровано\n"
                + "(номер також відправлено Вам електронною поштою на Ваш e-mail " + email + ") Результати будуть спрямовані також на email.\n"
                + "Звертаємо увагу, що Іноді листи потрапляють у спам або у розділ \"Реклама\" (для Gmail).");

        click(o.buttonLogOut);
    }
    //</editor-fold>
}
