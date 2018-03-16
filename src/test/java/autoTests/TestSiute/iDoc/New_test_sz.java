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
//        templateSelect("Тестування службової записки");
        clickButton("Далi");
        pause(5000);
        
        //SetRegionFieldInputTypeString("sVarString", "Тип даних string");
        setDocContent("Текст службової записки");
        //pause(5000);

        setRegionTableCellsInputTypeString("sNumber", "0", "1");
        setRegionTableCellsInputTypeString("sNameFile", "0", "Тестовий додаток");
        setRegionTableCellsInputTypeFile(sBP,"sTableFile", "sFile", "0", "src/test/resources/files/test.jpg");

        /*Таблица Узгоджуючі*/
        setAcceptor(sBP, "sTableAccept", "sName_Acceptor", "0", "Туренко Ольга Володимирівна");
        addRegionsTableRow("sTableAccept");
        setAcceptor(sBP, "sTableAccept", "sName_Acceptor", "1", "Грек Одарка Олексіївна");
        //addRegionsTableRow("sTableAccept");
        //setAcceptor(sBP, "sTableAccept", "sName_Acceptor", "2", "Павленко Юлія Юріївна");

        /*Таблица Затверджуючий*/
        setApprover(sBP, "sTableAgree", "sName_Approver", "0", "Герман Август Васильович");

        /*Таблица Адресат*/
        setDirect(sBP, "sTableDirect", "sName_Direct", "0", "Столбова Анна Юріївна");
        addRegionsTableRow("sTableDirect");
        setDirect(sBP, "sTableDirect", "sName_Direct", "1", "Літовченко Інна Вадимівна");

        getOrderFromUrlCurrentPage();
        clickButtonCreate();
        pause(5000);
        clickButton("Ok");
        clickLink("Смоктій Вікторія Кирилівна");
        clickLink("Вийти");
        
        /*2 Делегируем согласование первым подписантом на другого сотрудника*/
        AuthorizationBySetLoginPassword("IGOV_200687TOV", " ");
        clickButton("Увійти");
        pause(5000);
        setRegionFindOrderByNumberDocument();  
        pause(5000);
        clickButton("Інші дії");
        clickButton("Делегувати");
        //pause(5000);
        addDelegate("Гуков Юрій Олександрович");
        //pause(5000);
        clickLink("Туренко Ольга Володимирівна");
        clickLink("Вийти");

        /*3. Работа на этапе согласования (1 пользователь). Подписываем*/
        AuthorizationBySetLoginPassword("IGOV_151071GUO", " ");
        clickButton("Увійти");
        pause(5000);
        setRegionFindOrderByNumberDocument();
        pause(5000);
        clickButtonSign();
        clickLink("Гуков Юрій Олександрович");
        clickLink("Вийти");
        
        /*4 Подписываем и снимаем подпись
        AuthorizationBySetLoginPassword("IGOV_220290PUU", " ");
        clickButton("Увійти");
        setRegionFindOrderByNumberDocument();
        clickButtonSign();
        pause(5000);
        setRegionFindOrderByNumberDocument();
        pause(5000);
        cancelSign("Перевірка зняття підпису");
        clickLink("Павленко Юлія Юріївна");
        clickLink("Вийти");*/
        
        /*5. Работа на этапе согласования (2 пользователь). Добавляем подписанта и вібираем “Підпис не потрібен”*/
        AuthorizationBySetLoginPassword("IGOV_130384GOA", " ");
        clickButton("Увійти");
        pause(5000);
        setRegionFindOrderByNumberDocument();
        pause(5000);
        clickButton("Інші дії");
        clickButton("Додати підписанта");
        addAcceptor("Столбова Анна Юріївна");
        clickButton("Інші дії");
        clickButtonSignNotNeed(sBP, "коментар 1");
        clickButton("Ok");
        clickLink("Грек Одарка Олексіївна");
        clickLink("Вийти");
        
        /*6. Работа на этапе согласования (3 пользователь). Подписываем*/
        AuthorizationBySetLoginPassword("IGOV_260185SAU", " ");
        clickButton("Увійти");
        setRegionFindOrderByNumberDocument();
        clickButtonSign();
        clickLink("Столбова Анна Юріївна");
        clickLink("Вийти");

        /*7. Удаляем подписанта который снял подпись
        AuthorizationBySetLoginPassword("IGOV_270907SVK", " ");
        clickButton("Увійти");
        setRegionFindOrderByNumberDocument();
        clickButton("Редагувати");
        pause(5000);
        removeRowFromTable("sTableAccept", 2);
        clickButtonSign();
        clickLink("Смоктій Вікторія Кирилівна");
        clickLink("Вийти");*/

        /*8. Работа на этапе утверждения. Добавляем замечания и нового подписанта*/
        AuthorizationBySetLoginPassword("IGOV_110771GAV", " ");
        clickButton("Увійти");
        setRegionFindOrderByNumberDocument();
        clickButton("Інші дії");
        clickButton("Додати підписанта");
        addAcceptor("Грек Одарка Олексіївна");
        addComment("Тестове зауваження");
        clickButton("Ok");
        clickButtonSign();
        clickLink("Герман Август Васильович");
        clickLink("Вийти");

        /*9. Отвечаем на зауваження, снимаем подписи и возвращаем на согласование*/
        AuthorizationBySetLoginPassword("IGOV_270907SVK", " ");
        clickButton("Увійти");
        setRegionFindOrderByNumberDocument();
        clickButton("Редагувати");
        pause(5000);
        removeAllSigns();
        setFieldCheckBox("eReturnDocument");
        answerComment("Відповідь на зауваження");
        clickButton("Ok");
        clickButtonSign();
        clickLink("Смоктій Вікторія Кирилівна");
        clickLink("Вийти");
        
        /*10. Работа на этапе согласования (1 пользователь). Подписываем
        AuthorizationBySetLoginPassword("IGOV_151071GUO", " ");
        clickButton("Увійти");
        pause(5000);
        setRegionFindOrderByNumberDocument();
        clickButtonSign();
        clickLink("Гуков Юрій Олександрович");
        clickLink("Вийти");*/
        
        /*11. Работа на этапе согласования (2 пользователь). Подписываем”*/
        AuthorizationBySetLoginPassword("IGOV_130384GOA", " ");
        clickButton("Увійти");
        setRegionFindOrderByNumberDocument();
        pause(5000);
        clickButtonSign();
        clickLink("Грек Одарка Олексіївна");
        clickLink("Вийти");

        /*12. Работа на этапе согласования (3 пользователь). Подписываем
        AuthorizationBySetLoginPassword("IGOV_260185SAU", " ");
        clickButton("Увійти");
        setRegionFindOrderByNumberDocument();
        clickButtonSign();
        clickLink("Столбова Анна Юріївна");
        clickLink("Вийти");*/

        /*13. Работа на этапе утверждения. Подписываем*/
        AuthorizationBySetLoginPassword("IGOV_110771GAV", " ");
        clickButton("Увійти");
        setRegionFindOrderByNumberDocument();
        clickButtonSign();
        clickLink("Герман Август Васильович");
        clickLink("Вийти");

        /*14. Подписываем дополнительнымм утверждающим*/
        AuthorizationBySetLoginPassword("IGOV_130384GOA", " ");
        clickButton("Увійти");
        setRegionFindOrderByNumberDocument();
        clickButtonSign();
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
        pause(5000);
        setTaskContent("Перевірка завдання автотестом");
        clickButtonSign();
        clickLink("Столбова Анна Юріївна");
        clickLink("Вийти");

        /*14. Заходим адресатом 2.*/
        AuthorizationBySetLoginPassword("IGOV_230878LIV", " ");
        clickButton("Увійти");
        setRegionFindOrderByNumberDocument();
        clickButtonSign();
        clickLink("Літовченко Інна Вадимівна");
        clickLink("Вийти");

        /*15. Заходим исполнителем 1. Обработка задания. Добавляем отчет 1*/
        AuthorizationBySetLoginPassword("IGOV_270907SVK", " ");
        clickButton("Увійти");
        pause(5000);
        snapDrawerButtonMenuTabs("Завдання");
        clickLink("На виконанні");
        searchTaskByText(generateText);
        pause(5000);
        addReport("Виконане", "Завдання виконане");
        clickLink("Смоктій Вікторія Кирилівна");
        clickLink("Вийти");

        /*16. Заходим исполнителем 2. Обработка задания. Добавляем отчет 2*/
        AuthorizationBySetLoginPassword("IGOV_110771GAV", " ");
        clickButton("Увійти");
        snapDrawerButtonMenuTabs("Завдання");
        clickLink("На виконанні");
        searchTaskByText(generateText);
        addReport("Не актуальне", "Не хочу це робити");
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
        clickLink("Столбова Анна Юріївна");
        clickLink("Вийти");

        /*18. Заходим исполнителем. Подписіваем*/
        AuthorizationBySetLoginPassword("IGOV_270907SVK", " ");
        clickButton("Увійти");
        clickButton("Додати на перегляд");
        addViewer("Герман Август Васильович");
        addViewer("Павленко Юлія Юріївна");
        removeParticipant(2);
        setRegionFindOrderByNumberDocument();
        clickButton("Ознайомлений");
        clickLink("Смоктій Вікторія Кирилівна");
        clickLink("Вийти");
    }
    
    
}
