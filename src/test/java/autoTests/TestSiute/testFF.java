/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoTests.TestSiute;

/**
 *
 * @author User
 */
import autoTests.CustomMethods;
import com.codeborne.selenide.junit.TextReport;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.openqa.selenium.By;

import java.awt.*;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class testFF extends CustomMethods{
    @Test
  public void search_selenide_in_google() throws AWTException {
    open("http://google.com/ncr");
    $(By.name("q")).val("selenide").pressEnter();


    installECP();
  }
}
