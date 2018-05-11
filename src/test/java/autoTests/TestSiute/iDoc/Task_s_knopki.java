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
//        String LoginAuthor = "IGOV_301082BOY";
//        String NameAuthor = "Бондарь Ольга Євгенієвна";
//        String Login1 = "IGOV_160582SOD";
//        String Name1 = "Смоктій Оксана Данилівна";
//        String Login2 = "IGOV_310780BVV";
//        String Name2 = "Белявцев Володимир Володимирович";
//        String Login3 = "IGOV_230878LIV";
//        String Name3 = "Літовченко Інна Вадимівна";
//        String Login4 = "IGOV_100982SOV";
//        String Name4 = "Смірнова Олена Володимирівна";
//        String Login5 = "IGOV_210961SMU";
//        String Name5 = "Соколова Марина Юріївна";
//        String Login6 = "IGOV_311288BUD";
//        String Name6 = "Біла Юлія Данилівна";   
//        String Login7 = "IGOV_180277SMV";
//        String Name7 = "Свідрань Максим Володимирович";
        
        //Используемые в документе логины для Альфы/Гаммы
        String LoginAuthor = "IGOV_270907SVK";
        String NameAuthor = "Смоктій Вікторія Кирилівна";
        String Login1 = "IGOV_200687TOV";
        String Name1 = "Туренко Ольга Володимирівна";
        String Login2 = "IGOV_220290PUU";
        String Name2 = "Павленко Юлія Юріївна";
        String Login3 = "IGOV_130384GOA";
        String Name3 = "Грек Одарка Олексіївна";
        String Login4 = "IGOV_110771GAV";
        String Name4 = "Герман Август Васильович";
        String Login5 = "IGOV_260185SAU";
        String Name5 = "Столбова Анна Юріївна";
        String Login6 = "IGOV_151071GUO";
        String Name6 = "Гуков Юрій Олександрович";
 

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
//        setTaskTerm(getDate(5));
        setTaskTerm("15/05/2018");
        setTaskTime(getTime(17));
        setTaskForm("Текстове повiдомлення");
        
        setExecutor(Name1);
        addNewExecutor(Name2);
        setTaskContent("Перевірка завдання автотестом, задача з кнопки"); 
        clickButtonCreateTask();
        pause(5000);
    
        //Проверка наличия кнопок сразу после создания (автор)
        clickLink("На контролі ");
        searchTaskByText(generateText);
        isExistButton("Відкрити документ",false);
        isExistButton("Прийняти завдання",false);
        isExistButton("Перенести",true);
        isExistButton("Вiдхилити звiт",true);
        isExistButton("Інші дії",true);
        clickButton("Інші дії");
        isExistButton("Коментар",true);
        isExistButton("Редагувати завдання",true);
        isExistButton("Не прийняти",true);
        isExistButton("Неактуально",true);
        logout();
        
        
        //Первый исполнитель
        login(Login1, " ");
        snapDrawerButtonMenuTabs("Завдання");
        clickLink("На виконанні");
        searchTaskByText(generateText);
        
        //Проверка наличия кнопок исполнителя
        isExistButton("Перенести",true);
        isExistButton("Додати звiт",true);
        isExistButton("Делегувати",true);
        isExistButton("Відкрити документ",false);
        isExistButton("Прийняти завдання",false);
        isExistButton("Вiдхилити звiт",false);
        isExistButton("Інші дії",true);
        clickButton("Інші дії");
        isExistButton("Коментар",true);
        isExistButton("Редагувати завдання",false);
        isExistButton("Не прийняти",false);
        isExistButton("Неактуально",false);
        
        //Прикрепление отчета
        addReport("Виконане", "Завдання виконане"); 
        
        
        pause(5000);
        logout();
        
        
        //Второй исполнитель
        login(Login2, " ");
        snapDrawerButtonMenuTabs("Завдання");
        clickLink("На виконанні");
        searchTaskByText(generateText);
        scrollTo("Виконано");   //Проверка наличия отчета первым исполнителем
        scrollTo("Завдання виконане");      //Проверка наличия отчета первым исполнителем
        
        //Проверка наличия кнопок исполнителя
        isExistButton("Перенести",true);
        isExistButton("Додати звiт",true);
        isExistButton("Делегувати",true);
        isExistButton("Відкрити документ",false);
        isExistButton("Прийняти завдання",false);
        isExistButton("Вiдхилити звiт",false);
        isExistButton("Інші дії",true);
        clickButton("Інші дії");
        isExistButton("Коментар",true);
        isExistButton("Редагувати завдання",false);
        isExistButton("Не прийняти",false);
        isExistButton("Неактуально",false);
        
        //Прикрепление отчета
        addReport("Виконане", "Завдання виконане"); 

        pause(5000);
        logout();
        
        
        //Автор Принятие отчета
        login(LoginAuthor, " ");
        snapDrawerButtonMenuTabs("Завдання");
        clickLink("На виконанні");
        searchTaskByText(generateText);
        
        clickButton("Інші дії");
        clickButton("Редагувати завдання");
        //Наличие кнопок (Редактирование)
        isExistButton("Редагувати",true);
        isExistButton("Перенести",true);
        isExistButton("Вiдхилити звiт",true);
        isExistButton("Делегувати",false);
        isExistButton("Відкрити документ",false);
        isExistButton("Прийняти завдання",false);
        isExistButton("Інші дії",true);
        clickButton("Інші дії");
        isExistButton("Коментар",true);
        isExistButton("Редагувати завдання",true);
        isExistButton("Не прийняти",true);
        isExistButton("Неактуально",true);
        
        clickButton("Прийняти завдання");
        pause(5000);
        logout();
        
    }
}
