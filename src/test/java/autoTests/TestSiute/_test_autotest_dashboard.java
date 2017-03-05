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
    public void Test_test_tables_other_types() throws Exception {
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
        setFieldCheckBox(driver, sBP, "#asEnumTypeCheckbox");
        
        setFieldSelectSlotDate(driver, sBP, "."+sBP+"_--_"+"visitDay"); 
        setFieldSelectSlotTime(driver, sBP, "."+sBP+"_--_"+"visitDay");
        
        setTableCellsInputTypeSelect(driver, sBP, "sTable1","sTables1Field10", "Найменування товару 1");
        setTableCellsInputTypeString(driver, sBP, "sTable1", "sTables1Field20", "Код товару 1");
        setTableCellsInputTypeEnum(driver, sBP, "sTable1","sTables1Field30", "1");
        setTableCellsInputTypeString(driver, sBP, "sTable1", "sTables1Field40","10");
        setTableCellsInputTypeString(driver, sBP, "sTable1", "sTables1Field50","12.02020");
        setTableCellsInputTypeString(driver, sBP, "sTable1", "sTables1Field60","Вартість товару 1");
        setTableCellsInputTypeString(driver, sBP, "sTable1", "sTables1Field70","Додаткове найменування 1");
        addTableRow(driver, sBP, "sTable1");
        setTableCellsInputTypeSelect(driver, sBP, "sTable1","sTables1Field11", "Найменування товару 2");
        setTableCellsInputTypeString(driver, sBP, "sTable1", "sTables1Field21", "Код товару 2");
        setTableCellsInputTypeEnum(driver, sBP, "sTable1","sTables1Field31", "2");
        setTableCellsInputTypeString(driver, sBP, "sTable1", "sTables1Field41","20");
        setTableCellsInputTypeString(driver, sBP, "sTable1", "sTables1Field51","22.02020");
        setTableCellsInputTypeString(driver, sBP, "sTable1", "sTables1Field61","Вартість товару 2");
        setTableCellsInputTypeString(driver, sBP, "sTable1", "sTables1Field71","Додаткове найменування 2");

        setTableCellsInputTypeSelect(driver, sBP, "sTable2","sTables2Field10", "Найменування товару 1");
        // setTableCellsInputTypeFile(driver, sBP, "sTable2", "sTables2Field20", "src/test/resources/files/test.jpg");
        setTableCellsTypeCalendar(driver, sBP, "sTable2","sTables2Field30", "2017/03/05");
        addTableRow(driver, sBP, "sTable1");
        setTableCellsInputTypeSelect(driver, sBP, "sTable2","sTables2Field11", "Найменування товару 2");
        // setTableCellsInputTypeFile(driver, sBP, "sTable2", "sTables2Field21", "src/test/resources/files/test.jpg");
        setTableCellsTypeCalendar(driver, sBP, "sTable2","sTables2Field31", "2017/03/05");
        

        _step("6. Отправка формы");
        click(driver, o.buttonSendingForm);

        _step("7. Проверка сообщения о успешной отправке");
        o.checkMessageSuccess("Шановний(-а) MockUser MockUser!\n" +
                "Ваше звернення х-хххххххх успішно зареєстровано\n" +
                "(номер також відправлено Вам електронною поштою на Ваш e-mail "+email+") Результати будуть спрямовані також на email.\n" +
                "Звертаємо увагу, що Іноді листи потрапляють у спам або у розділ \"Реклама\" (для Gmail).");

        _step("8. Нажать кнопку Выйти");
        click(driver, o.buttonLogOut);
        
    }
}
