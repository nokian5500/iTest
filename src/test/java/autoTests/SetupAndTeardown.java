package autoTests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestException;
import org.testng.annotations.*;
import ru.stqa.selenium.factory.WebDriverFactory;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class SetupAndTeardown extends CustomMethods {

    public ConfigurationVariables CV = ConfigurationVariables.getInstance();
    public WebDriver driver;

    DesiredCapabilities capabilities;

    /**************************************************************/
    @BeforeMethod() //  TODO Grushko E.i refactor to singleton
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

            this.driver = WebDriverFactory.getDriver(capabilities);
            this.driver.manage().timeouts().implicitlyWait(CV.implicitTimeWait, TimeUnit.SECONDS);
            this.driver.manage().window().maximize();
            this.driver.manage().deleteAllCookies();
        }
    }

    @AfterSuite()
    public void closeSession() {
        driver.manage().deleteAllCookies();
        driver.quit();
        WebDriverFactory.dismissAll();

    }

    @AfterMethod()
    public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
        if (testResult.getStatus() != ITestResult.SUCCESS) {
            attachScreenshotToAllure();
        }
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] attachScreenshotToAllure() {

        if (null != driver) {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        }
        else throw new TestException("Something wrong with driver initialization...");
    }

}
