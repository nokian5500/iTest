package autoTests;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.commands.ScrollTo;
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

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class CustomMethods extends SetupAndTeardown {

    public void openNewTab(WebDriver driver) {
        WebElement body = driver.findElement(By.tagName("body"));
        body.sendKeys(Keys.CONTROL + "t");
    }

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

    public static void delete(File file) throws IOException {
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

    public void pause(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void click(WebElement webElement) {
        $(webElement).click();
    }

    public void clickXpath(String xpath) {
        $(By.xpath(xpath)).click();
    }

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
    public void openURLdashboard(String serviceName) {
        //String dashbordUrl = getURLdashboard(serviceName);
        open(serviceName);
    }

    public void assertThis(WebElement webElement, String textAssert) {
        $(webElement).shouldBe(visible);
        Assert.assertEquals(webElement.getText(), textAssert);
    }

    public void assertThis(By by, String textAssert) {
        $(by).shouldBe(visible);
        Assert.assertEquals($(by).getText(), textAssert);
    }

    public void setFieldValue(String cssSelector, String value) {
        $(By.xpath("//*[@name='" + cssSelector + "']")).val(value);
    }

    public void openTab() {
        $(By.xpath("//a[@ng-click='toggleMenu()']")).click();
    }

    public void setFieldTextArea(String serviceName, String cssSelector, String value) {
        $(By.xpath("//textarea[@name='" + cssSelector + "']")).val(value);
    }

    public void setEmail(String serviceName, String cssSelector, String value) {
        $(By.cssSelector(".form-control.ng-pristine.ng-untouched.ng-scope.ng-invalid.ng-invalid-required." + serviceName + "_--_" + cssSelector)).val(value);
    }

    public void setFieldfieldPhone(String serviceName, String cssSelector, String value) {
        $(By.cssSelector(".form-control.ng-pristine.ng-untouched.ng-scope.ng-invalid.ng-invalid-tel.ng-valid-required." + serviceName + "_--_" + cssSelector)).val(value);
    }

    public void setFieldFile(String serviceName, String cssSelector, String sPathFile) {
        WebElement oWebElement = $(By.cssSelector("." + serviceName + "_--_" + cssSelector + " input"));

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
    }

    public String getText(WebElement webElement) throws Exception {
        $(webElement).waitUntil(visible, 10000);
        String answer = null;
        try {
            answer = webElement.getText();
        } catch (Exception e) {
            throw new Exception(e);
        }
        return answer;
    }

    public void click(By by) {
        $(by).click();
    }

    public void setFieldSelectByText(String cssSelector, String text) {
        WebElement webElement = $(By.xpath("//*[@id=\"field-" + cssSelector + "\"]/select"));
        Select select = new Select(webElement);
        select.selectByVisibleText(text);

    }

    public void setFieldAutocomplete(String name, String value) {
        SelenideElement elem = $(By.xpath("//input[@name='" + name + "']"));
        elem.sendKeys(value);
        elem.pressEnter();
        //$(By.xpath(".//strong[contains(.,'" + value + "')]")).click();               
    }

    public void setFieldCalendar(String serviceName, String cssSelector, String data) {
        WebElement webElement = $(By.cssSelector("." + serviceName + "_--_" + cssSelector)).shouldBe(visible);
        executeJavaScript("angular.element(document.getElementsByName('" + cssSelector + "')[0]).removeAttr('readonly');");
        webElement.click();
        webElement.clear();
        webElement.sendKeys(data);
    }

    public void setFieldCheckBox(String cssSelector) {
        $(By.cssSelector("#" + cssSelector)).setSelected(true);
    }

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

    public void setTableCellsInputTypeString(String tableName, String cellName, String NameRow, String text) {
        $(By.cssSelector("#field-" + tableName + " input[name=" + cellName + NameRow + "]")).sendKeys(text);
    }

    public void setTableCellsInputTypeSelect(String tableName, String cellName, String NameRow, String item) {
        $(By.cssSelector("#field-" + tableName + " div[name=" + cellName + NameRow + "]")).click();
        $(By.cssSelector("#ui-select-choices-row" + item + " > a")).click(); //#ui-select-choices-row-1-1 > a #ui-select-choices-row-7-1 > a
    }

    public void setFieldTypeSelect(String cssSelector, String row) {
        $(By.cssSelector("#field-" + cssSelector)).click();
        $(By.cssSelector("#ui-select-choices-row" + row + " > a")).click(); //#ui-select-choices-row-1-1 > a #ui-select-choices-row-7-1 > a
    }

    public void setTableCellsInputTypeEnum(String tableName, String cellName, String NameRow, String text) {
        WebElement td = $(By.cssSelector("#field-" + tableName + " select[name=" + cellName + NameRow + "]")).shouldBe(visible);
        td.click();
        Select select = new Select(td);
        select.selectByVisibleText(text);
    }

    public void setTableCellsTypeCalendar(String tableName, String cellName, String NameRow, String date) {
        WebElement td = $(By.cssSelector("#field-" + tableName + " input[name=" + cellName + NameRow + "]")).shouldBe(visible);
        executeJavaScript("angular.element(document.getElementsByName('" + cellName + NameRow + "')[0]).removeAttr('readonly');");
        td.click();
        td.clear();
        td.sendKeys(date);
    }

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

    public void addTableRow(String tableName) {
        $(By.cssSelector("#field-" + tableName + " a")).click();
    }

    public void addRegionsTableRow(String tableName) {
        $(By.cssSelector("a." + tableName + "_add_row_button")).click(); //a[contains(@class,'sTable2_add_row_button')]
    }

    public String getNumbersIdOrder() throws Exception {
        List<String> ID_Order = ConfigClass.orderId;
        System.out.println(ID_Order.size());
        String sID_Order = ID_Order.get(0);
        System.out.println("sID_Order= " + sID_Order);
        return sID_Order;
    }

    public void AuthorizationBySetLoginPassword(String loginName, String passwordName) {
        $(By.name("login")).val(loginName);
        $(By.name("password")).val(passwordName);
        //.pressEnter();
    }

    public void setRegionFindOrder(String sID_Order) throws Exception { // поиск ID_Order
       /* WebElement searchForm = $(By.cssSelector(".form-control.searched-text"));
        String sScript = "$('.form-control.searched-text').val('" + sID_Order + "');";
        executeJavaScript(sScript, searchForm);
        String sScript2 = "$('.btn.btn-default.idoc-search-button').click();";
        executeJavaScript(sScript2, searchForm);*/
        $(By.cssSelector(".form-control.searched-text")).val(sID_Order).pressEnter();
    }

    public String getsID_OrderFromH3element() throws Exception {
        WebElement h3Element = $(By.xpath("//h3[contains(.,'№ ')]"));
        String sID_OrderWithSymbolNumber = getText(h3Element);
        String sID_OrderFromH3element = getSubString(sID_OrderWithSymbolNumber, 2, 13);
        return sID_OrderFromH3element;

    }

    public void setRegionFindOrder() throws Exception { // поиск ID_Order
        String sID_Order = getNumbersIdOrder();
        WebElement searchForm = $(By.cssSelector(".form-control.searched-text"));
        String sScript = "$('.form-control.searched-text').val('" + sID_Order + "');";
        executeJavaScript(sScript, searchForm);
        String sScript2 = "$('.btn.btn-default.idoc-search-button').click();";
        executeJavaScript(sScript2, searchForm);
    }

    public void clickButton(String nameButton) { // нажатие любой кнопки с указанным тескстом на ней
        $(By.xpath("//button[contains(.,'" + nameButton + "')]")).click(); ////button[contains(.,'Опрацювати')]
    }

    public void clickButtonCreate() { // нажатие любой кнопки с указанным тескстом на ней
        $(By.xpath("//button[@ng-click='submitTask(form)']")).click(); ////button[contains(.,'Опрацювати')]
    }

    public void clickLink(String nameLink) throws Exception { // нажатие любой кнопки с указанным тескстом на ней
        $(By.xpath("//a[contains(.,'" + nameLink + "')]")).click(); ////button[contains(.,'Опрацювати')]
    }

    public void clickButtonECP() { // нажатие любой кнопки с указанным тескстом на ней
        $(By.xpath("//button[@ng-disabled='cantSubmit(form)']")).click(); ////button[contains(.,'Опрацювати')]
    }

    public void setRegionTask() throws Exception { // поиск ID_Order  в списке с заявками (согласно пребывания на конкретном табе дашборда)
        String sID_Order = getNumbersIdOrder();
        $(By.xpath("//input[@ng-model='tasksSearch.value']")).val(sID_Order).pressEnter();
        pause(8000);
        //$(By.xpath("//span[contains(.,'" + sID_Order + "')]")).shouldBe(visible);
        //$(By.xpath("//button[@class='btn btn-default idoc-search-button']")).click();
    }

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

    public void SetRegionFieldInputTypeString(String cssSelector, String value) {
        $(By.cssSelector("input[name='" + cssSelector + "']")).sendKeys(value);
    }

    public void SetRegionFieldInputTypeTextArea(String cssSelector, String value) {
        $(By.cssSelector("textarea[name='" + cssSelector + "']")).sendKeys(value);
    }

    public void SetRegionFieldInputTypeLong(String cssSelector, String value) {
        $(By.cssSelector("input[name='" + cssSelector + "']")).sendKeys(value);;
    }

    public void SetRegionFieldInputTypeDouble(String cssSelector, String value) {
        $(By.cssSelector("input[name='" + cssSelector + "']")).sendKeys(value);
    }

    public void SetRegionFieldInputTypeDate(String cssSelector, String date) {
        WebElement webElement = $(By.cssSelector("input[name='" + cssSelector + "']")).shouldBe(visible);
        executeJavaScript("angular.element(document.getElementsByName('" + cssSelector + "')[0]).removeAttr('readonly');");
        webElement.clear();
        webElement.sendKeys(date);
    }

    public void SetRegionFieldInputTypeFile(String sPathFile) {
        WebElement oWebElement = $(By.xpath(".//button[@ng-class=\"{'btn-igov':field && field.value, 'btn-link attach-btn':!field, 'btn-default':field && !field.value}\"]//input"));
        String sScript = "var element = arguments[0];" + "element.style.display='inline';";
        executeJavaScript(sScript, oWebElement);
        File oFile = new File(sPathFile);
        oWebElement.sendKeys(oFile.getAbsolutePath());
    }

    public void SetRegionFieldInputTypeEnum(String value) {
        $(By.xpath("//option[@label='" + value + "']")).click();
    }

    public void SetRegionFieldInputTypeCheckbox() {
        $(By.xpath("//input[@name='asEnumTypeCheckbox']")).click();
    }

    public void setRegionTableCellsInputTypeString(String cellName, String NameRow, String text) {
        WebElement td = $(By.xpath("//input[@name='" + cellName + NameRow + "']")).shouldBe(visible);
        td.click();
        td.clear();
        td.sendKeys(text);
    }

    public void setRegionTableCellsInputTypeEnumSelect(String cellName, String NameRow, String text) {
        WebElement td = $(By.xpath("//select[@name='" + cellName + NameRow + "']")).shouldBe(visible);
        td.click();
        Select select = new Select(td);
        select.selectByVisibleText(text);
    }

    public void setRegionTableCellsInputTypeEnumSpan(String tableName, String cellName, String NameRow, String text) {
        $(By.cssSelector(".ng-scope._doc_iTest_test_all_case_--_" + tableName + "_--_COL_" + cellName + "_--_ROW_" + NameRow + " .btn.btn-default.form-control.ui-select-toggle")).click();
        $(By.xpath("//*[contains(.,'" + text + "')]")).click();
    }

    public String getSubString(String text, int beginIndex, int endIndex) {
        String newtext = text.substring(beginIndex, endIndex);
        System.out.println(newtext);
        return newtext;
    }

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

    public void setRegionTableCellsInputTypeFile(String sBP,String tableName, String cellName, String nameRow, String sPathFile) throws InterruptedException {
        WebElement oWebElement = $(By.xpath("//td[@class='ng-scope "+ sBP +"_--_" + tableName + "_--_COL_" + cellName + "_--_ROW_" + nameRow + "']//*[@id='upload-button']//input"));
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

    public void setRegionTableCellsInputTypeCalendar(String tableName, String cellName, String NameRow, String date) throws InterruptedException {
        WebElement td = $(By.xpath("//td[@class='ng-scope _doc_iTest_test_all_case_--_" + tableName + "_--_COL_" + cellName + "_--_ROW_" + NameRow + "']//input")).shouldBe(visible);
        executeJavaScript("angular.element(document.getElementsByName('" + cellName + NameRow + "')[0]).removeAttr('readonly');");
        td.clear();
        td.sendKeys(date);
        // Thread.sleep(1000);
    }

    public static void setClipboardData(String string) {
        StringSelection stringSelection = new StringSelection(string);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
    }

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

    public void uploadECPKeyFile(String filePath) throws InterruptedException, AWTException {
        File file = new File(filePath);
        //
        WebElement buttonECP = $(By.xpath("//button[@ng-click='chooseEDSFile()']"));
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

    public void setPaswordForECPKey() {
        WebElement passwordECP = $(By.xpath("//input[@type='password']"));
        passwordECP.sendKeys("12345677");
        WebElement clickButtonSubmint = $(By.xpath("//button[@ng-click='findKeys()']"));
        clickButtonSubmint.click();
        $(By.xpath("//button[@ng-click='sign()']")).click();
    }

    public void uploadECPKeyAutoIT() throws Exception {
        WebElement buttonECP = $(By.xpath("//button[@ng-click='chooseEDSFile()']"));
        buttonECP.click();
        Runtime.getRuntime().exec("src\\test\\resources\\files\\UploadKey.exe"); //Key-6.dat
        Thread.sleep(5000);
    }

    public void navigateToggleMenu() {
        $(By.cssSelector(".igov-hamburger>a")).click();
    }

    public void snapDrawerButtonMenuTabs(String buttonName) {
        WebElement button = $(By.xpath(".//a[contains(.,'" + buttonName + "')]"));
        $(button).waitUntil(visible, 10000);
        button.click();
    }

    public void createDocumentOrTask(String nameDocumentOrTask) { //*
        $(By.cssSelector(".btn.btn-default.ng-scope")).click();
        WebElement Element = $(By.xpath("//i[@ng-click='$select.toggle($event)']"));
        Element.click();
        WebElement listElement = $(By.xpath("//span[contains(.,'" + nameDocumentOrTask + "')]"));
        listElement.click();
    }

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

    public void searchBoxIdoc() throws Exception {
        String sID_Order = getNumbersIdOrder();
        WebElement search = $(By.xpath(".//*[@id='adv-search']/input"));
        $(search).shouldBe(visible);
        search.click();
        search.sendKeys(sID_Order);
        search.sendKeys(Keys.ENTER);

    }

    public String getOrderFromUrlCurrentPage() {
        String UrlCurrentPage = url();
        System.out.println("UrlCurrentPage: " + UrlCurrentPage);
        String sOrder = UrlCurrentPage.substring(UrlCurrentPage.indexOf("=") + 1, UrlCurrentPage.indexOf("#"));
        System.out.println("Полученный sID_Order: " + sOrder);
        return sOrder;

    }

    public void setRegionFindOrderByNumberDocument() throws Exception { // поиск ID_Order
        String sID_Order = getOrderFromUrlCurrentPage();
        clickButtonCreate();

        clickButton("Ok");
        clickLink("Співробітник2 підрозділу 1.1  ");
        clickLink("Вийти");

        AuthorizationBySetLoginPassword("iTest_User_0018", "iTest_User_0018");
        clickButton("Увійти");
        clickLink("Нерозглянутi");
        WebElement searchForm = $(By.cssSelector("#adv-search input"));
        searchForm.click();
        System.out.println("Вставка sID_Order= " + sID_Order);
        String sScript = "$('#adv-search input').val('" + sID_Order + "')";
        executeJavaScript(sScript, $(By.cssSelector("#adv-search input")));
        String sScript2 = "$('.btn.btn-default.idoc-search-button').click();";
        executeJavaScript(sScript2, searchForm);
        //pause(5000);
    }

    public void openDocFromPrivatDoc(String docName) {
        openURLservice(docName);
        pause(10000);
    }

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

    public void clickButtonPrintAutoIT() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("src\\test\\resources\\files\\clickButtonPrint.exe");
        Thread.sleep(5000);
    }

    public void clickButtonSaveAutoIT() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("src\\test\\resources\\files\\clickButtonSave.exe");
        Thread.sleep(10000);
    }



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

    private int TaskCount(){
        ElementsCollection task =  $$x("//div[@class='row issue-workers']");
        task.last();
        return task.size();
    }

    /**
     * Назначить исполнителя
     * @param name
     */
    public void setExecutor(String name){
        ElementsCollection executors =  $$x("//*[@ng-model='executor.value']");
        //int count = executors.size();
        System.out.println("executors " + executors.size());
        SelenideElement currentExecutor = executors.last();
        String nameCurrent = currentExecutor.attr("name");
        String xpath = "//*[@name='"+nameCurrent+"']";
        ElementsCollection executorByXpath =  $$x(xpath);
        System.out.println(executorByXpath.size());

        int math = executorByXpath.size()-2;
        executorByXpath.get(math).click();
        executorByXpath = $$x(xpath+"/input[@ng-model='$select.search']");
        System.out.println("input " + executorByXpath.size());
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


    public void setMainExecutor(String name){
        SelenideElement parent = $(By.xpath("//span[@title='"+ name+"']")).parent().parent().parent().parent().parent().parent();
        $(By.xpath(parent+"//input[@ng-model='executor.isMain']")).click();
    }

    /**
     * Присвоить название задаче
     * @param name
     */
    public void setTaskName(String name){
        $$(By.xpath("//input[@ng-model='issue.taskName']")).last().val(name);
    }

    /**
     * Тело задачи
     * @param content
     */
    public void setContent(String content, boolean isDoc){
        ElementsCollection body = $$(By.tagName("iframe"));
        int count;
        if(isDoc){
            count = 1;
        }
        else {
            count = body.size();
        }
        $(By.xpath("//*[@id='ui-tinymce-"+count+"_ifr']")).click();
        switchTo().innerFrame("ui-tinymce-"+count+"_ifr");
        $(By.xpath("//body")).val(content);
        System.out.println("test1");
        pause(5000);
        switchTo().defaultContent(); 
        System.out.println("test2");
    }

    public void setDocTitle(String title){
        $(By.xpath("//*[@id='sTitleDoc']")).val(title);
    }

    public void setDocContent(String content){
        setContent(content, true);
    }

    public void setTaskContent(String content){
        setContent(content, false);
    }

    public void setAcceptor(String serviceName, String tableName, String cellName, String NameRow, String text){
        setParticipant(serviceName, tableName, cellName, NameRow, text);
    }

    public void setApprover(String serviceName, String tableName, String cellName, String NameRow, String text){
        setParticipant(serviceName, tableName, cellName, NameRow, text);
    }

    public void setDirect(String serviceName, String tableName, String cellName, String NameRow, String text){
        setParticipant(serviceName, tableName, cellName, NameRow, text);
    }

    private void setParticipant(String serviceName, String tableName, String cellName, String NameRow, String text){
        $(By.xpath("//*[@class='ng-scope " + serviceName + "_--_" + tableName + "_--_COL_" + cellName + "_--_ROW_" + NameRow + "']")).scrollIntoView(true).click();
        $x("//*[@class='ng-scope " + serviceName + "_--_" + tableName + "_--_COL_" + cellName + "_--_ROW_" + NameRow + "']" +
                "//input[@aria-label='Select box']").sendKeys(text);
        //$(By.cssSelector(".form-control.ui-select-search.ng-pristine.ng-untouched.ng-valid.ng-empty")).sendKeys(text);
        //pause(2000);

        $(By.xpath("//div[@class='ui-select-choices-row ng-scope active']//a[contains(.,'" + text + "')]")).click();
    }
}
