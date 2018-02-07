package autoTests.TestSiute;

import autoTests.ConfigClass;
import autoTests.CustomMethods;
import autoTests.pages.main.TemplatePage;
import org.junit.Test;

/**
 * Created by Privat24 on 09.09.2016.
 */
public class common_mreo_2 extends CustomMethods {

    //<editor-fold desc="Тестовый пример заполнение полей">
    @Test
    public void default_test() throws Exception {
        /**
         * ***************************************объявляем элементы страниц******************************************
         */
        TemplatePage o = new TemplatePage();
        //  Вносим в переменные название услуги начиная с точки ._test_fields_bankid_--_ и до начала названия поля
        String sBP = "common_mreo_2";
        String email = "autotestbeta@gmail.com";

        openURLservice(getBaseUrl() + "/service/1397/general");

        // _step("3. Выбор области/города");
        o.mokAuthorization();

        setFieldValue("bankIdbirthDay", "27.05.1985");
        setFieldValue("phone", "380623155533");
        setFieldValue("email", email);
        setFieldSelectByText("reqType", "інформація про володіння транспортнними засобами фізичною особою");
        setFieldSelectByText("reqPhis", "пошук за індивідуальним податковим номером");
        setFieldValue("ownerInn", "2731300834");

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
        SetRegionFieldInputTypeString("ownerFirstName", "Ім'я власника");
        SetRegionFieldInputTypeString("ownerLastName", "Прізвище власника");
        SetRegionFieldInputTypeString("ownerMiddleName", "По батькові власника");

        clickButton("Опрацювати");
        clickButton("Підтвердити");

        //pause(90000);
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //clickButton("Ok");

    }
    //</editor-fold>
}
