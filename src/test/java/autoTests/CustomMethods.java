package autoTests;

import autoTests.TestSiute.HistoryEventType;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import javafx.scene.control.Alert;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import javax.swing.*;
import javax.xml.bind.Element;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.source;
import static com.codeborne.selenide.WebDriverRunner.url;

public class CustomMethods extends SetupAndTeardown {

    //для метода генерации текста
    private static char[] chars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',  'а', 'б', 'в', 'г',
            'ґ', 'д', 'е', 'є', 'ж', 'з', 'и', 'і', 'ї', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р',
            'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ь', 'ю', 'я', 'А', 'Б', 'В', 'Г', 'Ґ',
            'Д', 'Е', 'Є', 'Ж', 'З', 'И', 'І', 'Ї', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С',
            'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ю', 'Я', ' ' };

    //переменная, в которую записывается сгенерированный текст
    public static String generateText;
    //блок переменных счетчиков папок
    private static Integer unwatched;
    private static Integer myDocs;
    private static Integer ecp;
    private static Integer watched;
    //private static Integer history;
    private static Integer control;
    private static Integer execution;
    private static boolean isWaitError = false;
    //private static Integer controlled;
    //private static Integer executed;
    //private static Integer untreated;
    //private static Integer inwork;
    //private static Integer shedule;

    //variable to save names of attachments
    private static ArrayList<String> attachments = new ArrayList<>();

    /**
    Первые данные со счетчика
     */
    public void counterBefore(){
        counter(true, null);
    }

    /**
     * Проверяет изменения счетчиков после события
     * Фильтр может принимать следующие значения:
     * createDoc: Мої документи + 1
     * sign: Нерозглянуті - 1, Переглянуті + 1
     * finishDoc: Мої документи не меняются
     * backToWork: Переглянуті - 1, Нерозглянуті + 1
     * deleteDoc: Мої документи - 1
     * editDoc: ничего не должно поменяться
     * signToHistory: Нерозглянуті - 1
     * <p>
     * createTask: На контролі + 1
     * finishTask: На контролі - 1
     * reportTask: ничего не должно поменяться
     * answerTask: ничего не должно поменяться
     *
     * @param filter
     */
    public void counterAfter(String filter){
        counter(false, filter);
    }

    /**
     * Метод работы со счетчиком, как он работает знает только небо
     * @param isBefore первый вызов метода или нет
     * @param filter какие счетчики должны измениться
     */
    private void counter(boolean isBefore, String filter){
        pause(5000);
        //вкладка на которой мы находимся
        String tab = $x("//a[@class='btn btn-link menu-tab-is-selected']").getText();
        //временные значения счетчиков
        Integer unwatchedTemp = 0;
        Integer myDocsTemp = 0;
        Integer ecpTemp = 0;
        Integer watchedTemp = 0;
        Integer controlTemp = 0;
        Integer executionTemp = 0;


        //булевое значение, тру если разница не ноль
        boolean unwatchedResult;
        boolean myDocsResult;
        boolean ecpResult;
        boolean watchedResult;
        boolean controlResult;
        boolean executionResult;


        if("Документи".equalsIgnoreCase(tab)){
            unwatchedTemp = Integer.valueOf($x("//a[@id='documents']/span").getText());
            myDocsTemp = Integer.valueOf($x("//a[@id='myDocuments']/span").getText());
            ecpTemp = Integer.valueOf($x("//a[@id='ecp']/span").getText());
            watchedTemp = Integer.valueOf($x("//a[@id='viewed']/span").getText());
            //historyTemp = Integer.valueOf($x("//a[@id='docHistory']/span").getText());
            System.out.println(unwatchedTemp);
            System.out.println(myDocsTemp);
            System.out.println(ecpTemp);
            System.out.println(watchedTemp);
            if (isBefore) {
                unwatched = unwatchedTemp;
                myDocs = myDocsTemp;
                ecp = ecpTemp;
                watched = watchedTemp;
            }
            unwatchedResult = unwatchedTemp - unwatched == 0? false : true;
            myDocsResult = myDocsTemp - myDocs == 0? false : true;
            ecpResult = ecpTemp - ecp == 0? false : true;
            watchedResult = watchedTemp - watched == 0? false : true;

            if("createDoc".equalsIgnoreCase(filter)){
                if(myDocsTemp != (myDocs + 1)){
                    screenshot(generateText(10));
                    throw new RuntimeException("Мої документи повинні мати " + (myDocs + 1) +
                            " документів, натомість мають - " + myDocsTemp);
                }
                if(unwatchedResult || ecpResult || watchedResult){
                    screenshot(generateText(10));
                    throw new RuntimeException("Інший лічильник змінився");
                }
            }else if("sign".equalsIgnoreCase(filter)){
                if(unwatchedTemp != (unwatched - 1)){
                    screenshot(generateText(10));
                    throw new RuntimeException("Нерозглянуті  повинні мати " + (unwatched - 1) +
                            " документів, натомість мають - " + unwatchedTemp);
                }
                if(watchedTemp != (watched +1)){
                    screenshot(generateText(10));
                    throw new RuntimeException("Переглянуті  повинні мати " + (watched + 1) +
                            " документів, натомість мають - " + watchedTemp);
                }
                if(myDocsResult || ecpResult){
                    screenshot(generateText(10));
                    throw new RuntimeException("Інший лічильник змінився");
                }
            }else if("finishDoc".equalsIgnoreCase(filter)){
                if(unwatchedResult || myDocsResult || ecpResult || watchedResult){
                    screenshot(generateText(10));
                    throw new RuntimeException("Інший лічильник змінився");
                }
            }else if("backToWork".equalsIgnoreCase(filter)){
                if(unwatchedTemp != (unwatched + 1)){
                    screenshot(generateText(10));
                    throw new RuntimeException("Нерозглянуті  повинні мати " + (unwatched + 1) +
                            " документів, натомість мають - " + unwatchedTemp);
                }
                if(watchedTemp != (watched - 1)){
                    screenshot(generateText(10));
                    throw new RuntimeException("Переглянуті  повинні мати " + (watched - 1) +
                            " документів, натомість мають - " + watchedTemp);
                }
                if(myDocsResult || ecpResult){
                    screenshot(generateText(10));
                    throw new RuntimeException("Інший лічильник змінився");
                }
            }else if("deleteDoc".equalsIgnoreCase(filter)){
                if(myDocsTemp != (myDocs - 1)){
                    screenshot(generateText(10));
                    throw new RuntimeException("Мої документи повинні мати " + (myDocs - 1) +
                            " документів, натомість мають - " + myDocsTemp);
                }
                if(unwatchedResult || ecpResult || watchedResult){
                    screenshot(generateText(10));
                    throw new RuntimeException("Інший лічильник змінився");
                }
            }else if ("signToHistory".equalsIgnoreCase(filter)) {
                if (unwatchedTemp != (unwatched - 1)) {
                    screenshot(generateText(10));
                    throw new RuntimeException("Нерозглянуті  повинні мати " + (unwatched - 1) +
                            " документів, натомість мають - " + unwatchedTemp);
                }
                if(myDocsResult || ecpResult || watchedResult){
                    screenshot(generateText(10));
                    throw new RuntimeException("Інший лічильник змінився");
                }
            }else if ("editDoc".equalsIgnoreCase(filter)) {
                if(unwatchedResult || myDocsResult || ecpResult || watchedResult){
                    screenshot(generateText(10));
                    throw new RuntimeException("Інший лічильник змінився");
                }
            }
        }
        else if("Завдання".equalsIgnoreCase(tab)){
            controlTemp = Integer.valueOf($x("//a[@id='control']/span").getText());
            executionTemp = Integer.valueOf($x("//a[@id='execution']/span").getText());
            System.out.println(controlTemp);
            System.out.println(executionTemp);

            if (isBefore) {
                control = controlTemp;
                execution = executionTemp;
            }
            controlResult = controlTemp - control == 0? false : true;
            executionResult = executionTemp - execution == 0? false : true;

            if ("reportTask".equalsIgnoreCase(filter) || "answerTask".equalsIgnoreCase(filter)) {
                if(controlResult || executionResult){
                    screenshot(generateText(10));
                    throw new RuntimeException("Інший лічильник змінився");
                }
            }  else if ("createTask".equalsIgnoreCase(filter)) {
                if (controlTemp != (control + 1)) {
                    screenshot(generateText(10));
                    throw new RuntimeException("На контролі повинна бути " + (control + 1) +
                            " задач, натомість - " + controlTemp);
                }
                if(executionResult){
                    screenshot(generateText(10));
                    throw new RuntimeException("Інший лічильник змінився");
                }
            } else if ("finishTask".equalsIgnoreCase(filter)) {
                if (controlTemp != (control - 1)) {
                    screenshot(generateText(10));
                    throw new RuntimeException("На контролі повинна бути " + (control - 1) +
                            " задач, натомість - " + controlTemp);
                }
                if(executionResult){
                    screenshot(generateText(10));
                    throw new RuntimeException("Інший лічильник змінився");
                }
            }
        }
    }

    /**
     * Открыть новое окно
     */
    public void openNewTab() throws AWTException {
        //WebElement body = $(By.tagName("body"));
        //body.sendKeys(Keys.CONTROL + "t");
        Robot robot = new Robot();
        robot.delay(1000);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.delay(300);
        robot.keyPress(KeyEvent.VK_T);
        robot.delay(300);
        robot.keyRelease(KeyEvent.VK_T);
        robot.delay(300);
        robot.keyRelease(KeyEvent.VK_CONTROL);
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
        $(By.cssSelector(".form-control.ng-pristine.ng-untouched.ng-scope.ng-invalid.ng-invalid-required." +
                serviceName + "_--_" + cssSelector)).val(value);
    }

    /**
     * Добавить значкение поля для телефона
     * @param serviceName
     * @param cssSelector
     * @param value
     */
    public void setFieldfieldPhone(String serviceName, String cssSelector, String value) {
        $(By.cssSelector(".form-control.ng-pristine.ng-untouched.ng-scope.ng-invalid.ng-invalid-tel.ng-valid-required."
                + serviceName + "_--_" + cssSelector)).val(value);
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
        executeJavaScript("angular.element(document.getElementsByName('" +
                cssSelector + "')[0]).removeAttr('readonly');");
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
        Assert.assertTrue("NO AVAILABLE SLOTS DATE!, NO AVAILABLE SLOTS DATE =!, NO AVAILABLE SLOTS DATE!",
                status);

    }

    /**
     * Проверка свободных слотов для времени
     */
    public void setFieldSelectSlotTime() {
        Boolean status;
        try {
            WebElement webElement = $(By.xpath("//select[@ng-model='selected.slot']")).shouldBe(visible);
            //By.xpath("//select[@ng-model='selected.slot']")
            Select select = new Select(webElement);
            select.selectByValue("0"); // выбор первого значения времени из списка
            status = true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            status = false;
        }
        Assert.assertTrue("NO AVAILABLE SLOTS TIME!, NO AVAILABLE SLOTS TIME!, NO AVAILABLE SLOTS TIME!",
                status);

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
        $(By.cssSelector("#ui-select-choices-row" + item + " > a")).click();
        //#ui-select-choices-row-1-1 > a #ui-select-choices-row-7-1 > a
    }

    /**
     * Заполнить поле типа селект
     * @param cssSelector
     * @param row
     */
    public void setFieldTypeSelect(String cssSelector, String row) {
        $(By.cssSelector("#field-" + cssSelector)).click();
        $(By.cssSelector("#ui-select-choices-row" + row + " > a")).click();
        //#ui-select-choices-row-1-1 > a #ui-select-choices-row-7-1 > a
    }

    /**
     * Заполнить поле таблицы типа перечисления
     * @param tableName
     * @param cellName
     * @param NameRow
     * @param text
     */
    public void setTableCellsInputTypeEnum(String tableName, String cellName, String NameRow, String text) {
        WebElement td = $(By.cssSelector("#field-" + tableName + " select[name=" + cellName + NameRow + "]"))
                .shouldBe(visible);
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
        WebElement td = $(By.cssSelector("#field-" + tableName + " input[name=" + cellName + NameRow + "]"))
                .shouldBe(visible);
        executeJavaScript("angular.element(document.getElementsByName('" + cellName + NameRow +
                "')[0]).removeAttr('readonly');");
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
    public void setTableCellsInputTypeFile(String serviceName, String tableName, String cellName, String nameRow,
                                           String sPathFile) {
        if (nameRow.equals("0")) {
            WebElement fileInput = $(By.cssSelector("." + serviceName + "_--_" + tableName + "_--_COL_" + cellName +
                    "_--_ROW_" + nameRow + " p[name=" + cellName + "] input"));
            String sScript = "var element = arguments[0];" + "element.style.display='inline';";
            executeJavaScript(sScript, fileInput);
            File oFile = new File(sPathFile);
            fileInput.sendKeys(oFile.getAbsolutePath());
        } else {
            WebElement fileInput = $(By.cssSelector("." + serviceName + "_--_" + tableName + "_--_COL_" + cellName +
                    "_--_ROW_" + nameRow + " p[name=" + cellName + "_" + nameRow + "] input"));
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
        $(By.cssSelector("a." + tableName + "_add_row_button")).scrollIntoView(true).click();
        //a[contains(@class,'sTable2_add_row_button')]
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
    public void login(String loginName, String passwordName) {
        $(By.name("login")).val(loginName);
        $(By.name("password")).val(passwordName);
        clickButton("Увійти");
        //.pressEnter();
    }

    /**
     * Выход из учетки
     */
    public void logout() throws Exception {
        pause (2300);
        $("#accountId").click();
        clickLink("Вийти");
        //.pressEnter();
    }


    /**
     * Авторизация
     * @param loginName
     * @param passwordName
     */
    public void AuthorizationBySetLoginPassword(String loginName, String passwordName) {
        $(By.name("login")).val(loginName);
        $(By.name("password")).val(passwordName);
        clickButton("Увійти");
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
        $(By.xpath("//button[contains(.,'" + nameButton + "')]")).scrollIntoView(true).click();
        ////button[contains(.,'Опрацювати')]
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
        $x("//*[@name='taskDate0']").click();
        $x("//input[@id='datetimepicker2']").click();
        $(By.xpath("//button[@ng-click='submit(form)']")).click();
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
    public void setRegionTask() throws Exception {
        // поиск ID_Order  в списке с заявками (согласно пребывания на конкретном табе дашборда)
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
    public void setRegionTab(String serviceName, String enumRegionTab) throws Exception {
        // навигация по табам navbar в дашборде
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
        executeJavaScript("angular.element(document.getElementsByName('" + cssSelector +
                "')[0]).removeAttr('readonly');");
        webElement.clear();
        webElement.sendKeys(date);
    }

    /**
     * Выбрать файл
     * @param sPathFile
     */
    public void SetRegionFieldInputTypeFile(String sPathFile) {
        WebElement oWebElement = $(By.xpath(".//button[@ng-class=\"{'btn-igov':field && field.value, " +
                "'btn-link attach-btn':!field, 'btn-default':field && !field.value}\"]//input"));
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
        WebElement td = $(By.xpath("//input[@name='" + cellName + NameRow + "']")).scrollIntoView(true)
                .shouldBe(visible);
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
        WebElement td = $(By.xpath("//select[@name='" + cellName + NameRow + "']")).scrollIntoView(true)
                .shouldBe(visible);
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
        $(By.cssSelector(".ng-scope._doc_iTest_test_all_case_--_" + tableName + "_--_COL_" + cellName + "_--_ROW_" +
                NameRow + " .btn.btn-default.form-control.ui-select-toggle")).scrollIntoView(true).click();
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
    public void setRegionTableCellsInputTypeEnumInput(String serviceName, String tableName, String cellName,
                                                      String NameRow, String text) throws Exception {
        try {
            String textNew = getSubString(text, 0, 3);
            System.out.println(textNew);
            $(By.cssSelector(".ng-scope." + serviceName + "_--_" + tableName + "_--_COL_" + cellName + "_--_ROW_" +
                    NameRow + " .btn.btn-default.form-control.ui-select-toggle")).click();
            $(By.cssSelector(".form-control.ui-select-search.ng-pristine.ng-untouched.ng-valid.ng-empty"))
                    .sendKeys(textNew);
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
    public void setRegionTableCellsInputTypeFile(String sBP,String tableName, String cellName, String nameRow,
                                                 String sPathFile) throws InterruptedException {
        WebElement oWebElement = $(By.xpath("//td[@class='ng-scope "+ sBP +"_--_" + tableName + "_--_COL_" + cellName +
                "_--_ROW_" + nameRow + "']//*[@id='upload-button']//input")).scrollIntoView(true);
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
    public void setRegionTableCellsInputTypeCalendar(String tableName, String cellName, String NameRow, String date)
            throws InterruptedException {
        WebElement td = $(By.xpath("//td[@class='ng-scope _doc_iTest_test_all_case_--_" + tableName + "_--_COL_" +
                cellName + "_--_ROW_" + NameRow + "']//input")).shouldBe(visible);
        executeJavaScript("angular.element(document.getElementsByName('" + cellName + NameRow +
                "')[0]).removeAttr('readonly');");
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
        //$(By.cssSelector(".igov-hamburger>a")).click();
        $x("//div[@class='igov-hamburger']").scrollIntoView(true).click();
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
        Pattern pattern = Pattern.compile("[\\d]+[-]+[\\d]+");
        Matcher matcher = pattern.matcher(sOrder);
        while (matcher.find()) {
            sOrder = matcher.group(0);
        }
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

        //login("iTest_User_0018", "iTest_User_0018");
        //clickButton("Увійти");
        //clickLink("Нерозглянутi");
        String sID_Order = ConfigClass.orderId.get(0);
        SelenideElement searchForm = $(By.cssSelector("#adv-search input"));
        searchForm.click();
        System.out.println("Вставка sID_Order= " + sID_Order);
        pause(2000);
        searchForm.val(sID_Order).pressEnter();
        pause(5000);
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
        $x(xpath + "//span[contains(.,'"+name+"')]").click();

        /*
        <span id="textError" class="col-md-12" style="margin-top: 20px; text-align:
        center; padding: 5px; color: rgb(169, 68, 66);">
        Користувач не може бути одночасно і контролюючим, і виконавцем!</span>
         */
    }

    /**
     * Добавить исполнителя
     * @param name
     */
    public void addNewExecutor(String name){
        //$(By.xpath("//a[@a=ng-click='addNewExecutor($index)']")).click();
        $$(By.xpath("//a[contains(.,'Додати виконавця')]")).last().scrollIntoView(true).click();
        setExecutor(name);
    }

    /**
     * Выбор типа отчета по заданию
     * @param type
     */
    public void setTaskForm(String type) {
        $$(By.xpath("//*[@ng-model='issue.taskForm']")).last().scrollIntoView(true).click();
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
        int count = $$(By.xpath("//*[@ng-model='issue.taskTerm.value']")).size()-1;
        $(By.xpath("//*[@name='taskTerm" + count +"']")).scrollIntoView(true).click();
        $(By.xpath("//*[@name='taskTerm" + count +"']//option[contains(.,'" + type + "')]")).click();
        if (type.equalsIgnoreCase("Календарна дата")) {
            $(By.xpath("//*[@name='taskDate" + count + "']")).shouldBe(exist).setValue(term);
        } else {
            $(By.xpath("//*[@name='taskDay" + count + "']")).val(term);
        }
    }

    /**
     * Выставить срок задаче
     * @param term
     */
    public void setTaskTerm(String term) {
        String xpath = "//*[@name='taskDate0']";
        $x(xpath).scrollIntoView(true).shouldBe(exist).clear();
        //$x(xpath).setValue(term);
        executeJavaScript("document.getElementsByName('taskDate0')[0].value='" + term + "'");
        /*executeJavaScript("angular.element(document.getElementsByName('taskDate0')[0]).removeAttr('datetimepicker')");
        executeJavaScript("angular.element(document.getElementsByName('taskDate0')[0]).removeAttr('required')");*/
        $x(xpath).click();
        System.out.println($x(xpath).getValue());
    }


    /**
     * Выбор главного исполнителя по порядковому номеру
     * @param nExecutor
     */
    public void setMainExecutor(int nExecutor){
        $$x("//input[@ng-model='executor.isMain']").
                get(nExecutor-1).click();
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
        $$x("//span[@ng-click='removeExecutor($parent.$parent.$index, $parent.$index)']").
                get(nExecutor-1).click();
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
        switchFrame("ui-tinymce-" + count + "_ifr", content);
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
     * Добавить еще одно содержимое дока
     * false отправляем, так как логика та же, что и для задачи в документе,
     * просто для красоты теста лучше такой метод юзать для тела дока,
     * чем setTaskContent
     * @param content
     */
    public void addDocContent(String content){
        setContent(content, false);
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
    public void setParticipant(String serviceName, String tableName, String cellName, String NameRow, String text){
        $(By.xpath("//*[@class='ng-scope " + serviceName + "_--_" + tableName + "_--_COL_" + cellName + "_--_ROW_" +
                NameRow + "']")).scrollIntoView(true).click();
        $x("//*[@class='ng-scope " + serviceName + "_--_" + tableName + "_--_COL_" + cellName +
                "_--_ROW_" + NameRow + "']" +
                "//input[@aria-label='Select box']").sendKeys(text);
        //$(By.cssSelector(".form-control.ui-select-search.ng-pristine.ng-untouched.ng-valid.ng-empty")).sendKeys(text);
        //pause(2000);

        $(By.xpath("//div[@class='ui-select-choices-row ng-scope active']//span/span[contains(.,'" + text + "')]")).click();
    }

    /**
     * Добавить на узгодження с кнопки
     * @param name
     */
    public void addAcceptor(String name){
        String xpath = "//*[ng-if='execCtrlModals.bAddAcceptor']//input";
        clickButton("Додати підписанта");
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
        clickButton("Ознайомити");
        addParticipant(xpath, name);
        closeParticipant();
    }

    /**
     * Добавить подписанта с кнопки
     * @param xpath
     * @param name
     */
    private void addParticipant(String xpath, String name) {
        //xpath = "//*[@id='draggable-dialog']/div/div[2]/delegate-document";
        addWorker(name);
        $x("//*[@id='draggable-dialog']//span/span[contains(.,'"+name+"')]").click();

        if($x("//span[contains(.,'Не можна делегувати на себе')]").exists() ||
                $x("//strong[contains(.,'ПІБ вже існує на цьому кроці')]").exists()) {
            if (isWaitError) {
                xpath = "//button[@class='button.classes']";
                if ($x(xpath).exists()) {
                    $x(xpath).click();
                } else {
                    closeParticipant();
                }
                return;
            } else {
                throw new RuntimeException("На себе дало можливість делегувати");
            }
        }
        /*<span style="color: darkred" ng-if="duplicateUser" class="ng-scope"></span>*/
        $x("//button[contains(.,'Підтвердити')]").click();

    }

    /**
     * Закрыть окно добавления подписанта с кнопки
     */
    private void closeParticipant(){
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
        participant = $$x(xpath).last().scrollIntoView(true);
        participant.click();
        xpath = "//input[@placeholder='Введіть від 3-х символів']";
        participant = $$x(xpath).last();
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
        String frame = $x("//iframe").attr("id");
        if (type.equalsIgnoreCase("Не виконане") || type.equalsIgnoreCase("Не актуальне")) {
            switchFrame(frame, value);
            //$x("//textarea[@id='reportText']").val(value);
        } else if (type.equalsIgnoreCase("Виконане")) {
            if($x("//input[@id='sOrder_1']").exists()){
                $x("//input[@id='sOrder_1']").val(value);
            } else if ($x("//*[@id='" + frame + "']").exists()) {
                switchFrame(frame, value);
            }
            else if($x("//button[@id='upload-button']").exists()){
                File oFile = new File(value);
                $x("//button[@id='upload-button']/input").sendKeys(oFile.getAbsolutePath());
            }
        }
        clickButton("Пiдтвердити");
    }

    private void switchFrame(String frame, String value){
        $(By.xpath("//*[@id='" + frame + "']")).scrollIntoView(true).click();
        switchTo().innerFrame(frame);
        $(By.xpath("//body")).val(value);
        pause(5000);
        switchTo().defaultContent();
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
        clickButton("Інші дії");
        clickButton("Підпис не потрібен");
        setFieldTextArea("askMessage", text);
        $x("//*[@id='draggable-dialog']/div/div[2]//button[contains(.,'Підпис не потрібен')]").click();
        pause(3000);
    }

    /**
     * Нажать кнопку подписи "Відмовити"
     * @param sBP
     * @param text
     */
    public void clickButtonRefuse(String sBP, String text){
        clickButton("Інші дії");
        clickButton("Відмовити");
        setFieldTextArea("askMessage", text);
        $x("//*[@id='draggable-dialog']/div/div[2]//button[contains(.,'Відмовити')]").click();
        pause(3000);
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
        pause(3000);
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
        pause(3000);
    }

    /**
     * Отклонить отчет
     * @param comment
     */
    public void refuseTask(String comment){
        clickButton("Вiдхилити звiт");
        $(By.xpath("//textarea[@id='execText']")).val(comment);
        clickButton("Підтвердити");
        pause(3000);
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

        ElementsCollection chats = $$x("//a[@ng-click='showConversation = !showConversation']/" +
                "i[@ng-if='user.sLogin === item.sKeyGroup_Author']");

        for (SelenideElement chat : chats) {

        }

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
        $x("//input[@ng-model='options.sFind']").val(name);
        searchTask();
        
    }

    /**
     * Поиск задачи по тексту и дате создания
     * @param name
     * @param date
     */
    public void searchTaskByTextAndDateCreate(String name, String date){
        prepareSearchTask();
        $x("//input[@ng-model='options.sFind']").val(name);
        $x("//select[@ng-model='options.sDateType']").selectOption("Період створення");
        $x("//input[@ng-model='options.sDateFrom']").setValue(date);
        searchTask();
    }

    /**
     * Поиск задачи по тексту и крайней дате исполнения
     * @param name
     * @param date
     */
    public void searchTaskByTextAndDateExecute(String name, String date){
        prepareSearchTask();
        $x("//input[@ng-model='options.sFind']").val(name);
        $x("//select[@ng-model='options.sDateType']").selectOption("Період створення");
        $x("//input[@ng-model='options.sDateTo']").setValue(date);
        searchTask();
    }

    /**
     * Поиск задачи по дате создания
     * @param date
     */
    public void searchTaskByDateCreate(String date){
        prepareSearchTask();
        $x("//select[@ng-model='options.sDateType']").selectOption("Період створення");
        $x("//option[@value='startTime']").click();
        $x("//input[@ng-model='options.sDateFrom']").setValue(date);
        searchTask();
    }

    /**
     * Поиск задачи крайней дате исполнения
     * @param date
     */
    public void searchTaskByDateExecute(String date){
        prepareSearchTask();
        $x("//select[@ng-model='options.sDateType']").selectOption("Період створення");
        $x("//input[@ng-model='options.sDateTo']").setValue(date);
        searchTask();
    }

    /**
     * Подготовка фильтра
     */
    private void prepareSearchTask(){
        $x("//button[@ng-click='setCurrentTab(sSelectedTask, tabMenu)']").click();
        $x("//div[@placeholder='Оберіть вкладку...']/span[@ng-click='$select.activate()']").click();
        $x("//ul[@repeat='item in tabsObject | tabsFilter: {tabMenu}']//div[contains(.,'Усі завдання')]")
                .click();
    }

    /**
     * Поиск задачи
     */
    private void searchTask(){
        $x("//input[@type='submit']").click();
        pause(3000);
        SelenideElement seCounter = $x("//div[@ng-if='paramsForFilter']//" +
                "span[contains(.,'Знайдено документів:')]");
        String tag = seCounter.getText();
        int counter = countByTag(tag);
        if (counter == 1){
            //$x("//span[@class='glyphicon glyphicon-remove close-search-box']").click();
            $x("//a[@ng-click='fillTaskOrderArray()']").click();
            pause(5000);
        }
        else{
            throw new NoSuchElementException("Було знайдено " + counter + "задач замість однієї");
        }
    }

    /**
     * Нажать на чекбокс Снять все подписи
     */
    public void removeAllSigns(){
        $("#eRemoveSignes").scrollIntoView(true).shouldNotBe(checked).
                setSelected(true);
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
    public void removeParticipant(int position, boolean isAwait){
        pause(3000);
        String xPath = "//button[@ng-click='removeUserFromDoc(users)']";
        String xPath2 = "//button[@ng-click='removeUserFromDoc(users, true)']";

        boolean isExist;

        try {
            isExist = $x(xPath).scrollIntoView(true).isDisplayed();
        }
        catch (ElementNotVisibleException | ElementNotFound ex){
            isExist = $x(xPath2).scrollIntoView(true).isDisplayed();
            xPath = xPath2;
        }

        if(isAwait && !isExist){
            throw new ElementNotVisibleException("Має бути можливість вилучити підписанта");
        }
        if (!isAwait && isExist){
            throw new NoSuchElementException("Можливості вилучити підписанта не має бути");
        }
        $x(xPath).scrollIntoView(true);
        ElementsCollection participants = $$x(xPath);
        System.out.println(participants.size());
        participants.get(position-1).click();
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
        ElementsCollection buttons = $$x("//button").filterBy(text(name)).filterBy(visible);
        if (buttons.size() > 1){
            screenshot(generateText(10));
            throw new ElementNotVisibleException("Кнопкок з назвою \""+name+"\" - "+ buttons.size() +
                    " штук замість однієї");
        }
        return  buttons.get(0).isDisplayed();
    }

    /**
     * Метод проверки существует ли кнопка когда она нужна, и отсутствует ли когда должна
     * @param name
     * @param isAwait
     */
    public void isExistButton(String name, boolean isAwait){
        boolean isExist = isExistButton(name);
        if(isAwait && !isExist){
            screenshot(generateText(10));
            throw new ElementNotVisibleException("Кнопка \""+name+"\" має бути");
        }
        if (!isAwait && isExist){
            screenshot(generateText(10));
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
     * Удаление документа через запрос, если нема кнопки (например из истории)
     */
    public void deleteProcess(String login, String referent) {
        String snID_ProcessActiviti = getProcessInstanse(ConfigClass.orderId.get(0));
        String request = "/wf/service/common/document/removeDocumentSteps?sLogin=" + login +
                "&sLoginReferent="
                + referent + "&snID_Process_Activiti=" + snID_ProcessActiviti;
        openApi(request);
    }

    /**
     * Запускание эскалации вручную
     * @param nID
     */
    public void runEscalationRule(int nID) {
        String request = "/wf/service/action/escalation/runEscalationRule?nID=" + nID;
        openApi(request);
    }

    private void openApi(String url) {
        String sUrl = getRegionUrl();
        sUrl = sUrl.substring(8);
        String token = "system";
        String auth = "http://" + token + ":" + token + "@";
        String request = auth + sUrl;
        request = request.concat(url);
        open(request);
    }

    /**
     * Проверка удаленного дока под админом
     */
    public void checkDeletedDocByAdmin() throws Exception {
       checkDeletedDocImpl("tester", "tester");
    }
    /**
     * Проверка удаленного дока
     */
    public void checkDeletedDoc(String sLogin, String sFIO) throws Exception {
        checkDeletedDocImpl(sLogin, sFIO);
    }
    /**
     * Проверка удаленного дока
     */
    private void checkDeletedDocImpl(String sLogin, String sFIO) throws Exception {
        login(sLogin, " ");
        clickButton("Увійти");
        setRegionFindOrderByNumberDocument();
        if(!isError()){
            clickLink(sFIO);
            clickLink("Вийти");
        }
    }



    /**
     * Проверка последний ли док в списке
     */
    public boolean isLastDoc(){
        return $x("//button[@title='Наступний документ/завдання']").is(disabled);
    }

    public void setSelect(String sBP, String sSelect, String sText){
        String base = "//div[@class='task-form-property row ng-scope " + sBP +"_--_" + sSelect + "']";
        String selector = base + "//span[@class='ui-select-placeholder text-muted ng-binding']";
        $x(selector).scrollIntoView(true).click();
        selector = base + "//input";
        $x(selector).val(sText);
        selector = base + "//a/span[contains(.,'"+sText+"')]";
        $x(selector).click();
        pause(5000);
    }

    public boolean isError() {
        String xpath = "//div[@window-class='modal-danger']//div[@class='modal-content']";
        pause(3000);
        if($x(xpath).isDisplayed()){
            xpath = xpath + "//strong";
            String error = $x(xpath).getText();
            screenshot(generateText(10));
            throw new NoSuchElementException(error);
        }
        else return false;
    }

    public void clickCross(){
        $x("//div[@ng-click='closeModalByButton()']//i[@class='fa fa-times']").
                click();
    }

    public void scrollTo(String sText){
        $(byText(sText)).scrollIntoView(true);
    }

    public void checkAllAttachmentsFromHTML(){
        ElementsCollection links = $$x("//span[@d='sTextForm']//a");
        for(SelenideElement link : links){
            String attach = link.attr("href");
            attach = parseHref(attach);
            if(!isExistButton(attach)){
                screenshot(generateText(10));
                throw new ElementNotVisibleException("Додатка" + attach +" у таблиці нема");
            }
        }
    }

    public String parseHref(String href){

        String sAttach = "";
        String[] list;

        if(href.contains("/attachment/Mongo/")){
            list = href.split("/");
            System.out.println("len " + list.length);
            System.out.println("list + " + list.toString());
            for(int i = 0; i <= list.length; i++){
                if(list[i].equalsIgnoreCase("Mongo")){
                    sAttach = list[++i];
                    break;
                }
            }
        }
        return sAttach;
    }
    public void openLink(String sForm) {
        SelenideElement elem = $x("//span[@id='" + sForm +"']//a");
       // Action action = new Action();
        //button[@role='presentation']/i[@class='mce-ico mce-i-link']
        if(elem.scrollIntoView(true).isDisplayed()){
            elem.click();
        }else {
            screenshot(generateText(10));
            throw new NoSuchElementException("Посилання відсутнє");
        }
    }
    //href="api/tasks/download/0787b4d3-95f0-415e-8007-8450d5c74e71/attachment/Mongo/coverage-error.log/server/
    // alpha.test.idoc.com.ua"

    public void backPage(){
        back();
    }

    public void forwardPage(){
        forward();
    }

    public String getDate(int amount)  {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, amount);
        return format.format(calendar.getTime());
    }

    public String getTime(Integer amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        if(hours + amount >= 24){
            amount = 23 - hours;
        }
        calendar.add(Calendar.HOUR_OF_DAY, amount);
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        return format.format(calendar.getTime());
    }

    public void loadFileToHTML(String text, String attach) throws AWTException {
        File oFile = new File(attach);
        attach = oFile.getAbsolutePath();
        setClipboardData(attach);
        //switchToBody(true);
        $x("//button[@role='presentation']/i[@class='mce-ico mce-i-link']").scrollIntoView(true).click();
        pause(3000);
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
        robot.keyPress(KeyEvent.VK_TAB);
        robot.delay(300);
        robot.keyRelease(KeyEvent.VK_TAB);
        robot.delay(300);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.delay(300);
        robot.keyRelease(KeyEvent.VK_TAB);
        robot.delay(300);
        //////
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.delay(300);
        robot.keyPress(KeyEvent.VK_A);
        robot.delay(300);
        robot.keyRelease(KeyEvent.VK_A);
        robot.delay(300);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.delay(300);
        robot.keyPress(KeyEvent.VK_DELETE);
        robot.delay(300);
        robot.keyRelease(KeyEvent.VK_DELETE);
        robot.delay(300);
        //////////////
        setClipboardData(text);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.delay(300);
        robot.keyPress(KeyEvent.VK_V);
        robot.delay(300);
        robot.keyRelease(KeyEvent.VK_V);
        robot.delay(300);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        //////////////
        robot.keyPress(KeyEvent.VK_TAB);
        robot.delay(300);
        robot.keyRelease(KeyEvent.VK_TAB);
        robot.delay(300);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.delay(300);
        robot.keyRelease(KeyEvent.VK_TAB);
        robot.delay(300);
        /*robot.keyPress(KeyEvent.VK_TAB);
        robot.delay(300);
        robot.keyRelease(KeyEvent.VK_TAB);
        robot.delay(300);*/
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.delay(300);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.delay(300);
        pause(5000);
    }


    public void setTaskTime(String term) throws AWTException {
        String xpath = "//input[@id='datetimepicker2']";
        $x(xpath).scrollIntoView(true).clear();
        //$x(xpath).setValue(term);
        executeJavaScript("document.getElementById('datetimepicker2').value='" + term + "'");
    }

    public void closePrintform(){
        $x("//div[@ng-click='hideModal()']").click();
    }

    public void closeDoc(){
        $x("//button[@ng-click='historyBack()']").click();
    }

    public void addParticipant(String filter, String fio, boolean isAwait) throws ElementClickInterceptedException {
        isWaitError = isAwait;
        if ("delegate".equalsIgnoreCase(filter)) {
            addDelegate(fio);
        } else if ("accept".equalsIgnoreCase(filter)) {
            addAcceptor(fio);
        } else if ("view".equalsIgnoreCase(filter)) {
            addViewer(fio);
        } else if ("visor".equalsIgnoreCase(filter)) {
            addVisor(fio);
        }
    }

    private String getUserInitials(String sFIO) {
        String sInitial = "";
        try {
            String[] aFIO = sFIO.split(" ");
            String sSurname = aFIO[0];
            String sFirstInitial = aFIO[1].substring(0, 1);
            String sMiddleInitial = aFIO[2].substring(0, 1);
            sInitial = sSurname + " " + sFirstInitial + ". " + sMiddleInitial + ".";
        } catch (Exception e) {
            System.out.println("Error in getUserInitials");
        }
        return sInitial;
    }

    public void checkAttachments(int changes){
        if (attachments.isEmpty()) {
            attachments.addAll(getAttachmentsText());
        }
        if (changes ==0){
            compareAttachments(changes);
        }
        ArrayList<String> attachments = getAttachmentsText();
        ArrayList<String> temp;
        if(changes != 0){
            if (changes > 0){
                temp = attachments;
                temp.removeAll(this.attachments);
                System.out.println(temp);
                this.attachments.addAll(temp);
                changes = 0;
            } else if (changes < 0) {
                temp = this.attachments;
                temp.removeAll(attachments);
                System.out.println(temp);
                this.attachments.removeAll(temp);
                changes = 0;
            }
            compareAttachments(changes);
        }

    }

    private void compareAttachments(int changes){
        ArrayList<String> attachments = getAttachmentsText();
        if ((!attachments.equals(this.attachments) && changes == 0) ||
                (attachments.equals(this.attachments) && changes > 0)) {
            throw new RuntimeException("Додатки не збігаються");
        }
    }

    private ArrayList<String> getAttachmentsText(){
        ArrayList<String> result = new ArrayList<>();
        ElementsCollection attachments = getAttachments();
        for (SelenideElement attachment : attachments){
            result.add(attachment.getText());
        }
        return result;
    }

    private ElementsCollection getAttachments(){
        return $$x("//button[@class='btn btn-default dropdown-toggle ng-binding ng-scope']");
    }

    public void downloadAttach(String attachName) throws FileNotFoundException {
        compareAttachments(0);
        String fileName = "";
        if(this.attachments.contains(attachName)){
            ElementsCollection attachments = getAttachments();
            for (SelenideElement attach : attachments){
                if(attach.getText().equals(attachName)){
                    attach.click();
                    ElementsCollection links = $$x("//a");
                    for (SelenideElement link : links){
                        if(link.getText().equals(attachName)){
                            File file = link.download();
                            fileName = file.getName();
                            deleteFileOrDirectory(file);
                            if (!fileName.equals(attachName)) {
                                throw new RuntimeException("Завантажено інший файл");
                            }
                        }
                    }
                    if (fileName.isEmpty()){
                        throw new RuntimeException("Нічого не завантажено");
                    }
                }
            }
        } else {
            throw new RuntimeException("Цей файл не знайдено");
        }
    }

    public void enterInHistory() {
        $x("//span[contains(.,'Iсторiя документу')]").scrollIntoView(true).click();
    }

    public void searchInHistory(String filter, String sFIO, String sFIOReferent, String sComment,String sNameHuman, String sRole, String sNameDelegate, String sStatus) {
        String sCurrentFIO = getUserInitials(sFIO);
        String sCurrentFIOReferent = getUserInitials(sFIOReferent);
        String sNameHumanFIO = getUserInitials(sNameHuman);
        pause(5000);
        String historyDocumentType = "";
        if (("CreateDoc").equals(filter)) {
            historyDocumentType = HistoryEventType.CREATE_DOCUMENT;
            historyDocumentType = historyDocumentType.replace("[sID_OrderURL]", getOrderFromUrlCurrentPage());

            ElementsCollection events = findAllEvents(historyDocumentType);
            if (events.size() != 1) {
                throw new RuntimeException("Найдено " + events.size() + " записей о создании документа");
            }
        }
        if (("EditDoc").equals(filter)) {
            historyDocumentType = HistoryEventType.EDIT_DOCUMENT;
            historyDocumentType = historyDocumentType.replace("[sName]", sCurrentFIO);
            historyDocumentType = historyDocumentType.replace("[sNameReferent]", sCurrentFIOReferent);
            System.out.println(historyDocumentType);
            ElementsCollection events = findAllEvents(historyDocumentType);
            if (events.size() == 0) {
                throw new RuntimeException("Не найдено записей об редактировании документа");
            }
        }
        if (("ChangeDocStatus").equals(filter)) {
            historyDocumentType = HistoryEventType.STATUS_DOCUMENT;
            ElementsCollection events = findAllEvents(historyDocumentType);
            historyDocumentType = historyDocumentType.replace("[sStatus]", sStatus);
            if (events.size() == 0) {
                throw new RuntimeException("не найдено записей о смене статуса документа");
            }
            if ( ("документ закрито").equals(sStatus) && events.size()>1){
                throw new RuntimeException("Должна быть только одна запись о закрытии документа");
            }
        }

        if (("AddComment").equals(filter)) {
            historyDocumentType = HistoryEventType.ADD_COMMENT;
            historyDocumentType = historyDocumentType.replace("[sName]", sCurrentFIO);
            historyDocumentType = historyDocumentType.replace("[sNameReferent]", sCurrentFIOReferent);
            historyDocumentType = historyDocumentType.replace("[sCommentary]", sComment);
            System.out.println(historyDocumentType);
            ElementsCollection events = findAllEvents(historyDocumentType);
            if (events.size() == 0) {
                throw new RuntimeException("Не найдено записей о добавлении коммента");
            }
        }
        if (("EditComment").equals(filter)) {
            historyDocumentType = HistoryEventType.EDIT_COMMENT;
            historyDocumentType = historyDocumentType.replace("[sName]", sCurrentFIO);
            historyDocumentType = historyDocumentType.replace("[sNameReferent]", sCurrentFIOReferent);
            historyDocumentType = historyDocumentType.replace("[sCommentary]", sComment);
            System.out.println(historyDocumentType);
            ElementsCollection events = findAllEvents(historyDocumentType);
            if (events.size() == 0) {
                throw new RuntimeException("Не найдено записей о редактировании коммента");
            }
        }
        if (("DeleteComment").equals(filter)) {
            historyDocumentType = HistoryEventType.DELETE_COMMENT;
            historyDocumentType = historyDocumentType.replace("[sName]", sCurrentFIO);
            historyDocumentType = historyDocumentType.replace("[sNameReferent]", sCurrentFIOReferent);
            historyDocumentType = historyDocumentType.replace("[sCommentary]", sComment);
            System.out.println(historyDocumentType);
            ElementsCollection events = findAllEvents(historyDocumentType);
            if (events.size() == 0) {
                throw new RuntimeException("Не найдено записей об удалении коммента");
            }
        }
        if (("AddHuman").equals(filter)) {
            historyDocumentType = HistoryEventType.ADD_HUMAN;
            historyDocumentType = historyDocumentType.replace("[sID_OrderURL]", getOrderFromUrlCurrentPage());
            historyDocumentType = historyDocumentType.replace("[sName]", sCurrentFIO);
            historyDocumentType = historyDocumentType.replace("[sNameReferent]", sCurrentFIOReferent);
            historyDocumentType = historyDocumentType.replace("[sNameHuman]", sNameHumanFIO);
            historyDocumentType = historyDocumentType.replace("[sRole]", sRole);
            System.out.println(historyDocumentType);
            ElementsCollection events = findAllEvents(historyDocumentType);
            if (events.size() == 0) {
                throw new RuntimeException("Не найдено записей о добавлении человека");
            }
        }
        if (("ApprovedDoc").equals(filter)) {
            historyDocumentType = HistoryEventType.DOCUMENT_APPROVED;
            historyDocumentType = historyDocumentType.replace("[sName]", sCurrentFIO);
            historyDocumentType = historyDocumentType.replace("[sNameReferent]", sCurrentFIOReferent);
            System.out.println(historyDocumentType);
            ElementsCollection events = findAllEvents(historyDocumentType);
            if (events.size() == 0) {
                throw new RuntimeException("Не найдено записей о согласовании документа");
            }
        }
        if (("DeleteHuman").equals(filter)) {
            historyDocumentType = HistoryEventType.DELETE_HUMAN;
            historyDocumentType = historyDocumentType.replace("[sNameHuman]", sNameHumanFIO);
            System.out.println(historyDocumentType);
            ElementsCollection events = findAllEvents(historyDocumentType);
            if (events.size() == 0) {
                throw new RuntimeException("Не найдено записей об удалении человека");
            }
        }
        if (("FirstSeen").equals(filter)) {
            historyDocumentType = HistoryEventType.FIRST_SEEN;
            historyDocumentType = historyDocumentType.replace("[sName]", sCurrentFIO);
            historyDocumentType = historyDocumentType.replace("[sNameReferent]", sCurrentFIOReferent);
            System.out.println(historyDocumentType);
            ElementsCollection events = findAllEvents(historyDocumentType);
            if (events.size() == 0) {
                throw new RuntimeException("Не найдено записей о первом просмотре документа");
            }
        }
        if (("DelegateDoc").equals(filter)) {
            historyDocumentType = HistoryEventType.DELEGATE;
            historyDocumentType = historyDocumentType.replace("[sName]", sCurrentFIO);
            historyDocumentType = historyDocumentType.replace("[sID_OrderURL]", getOrderFromUrlCurrentPage());
            historyDocumentType = historyDocumentType.replace("[sNameReferent]", sCurrentFIOReferent);
            historyDocumentType = historyDocumentType.replace("[sNameDelegate]", sNameDelegate);
            System.out.println(historyDocumentType);
            ElementsCollection events = findAllEvents(historyDocumentType);
            if (events.size() == 0) {
                throw new RuntimeException("Не найдено записей о делегировании документа");
            }
        }
        if (("SigneDoc").equals(filter)) {
            historyDocumentType = HistoryEventType.SIGNE_DOCUMENT;
            historyDocumentType = historyDocumentType.replace("[sName]", sCurrentFIO);
            historyDocumentType = historyDocumentType.replace("[sNameReferent]", sCurrentFIOReferent);
            System.out.println(historyDocumentType);
            ElementsCollection events = findAllEvents(historyDocumentType);
            if (events.size() == 0) {
                throw new RuntimeException("Не найдено записей о визировании документа");
            }
        }
        if (("CloseDoc").equals(filter)) {
            historyDocumentType = HistoryEventType.CLOSE_DOCUMENT;
            System.out.println(historyDocumentType);
            ElementsCollection events = findAllEvents(historyDocumentType);
            if (events.size() == 0) {
                throw new RuntimeException("Не найдено записей закрытии документа");
            }
        }

    }

    private ElementsCollection findAllEvents(String type) {
        return $$x("//p[contains(.,'" + type + "')]");
    }

    public void clickUrgentStatusAllButton() {
        ElementsCollection urgents = $$x("//input[@title='Позначити увесь крок як екстрений/зняти екстреність з усього кроку']");
        for (SelenideElement urgent : urgents) {
            urgent.scrollIntoView(true).click();
            pause(7000);
        }
    }

    public void clickUrgentStatusSingleButton(Integer id) {
        $$x("//input[@type='checkbox']").get(id).scrollIntoView(true).click();
        System.out.println($$x("//input[@type='checkbox']").get(id));
    }

    public void checkUrgentDoc() {
        pause(5000);
        ElementsCollection documentsType = $$x("//div[@class='idoc-menus-list selected-menu-list']");
        if (documentsType.size() != 1) {
            throw new RuntimeException("Должна быть одна вкладка документов");
        }

        ElementsCollection urgents = $$x("//a[@class='list-group-item igov-tasks-list task urgent_  urgent']");
        int countUD = Integer.valueOf($x("//span[@ng-if='menu.showCount']").getText());
        if (urgents.size() != countUD) {
            throw new RuntimeException("Должно быть " + countUD + " элементов");
        }
    }

    public void checkDelegate() {
        boolean flag = false;
        if ($x("//span[contains(.,'Не можна делегувати на себе')]").exists() && $x("//button[@disabled='disabled']").exists()) {
            flag = true;
            if (flag == false) {
                throw new RuntimeException("На себя нельзя делегировать");
            }

        }
    }
    public void removeTask(){
        WebElement button = $(By.xpath(".//a[contains(.,'Інші дії')]"));
        $(button).waitUntil(visible, 10000);
        button.click();
        clickButton("Редагувати завдання");
        pause(3000);
        $x("//a[@ng-click='remove($index)']").scrollIntoView(true).click();

    }
    public void editComment(String comment){
        $x("//i[@class='glyphicon glyphicon-comment']").click();
        $x("//i[@class='glyphicon glyphicon-pencil ng-scope']").click();
        $(By.xpath("//textarea[@id='askMessage']")).val(comment);
        clickButton("Зберегти зміни");

    }
    public void deleteComment(){
        $x("//i[@class='glyphicon glyphicon-comment']").click();
        $x("//i[@class='glyphicon glyphicon-trash']").click();
        clickButton("Підтвердити");
    }
    public void checkDelegateTaskDoc() {
        boolean flag = true;
        if ($x("//span[contains(.,'Користувач не може бути одночасно і контролюючим і виконавцем!')]").exists() && $x("//button[@disabled='disabled']").exists()) {
            flag = false;
            if (flag == false) {
                throw new RuntimeException("На себя нельзя делегировать");
            }

        }
    }

    public void checkScrollForEmptyFields() {
        pause(3000);
        if (!$(By.xpath("//span[contains(.,'Необхідно заповнити.')]")).has(focused)) {
            throw new RuntimeException("Не прокрутилось до пустого поля");
        }
    }
    public void checkPositionNotNull(){
      ElementsCollection se =  $$x("//input[@type='checkbox']");
    }
}


