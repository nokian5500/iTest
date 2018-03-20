/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoTests.TestSiute.iDoc;

import autoTests.CustomMethods;
import static autoTests.CustomMethods.generateText;

import static com.codeborne.selenide.Selenide.open;
import org.junit.Ignore;

import org.junit.Test;
/**
 *
 * @author User
 */
public class Test_ManyVoids_SZ extends CustomMethods {
    
    String sBP = "_doc_btsol_vertical_sz";
    String email = "autotestbeta@gmail.com";
    
    @Test
    
    public void CreateDoc() throws Exception {
        /*1. Создание документа*/
        openURLdashboard(getRegionUrl());
        AuthorizationBySetLoginPassword("IGOV_270907SVK", " ");
        clickButton("Увійти");
        pause(2000);
        navigateToggleMenu();
        createDocumentOrTask("Службова записка");
        clickButton("Далi");
        setDocContent("Текст службової записки");
        
        /*Заполняем таблицу с приложениями*/
        setRegionTableCellsInputTypeString("sNumber", "0", "1");
        setRegionTableCellsInputTypeString("sNameFile", "0", "Тестовий додаток");
        setRegionTableCellsInputTypeFile(sBP,"sTableFile", "sFile", "0", "src/test/resources/files/test.jpg");

        /*Таблица согласующих*/
        setAcceptor(sBP, "sTableAccept", "sName_Acceptor", "0", "Туренко Ольга Володимирівна");
        addRegionsTableRow("sTableAccept");
        setAcceptor(sBP, "sTableAccept", "sName_Acceptor", "1", "Грек Одарка Олексіївна");
        addRegionsTableRow("sTableAccept");
        setAcceptor(sBP, "sTableAccept", "sName_Acceptor", "2", "Павленко Юлія Юріївна");

        /*Таблица утверждающих*/
        setApprover(sBP, "sTableAgree", "sName_Approver", "0", "Герман Август Васильович");

        /*Таблиця адресатов*/
        setDirect(sBP, "sTableDirect", "sName_Direct", "0", "Столбова Анна Юріївна");
        addRegionsTableRow("sTableDirect");
        setDirect(sBP, "sTableDirect", "sName_Direct", "1", "Літовченко Інна Вадимівна");

        getOrderFromUrlCurrentPage(); /*копируем sID_Order*/
        clickButtonCreate();
        clickButton("Ok");
        clickLink("Смоктій Вікторія Кирилівна");
        clickLink("Вийти");
    }
    @Test
    public void DelegeteDoc() throws Exception {
        /*2. Делегируем согласование первым подписантом на другого сотрудника*/
        openURLdashboard(getRegionUrl());
        AuthorizationBySetLoginPassword("IGOV_200687TOV", " ");
        clickButton("Увійти");
        pause(2000);
        setRegionFindOrderByNumberDocument();  
        clickButton("Інші дії");
        clickButton("Делегувати");
        addDelegate("Гуков Юрій Олександрович");
        clickLink("Туренко Ольга Володимирівна");
        clickLink("Вийти");
    }
    @Test
    public void FirstAccept() throws Exception {
        /*3. Работа на этапе согласования (1 пользователь). Подписываем*/
        openURLdashboard(getRegionUrl());
        AuthorizationBySetLoginPassword("IGOV_151071GUO", " ");
        clickButton("Увійти");
        pause(2000);
        setRegionFindOrderByNumberDocument();
        /*Проверяем наличие/отсутствие кнопок на шаге*/
        clickButton("Інші дії");
        isExistButton("Пiдписати",true);
        isExistButton("Зберегти",true);
        isExistButton("Роздрукувати",true);
        isExistButton("Зауваження",true);
        isExistButton("Інші дії",true);
        isExistButton("Додати на перегляд",true);
        isExistButton("Ознайомити",true);
        isExistButton("Додати підписанта",true);
        isExistButton("Делегувати",true);
        isExistButton("Відмовити",true);
        isExistButton("Підпис не потрібен",true);
        isExistButton("Додати завдання",false);
        isExistButton("Редагувати завдання",false);
        clickButtonSign();
        clickLink("Гуков Юрій Олександрович");
        clickLink("Вийти");
    }
    @Test
    public void CancelSign() throws Exception {    
        /*4. Подписываем и снимаем подпись*/
        openURLdashboard(getRegionUrl());
        AuthorizationBySetLoginPassword("IGOV_220290PUU", " ");
        clickButton("Увійти");
        pause(2000);
        setRegionFindOrderByNumberDocument();
        clickButtonSign();
        setRegionFindOrderByNumberDocument();
        cancelSign("Перевірка зняття підпису");
        clickLink("Павленко Юлія Юріївна");
        clickLink("Вийти");
    }
    @Test
    public void AddAcceptor_SignNotNeed() throws Exception {
        /*5. Работа на этапе согласования (2 пользователь). Добавляем подписанта и вібираем “Підпис не потрібен”*/
        openURLdashboard(getRegionUrl());
        AuthorizationBySetLoginPassword("IGOV_130384GOA", " ");
        clickButton("Увійти");
        pause(2000);
        setRegionFindOrderByNumberDocument();
        clickButton("Інші дії");
        addAcceptor("Столбова Анна Юріївна");
        clickButton("Інші дії");
        clickButtonSignNotNeed(sBP, "коментар 1");
        clickButton("Ok");
        clickLink("Грек Одарка Олексіївна");
        clickLink("Вийти");
    }
    @Test
    public void Accept_3_user() throws Exception {
        /*6. Работа на этапе согласования (3 пользователь). Подписываем*/
        openURLdashboard(getRegionUrl());
        AuthorizationBySetLoginPassword("IGOV_260185SAU", " ");
        clickButton("Увійти");
        pause(2000);
        setRegionFindOrderByNumberDocument();
        clickButtonSign();
        clickLink("Столбова Анна Юріївна");
        clickLink("Вийти");
    }   
    @Test
    public void AddNewApprover_and_AskQuestion() throws Exception {
        /*7. Работа на этапе утверждения. Добавляем замечания и нового подписанта*/
        openURLdashboard(getRegionUrl());
        AuthorizationBySetLoginPassword("IGOV_110771GAV", " ");
        clickButton("Увійти");
        pause(2000);
        setRegionFindOrderByNumberDocument();
        clickButton("Інші дії");
        addAcceptor("Грек Одарка Олексіївна");
        addComment("Тестове зауваження");
        clickButton("Ok");
        clickButtonSign();
        clickLink("Герман Август Васильович");
        clickLink("Вийти");
    }    
    @Test
    public void AuthorEdit() throws Exception {
        /*8. Отвечаем на зауваження, снимаем подписи и возвращаем на согласование*/
        openURLdashboard(getRegionUrl());
        AuthorizationBySetLoginPassword("IGOV_270907SVK", " ");
        clickButton("Увійти");
        pause(2000);
        setRegionFindOrderByNumberDocument();
        clickButton("Редагувати");
        removeAllSigns();
        setFieldCheckBox("eReturnDocument");
        answerComment("Відповідь на зауваження");
        clickButton("Ok");
        clickButtonSign();
        clickLink("Смоктій Вікторія Кирилівна");
        clickLink("Вийти");
    }
    @Test
    public void Accept_1_user() throws Exception {
        /*9. Работа на этапе согласования (1 пользователь). Подписываем*/
        openURLdashboard(getRegionUrl());
        AuthorizationBySetLoginPassword("IGOV_220290PUU", " ");
        clickButton("Увійти");
        pause(2000);
        setRegionFindOrderByNumberDocument();
        clickButtonSign();
        clickLink("Павленко Юлія Юріївна");
        clickLink("Вийти");
    }    
    @Test
    public void Accept_2_user() throws Exception {
        /*10. Работа на этапе согласования (2 пользователь). Подписываем”*/
        openURLdashboard(getRegionUrl());
        AuthorizationBySetLoginPassword("IGOV_130384GOA", " ");
        clickButton("Увійти");
        pause(2000);
        setRegionFindOrderByNumberDocument();
        clickButtonSign();
        clickLink("Грек Одарка Олексіївна");
        clickLink("Вийти");
    }    
    @Test
    public void Approve_1_user() throws Exception {
        /*11. Работа на этапе утверждения. Подписываем*/
        openURLdashboard(getRegionUrl());
        AuthorizationBySetLoginPassword("IGOV_110771GAV", " ");
        clickButton("Увійти");
        pause(2000);
        setRegionFindOrderByNumberDocument();
        clickButtonSign();
        clickLink("Герман Август Васильович");
        clickLink("Вийти");
    }
    @Test
    public void Approve_2_user() throws Exception {
        /*12. Подписываем дополнительнымм утверждающим*/
        openURLdashboard(getRegionUrl());
        AuthorizationBySetLoginPassword("IGOV_130384GOA", " ");
        clickButton("Увійти");
        pause(2000);
        setRegionFindOrderByNumberDocument();
        clickButtonSign();
        clickLink("Грек Одарка Олексіївна");
        clickLink("Вийти");
    }    
    @Test
    public void Direct_1_user_AddTask() throws Exception {
        /*13. Заходим адресатом 1. Добавляем задание 1*/
        openURLdashboard(getRegionUrl());
        AuthorizationBySetLoginPassword("IGOV_260185SAU", " ");
        clickButton("Увійти");
        pause(2000);
        setRegionFindOrderByNumberDocument();
        addTask();
        setTaskName(generateText(10));/*Генерируем название задания*/
        setTaskTerm("Кiлькiсть днiв пiсля", "5");
        setTaskForm("Текстове повiдомлення");
        //setTaskForm("Документ");
        //setTaskForm("Файл");
        setController("Столбова Анна Юріївна");
        setExecutor("Смоктій Вікторія Кирилівна");
        addNewExecutor("Герман Август Васильович");
        setTaskContent("Перевірка завдання автотестом");
        clickButtonSign();
        clickLink("Столбова Анна Юріївна");
        clickLink("Вийти");
    }    
    @Test
    public void Direct_2_user() throws Exception {
        /*14. Заходим адресатом 2.*/
        openURLdashboard(getRegionUrl());
        AuthorizationBySetLoginPassword("IGOV_230878LIV", " ");
        clickButton("Увійти");
        pause(2000);
        setRegionFindOrderByNumberDocument();
        clickButtonSign();
        clickLink("Літовченко Інна Вадимівна");
        clickLink("Вийти");
    }
    @Test
    public void Executor_1_user() throws Exception {
        /*15. Заходим исполнителем 1. Обработка задания. Добавляем отчет 1*/
        openURLdashboard(getRegionUrl());
        AuthorizationBySetLoginPassword("IGOV_270907SVK", " ");
        clickButton("Увійти");
        pause(2000);
        snapDrawerButtonMenuTabs("Завдання");
        clickLink("На виконанні");
        searchTaskByText(generateText);
        addReport("Виконане", "Завдання виконане");
        clickLink("Смоктій Вікторія Кирилівна");
        clickLink("Вийти");
    }    
    @Test
    public void Executor_2_user() throws Exception {
        /*16. Заходим исполнителем 2. Обработка задания. Добавляем отчет 2*/
        openURLdashboard(getRegionUrl());
        AuthorizationBySetLoginPassword("IGOV_110771GAV", " ");
        clickButton("Увійти");
        pause(2000);
        snapDrawerButtonMenuTabs("Завдання");
        clickLink("На виконанні");
        searchTaskByText(generateText);
        addReport("Не актуальне", "Не хочу це робити");
        clickLink("Герман Август Васильович");
        clickLink("Вийти");
    }    
    @Test
    public void Controller() throws Exception {
        /*17. Заходим контролирующим. Подтверждаем отчет*/
        openURLdashboard(getRegionUrl());
        AuthorizationBySetLoginPassword("IGOV_260185SAU", " ");
        clickButton("Увійти");
        pause(2000);
        snapDrawerButtonMenuTabs("Завдання");
        clickLink("На контролі");
        searchTaskByText(generateText);
        clickButton("Прийняти завдання");
        clickLink("Столбова Анна Юріївна");
        clickLink("Вийти");
    }    
    @Test
    public void Author_add_and_removeViwer() throws Exception {
        /*18. Заходим автором. Подписываем*/
        openURLdashboard(getRegionUrl());
        AuthorizationBySetLoginPassword("IGOV_270907SVK", " ");
        clickButton("Увійти");
        pause(2000);
        setRegionFindOrderByNumberDocument();
        clickButton("Ознайомлений");
        setRegionFindOrderByNumberDocument();
        clickButton("Інші дії");
        addViewer("Бондарь Ольга Євгенієвна");
        clickButton("Інші дії");
        addViewer("Павленко Юлія Юріївна");
        removeParticipant(2,true);
        clickLink("Смоктій Вікторія Кирилівна");
        clickLink("Вийти");
    }
    
    
}
