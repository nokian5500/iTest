package autoTests.TestSiute.iGov;

import autoTests.CustomMethods;
import autoTests.pages.main.TemplatePage;
import org.junit.Test;

/**
 * Created by Privat24 on 09.09.2016.
 */
public class subsidiesUkraine_ECP_2 extends CustomMethods {

    //<editor-fold desc="Тестовый пример заполнение полей">
    @Test
    public void default_test() throws Exception {
        /**
         * ***************************************объявляем элементы страниц******************************************
         */
        TemplatePage o = new TemplatePage();
        //  Вносим в переменные название услуги начиная с точки ._test_fields_bankid_--_ и до начала названия поля
        String sBP = "subsidiesUkraine_ECP";
        String email = "autotestbeta@gmail.com";

        openURLservice(getBaseUrl() + "/service/654/general");

        o.selectRegion("Дніпропетровська");

        o.testPrivat24Authorization();

        setFieldAutocomplete("sID_Public_SubjectOrganJoin", "Бабушкінській район, м.Дніпропетровськ");
        setFieldValue("bankIdbirthDay", "01.01.1985");
        setFieldFile(sBP, "ScanPassport", "src/test/resources/files/test.jpg");
        setFieldValue("place_of_living", "Місце реєстрації");
        setFieldValue("phone", "+380623155533");
        setFieldValue("email", email);
        setFieldSelectByText("SubsNumber", "Ні, це перше звернення");
        setFieldSelectByText("subsidy", "Оплата житлово-комунальних послуг");
        setFieldFile(sBP, "rent", "src/test/resources/files/test.jpg");
        setFieldSelectByText("electricity", "Користуюсь");
        setFieldValue("electricity_number1", "Номер особового рахунку");
        setFieldValue("electricity_name", "Назва організації, що надає послугу");
        setFieldValue("electricity_notice", "Примітки");
        setFieldSelectByText("house", "Користуюсь");
        setFieldValue("house_number1", "Номер особового рахунку");
        setFieldValue("house_name", "Назва організації, що надає послугу");
        setFieldValue("house_notice", "Примітки");
        setFieldSelectByText("osbb", "Не користуюсь");
        setFieldSelectByText("gas", "Користуюсь");
        setFieldValue("gas_number1", "Номер особового рахунку");
        setFieldValue("gas_name", "Назва організації, що надає послугу");
        setFieldValue("gas_notice", "Примітки");
        setFieldSelectByText("coolwater", "Користуюсь");
        setFieldValue("coolwater_number1", "Номер особового рахунку");
        setFieldValue("coolwater_name", "Назва організації, що надає послугу");
        setFieldValue("coolwater_notice", "Примітки");
        setFieldSelectByText("hotwater", "Не користуюсь");
        setFieldSelectByText("waterback", "Користуюсь");
        setFieldValue("waterback_number1", "Номер особового рахунку");
        setFieldValue("waterback_name", "Назва організації, що надає послугу");
        setFieldValue("waterback_notice", "Примітки");
        setFieldSelectByText("warming", "Користуюсь");
        setFieldValue("warming_number1", "Номер особового рахунку");
        setFieldValue("warming_name", "Назва організації, що надає послугу");
        setFieldValue("warming_notice", "Примітки");
        setFieldSelectByText("garbage", "Користуюсь");
        setFieldValue("garbage_number1", "Номер особового рахунку");
        setFieldValue("garbage_name", "Назва організації, що надає послугу");
        setFieldValue("garbage_notice", "Примітки");
        setFieldSelectByText("place_type", "багатоквартирний будинок");
        setFieldValue("floors", "2");
        setFieldValue("total_place", "10000000");
        setFieldValue("warming_place", "Опалювальна площа (кв. м.)");
        setFieldValue("income0", "Ваш вид доходу");
        setFieldFile(sBP, "declaration0", "src/test/resources/files/test.jpg");
        setFieldSelectByText("other_people", "Ні");
        setFieldSelectByText("overload", "Ні");
        setFieldValue("org0", "Назва організації, де Ви отримуєте дохід");
//        setFieldSelectByTextNew1(driver,sBP,"ECP","з ЕЦП (для отримання електронної послуги про призначення субсидії)");
        setFieldSelectByText("ECP", "з ЕЦП (для отримання електронної послуги про призначення субсидії)");

//        _step("6. Отправка формы");
        // click(driver, o.buttonSendingForm);
        clickButtonECP();
        uploadECPKeyFile("src/test/resources/files/Key-6.dat");
        //uploadECPKeyAutoIT();
        setPaswordForECPKey();
        pause(10000);

        o.checkMessageSuccess("Шановний(-а) ВОЛОДИМИР ВОЛОДИМИРОВИЧ!\n"
                + "Ваше звернення х-хххххххх успішно зареєстровано\n"
                + "(номер також відправлено Вам електронною поштою на Ваш e-mail " + email + ") Результати будуть спрямовані також на email.\n"
                + "Звертаємо увагу, що Іноді листи потрапляють у спам або у розділ \"Реклама\" (для Gmail).");

        click(o.buttonLogOut);
//        pause(5000);

        openURLdashboard(getRegionUrl());

        AuthorizationBySetLoginPassword("tester", "tester");
        clickButton("Увійти");

        // Опрацювання в табі "В необроблені"    
        setRegionTask();
        clickButton("Взяти в роботу");
        clickButton("Почати опрацювання задачі");

        // Опрацювання [Етап I] 
        SetRegionFieldInputTypeEnum("запросити громадянина до підписання (якщо заявка без ЕЦП)");
        SetRegionFieldInputTypeTextArea("comment", "Коментар до рішення");

        clickButton("Опрацювати");
        clickButton("Підтвердити");
        clickButton("Ok");

        // Опрацювання [Етап II]
        setRegionFindOrder(sBP);
        clickButton("Взяти в роботу");
        clickButton("Почати опрацювання задачі");

        SetRegionFieldInputTypeFile("src/test/resources/files/test.jpg");
        SetRegionFieldInputTypeEnum("субсидія погоджена");
        SetRegionFieldInputTypeTextArea("comment1", "Примітки");

        clickButton("Опрацювати");
        clickButton("Підтвердити");
//        clickButton(driver, sBP, "Ok");

    }
    //</editor-fold>
}
