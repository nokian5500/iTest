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
public class Test_Example_Fill_Field  extends CustomMethods {

    ConfigurationVariables CV = ConfigurationVariables.getInstance();
    public WebDriver driver;
    static ConfigurationVariables configVariables = ConfigurationVariables.getInstance();
    DesiredCapabilities capabilities;

    @BeforeTest(alwaysRun = true)
    public void SetUp() throws IOException {

        /********* Закоментить для  для запуска на своем профиле и откоментить для запуска на дефолтном ***********/
        /*FirefoxProfile profile = new FirefoxProfile();
       profile.setEnableNativeEvents(false);
        profile.setAcceptUntrustedCertificates(true);
*/
        /********* Раскомментить для запуска на своем профиле и закоментить для дефолтного ***********/
        ProfilesIni allProfiles = new ProfilesIni();
        FirefoxProfile profile = allProfiles.getProfile("default");

        profile.setEnableNativeEvents(false);
        profile.setAcceptUntrustedCertificates(true);
        profile.setAssumeUntrustedCertificateIssuer(true);
        profile.setPreference("javascript.enabled", true);
        profile.setPreference("geo.enabled", false);

        capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability(FirefoxDriver.PROFILE, profile);
        capabilities.setCapability("unexpectedAlertBehaviour", "ignore");

        System.out.println("Tests will be run (or rerun) in Firefox with custom profile...");


        driver = WebDriverFactory.getDriver(capabilities);
        this.driver.manage().timeouts().implicitlyWait(configVariables.implicitTimeWait, TimeUnit.SECONDS);
        this.driver.manage().window().maximize();
        this.driver.manage().deleteAllCookies();

    }

    @BeforeMethod(alwaysRun = true)
    public void doLogin() throws Exception {
        driver = WebDriverFactory.getDriver(capabilities);
    }

    //<editor-fold desc="Тестовый пример заполнение полей">
    @Test(enabled = true, groups = {"Main", "Критический функционал"}, priority = 1)
    public void Test_Example_Fill_Field() throws Exception {
        /*****************************************объявляем элементы страниц*******************************************/
        TemplatePage tp = new TemplatePage(driver);
        //  Вносим в переменные название услуги начиная с точки ._test_fields_bankid_--_ и до начала названия поля
        String sn = "._test_fields_bankid_--_";

        addStepToTheReport("1. Вход по прямому URL на услугу");
        openURLservice(driver, CV.baseUrl + "/service/720/general");

        addStepToTheReport("2. Проверить, что открылась нужная услуга");
        assertThis(driver, tp.usluga, "_test_fields_bankid");

        addStepToTheReport("3. Выбор области/города");
        tp.selectRegion("Дніпропетровська");
        tp.selectCity("Дніпро (Дніпропетровськ");

        addStepToTheReport("4. Авторизация Off AuthMock/BankID");
        tp.mokAuthorization();

        addStepToTheReport("5. Заполняем форму услуги");
        fillInField(driver, sn, "tooltiptext", "test");
        fillInField(driver, sn, "email", "v-i-d-k@mail.ru");

        addStepToTheReport("6. Отправка формы");
        click(driver, tp.buttonSendingForm);

        addStepToTheReport("7. Проверка сообщения о успешной отправке");
        tp.checkMessageSuccess("Шановний(-а) MockUser MockUser!\n" +
                "Ваше звернення х-хххххххх успішно зареєстровано\n" +
                "(номер також відправлено Вам електронною поштою на Ваш e-mail v-i-d-k@mail.ru) Результати будуть спрямовані також на email.\n" +
                "Звертаємо увагу, що Іноді листи потрапляють у спам або у розділ \"Реклама\" (для Gmail).");

        addStepToTheReport("8. Нажать кнопку Выйти");
        click(driver, tp.buttonLogOut);
    }
    //</editor-fold>
}
