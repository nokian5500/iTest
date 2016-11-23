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

/**
 * Created by Privat24 on 09.09.2016.
 */
public class dvs_732 extends CustomMethods {

    ConfigurationVariables CV = ConfigurationVariables.getInstance();
    public WebDriver driver;


     //<editor-fold desc="Тестовый пример заполнение полей">
    @Test(enabled = true, groups = {"Main", "Критический функционал"}, priority = 1)
    public void default_test() throws Exception {
        /*****************************************объявляем элементы страниц*******************************************/
        TemplatePage o = new TemplatePage(driver);
        //  Вносим в переменные название услуги начиная с точки ._test_fields_bankid_--_ и до начала названия поля
        String sBP = "dvs_732";
        String email = "autotestbeta@gmail.com";

        _step("1. Вход по прямому URL на услугу");
        openURLservice(driver, CV.baseUrl + "/service/112/general");

        // 2016-11-23 Убираем проверку на открытие нужной услуги.
        // Считаем, что мы ее правильно по прямому пути выше 
        // _step("2. Проверить, что открылась нужная услуга");
        // assertThis(driver, o.usluga, "Видача розрахунку заборгованості зі сплати аліментів");

        _step("3. Выбор области/города");
        o.selectRegion("Дніпропетровська");
        
        _step("4. Авторизация Off AuthMock/BankID");
        o.mokAuthorization();

        _step("5. Заполняем форму услуги");
        setFieldAutocomplete(driver,"sID_Public_SubjectOrganJoin","Васильківський районний відділ ДВС ГТУЮ у Дніпропетровській обл.");
        setFieldValue(driver, sBP, "phone", "+380999999999");
        setFieldValue(driver, sBP, "email", email);
        setFieldValue(driver, sBP, "sDebtorLastname", "Прізвище боржника");
        setFieldValue(driver, sBP, "sDebtorFirstname", "Ім'я боржника");
        setFieldValue(driver, sBP, "sDebtorMiddlename", "По батькові боржника");
        setFieldSelectByText(driver, sBP, "anAmount", "1");
        setFieldValue(driver, sBP, "sChildName1", "ПІБ дитини (1)");
        setFieldValue(driver, sBP, "sChildBirth1", "1/01/2010");
        setFieldValue(driver, sBP, "sDocName", "Назва документа");
        setFieldValue(driver, sBP, "sDocNumber", "№1 від 01/01/2011");
        setFieldValue(driver, sBP, "sDocOrgan", "Найменування органу");
        setFieldCalendar(driver, sBP, "dStartDate", "2016/12/25");
        

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
