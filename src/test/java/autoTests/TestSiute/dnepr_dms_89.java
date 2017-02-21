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
public class dnepr_dms_89 extends CustomMethods {


    //<editor-fold desc="Тестовый пример заполнение полей">
    @Test(enabled = true, groups = {"Main", "Критический функционал"}, priority = 1)
    public void default_test() throws Exception {
        /*****************************************объявляем элементы страниц*******************************************/
        TemplatePage o = new TemplatePage(driver);
        //  Вносим в переменные название услуги начиная с точки ._test_fields_bankid_--_ и до начала названия поля
        String sBP = "dnepr_dms-89";
        String email = "autotestbeta@gmail.com";

        _step("1. Вход по прямому URL на услугу");
        openURLservice(driver, CV.baseUrl + "/service/29/general");

        // 2016-11-23 Убираем проверку на открытие нужной услуги.
        // Считаем, что мы ее правильно по прямому пути выше 
        // _step("2. Проверить, что открылась нужная услуга");
        // assertThis(driver, o.usluga, "Копії рішень міської (селищної) ради про надання дозволу на розроблення проекту відведення земельної ділянки");

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
        setFieldValue(driver, sBP, "sRegistrationAddress", "Стара адреса реєстрації");
        setFieldAutocomplete(driver,"sID_Public_SubjectOrganJoin","Соборний (Жовтневий) район");
        setFieldValue(driver, sBP, "sNewAddress", "Нова адреса реєстрації");
        setFieldSelectByText(driver,sBP,"asRegistrationType","до власного житлового приміщення");
        setFieldSelectByText(driver,sBP,"asRegistrationDoc","Ордер");
        setFieldValue(driver, sBP, "sDocRekv", "Опис документу");        
        setFieldSelectByText(driver,sBP,"asMilitStatus","Ні");
        setFieldSelectByText(driver,sBP,"asRegistr","Ні");
        setFieldValue(driver, sBP, "sWhenCome", "2016-11-28");   
        setFieldSelectSlotDate(driver, sBP, "."+sBP+"_--_"+"visitDay"); 
        setFieldSelectSlotTime(driver, sBP, "."+sBP+"_--_"+"visitDay");
//        setFieldSelectSlotDate(driver, sBP, "visitDay"); 
//        setFieldSelectSlotTime(driver, sBP, "visitDay");
//        pause(1000000); 
        setFieldFile(driver, sBP, "nOrder", "src/test/resources/files/test.jpg");
        setFieldFile(driver, sBP, "nForma3", "src/test/resources/files/test.jpg");
        setFieldValue(driver, sBP, "sMailClerk", email);

        _step("6. Отправка формы");
        click(driver, o.buttonSendingForm);

        _step("7. Проверка сообщения о успешной отправке");
        o.checkMessageSuccess("Шановний(-а) MockUser MockUser!\n" +
                "Ваше звернення х-хххххххх успішно зареєстровано\n" +
                "(номер також відправлено Вам електронною поштою на Ваш e-mail "+email+") Результати будуть спрямовані також на email.\n" +
                "Звертаємо увагу, що Іноді листи потрапляють у спам або у розділ \"Реклама\" (для Gmail).");

        _step("8. Нажать кнопку Выйти");
        click(driver, o.buttonLogOut);
    }
    //</editor-fold>
}
