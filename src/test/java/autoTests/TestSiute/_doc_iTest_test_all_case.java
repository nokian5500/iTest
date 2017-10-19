/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoTests.TestSiute;

import autoTests.CustomMethods;
import org.testng.annotations.Test;

/**
 *
 * @author User
 */
public class _doc_iTest_test_all_case extends CustomMethods {

    @Test(enabled = true, groups = {"Main", "Критический функционал"}, priority = 2)
    public void Test_doc_iTest_test_all_case() throws Exception {
        String sBP = "_test_autotest_dashboard";
        String email = "autotestbeta@gmail.com";
        _step("1. Вход по прямому URL на дашборд");
        openURLdashboard(driver, sBP);
        _step("2. Авторизация login/BankID на дашборде. login/pass: (iTest_User_0007/iTest_User_0007)");
        AuthorizationBySetLoginPassword(driver, sBP, "iTest_User_0007", "iTest_User_0007");
        clickButton(driver, sBP, "Увійти");
//        pause(6000);
        navigateToggleMenu();
//        pause(6000);
//        snapDrawerButtonMenuTabs("Документи");
//        clickButton(driver, sBP, "Створити документ");
        createDocumentOrTask("001. Тестовий документ (тестування різних типів даних)");
//        templateSelect("001. Тестовий документ (тестування різних типів даних)");
        clickButton(driver, sBP, "Далi");
//        pause(6000);
        SetRegionFieldInputTypeString(driver, sBP, "sVarString", "Тип даних string");
        SetRegionFieldInputTypeTextArea(driver, sBP, "sVarTextArea", "Тип даних TextArea");
        SetRegionFieldInputTypeLong(driver, sBP, "sVarlong", "Тип даних long");
        SetRegionFieldInputTypeLong(driver, sBP, "sVarDouble", "Тип даних Double");
        SetRegionFieldInputTypeDate(driver, sBP, "sVarDate", "27/09/2017");
        SetRegionFieldInputTypeFile(driver, sBP, "", "src/test/resources/files/test.jpg");
        SetRegionFieldInputTypeEnum(driver, sBP, "asEnumType", "Значення 1 для Enum");
        SetRegionFieldInputTypeCheckbox(driver, sBP, "asEnumTypeCheckbox");
        
        /*Таблица sTable1*/
        setRegionTableCellsInputTypeString(driver, sBP, "sTable1", "sTables1FieldA", "0", "1");
        setRegionTableCellsInputTypeString(driver, sBP, "sTable1", "sTables1FieldB", "0", "1");
        setRegionTableCellsInputTypeEnumSelect(driver, sBP, "sTable1", "sTables1FieldC", "0", "кілограм|кг");
        setRegionTableCellsInputTypeString(driver, sBP, "sTable1", "sTables1FieldD", "0", "1");
        setRegionTableCellsInputTypeString(driver, sBP, "sTable1", "sTables1FieldE", "0", "12345");
        setRegionTableCellsInputTypeString(driver, sBP, "sTable1", "sTables1FieldF", "0", "12345");
        setRegionTableCellsInputTypeString(driver, sBP, "sTable1", "sTables1FieldG", "0", "123");
        addRegionsTableRow(driver, sBP, "sTable1");
        setRegionTableCellsInputTypeString(driver, sBP, "sTable1", "sTables1FieldA", "1", "1");
        setRegionTableCellsInputTypeString(driver, sBP, "sTable1", "sTables1FieldB", "1", "1");
        setRegionTableCellsInputTypeEnumSelect(driver, sBP, "sTable1", "sTables1FieldC", "1", "кілограм|кг");
        setRegionTableCellsInputTypeString(driver, sBP, "sTable1", "sTables1FieldD", "1", "1");
        setRegionTableCellsInputTypeString(driver, sBP, "sTable1", "sTables1FieldE", "1", "12345");
        setRegionTableCellsInputTypeString(driver, sBP, "sTable1", "sTables1FieldF", "1", "12345");
        setRegionTableCellsInputTypeString(driver, sBP, "sTable1", "sTables1FieldG", "1", "123");

        /*Таблица sTable2*/
        setRegionTableCellsInputTypeString(driver, sBP, "sTable2", "sTables2FieldA", "0", "1");
        setRegionTableCellsInputTypeFile(driver, sBP, "sTable2", "sTables2FieldB", "0", "src/test/resources/files/test.jpg");
        setRegionTableCellsInputTypeCalendar(driver, sBP, "sTable2", "sTables2FieldC", "0", "27/09/2017");
        
        
        addRegionsTableRow(driver, sBP, "sTable2");
        setRegionTableCellsInputTypeString(driver, sBP, "sTable2", "sTables2FieldA", "1", "1");
        setRegionTableCellsInputTypeFile(driver, sBP, "sTable2", "sTables2FieldB", "1", "src/test/resources/files/test.jpg");
        setRegionTableCellsInputTypeCalendar(driver, sBP, "sTable2", "sTables2FieldC", "1", "27/09/2017");
       
        SetRegionFieldInputTypeEnum(driver, sBP, "asAttributeApplication", "Заявка ТМЦ");
        SetRegionFieldInputTypeString(driver, sBP, "sEnterpriseCustomer", "Автотестування");
        SetRegionFieldInputTypeTextArea(driver, sBP, "sPreamble", "В рамках договору на надання послуг прошу організувати та провести тендер з відбору...");
        SetRegionFieldInputTypeString(driver, sBP, "sNumberCard", "41885110");
        SetRegionFieldInputTypeString(driver, sBP, "sSubgroup", "ЗНайменування продукції");
        
        setRegionTableCellsInputTypeEnumSpan(driver, sBP, "sTable_Goods", "sName_Goods", "0", "Изыскательские работы для строительства");
        addRegionsTableRow(driver, sBP, "sTable_Goods");
        setRegionTableCellsInputTypeEnumSpan(driver, sBP, "sTable_Goods", "sName_Goods", "1", "Изыскательские работы для строительства");
        
        SetRegionFieldInputTypeTextArea(driver, sBP, "sTechCharacteristic", "Технічні характеристики, особливі вимоги");
        SetRegionFieldInputTypeEnum(driver, sBP, "asUnit", "кілограм|кг");
        SetRegionFieldInputTypeString(driver, sBP, "sQuantity", "100");
        SetRegionFieldInputTypeString(driver, sBP, "sPurposeSupply", "Призначення поставки");
        SetRegionFieldInputTypeString(driver, sBP, "sScheduleSupply", "Термін поставки (графік)");
        SetRegionFieldInputTypeString(driver, sBP, "sProducer", "Виробник (повне найменування)");
        SetRegionFieldInputTypeTextArea(driver, sBP, "sPrefPay", "АкредитивОплата, що виключає передоплату, По факту виконаних робіт (наданих послуг), По факту загрузки, По факту поставки, По факту повідомлення про готовність, Передоплата, Інше");
        SetRegionFieldInputTypeString(driver, sBP, "sContactPerson", "Иванов Иван ИВанович");
        SetRegionFieldInputTypeString(driver, sBP, "phone", "+380621122233");
        SetRegionFieldInputTypeEnum(driver, sBP, "asBankGaranty", "Ні");
        SetRegionFieldInputTypeEnum(driver, sBP, "asPersonalResponse", "Не потрібно");
        
        /*Таблица Назва додатка*/
        setRegionTableCellsInputTypeString(driver, sBP, "sTableFile", "sNameFile", "0", "100");
        setRegionTableCellsInputTypeFile(driver, sBP, "sTableFile", "sFileAuthor", "0", "src/test/resources/files/test.jpg");
        
        getsID_OrderFromH3element(driver);
        
        /*Таблица Узгоджуючі*/
        setRegionTableCellsInputTypeEnumInput(driver, sBP, "sTableAcceptor", "sName_Acceptor", "0", "Співробітник2 підрозділу 2.2");
        addRegionsTableRow(driver, sBP, "sTableAcceptor");
        setRegionTableCellsInputTypeEnumInput(driver, sBP, "sTableAcceptor", "sName_Acceptor", "1", "керівник підрозділу 2.1");
        
        /*Таблица Узгоджуючі*/
        setRegionTableCellsInputTypeEnumInput(driver, sBP, "sTableAgree", "sName_Approver", "0", "Замдиректора Потапченко Василь");
        
        
        
        /*Таблица Адресат*/
        setRegionTableCellsInputTypeEnumInput(driver, sBP, "sTableDirect", "sName_Addressee", "0", "керівник П2");
        addRegionsTableRow(driver, sBP, "sTableDirect");
        setRegionTableCellsInputTypeEnumInput(driver, sBP, "sTableDirect", "sName_Addressee", "1", "керівник підрозділу 2.2");
        
        clickButtonCreate(driver, sBP);
        pause(10000);
//        clickButton(driver, sBP, "Ok");
        clickLink(driver, sBP, "Співробітник2 підрозділу 1.1");
        clickLink(driver, sBP, "Вийти");
        
        /*Работа на этапе согласования (1 пользователь). Делегирование полномочий*/
        AuthorizationBySetLoginPassword(driver, sBP, "iTest_User_0018", "iTest_User_0018");
        clickButton(driver, sBP, "Увійти");
        searchBoxIdoc();
        clickButton(driver, sBP, "Інші дії");
        clickButton(driver, sBP, "Делегувати");
        setRegionTableCellsInputTypeEnumInput(driver, sBP, "sTableAcceptor", "sName_Acceptor", "0", "Співробітник1 підрозділу 1.1");
        clickButton(driver, sBP, "Підтвердити");
        clickLink(driver, sBP, "Співробітник2 підрозділу 2.2");
        clickLink(driver, sBP, "Вийти");
        
        /*3. Работа на этапе согласования (1 пользователь). Подписываем*/
        AuthorizationBySetLoginPassword(driver, sBP, "iTest_User_0006", "iTest_User_0006");
        clickButton(driver, sBP, "Увійти");
        clickLink(driver, sBP, "Нерозглянуті");
        clickButton(driver, sBP, "Підписати");

        /*4. Работа на этапе согласования (2 пользователь). Добавляем подписанта и вібираем “Підпис не потрібен”*/
        AuthorizationBySetLoginPassword(driver, sBP, "iTest_User_0013", "iTest_User_0013");
        clickButton(driver, sBP, "Увійти");
        clickLink(driver, sBP, "Нерозглянуті");
        clickButton(driver, sBP, "Інші дії");
        clickButton(driver, sBP, "Додати підписанта");
        setRegionTableCellsInputTypeEnumInput(driver, sBP, "sTableAcceptor", "sName_Acceptor", "0", "секретар i1");
        clickButton(driver, sBP, "Підтвердити");
        clickButton(driver, sBP, "Ok");
        clickButton(driver, sBP, "Інші дії");
        clickButton(driver, sBP, "Підпис не потрібен");
        setFieldTextArea(driver, sBP, "???", "коментар 1");
        clickButton(driver, sBP, "Підпис не потрібен");
        clickButton(driver, sBP, "Ok");
        
        /*5. Работа на этапе согласования (3 пользователь). Подписываем*/
        AuthorizationBySetLoginPassword(driver, sBP, "iTest_User_0004", "iTest_User_0004");
        clickButton(driver, sBP, "Увійти");
        clickLink(driver, sBP, "Нерозглянуті");
        clickButton(driver, sBP, "Підписати");
        
        /*6. Работа на этапе утверждения. Добавляем замечания и нового подписанта*/
        AuthorizationBySetLoginPassword(driver, sBP, "iTest_User_0002", "iTest_User_0002");
        clickButton(driver, sBP, "Увійти");
        setRegionFindOrder(driver, sBP, "");
        clickButton(driver, sBP, "Зауваження");
        SetRegionFieldInputTypeString(driver, sBP, "???", "Зауваження");
        clickButton(driver, sBP, "Выдправити зауваження");
        clickButton(driver, sBP, "Інші дії");
        clickLink(driver, sBP, "Додадати підписанта");
        SetRegionFieldInputTypeEnum(driver, sBP, "asEnumType", "Директор Гнатушенко Тарас");
        clickButton(driver, sBP, "Підтвердити");
        clickButton(driver, sBP, "Ok");
        clickButton(driver, sBP, "Підписати");
        clickLink(driver, sBP, "Замдиректора Потапченко Василь");
        clickLink(driver, sBP, "Вийти");
        
        /*7. Отвечаем на зауваження*/
        AuthorizationBySetLoginPassword(driver, sBP, "iTest_User_0007", "iTest_User_0007");
        clickButton(driver, sBP, "Увійти");
        clickLink(driver, sBP, "Нерозглянутi");
        setRegionFindOrderForDocument(driver, sBP);
        clickLink(driver, sBP, "dialog"); //
        clickLink(driver, sBP, "Відповісти");
        SetRegionFieldInputTypeString(driver, sBP, "", " Ответ на замечание1");
        clickButton(driver, sBP, "Відповісти");
        clickButton(driver, sBP, "Ok");
        clickButton(driver, sBP, "Зберегти");
        clickButton(driver, sBP, "Ok");
        clickLink(driver, sBP, "Співробітник2 підрозділу 1.1");
        clickLink(driver, sBP, "Вийти");
        
        /*8. Подписываем дополнительно директором*/
        AuthorizationBySetLoginPassword(driver, sBP, "Test_User_0001", "Test_User_0001");
        clickButton(driver, sBP, "Увійти");
        clickLink(driver, sBP, "Нерозглянутi");
        setRegionFindOrderForDocument(driver, sBP);
        clickButton(driver, sBP, "Підписати");
        clickLink(driver, sBP, "Директор Гнатушенко Тарас");
        clickLink(driver, sBP, "Вийти");
        
        /*9. Заходим адресатом 1. Добавляем задание 1*/
        AuthorizationBySetLoginPassword(driver, sBP, "iTest_User_0011", "iTest_User_0011");
        clickButton(driver, sBP, "Увійти");
        clickLink(driver, sBP, "Нерозглянуті");
        setRegionFindOrderForDocument(driver, sBP);
        setRegionTableCellsInputTypeEnumInput(driver, sBP, "sTableAcceptor", "sName_Acceptor", "1", "секретар І2");
        clickButton(driver, sBP, "Додати завдання");
        
        
        
        /*10. Заходим адресатом 2.*/
        AuthorizationBySetLoginPassword(driver, sBP, "iTest_User_0016", "iTest_User_0016");
        clickButton(driver, sBP, "Увійти");
        clickLink(driver, sBP, "Нерозглянуті");
        setRegionFindOrderForDocument(driver, sBP);
        clickButton(driver, sBP, "Підписати");
        clickLink(driver, sBP, "керівник підрозділу 2.2");
        clickLink(driver, sBP, "Вийти");
        
        /*11. Заходим исполнителем 1. Обработка задания. Добавляем отчет 1*/
        AuthorizationBySetLoginPassword(driver, sBP, "iTest_User_0014", "iTest_User_0014");
        clickButton(driver, sBP, "Увійти");
        snapDrawerButtonMenuTabs("Завдання");
        clickLink(driver, sBP, "Необроблені");
        setRegionFindOrder(driver, sBP);
        clickButton(driver, sBP, "Додати звіт");
        SetRegionFieldInputTypeEnum(driver, sBP, "", "Виконано");
        SetRegionFieldInputTypeFile(driver, sBP, "", "src/test/resources/files/test.jpg");
        clickButton(driver, sBP, "Підтвердити");
        clickLink(driver, sBP, "Співробітник1 підрозділу 2.1");
        clickLink(driver, sBP, "Вийти");
        
        /*12. Заходим исполнителем 2. Обработка задания. Добавляем отчет 2*/
        AuthorizationBySetLoginPassword(driver, sBP, "iTest_User_0015", "iTest_User_0015");
        clickButton(driver, sBP, "Увійти");
        snapDrawerButtonMenuTabs("Завдання");
        clickLink(driver, sBP, "Необроблені");
        setRegionFindOrder(driver, sBP);
        clickButton(driver, sBP, "Додати звіт");
        SetRegionFieldInputTypeEnum(driver, sBP, "", "неактуально");
        SetRegionFieldInputTypeTextArea(driver, sBP, "", "Звіт не актуальний");
        clickButton(driver, sBP, "Підтвердити");
        clickLink(driver, sBP, "Співробітник2 підрозділу 2.1");
        clickLink(driver, sBP, "Вийти");
       
        /*13. Заходим контролирующим. Подтверждаем отчет*/
        AuthorizationBySetLoginPassword(driver, sBP, "iTest_User_0013", "iTest_User_0013");
        clickButton(driver, sBP, "Увійти");
        snapDrawerButtonMenuTabs("Завдання");
        clickLink(driver, sBP, "На контролі");
        setRegionFindOrder(driver, sBP);
        clickButton(driver, sBP, "Прийняти завдання");
        clickLink(driver, sBP, "керівник підрозділу 2.1");
        clickLink(driver, sBP, "Вийти");
        
        /*14. Заходим исполнителем. Подписіваем*/
        AuthorizationBySetLoginPassword(driver, sBP, "iTest_User_0012 ", "iTest_User_0012 ");
        clickButton(driver, sBP, "Увійти");
        clickLink(driver, sBP, "Нерозглянуті");
        setRegionFindOrderForDocument(driver, sBP);
        clickButton(driver, sBP, "Підписати");
        clickLink(driver, sBP, "секретар І2");
        clickLink(driver, sBP, "Вийти");
    }
}
