/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoTests.TestSiute.iGov;

import autoTests.CustomMethods;
import autoTests.pages.main.TemplatePage;
import org.junit.Test;

/**
 *
 * IDOC архив укрДок
 */
public class Test_PrivatDoc extends CustomMethods {

    @Test
    public void Test_test_PrivatDoc() throws Exception {
        TemplatePage o = new TemplatePage();
        String sBP = "_test_PrivatDoc";
        String email = "autotestbeta@gmail.com";

        openURLservice("https://doc.p-office.com.ua/#/folder=ANOTHER_UNDONE");

        AuthorizationBySetLoginPassword("it200687kov", "9379992privat");
        clickButton("Продолжить");
        openDocFromPrivatDoc("https://doc.p-office.com.ua/preview.html#/folder=SEARCH&doc=10322628&year=2017");
        scanDocFromPrivatDoc();
//        getSubstringFromUrlCurrentPage(driver, "doc=");
//        getNameSavingFile("d://archiveIGOV//");

    }
}
