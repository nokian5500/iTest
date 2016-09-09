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
public class Test_Example_Attach  extends CustomMethods {

    ConfigurationVariables CV = ConfigurationVariables.getInstance();
    public WebDriver driver;
    static ConfigurationVariables configVariables = ConfigurationVariables.getInstance();
    DesiredCapabilities capabilities;

    /**************************************************************/
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

    //<editor-fold desc="Тестовый пример загрузки файла">
    @Test(enabled = true, groups = {"Main", "Критический функционал"}, priority = 2)
    public void Test_Example_Attach() throws Exception {
        /*****************************************объявляем элементы страниц*******************************************/
        TemplatePage o = new TemplatePage(driver);
        //  Вносим в переменные название услуги начиная с точки ._test_mailer_--_ и до начала названия поля
        String sBP = "_test_mailer";

        _step("1. Вход по прямому URL на услугу");
        openURLservice(driver, CV.baseUrl + "/service/755/general");

        _step("2. Проверить, что открылась нужная услуга");
        assertThis(driver, o.usluga, "_test_mailer");

        _step("3. Авторизация Off AuthMock/BankID");
        o.mokAuthorization();

        _step("4. Заполняем форму услуги");
        setFieldValue(driver, sBP, "mail", "v-i-d-k@mail.ru");
        setFieldFile(driver, sBP, "nFileOne", "src/test/resources/files/test.jpg");
        //attachDocument(tp.attachDocumentButton, "src/test/resources/files/test.jpg", driver);

        _step("5. Отправка формы");
        click(driver, o.buttonSendingForm);

        _step("6. Проверка сообщения о успешной отправке");
        o.checkMessageSuccess("Шановний(-а) MockUser MockUser!\n" +
                "Ваше звернення х-хххххххх успішно зареєстровано");

        _step("7. Нажать кнопку Выйти ");
        click(driver, o.buttonLogOut);

    }
    //</editor-fold>
}
