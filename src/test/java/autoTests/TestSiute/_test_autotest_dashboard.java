/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoTests.TestSiute;

import autoTests.CustomMethods;
import autoTests.pages.main.TemplatePage;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

/**
 *
 * @author HS
 */
public class _test_autotest_dashboard extends CustomMethods {
//<editor-fold desc="Тестовый пример загрузки файла">
    @Test(enabled = true, groups = {"Main", "Критический функционал"}, priority = 2)
    public void Test_test_autotest_dashboard() throws Exception {
        /*****************************************объявляем элементы страниц*******************************************/
        TemplatePage o = new TemplatePage(driver);
        String sBP = "_test_autotest_dashboard";
        String email = "autotestbeta@gmail.com";

        _step("1. Вход по прямому URL на услугу");
        openURLservice(driver, CV.baseUrl + "/service/1584/general");
        
        _step("3. Выбор области/города");
        o.selectRegion("Вінницька");
        o.selectCity("Вінниця");
        
        _step("4. Авторизация Off AuthMock/BankID");
        o.mokAuthorization();
        
        _step("5. Заполняем форму услуги");
        setFieldAutocomplete(driver,"sID_Public_SubjectOrganJoin","_test_all_case");
        setFieldValue(driver, sBP, "phone", "+380623155533");
        setFieldValue(driver, sBP, "email", email);
        setFieldValue(driver, sBP, "sVarString", "Тип даних string");
        setFieldValue(driver, sBP, "sVarTextArea", "Тип даних textArea");
        setFieldValue(driver, sBP, "sVarlong", "1234567890");
        setFieldValue(driver, sBP, "sVarDouble", "1234.56789");
        setFieldCalendar(driver, sBP, "sVarDate", "2003/01/01");
        setFieldFile(driver, sBP, "nVarFile", "src/test/resources/files/test.jpg");
        setFieldSelectByText(driver,sBP,"asEnumType","Значення 2 для Enum");
        setFieldCheckBox(driver, sBP, "asEnumTypeCheckbox");
        
         setFieldSelectSlotDate(driver, sBP, "."+sBP+"_--_"+"visitDay"); 
         setFieldSelectSlotTime(driver, sBP, "."+sBP+"_--_"+"visitDay");
        
        setTableCellsInputTypeString(driver, sBP, "sTable1","sTables1FieldA","0", "Найменування товару 1");
        setTableCellsInputTypeString(driver, sBP, "sTable1", "sTables1FieldB","0", "Код товару 1");
        setTableCellsInputTypeEnum(driver, sBP, "sTable1","sTables1FieldC","0", "кілограм|кг");
        setTableCellsInputTypeString(driver, sBP, "sTable1", "sTables1FieldD","0","10");
        setTableCellsInputTypeString(driver, sBP, "sTable1", "sTables1FieldE","0","12.02020");
        setTableCellsInputTypeString(driver, sBP, "sTable1", "sTables1FieldF","0","Вартість товару 1");
        setTableCellsInputTypeString(driver, sBP, "sTable1", "sTables1FieldG","0","Додаткове найменування 1");
        addTableRow(driver, sBP, "sTable1");
        setTableCellsInputTypeString(driver, sBP, "sTable1","sTables1FieldA","1", "Найменування товару 2");
        setTableCellsInputTypeString(driver, sBP, "sTable1", "sTables1FieldB","1", "Код товару 2");
        setTableCellsInputTypeEnum(driver, sBP, "sTable1","sTables1FieldC","1", "кілограм|кг");
        setTableCellsInputTypeString(driver, sBP, "sTable1", "sTables1FieldD","1","20");
        setTableCellsInputTypeString(driver, sBP, "sTable1", "sTables1FieldE","1","22.02020");
        setTableCellsInputTypeString(driver, sBP, "sTable1", "sTables1FieldF","1","Вартість товару 2");
        setTableCellsInputTypeString(driver, sBP, "sTable1", "sTables1FieldG","1","Додаткове найменування 2");

        setTableCellsInputTypeString(driver, sBP, "sTable2", "sTables2FieldA","0", "Найменування товару 1");
        setTableCellsInputTypeFile(driver, sBP, "sTable2", "sTables2FieldB","0", "src/test/resources/files/test.jpg");
        setTableCellsTypeCalendar(driver, sBP, "sTable2", "sTables2FieldC","0", "2017/03/05");
  
        _step("6. Отправка формы");
        click(driver, o.buttonSendingForm);
//        clickButton(driver, sBP, "Замовити послугу");
//        pause(3000000);
        _step("7. Проверка сообщения о успешной отправке");
        o.checkMessageSuccess("Шановний(-а) MockUser MockUser!\n" +
                "Ваше звернення х-хххххххх успішно зареєстровано\n" +
                "(номер також відправлено Вам електронною поштою на Ваш e-mail "+email+") Результати будуть спрямовані також на email.\n" +
                "Звертаємо увагу, що Іноді листи потрапляють у спам або у розділ \"Реклама\" (для Gmail).");
        
        _step("7-1. Нажать кнопку Выйти");
        click(driver, o.buttonLogOut);
        
        _step("8. Вход по прямому URL на дашборд");
        openURLdashboard(driver, sBP);

        _step("9. Авторизация login/BankID на дашборде. login/pass: (tester/tester)");
        AuthorizationBySetLoginPassword(driver, sBP, "tester", "tester");
        clickButton(driver, sBP, "Увійти");
     // Опрацювання в табі "В необроблені"    
        setRegionTask(driver, sBP);
        clickButton(driver, sBP, "Взяти в роботу");
        clickButton(driver, sBP, "Почати опрацювання задачі");
        
        // Опрацювання [Етап I] 
        SetRegionFieldInputTypeString(driver, sBP, "sVarStringDashboard", "Тип даних string (на дашборді)");
        SetRegionFieldInputTypeTextArea(driver, sBP, "sVarTextAreaDashboard", "Тип даних textArea  (на дашборді)");

        SetRegionFieldInputTypeLong(driver, sBP, "sVarlongDashboard", "1234567890");
        SetRegionFieldInputTypeDouble(driver, sBP, "sVarDoubleDashboard", "1234.56789");

        SetRegionFieldInputTypeDate(driver, sBP, "sVarDateDashboard", "2017/03/20");

        SetRegionFieldInputTypeFile(driver, sBP, "nVarFileDashboard", "src/test/resources/files/test.jpg");
        SetRegionFieldInputTypeEnum(driver, sBP, "asEnumTypeDashboard", "Значення 2 (на дашборді) для Enum");

        SetRegionFieldInputTypeCheckbox(driver, sBP, "asEnumTypeCheckboxDashboard");
        
        setRegionTableCellsInputTypeString(driver, sBP, "sTable3", "sTables3Field1", "0", "Найменування товару (тип string)");
        setRegionTableCellsInputTypeString(driver, sBP, "sTable3", "sTables3Field2", "0", "Код товару (тип string)");
        setRegionTableCellsInputTypeEnum(driver, sBP, "sTable3", "sTables3Field3", "0", "квадратний метр|кв.м");
        setRegionTableCellsInputTypeString(driver, sBP, "sTable3", "sTables3Field4", "0", "1234567890");
        setRegionTableCellsInputTypeString(driver, sBP, "sTable3", "sTables3Field5", "0", "1324,54560");
        setRegionTableCellsInputTypeString(driver, sBP, "sTable3", "sTables3Field6", "0", "12345");
        setRegionTableCellsInputTypeString(driver, sBP, "sTable3", "sTables3Field7", "0", "Додаткове найменування (тип string)");
        addRegionsTableRow(driver, sBP, "sTable3");
        setRegionTableCellsInputTypeString(driver, sBP, "sTable3", "sTables3Field1", "1", "Найменування товару (тип string)");
        setRegionTableCellsInputTypeString(driver, sBP, "sTable3", "sTables3Field2", "1", "Код товару (тип string)");
        setRegionTableCellsInputTypeEnum(driver, sBP, "sTable3", "sTables3Field3", "1", "квадратний метр|кв.м");
        setRegionTableCellsInputTypeString(driver, sBP, "sTable3", "sTables3Field4", "1", "2134567890");
        setRegionTableCellsInputTypeString(driver, sBP, "sTable3", "sTables3Field5", "1", "21324.50");
        setRegionTableCellsInputTypeString(driver, sBP, "sTable3", "sTables3Field6", "1", "212345");
        setRegionTableCellsInputTypeString(driver, sBP, "sTable3", "sTables3Field7", "1", "2. Додаткове найменування (тип string)");
        
        setRegionTableCellsInputTypeString(driver, sBP, "sTable4", "sTables4Field1", "0", "Назва документа (тип string)");
        setRegionTableCellsInputTypeFile(driver, sBP, "sTable4", "sTables4Field2", "0", "src/test/resources/files/test.jpg");
        setRegionTableCellsInputTypeCalendar(driver, sBP, "sTable4", "sTables4Field3", "0", "2017/03/05");
        addRegionsTableRow(driver, sBP, "sTable4");
        setRegionTableCellsInputTypeString(driver, sBP, "sTable4", "sTables4Field1", "1", "Назва документа (тип string)");
        setRegionTableCellsInputTypeFile(driver, sBP, "sTable4", "sTables4Field2", "1", "src/test/resources/files/test.jpg");
        setRegionTableCellsInputTypeCalendar(driver, sBP, "sTable4", "sTables4Field3", "1", "2017/03/05");

        clickButton(driver, sBP, "Опрацювати");
        clickButton(driver, sBP, "Підтвердити");

        // Опрацювання [Етап II]
        
        setRegionFindOrder(driver, sBP);
        clickButton(driver, sBP, "Взяти в роботу");
        clickButton(driver, sBP, "Почати опрацювання задачі");
        
         
        SetRegionFieldInputTypeString(driver, sBP, "sVarStringDashboard2", "Тип даних string (на дашборді)");
        SetRegionFieldInputTypeTextArea(driver, sBP, "sVarTextAreaDashboard2", "Тип даних textArea  (на дашборді)");
//        pause(300000000);
        SetRegionFieldInputTypeLong(driver, sBP, "sVarlongDashboard2", "1234567890");
        SetRegionFieldInputTypeDouble(driver, sBP, "sVarDoubleDashboard2", "1234.567");
        SetRegionFieldInputTypeDate(driver, sBP, "sVarDateDashboard2", "2016/03/08");

        SetRegionFieldInputTypeFile(driver, sBP, "nVarFileDashboard2", "src/test/resources/files/test.jpg");
        SetRegionFieldInputTypeEnum(driver, sBP, "asEnumTypeDashboard2", "Значення 2 (на дашборді Етап 2) для Enum");

        SetRegionFieldInputTypeCheckbox(driver, sBP, "asEnumTypeCheckboxDashboard");
        
        setRegionTableCellsInputTypeString(driver, sBP, "sTable5", "sTables5Field1", "0", "Найменування товару (тип string)");
        setRegionTableCellsInputTypeString(driver, sBP, "sTable5", "sTables5Field2", "0", "Код товару (тип string)");
        setRegionTableCellsInputTypeEnum(driver, sBP, "sTable5", "sTables5Field3", "0", "квадратний метр|кв.м");
        setRegionTableCellsInputTypeString(driver, sBP, "sTable5", "sTables5Field4", "0", "123456789");
        setRegionTableCellsInputTypeString(driver, sBP, "sTable5", "sTables5Field5", "0", "21324.50");
        setRegionTableCellsInputTypeString(driver, sBP, "sTable5", "sTables5Field6", "0", "212345.67");
        setRegionTableCellsInputTypeString(driver, sBP, "sTable5", "sTables5Field7", "0", "Додаткове найменування (тип string)");
        addRegionsTableRow(driver, sBP, "sTable5");
        setRegionTableCellsInputTypeString(driver, sBP, "sTable5", "sTables5Field1", "1", "2. Найменування товару (тип string)");
        setRegionTableCellsInputTypeString(driver, sBP, "sTable5", "sTables5Field2", "1", "2. Код товару (тип string)");
        setRegionTableCellsInputTypeEnum(driver, sBP, "sTable5", "sTables5Field3", "1", "квадратний метр|кв.м");
        setRegionTableCellsInputTypeString(driver, sBP, "sTable5", "sTables5Field4", "1", "2134567890");
        setRegionTableCellsInputTypeString(driver, sBP, "sTable5", "sTables5Field5", "1", "21324.50");
        setRegionTableCellsInputTypeString(driver, sBP, "sTable5", "sTables5Field6", "1", "212345.67");
        setRegionTableCellsInputTypeString(driver, sBP, "sTable5", "sTables5Field7", "1", "2. Додаткове найменування (тип string)");

        setRegionTableCellsInputTypeString(driver, sBP, "sTable6", "sTables6Field1", "0", "Назва документа (тип string)");
        setRegionTableCellsInputTypeFile(driver, sBP, "sTable6", "sTables6Field2", "0", "src/test/resources/files/test.jpg");
        setRegionTableCellsInputTypeCalendar(driver, sBP, "sTable6", "sTables6Field3", "0", "2017/03/05");
        addRegionsTableRow(driver, sBP, "sTable6");
        setRegionTableCellsInputTypeString(driver, sBP, "sTable6", "sTables6Field1", "1", "2. Назва документа (тип string)");
        setRegionTableCellsInputTypeFile(driver, sBP, "sTable6", "sTables6Field2", "1", "src/test/resources/files/test.jpg");
        setRegionTableCellsInputTypeCalendar(driver, sBP, "sTable6", "sTables6Field3", "1", "2017/03/05");

        clickButton(driver, sBP, "Опрацювати");
        clickButton(driver, sBP, "Підтвердити");
        _step("10. Нажать кнопку Выйти");
        click(driver, o.buttonLogOut);
        
        
        
        
    }
}
