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
        snapDrawerButtonMenuTabs("Документи");
        createDocumentOrTask("001. Тестовий документ (тестування різних типів даних)");
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
        
        /*ПРОБЛЕМА ЗАПОЛНЕНИЯ второго ряда Таблица sTable2*/
//        addRegionsTableRow(driver, sBP, "sTable2");
//        setRegionTableCellsInputTypeString(driver, sBP, "sTable2", "sTables2FieldA", "1", "1");
//        setRegionTableCellsInputTypeFile(driver, sBP, "sTable2", "sTables2FieldB", "1", "src/test/resources/files/test.jpg");
//        setRegionTableCellsInputTypeCalendar(driver, sBP, "sTable2", "sTables2FieldC", "1", "27/09/2017");
       
        SetRegionFieldInputTypeEnum(driver, sBP, "asAttributeApplication", "Заявка ТМЦ");
        SetRegionFieldInputTypeString(driver, sBP, "sEnterpriseCustomer", "Автотестування");
        SetRegionFieldInputTypeTextArea(driver, sBP, "sPreamble", "В рамках договору на надання послуг прошу організувати та провести тендер з відбору...");
        SetRegionFieldInputTypeString(driver, sBP, "sNumberCard", "41885110");
        SetRegionFieldInputTypeString(driver, sBP, "sSubgroup", "ЗНайменування продукції");
        
        setRegionTableCellsInputTypeEnumSpan(driver, sBP, "sTable_Goods", "sName_Goods", "0", "Изыскательские работы для строительства");
//        addRegionsTableRow(driver, sBP, "sTable_Goods");
//        setRegionTableCellsInputTypeEnumSpan(driver, sBP, "sTable_Goods", "sName_Goods", "1", "Изыскательские работы для строительства");
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
        
        /*Таблица Узгоджуючі*/
//        setRegionTableCellsInputTypeEnumSpan(driver, sBP, "sTableAcceptor", "sName_Acceptor", "0", "Співробітник2 підрозділу 2.2");
//        addRegionsTableRow(driver, sBP, "sTableAcceptor");
//        setRegionTableCellsInputTypeEnumSpan(driver, sBP, "sTableAcceptor", "sName_Acceptor", "0", "керівник підрозділу 2.1");
    }
}
