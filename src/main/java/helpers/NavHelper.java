package helpers;

import org.openqa.selenium.By;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class NavHelper extends BaseHelper {

    // Methods

    public void openServicesPage() {
        if(!driver.getCurrentUrl().contentEquals(baseUrl)) {
            app.header.servicesLink.click();
            wait.until(visibilityOfElementLocated(By.xpath("//h1[text()='Громадянам ']")));
        }
    }

    public void openDocumentsPage() {
        if(!driver.getCurrentUrl().contentEquals(baseUrl + "documents/user/bankid")) {
            app.header.documentsLink.click();
            wait.until(visibilityOfElementLocated(By.xpath("//a[text()='Мої документи']")));
        }
    }

    public void openStatusesPage() {
        if(!driver.getCurrentUrl().contentEquals(baseUrl + "order/search")) {
            app.header.statusesLink.click();
            wait.until(visibilityOfElementLocated(By.xpath("//h1[text()='Заявки']")));
        }
    }

    public void openMyJournalPage() {
        if(!driver.getCurrentUrl().contentEquals(baseUrl + "journal/bankid")) {
            app.header.myJournalLink.click();
        }
    }

    public void openAboutPortalPage() {
        if(!driver.getCurrentUrl().contentEquals(baseUrl + "about")) {
            app.header.aboutPortalLink.click();
            wait.until(visibilityOfElementLocated(By.xpath("//p[1][contains(.,'На цьому порталі зібрано послуги')]")));
        }
    }
}