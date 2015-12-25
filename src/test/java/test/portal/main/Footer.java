package test.portal.main;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;
import test.TestBase;

public class Footer extends TestBase {

    @Test (priority = 10)
    public void electronDigitalSignatureLink() {
        app.mainPage.goToServices();
        assertEquals(app.mainPage.checkElectronDigitalSignatureLink.getText(), "Перевірити електронно-\n" + "цифровий підпис");
    }

    @Test (priority = 20)
    public void portalNewsOnFacebookLink() {
        app.mainPage.goToServices();
        assertEquals(app.mainPage.portalsNewsOnFacebookLink.getText(), "Новини порталу\n" + "на Facebook");
    }

    @Test (priority = 30)
    public void errorOrBugOnPortalLink() {
        app.mainPage.goToServices();
        assertEquals(app.mainPage.errorOrABugOnThePortalLink.getText(), "Помилка або баг\n" + "на Порталі?");
    }

    @Test (priority = 40)
    public void joinOnGitHubLink() {
        app.mainPage.goToServices();
        assertEquals(app.mainPage.joinOnGitHubLink.getText(), "Приєднатись\n" + "на GitHub!");
    }

    @Test (priority = 50)
    public void becomeIgovVolunteerLink() {
        app.mainPage.goToServices();
        assertEquals(app.mainPage.volunteerIGov.getText(), "NEW Станьте\n" + "волонтером iGov!");
    }
}