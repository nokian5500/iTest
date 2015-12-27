package helpers;

import common.ApplicationManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseHelper {

    protected static ApplicationManager app;
    protected static WebDriver driver;
    protected static String baseUrl;
    protected static WebDriverWait wait;

    public static void setUp(ApplicationManager appObject) {
        app = appObject;
        driver = app.getDriver();
        baseUrl = app.getBaseUrl();
        wait = app.getWait();
    }
}