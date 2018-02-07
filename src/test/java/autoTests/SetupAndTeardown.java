package autoTests;

import autoTests.API.DeleteTask;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit.ScreenShooter;
import com.codeborne.selenide.junit.TextReport;
import java.io.File;
import java.util.prefs.Preferences;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import sun.java2d.cmm.Profile;

public class SetupAndTeardown extends ConfigClass {

    DeleteTask delete = new DeleteTask();

    @Rule
    public ScreenShooter makeScreenshotOnFailure = ScreenShooter.failedTests().succeededTests();

    @Rule
    public TestRule report = new TextReport();

    @Before
    public void setDriver() {
        Configuration.startMaximized = true;
        //Configuration.browser = "chrome";
        FirefoxOptions firefoxOptions = new FirefoxOptions();

        //ChromeOptions options = new ChromeOptions();
        String path = "src/test/resources/files/cryptoplugin_ext_id@privatbank.ua.xpi";
        firefoxOptions.addPreference("plugin.state.npcryptoplugin", 2);
                //.addExtensions(new File(path));
    }

    @After
    public void deleteFiles() throws Exception {
        //Удаляем заявки
        delete.deleteAllOrderId();
        //Удаляем временные папки и файлы...
        File directory = new File("target");
        CustomMethods.deleteFileOrDirectory(directory);
        directory = new File("surefire");
        CustomMethods.deleteFileOrDirectory(directory);
    }
}
