package autoTests.TestSiute;

import autoTests.ConfigurationVariables;
import autoTests.CustomMethods;
import autoTests.pages.main.TemplatePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.selenium.factory.WebDriverFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Test_Example_Select extends CustomMethods {
    ConfigurationVariables CV = ConfigurationVariables.getInstance();
    public WebDriver driver;

    DesiredCapabilities capabilities;

    /**************************************************************/
    @BeforeTest(alwaysRun = true)
    public void SetUp() throws IOException {
        /********* Закоментить для  для запуска на своем профиле и откоментить для запуска на дефолтном ***********/
        FirefoxProfile profile = new FirefoxProfile();
        profile.setEnableNativeEvents(false);
        profile.setAcceptUntrustedCertificates(true);
        profile.setPreference("WebDriver.load.strategy", "unstable");


        /********* Раскомментить для запуска на своем профиле и закоментить для дефолтного ***********/
//        ProfilesIni allProfiles = new ProfilesIni();
//        FirefoxProfile profile = allProfiles.getProfile("default");

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
        this.driver.manage().timeouts().implicitlyWait(CV.implicitTimeWait, TimeUnit.SECONDS);
        this.driver.manage().window().maximize();
        this.driver.manage().deleteAllCookies();

    }

    @BeforeMethod(alwaysRun = true)
    public void doLogin() throws Exception {
        driver = WebDriverFactory.getDriver(capabilities);
    }


    //<editor-fold desc="Тестовый пример выпадающее листы">
     @Test(enabled = true, groups = {"Main", "Критический функционал"}, priority = 3)
    public void Test_Example_Select() throws Exception {
        /*****************************************объявляем элементы страниц*******************************************/
        TemplatePage o = new TemplatePage(driver);
        //  Вносим в переменные название услуги и почту
        String sBP = "_test_sID_UA";
        String email = "v-i-d-o-k@mail.ru";

        _step("1. Вход по прямому URL на услугу");
        openURLservice(driver, CV.baseUrl + "/service/785/general");

        _step("2. Проверить, что открылась нужная услуга");
        assertThis(driver, o.usluga, "_test_sID_UA");

        _step("3. Выбор области/города");
        o.selectRegion("Дніпропетровська");
        o.selectCity("Дніпро (Дніпропетровськ");

        _step("3. Авторизация Off AuthMock/BankID");
        o.mokAuthorization();

        _step("4. Заполняем форму услуги");
        setFieldValue(driver, sBP, "sID_Place_UA", "test");
        setFieldValue(driver, sBP, "sID_UA", "test");
        setFieldValue(driver, sBP, "email", email);
        setFieldSelectByText(driver,sBP,"client","нет");

        _step("5. Отправка формы");
        click(driver, o.buttonSendingForm);

        _step("6. Проверка сообщения о успешной отправке");
         o.checkMessageSuccess("Шановний(-а) MockUser MockUser!\n" +
                 "Ваше звернення х-хххххххх успішно зареєстровано\n" +
                 "(номер також відправлено Вам електронною поштою на Ваш e-mail "+email+") Результати будуть спрямовані також на email.\n" +
                 "Звертаємо увагу, що Іноді листи потрапляють у спам або у розділ \"Реклама\" (для Gmail).");

        _step("7. Нажать кнопку Выйти ");
        click(driver, o.buttonLogOut);
    }
    //</editor-fold>

  }
