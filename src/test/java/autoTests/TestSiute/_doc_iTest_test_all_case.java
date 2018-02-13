/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoTests.TestSiute;

import autoTests.CustomMethods;
import org.junit.Test;

/**
 *
 * @author User
 */
public class _doc_iTest_test_all_case extends CustomMethods {

    @Test
    public void Test_doc_iTest_test_all_case() throws Exception {
        String sBP = "_idoc_iTest_test_all_case";
        String email = "autotestbeta@gmail.com";

        openURLdashboard(getRegionUrl());

        AuthorizationBySetLoginPassword("iTest_User_0007", "iTest_User_0007");
        clickButton("Увійти");
//        pause(6000);
        navigateToggleMenu();
//        pause(6000);
//        snapDrawerButtonMenuTabs("Документи");
//        clickButton(driver, sBP, "Створити документ");
        createDocumentOrTask("001. Тестовий документ (тестування різних типів даних)");
//        templateSelect("001. Тестовий документ (тестування різних типів даних)");
        clickButton("Далi");
        pause(6000);
        SetRegionFieldInputTypeString("sVarString", "Тип даних string");
        SetRegionFieldInputTypeTextArea("sVarTextArea", "Тип даних TextArea");
        SetRegionFieldInputTypeLong("sVarlong", "1234567890");
        SetRegionFieldInputTypeLong("sVarDouble", "1234.56789");
        SetRegionFieldInputTypeDate("sVarDate", "27/09/2017");
        SetRegionFieldInputTypeFile("src/test/resources/files/test.jpg");
        SetRegionFieldInputTypeEnum("Значення 1 для Enum");
        SetRegionFieldInputTypeCheckbox();

        /*Таблица sTable1*/
        pause(6000);
        setRegionTableCellsInputTypeString("sTables1FieldA", "0", "1");
        setRegionTableCellsInputTypeString("sTables1FieldB", "0", "1");
        setRegionTableCellsInputTypeEnumSelect("sTables1FieldC", "0", "кілограм|кг");
        setRegionTableCellsInputTypeString("sTables1FieldD", "0", "1");
        setRegionTableCellsInputTypeString("sTables1FieldE", "0", "12345");
        setRegionTableCellsInputTypeString("sTables1FieldF", "0", "12345");
        setRegionTableCellsInputTypeString("sTables1FieldG", "0", "123");
        addRegionsTableRow("sTable1");
        setRegionTableCellsInputTypeString("sTables1FieldA", "1", "1");
        setRegionTableCellsInputTypeString("sTables1FieldB", "1", "1");
        setRegionTableCellsInputTypeEnumSelect("sTables1FieldC", "1", "кілограм|кг");
        setRegionTableCellsInputTypeString("sTables1FieldD", "1", "1");
        setRegionTableCellsInputTypeString("sTables1FieldE", "1", "12345");
        setRegionTableCellsInputTypeString("sTables1FieldF", "1", "12345");
        setRegionTableCellsInputTypeString("sTables1FieldG", "1", "123");

        /*Таблица sTable2*/
        setRegionTableCellsInputTypeString("sTables2FieldA", "0", "1");
        setRegionTableCellsInputTypeFile(sBP,"sTable2", "sTables2FieldB", "0", "src/test/resources/files/test.jpg");
        setRegionTableCellsInputTypeCalendar("sTable2", "sTables2FieldC", "0", "27/09/2017");

        addRegionsTableRow("sTable2");
        setRegionTableCellsInputTypeString("sTables2FieldA", "1", "1");
        setRegionTableCellsInputTypeFile(sBP,"sTable2", "sTables2FieldB", "1", "src/test/resources/files/test.jpg");
        setRegionTableCellsInputTypeCalendar("sTable2", "sTables2FieldC", "1", "27/09/2017");

        SetRegionFieldInputTypeEnum("Заявка ТМЦ");
        SetRegionFieldInputTypeString("sEnterpriseCustomer", "Автотестування");
        SetRegionFieldInputTypeTextArea("sPreamble", "В рамках договору на надання послуг прошу організувати та провести тендер з відбору...");
        SetRegionFieldInputTypeString("sNumberCard", "41885110");
        SetRegionFieldInputTypeString("sSubgroup", "ЗНайменування продукції");

        setRegionTableCellsInputTypeEnumSpan("sTable_Goods", "sName_Goods", "0", "Изыскательские работы для строительства");
        addRegionsTableRow("sTable_Goods");
        setRegionTableCellsInputTypeEnumSpan("sTable_Goods", "sName_Goods", "1", "Изыскательские работы для строительства");

        SetRegionFieldInputTypeTextArea("sTechCharacteristic", "Технічні характеристики, особливі вимоги");
        SetRegionFieldInputTypeEnum("кілограм|кг");
        SetRegionFieldInputTypeString("sQuantity", "100");
        SetRegionFieldInputTypeString("sPurposeSupply", "Призначення поставки");
        SetRegionFieldInputTypeString("sScheduleSupply", "Термін поставки (графік)");
        SetRegionFieldInputTypeString("sProducer", "Виробник (повне найменування)");
        SetRegionFieldInputTypeTextArea("sPrefPay", "АкредитивОплата, що виключає передоплату, По факту виконаних робіт (наданих послуг), По факту загрузки, По факту поставки, По факту повідомлення про готовність, Передоплата, Інше");
        SetRegionFieldInputTypeString("sContactPerson", "Иванов Иван ИВанович");
        SetRegionFieldInputTypeString("phone", "+380621122233");
        SetRegionFieldInputTypeEnum("Ні");
        SetRegionFieldInputTypeEnum("Не потрібно");
//        getsID_OrderFromH3element();
//        getOrderFromUrlCurrentPage();
        /*Таблица Назва додатка*/
        setRegionTableCellsInputTypeString("sNameFile", "0", "100");
        setRegionTableCellsInputTypeFile(sBP,"sTableFile", "sFileAuthor", "0", "src/test/resources/files/test.jpg");

        /*Таблица Узгоджуючі*/
//        setRegionTableCellsInputTypeEnumInput(driver, sBP, "sTableAgree", "sName_Approver", "0", "Замдиректора Потапченко Василь");
        setRegionTableCellsInputTypeEnumInput(sBP, "sTableAcceptor1", "sName_Acceptor1", "0", "Співробітник2 підрозділу 2.2");
        addRegionsTableRow("sTableAcceptor1");
        setRegionTableCellsInputTypeEnumInput(sBP, "sTableAcceptor1", "sName_Acceptor1", "1", "керівник підрозділу 2.1");

        /*Таблица Затверджуючий*/
        setRegionTableCellsInputTypeEnumInput(sBP, "sTableAcceptor", "sName_Acceptor", "0", "Співробітник2 підрозділу 2.2");
        addRegionsTableRow("sTableAcceptor");
        setRegionTableCellsInputTypeEnumInput(sBP, "sTableAcceptor", "sName_Acceptor", "1", "керівник підрозділу 2.1");

        /*Таблица Адресат*/
        setRegionTableCellsInputTypeEnumInput(sBP, "sTableDirect", "sName_Addressee", "0", "керівник П2");
        addRegionsTableRow("sTableDirect");
        setRegionTableCellsInputTypeEnumInput(sBP, "sTableDirect", "sName_Addressee", "1", "керівник підрозділу 2.2");

        getOrderFromUrlCurrentPage();
//        clickButtonCreate(driver, sBP);
//        
//        
//        clickButton(driver, sBP, "Ok");
//        clickLink(driver, sBP, "Співробітник2 підрозділу 1.1  ");
//        clickLink(driver, sBP, "Вийти");
//        
//        /*2 Работа на этапе согласования (1 пользователь). Делегирование полномочий*/
//        AuthorizationBySetLoginPassword(driver, sBP, "iTest_User_0018", "iTest_User_0018");
//        clickButton(driver, sBP, "Увійти");
//        clickLink(driver, sBP, "Нерозглянутi");
        setRegionFindOrderByNumberDocument();
        pause(5000);
        clickButton("Інші дії");
        clickButton("Делегувати");
        setRegionTableCellsInputTypeEnumInput(sBP, "sTableAcceptor", "sName_Acceptor", "0", "Співробітник1 підрозділу 1.1");
        clickButton("Підтвердити");
        clickLink("Співробітник2 підрозділу 2.2");
        clickLink("Вийти");

        /*3. Работа на этапе согласования (1 пользователь). Подписываем*/
        AuthorizationBySetLoginPassword("iTest_User_0006", "iTest_User_0006");
        clickButton("Увійти");
        clickButton("Підписати");

        /*4. Работа на этапе согласования (2 пользователь). Добавляем подписанта и вібираем “Підпис не потрібен”*/
        AuthorizationBySetLoginPassword("iTest_User_0013", "iTest_User_0013");
        clickButton("Увійти");
        clickLink("Нерозглянуті");
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
