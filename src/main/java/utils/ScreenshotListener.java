package utils;

import common.ApplicationManager;
import com.google.common.io.Files;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotListener extends TestListenerAdapter {

    protected static final Logger log = LogManager.getLogger(ScreenshotListener.class);

    @Override
    public void onTestFailure(ITestResult result) {
        saveScreenshot(result, ApplicationManager.driver);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        saveScreenshot(result, ApplicationManager.driver);
    }

    private void saveScreenshot(ITestResult result, WebDriver driver) {
        //Для того чтобы передавать html теги и спец-символы в reporter.log
        //Или можно передать параметр в командную строку при выполнении TestNG:
        //-Dorg.uncommons.reportng.escape-output=false
        System.setProperty("org.uncommons.reportng.escape-output", "false");

        Reporter.setCurrentTestResult(result);

        //Create a directory; all non-existent ancestor directories are
        //automatically created
        boolean success = (new File("TestReport/html/Screens/")).mkdirs();
        if (!success) {
            //Directory creation failed
            log.info("Directory creation failed. Папка уже создана?");
        }

        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String successLogMessage = "The test - \"" + result.getMethod().getMethodName() + "\" was successfully ended (" + formatter.format(new Date()) + ")";
        String ErrorLogMessage = "The test - \"" + result.getMethod().getMethodName() + "\" was failed! (" + formatter.format(new Date()) + ")";

        try {
            if (!result.isSuccess()) {
                File screenshot = new File("TestReport/html/Screens/" + result.getMethod().getMethodName() + ".png");
                screenshot.delete();
                File screenshotTempFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                try {
                    Files.copy(screenshotTempFile, screenshot);
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
                Reporter.log(
                        "<center>Скриншот снят при падении теста " +
                                screenshot.getName() +
                                ", URL = " +
                                driver.getCurrentUrl() +
                                "<br><div><a target=\"_blank\" href=\"Screens/" +
                                result.getMethod().getMethodName() +
                                ".png\"><img  style=\"height:400px; width: 600px;\"  src=\"" + "Screens/" +
                                result.getMethod().getMethodName() +
                                ".png" +
                                "\"></a></div><center><br><br>",
                        true);
                log.error(ErrorLogMessage);
            } else {
                log.info(successLogMessage);
                Reporter.log(successLogMessage);
            }
        } catch (Exception e) {
            ApplicationManager.addErrorToTheReport("Connection with browser was lost.");
        }
    }
}