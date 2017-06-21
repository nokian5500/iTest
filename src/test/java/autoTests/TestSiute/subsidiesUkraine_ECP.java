package autoTests.TestSiute;

import autoTests.ConfigurationVariables;
import autoTests.CustomMethods;
import autoTests.pages.main.TemplatePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.selenium.factory.WebDriverFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.tools.ant.taskdefs.Sleep;

/**
 * Created by Privat24 on 09.09.2016.
 */
public class subsidiesUkraine_ECP extends CustomMethods {


    //<editor-fold desc="Тестовый пример заполнение полей">
    @Test(enabled = true, groups = {"Main", "Критический функционал"}, priority = 1)
    public void default_test() throws Exception {
        /*****************************************объявляем элементы страниц*******************************************/
        TemplatePage o = new TemplatePage(driver);
        //  Вносим в переменные название услуги начиная с точки ._test_fields_bankid_--_ и до начала названия поля
        String sBP = "subsidiesUkraine_ECP";
        String email = "autotestbeta@gmail.com";

        _step("1. Вход по прямому URL на услугу");
        openURLservice(driver, CV.baseUrl + "/service/654/general");

        _step("3. Выбор области/города");
        o.selectRegion("Дніпропетровська");

        _step("4. Авторизация Off AuthMock/BankID");
        o.mokAuthorization();

        _step("5. Заполняем форму услуги");
        setFieldAutocomplete(driver,"sID_Public_SubjectOrganJoin","Бабушкінській район, м.Дніпропетровськ");
        setFieldValue(driver, sBP, "bankIdbirthDay", "01.01.1985");
        setFieldFile(driver, sBP, "ScanPassport", "src/test/resources/files/test.jpg");
        setFieldValue(driver, sBP, "place_of_living", "Місце реєстрації");
        setFieldValue(driver, sBP, "phone", "+380623155533");
        setFieldValue(driver, sBP, "email", email);
        setFieldSelectByText(driver,sBP,"SubsNumber","Ні, це перше звернення");
        setFieldSelectByText(driver,sBP,"subsidy","Оплата житлово-комунальних послуг");
        setFieldFile(driver, sBP, "rent", "src/test/resources/files/test.jpg");
        setFieldSelectByText(driver,sBP,"electricity","Користуюсь");
        setFieldValue(driver, sBP, "electricity_number1", "Номер особового рахунку");
        setFieldValue(driver, sBP, "electricity_name", "Назва організації, що надає послугу");
        setFieldValue(driver, sBP, "electricity_notice", "Примітки");
        setFieldSelectByText(driver,sBP,"house","Користуюсь");
        setFieldValue(driver, sBP, "house_number1", "Номер особового рахунку");
        setFieldValue(driver, sBP, "house_name", "Назва організації, що надає послугу");
        setFieldValue(driver, sBP, "house_notice", "Примітки");
        setFieldSelectByText(driver,sBP,"osbb","Не користуюсь");
        setFieldSelectByText(driver,sBP,"gas","Користуюсь");
        setFieldValue(driver, sBP, "gas_number1", "Номер особового рахунку");
        setFieldValue(driver, sBP, "gas_name", "Назва організації, що надає послугу");
        setFieldValue(driver, sBP, "gas_notice", "Примітки");
        setFieldSelectByText(driver,sBP,"coolwater","Користуюсь");
        setFieldValue(driver, sBP, "coolwater_number1", "Номер особового рахунку");
        setFieldValue(driver, sBP, "coolwater_name", "Назва організації, що надає послугу");
        setFieldValue(driver, sBP, "coolwater_notice", "Примітки");
        setFieldSelectByText(driver,sBP,"hotwater","Не користуюсь");
        setFieldSelectByText(driver,sBP,"waterback","Користуюсь");
        setFieldValue(driver, sBP, "waterback_number1", "Номер особового рахунку");
        setFieldValue(driver, sBP, "waterback_name", "Назва організації, що надає послугу");
        setFieldValue(driver, sBP, "waterback_notice", "Примітки");
        setFieldSelectByText(driver,sBP,"warming","Користуюсь");
        setFieldValue(driver, sBP, "warming_number1", "Номер особового рахунку");
        setFieldValue(driver, sBP, "warming_name", "Назва організації, що надає послугу");
        setFieldValue(driver, sBP, "warming_notice", "Примітки");
        setFieldSelectByText(driver,sBP,"garbage","Користуюсь");
        setFieldValue(driver, sBP, "garbage_number1", "Номер особового рахунку");
        setFieldValue(driver, sBP, "garbage_name", "Назва організації, що надає послугу");
        setFieldValue(driver, sBP, "garbage_notice", "Примітки");
        setFieldSelectByText(driver,sBP,"place_type","багатоквартирний будинок");
        setFieldValue(driver, sBP, "floors", "Кількість поверхів");
        setFieldValue(driver, sBP, "total_place", "Загальна площа");
        setFieldValue(driver, sBP, "warming_place", "Опалювальна площа (кв. м.)");
        setFieldValue(driver, sBP, "income0", "Ваш вид доходу");
        setFieldFile(driver, sBP, "declaration0", "src/test/resources/files/test.jpg");
        setFieldSelectByText(driver,sBP,"other_people","Ні");
        setFieldSelectByText(driver,sBP,"overload","Ні");
        setFieldSelectByText(driver,sBP,"ECP","без ЕЦП (погоджуєтесь на особистий візит після перевірки правильності даних)");
        setFieldValue(driver, sBP, "org0", "Назва організації, де Ви отримуєте дохід");

        _step("6. Отправка формы");
        click(driver, o.buttonSendingForm);

        _step("7. Проверка сообщения о успешной отправке");
        o.checkMessageSuccess("Шановний(-а) MockUser MockUser!\n" +
                "Ваше звернення х-хххххххх успішно зареєстровано\n" +
                "(номер також відправлено Вам електронною поштою на Ваш e-mail "+email+") Результати будуть спрямовані також на email.\n" +
                "Звертаємо увагу, що Іноді листи потрапляють у спам або у розділ \"Реклама\" (для Gmail).");

        _step("7-1. Нажать кнопку Выйти");
        click(driver, o.buttonLogOut);

        _step("8. Вход по прямому URL на дашборд");
        openURLdashboard(driver, sBP);

        _step("9. Авторизация login/BankID на дашборде. login/pass: (tester/tester)");
        AuthorizationBySetLoginPassword(driver, sBP, "tester", "tester");
        clickButton(driver, sBP, "Увійти");

        // Опрацювання в табі "В необроблені"    

        setRegionTask(driver, sBP);
        clickButton(driver, sBP, "Взяти в роботу");
        clickButton(driver, sBP, "Почати опрацювання задачі");
        
        // Опрацювання [Етап I] 

        SetRegionFieldInputTypeEnum(driver, sBP, "decide", "запросити громадянина до підписання (якщо заявка без ЕЦП)");
        SetRegionFieldInputTypeTextArea(driver, sBP, "comment", "Коментар до рішення");


        clickButton(driver, sBP, "Опрацювати");
        clickButton(driver, sBP, "Підтвердити");
        clickButton(driver, sBP, "Ok");
        
        // Опрацювання [Етап II]

        setRegionFindOrder(driver, sBP);
        clickButton(driver, sBP, "Взяти в роботу");
        clickButton(driver, sBP, "Почати опрацювання задачі");

        SetRegionFieldInputTypeFile(driver, sBP, "decision", "src/test/resources/files/test.jpg");
        SetRegionFieldInputTypeEnum(driver, sBP, "selection", "субсидія погоджена");
        SetRegionFieldInputTypeTextArea(driver, sBP, "comment1", "Примітки");

        clickButton(driver, sBP, "Опрацювати");
        clickButton(driver, sBP, "Підтвердити");
        clickButton(driver, sBP, "Ok");
      
     }
    //</editor-fold>
}
