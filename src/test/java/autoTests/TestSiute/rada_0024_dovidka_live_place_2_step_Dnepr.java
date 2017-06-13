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
public class rada_0024_dovidka_live_place_2_step_Dnepr extends CustomMethods {


    //<editor-fold desc="Тестовый пример заполнение полей">
    @Test(enabled = true, groups = {"Main", "Критический функционал"}, priority = 1)
    public void default_test() throws Exception {
        /*****************************************объявляем элементы страниц*******************************************/
        TemplatePage o = new TemplatePage(driver);
        //  Вносим в переменные название услуги начиная с точки ._test_fields_bankid_--_ и до начала названия поля
        String sBP = "rada_0024_dovidka_live_place_2_step_Dnepr";
        String email = "autotestbeta@gmail.com";

        _step("1. Вход по прямому URL на услугу");
        openURLservice(driver, CV.baseUrl + "/service/24/general");

         _step("3. Выбор области/города");
        o.selectRegion("Дніпропетровська");
        o.selectCity("Дніпро (Дніпропетровськ)");

        _step("4. Авторизация Off AuthMock/BankID");
        o.mokAuthorization();

        _step("5. Заполняем форму услуги");
        setFieldAutocomplete(driver,"sID_Public_SubjectOrganJoin","Соборний (Жовтневий) район");
        setFieldSelectByText(driver,sBP,"asEnumTypeDocument","Паспорт");
        setFieldValue(driver, sBP, "sBankIdPassport", "Документ, що посвідчує заявника");
        setFieldFile(driver, sBP, "nFile_CopyPassportZ", "src/test/resources/files/test.jpg");
        setFieldValue(driver, sBP, "phone", "+380623155533");
        setFieldValue(driver, sBP, "email", email);
        setFieldSelectByText(driver,sBP,"asForMySelf","Так, для себе");
        setFieldSelectByText(driver,sBP,"asTargetGetDovidka","Вперше оформлення паспорта (ID-картки)");
        setFieldFile(driver, sBP, "nFile_SvidoztvoNarodzennya", "src/test/resources/files/test.jpg");
        
        setFieldValue(driver, sBP, "sDate_of_birth", "Дата народження");
        setFieldValue(driver, sBP, "sAreabirth", "Місце народження");
        setFieldValue(driver, sBP, "sRegistrationAddress", "Адреса реєстрації");
        setFieldValue(driver, sBP, "sNationality", "Україна");
        setFieldValue(driver, sBP, "sMailClerk", email);

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

        SetRegionFieldInputTypeEnum(driver, sBP, "asDecision", "Дані громадянина вірні, починаємо формування довідки");
        SetRegionFieldInputTypeTextArea(driver, sBP, "sDecisionComment", "Коментар до рішення");


        clickButton(driver, sBP, "Опрацювати");
        clickButton(driver, sBP, "Підтвердити");
        clickButton(driver, sBP, "Ok");
        
        // Опрацювання [Етап II]

        setRegionFindOrder(driver, sBP);
        clickButton(driver, sBP, "Взяти в роботу");
        clickButton(driver, sBP, "Почати опрацювання задачі");

        SetRegionFieldInputTypeEnum(driver, sBP, "asDecision2", "Довідка виготовлена, призначаємо зустріч у відділенні");

        clickButton(driver, sBP, "Опрацювати");
        clickButton(driver, sBP, "Підтвердити");
        clickButton(driver, sBP, "Ok");
      
     }
    //</editor-fold>
}
