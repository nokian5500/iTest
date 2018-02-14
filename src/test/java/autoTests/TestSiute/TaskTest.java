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
    
    @Test
    public void SZ() throws Exception {
        String sBP = "_doc_btsol_vertical_sz";
        openURLdashboard(getRegionUrl());

        AuthorizationBySetLoginPassword("IGOV_270907SVK", " ");
        clickButton("Увійти");
        navigateToggleMenu();
        //setRegionFindOrder("5-275013");
        createDocumentOrTask("Службова записка");
        clickButton("Далi");
        pause(10000);

        setDocTitle("Autotest is back");
        setDocContent("Back! Back! Back!");

        /*Таблица Узгоджуючі*/
//        setRegionTableCellsInputTypeEnumInput(driver, sBP, "sTableAgree", "sName_Approver", "0", "Замдиректора Потапченко Василь");
        setAcceptor(sBP, "sTableAccept", "sName_Acceptor", "0", "Туренко Ольга Володимирівна");

        /*Таблица Затверджуючий*/
        setApprover(sBP, "sTableAgree", "sName_Approver", "0", "Павленко Юлія Юріївна");

        /*Таблица Адресат*/
        setDirect(sBP, "sTableDirect", "sName_Direct", "0", "Смоктій Вікторія Кирилівна");

        addTask();
        setController("Туренко Ольга Володимирівна");
        setExecutor("Павленко Юлія Юріївна");
        //setTaskForm("Документ");
        //setTaskForm("Файл");
        setTaskForm("Текстове повiдомлення");
        setTaskTerm("Кiлькiсть днiв пiсля", "10");
        setTaskName("Theme1");
        setTaskContent("Content1");
        addNewExecutor("Смоктій Вікторія Кирилівна");
        //setMainExecutor("Смоктій Вікторія Кирилівна");


        addTask();
        setController("Туренко Ольга Володимирівна");
        setExecutor("Гуков Юрій Олександрович");
        setTaskForm("Документ");
        //setTaskForm("Файл");
        //setTaskForm("Текстове повiдомлення");
        setTaskTerm("Календарна дата", "30/05/2018");
        setTaskName("Theme2");
        setTaskContent("Content2");
        addNewExecutor("Грек Одарка Олексіївна");
        //setMainExecutor("Смоктій Вікторія Кирилівна");

        clickButtonCreate();
        clickButton("Ok");
    }

    @Test
    public void SZ2() throws Exception {
        String sBP = "_doc_btsol_vertical_sz";
        openURLdashboard(getRegionUrl());

        AuthorizationBySetLoginPassword("IGOV_200687TOV", " ");
        clickButton("Увійти");
        navigateToggleMenu();
        setRegionFindOrder("5-875451");
        pause(10000);

        //clickButton("Інші дії");
        //clickButton("Делегувати");
       // addDelegate("Туренко Ольга Володимирівна");
        clickButtonSign();

    }



}
