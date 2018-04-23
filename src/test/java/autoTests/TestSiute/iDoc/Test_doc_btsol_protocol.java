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
        
        //Используемые в документе логины для Альфы/Гаммы
        /*String LoginAuthor = "IGOV_270907SVK";
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
        String Login6 = "IGOV_230878LIV";
        String Name6 = "Літовченко Інна Вадимівна";
        String Login7 = "IGOV_151071GUO";
        String Name7 = "Гуков Юрій Олександрович";
        String Login8 = "IGOV_301082BOY";
        String Name8 = "Бондарь Ольга Євгенієвна";
        String Login9 = "IGOV_310780BVV";
        String Name9 = "Белявцев Володимир Володимирович";*/
        
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
        String Login8 = "IGOV_200687TOV";
        String Name8 = "Туренко Ольга Володимирівна";
        String Login9 = "IGOV_220290PUU";
        String Name9 = "Павленко Юлія Юріївна";


        openURLdashboard(getRegionUrl());
        
        //----Создание---- 
        //Авторизация
        login(LoginAuthor, " ");
        navigateToggleMenu();
        createDocumentOrTask("Протокол оперативної наради");
        clickButton("Далi");
        pause(5000); 
        setDocTitle("Автотест протокола"); 
        setDocContent("Автотест протокола");
        SetRegionFieldInputTypeDate("sDateMeeting", "19.03.2018");
        SetRegionFieldInputTypeLong("sAmtDecisions", "2");
        setSelect("_doc_btsol_protocol_meeting", "sName_Chairperson", NameAuthor);
        setParticipant(sBP, "sTable_listener", "sName_isExecute", "0", Name1);
        addRegionsTableRow("sTable_listener");
        setParticipant(sBP, "sTable_listener", "sName_isExecute", "1", NameAuthor);
        setTaskContent("Запустить автотест протокола");
        setRegionTableCellsInputTypeString("sNumber", "0", "1");
        setRegionTableCellsInputTypeString("sNameFile", "0", "Тестовий додаток");
        setRegionTableCellsInputTypeFile(sBP,"sTableFile", "sFile", "0", "src/test/resources/files/test.jpg");
              
        /*Таблица Узгоджуючі*/
        setAcceptor(sBP, "sTableAccept", "sName_Acceptor", "0", Name1);
        /*addRegionsTableRow("sTableAccept");
        setAcceptor(sBP, "sTableAccept", "sName_Acceptor", "1", "Грек Одарка Олексіївна");
        addRegionsTableRow("sTableAccept");
        setAcceptor(sBP, "sTableAccept", "sName_Acceptor", "2", "Столбова Анна Юріївна");*/
        
        /*Таблица Затверджуючий*/
        setApprover(sBP, "sTableAgree", "sName_Approver", "0", Name2);
        
        //Додати завдання
        addTask();
        setTaskName(generateText(10));
        setTaskTerm("Кiлькiсть днiв пiсля", "3");
        setTaskForm("Текстове повiдомлення");
        SetRegionFieldInputTypeLong("sAmtDecisions", "3");
        setController(NameAuthor);
        setExecutor(Name4);
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
        pause(5000); 
        isError();
        logout();       

            //----Подписываем (Согласование)----
            
        //Первый подписант с таблицы (На просмотр + Делегирование)
        login(Login1, " ");
        setRegionFindOrderByNumberDocument();
        pause(5000);
        scrollTo("Затвердження");
        clickButton("Інші дії");
        addViewer(Name9);
        pause(2000);
        clickButton("Інші дії");
        clickButton("Делегувати");
        addDelegate(Name6);
        isError();
        pause(2000);
        logout();
        
        //Подписант с делегирования
        login(Login6, " ");
        setRegionFindOrderByNumberDocument();
        pause(2000);
        //Проверка наличия кнопок
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

        //clickButton("Інші дії");
        addAcceptor(Name7);
        
        removeParticipant(1, true);
        pause(5000);
        
        clickButton("Інші дії");        
        addAcceptor(Name7);
        clickButtonSignNotNeed(sBP, "Подпись не нужна");
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

        //Третий подписант с (Додати підписанта)
        login(Login7, " ");
        setRegionFindOrderByNumberDocument();
        pause(2000);
        clickButtonSign();
        pause(5000);
        logout();
        //Проверка снятия подписи
        login(Login7, " ");
        setRegionFindOrderByNumberDocument();
        pause(2000);
        cancelSign("Перевірка зняття підпису");
        pause(5000);
        clickButtonSign();
        pause(5000);
        logout();                  
        
                //-----Редактирование-----
        //Автору, ответить на замечание и редактировать
        //Проверка наличия кнопок
        login(LoginAuthor, " ");
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
        clickButton("Інші дії");
        clickButton("Редагувати завдання");
        SetRegionFieldInputTypeLong("sAmtDecisions", "5");      
        clickButtonSign();
        pause(5000);
        logout();
        
        
        //Подписи исполнителей задачи (Согласование)
        login(Login4, " ");
        setRegionFindOrderByNumberDocument();  
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
        login(Login2, " ");
        setRegionFindOrderByNumberDocument();
        pause(2000);
        clickButton("Інші дії");
        addVisor(Name7);
        clickButtonSign();
        pause(5000);
        logout();
        
        //Ознакомление
        login(Login7, " ");
        pause(5000);
        setRegionFindOrderByNumberDocument();
        pause(5000);
        clickButton("Ознайомлений");
        pause(5000);
        logout();
        
        //Ознакомление Автором
        login(LoginAuthor, " ");
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
        isExistButton("Ознайомити",true);
        isExistButton("Видалити",false);
        isExistButton("Додати завдання",false);
        isExistButton("Редагувати завдання",false);
        isExistButton("Зауваження",false);
        isExistButton("Додати підписанта",false);
        isExistButton("Делегувати",false);
        isExistButton("Відмовити",false);
        clickButton("Ознайомлений");
        pause(5000);
        logout();
        
            //-----Работа с задачей------
        //Первый исполнитель
        login(Login4, " ");
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
        login(LoginAuthor, " ");
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
        