package pages;

import common.ApplicationManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class BasePage {

    // Variables

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