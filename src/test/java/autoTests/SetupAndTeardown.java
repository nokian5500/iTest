package autoTests;

import autoTests.API.DeleteTask;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.junit.ScreenShooter;
import com.codeborne.selenide.junit.TextReport;
import java.io.File;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

public class SetupAndTeardown extends ConfigClass {

    DeleteTask delete = new DeleteTask();

    @Rule
    public ScreenShooter makeScreenshotOnFailure = ScreenShooter.failedTests().succeededTests();

    @Rule
    public TestRule report = new TextReport();

    @Before
    public void setDriver() {
        Configuration.startMaximized = true;

        FirefoxProfile profile = createFirefoxProfileWithExtensions();
        WebDriver driver = new FirefoxDriver(new FirefoxOptions().setProfile(profile));
        driver.manage().window().maximize();
        WebDriverRunner.setWebDriver(driver);
    }

    @After
    public void closeDriver() throws Exception {
        WebDriverRunner.closeWebDriver();
    }

    //@After
    public void deleteFiles() throws Exception {
        //Удаляем заявки
        delete.deleteAllOrderId();
        //Удаляем временные папки и файлы...
        File directory = new File("target");
        CustomMethods.deleteFileOrDirectory(directory);
        directory = new File("surefire");
        CustomMethods.deleteFileOrDirectory(directory);
    }

    private FirefoxProfile createFirefoxProfileWithExtensions() {
        System.setProperty("webdriver.gecko.driver", "src\\test\\resources\\files\\geckodriver.exe");
        //System.setProperty("webdriver.firefox.marionette","src\\test\\resources\\files\\geckodriver.exe");

        String path = "src/test/resources/files/cryptoplugin_ext_id@ff.xpi";
        FirefoxProfile profile = new FirefoxProfile();
        profile.setAssumeUntrustedCertificateIssuer(false);
        profile.addExtension(new File(path));
        profile.setPreference("plugin.state.npcryptoplugin", 2);
        profile.setAssumeUntrustedCertificateIssuer(false);
        profile.setPreference("general.useragent.override", "some UA string");
        profile.setPreference("xpinstall.signatures.required", false);
        DesiredCapabilities ff = DesiredCapabilities.firefox();
        ff.setCapability(FirefoxDriver.PROFILE, profile);
        ff.setCapability("marionette", true);
        return profile;
    }
}
