package test.portal.main;

import common.Constants;
import org.testng.annotations.Test;
import test.TestBase;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class MainPage extends TestBase {

    @Test (priority = 10)
    public void simpleSearchArchivalCertificates() throws Exception {
        // Data
        String service = "Видача архівних довідок, копій, витягів";
        // Test
        app.mainPage.search(service);
        assertTrue(app.mainPage.isSearchResultContains(service));
    }

    @Test (priority = 20)
    public void statisticTabAvailability() throws Exception {
        // Data
        String service = Constants.Services.MVD.CRIMINAL_RECORD;
        // Test
        app.mainPage.search(service);
        app.mainPage.clickService(service);
        assertTrue(app.selectAreaPage.isServiceName(service));
        app.selectAreaPage.openStatisticTab();
        assertTrue(app.statisticTab.numberOfProvidedServicesColumn.isDisplayed());
        assertTrue(app.statisticTab.averageScoreColumn.isDisplayed());
        assertTrue(app.statisticTab.timingColumn.isDisplayed());
    }
}