package test.portal.main;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;
import test.TestBase;

public class Footer extends TestBase {

    @Test (priority = 10)
    public void electronDigitalSignatureLink() {
        mainPage.goToServices();
        assertEquals(mainPage.checkElectronDigitalSignatureLink.getText(), "Перевірити електронно-\n" + "цифровий підпис");
    }

    @Test (priority = 20)
    public void portalNewsOnFacebookLink() {
        mainPage.goToServices();
        assertEquals(mainPage.portalsNewsOnFacebookLink.getText(), "Новини порталу\n" + "на Facebook");
    }

    @Test (priority = 30)
    public void errorOrBugOnPortalLink() {
        mainPage.goToServices();
        assertEquals(mainPage.errorOrABugOnThePortalLink.getText(), "Помилка або баг\n" + "на Порталі?");
    }

    @Test (priority = 40)
    public void joinOnGitHubLink() {
        mainPage.goToServices();
        assertEquals(mainPage.joinOnGitHubLink.getText(), "Приєднатись\n" + "на GitHub!");
    }

    @Test (priority = 50)
    public void becomeIgovVolunteerLink() {
        mainPage.goToServices();
        assertEquals(mainPage.volunteerIGov.getText(), "NEW Станьте\n" + "волонтером iGov!");
    }
}