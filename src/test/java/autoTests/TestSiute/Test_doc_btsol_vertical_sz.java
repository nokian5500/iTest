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
 * @author User
 */
public class Test_doc_btsol_vertical_sz extends CustomMethods {
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
        pause(10000);
        
        //SetRegionFieldInputTypeString("sVarString", "Тип даних string");
        setDocContent("Текст службової записки");

        setRegionTableCellsInputTypeString("sNumber", "0", "100");
        setRegionTableCellsInputTypeString("sNameFile", "0", "name");
        setRegionTableCellsInputTypeFile(sBP,"sTableFile", "sFile", "0", "src/test/resources/files/test.jpg");

        /*Таблица Узгоджуючі*/
//        setRegionTableCellsInputTypeEnumInput(driver, sBP, "sTableAccept", "sName_Approver", "0", "Замдиректора Потапченко Василь");
        setAcceptor(sBP, "sTableAccept", "sName_Acceptor", "0", "Туренко Ольга Володимирівна");
        addRegionsTableRow("sTableAccept");
        setAcceptor(sBP, "sTableAccept", "sName_Acceptor", "1", "Грек Одарка Олексіївна");

        /*Таблица Затверджуючий*/
        setApprover(sBP, "sTableAgree", "sName_Approver", "0", "Герман Август Васильович");

        /*Таблица Адресат*/
        setDirect(sBP, "sTableDirect", "sName_Direct", "0", "Столбова Анна Юріївна");
        addRegionsTableRow("sTableDirect");
        setDirect(sBP, "sTableDirect", "sName_Direct", "1", "Літовченко Інна Вадимівна");

        getOrderFromUrlCurrentPage();
        clickButtonCreate();
        pause(5000);
        clickLink("Смоктій Вікторія Кирилівна");
        clickLink("Вийти");
        
        /*2 Делегируем согласование первым подписантом на другого сотрудника*/
        AuthorizationBySetLoginPassword("IGOV_200687TOV", " ");
        clickButton("Увійти");
        setRegionFindOrderByNumberDocument();  
        pause(5000);
        clickButton("Інші дії");
        clickButton("Делегувати");
        setRegionTableCellsInputTypeEnumInput(sBP, "sTableAccept", "sName_Acceptor", "0", "Гуков Юрій Олександрович");
        clickButton("Підтвердити");
        clickLink("Туренко Ольга Володимирівна");
        clickLink("Вийти");

        /*3. Работа на этапе согласования (1 пользователь). Подписываем*/
        AuthorizationBySetLoginPassword("IGOV_151071GUO", " ");
        clickButton("Увійти");
        setRegionFindOrderByNumberDocument();
        clickButton("Підписати");
        clickLink("Гуков Юрій Олександрович");
        clickLink("Вийти");
        
        /*4. Работа на этапе согласования (2 пользователь). Добавляем подписанта и вібираем “Підпис не потрібен”*/
        AuthorizationBySetLoginPassword("IGOV_130384GOA", " ");
        clickButton("Увійти");
        setRegionFindOrderByNumberDocument();
        clickButton("Інші дії");
        clickButton("Додати підписанта");
        setRegionTableCellsInputTypeEnumInput(sBP, "sTableAcceptor", "sName_Acceptor", "0", "секретар i1");
        clickButton("Підтвердити");
        clickButton("Ok");
        clickButton("Інші дії");
        clickButton("Підпис не потрібен");
        setFieldTextArea(sBP, "???", "коментар 1");
        clickButton("Підпис не потрібен");
        clickButton("Ok");

        /*5. Работа на этапе согласования (3 пользователь). Подписываем*/
        AuthorizationBySetLoginPassword("iTest_User_0004", "iTest_User_0004");
        clickButton("Увійти");
        clickLink("Нерозглянуті");
        clickButton("Підписати");

        /*6. Работа на этапе утверждения. Добавляем замечания и нового подписанта*/
        AuthorizationBySetLoginPassword("iTest_User_0002", "iTest_User_0002");
        clickButton("Увійти");
        setRegionFindOrder("");
        clickButton("Зауваження");
        SetRegionFieldInputTypeString("???", "Зауваження");
        clickButton("Выдправити зауваження");
        clickButton("Інші дії");
        clickLink("Додадати підписанта");
        SetRegionFieldInputTypeEnum("Директор Гнатушенко Тарас");
        clickButton("Підтвердити");
        clickButton("Ok");
        clickButton("Підписати");
        clickLink("Замдиректора Потапченко Василь");
        clickLink("Вийти");

