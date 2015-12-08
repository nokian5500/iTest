package driverLogic;

import ServicePages.CriminalRecordPage;
import ServicePages.InternationalPassportPage;
import ServicePages.SubsidyPage;
import ServicePages.UnregisterFromLocationPage;
import TestServicePages.TestDependenceFormPage;
import TestServicePages.TestFieldsBankidPage;
import TestServicePages.TestLiqpayPage;
import appLogic.ApplicationManager;
import appLogic.Constants;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import pages.*;

@Listeners({appLogic.ScreenshotListener.class})
public class TestBase {

    private static WebDriver driver;
    public ApplicationManager app;
    public MainPage mainPage;
    public BankIdAuthorizationPage authorizationPage;
    public DocumentsPage documentsPage;
    public MyJournalPage journalPage;
    public AboutPortalPage portalPage;
    public SelectAreaPage selectAreaPage;
    public StatusPage statusPage;
    public SubsidyPage subsidyPage;
    public CriminalRecordPage criminalRecordPage;
    public InternationalPassportPage internationalPassportPage;
    public TestDependenceFormPage testDependenceFormPage;
    public TestFieldsBankidPage testFieldsBankidPage;
    public TestLiqpayPage testLiqpayPage;
    public StatisticTab statisticTab;
    public UnregisterFromLocationPage unregisterFromLocationPage;

    @BeforeClass()
    public static void setUp() {
        driver = ApplicationManager.startTestsIn(Constants.Settings.BROWSER);
    }

    @AfterClass()
    public static void tearsDown() {
        driver.quit();
    }

    @BeforeMethod()
    public void set() {
        app = new ApplicationManager(driver);
        mainPage = new MainPage(driver);
        authorizationPage = new BankIdAuthorizationPage(driver);
        documentsPage = new DocumentsPage(driver);
        journalPage = new MyJournalPage(driver);
        portalPage = new AboutPortalPage(driver);
        selectAreaPage = new SelectAreaPage(driver);
        statusPage = new StatusPage(driver);
        subsidyPage = new SubsidyPage(driver);
        criminalRecordPage = new CriminalRecordPage(driver);
        internationalPassportPage = new InternationalPassportPage(driver);
        testDependenceFormPage = new TestDependenceFormPage(driver);
        testFieldsBankidPage = new TestFieldsBankidPage(driver);
        testLiqpayPage = new TestLiqpayPage(driver);
        statisticTab = new StatisticTab(driver);
        unregisterFromLocationPage = new UnregisterFromLocationPage(driver);
        driver.get(Constants.Server.VersionSERVER);
    }
}
