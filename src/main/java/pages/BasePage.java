package pages;

import common.ApplicationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    // Variables
    protected static final Logger log = LogManager.getLogger(BasePage.class);

    protected static ApplicationManager app;
    protected static WebDriver driver;
    protected static WebDriverWait wait;

    // Methods

    public static void setUp(ApplicationManager appObj) {
        app = appObj;
        driver = app.getDriver();
        wait = app.getWait();
    }
}