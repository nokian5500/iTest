package test.portal.main;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;
import test.TestBase;

public class Footer extends TestBase {

    @Test (priority = 10)
    public void footerLinks() {
        assertTrue(app.footer.checkSignatureLink.isDisplayed() &&
                    app.footer.portalsNewsOnFacebookLink.isDisplayed() &&
                    app.footer.errorOrABugOnThePortalLink.isDisplayed() &&
                    app.footer.joinOnGitHubLink.isDisplayed() &&
                    app.footer.volunteerIGov.isDisplayed());
    }
}