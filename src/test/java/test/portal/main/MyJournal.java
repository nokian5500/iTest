package test.portal.main;

import org.testng.Assert;
import org.testng.annotations.Test;
import test.TestBase;

import static org.testng.Assert.assertEquals;

public class MyJournal extends TestBase {

    @Test (priority = 10)
    public void myJournalTest() throws Exception {
        app.navHelper.openMyJournalPage();
        assertEquals(app.journalPage.formSignInBankId.getText(), "Щоб почати користуватись сервісом “Мій журнал”, увійдіть через BankID\n" +
                "Сертифікат електронно-\n" +
                "цифрового підпису\n" + "Email");
        app.bankIdPage.loginByPrivatBankBankID();
        assertEquals(app.journalPage.myLog.getText(), "Мій журнал");
        assertEquals(app.journalPage.nextLink.getText(), "Показати ще");
        app.bankIdPage.logOut();
        app.navHelper.openMyJournalPage();
        assertEquals(app.journalPage.formSignInBankId.getText(), "Щоб почати користуватись сервісом “Мій журнал”, увійдіть через BankID\n" +
                "Сертифікат електронно-\n" +
                "цифрового підпису\n" + "Email");
    }
}