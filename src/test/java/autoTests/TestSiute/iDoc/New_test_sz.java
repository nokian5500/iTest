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

        login("IGOV_270907SVK", " ");
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
        isError();
        logout();
        
        /*2 Делегируем согласование первым подписантом на другого сотрудника*/
        login("IGOV_200687TOV", " ");
        setRegionFindOrderByNumberDocument();
        pause(2000);
        clickButton("Інші дії");
        clickButton("Делегувати");
        addDelegate("Смоктій Оксана Данилівна");
        isError();
        pause(2000);
        logout();

        /*3. Работа на этапе согласования (1 пользователь). Подписываем*/
        login("IGOV_160582SOD", " ");
        setRegionFindOrderByNumberDocument();
        pause(2000);
        /*Наличие кнопок*/
        clickButton("Інші дії");
        isExistButton("Пiдписати",true);
        isExistButton("Зберегти",true);
        isExistButton("Роздрукувати",true);
        isExistButton("Зауваження",true);
        isExistButton("Додати на перегляд",true);
        isExistButton("Ознайомити",true);
        isExistButton("Додати підписанта",true);
        isExistButton("Делегувати",true);
        isExistButton("Відмовити",true);
        isExistButton("Підпис не потрібен",true);
        isExistButton("Коментар",true);
        /*Отсутствие кнопок*/
        isExistButton("Ознайомлен",false);
        isExistButton("Додати завдання",false);
        isExistButton("Редагувати завдання",false);
        isExistButton("Видалити",false);
        clickButtonSign();
        isError();
        pause(2000);
        logout();
        
        /*4 Подписываем и снимаем подпись*/
        login("IGOV_220290PUU", " ");
        setRegionFindOrderByNumberDocument();
        pause(2000);
        clickButtonSign();
        setRegionFindOrderByNumberDocument();
        cancelSign("Перевірка зняття підпису");
        isError();
        pause(2000);
        logout();
        
        /*5. Работа на этапе согласования (2 пользователь). Добавляем подписанта и вібираем “Підпис не потрібен”*/
        login("IGOV_130384GOA", " ");
        setRegionFindOrderByNumberDocument();
        pause(2000);
        clickButton("Інші дії");
        addAcceptor("Столбова Анна Юріївна");
        clickButtonSignNotNeed(sBP, "коментар 1");
        isError();
        pause(2000);
        logout();
        
        /*6. Работа на этапе согласования (3 пользователь). Подписываем*/
        login("IGOV_260185SAU", " ");
        setRegionFindOrderByNumberDocument();
        pause(2000);
        clickButtonSign();
        isError();
        pause(2000);
        logout();

        /*7 Подписываем и снимаем подпись*/
        login("IGOV_220290PUU", " ");
        setRegionFindOrderByNumberDocument();
        pause(2000);
        clickButtonSign();
        isError();
        pause(2000);
        logout();

        /*8. Работа на этапе утверждения. Добавляем замечания и нового подписанта*/
        login("IGOV_110771GAV", " ");
        setRegionFindOrderByNumberDocument();
        pause(2000);
        clickButton("Інші дії");
        addAcceptor("Грек Одарка Олексіївна");
        addComment("Тестове зауваження");
        //clickButtonSign();
        isError();
        pause(2000);
        logout();

        /*9. Отвечаем на зауваження, снимаем подписи и возвращаем на согласование*/
        login("IGOV_270907SVK", " ");
        setRegionFindOrderByNumberDocument();
        pause(2000);
        clickButton("Редагувати");
        clickButton("Інші дії");
        /*Наличие кнопок*/
        isExistButton("Пiдписати",true);
        isExistButton("Зберегти",true);
        isExistButton("Роздрукувати",true);
        isExistButton("Коментар",true);
        isExistButton("Видалити",true);
        isExistButton("Додати на перегляд",true);
        /*Отсутствие кнопок*/
        isExistButton("Зауваження",false);
        isExistButton("Ознайомити",false);
        isExistButton("Додати підписанта",false);
        isExistButton("Делегувати",false);
        isExistButton("Відмовити",false);
        isExistButton("Підпис не потрібен",false);
        isExistButton("Ознайомлен",false);
        isExistButton("Додати завдання",false);
        isExistButton("Редагувати завдання",false);
        
        removeAllSigns();
        setFieldCheckBox("eReturnDocument");
        //answerComment("Відповідь на зауваження");
        //clickButton("Ok");
        clickButtonSign();
        isError();
        pause(2000);
        logout();

        /*10. Работа на этапе согласования (1 пользователь). Подписываем”*/
        login("IGOV_220290PUU", " ");
        setRegionFindOrderByNumberDocument();
        pause(2000);
        clickButtonSign();
        isError();
        pause(2000);
        logout();
        
        /*11. Работа на этапе согласования (2 пользователь). Подписываем”
        login("IGOV_130384GOA", " ");
        setRegionFindOrderByNumberDocument();
        pause(2000);
        clickButtonSign();
        isError();
        pause(2000);
        clickLink("Грек Одарка Олексіївна");
        clickLink("Вийти");*/

        /*12. Работа на этапе утверждения (1 пользователь. Подписываем*/
        login("IGOV_110771GAV", " ");
        setRegionFindOrderByNumberDocument();
        pause(2000);
        clickButton("Інші дії");
        /*Наличие кнопок*/
        isExistButton("Пiдписати",true);
        isExistButton("Зберегти",true);
        isExistButton("Роздрукувати",true);
        isExistButton("Зауваження",true);
        isExistButton("Додати на перегляд",true);
        isExistButton("Ознайомити",true);
        isExistButton("Додати підписанта",true);
        isExistButton("Делегувати",true);
        isExistButton("Відмовити",true);
        isExistButton("Коментар",true);
        /*Отсутствие кнопок*/
        isExistButton("Ознайомлен",false);
        isExistButton("Підпис не потрібен",false);
        isExistButton("Додати завдання",false);
        isExistButton("Редагувати завдання",false);
        isExistButton("Видалити",false);
        clickButtonSign();
        isError();
        pause(2000);
        logout();

        /*13. Подписываем дополнительнымм утверждающим (2 пользователь). Подписываем*/
        login("IGOV_130384GOA", " ");
        setRegionFindOrderByNumberDocument();
        pause(2000);
        clickButtonSign();
        isError();
        pause(2000);
        logout();

        /*14. Заходим адресатом 1. Добавляем задание 1*/
        login("IGOV_260185SAU", " ");
        setRegionFindOrderByNumberDocument();
        pause(2000);
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
        pause(2000);
        logout();

        /*15. Заходим адресатом 2.*/
        login("IGOV_230878LIV", " ");
        setRegionFindOrderByNumberDocument();
        pause(2000);
        clickButton("Інші дії");
        /*Наличие кнопок*/
        isExistButton("Пiдписати",true);
        isExistButton("Зберегти",true);
        isExistButton("Роздрукувати",true);
        isExistButton("Зауваження",true);
        isExistButton("Додати на перегляд",true);
        isExistButton("Ознайомити",true);
        isExistButton("Ознайомлен",true);
        isExistButton("Делегувати",true);
        isExistButton("Відмовити",true);
        isExistButton("Коментар",true);
        isExistButton("Підпис не потрібен",true);
        isExistButton("Додати завдання",true);
        /*Отсутствие кнопок*/
        isExistButton("Додати підписанта",false);
        isExistButton("Редагувати завдання",false);
        isExistButton("Видалити",false);
        clickButtonSign();
        isError();
        pause(2000);
        logout();

        /*16. Заходим исполнителем 1. Обработка задания. Добавляем отчет 1*/
        login("IGOV_270907SVK", " ");
        snapDrawerButtonMenuTabs("Завдання");
        clickLink("На виконанні");
        searchTaskByText(generateText);
        /*Наличие кнопок*/
        clickButton("Інші дії");
        isExistButton("Відкрити документ",true);
        isExistButton("Перенести",true);
        isExistButton("Додати звiт",true);
        isExistButton("Делегувати",true);
        isExistButton("Коментар",true);
        isExistButton("Редагувати завдання",true);
        addReport("Виконане", "Завдання виконане");
        isError();
        pause(2000);
        logout();

        /*17. Заходим исполнителем 2. Обработка задания. Добавляем отчет 2*/
        login("IGOV_110771GAV", " ");
        snapDrawerButtonMenuTabs("Завдання");
        clickLink("На виконанні");
        searchTaskByText(generateText);
        addReport("Не актуальне", "Не хочу це робити");
        isError();
        pause(2000);
        logout();

        /*18. Заходим контролирующим. Подтверждаем отчет*/
        login("IGOV_260185SAU", " ");
        snapDrawerButtonMenuTabs("Завдання");
        clickLink("На контролі");
        searchTaskByText(generateText);
        clickButton("Прийняти завдання");
        isError();
        pause(2000);
        logout();

        /*19. Заходим автором. Подписываем*/
        login("IGOV_270907SVK", " ");
        setRegionFindOrderByNumberDocument();
        clickButton("Ознайомлений");
        setRegionFindOrderByNumberDocument();
        clickButton("Інші дії");
        addViewer("Бондарь Ольга Євгенієвна");
        clickButton("Інші дії");
        addViewer("Белявцев Володимир Володимирович");
        removeParticipant(2,true);
        isError();
        pause(2000);
        logout();
    }
    
    
}
