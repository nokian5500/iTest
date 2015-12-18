package test.portal.main;

import org.testng.Assert;
import org.testng.annotations.Test;
import test.TestBase;

public class Footer extends TestBase {

    @Test (priority = 1)
    public void portalsNewsOnFacebookLinkTest() {
        mainPage.goToServices();
        Assert.assertEquals(mainPage.portalsNewsOnFacebookLink.getText(), "Новини порталу\n" +
                "на Facebook");
    }

    @Test (priority = 2)
    public void checkElectronDigitalSignatureLinkTest() {
        mainPage.goToServices();
        Assert.assertEquals(mainPage.checkElectronDigitalSignatureLink.getText(), "Перевірити електронно-\n" +
                "цифровий підпис");
    }

    @Test (priority = 3)
    public void errorOrABugOnThePortalLinkTest() {
        mainPage.goToServices();
        Assert.assertEquals(mainPage.errorOrABugOnThePortalLink.getText(), "Помилка або баг\n" +
                "на Порталі?");
    }

    @Test (priority = 4)
    public void joinOnGitHubLinkTest() {
        mainPage.goToServices();
        Assert.assertEquals(mainPage.joinOnGitHubLink.getText(), "Приєднатись\n" +
                "на GitHub!");
    }

    @Test (priority = 5)
    public void commentsAndOpportunitiesTest() {
        mainPage.goToServices();
        Assert.assertEquals(mainPage.volunteerIGov.getText(), "NEW Станьте\n" +
                "волонтером iGov!");
    }
}