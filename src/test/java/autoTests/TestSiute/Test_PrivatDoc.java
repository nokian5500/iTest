/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoTests.TestSiute;

import autoTests.CustomMethods;
import autoTests.pages.main.TemplatePage;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.testng.annotations.Test;

/**
 *
 * IDOC архив укрДок
 */
public class Test_PrivatDoc extends CustomMethods {

    @Test(enabled = true, groups = {"Main", "Критический функционал"}, priority = 2)
    public void Test_test_PrivatDoc() throws Exception {
        TemplatePage o = new TemplatePage(driver);
        String sBP = "_test_PrivatDoc";
        String email = "autotestbeta@gmail.com";
      
        _step("1. Вход по прямому URL на  https://doc.p-office.com.ua/#/folder=ANOTHER_UNDONE");

        openURLservice(driver, "https://doc.p-office.com.ua/#/folder=ANOTHER_UNDONE");
       
        _step("2. Авторизация login/BankID на дашборде. login/pass: (it200687kov/9379992privat)");
        AuthorizationBySetLoginPassword(driver, sBP, "it200687kov", "9379992privat");
        clickButton(driver, sBP, "Продолжить");
        openDocFromPrivatDoc(driver, sBP, "https://doc.p-office.com.ua/preview.html#/folder=SEARCH&doc=10322628&year=2017");
        scanDocFromPrivatDoc(driver);
//        getSubstringFromUrlCurrentPage(driver, "doc=");
//        getNameSavingFile("d://archiveIGOV//");

    }
}
