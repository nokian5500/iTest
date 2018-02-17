package autoTests.TestSiute.iGov;

import autoTests.CustomMethods;
import autoTests.pages.main.TemplatePage;
import org.junit.Test;

/**
 * Created by Privat24 on 09.09.2016.
 */
public class rada_0676_citizensAppeals extends CustomMethods {

    //<editor-fold desc="Тестовый пример заполнение полей">
    @Test
    public void default_test() throws Exception {
        /**
         * ***************************************объявляем элементы страниц******************************************
         */
        TemplatePage o = new TemplatePage();
        //  Вносим в переменные название услуги начиная с точки ._test_fields_bankid_--_ и до начала названия поля
        String sBP = "rada_0676_citizensAppeals";
        String email = "autotestbeta@gmail.com";

        openURLservice(getBaseUrl() + "/service/676/general");

        o.selectRegion("Дніпропетровська");
        o.selectCity("Дніпро (Дніпропетровськ)");

        o.mokAuthorization();

        setFieldValue("sPhone", "+380623155533");
        setFieldValue("email", email);
        setFieldSelectByText("asDistrict", "Соборний (Жовтневий)");
        setFieldValue("sAdress", "Адреса проживання заявника");
        setFieldSelectByText("asHowCategory", "Учасник війни");
        setFieldSelectByText("asKindTr", "Індивідуальне");
        setFieldSelectByText("asHowSocStatus", "Робітник");
        setFieldSelectByText("asTopHead", "Благоустрій та інфраструктура");
        setFieldSelectByText("asTop4", "Видалення карантинних рослин за межами прибудинкової території");
        setFieldValue("sReason", "Опишіть суть звернення");
        setFieldSelectByText("asAnswerChannel", "Відповідь на e-mail");

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
        SetRegionFieldInputTypeEnum("ЗАЯВА");
        SetRegionFieldInputTypeEnum("Портал iGov.org.ua");
        SetRegionFieldInputTypeTextArea("sDocument_Body_UkrDoc", "Відповідь на звернення");
        SetRegionFieldInputTypeEnum("Департамент економіки, фінансів та міського бюджету");
        SetRegionFieldInputTypeEnum("Департамент економіки, фінансів та міського бюджету");
        SetRegionFieldInputTypeEnum("Звернення зареєстровано. Повідомити заявника");
        SetRegionFieldInputTypeTextArea("sAnswerExplanation", "Коментар колл-центру");

        clickButton("Опрацювати");
        clickButton("Підтвердити");
        clickButton("Ok");

    }
    //</editor-fold>
}
