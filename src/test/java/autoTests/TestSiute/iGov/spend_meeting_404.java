package autoTests.TestSiute.iGov;

import autoTests.CustomMethods;
import autoTests.pages.main.TemplatePage;
import org.junit.Test;

/**
 * Created by Privat24 on 09.09.2016.
 */
public class spend_meeting_404 extends CustomMethods {

    //<editor-fold desc="Тестовый пример заполнение полей">
    @Test
    public void default_test() throws Exception {
        /**
         * ***************************************объявляем элементы страниц******************************************
         */
        TemplatePage o = new TemplatePage();
        //  Вносим в переменные название услуги начиная с точки ._test_fields_bankid_--_ и до начала названия поля
        String sBP = "spend_meeting_404";
        String email = "autotestbeta@gmail.com";

        openURLservice(getBaseUrl() + "/service/40/general");

        // 2016-11-23 Убираем проверку на открытие нужной услуги.
        // Считаем, что мы ее правильно по прямому пути выше 
        // _step("2. Проверить, что открылась нужная услуга");
        // assertThis(driver, o.usluga, "Повідомлення про проведення зборів, мітингів, маніфестацій і демонстрацій, спортивних, видовищних та інших масових заходів");
        o.selectRegion("Київська");
        o.selectCity("Колонщина");

        o.mokAuthorization();

        setFieldCalendar(sBP, "dDate_Beg", "2003/01/01");
        setFieldAutocomplete("sID_Public_SubjectOrganJoin", "ЦНАП Колонщинської сільради");
        setFieldValue("phone", "+380623155533");
        setFieldValue("email", email);
        setFieldValue("sMetaZahodu", "Мета заходу");
        setFieldSelectByText("eFormaZahodu", "мітинг");
        setFieldValue("sMistoZahodu", "Місце проведення заходу або маршрути руху");
        setFieldFile(sBP, "fMapViewMarshrut", "src/test/resources/files/test.jpg");

        setFieldSelectByText("eTime_Beg", "12:00");
        setFieldCalendar(sBP, "dDate_End", "2003/01/01");
        setFieldSelectByText("eTime_End", "19:00");
        setFieldValue("lCountPeople", "100");
        setFieldFile(sBP, "fPovidomlennyaProZahid", "src/test/resources/files/test.jpg");
        setFieldSelectByText("eCountOrganizatorov", "1");
        setFieldValue("lastName_UA1", "Прізвище");
        setFieldValue("firstName_UA1", "Ім'я");
        setFieldValue("middleName_UA1", "По батькові");
        setFieldValue("sAdress_1", "Адреса реєстрації відповідальної особи 1");
        setFieldValue("sphone_1", "Контактний (особистий) номер");
        setFieldValue("sPlace_of_Work_1", "Місце роботи (навчання) відповідальної особи 1");
        setFieldSelectByText("isRepresentCompany_1", "Ні");
        setFieldValue("sMailClerk", email);
//        pause(10000);

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
        SetRegionFieldInputTypeEnum("прийняти до розгляду повідомлення");
        SetRegionFieldInputTypeTextArea("comment_1", "коментар 1");

        clickButton("Опрацювати");
        clickButton("Підтвердити");
        clickButton("Ok");

        // Опрацювання [Етап II]
        setRegionFindOrder(email);
        clickButton("Взяти в роботу");
        clickButton("Почати опрацювання задачі");

        SetRegionFieldInputTypeEnum("повідомлення прийнято до відома");
        SetRegionFieldInputTypeTextArea("comment_2", "коментар 2");

        clickButton("Опрацювати");
        clickButton("Підтвердити");
        clickButton("Ok");
    }

    //</editor-fold>
}
