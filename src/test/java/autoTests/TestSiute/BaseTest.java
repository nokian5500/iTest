package autoTests.TestSiute;

import autoTests.ConfigurationVariables;
import autoTests.CustomMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import ru.stqa.selenium.factory.WebDriverFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * **********************************************************
 * Created by Evgeny Grushko on 15.09.2016.
 * <p>
 * Class contains base methods that should be the same for all tests
 * <p>
 * ***********************************************************
 **/
public class BaseTest extends CustomMethods {

    ConfigurationVariables CV = ConfigurationVariables.getInstance();
    public WebDriver driver;

    DesiredCapabilities capabilities;

    /**************************************************************/
    @BeforeMethod()
    public void SetUp() throws IOException {

        if (null == driver) {
            /********* Закоментить для  для запуска на своем профиле и откоментить для запуска на дефолтном ***********/
            FirefoxProfile profile = new FirefoxProfile();
            profile.setEnableNativeEvents(false);
            profile.setAcceptUntrustedCertificates(true);

            /********* Раскомментить для запуска на своем профиле и закоментить для дефолтного ***********/
//        ProfilesIni allProfiles = new ProfilesIni();
//        FirefoxProfile profile = allProfiles.getProfile("default");

            profile.setEnableNativeEvents(false);
            profile.setAcceptUntrustedCertificates(true);
            profile.setAssumeUntrustedCertificateIssuer(true);
            profile.setPreference("javascript.enabled", true);
            profile.setPreference("geo.enabled", false);

            this.capabilities = DesiredCapabilities.firefox();
            this.capabilities.setCapability(FirefoxDriver.PROFILE, profile);
            this.capabilities.setCapability("unexpectedAlertBehaviour", "ignore");
            System.out.println("Tests will be run (or rerun) in Firefox with custom profile...");

            this.driver = WebDriverFactory.getDriver(capabilities);
            this.driver.manage().timeouts().implicitlyWait(CV.implicitTimeWait, TimeUnit.SECONDS);
            this.driver.manage().window().maximize();
            this.driver.manage().deleteAllCookies();
        }
    }

}
