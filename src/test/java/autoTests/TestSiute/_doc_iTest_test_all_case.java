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
        pause(6000);
        navigateToggleMenu();
        pause(6000);
        snapDrawerButtonMenuTabs("Документи");
        createDocumentOrTask("001. Тестовий документ (тестування різних типів даних)");
        clickButton(driver, sBP, "Далi");
        pause(6000);
        SetRegionFieldInputTypeString(driver, sBP, "sVarString", "Тип даних string");
        SetRegionFieldInputTypeTextArea(driver, sBP, "sVarTextArea", "Тип даних TextArea");
        SetRegionFieldInputTypeLong(driver, sBP, "sVarlong", "Тип даних long");
        SetRegionFieldInputTypeLong(driver, sBP, "sVarDouble", "Тип даних Double");
        SetRegionFieldInputTypeDate(driver, sBP, "sVarDate", "27/09/2017");
        SetRegionFieldInputTypeFile(driver, sBP, "", "src/test/resources/files/test.jpg");
        SetRegionFieldInputTypeEnum(driver, sBP, "asEnumType", "Значення 3 для Enum");
//        SetRegionFieldInputTypeCheckbox(driver, sBP, "asEnumTypeCheckbox");
        setRegionTableCellsInputTypeString(driver, sBP, "sTable1", "sTables1FieldA", "0", "1");
        setRegionTableCellsInputTypeString(driver, sBP, "sTable1", "sTables1FieldB", "0", "1");
        setRegionTableCellsInputTypeEnum(driver, sBP, "sTable1", "sTables1FieldC", "0", "кілограм|кг");
        setRegionTableCellsInputTypeString(driver, sBP, "sTable1", "sTables1FieldD", "0", "1");
        setRegionTableCellsInputTypeString(driver, sBP, "sTable1", "sTables1FieldE", "0", "12345");
        setRegionTableCellsInputTypeString(driver, sBP, "sTable1", "sTables1FieldF", "0", "12345");
        setRegionTableCellsInputTypeString(driver, sBP, "sTable1", "sTables1FieldG", "0", "123");
        addRegionsTableRow(driver, sBP, "sTable1");
        setRegionTableCellsInputTypeString(driver, sBP, "sTable1", "sTables1FieldA", "1", "1");
        setRegionTableCellsInputTypeString(driver, sBP, "sTable1", "sTables1FieldB", "1", "1");
        setRegionTableCellsInputTypeEnum(driver, sBP, "sTable1", "sTables1FieldC", "1", "кілограм|кг");
        setRegionTableCellsInputTypeString(driver, sBP, "sTable1", "sTables1FieldD", "1", "1");
        setRegionTableCellsInputTypeString(driver, sBP, "sTable1", "sTables1FieldE", "1", "12345");
        setRegionTableCellsInputTypeString(driver, sBP, "sTable1", "sTables1FieldF", "1", "12345");
        setRegionTableCellsInputTypeString(driver, sBP, "sTable1", "sTables1FieldG", "1", "123");

        setRegionTableCellsInputTypeString(driver, sBP, "sTable2", "sTables2FieldA", "0", "1");
        setRegionTableCellsInputTypeFile(driver, sBP, "sTable2", "sTables2FieldB", "0", "src/test/resources/files/test.jpg");
        setRegionTableCellsInputTypeCalendar(driver, sBP, "sTable2", "sTables2FieldB", "0", "27/09/2017");
        addRegionsTableRow(driver, sBP, "sTable2");
        setRegionTableCellsInputTypeString(driver, sBP, "sTable2", "sTables2FieldA", "1", "1");
        setRegionTableCellsInputTypeFile(driver, sBP, "sTable2", "sTables2FieldB", "1", "src/test/resources/files/test.jpg");
        setRegionTableCellsInputTypeCalendar(driver, sBP, "sTable2", "sTables2FieldB", "1", "27/09/2017");
    }
}