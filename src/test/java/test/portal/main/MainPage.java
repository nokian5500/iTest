package test.portal.main;

import common.Constants;
import org.testng.annotations.Test;
import test.TestBase;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class MainPage extends TestBase {

    @Test(priority = 10)
    public void simpleSearchArchivalCertificates() throws Exception {
        // Data
        String service = "Видача архівних довідок, копій, витягів";
        // Test
        app.mainPage.search(service);
        assertTrue(app.mainPage.isSearchResultContains(service));
    }

    @Test(priority = 20)
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

    @Test(priority = 30)
    public void checkAvailabilityOfIssuanceAndReplacementPassportForTravelAbroad() {
        // Data
        String service                        = Constants.Services.Identity.INTERNATIONAL_PASSPORT;
        String regions                        = Constants.Areas.Region.KYIVSKA;
        String cities                         = Constants.Areas.City.BUCHA;
        String email                          = Constants.TestData.PersonalInfo.E_MAIL;
        String successfulServiceMessagesTitle = "Інформація по операції: 'Відсилка запиту на додання нової послуги'";
        String successfulServiceMessagesBody  = Constants.AlertMessages.SuccessfulMessages.INFORMED_SERVICE_AVAILABLE_VIA_INTERNET;

        // Test
        app.mainPage.typeInSearchField(service);
        app.mainPage.clickService(service);
        assertEquals(app.selectAreaPage.serviceName.getText(), service);
        app.selectAreaPage.selectRegion(regions);
        app.selectAreaPage.selectCity(cities);
        assertTrue(app.mainPage.changeRegionButton.isDisplayed());
        assertTrue(app.mainPage.inputEmailAbsentMessageField.isDisplayed());
        assertTrue(app.mainPage.sendAbsentMessageButton.isDisplayed());
        app.mainPage.typeInEmailField(email);
        app.mainPage.clickSendAbsentMessageButton();
        assertEquals(app.modalDialog.titlePopUpMessages.getText(), successfulServiceMessagesTitle);
        assertEquals(app.modalDialog.bodyPopUpMessages.getText(), successfulServiceMessagesBody);
    }
}