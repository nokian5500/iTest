/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoTests.TestSiute;

import autoTests.CustomMethods;
import autoTests.pages.main.TemplatePage;
import java.awt.RenderingHints;
import java.awt.RenderingHints.Key;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

/**
 *
 * @author User
 */
public class Test_PrivatDoc extends CustomMethods {

    @Test(enabled = true, groups = {"Main", "Критический функционал"}, priority = 2)
    public void Test_test_PrivatDoc() throws Exception {
        TemplatePage o = new TemplatePage(driver);
        String sBP = "_test_PrivatDoc";
        String email = "autotestbeta@gmail.com";
        _step("2. Вход по прямому URL на  https://doc.p-office.com.ua/#/folder=ANOTHER_UNDONE");

        openURLservice(driver, "https://doc.p-office.com.ua/#/folder=ANOTHER_UNDONE");
        _step("3. Авторизация login/BankID на дашборде. login/pass: (it200687kov/9379992privat)");

        AuthorizationBySetLoginPassword(driver, sBP, "it200687kov", "9379992privat");
        clickButton(driver, sBP, "Продолжить");
        clickButton(driver, sBP, "Неотработанные");
        driver.findElement(By.xpath("//h4[contains(.,'Результаты тендера на выполнение работ по повторному обследованию 15-и зданий и сооружений ПАО \"ОГОК\" в 2016 году /на № 8531818, 18365/')]")).click();
        
        driver.findElement(By.xpath("//a[@class='circle print']")).click();
        driver.findElement(By.xpath(".//*[@id='docFull']")).sendKeys(Keys.CONTROL+"P");
        pause(10000);
    }
}
