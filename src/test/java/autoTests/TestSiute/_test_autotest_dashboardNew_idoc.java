/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoTests.TestSiute;

import autoTests.CustomMethods;
import autoTests.pages.main.TemplatePage;
import org.junit.Test;

/**
 *
 * @author User
 */
public class _test_autotest_dashboardNew_idoc extends CustomMethods {

    @Test
    public void Test_test_autotest_dashboardNew_idoc() throws Exception {
        /**
         * ***************************************объявляем элементы страниц******************************************
         */
        TemplatePage o = new TemplatePage();
        String sBP = "_test_autotest_dashboard";
        String email = "autotestbeta@gmail.com";

        openURLservice(getBaseUrl() + "/service/1584/general");

        o.selectRegion("Дніпропетровська");
        o.selectCity("Дніпро (Дніпропетровськ)");

        o.mokAuthorization();

        setFieldAutocomplete("sID_Public_SubjectOrganJoin", "_test_all_case");
        setFieldValue("phone", "+380623155533");
        setFieldValue("email", email);
        setFieldValue("sVarString", "Тип даних string");
        setFieldValue("sVarTextArea", "Тип даних textArea");
        setFieldValue("sVarlong", "1234567890");
        setFieldValue("sVarDouble", "1234.56789");
        setFieldCalendar(sBP, "sVarDate", "2003/01/01");
        setFieldFile(sBP, "nVarFile", "src/test/resources/files/test.jpg");
        setFieldSelectByText("asEnumType", "Значення 2 для Enum");
        setFieldCheckBox("asEnumTypeCheckbox");

        setFieldSelectSlotDate();
        setFieldSelectSlotTime();

        setTableCellsInputTypeString("sTable1", "sTables1FieldA", "0", "Найменування товару 1");
        setTableCellsInputTypeString("sTable1", "sTables1FieldB", "0", "Код товару 1");
        setTableCellsInputTypeEnum("sTable1", "sTables1FieldC", "0", "кілограм|кг");
        setTableCellsInputTypeString("sTable1", "sTables1FieldD", "0", "10");
        setTableCellsInputTypeString("sTable1", "sTables1FieldE", "0", "12.02020");
        setTableCellsInputTypeString("sTable1", "sTables1FieldF", "0", "Вартість товару 1");
        setTableCellsInputTypeString("sTable1", "sTables1FieldG", "0", "Додаткове найменування 1");
        addTableRow("sTable1");
        setTableCellsInputTypeString("sTable1", "sTables1FieldA", "1", "Найменування товару 2");
        setTableCellsInputTypeString("sTable1", "sTables1FieldB", "1", "Код товару 2");
        setTableCellsInputTypeEnum("sTable1", "sTables1FieldC", "1", "кілограм|кг");
        setTableCellsInputTypeString("sTable1", "sTables1FieldD", "1", "20");
        setTableCellsInputTypeString("sTable1", "sTables1FieldE", "1", "22.02020");
        setTableCellsInputTypeString("sTable1", "sTables1FieldF", "1", "Вартість товару 2");
        setTableCellsInputTypeString("sTable1", "sTables1FieldG", "1", "Додаткове найменування 2");

        setTableCellsInputTypeString("sTable2", "sTables2FieldA", "0", "Найменування товару 1");
        setTableCellsInputTypeFile(sBP, "sTable2", "sTables2FieldB", "0", "src/test/resources/files/test.jpg");
        setTableCellsTypeCalendar("sTable2", "sTables2FieldC", "0", "2017/03/05");

        click(o.buttonSendingForm);
//        clickButton(driver, sBP, "Замовити послугу");
//        pause(3000000);

        o.checkMessageSuccess("Шановний(-а) MockUser MockUser!\n"
                + "Ваше звернення х-хххххххх успішно зареєстровано\n"
                + "(номер також відправлено Вам електронною поштою на Ваш e-mail " + email + ") Результати будуть спрямовані також на email.\n"
                + "Звертаємо увагу, що Іноді листи потрапляють у спам або у розділ \"Реклама\" (для Gmail).");

//        pause(3000000);
//        click(driver, o.buttonLogOut);
        openURLdashboard(getRegionUrl());

        AuthorizationBySetLoginPassword("tester", "tester");
        clickButton("Увійти");
        // Опрацювання в табі "В необроблені"    
        searchBoxIdoc();
        clickButton("Взяти в роботу");
        clickButton("Почати опрацювання задачі");

        // Опрацювання [Етап I] 
        SetRegionFieldInputTypeString("sVarStringDashboard", "Тип даних string (на дашборді)");
        SetRegionFieldInputTypeTextArea("sVarTextAreaDashboard", "Тип даних textArea  (на дашборді)");

        SetRegionFieldInputTypeLong("sVarlongDashboard", "1234567890");
        SetRegionFieldInputTypeDouble("sVarDoubleDashboard", "1234.56789");

        SetRegionFieldInputTypeDate("sVarDateDashboard", "2017/03/20");

        SetRegionFieldInputTypeFile("src/test/resources/files/test.jpg");
        SetRegionFieldInputTypeEnum("Значення 2 (на дашборді) для Enum");

        SetRegionFieldInputTypeCheckbox();

        setRegionTableCellsInputTypeString("sTables3Field1", "0", "Найменування товару (тип string)");
        setRegionTableCellsInputTypeString("sTables3Field2", "0", "Код товару (тип string)");
        setRegionTableCellsInputTypeEnumSelect("sTables3Field3", "0", "квадратний метр|кв.м");
        setRegionTableCellsInputTypeString("sTables3Field4", "0", "1234567890");
        setRegionTableCellsInputTypeString("sTables3Field5", "0", "1324,54560");
        setRegionTableCellsInputTypeString("sTables3Field6", "0", "12345");
        setRegionTableCellsInputTypeString("sTables3Field7", "0", "Додаткове найменування (тип string)");
        addRegionsTableRow("sTable3");
        setRegionTableCellsInputTypeString("sTables3Field1", "1", "Найменування товару (тип string)");
        setRegionTableCellsInputTypeString("sTables3Field2", "1", "Код товару (тип string)");
        setRegionTableCellsInputTypeEnumSelect("sTables3Field3", "1", "квадратний метр|кв.м");
        setRegionTableCellsInputTypeString("sTables3Field4", "1", "2134567890");
        setRegionTableCellsInputTypeString("sTables3Field5", "1", "21324.50");
        setRegionTableCellsInputTypeString("sTables3Field6", "1", "212345");
        setRegionTableCellsInputTypeString("sTables3Field7", "1", "2. Додаткове найменування (тип string)");

        setRegionTableCellsInputTypeString("sTables4Field1", "0", "Назва документа (тип string)");
        setRegionTableCellsInputTypeFile(sBP,"sTable4", "sTables4Field2", "0", "src/test/resources/files/test.jpg");
        setRegionTableCellsInputTypeCalendar("sTable4", "sTables4Field3", "0", "2017/03/05");
        addRegionsTableRow("sTable4");
        setRegionTableCellsInputTypeString("sTables4Field1", "1", "Назва документа (тип string)");
        setRegionTableCellsInputTypeFile(sBP,"sTable4", "sTables4Field2", "1", "src/test/resources/files/test.jpg");
        setRegionTableCellsInputTypeCalendar("sTable4", "sTables4Field3", "1", "2017/03/05");

        clickButton("Опрацювати");
        clickButton("Підтвердити");
        clickButton("Ok");

        // Опрацювання [Етап II]
        setRegionFindOrder(sBP);
        clickButton("Взяти в роботу");
        clickButton("Почати опрацювання задачі");

        SetRegionFieldInputTypeString("sVarStringDashboard2", "Тип даних string (на дашборді)");
        SetRegionFieldInputTypeTextArea("sVarTextAreaDashboard2", "Тип даних textArea  (на дашборді)");
//        pause(300000000);
        SetRegionFieldInputTypeLong("sVarlongDashboard2", "1234567890");
        SetRegionFieldInputTypeDouble("sVarDoubleDashboard2", "1234.567");
        SetRegionFieldInputTypeDate("sVarDateDashboard2", "2016/03/08");

        SetRegionFieldInputTypeFile("src/test/resources/files/test.jpg");
        SetRegionFieldInputTypeEnum("Значення 2 (на дашборді Етап 2) для Enum");

        SetRegionFieldInputTypeCheckbox();

        setRegionTableCellsInputTypeString("sTables5Field1", "0", "Найменування товару (тип string)");
        setRegionTableCellsInputTypeString("sTables5Field2", "0", "Код товару (тип string)");
        setRegionTableCellsInputTypeEnumSelect("sTables5Field3", "0", "квадратний метр|кв.м");
        setRegionTableCellsInputTypeString("sTables5Field4", "0", "123456789");
        setRegionTableCellsInputTypeString("sTables5Field5", "0", "21324.50");
        setRegionTableCellsInputTypeString("sTables5Field6", "0", "212345.67");
        setRegionTableCellsInputTypeString("sTables5Field7", "0", "Додаткове найменування (тип string)");
        addRegionsTableRow("sTable5");
        setRegionTableCellsInputTypeString("sTables5Field1", "1", "2. Найменування товару (тип string)");
        setRegionTableCellsInputTypeString("sTables5Field2", "1", "2. Код товару (тип string)");
        setRegionTableCellsInputTypeEnumSelect("sTables5Field3", "1", "квадратний метр|кв.м");
        setRegionTableCellsInputTypeString("sTables5Field4", "1", "2134567890");
        setRegionTableCellsInputTypeString("sTables5Field5", "1", "21324.50");
        setRegionTableCellsInputTypeString("sTables5Field6", "1", "212345.67");
        setRegionTableCellsInputTypeString("sTables5Field7", "1", "2. Додаткове найменування (тип string)");

        setRegionTableCellsInputTypeString("sTables6Field1", "0", "Назва документа (тип string)");
        setRegionTableCellsInputTypeFile(sBP,"sTable6", "sTables6Field2", "0", "src/test/resources/files/test.jpg");
        setRegionTableCellsInputTypeCalendar("sTable6", "sTables6Field3", "0", "2017/03/05");
        addRegionsTableRow("sTable6");
        setRegionTableCellsInputTypeString("sTables6Field1", "1", "2. Назва документа (тип string)");
        setRegionTableCellsInputTypeFile(sBP,"sTable6", "sTables6Field2", "1", "src/test/resources/files/test.jpg");
        setRegionTableCellsInputTypeCalendar("sTable6", "sTables6Field3", "1", "2017/03/05");

        clickButton("Опрацювати");
        clickButton("Підтвердити");
        clickButton("Ok");
    }
}