        /*7. Отвечаем на зауваження*/
        AuthorizationBySetLoginPassword("iTest_User_0007", "iTest_User_0007");
        clickButton("Увійти");
        clickLink("Нерозглянутi");
        setRegionFindOrderByNumberDocument();
        clickLink("dialog"); //
        clickLink("Відповісти");
        SetRegionFieldInputTypeString("", " Ответ на замечание1");
        clickButton("Відповісти");
        clickButton("Ok");
        clickButton("Зберегти");
        clickButton("Ok");
        clickLink("Співробітник2 підрозділу 1.1");
        clickLink("Вийти");

        /*8. Подписываем дополнительно директором*/
        AuthorizationBySetLoginPassword("Test_User_0001", "Test_User_0001");
        clickButton("Увійти");
        clickLink("Нерозглянутi");
        setRegionFindOrderByNumberDocument();
        clickButton("Підписати");
        clickLink("Директор Гнатушенко Тарас");
        clickLink("Вийти");

        /*9. Заходим адресатом 1. Добавляем задание 1*/
        AuthorizationBySetLoginPassword("iTest_User_0011", "iTest_User_0011");
        clickButton("Увійти");
        clickLink("Нерозглянуті");
        setRegionFindOrderByNumberDocument();
        setRegionTableCellsInputTypeEnumInput(sBP, "sTableAcceptor", "sName_Acceptor", "1", "секретар І2");
        clickButton("Додати завдання");

        /*10. Заходим адресатом 2.*/
        AuthorizationBySetLoginPassword("iTest_User_0016", "iTest_User_0016");
        clickButton("Увійти");
        clickLink("Нерозглянуті");
        setRegionFindOrderByNumberDocument();
        clickButton("Підписати");
        clickLink("керівник підрозділу 2.2");
        clickLink("Вийти");

        /*11. Заходим исполнителем 1. Обработка задания. Добавляем отчет 1*/
        AuthorizationBySetLoginPassword("iTest_User_0014", "iTest_User_0014");
        clickButton("Увійти");
        snapDrawerButtonMenuTabs("Завдання");
        clickLink("Необроблені");
        setRegionFindOrder(sBP);
        clickButton("Додати звіт");
        SetRegionFieldInputTypeEnum("Виконано");
        SetRegionFieldInputTypeFile("src/test/resources/files/test.jpg");
        clickButton("Підтвердити");
        clickLink("Співробітник1 підрозділу 2.1");
        clickLink("Вийти");

        /*12. Заходим исполнителем 2. Обработка задания. Добавляем отчет 2*/
        AuthorizationBySetLoginPassword("iTest_User_0015", "iTest_User_0015");
        clickButton("Увійти");
        snapDrawerButtonMenuTabs("Завдання");
        clickLink("Необроблені");
        setRegionFindOrder(sBP);
        clickButton("Додати звіт");
        SetRegionFieldInputTypeEnum("неактуально");
        SetRegionFieldInputTypeTextArea("", "Звіт не актуальний");
        clickButton("Підтвердити");
        clickLink("Співробітник2 підрозділу 2.1");
        clickLink("Вийти");

        /*13. Заходим контролирующим. Подтверждаем отчет*/
        AuthorizationBySetLoginPassword("iTest_User_0013", "iTest_User_0013");
        clickButton("Увійти");
        snapDrawerButtonMenuTabs("Завдання");
        clickLink("На контролі");
        setRegionFindOrder(sBP);
        clickButton("Прийняти завдання");
        clickLink("керівник підрозділу 2.1");
        clickLink("Вийти");

        /*14. Заходим исполнителем. Подписіваем*/
        AuthorizationBySetLoginPassword("iTest_User_0012 ", "iTest_User_0012 ");
        clickButton("Увійти");
        clickLink("Нерозглянуті");
        setRegionFindOrderByNumberDocument();
        clickButton("Підписати");
        clickLink("секретар І2");
        clickLink("Вийти");
    }
}
