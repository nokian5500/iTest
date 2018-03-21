/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoTests.TestSiute.iDoc;

import autoTests.CustomMethods;

import static com.codeborne.selenide.Selenide.open;

import org.junit.Test;
/**
 *
 * @author User
 */
public class New_test_sz extends CustomMethods {
    @Test
    public void Test_doc_btsol_vertical_sz() throws Exception {
        String sBP = "_doc_btsol_vertical_sz";
        String email = "autotestbeta@gmail.com";

        openURLdashboard(getRegionUrl());

        AuthorizationBySetLoginPassword("IGOV_270907SVK", " ");
        clickButton("Увійти");
        navigateToggleMenu();
        createDocumentOrTask("Службова записка");
        clickButton("Далi");
        
        setDocContent("Текст службової записки");

        setRegionTableCellsInputTypeString("sNumber", "0", "1");
        setRegionTableCellsInputTypeString("sNameFile", "0", "Тестовий додаток");
        setRegionTableCellsInputTypeFile(sBP,"sTableFile", "sFile", "0", "src/test/resources/files/test.jpg");

        /*Таблица Узгоджуючі*/
        setAcceptor(sBP, "sTableAccept", "sName_Acceptor", "0", "Туренко Ольга Володимирівна");
        addRegionsTableRow("sTableAccept");
        setAcceptor(sBP, "sTableAccept", "sName_Acceptor", "1", "Грек Одарка Олексіївна");
        addRegionsTableRow("sTableAccept");
        setAcceptor(sBP, "sTableAccept", "sName_Acceptor", "2", "Павленко Юлія Юріївна");

        /*Таблица Затверджуючий*/
        setApprover(sBP, "sTableAgree", "sName_Approver", "0", "Герман Август Васильович");

        /*Таблица Адресат*/
        setDirect(sBP, "sTableDirect", "sName_Direct", "0", "Столбова Анна Юріївна");
        addRegionsTableRow("sTableDirect");
        setDirect(sBP, "sTableDirect", "sName_Direct", "1", "Літовченко Інна Вадимівна");

        getOrderFromUrlCurrentPage();
        clickButtonCreate();
        clickButton("Ok");
        isError();
        clickLink("Смоктій Вікторія Кирилівна");
        clickLink("Вийти");
        
        /*2 Делегируем согласование первым подписантом на другого сотрудника*/
        AuthorizationBySetLoginPassword("IGOV_200687TOV", " ");
        clickButton("Увійти");
        setRegionFindOrderByNumberDocument();  
        clickButton("Інші дії");
        clickButton("Делегувати");
        addDelegate("Гуков Юрій Олександрович");
        isError();
        clickLink("Туренко Ольга Володимирівна");
        clickLink("Вийти");

        /*3. Работа на этапе согласования (1 пользователь). Подписываем*/
        AuthorizationBySetLoginPassword("IGOV_151071GUO", " ");
        clickButton("Увійти");
        setRegionFindOrderByNumberDocument();
        isExistButton("Пiдписати",true);
        isExistButton("Зберегти",true);
        isExistButton("Роздрукувати",true);
        isExistButton("Зауваження",true);
        isExistButton("Інші дії",true);
        clickButton("Інші дії");
        isExistButton("Додати на перегляд",true);
        isExistButton("Ознайомити",true);
        isExistButton("Додати підписанта",true);
        isExistButton("Делегувати",true);
        isExistButton("Відмовити",true);
        isExistButton("Підпис не потрібен",true);
        isExistButton("Додати завдання",false);
        isExistButton("Редагувати завдання",false);
        clickButtonSign();
        isError();
        clickLink("Гуков Юрій Олександрович");
        clickLink("Вийти");
        
        /*4 Подписываем и снимаем подпись*/
        AuthorizationBySetLoginPassword("IGOV_220290PUU", " ");
        clickButton("Увійти");
        setRegionFindOrderByNumberDocument();
        clickButtonSign();
        setRegionFindOrderByNumberDocument();
        cancelSign("Перевірка зняття підпису");
        clickLink("Павленко Юлія Юріївна");
        clickLink("Вийти");
        
        /*5. Работа на этапе согласования (2 пользователь). Добавляем подписанта и вібираем “Підпис не потрібен”*/
        AuthorizationBySetLoginPassword("IGOV_130384GOA", " ");
        clickButton("Увійти");
        setRegionFindOrderByNumberDocument();
        clickButton("Інші дії");
        addAcceptor("Столбова Анна Юріївна");
        clickButtonSignNotNeed(sBP, "коментар 1");
        isError();
        clickLink("Грек Одарка Олексіївна");
        clickLink("Вийти");
        
        /*6. Работа на этапе согласования (3 пользователь). Подписываем*/
        AuthorizationBySetLoginPassword("IGOV_260185SAU", " ");
        clickButton("Увійти");
        setRegionFindOrderByNumberDocument();
        clickButtonSign();
        isError();
        clickLink("Столбова Анна Юріївна");
        clickLink("Вийти");

        /*7. Работа на этапе утверждения. Добавляем замечания и нового подписанта*/
        AuthorizationBySetLoginPassword("IGOV_110771GAV", " ");
        clickButton("Увійти");
        setRegionFindOrderByNumberDocument();
        clickButton("Інші дії");
        addAcceptor("Грек Одарка Олексіївна");
        addComment("Тестове зауваження");
        clickButton("Ok");
        clickButtonSign();
        isError();
        clickLink("Герман Август Васильович");
        clickLink("Вийти");

        /*8. Отвечаем на зауваження, снимаем подписи и возвращаем на согласование*/
        AuthorizationBySetLoginPassword("IGOV_270907SVK", " ");
        clickButton("Увійти");
        setRegionFindOrderByNumberDocument();
        clickButton("Редагувати");
        removeAllSigns();
        setFieldCheckBox("eReturnDocument");
        answerComment("Відповідь на зауваження");
        clickButton("Ok");
        clickButtonSign();
        isError();
        clickLink("Смоктій Вікторія Кирилівна");
        clickLink("Вийти");

        /*9. Работа на этапе согласования (1 пользователь). Подписываем”*/
        AuthorizationBySetLoginPassword("IGOV_220290PUU", " ");
        clickButton("Увійти");
        setRegionFindOrderByNumberDocument();
        clickButtonSign();
        isError();
        clickLink("Павленко Юлія Юріївна");
        clickLink("Вийти");
        
        /*10. Работа на этапе согласования (2 пользователь). Подписываем”*/
        AuthorizationBySetLoginPassword("IGOV_130384GOA", " ");
        clickButton("Увійти");
        setRegionFindOrderByNumberDocument();
        clickButtonSign();
        isError();
        clickLink("Грек Одарка Олексіївна");
        clickLink("Вийти");

        /*11. Работа на этапе утверждения (1 пользователь. Подписываем*/
        AuthorizationBySetLoginPassword("IGOV_110771GAV", " ");
        clickButton("Увійти");
        setRegionFindOrderByNumberDocument();
        clickButtonSign();
        isError();
        clickLink("Герман Август Васильович");
        clickLink("Вийти");

        /*12. Подписываем дополнительнымм утверждающим (2 пользователь). Подписываем*/
        AuthorizationBySetLoginPassword("IGOV_130384GOA", " ");
        clickButton("Увійти");
        setRegionFindOrderByNumberDocument();
        clickButtonSign();
        isError();
        clickLink("Грек Одарка Олексіївна");
        clickLink("Вийти");

        /*13. Заходим адресатом 1. Добавляем задание 1*/
        AuthorizationBySetLoginPassword("IGOV_260185SAU", " ");
        clickButton("Увійти");
        setRegionFindOrderByNumberDocument();
        addTask();
        setTaskName(generateText(10));
        setTaskTerm("Кiлькiсть днiв пiсля", "5");
        setTaskForm("Текстове повiдомлення");
        //setTaskForm("Документ");
        //setTaskForm("Файл");
        setController("Столбова Анна Юріївна");
        setExecutor("Смоктій Вікторія Кирилівна");
        addNewExecutor("Герман Август Васильович");
        setTaskContent("Перевірка завдання автотестом");
        clickButtonSign();
        isError();
        clickLink("Столбова Анна Юріївна");
        clickLink("Вийти");

        /*14. Заходим адресатом 2.*/
        AuthorizationBySetLoginPassword("IGOV_230878LIV", " ");
        clickButton("Увійти");
        setRegionFindOrderByNumberDocument();
        clickButtonSign();
        isError();
        clickLink("Літовченко Інна Вадимівна");
        clickLink("Вийти");

        /*15. Заходим исполнителем 1. Обработка задания. Добавляем отчет 1*/
        AuthorizationBySetLoginPassword("IGOV_270907SVK", " ");
        clickButton("Увійти");
        snapDrawerButtonMenuTabs("Завдання");
        clickLink("На виконанні");
        searchTaskByText(generateText);
        addReport("Виконане", "Завдання виконане");
        isError();
        clickLink("Смоктій Вікторія Кирилівна");
        clickLink("Вийти");

        /*16. Заходим исполнителем 2. Обработка задания. Добавляем отчет 2*/
        AuthorizationBySetLoginPassword("IGOV_110771GAV", " ");
        clickButton("Увійти");
        snapDrawerButtonMenuTabs("Завдання");
        clickLink("На виконанні");
        searchTaskByText(generateText);
        addReport("Не актуальне", "Не хочу це робити");
        isError();
        //clickButton("Ok");
        clickLink("Герман Август Васильович");
        clickLink("Вийти");

        /*17. Заходим контролирующим. Подтверждаем отчет*/
        AuthorizationBySetLoginPassword("IGOV_260185SAU", " ");
        clickButton("Увійти");
        snapDrawerButtonMenuTabs("Завдання");
        clickLink("На контролі");
        searchTaskByText(generateText);
        clickButton("Прийняти завдання");
        isError();
        clickLink("Столбова Анна Юріївна");
        clickLink("Вийти");

        /*18. Заходим исполнителем. Подписіваем*/
        AuthorizationBySetLoginPassword("IGOV_270907SVK", " ");
        clickButton("Увійти");
        setRegionFindOrderByNumberDocument();
        clickButton("Ознайомлений");
        setRegionFindOrderByNumberDocument();
        clickButton("Інші дії");
        addViewer("Бондарь Ольга Євгенієвна");
        clickButton("Інші дії");
        addViewer("Павленко Юлія Юріївна");
        removeParticipant(2,true);
        isError();
        clickLink("Смоктій Вікторія Кирилівна");
        clickLink("Вийти");
    }
    
    
}
