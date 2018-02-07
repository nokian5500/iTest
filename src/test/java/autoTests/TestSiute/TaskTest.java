/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoTests.TestSiute;

import autoTests.CustomMethods;


import static com.codeborne.selenide.Selenide.open;
import org.junit.Test;

/**
 *
 * @author Oleksandr Belichenko
 */
public class TaskTest extends CustomMethods{
    
    @org.junit.Test
    public void Go() throws Exception {
        open("https://alpha.test.idoc.com.ua/login");
        AuthorizationBySetLoginPassword("IGOV_270907SVK", " ");
        clickButton("Увійти");
        navigateToggleMenu();
        setRegionFindOrder("5-275013");
        //createDocumentOrTask("Службова записка");
        //clickButton("Далi");
        pause(10000);
        addTask();

        setExecutor("Павленко Юлія Юріївна");
        addNewExecutor("Смоктій Вікторія Кирилівна");
        setController("Павленко Юлія Юріївна");

        //setTaskForm("Документ");
        //setTaskForm("Файл");
       // setTaskForm("Текстове повiдомлення");
        //setTaskTerm("Календарна дата", "30/05/2018");
        //setTaskTerm("Кiлькiсть днiв пiсля", "10");
        //setTaskName("Theme");
        //setTaskContents("Content");
        //addNewExecutor("Смоктій Вікторія Кирилівна");
        //setMainExecutor("Смоктій Вікторія Кирилівна");
    }



}
