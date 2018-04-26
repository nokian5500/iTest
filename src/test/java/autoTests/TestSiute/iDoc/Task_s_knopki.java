/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoTests.TestSiute.iDoc;

import autoTests.CustomMethods;
import org.junit.Test;

/**
 *
 * @author aleks
 */
public class Task_s_knopki extends CustomMethods {
    
   @Test
    public void Task_s_knopki() throws Exception { 
    
        String sBP = "_task";
        String email = "autotestbeta@gmail.com";
        openURLdashboard(getRegionUrl());
        
        //Используемые в документе логины для Беты
        String LoginAuthor = "IGOV_301082BOY";
        String NameAuthor = "Бондарь Ольга Євгенієвна";
        String Login1 = "IGOV_160582SOD";
        String Name1 = "Смоктій Оксана Данилівна";
        String Login2 = "IGOV_310780BVV";
        String Name2 = "Белявцев Володимир Володимирович";
        String Login3 = "IGOV_180488PUG";
        String Name3 = "Продан Юлія Георгіївна";
        String Login4 = "IGOV_100982SOV";
        String Name4 = "Смірнова Олена Володимирівна";
        String Login5 = "IGOV_210961SMU";
        String Name5 = "Соколова Марина Юріївна";
        String Login6 = "IGOV_311288BUD";
        String Name6 = "Біла Юлія Данилівна";
        String Login7 = "IGOV_180277SMV";
        String Name7 = "Свідрань Максим Володимирович";

        //----Создание---- 
        //Авторизация
        login(LoginAuthor, " ");
        navigateToggleMenu();
        snapDrawerButtonMenuTabs("Завдання");
        clickButton("Створити завдання");        
        createDocumentOrTask("Завдання");
        clickButton("Далi");
        pause(5000);
        setTaskName(generateText(10));
        setTaskTerm("Календарна дата", getDate(5));
        
        
        setExecutor(Name4);
        addNewExecutor(Name5);
        setTaskContent("Перевірка завдання автотестом, задача 3 кнопки");
        pause(10000);
        
        
    
    }
}
