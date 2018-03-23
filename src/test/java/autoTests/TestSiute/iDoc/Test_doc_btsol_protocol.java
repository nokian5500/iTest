/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoTests.TestSiute.iDoc;

import autoTests.CustomMethods;
import static autoTests.CustomMethods.generateText;

import static com.codeborne.selenide.Selenide.open;

import org.junit.Test;
/**
 *
 * @author User
 */
public class Test_doc_btsol_protocol  extends CustomMethods {
    @Test
    public void Test_doc_btsol_protocol() throws Exception {
        String sBP = "_doc_btsol_protocol_meeting";
        String email = "autotestbeta@gmail.com";

        openURLdashboard(getRegionUrl());
        
        //Авторизация
        login("IGOV_270907SVK", " ");
        navigateToggleMenu();
        createDocumentOrTask("Протокол оперативної наради");
        clickButton("Далi");
        pause(5000);
        
            //----Создание---- 
        setDocTitle("Автотест протокола"); 
        setDocContent("Автотест протокола");
        SetRegionFieldInputTypeDate("sDateMeeting", "19.03.2018");
        SetRegionFieldInputTypeLong("sAmtDecisions", "2");
        setSelect("_doc_btsol_protocol_meeting", "sName_Chairperson", "Смоктій Вікторія Кирилівна");
        setParticipant(sBP, "sTable_listener", "sName_isExecute", "0", "Туренко Ольга Володимирівна");
        addRegionsTableRow("sTable_listener");
        setParticipant(sBP, "sTable_listener", "sName_isExecute", "1", "Смоктій Вікторія Кирилівна");
        setTaskContent("Запустить автотест протокола");
        setRegionTableCellsInputTypeString("sNumber", "0", "1");
        setRegionTableCellsInputTypeString("sNameFile", "0", "Тестовий додаток");
        setRegionTableCellsInputTypeFile(sBP,"sTableFile", "sFile", "0", "src/test/resources/files/test.jpg");
        
        
        
        /*Таблица Узгоджуючі*/
        setAcceptor(sBP, "sTableAccept", "sName_Acceptor", "0", "Туренко Ольга Володимирівна");
        /*addRegionsTableRow("sTableAccept");
        setAcceptor(sBP, "sTableAccept", "sName_Acceptor", "1", "Грек Одарка Олексіївна");
        addRegionsTableRow("sTableAccept");
        setAcceptor(sBP, "sTableAccept", "sName_Acceptor", "2", "Столбова Анна Юріївна");*/
        
        /*Таблица Затверджуючий*/
        setApprover(sBP, "sTableAgree", "sName_Approver", "0", "Герман Август Васильович");
        
        //Додати завдання (Работает)
        addTask();
        setTaskName(generateText(10));
        setTaskTerm("Кiлькiсть днiв пiсля", "3");
        setTaskForm("Текстове повiдомлення");
        SetRegionFieldInputTypeLong("sAmtDecisions", "3");
        setController("Смоктій Вікторія Кирилівна");
        setExecutor("Столбова Анна Юріївна");
        addNewExecutor("Літовченко Інна Вадимівна");  
        pause(5000);
        setTaskContent("Перевірка завдання автотестом");
       
        //Добавляем на просмотр
        clickButton("Інші дії");
        addViewer("Літовченко Інна Вадимівна");
        //Проверка наличия кнопок (Работает)
        isExistButton("Зберегти",true);
        isExistButton("Роздрукувати",true);
        isExistButton("Додати завдання",true);
        isExistButton("Інші дії",true);
        clickButton("Інші дії");
        isExistButton("Додати на перегляд",true);
        isExistButton("Видалити",true);
        isExistButton("Зауваження",false);
        isExistButton("Ознайомити",false);
        isExistButton("Додати підписанта",false);
        isExistButton("Делегувати",false);
        isExistButton("Відмовити",false);
        isExistButton("Підпис не потрібен",false);
        isExistButton("Редагувати завдання",false);
           
        getOrderFromUrlCurrentPage();
        clickButtonCreate();
        isError();
        logout();       

            //----Подписываем (Согласование)----
        //Первый подписант
        login("IGOV_200687TOV", " ");
        setRegionFindOrderByNumberDocument();
        pause(5000);
//        isError();
       // addComment("Тестове зауваження");
        //clickButton("Ok"); 
        scrollTo("Затвердження");
        clickButton("Інші дії");
        addViewer("Смоктій Оксана Данилівна");
        clickButton("Інші дії");
        clickButton("Делегувати");
        addDelegate("Грек Одарка Олексіївна");
        isError();
        pause(2000);
        logout();
        
        //подпись еще одним исполнителем (с добавить на согласования)
        login("IGOV_130384GOA", " ");
        setRegionFindOrderByNumberDocument();
        pause(2000);
        
        isExistButton("Пiдписати",true);
        isExistButton("Зберегти",true);
        isExistButton("Роздрукувати",true);
        isExistButton("Зауваження",true); 
        isExistButton("Інші дії",true);
        clickButton("Інші дії");
        isExistButton("Коментар",true);
        isExistButton("Додати на перегляд",true);
        isExistButton("Ознайомити",true);
        isExistButton("Додати підписанта",true);
        isExistButton("Делегувати",true);
        isExistButton("Відмовити",true);
        isExistButton("Підпис не потрібен",true);
        isExistButton("Редагувати завдання",false);
        isExistButton("Видалити",false);
        isExistButton("Додати завдання",false);
        //----
        //clickButton("Інші дії");
        addAcceptor("Гуков Юрій Олександрович");

        clickButtonSignNotNeed(sBP, "Подпись не нужна");
        pause(5000);
        logout();
        
        //Второй подписант(С делегирования)
        login("IGOV_151071GUO", " ");
        setRegionFindOrderByNumberDocument();
        pause(2000);
        clickButtonSign();
        pause(5000);
        logout();
        
        //Снять подпись 
        login("IGOV_151071GUO", " ");
        setRegionFindOrderByNumberDocument();
        pause(2000);
        cancelSign("Перевірка зняття підпису");
        pause(5000);
        clickButtonSign();
        pause(5000);
        logout();
        
//        //Удалить подписанта
//        login("IGOV_130384GOA", " ");
//        setRegionFindOrderByNumberDocument();
//        pause(5000);
//        removeParticipant(2, true);
//        clickButtonSign();
//        pause(5000);
//        clickLink("рек Одарка Олексіївна");
//        clickLink("Вийти");
        
        
                //-----Редактирование-----
        //Автору, ответить на замечание и редактировать
        //Проверка наличия кнопок
        login("IGOV_270907SVK", " ");
        setRegionFindOrderByNumberDocument();
        answerComment("Відповідь на зауваження");
        pause(5000);
//        clickButton("Ok");
        clickButton("Редагувати");
        pause(5000);
        //Проверка наличия кнопок
        isExistButton("Пiдписати",true);
        isExistButton("Зберегти",true);
        isExistButton("Роздрукувати",true);
        isExistButton("Додати завдання",false);
        isExistButton("Інші дії",true);
        clickButton("Інші дії");
        isExistButton("Додати на перегляд",true);
        isExistButton("Видалити",true);
        isExistButton("Редагувати завдання",true);
        isExistButton("Зауваження",false);
        isExistButton("Ознайомити",false);
        isExistButton("Додати підписанта",false);
        isExistButton("Делегувати",false);
        isExistButton("Відмовити",false);
        
        //Редактирование задачи
        //clickButton("Інші дії");
        clickButton("Редагувати завдання");
        SetRegionFieldInputTypeLong("sAmtDecisions", "5");      
        clickButtonSign();
        pause(5000);
        logout();
        
        //Подписи исполнителей задачи
        login("IGOV_260185SAU", " ");
        setRegionFindOrderByNumberDocument();
        pause(2000);
        clickButtonRefuse(sBP, "Отказать");
        //clickButtonSign();
        pause(5000);
        logout();
        
        login("IGOV_230878LIV", " ");
        setRegionFindOrderByNumberDocument();
        pause(2000);
        clickButtonSign();
        pause(5000); 
        logout();
        
        //Подписываем (Утверждение)      
        login("IGOV_110771GAV", " ");
        setRegionFindOrderByNumberDocument();
        pause(2000);
        clickButton("Інші дії");
        addVisor("Павленко Юлія Юріївна");
        clickButtonSign();
        pause(5000);
        logout();
        
        //Ознакомление
        login("IGOV_220290PUU", " ");
        pause(5000);
        setRegionFindOrderByNumberDocument();
        pause(5000);
        clickButton("Ознайомлений");
        pause(5000);
        logout();
        
        //Ознакомление Автором
        login("IGOV_270907SVK", " ");
        pause(2000);
        setRegionFindOrderByNumberDocument();
        pause(5000);
         //Проверка наличия кнопок
        isExistButton("Ознайомлений",true);
        isExistButton("Зберегти",true);
        isExistButton("Роздрукувати",true);
        isExistButton("Інші дії",true);
        clickButton("Інші дії");
        isExistButton("Коментар",true);
        isExistButton("Додати на перегляд",true);
        isExistButton("Ознайомити",false);
        isExistButton("Видалити",false);
        isExistButton("Додати завдання",false);
        isExistButton("Редагувати завдання",false);
        isExistButton("Зауваження",false);
        isExistButton("Ознайомити",false);
        isExistButton("Додати підписанта",false);
        isExistButton("Делегувати",false);
        isExistButton("Відмовити",false);
        clickButton("Ознайомлений");
        pause(5000);
        logout();
        
            //-----Работа с задачей------
        //Первый исполнитель
        login("IGOV_260185SAU", " ");
        snapDrawerButtonMenuTabs("Завдання");
        clickLink("На виконанні");
        searchTaskByText(generateText);
        //Проверка наличия кнопок
        isExistButton("Відкрити документ",true); 
        isExistButton("Перенести",true);
        isExistButton("Додати",true);
        isExistButton("Делегувати",true);
        isExistButton("Інші дії",true);
        clickButton("Інші дії");
        isExistButton("Коментар",true);
        //--
        addReport("Виконане", "Завдання виконане");
        isError();
        pause(2000);
        logout();
        
        
        //Второй исполнитель
        login("IGOV_230878LIV", " ");
        snapDrawerButtonMenuTabs("Завдання");
        clickLink("На виконанні");
        searchTaskByText(generateText);
        /*Наличие кнопок*/
        addReport("Виконане", "Завдання виконане");
        isError();
        pause(2000);
        logout();
        
  
        //Контролирующий
        login("IGOV_270907SVK", " ");
        snapDrawerButtonMenuTabs("Завдання");
        clickLink("На контролі");
        searchTaskByText(generateText);
        //Проверка наличия кнопок
        isExistButton("Відкрити документ",true);
        isExistButton("Прийняти завдання",true);
        isExistButton("Перенести",true);
        isExistButton("Вiдхилити звiт",true);
        isExistButton("Інші дії",true);
        clickButton("Інші дії");
        isExistButton("Коментар",true);
        isExistButton("Редагувати завдання",true);
        isExistButton("Не прийняти",true);
        isExistButton("Неактуально",true);
        //--
        clickButton("Прийняти завдання");
        pause(2000);
        clickButton("Відкрити документ");
        pause(5000);
        scrollTo("Коментарі");
        pause(10000);
        logout();
    }
}
        