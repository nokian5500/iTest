package autoTests;

import autoTests.API.DeleteTask;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit.ScreenShooter;
import com.codeborne.selenide.junit.TextReport;
import java.io.File;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestRule;

public class SetupAndTeardown extends ConfigClass {

    DeleteTask delete = new DeleteTask();

    @Rule
    public ScreenShooter makeScreenshotOnFailure = ScreenShooter.failedTests().succeededTests();

    @Rule
    public TestRule report = new TextReport();

    @Before
    public void setDriver() {
        Configuration.browser = "chrome";
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
