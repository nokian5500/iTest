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
public class dnepr_dms_89s extends CustomMethods {


    //<editor-fold desc="Тестовый пример заполнение полей">
    @Test(enabled = true, groups = {"Main", "Критический функционал"}, priority = 1)
    public void default_test() throws Exception {
        /*****************************************объявляем элементы страниц*******************************************/
        TemplatePage o = new TemplatePage(driver);
        //  Вносим в переменные название услуги начиная с точки ._test_fields_bankid_--_ и до начала названия поля
        String sBP = "dnepr_dms-89s";
        String email = "autotestbeta@gmail.com";

        _step("1. Вход по прямому URL на услугу");
        openURLservice(driver, CV.baseUrl + "/service/26/general");

        _step("3. Выбор области/города");
        o.selectRegion("Дніпропетровська");
        o.selectCity("Дніпро (Дніпропетровськ)");

        _step("4. Авторизация Off AuthMock/BankID");
        o.mokAuthorization();

        _step("5. Заполняем форму услуги");
        setFieldValue(driver, sBP, "phone", "+380623155533");
        setFieldValue(driver, sBP, "email", email);
        setFieldSelectByText(driver,sBP,"asPrevName","Ні");
        setFieldValue(driver, sBP, "sDate_of_birth", "Дата народження");
        setFieldValue(driver, sBP, "sAreabirth", "Місце народження");
        setFieldValue(driver, sBP, "sNationality", "Україна");
        setFieldAutocomplete(driver,"sID_Public_SubjectOrganJoin","Соборний (Жовтневий) район");
        setFieldValue(driver, sBP, "sRegistrationAddress", "Поточна адреса реєстрації");
        setFieldValue(driver, sBP, "sNewAddress", "Нова адреса реєстрації");
        setFieldSelectByText(driver,sBP,"asMilitStatus","Ні");
        setFieldSelectSlotDate(driver, sBP, "."+sBP+"_--_"+"visitDay"); 
        setFieldSelectSlotTime(driver, sBP, "."+sBP+"_--_"+"visitDay");
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

        SetRegionFieldInputTypeEnum(driver, sBP, "asDecision", "Призначити зустріч у відділенні");
        SetRegionFieldInputTypeTextArea(driver, sBP, "sDecisionComment", "Коментар до рішення");


        clickButton(driver, sBP, "Опрацювати");
        clickButton(driver, sBP, "Підтвердити");
        clickButton(driver, sBP, "Ok");
        
        // Опрацювання [Етап II]

        setRegionFindOrder(driver, sBP);
        clickButton(driver, sBP, "Взяти в роботу");
        clickButton(driver, sBP, "Почати опрацювання задачі");

        SetRegionFieldInputTypeEnum(driver, sBP, "asResult", "Громадянин знятий з реєстрації");
        SetRegionFieldInputTypeString(driver, sBP, "sWhenCome", "Вкажіть дату зняття з реєстрації");

        clickButton(driver, sBP, "Опрацювати");
        clickButton(driver, sBP, "Підтвердити");
        clickButton(driver, sBP, "Підтвердити");
        clickButton(driver, sBP, "Ok");
      
     }
    //</editor-fold>
}
