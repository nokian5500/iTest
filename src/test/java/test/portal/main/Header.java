package test.portal.main;

import org.testng.annotations.Test;
import test.TestBase;
import static org.testng.Assert.assertTrue;

public class Header extends TestBase {

    @Test(priority = 10)
    public void headerLinks() {
        assertTrue(app.header.servicesLink.isDisplayed() &&
                app.header.documentsLink.isDisplayed() &&
                app.header.statusesLink.isDisplayed() &&
                app.header.myJournalLink.isDisplayed() &&
                app.header.aboutPortalLink.isDisplayed());
    }
}