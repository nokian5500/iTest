package autoTests;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.NoSuchElementException;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class CustomMethods extends SetupAndTeardown {

    private static char[] chars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',  'а', 'б', 'в', 'г',
            'ґ', 'д', 'е', 'є', 'ж', 'з', 'и', 'і', 'ї', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р',
            'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ь', 'ю', 'я', 'А', 'Б', 'В', 'Г', 'Ґ',
            'Д', 'Е', 'Є', 'Ж', 'З', 'И', 'І', 'Ї', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С',
            'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ю', 'Я', ' ' };

    public static String generateText;

    /**
     * Открыть новое окно
     */
    public void openNewTab() {
        WebElement body = $(By.tagName("body"));
        body.sendKeys(Keys.CONTROL + "t");
    }

    /**
     * Получить текущий день календаря
     * @return
     */
    public Calendar getCurrentCalendar() {
        // http://docs.oracle.com/javase/6/docs/api/java/util/GregorianCalendar.html
        // get the supported ids for GMT+02:00 ("Среднеевропейское (Центральноевропейское) летнее время")
        String[] ids = TimeZone.getAvailableIDs(+2 * 60 * 60 * 1000);
        // if no ids were returned, something is wrong. get out.
        if (ids.length == 0) {
            System.exit(0);
        }
        // create a (CEST - Central Europe Summer Time Zone) UTC/GMT+2 time zone
        SimpleTimeZone GMT = new SimpleTimeZone(+2 * 60 * 60 * 1000, ids[0]);
        // create a GregorianCalendar with the current date and time
        Calendar calendar = new GregorianCalendar(GMT);
        Date trialTime = new Date();
        calendar.setTime(trialTime);
        return calendar;
    }

    /**
     * Удалиение файла/папки
     * @param file
     * @throws IOException
     */
    private static void delete(File file) throws IOException {
        if (file.isDirectory()) {
            //directory is empty, then delete it
            if (file.list().length == 0) {
                file.delete();
                //System.out.println("Directory is deleted : " + file.getAbsolutePath());
            } else {
                //list all the directory contents
                String files[] = file.list();
                for (String temp : files) {
                    //construct the file structure
                    File fileDelete = new File(file, temp);
                    //recursive delete
                    delete(fileDelete);
                }
                //check the directory again, if empty then delete it
                if (file.list().length == 0) {
                    file.delete();
                    //System.out.println("Directory is deleted : " + file.getAbsolutePath());
                }
            }
        } else {
            //if file, then delete it
            file.delete();
        }
    }

    /**
     * Удалить файл или папку
     * @param directory
     */
    public static void deleteFileOrDirectory(File directory) {
        //make sure directory exists
        if (!directory.exists()) {
            //System.out.println("Directory "+directory+" does not exist.");
        } else {
            try {
                delete(directory);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Поставить паузу
     * @param timeout
     */
    public void pause(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Кликнуть по найденному элементу
     * @param selenideElement
     */
    public void click(SelenideElement selenideElement) {
        $(selenideElement).click();
    }

    /**
     * Кликнуть по найденному элементу через xPath
     * @param xpath
     */
    public void clickXpath(String xpath) {
        $(By.xpath(xpath)).click();
    }

    /**
     * Открыть страницу
     * @param url
     */
    public void openURLservice(String url) {
        open(url);
    }

    /*public String getURLdashboard(String serviceName) {
        String Url = getBaseUrl();
        /*if (CV.baseUrl.contains("alpha.test.idoc.com.ua")) {
            Url = "https://alpha.test.idoc.com.ua";
        } else if (CV.baseUrl.contains("beta.test.idoc.com.ua")) {
            Url = "https://beta.test.idoc.com.ua";
        } else if (CV.baseUrl.contains("alpha.test.igov.org.ua")) {
            Url = "https://alpha.test.igov.org.ua";
        } else if (CV.baseUrl.contains("delta.test.igov.org.ua")) {
            Url = "https://delta.test.region.igov.org.ua";
        } else if (CV.baseUrl.contains("gamma.test.idoc.com.ua")) {
            Url = "https://gamma.test.idoc.com.ua";
        } else if (CV.baseUrl.contains("gamma.test.igov.org.ua")) {
            Url = "https://gamma.test.igov.org.ua";
        } else {
            System.out.println("UrlError");
        }

        String dashbordUrl = Url;
        System.out.println("dashbordUrl: " + dashbordUrl);
        return dashbordUrl;
    }*/

    /**
     * Открыть страницу
     * @param serviceName
     */
    public void openURLdashboard(String serviceName) {
        //String dashbordUrl = getURLdashboard(serviceName);
        open(serviceName);
    }

    /**
     * Сравнение текста элемента с необходимым
     * @param selenideElement
     * @param textAssert
     */
    public void assertThis(SelenideElement selenideElement, String textAssert) {
        $(selenideElement).shouldBe(visible);
        Assert.assertEquals(selenideElement.getText(), textAssert);
    }

    /**
     * Сравнение текста элемента с необходимым
     * @param by
     * @param textAssert
     */
    public void assertThis(By by, String textAssert) {
        $(by).shouldBe(visible);
        Assert.assertEquals($(by).getText(), textAssert);
    }

    /**
     * Добавить значкение текстового поля
     * @param cssSelector
     * @param value
     */
    public void setFieldValue(String cssSelector, String value) {
        $(By.xpath("//*[@name='" + cssSelector + "']")).val(value);
    }

    /**
     * Открыть меню навигации
     */
    public void openTab() {
        $(By.xpath("//a[@ng-click='toggleMenu()']")).click();
    }

    /**
     * Добавить значкение текстового поля типа textarea
     * @param cssSelector
     * @param value
     */
    public void setFieldTextArea(String cssSelector, String value) {
        $(By.xpath("//textarea[@name='" + cssSelector + "']")).val(value);
    }

    /**
     * Добавить значкение поля для емейла
     * @param serviceName
     * @param cssSelector
     * @param value
     */
    public void setEmail(String serviceName, String cssSelector, String value) {
        $(By.cssSelector(".form-control.ng-pristine.ng-untouched.ng-scope.ng-invalid.ng-invalid-required." + serviceName + "_--_" + cssSelector)).val(value);
    }

    /**
     * Добавить значкение поля для телефона
     * @param serviceName
     * @param cssSelector
     * @param value
     */
    public void setFieldfieldPhone(String serviceName, String cssSelector, String value) {
        $(By.cssSelector(".form-control.ng-pristine.ng-untouched.ng-scope.ng-invalid.ng-invalid-tel.ng-valid-required." + serviceName + "_--_" + cssSelector)).val(value);
    }

    /**
     *Добавить файл
     * @param serviceName
     * @param cssSelector
     * @param sPathFile
     */
    public void setFieldFile(String serviceName, String cssSelector, String sPathFile) {
        SelenideElement oWebElement = $x("//*[@name='"+ cssSelector + "']//input");

        String sScript = "var element = arguments[0];" + "element.style.display='inline';";
        executeJavaScript(sScript, oWebElement);

        File oFile = new File(sPathFile);
        oWebElement.sendKeys(oFile.getAbsolutePath());

        // Wait attach upload
        if (!oWebElement.isEnabled()) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Полученик текста элемента
     * @param webElement
     * @return
     * @throws Exception
     */
    public String getText(SelenideElement webElement) throws Exception {
        $(webElement).waitUntil(visible, 10000);
        String answer = null;
        try {
            answer = webElement.getText();
        } catch (Exception e) {
            throw new Exception(e);
        }
        return answer;
    }

    /**
     * Клик по объекту
     * @param by
     */
    public void click(By by) {
        $(by).click();
    }

    /**
     * Добавление текста для поля
     * @param cssSelector
     * @param text
     */
    public void setFieldSelectByText(String cssSelector, String text) {
        SelenideElement webElement = $(By.xpath("//*[@id=\"field-" + cssSelector + "\"]/select"));
        Select select = new Select(webElement);
        select.selectByVisibleText(text);

    }

    /**
     * Добавления текста с автокомплитом
     * @param name
     * @param value
     */
    public void setFieldAutocomplete(String name, String value) {
        SelenideElement elem = $(By.xpath("//input[@name='" + name + "']"));
        elem.sendKeys(value);
        elem.pressEnter();
        //$(By.xpath(".//strong[contains(.,'" + value + "')]")).click();               
    }

    /**
     * Добавить дату
     * @param serviceName
     * @param cssSelector
     * @param data
     */
    public void setFieldCalendar(String serviceName, String cssSelector, String data) {
        WebElement webElement = $(By.cssSelector("." + serviceName + "_--_" + cssSelector)).shouldBe(visible);
        executeJavaScript("angular.element(document.getElementsByName('" + cssSelector + "')[0]).removeAttr('readonly');");
        webElement.click();
        webElement.clear();
        webElement.sendKeys(data);
    }

    /**
     * Выбор чекбокса
     * @param cssSelector
     */
    public void setFieldCheckBox(String cssSelector) {
        $(By.cssSelector("#" + cssSelector)).setSelected(true);
    }

    /**
     * Проверка свободных слотов для даты
     */
    public void setFieldSelectSlotDate() {
        Boolean status;
        try {
            WebElement webElement = $(By.xpath("//select[@ng-model='selected.date']")).shouldBe(visible);
            Select select = new Select(webElement);
            select.selectByValue("0"); // выбор первого значения даты из списка
            status = true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            status = false;
        }
        Assert.assertTrue("NO AVAILABLE SLOTS DATE!, NO AVAILABLE SLOTS DATE =!, NO AVAILABLE SLOTS DATE!", status);

    }

    /**
     * Проверка свободных слотов для времени
     */
    public void setFieldSelectSlotTime() {
        Boolean status;
        try {
            WebElement webElement = $(By.xpath("//select[@ng-model='selected.slot']")).shouldBe(visible); //By.xpath("//select[@ng-model='selected.slot']")
            Select select = new Select(webElement);
            select.selectByValue("0"); // выбор первого значения времени из списка
            status = true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            status = false;
        }
        Assert.assertTrue("NO AVAILABLE SLOTS TIME!, NO AVAILABLE SLOTS TIME!, NO AVAILABLE SLOTS TIME!", status);

    }

    /**
     * Заполнить стринговое поле таблицы
     * @param tableName
     * @param cellName
     * @param NameRow
     * @param text
     */
    public void setTableCellsInputTypeString(String tableName, String cellName, String NameRow, String text) {
        $(By.cssSelector("#field-" + tableName + " input[name=" + cellName + NameRow + "]")).sendKeys(text);
    }

    /**
     * Заполнить поле таблицы типа селект
     * @param tableName
     * @param cellName
     * @param NameRow
     * @param item
     */
    public void setTableCellsInputTypeSelect(String tableName, String cellName, String NameRow, String item) {
        $(By.cssSelector("#field-" + tableName + " div[name=" + cellName + NameRow + "]")).click();
        $(By.cssSelector("#ui-select-choices-row" + item + " > a")).click(); //#ui-select-choices-row-1-1 > a #ui-select-choices-row-7-1 > a
    }

    /**
     * Заполнить поле типа селект
     * @param cssSelector
     * @param row
     */
    public void setFieldTypeSelect(String cssSelector, String row) {
        $(By.cssSelector("#field-" + cssSelector)).click();
        $(By.cssSelector("#ui-select-choices-row" + row + " > a")).click(); //#ui-select-choices-row-1-1 > a #ui-select-choices-row-7-1 > a
    }

    /**
     * Заполнить поле таблицы типа перечисления
     * @param tableName
     * @param cellName
     * @param NameRow
     * @param text
     */
    public void setTableCellsInputTypeEnum(String tableName, String cellName, String NameRow, String text) {
        WebElement td = $(By.cssSelector("#field-" + tableName + " select[name=" + cellName + NameRow + "]")).shouldBe(visible);
        td.click();
        Select select = new Select(td);
        select.selectByVisibleText(text);
    }

    /**
     * Заполнить поле таблицы типа календарь
     * @param tableName
     * @param cellName
     * @param NameRow
     * @param date
     */
    public void setTableCellsTypeCalendar(String tableName, String cellName, String NameRow, String date) {
        WebElement td = $(By.cssSelector("#field-" + tableName + " input[name=" + cellName + NameRow + "]")).shouldBe(visible);
        executeJavaScript("angular.element(document.getElementsByName('" + cellName + NameRow + "')[0]).removeAttr('readonly');");
        td.click();
        td.clear();
        td.sendKeys(date);
    }

    /**
     * Заполнить файловое поле таблицы
     * @param serviceName
     * @param tableName
     * @param cellName
     * @param nameRow
     * @param sPathFile
     */
    public void setTableCellsInputTypeFile(String serviceName, String tableName, String cellName, String nameRow, String sPathFile) {
        if (nameRow.equals("0")) {
            WebElement fileInput = $(By.cssSelector("." + serviceName + "_--_" + tableName + "_--_COL_" + cellName + "_--_ROW_" + nameRow + " p[name=" + cellName + "] input"));
            String sScript = "var element = arguments[0];" + "element.style.display='inline';";
            executeJavaScript(sScript, fileInput);
            File oFile = new File(sPathFile);
            fileInput.sendKeys(oFile.getAbsolutePath());
        } else {
            WebElement fileInput = $(By.cssSelector("." + serviceName + "_--_" + tableName + "_--_COL_" + cellName + "_--_ROW_" + nameRow + " p[name=" + cellName + "_" + nameRow + "] input"));
            String sScript = "var element = arguments[0];" + "element.style.display='inline';";
            executeJavaScript(sScript, fileInput);
            File oFile = new File(sPathFile);
            fileInput.sendKeys(oFile.getAbsolutePath());
        }
    }

    /**
     * Добавить таблице строку iGov
     * @param tableName
     */
    public void addTableRow(String tableName) {
        $(By.cssSelector("#field-" + tableName + " a")).click();
    }

    /**
     * Добавить таблице строку iDoc
     * @param tableName
     */
    public void addRegionsTableRow(String tableName) {
        $(By.cssSelector("a." + tableName + "_add_row_button")).scrollIntoView(true).click(); //a[contains(@class,'sTable2_add_row_button')]
    }

    /**
     * Получение номера дока через урл
     * @return
     * @throws Exception
     */
    public String getNumbersIdOrder() throws Exception {
        List<String> ID_Order = ConfigClass.orderId;
        System.out.println(ID_Order.size());
        String sID_Order = ID_Order.get(0);
        System.out.println("sID_Order= " + sID_Order);
        return sID_Order;
    }

    /**
     * Авторизация
     * @param loginName
     * @param passwordName
     */
    public void AuthorizationBySetLoginPassword(String loginName, String passwordName) {
        $(By.name("login")).val(loginName);
        $(By.name("password")).val(passwordName);
        //.pressEnter();
    }

    /**
     * Поиск по номеру документа
     * @param sID_Order
     * @throws Exception
     */
    public void setRegionFindOrder(String sID_Order) throws Exception { // поиск ID_Order
       /* WebElement searchForm = $(By.cssSelector(".form-control.searched-text"));
        String sScript = "$('.form-control.searched-text').val('" + sID_Order + "');";
        executeJavaScript(sScript, searchForm);
        String sScript2 = "$('.btn.btn-default.idoc-search-button').click();";
        executeJavaScript(sScript2, searchForm);*/
        $(By.cssSelector(".form-control.searched-text")).val(sID_Order).pressEnter();
    }

    /**
     * Получение номера дока через элемент заголовка
     * @return
     * @throws Exception
     */
    public String getsID_OrderFromH3element() throws Exception {
        SelenideElement h3Element = $(By.xpath("//h3[contains(.,'№ ')]"));
        String sID_OrderWithSymbolNumber = getText(h3Element);
        String sID_OrderFromH3element = getSubString(sID_OrderWithSymbolNumber, 2, 13);
        return sID_OrderFromH3element;

    }

    /**
     * Поиск по номеру документа
     * @throws Exception
     */
    private void setRegionFindOrder() throws Exception { // поиск ID_Order
        String sID_Order = getNumbersIdOrder();
        WebElement searchForm = $(By.cssSelector(".form-control.searched-text"));
        String sScript = "$('.form-control.searched-text').val('" + sID_Order + "');";
        executeJavaScript(sScript, searchForm);
        String sScript2 = "$('.btn.btn-default.idoc-search-button').click();";
        executeJavaScript(sScript2, searchForm);
    }

    /**
     * Нажать на кнопку с указанным текстом
     * @param nameButton
     */
    public void clickButton(String nameButton) { // нажатие любой кнопки с указанным тескстом на ней
        $(By.xpath("//button[contains(.,'" + nameButton + "')]")).click(); ////button[contains(.,'Опрацювати')]
        //$x("//button").shouldHave(text(nameButton)).click();
    }

    /**
     * Нажать кнопку создать
     */
    public void clickButtonCreate() { // нажатие любой кнопки с указанным тескстом на ней
        $(By.xpath("//button[@ng-click='submitTask(form)']")).click(); ////button[contains(.,'Опрацювати')]
    }

    /**
     * Нажать кнопку создать задачу
     */
    public void clickButtonCreateTask() { // нажатие любой кнопки с указанным тескстом на ней
        $(By.xpath("//button[@ng-click='submit(form)']")).click(); ////button[contains(.,'Опрацювати')]
    }

    /**
     * Нажать на ссылку
     * @param nameLink
     * @throws Exception
     */
    public void clickLink(String nameLink) throws Exception { // нажатие любой кнопки с указанным тескстом на ней
        $(By.xpath("//a[contains(.,'" + nameLink + "')]")).click(); ////button[contains(.,'Опрацювати')]
    }

    /**
     * Нажать на кнопку ЕЦП
     */
    public void clickButtonECP() { // нажатие любой кнопки с указанным тескстом на ней
        $(By.xpath("//button[@ng-disabled='cantSubmit(form)']")).click(); ////button[contains(.,'Опрацювати')]
    }

    /**
     * Поиск по номеру документа
     * @throws Exception
     */
    public void setRegionTask() throws Exception { // поиск ID_Order  в списке с заявками (согласно пребывания на конкретном табе дашборда)
        String sID_Order = getNumbersIdOrder();
        $(By.xpath("//input[@ng-model='tasksSearch.value']")).val(sID_Order).pressEnter();
        pause(8000);
        //$(By.xpath("//span[contains(.,'" + sID_Order + "')]")).shouldBe(visible);
        //$(By.xpath("//button[@class='btn btn-default idoc-search-button']")).click();
    }

    /**
     * Выбор вкладки выбора папки документа iGov
     * @param serviceName
     * @param enumRegionTab
     * @throws Exception
     */
    public void setRegionTab(String serviceName, String enumRegionTab) throws Exception { // навигация по табам navbar в дашборде
        //pause(5000);
        if (serviceName.contains("idoc")) {
            WebElement button = $(By.cssSelector(".btn-group.menu-tabs.ng-scope"));
            String sScript = "$('a:contains(\"" + enumRegionTab + "\")').click()";
            executeJavaScript(sScript, button);
        } else {
            WebElement element = $(By.cssSelector(".navbar.navbar-default.navbar-static-top.i-gov-navbar"));
            if (enumRegionTab.contains("Необроблені")) {
                executeJavaScript("$(\"#unassigned\").click();", element);
            } else if (enumRegionTab.contains("В роботі ")) {
                executeJavaScript("$(\"#selfAssigned\").click();", element);
            } else if (enumRegionTab.contains("Документи")) {
                executeJavaScript("$(\"#documents\").click();", element);
            } else if (enumRegionTab.contains("ЕЦП")) {
                executeJavaScript("$(\"#ecp\").click();", element);
            } else if (enumRegionTab.contains("Мій розклад")) {
                executeJavaScript("$(\"#tickets\").click();", element);
            } else if (enumRegionTab.contains("Історія")) {
                executeJavaScript("$(\"#finished\").click();", element);
            }
        }
    }

    /**
     * Заполнить стринговое поле
     * @param cssSelector
     * @param value
     */
    public void SetRegionFieldInputTypeString(String cssSelector, String value) {
        $(By.cssSelector("input[name='" + cssSelector + "']")).sendKeys(value);
    }

    /**
     * Заполнить поле типа textarea
     * @param cssSelector
     * @param value
     */
    public void SetRegionFieldInputTypeTextArea(String cssSelector, String value) {
        $(By.cssSelector("textarea[name='" + cssSelector + "']")).sendKeys(value);
    }

    /**
     * Заполнить поле типа Long
     * @param cssSelector
     * @param value
     */
    public void SetRegionFieldInputTypeLong(String cssSelector, String value) {
        $(By.cssSelector("input[name='" + cssSelector + "']")).sendKeys(value);;
    }

    /**
     * Заполнить поле типа Double
     * @param cssSelector
     * @param value
     */
    public void SetRegionFieldInputTypeDouble(String cssSelector, String value) {
        $(By.cssSelector("input[name='" + cssSelector + "']")).sendKeys(value);
    }

    /**
     * Заполнить поле типа Date
     * @param cssSelector
     * @param date
     */
    public void SetRegionFieldInputTypeDate(String cssSelector, String date) {
        WebElement webElement = $(By.cssSelector("input[name='" + cssSelector + "']")).shouldBe(visible);
        executeJavaScript("angular.element(document.getElementsByName('" + cssSelector + "')[0]).removeAttr('readonly');");
        webElement.clear();
        webElement.sendKeys(date);
    }

    /**
     * Выбрать файл
     * @param sPathFile
     */
    public void SetRegionFieldInputTypeFile(String sPathFile) {
        WebElement oWebElement = $(By.xpath(".//button[@ng-class=\"{'btn-igov':field && field.value, 'btn-link attach-btn':!field, 'btn-default':field && !field.value}\"]//input"));
        String sScript = "var element = arguments[0];" + "element.style.display='inline';";
        executeJavaScript(sScript, oWebElement);
        File oFile = new File(sPathFile);
        oWebElement.sendKeys(oFile.getAbsolutePath());
    }

    /**
     * Заполнить поле типа Enum
     * @param value
     */
    public void SetRegionFieldInputTypeEnum(String value) {
        $(By.xpath("//option[@label='" + value + "']")).click();
    }

    /**
     * Заполнить поле типа Checkbox
     */
    public void SetRegionFieldInputTypeCheckbox() {
        $(By.xpath("//input[@name='asEnumTypeCheckbox']")).click();
    }

    /**
     * Заполнить табличное поле типа стринг iDoc
     * @param cellName
     * @param NameRow
     * @param text
     */
    public void setRegionTableCellsInputTypeString(String cellName, String NameRow, String text) {
        WebElement td = $(By.xpath("//input[@name='" + cellName + NameRow + "']")).scrollIntoView(true).shouldBe(visible);
        td.click();
        td.clear();
        td.sendKeys(text);
    }

    /**
     * Заполнить табличное поле типа Select iDoc
     * @param cellName
     * @param NameRow
     * @param text
     */
    public void setRegionTableCellsInputTypeEnumSelect(String cellName, String NameRow, String text) {
        WebElement td = $(By.xpath("//select[@name='" + cellName + NameRow + "']")).scrollIntoView(true).shouldBe(visible);
        td.click();
        Select select = new Select(td);
        select.selectByVisibleText(text);
    }

    /**
     * Заполнить табличное поле типа span iDoc
     * @param tableName
     * @param cellName
     * @param NameRow
     * @param text
     */
    public void setRegionTableCellsInputTypeEnumSpan(String tableName, String cellName, String NameRow, String text) {
        $(By.cssSelector(".ng-scope._doc_iTest_test_all_case_--_" + tableName + "_--_COL_" + cellName + "_--_ROW_" + NameRow + " .btn.btn-default.form-control.ui-select-toggle")).scrollIntoView(true).click();
        $(By.xpath("//*[contains(.,'" + text + "')]")).click();
    }

    /**
     * Обрезка части текста
     * @param text
     * @param beginIndex
     * @param endIndex
     * @return
     */
    private String getSubString(String text, int beginIndex, int endIndex) {
        String newtext = text.substring(beginIndex, endIndex);
        System.out.println(newtext);
        return newtext;
    }

    /**
     * Заполнить табличное поле типа enum iDoc
     * @param serviceName
     * @param tableName
     * @param cellName
     * @param NameRow
     * @param text
     * @throws Exception
     */
    public void setRegionTableCellsInputTypeEnumInput(String serviceName, String tableName, String cellName, String NameRow, String text) throws Exception {
        try {
            String textNew = getSubString(text, 0, 3);
            System.out.println(textNew);
            $(By.cssSelector(".ng-scope." + serviceName + "_--_" + tableName + "_--_COL_" + cellName + "_--_ROW_" + NameRow + " .btn.btn-default.form-control.ui-select-toggle")).click();
            $(By.cssSelector(".form-control.ui-select-search.ng-pristine.ng-untouched.ng-valid.ng-empty")).sendKeys(textNew);
            //pause(2000);
            $(By.xpath("//a[contains(.,'" + text + "')]")).click();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Заполнить табличное файловое поле  iDoc
     * @param sBP
     * @param tableName
     * @param cellName
     * @param nameRow
     * @param sPathFile
     * @throws InterruptedException
     */
    public void setRegionTableCellsInputTypeFile(String sBP,String tableName, String cellName, String nameRow, String sPathFile) throws InterruptedException {
        WebElement oWebElement = $(By.xpath("//td[@class='ng-scope "+ sBP +"_--_" + tableName + "_--_COL_" + cellName + "_--_ROW_" + nameRow + "']//*[@id='upload-button']//input")).scrollIntoView(true);
        String sScript = "var element = arguments[0];" + "element.style.display='inline';";
        executeJavaScript(sScript, oWebElement);

        File oFile = new File(sPathFile);
        oWebElement.sendKeys(oFile.getAbsolutePath());

        // Wait attach upload
        //TODO: add counter condition to avoid infinite loop
        while (!oWebElement.isEnabled()) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Thread.sleep(3000);
    }

    /**
     * Заполнить табличное поле типа календарь iDoc
     * @param tableName
     * @param cellName
     * @param NameRow
     * @param date
     * @throws InterruptedException
     */
    public void setRegionTableCellsInputTypeCalendar(String tableName, String cellName, String NameRow, String date) throws InterruptedException {
        WebElement td = $(By.xpath("//td[@class='ng-scope _doc_iTest_test_all_case_--_" + tableName + "_--_COL_" + cellName + "_--_ROW_" + NameRow + "']//input")).shouldBe(visible);
        executeJavaScript("angular.element(document.getElementsByName('" + cellName + NameRow + "')[0]).removeAttr('readonly');");
        td.clear();
        td.sendKeys(date);
        // Thread.sleep(1000);
    }

    /**
     * Вставить данные с клипборда
     * @param string
     */
    public static void setClipboardData(String string) {
        StringSelection stringSelection = new StringSelection(string);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
    }

    /**
     * Загрузить ЕЦП ключ
     * @throws InterruptedException
     * @throws AWTException
     */
    public void uploadECPKeyFile() throws InterruptedException, AWTException {
        File file = new File("src/test/resources/files/Key-6.dat");
        //
        WebElement buttonECP = $(By.xpath("//button[@id='selectDir']"));
        buttonECP.click();
        //
        setClipboardData(file.getAbsolutePath());
        //
        Robot robot = new Robot();
        robot.delay(1000);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.delay(300);
        robot.keyPress(KeyEvent.VK_V);
        robot.delay(300);
        robot.keyRelease(KeyEvent.VK_V);
        robot.delay(300);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.delay(300);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.delay(300);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.delay(300);
    }

    /**
     * Загрузить ЕЦП ключ
     * @param filePath
     * @throws InterruptedException
     * @throws AWTException
     */
    public void uploadECPKeyFile(String filePath) throws InterruptedException, AWTException {
        pause(3000);
        File file = new File(filePath);
        /*String path = file.getAbsolutePath();
        String sScript = "$(\"input[name='eds']\").attr(\"readonly\", false)";
        executeJavaScript(sScript, $x("//input[@name='eds']"));
        sScript = "$(\"input[name='eds']\").attr(\"type\", 'file')";
        executeJavaScript(sScript, $x("//input[@name='eds']"));
        $x("//input[@name='eds']").uploadFile(file);
        //$x("//input[@name='eds']").sendKeys(path);
        sScript = "$(\"input[name='eds']\").attr(\"readonly\", true)";
        executeJavaScript(sScript, $x("//input[@name='eds']"));
        sScript = "$(\"input[name='eds']\").removeAttr(\"type\")";
        executeJavaScript(sScript, $x("//input[@name='eds']"));*/

        //
        WebElement buttonECP = $(By.xpath("//button[@ng-click='chooseEDSFile()']"));
        buttonECP.click();
        pause(5000);
        //
        setClipboardData(file.getAbsolutePath());
        //



        Robot robot = new Robot();
        robot.setAutoDelay(300);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.setAutoDelay(300);
        robot.keyPress(KeyEvent.VK_V);
        robot.setAutoDelay(300);
        robot.keyRelease(KeyEvent.VK_V);
        robot.setAutoDelay(300);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.setAutoDelay(300);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.setAutoDelay(300);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.setAutoDelay(300);
    }

    /**
     * Ввести пароль для ЕЦП
     */
    public void setPaswordForECPKey() {
        WebElement passwordECP = $(By.xpath("//input[@type='password']"));
        passwordECP.sendKeys("12345677");
        pause(10000);
        WebElement clickButtonSubmint = $(By.xpath("//button[@ng-click='findKeys()']"));
        pause(3000);
        clickButtonSubmint.click();
        $(By.xpath("//button[@ng-click='sign()']")).click();
    }

    /**
     * Загрузить ЕЦП ключ скриптом
     * @throws Exception
     */
    public void uploadECPKeyAutoIT() throws Exception {
        WebElement buttonECP = $(By.xpath("//button[@ng-click='chooseEDSFile()']"));
        buttonECP.click();
        Runtime.getRuntime().exec("src\\test\\resources\\files\\UploadKey.exe"); //Key-6.dat
        Thread.sleep(5000);
    }

    /**
     * Нажать на меню выбора
     */
    public void navigateToggleMenu() {
        $(By.cssSelector(".igov-hamburger>a")).click();
    }

    /**
     * Выбор вкладки
     * @param buttonName
     */
    public void snapDrawerButtonMenuTabs(String buttonName) {
        WebElement button = $(By.xpath(".//a[contains(.,'" + buttonName + "')]"));
        $(button).waitUntil(visible, 10000);
        button.click();
    }

    /**
     * Создать документ/задачу
     * @param nameDocumentOrTask
     */
    public void createDocumentOrTask(String nameDocumentOrTask) { //*
        $(By.cssSelector(".btn.btn-default.ng-scope")).click();
        WebElement Element = $(By.xpath("//i[@ng-click='$select.toggle($event)']"));
        Element.click();
        WebElement listElement = $(By.xpath("//span[contains(.,'" + nameDocumentOrTask + "')]"));
        listElement.click();
    }

    /**
     * ВСписок папок задач
     * @param buttonName
     */
    public void choiceMenuList(String buttonName) {
        if (buttonName.contains("Необроблені")) {
            $(By.cssSelector("#unassigned")).click();
        } else if (buttonName.contains("В роботі")) {
            $(By.cssSelector("#selfAssigned")).click();
        } else if (buttonName.contains("Мій розклад")) {
            $(By.cssSelector("#tickets")).click();
        } else if (buttonName.contains("Усі")) {
            $(By.cssSelector("#all")).click();
        } else if (buttonName.contains("Історія")) {
            $(By.cssSelector("#finished")).click();
        } else if (buttonName.contains("На контролі")) {
            $(By.cssSelector("#control")).click();
        }
    }

    /**
     * Поиск по номеру документа/задачи
     * @throws Exception
     */
    public void searchBoxIdoc() throws Exception {
        String sID_Order = getNumbersIdOrder();
        WebElement search = $(By.xpath(".//*[@id='adv-search']/input"));
        $(search).shouldBe(visible);
        search.click();
        search.sendKeys(sID_Order);
        search.sendKeys(Keys.ENTER);

    }

    /**
     * Получить номнр дока с урла текущей страницы
     * @return
     */
    public String getOrderFromUrlCurrentPage() {
        String UrlCurrentPage = url();
        System.out.println("UrlCurrentPage: " + UrlCurrentPage);
        String sOrder = UrlCurrentPage.substring(UrlCurrentPage.indexOf("=") + 1, UrlCurrentPage.indexOf("#"));
        System.out.println("Полученный sID_Order: " + sOrder);
        ConfigClass.orderId.add(sOrder);
        return sOrder;

    }

    /**
     * Поиск документа по номеру
     * @throws Exception
     */
    public void setRegionFindOrderByNumberDocument() throws Exception { // поиск ID_Order
        //String sID_Order = getOrderFromUrlCurrentPage();
        //clickButtonCreate();

        //clickButton("Ok");
        //clickLink("Співробітник2 підрозділу 1.1  ");
        //clickLink("Вийти");

        //AuthorizationBySetLoginPassword("iTest_User_0018", "iTest_User_0018");
        //clickButton("Увійти");
        //clickLink("Нерозглянутi");
        String sID_Order = ConfigClass.orderId.get(0);
        SelenideElement searchForm = $(By.cssSelector("#adv-search input"));
        searchForm.click();
        System.out.println("Вставка sID_Order= " + sID_Order);
        pause(2000);
        searchForm.val(sID_Order).pressEnter();
       // System.out.println("Вставка sID_Order= " + sID_Order);
        //String sScript = "$('#adv-search input').val('" + sID_Order + "')";
        //executeJavaScript(sScript, $(By.cssSelector("#adv-search input")));
        //String sScript2 = "$('.btn.btn-default.idoc-search-button').click();";
        //executeJavaScript(sScript2, searchForm);
        //pause(5000);
    }

    /**
     * Открыть док с ПриватДока
     * @param docName
     */
    public void openDocFromPrivatDoc(String docName) {
        openURLservice(docName);
        pause(10000);
    }

    /**
     * Просканировать док с ПриватДока
     * @throws InterruptedException
     */
    public void scanDocFromPrivatDoc() throws InterruptedException {
        List<SelenideElement> linkList = $$(By.xpath(".//a[@target='_blank']"));
        System.out.println("Count Link on page: " + linkList.size());
        System.out.println("Список приложений у текущего документа: ");

        System.out.println();
        System.out.println("/content?type=.xls: ");

        for (WebElement webElement1 : linkList) {
            if (webElement1.getAttribute("href").contains("/content?type=.xls")) {
                System.out.println(webElement1.getAttribute("href"));
            }
        }
        System.out.println();
        System.out.println("/content?type=.png: ");

        for (WebElement webElement1 : linkList) {
            if (webElement1.getAttribute("href").contains("/content?type=.png")) {
                System.out.println(webElement1.getAttribute("href"));
                webElement1.click();
                System.out.println("Click");
                Thread.sleep(5000);
                webElement1.sendKeys(Keys.CONTROL, "s");
                System.out.println("CONTROL+s");
                webElement1.sendKeys(Keys.ENTER);
                System.out.println("ENTER");
            }
        }

        System.out.println();
        System.out.println("/content?type=.pdf: ");

        for (WebElement webElement1 : linkList) {
            if (webElement1.getAttribute("href").contains("/content?type=.pdf")) {
                System.out.println(webElement1.getAttribute("href"));
            }
        }

        System.out.println();
        System.out.println("/content?type=.doc: ");
        for (WebElement webElement1 : linkList) {
            if (webElement1.getAttribute("href").contains("/content?type=.doc")) {
                System.out.println(webElement1.getAttribute("href"));
            }
        }
    }

    /**
     * Нажать кнопку печати через скрипт
     * @throws IOException
     * @throws InterruptedException
     */
    public void clickButtonPrintAutoIT() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("src\\test\\resources\\files\\clickButtonPrint.exe");
        Thread.sleep(5000);
    }

    /**
     * Нажать кнопку сохранить через скрипт
     * @throws IOException
     * @throws InterruptedException
     */
    public void clickButtonSaveAutoIT() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("src\\test\\resources\\files\\clickButtonSave.exe");
        Thread.sleep(10000);
    }

    /**
     * iDoc
     */

    /**
     * Создать задание
     */
    public void addTask() {
       //SelenideElement task = $(By.xpath("//button[contains(.,'Додати завдання')]"));
       $(By.xpath("//button[@ng-click='addIssue()']")).click();
       pause(5000);
      // System.out.println(task.getText());
      // task.click();
    }

    /**
     * Назначить контроллирующего
     * @param name
     */
    public void setController(String name) {
        ElementsCollection task =  $$x("//div[@ng-model='issue.taskController']");
        int count = task.size()-1;
        String xpath = "//div[@name='taskController"+count+"']";
        $(By.xpath(xpath)).shouldBe(exist).scrollIntoView(true).click();
        $(By.xpath(xpath+"//input")).val(name);
       // $(By.xpath("//span[contains(.,'" + name + "')]")).shouldBe(visible).click();
        ElementsCollection controller = $$x(xpath+"//span[contains(.,'" + name + "')]");
        controller.first().click();
    }

    /**
     * Счетчик задач
     * @return
     */
    private int TaskCount(){
        ElementsCollection task =  $$x("//div[@class='row issue-workers']");
        task.last();
        return task.size();
    }

    /**
     * Назначить исполнителя
     * TODO запилить проврку, чтоб низзя было выбрать одновременно контроллером и исполнителем
     * @param name
     */
    public void setExecutor(String name){
        ElementsCollection executors =  $$x("//*[@ng-model='executor.value']");
        //int count = executors.size();
        SelenideElement currentExecutor = executors.last();
        String nameCurrent = currentExecutor.attr("name");
        String xpath = "//*[@name='"+nameCurrent+"']";
        ElementsCollection executorByXpath =  $$x(xpath);

        int math = executorByXpath.size()-2;
        executorByXpath.get(math).scrollIntoView(true).click();
        executorByXpath = $$x(xpath+"/input[@ng-model='$select.search']");
        executorByXpath.last().val(name);
        $x(xpath + "//a/span[contains(.,'"+name+"')]").click();

        /*
        <span id="textError" class="col-md-12" style="margin-top: 20px; text-align: center; padding: 5px; color: rgb(169, 68, 66);">
        Користувач не може бути одночасно і контролюючим, і виконавцем!</span>
         */
    }

    /**
     * Добавить исполнителя
     * @param name
     */
    public void addNewExecutor(String name){
        //$(By.xpath("//a[@a=ng-click='addNewExecutor($index)']")).click();
        $$(By.xpath("//a[contains(.,'Додати виконавця')]")).last().click();
        setExecutor(name);
    }

    /**
     * Выбор типа отчета по заданию
     * @param type
     */
    public void setTaskForm(String type) {
        $$(By.xpath("//*[@ng-model='issue.taskForm']")).last().click();
       //ElementsCollection form = $$(By.xpath("//*[@ng-model='issue.taskForm']"));
        //form.last().click();
        $$(By.xpath("//*[@ng-model='issue.taskForm']//option[contains(.,'" + type + "')]")).last().click();
    }

    /**
     * Срок задачи
     * @param type
     * @param term
     */
    public void setTaskTerm(String type, String term) {
        int count = $$(By.xpath("//*[@ng-model='issue.taskTerm.property']")).size()-1;
        $(By.xpath("//*[@name='taskTerm" + count +"']")).click();
        $(By.xpath("//*[@name='taskTerm" + count +"']//option[contains(.,'" + type + "')]")).click();
        if (type.equalsIgnoreCase("Календарна дата")) {
            $(By.xpath("//*[@name='taskDate" + count + "']")).shouldBe(exist).sendKeys(term);
        } else {
            $(By.xpath("//*[@name='taskDay" + count + "']")).val(term);
        }
    }

    /**
     * Выставить срок задаче
     * @param term
     */
    public void setTaskTerm(String term) {
            $(By.xpath("//*[@name='taskDate0']")).shouldBe(exist).sendKeys(term);
    }


    /**
     * Выбор главного исполнителя по порядковому номеру
     * @param nExecutor
     */
    public void setMainExecutor(int nExecutor){
        $$x("//input[@ng-model='executor.isMain']").get(nExecutor-1).click();
    }

    /**
     * Выбор последнего исполнителя главным
     */
    public void setLastExecutorAsMain(){
        $$x("//input[@ng-model='executor.isMain']").last().click();
    }

    /**
     * Удаление исполнителя по порядковому номеру
     * @param nExecutor
     */
    public void removeExecutor(int nExecutor){
        $$x("//span[@ng-click='removeExecutor($parent.$parent.$index, $parent.$index)']").get(nExecutor-1).click();
    }

    /**
     * Удаление последнего исполнителя
     */
    public void removeLastExecutor(){
        $$x("//span[@ng-click='removeExecutor($parent.$parent.$index, $parent.$index)']").last().click();
    }

    /**
     * Присвоить название задаче
     * @param name
     */
    public void setTaskName(String name){
        $$(By.xpath("//input[@ng-model='issue.taskName']")).last().val(name);
    }

    /**
     * Тело задачи или дока
     * @param content
     */
    private void setContent(String content, boolean isDoc){
        ElementsCollection body = $$(By.tagName("iframe"));
        int count;
        if(isDoc){
            count = 1;
        }
        else {
            count = body.size();
            if (count == 1){
                String tag = body.first().attr("id");
                count = countByTag(tag);
            }
        }
        $(By.xpath("//*[@id='ui-tinymce-"+count+"_ifr']")).scrollIntoView(true).click();
        switchTo().innerFrame("ui-tinymce-"+count+"_ifr");
        $(By.xpath("//body")).val(content);
        pause(5000);
        switchTo().defaultContent();
    }

    /**
     * Вытягивание цифры с текста
     * @param tag
     * @return
     */
    private int countByTag(String tag){
        tag = tag.replaceAll("[^0-9]", "");
        return Integer.parseInt(tag);
    }

    /**
     * Выставить заголовок дока
     * @param title
     */
    public void setDocTitle(String title){
        $(By.xpath("//*[@id='sTitleDoc']")).scrollIntoView(true).val(title);
    }

    /**
     * Выставить содержимое дока
     * @param content
     */
    public void setDocContent(String content){
        setContent(content, true);
    }

    /**
     * Выставить содержимое задачи
     * @param content
     */
    public void setTaskContent(String content){
        setContent(content, false);
    }

    /**
     * Добавить в таблицу на узгодження
     * @param serviceName
     * @param tableName
     * @param cellName
     * @param NameRow
     * @param text
     */
    public void setAcceptor(String serviceName, String tableName, String cellName, String NameRow, String text){
        setParticipant(serviceName, tableName, cellName, NameRow, text);
    }

    /**
     * Добавить в таблицу на затвердження
     * @param serviceName
     * @param tableName
     * @param cellName
     * @param NameRow
     * @param text
     */
    public void setApprover(String serviceName, String tableName, String cellName, String NameRow, String text){
        setParticipant(serviceName, tableName, cellName, NameRow, text);
    }

    /**
     * Добавить в таблицу на адресацію
     * @param serviceName
     * @param tableName
     * @param cellName
     * @param NameRow
     * @param text
     */
    public void setDirect(String serviceName, String tableName, String cellName, String NameRow, String text){
        setParticipant(serviceName, tableName, cellName, NameRow, text);
    }

    /**
     * Добавить в таблице подписанта
     * @param serviceName
     * @param tableName
     * @param cellName
     * @param NameRow
     * @param text
     */
    private void setParticipant(String serviceName, String tableName, String cellName, String NameRow, String text){
        $(By.xpath("//*[@class='ng-scope " + serviceName + "_--_" + tableName + "_--_COL_" + cellName + "_--_ROW_" + NameRow + "']")).scrollIntoView(true).click();
        $x("//*[@class='ng-scope " + serviceName + "_--_" + tableName + "_--_COL_" + cellName + "_--_ROW_" + NameRow + "']" +
                "//input[@aria-label='Select box']").sendKeys(text);
        //$(By.cssSelector(".form-control.ui-select-search.ng-pristine.ng-untouched.ng-valid.ng-empty")).sendKeys(text);
        //pause(2000);

        $(By.xpath("//div[@class='ui-select-choices-row ng-scope active']//a[contains(.,'" + text + "')]")).click();
    }

    /**
     * Добавить на узгодження с кнопки
     * @param name
     */
    public void addAcceptor(String name){
        String xpath = "//*[ng-if='execCtrlModals.bAddAcceptor']//input";
        addParticipant(xpath, name);
        closeParticipant();
    }

    /**
     * Делегировать с кнопки
     * @param name
     */
    public void addDelegate(String name){
        String xpath = "//*[@id='draggable-dialog']/div/div[2]/delegate-document";
        addParticipant(xpath, name);
    }

    /**
     * Добавить на просмотр с кнопки
     * @param name
     */
    public void addViewer(String name){
        String xpath = "//*[ng-if='execCtrlModals.bAddViewer']//input";
        clickButton("Додати на перегляд");
        addParticipant(xpath, name);
        closeParticipant();
    }

    /**
     * Добавить подписанта с кнопки
     * @param name
     */
    public void addVisor(String name){
        String xpath = "//*[ng-if='execCtrlModals.bAddVisor']//input";
        addParticipant(xpath, name);
        closeParticipant();
    }

    /**
     * Добавить подписанта с кнопки
     * @param xpath
     * @param name
     */
    private void addParticipant(String xpath, String name){
        //xpath = "//*[@id='draggable-dialog']/div/div[2]/delegate-document";
        addWorker(name);
        $x("//*[@id='draggable-dialog']//a/span[contains(.,'"+name+"')]").click();
        $x("//button[contains(.,'Підтвердити')]").click();
    }

    /**
     * Закрыть окно добавления подписанта с кнопки
     */
    private void closeParticipant(){
        clickButton("Ok");
        $x("//i[@class='fa fa-times']").click();
    }

    /**
     * Добавление из выпадающего списка пользователя
     * @param name
     */
    private void addWorker(String name){
        SelenideElement participant;
        //participant.click();
        String xpath = "//div[@placeholder='Введіть від 3-х символів']";
        participant = $x(xpath).scrollIntoView(true);
        participant.click();
        xpath = "//input[@placeholder='Введіть від 3-х символів']";
        participant = $x(xpath);
        participant.val(name);
    }

    /**
     * Выбор отчета по задаче
     * @param type
     * @param value
     */
    public void addReport(String type, String value) {
        clickButton("Додати звiт");
        $x("//select[@id='status']/option[contains(.,'" + type + "')]").click();
        if (type.equalsIgnoreCase("Не виконане") || type.equalsIgnoreCase("Не актуальне")) {
            $x("//textarea[@id='reportText']").val(value);
        } else if (type.equalsIgnoreCase("Виконане")) {
            if($x("//input[@id='sOrder_1']").exists()){
                $x("//input[@id='sOrder_1']").val(value);
            }
            else if($x("//textarea[@id='reportText']").exists()){
                $x("//textarea[@id='reportText']").val(value);
            }
            else if($x("//button[@id='upload-button']").exists()){
                File oFile = new File(value);
                $x("//button[@id='upload-button']/input").sendKeys(oFile.getAbsolutePath());
            }
        }
        clickButton("Пiдтвердити");
    }

    /**
     * Нажать кнопку подписи дока
     */
    public void clickButtonSign(){
        $x("//button[@id='signId']").click();
    }

    /**
     * Нажать кнопку "Підпис не потрібна"
     * @param sBP
     * @param text
     */
    public void clickButtonSignNotNeed(String sBP, String text){
        clickButton("Підпис не потрібен");
        setFieldTextArea("askMessage", text);
        $x("//*[@id='draggable-dialog']/div/div[2]//button[contains(.,'Підпис не потрібен')]").click();
    }

    /**
     * Нажать кнопку подписи "Відмовити"
     * @param sBP
     * @param text
     */
    public void clickButtonRefuse(String sBP, String text){
        clickButton("Відмовити");
        setFieldTextArea("askMessage", text);
        $x("//*[@id='draggable-dialog']/div/div[2]//button[contains(.,'Відмовити')]").click();
    }

    /**
     * Создать задачу
     * @param text
     * @throws Exception
     */
    public void createTask(String text) throws Exception {
        clickLink("Завдання");
        createDocumentOrTask(text);
    }

    /**
     * Делегировать задачу
     * @param name
     * @param term
     * @param coop
     */
    public void delegateTask(String name, String term, String coop){
        clickButton("Делегувати");
        addWorker(name);
        $(By.xpath("//*[@name='execDate']")).shouldBe(exist).sendKeys(term);
        $("#soExec").selectOptionByValue(coop);
        clickButton("Підтвердити");
    }

    /**
     * Попросить перенести задачу
     * @param term
     * @param comment
     */
    public void askForRescheduleTask(String term, String comment){
        clickButton("Перенести");
        $(By.xpath("//*[@name='execDate']")).shouldBe(exist).sendKeys(term);
        $x("//textarea[@id='execText']").val(comment);
        clickButton("Підтвердити");
    }

    /**
     * Открыть документ с задачи
     */
    public void openDocFromTask(){
        clickButton("Відкрити документ");
    }

    /**
     * Перенести задачу
     * @param term
     */
    public void rescheduleTask(String term){
        clickButton("Перенести");
        $(By.xpath("//*[@name='execDate']")).shouldBe(exist).sendKeys(term);
        clickButton("Підтвердити");
    }

    /**
     * Отклонить отчет
     * @param comment
     */
    public void refuseTask(String comment){
        clickButton("Вiдхилити звiт");
        $(By.xpath("//textarea[@id='execText']")).val(comment);
        clickButton("Підтвердити");
    }

    /**
     * Отказать в приеме задачи
     * @param comment
     */
    public void cancelTask(String comment){
        clickButton("Інші дії");
        clickButton("Не прийняти");
    }

    /**
     * Отчет не актуальный
     * @param comment
     */
    public void notActualTask(String comment){
        clickButton("Інші дії");
        clickButton("Неактуально");
    }

    /**
     * Добавить замечание
     * @param comment
     */
    public void addComment(String comment){
        clickButton("Зауваження");
        $(By.xpath("//textarea[@id='askMessage']")).val(comment);
        clickButton("Відправити зауваження");
    }

    /**
     * Ответить на замечание
     * @param comment
     */
    public void answerComment(String comment){
        boolean isAwait=true;
        $x("//a[@ng-click='showConversation = !showConversation']/i[@ng-if='user.sLogin === item.sKeyGroup_Author']").scrollIntoView(true).click();
        if(isAwait){}
        clickButton("Відповісти");
        $(By.xpath("//textarea[@id='askMessage']")).val(comment);
        $x("//*[@id='draggable-dialog']/div/div[2]//button[contains(.,'Відповісти')]").click();
    }

    /**
     * Установить ЕЦП плагин
     * TODO запилить
     * @throws AWTException
     */
    public void installECP() throws AWTException {

        pause(30000);
        String plugin = getBaseUrl() + "/wf/VAADIN/themes/activiti/files/cryptoplugin_ext_id@ff.xpi";
        //openNewTab();
        openURLservice(plugin);
        Robot robot = new Robot();

        pause(100000);
    }

    /**
     * Поиск задачи по тексту
     * @param name
     */
    public void searchTaskByText(String name){
        prepareSearchTask();
        $x("//input[@ng-model='options.searchText']").val(name);
        searchTask();
    }

    /**
     * Поиск задачи по тексту и дате создания
     * @param name
     * @param date
     */
    public void searchTaskByTextAndDateCreate(String name, String date){
        prepareSearchTask();
        $x("//input[@ng-model='options.searchText']").val(name);
        $x("//select[@ng-model='options.dateOption']").click();
        $x("//option[@value='startTime']").click();
        $x("//input[@ng-model='options.startDate']").setValue(date);
        searchTask();
    }

    /**
     * Поиск задачи по тексту и крайней дате исполнения
     * @param name
     * @param date
     */
    public void searchTaskByTextAndDateExecute(String name, String date){
        prepareSearchTask();
        $x("//input[@ng-model='options.searchText']").val(name);
        $x("//select[@ng-model='options.dateOption']").click();
        $x("//option[@value='executionTime']").click();
        $x("//input[@ng-model='options.endDate']").setValue(date);
        searchTask();
    }

    /**
     * Поиск задачи по дате создания
     * @param date
     */
    public void searchTaskByDateCreate(String date){
        prepareSearchTask();
        $x("//select[@ng-model='options.dateOption']").click();
        $x("//option[@value='startTime']").click();
        $x("//input[@ng-model='options.startDate']").setValue(date);
        searchTask();
    }

    /**
     * Поиск задачи крайней дате исполнения
     * @param date
     */
    public void searchTaskByDateExecute(String date){
        prepareSearchTask();
        $x("//select[@ng-model='options.dateOption']").click();
        $x("//option[@value='executionTime']").click();
        $x("//input[@ng-model='options.endDate']").setValue(date);
        searchTask();
    }

    /**
     * Подготовка фильтра
     */
    private void prepareSearchTask(){
        $x("//button[@ng-click='setCurrentTab(sSelectedTask, tabMenu)']").click();
        $x("//select[@ng-model='options.tabSelect']").click();
        $x("//option[@value='TaskAll']").click();
    }

    /**
     * Поиск задачи
     */
    private void searchTask(){
        $x("//input[@type='submit']").click();
        pause(3000);
        SelenideElement seCounter = $x("//div[@ng-if='searchCounter']//span[contains(.,'Знайдено документів:')]");
        String tag = seCounter.getText();
        int counter = countByTag(tag);
        if (counter == 1){
            $x("//span[@class='glyphicon glyphicon-remove close-search-box']").click();
            $x("//a[@ng-click='fillTaskOrderArray()']").click();
        }
    }

    /**
     * Нажать на чекбокс Снять все подписи
     */
    public void removeAllSigns(){
        $("#eRemoveSignes").scrollIntoView(true).shouldNotBe(checked).setSelected(true);
    }

    /**
     * Удалить строку с таблицы
     * @param table
     * @param row
     */
    public void removeRowFromTable(String table, int row){
        String xpath = "//tbody[@ng-form='" + table + "']/tr";
        $x(xpath).scrollIntoView(true);
        //TODO видит Бог, не хотел
        xpath = xpath + "[" + row + "]/td[4]/a/i";
        $x(xpath).click();
    }

    /**
     * Удаление подписанта, добавленного с кнопки
     * @param position
     */
    public void removeParticipant(int position){
        String xPath = "//button[@title='Видалити учасника документу']";
        $x(xPath).scrollIntoView(true);
        ElementsCollection participants = $$x(xPath);
        System.out.println(participants.size());
        participants.get(position-1).click();
        clickButton("Ok");
    }

    /**
     * Автогенерация текста
     * @param length
     * @return
     */
    public static String generateText(int length) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            stringBuilder.append(chars[new Random().nextInt(chars.length)]);
        }
        generateText = stringBuilder.toString();
        return stringBuilder.toString();
    }

    /**
     * Снять подпись с кнопки
     * @param text
     */
    public void cancelSign(String text) {
        clickButton("Зняти підпис");
        SetRegionFieldInputTypeTextArea("askMessage", text);
        $x("//button[@ng-click='cancelDocumentSubmit()']").click();
        pause(5000);
        $x("//strong[contains(.,'Зауваження додано')]").waitUntil(visible, 5000);
        clickButton("Ok");
        $x("//strong[contains(.,'Підпис знято')]").waitUntil(visible, 5000);
        clickButton("Ок");
    }

    /**
     * Увімкнути референтність
     * @param sFIO
     */
    public void turnReferent(String sFIO) {
        $x("//a[@id='accountId']").click();
        ElementsCollection referents = $$x("//label[@ng-click='setsLoginPrincipal(referent.sLogin)']");
        for (SelenideElement referent : referents){
            if (sFIO.equalsIgnoreCase(referent.getText())){
                referent.click();
                break;
            }
        }
    }

    /**
     * метод для определения существует ли и отображается кнопка
     * @param name
     * @return
     */
    private boolean isExistButton(String name){
        return  $x("//button[contains(.,'" + name + "')]").isDisplayed();
    }

    /**
     * Метод проверки существует ли кнопка когда она нужна, и отсутствует ли когда должна
     * @param name
     * @param isAwait
     */
    public void isExistButton(String name, boolean isAwait){
        boolean isExist = isExistButton(name);
        if(isAwait && !isExist){
            throw new ElementNotVisibleException("Кнопка \""+name+"\" має бути");
        }
        if (!isAwait && isExist){
            throw new NoSuchElementException("Кнопки \""+name+"\" не повинно бути");
        }
    }

    /**
     * Получение из sID_Order snID_ProcessActiviti (без номера сервиса, префикса и последней цифры)
     * @param sID_Order
     * @return
     */
    private String getProcessInstanse(String sID_Order){
        String snID_ProcessActiviti = "";
        if (sID_Order != null && sID_Order.contains("-")) {
            long nID_Process_Protected = Long.valueOf(sID_Order.split("-")[1]);
            snID_ProcessActiviti = String.valueOf(nID_Process_Protected / 10);
        }
        return snID_ProcessActiviti;
    }

    /**
     * Полное кудаление документа
     * TODO сделать после подсказки Вани
     */
    public void totallyDeleteProcess(){
        String snID_ProcessActiviti = getProcessInstanse(ConfigClass.orderId.get(0));
    }

    /**
     * Проверка удаленного дока под админом
     * TODO сделать после totallyDeleteProcess
     */
    public void checkDeletedDoc(){}

    /**
     * Проверка последний ли док в списке
     */
    public boolean isLastDoc(){
        return $x("//button[@title='Наступний документ/завдання']").is(disabled);
    }
}
