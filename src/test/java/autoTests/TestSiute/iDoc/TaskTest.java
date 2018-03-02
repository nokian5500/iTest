/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoTests.TestSiute.iDoc;

import autoTests.CustomMethods;

import org.junit.Test;

/**
 * На тестах, находящихся тут, я тестирую работоспособность методов, файл перманентный
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
        createTask("Завдання");
        clickButton("Далi");
        //setRegionFindOrder("5-875451");
        pause(10000);
        setExecutor("Павленко Юлія Юріївна");
        addNewExecutor("Смоктій Вікторія Кирилівна");
        setTaskTerm("30/05/2018");
        setTaskName("Theme2");
        setTaskContent("Content2");
        setTaskForm("Документ");
        getOrderFromUrlCurrentPage();
        clickButtonCreateTask();
        pause(5000);
        clickButton("Ok");
        clickLink("Туренко Ольга Володимирівна");
        clickLink("Вийти");

        AuthorizationBySetLoginPassword("IGOV_270907SVK", " ");
        clickButton("Увійти");
        pause(5000);
        setRegionFindOrderByNumberDocument();
        delegateTask("Грек Одарка Олексіївна", "30/05/2018", "Без спільного виконання");


        //clickButton("Інші дії");
        //clickButton("Делегувати");
       // addDelegate("");
        //clickButtonSign();

    }

    @Test
    public void SZ3() throws Exception {
        openURLdashboard(getRegionUrl());
        AuthorizationBySetLoginPassword("IGOV_260185SAU", " ");
        clickButton("Увійти");
        /*setRegionFindOrder("5-1752592");
        addTask();
        setController("Туренко Ольга Володимирівна");
        setExecutor("Павленко Юлія Юріївна");
        //setTaskForm("Документ");
        //setTaskForm("Файл");
        setTaskForm("Текстове повiдомлення");
        setTaskTerm("Кiлькiсть днiв пiсля", "10");
        setTaskName("Theme1");
        setTaskContent("Content1");*/
        ;
        navigateToggleMenu();
        snapDrawerButtonMenuTabs("Завдання");
        clickLink("На виконанні");

        clickButton("Додати звіт");
        SetRegionFieldInputTypeEnum("Виконано");

    }

    @Test
    public void Test() throws Exception{
        openURLdashboard("https://alpha.test.idoc.com.ua");
        AuthorizationBySetLoginPassword("IGOV_270907SVK", " ");
        clickButton("Увійти");
        pause(5000);
        navigateToggleMenu();
        snapDrawerButtonMenuTabs("Завдання");
        searchTaskByText("проверка связи");
        addReport("Виконане", "Завдання виконане");

        pause(5000);
    }

    @Test
    public void test() throws Exception {
        openURLdashboard("https://beta.test.idoc.com.ua");
        AuthorizationBySetLoginPassword("IGOV_301082BOY", " ");
        clickButton("Увійти");
        pause(5000);
        setRegionFindOrder("№ 15-7604458");
        pause(5000);

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
        setLastExecutorAsMain();
        removeLastExecutor();

        pause(15000);

    }


}
