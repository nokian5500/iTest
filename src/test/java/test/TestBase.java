package test;

import common.ApplicationManager;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ScreenshotListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

@Listeners({ScreenshotListener.class})
public class TestBase {

    // Variables

    public static ApplicationManager app;
    protected static WebDriver driver;
    protected static String baseUrl;
    protected static WebDriverWait wait;

    // Methods

    @BeforeSuite(alwaysRun = true)
    public void setUp() {
        app = new ApplicationManager();
        driver = app.getDriver();
        baseUrl = app.getBaseUrl();
        wait = app.getWait();
    }

    @BeforeMethod(alwaysRun = true)
    public void set() {
        driver.get(baseUrl);
    }

    @AfterMethod(alwaysRun = true)
    public void clean() {
        driver.manage().deleteAllCookies();
    }

    @AfterSuite(alwaysRun = true)
    public void tearsDown() {
        driver.quit();
    }
}