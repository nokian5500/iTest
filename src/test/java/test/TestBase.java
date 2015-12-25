package test;

import common.ApplicationManager;
import pages.service.authorities.interaction.LandSizeAndExistencePage;
import pages.service.police.traffic.RegisterUsedCarPage;
import pages.service.test.*;
import pages.service.taxes.*;
import utils.ScreenshotListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.main.AboutPortalPage;
import pages.main.BankIdAuthorizationPage;
import pages.main.DocumentsPage;
import pages.main.MainPage;
import pages.main.MyJournalPage;
import pages.main.SelectAreaPage;
import pages.main.StatisticTab;
import pages.main.StatusPage;
import pages.service.authorities.interaction.AssignSocialAssistanceForChildBirthPage;
import pages.service.police.traffic.CriminalRecordPage;
import pages.service.identity.citizenship.residense.InternationalPassportPage;
import pages.service.authorities.interaction.SubsidyPage;
import pages.service.identity.citizenship.residense.UnregisterFromLocationPage;

@Listeners({ScreenshotListener.class})
public class TestBase {

    private static WebDriver driver;
    public static ApplicationManager app;

    @BeforeSuite
    public void setUp() {
        System.out.println("BeforeSuite");
        app = new ApplicationManager();
        driver = app.getDriver();
    }

    @BeforeMethod
    public void set() {
        driver.get(app.getBaseUrl());
    }

    @AfterClass
    public void clean() {
        driver.manage().deleteAllCookies();
    }

    @AfterSuite()
    public void tearsDown() {
        driver.quit();
    }
}