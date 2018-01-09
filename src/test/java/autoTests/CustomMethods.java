package autoTests;

import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Desktop.Action;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.http.client.HttpClient;
import org.testng.Assert;
import org.testng.Reporter;
import ru.stqa.selenium.factory.WebDriverFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.TimeUnit;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.openqa.selenium.interactions.Actions;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class CustomMethods extends SetupAndTeardown {

    ConfigurationVariables configVariables = ConfigurationVariables.getInstance();

    //Открыть новую вкладку
    public void openNewTab(WebDriver driver) {
        WebElement body = driver.findElement(By.tagName("body"));
        body.sendKeys(Keys.CONTROL + "t");
    }

    //Закрыть вкладку
    public void closeTab(WebDriver driver) {
        WebElement body = driver.findElement(By.tagName("body"));
        body.sendKeys(Keys.CONTROL + "w");

        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));

    }

    public int getRandomNumber(int n) {
        Random random = new Random();
        int RandomNumber = random.nextInt(n);
        return RandomNumber;
    }

    public void waitForElementRemoved(WebDriver driver, By locator, int timeoutInSeconds, int pollingInterval)
            throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds, pollingInterval);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    /**
     *
     * @param driver
     * @param webElement
     * @param timeoutInSeconds
     * @param pollingInterval
     * @throws Exception
     */
    public void waitForElementRemoved(WebDriver driver, WebElement webElement, int timeoutInSeconds, int pollingInterval)
            throws Exception {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

        boolean flag = true;
        int counter = 0;
        while (flag) {
            if (counter > (int) (timeoutInSeconds * 1000 / pollingInterval)) {
                flag = false;
                throw new Exception("Ошибка во время выполнения теста. "
                        + "В метод waitForElementRemoved передан WebElement "
                        + webElement
                        + " который не удаляется"
                );
            }
            try {
                Thread.sleep(pollingInterval);
                counter++;
                if (!webElement.isDisplayed()) {
                    flag = false;
                }
            } catch (Exception e) {
                flag = false;
            }
        }
        driver.manage().timeouts().implicitlyWait(configVariables.implicitTimeWait, TimeUnit.SECONDS);
    }

    /**
     * Ожидание элемента до его появления
     *
     * @param driver
     * @param locator ожидаемого элемента
     * @param timeoutInSeconds
     * @param pollingInterval
     * @throws Exception
     */
    public void waitForElementPresent(WebDriver driver, By locator, int timeoutInSeconds, int pollingInterval)
            throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds, pollingInterval);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
    }

    /**
     * Ожидание элемента до его появления
     *
     * @param driver
     * @param webElement
     * @param timeoutInSeconds
     * @param pollingInterval
     * @throws Exception
     */
    public void waitForElementPresent(WebDriver driver, WebElement webElement, int timeoutInSeconds, int pollingInterval)
            throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds, pollingInterval);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    /**
     * Получение calendar
     *
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
     *
     *
     * удаление файла по урлу
     */
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

    /**
     * Удаление вайла/директории
     *
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

    //convert from UTF-8 -> internal Java String format
    public String convertFromUTF8(String s) {
        String out = null;
        try {
            out = new String(s.getBytes("Windows-1251"), "UTF-8");
        } catch (java.io.UnsupportedEncodingException e) {
            return null;
        }
        return out;
    }

    //convert from internal Java String format -> UTF-8
    public String convertToUTF8(String s) {
        String out = null;
        try {
            out = new String(s.getBytes("UTF-8"), "Windows-1251");
        } catch (java.io.UnsupportedEncodingException e) {
            return null;
        }
        return out;
    }

    //Проверяем что элемент присутствует и видем
    public void CheckElementPresent(WebElement element) throws InterruptedException {
        Assert.assertEquals(true, element.isDisplayed());
        Assert.assertEquals(true, element.isEnabled());
    }

    public void _step(String stepName) throws Exception {
        Reporter.log("<b>" + stepName + "</b><br>");
    }

    public static void addTestNameToTheReport(String testName, String methodPath) throws Exception {
        methodPath = methodPath.substring(0, methodPath.indexOf("("));

        //получим id теста
        String testId = methodPath.substring(methodPath.lastIndexOf(".") + 1, methodPath.length());

        //отделим имя теста от имени класса символом '#'
        StringBuilder tempPath = new StringBuilder(methodPath);
        methodPath
                = tempPath.substring(0, methodPath.lastIndexOf("."))
                + URLEncoder.encode("#", "UTF8")
                + tempPath.substring(methodPath.lastIndexOf(".") + 1, methodPath.length());

        Reporter.log(
                "<form id = \"" + testId + "form\" action= \"\" method=\"post\">\n"
                + "<font color=\"blue\" size=\"3\">" + testName + "</font>\n"
                + "<input type=\"Submit\" value=\"Выполнить\">\n"
                + "</form> \n"
                + "<script type=\"text/javascript\">\n"
                + "\tvar currentURL = document.URL;\n"
                + "\tcurrentURL = currentURL.substring(0,currentURL.indexOf(\"/HTML_Report/\"));\n"
                + "var jobNameStartIndex = currentURL.indexOf(\"AT.SELENIUM.\");\n"
                + "while(currentURL.lastIndexOf(\"/\") > jobNameStartIndex)\n"
                + "  currentURL = currentURL.substring(0,currentURL.lastIndexOf(\"/\")); "
                + "\tdocument.getElementById('" + testId
                + "form').action = currentURL + \"/buildWithParameters?suiteFile=testng.xml&test=" + methodPath + "\";\n"
                + "</script>"
        );
    }

    /**
     * Добавлениие ошибки в отчет
     *
     * @param testName
     * @throws Exception
     */
    public static void addErrorToTheReport(String testName) throws Exception {
        Reporter.log("<b><font color=\"red\" size=\"3\">" + testName + "</font></b><br>");
    }

    // Method for file attachment
    public void attachDocument(WebElement locator, String document, WebDriver driver) {
        String script = "var element = arguments[0];" + "element.style.display='inline';";
        ((JavascriptExecutor) driver).executeScript(script, locator);

        File file = new File(document);
        locator.sendKeys(file.getAbsolutePath());

        // Wait attach upload
        //TODO: add counter condition to avoid infinite loop
        while (!locator.isEnabled()) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Пауза
     *
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
     * Выполнение клика
     *
     * @param driver
     * @param webElement явно заданный элемент
     */
    public void click(WebDriver driver, WebElement webElement) {
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.click();
    }

    /**
     * *
     * Выполнение клика
     *
     * @param driver
     * @param serviceName
     * @param cssSelector селлектор
     */
    public void click(WebDriver driver, String serviceName, String cssSelector) {
        WebElement webElement = driver.findElement(By.cssSelector(serviceName + cssSelector));
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.click();
    }

    /**
     * *
     * выполнение клика
     *
     * @param driver
     * @param xpath селлектор
     */
    public void clickXpath(WebDriver driver, String xpath) {
        WebElement webElement = driver.findElement(By.xpath(xpath));
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.click();
    }

    /**
     * *
     * Запуск сервиса по урлу. Открытие страницы в браузере
     *
     * @param driver
     * @param url
     */
    public void openURLservice(WebDriver driver, String url) {
        driver.get(url);
    }

    /**
     * Определение урла дашборда в зависимости от сервера централа, на котором
     * была поданна заявка. При добавлении нового сервера добавить логику вниз
     * по приведенному шаблону (см. код)
     *
     * @param driver
     * @param serviceName
     * @return
     */
    public String getURLdashboard(WebDriver driver, String serviceName) {

        String Url = "";
        if (configVariables.baseUrl.contains("alpha.test.idoc.com.ua")) {
            Url = "https://alpha.test.idoc.com.ua";
        } else if (configVariables.baseUrl.contains("beta.test.idoc.com.ua")) {
            Url = "https://beta.test.idoc.com.ua";
        } else if (configVariables.baseUrl.contains("alpha.test.igov.org.ua")) {
            Url = "https://alpha.test.igov.org.ua";
        } else if (configVariables.baseUrl.contains("delta.test.igov.org.ua")) {
            Url = "https://delta.test.region.igov.org.ua";
        } else if (configVariables.baseUrl.contains("gamma.test.idoc.com.ua")) {
            Url = "https://gamma.test.idoc.com.ua";
        } else if (configVariables.baseUrl.contains("gamma.test.igov.org.ua")) {
            Url = "https://gamma.test.igov.org.ua";
        }else {
            System.out.println("UrlError");
        }

        String dashbordUrl = Url;
        System.out.println("dashbordUrl: " + dashbordUrl);
        return dashbordUrl;

    }

    /**
     * Откытие дашборда
     *
     * @param driver
     * @param serviceName - название бизнес процесса
     */
    public void openURLdashboard(WebDriver driver, String serviceName) {
        String dashbordUrl = getURLdashboard(driver, serviceName);
        driver.get(dashbordUrl);
    }

    /**
     * *
     * Проверка на значения
     *
     * @param driver
     * @param webElement
     * @param textAssert
     */
    public void assertThis(WebDriver driver, WebElement webElement, String textAssert) {
        new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(webElement));
        Assert.assertEquals(webElement.getText(), textAssert);
    }

    /**
     * *
     * Заполнение поля
     *
     * @param driver
     * @param serviceName
     * @param cssSelector
     * @param value
     */
    public void setFieldValue(WebDriver driver, String serviceName, String cssSelector, String value) {
        WebElement webElement = driver.findElement(By.xpath("//input[@name='" + cssSelector + "']"));
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.click();
        webElement.clear();
        webElement.sendKeys(value);
    }

    /**
     * ЗАполнение элемента TextArea
     *
     * @param driver
     * @param serviceName
     * @param cssSelector
     * @param value текст для заполнения
     */
    public void setFieldTextArea(WebDriver driver, String serviceName, String cssSelector, String value) {
        WebElement webElement = driver.findElement(By.xpath("//textarea[@name='" + cssSelector + "']"));
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(webElement));
//        webElement.click();
        webElement.clear();
        webElement.sendKeys(value);
    }

    /**
     * Заполнение формы Email
     *
     * @param driver
     * @param serviceName
     * @param cssSelector
     * @param value
     */
    public void setEmail(WebDriver driver, String serviceName, String cssSelector, String value) {
        WebElement webElement = driver.findElement(By.cssSelector(".form-control.ng-pristine.ng-untouched.ng-scope.ng-invalid.ng-invalid-required." + serviceName + "_--_" + cssSelector));
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.click();
        webElement.clear();
        webElement.sendKeys(value);
    }

    /**
     * Заполнение элемента Phone
     *
     * @param driver
     * @param serviceName
     * @param cssSelector
     * @param value
     */
    public void setFieldfieldPhone(WebDriver driver, String serviceName, String cssSelector, String value) {
        WebElement webElement = driver.findElement(By.cssSelector(".form-control.ng-pristine.ng-untouched.ng-scope.ng-invalid.ng-invalid-tel.ng-valid-required." + serviceName + "_--_" + cssSelector));
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.click();
        webElement.clear();
        webElement.sendKeys(value);
    }

    /**
     * Работа с элемнтом File (загрузка файла)
     *
     * @param driver
     * @param serviceName
     * @param cssSelector
     * @param sPathFile
     */
    public void setFieldFile(WebDriver driver, String serviceName, String cssSelector, String sPathFile) {
        WebElement oWebElement = driver.findElement(By.cssSelector("." + serviceName + "_--_" + cssSelector + " input"));
        String sScript = "var element = arguments[0];" + "element.style.display='inline';";
        ((JavascriptExecutor) driver).executeScript(sScript, oWebElement);

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
    //public void attachDocument(WebElement locator, String document, WebDriver driver) {
    //}    

    /**
     * получение текста заключенного в элементе
     *
     * @param driver
     * @param webElement
     * @return
     * @throws Exception
     */
    public String getText(WebDriver driver, WebElement webElement) throws Exception {
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(webElement));
        String answer = null;
        try {
            answer = webElement.getText();
        } catch (Exception e) {
            throw new Exception(e);
        }
        return answer;
    }

    /**
     * Заоплнение текстом
     *
     * @param driver
     * @param serviceName
     * @param cssSelector
     * @param text
     */
    public void setFieldSelectByText(WebDriver driver, String serviceName, String cssSelector, String text) {
        WebElement webElement = driver.findElement(By.xpath("//select[@name='" + cssSelector + "']"));
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(webElement));
        Select select = new Select(webElement);
        select.selectByVisibleText(text);
    }

    /**
     * Работа с элементом type Select
     *
     * @param driver
     * @param serviceName
     * @param cssSelector
     * @param value
     */
    public void setFieldSelectByValue(WebDriver driver, String serviceName, String cssSelector, String value) {
        WebElement webElement = driver.findElement(By.cssSelector("." + serviceName + "_--_" + cssSelector));
        Select select = new Select(webElement);
        select.selectByValue(value);
    }

    /**
     * Автозаполнение
     *
     * @param driver
     * @param name
     * @param value
     */
    public void setFieldAutocomplete(WebDriver driver, String name, String value) {

        WebElement element = driver.findElement(By.xpath("//input[@name='" + name + "']"));
        element.click();
        element.sendKeys(value);
        WebElement elements = driver.findElement(By.xpath(".//strong[contains(.,'" + value + "')]"));
        elements.click();

    }

    /**
     * Заплнение элемента type Calendar
     *
     * @param driver
     * @param serviceName
     * @param cssSelector
     * @param data
     */
    public void setFieldCalendar(WebDriver driver, String serviceName, String cssSelector, String data) {

        WebElement webElement = driver.findElement(By.cssSelector("." + serviceName + "_--_" + cssSelector));
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(webElement));
        ((JavascriptExecutor) driver).executeScript("angular.element(document.getElementsByName('" + cssSelector + "')[0]).removeAttr('readonly');");
        webElement.click();
        webElement.clear();
        webElement.sendKeys(data);

    }

    /**
     * Работа с элементом CheckBox. при вкюченном элементе снимет "галку", при
     * выключенном - поставит
     *
     * @param driver
     * @param serviceName
     * @param cssSelector
     */
    public void setFieldCheckBox(WebDriver driver, String serviceName, String cssSelector) {
        WebElement webElement = driver.findElement(By.cssSelector("#" + cssSelector)); // //*[@id="bFavorite11"] //*[@id="field-bWrite"]/div
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.click();
    }

    /**
     * Работа с очередями. Дата
     *
     * @param driver
     * @param serviceName
     * @param cssSelector
     */
    public void setFieldSelectSlotDate(WebDriver driver, String serviceName, String cssSelector) {
        Boolean status;
        try {
            WebElement webElement = driver.findElement(By.xpath("//select[@ng-model='selected.date']"));
            new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(webElement));
            Select select = new Select(webElement);
            select.selectByValue("0"); // выбор первого значения даты из списка
            status = true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            status = false;
        }
        Assert.assertTrue(status, "NO AVAILABLE SLOTS DATE!, NO AVAILABLE SLOTS DATE =!, NO AVAILABLE SLOTS DATE!");

    }

    /**
     * Работа с очередями. Время
     *
     * @param driver
     * @param serviceName
     * @param cssSelector
     */
    public void setFieldSelectSlotTime(WebDriver driver, String serviceName, String cssSelector) {
        Boolean status;
        try {
            WebElement webElement = driver.findElement(By.xpath("//select[@ng-model='selected.slot']")); //By.xpath("//select[@ng-model='selected.slot']")
            new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(webElement));
            Select select = new Select(webElement);
            select.selectByValue("0"); // выбор первого значения времени из списка
            status = true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            status = false;
        }
        Assert.assertTrue(status, "NO AVAILABLE SLOTS TIME!, NO AVAILABLE SLOTS TIME!, NO AVAILABLE SLOTS TIME!");

    }

    // Методы для заполнения HTML-элементов на централе
    /**
     * Работа с ячейкой таблицы. тип ячейки String
     *
     * @param driver
     * @param serviceName имя бизнес процесса
     * @param tableName имя таблицы
     * @param cellName имя ячейки
     * @param NameRow имя ряда
     * @param text
     */
    public void setTableCellsInputTypeString(WebDriver driver, String serviceName, String tableName, String cellName, String NameRow, String text) {
        WebElement td = driver.findElement(By.cssSelector("#field-" + tableName + " input[name=" + cellName + NameRow + "]"));
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(td));
        td.click();
        td.clear();
        td.sendKeys(text);
    }

    /**
     * *
     * Работа с ячейкой таблицы. тип ячейки Select
     *
     * @param driver
     * @param serviceName имя бизнес процесса
     * @param tableName имя таблицы
     * @param cellName имя ячейки
     * @param NameRow имя ряда
     * @param item имя єлемента списка
     */
    public void setTableCellsInputTypeSelect(WebDriver driver, String serviceName, String tableName, String cellName, String NameRow, String item) {
        WebElement td = driver.findElement(By.cssSelector("#field-" + tableName + " div[name=" + cellName + NameRow + "]"));
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(td));
        td.click();
        driver.findElement(By.cssSelector("#ui-select-choices-row" + item + " > a")).click(); //#ui-select-choices-row-1-1 > a #ui-select-choices-row-7-1 > a

    }

    /**
     * Работа с элементом тип Select
     *
     * @param driver
     * @param serviceName
     * @param cssSelector
     * @param row
     */
    public void setFieldTypeSelect(WebDriver driver, String serviceName, String cssSelector, String row) {
        WebElement webElement = driver.findElement(By.cssSelector("#field-" + cssSelector));
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.click();
        driver.findElement(By.cssSelector("#ui-select-choices-row" + row + " > a")).click(); //#ui-select-choices-row-1-1 > a #ui-select-choices-row-7-1 > a

    }

    /**
     * Работа с ячейкой таблицы. тип ячейки Enum
     *
     * @param driver
     * @param serviceName
     * @param tableName
     * @param cellName
     * @param NameRow
     * @param text
     */
    public void setTableCellsInputTypeEnum(WebDriver driver, String serviceName, String tableName, String cellName, String NameRow, String text) {
//        WebElement td = driver.findElement(By.cssSelector("#field-"+tableName+" option[value=\""+value+"\"]"));
        WebElement td = driver.findElement(By.cssSelector("#field-" + tableName + " select[name=" + cellName + NameRow + "]"));
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(td));
        td.click();
        Select select = new Select(td);
        select.selectByVisibleText(text);
    }

    /**
     * Работа с ячейкой таблицы. тип ячейки Select
     *
     * @param driver
     * @param serviceName
     * @param cssSelector
     */
    public void setTableCellsSelectUp(WebDriver driver, String serviceName, String cssSelector) {
        WebElement td = driver.findElement(By.cssSelector(cssSelector));
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(td));
//        td.click();
        Select select = new Select(td);

    }

    /**
     * Работа с ячейкой таблицы. тип ячейки Calendar
     *
     * @param driver
     * @param serviceName
     * @param tableName
     * @param cellName
     * @param NameRow
     * @param date
     */
    public void setTableCellsTypeCalendar(WebDriver driver, String serviceName, String tableName, String cellName, String NameRow, String date) {

        WebElement td = driver.findElement(By.cssSelector("#field-" + tableName + " input[name=" + cellName + NameRow + "]"));
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(td));
        ((JavascriptExecutor) driver).executeScript("angular.element(document.getElementsByName('" + cellName + NameRow + "')[0]).removeAttr('readonly');");
        td.click();
        td.clear();
        td.sendKeys(date);

    }

    /**
     * Работа с ячейкой таблицы. тип ячейки File
     *
     * @param driver
     * @param serviceName
     * @param tableName
     * @param cellName
     * @param nameRow
     * @param sPathFile
     */
    public void setTableCellsInputTypeFile(WebDriver driver, String serviceName, String tableName, String cellName, String nameRow, String sPathFile) {
        if (nameRow.equals("0")) {
            WebElement fileInput = driver.findElement(By.cssSelector("." + serviceName + "_--_" + tableName + "_--_COL_" + cellName + "_--_ROW_" + nameRow + " p[name=" + cellName + "] input"));
            String sScript = "var element = arguments[0];" + "element.style.display='inline';";
            ((JavascriptExecutor) driver).executeScript(sScript, fileInput);

            File oFile = new File(sPathFile);
            fileInput.sendKeys(oFile.getAbsolutePath());
        } else {
            WebElement fileInput = driver.findElement(By.cssSelector("." + serviceName + "_--_" + tableName + "_--_COL_" + cellName + "_--_ROW_" + nameRow + " p[name=" + cellName + "_" + nameRow + "] input"));
            String sScript = "var element = arguments[0];" + "element.style.display='inline';";
            ((JavascriptExecutor) driver).executeScript(sScript, fileInput);

            File oFile = new File(sPathFile);
            fileInput.sendKeys(oFile.getAbsolutePath());
        }

    }

    /**
     * *
     * Добавление ряда в таблице централа
     *
     * @param driver
     * @param serviceName
     * @param tableName имя таблицы, в котором добовляют ряд
     */
    public void addTableRow(WebDriver driver, String serviceName, String tableName) {
        WebElement td = driver.findElement(By.cssSelector("#field-" + tableName + " a"));
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(td));
        td.click();
    }

    /**
     * Добавление ряда в таблице региона
     *
     * @param driver
     * @param serviceName
     * @param tableName имя таблицы, в котором добовляют ряд
     */
    public void addRegionsTableRow(WebDriver driver, String serviceName, String tableName) {
        WebElement button = driver.findElement(By.cssSelector("a." + tableName + "_add_row_button")); //a[contains(@class,'sTable2_add_row_button')]
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(button));
        button.click();
//        button.click();
    }

    /**
     * Получение номера поданной заявки с централа (для случая последовательной
     * отработки заявки. Подача на централе и отработка на дашборде)
     *
     * @return
     * @throws Exception
     */
    public String getNumbersIdOrder() throws Exception {
        List<String> ID_Order = configVariables.orderId;
        System.out.println(ID_Order.size());
        String sID_Order = ID_Order.get(0);
        System.out.println("sID_Order= " + sID_Order);
        return sID_Order;
    }

// Методы для работы с дашбордом (регион)
    /**
     * Авторизация на дашборде
     *
     * @param driver
     * @param serviceName
     * @param loginName логин
     * @param passwordName пароль
     */
    public void AuthorizationBySetLoginPassword(WebDriver driver, String serviceName, String loginName, String passwordName) { //Authorization on region(Dashboards)
        String windowHandler = driver.getWindowHandle();
        WebElement elementLogin = driver.findElement(By.name("login"));
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(elementLogin));
        elementLogin.click();
        elementLogin.clear();
        elementLogin.sendKeys(loginName);
        WebElement elementPassword = driver.findElement(By.name("password"));
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(elementPassword));
        elementPassword.click();
        elementPassword.clear();
        elementPassword.sendKeys(passwordName);
    }

    // TODO 
    public void AuthorizationBySetLoginPassword(WebDriver driver, String loginName, String passwordName) { //Authorization on region(Dashboards)
        String windowHandler = driver.getWindowHandle();
        WebElement elementLogin = driver.findElement(By.name("login"));
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(elementLogin));
        elementLogin.click();
        elementLogin.clear();
        elementLogin.sendKeys(loginName);
        WebElement elementPassword = driver.findElement(By.name("password"));
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(elementPassword));
        elementPassword.click();
        elementPassword.clear();
        elementPassword.sendKeys(passwordName);
    }

    /**
     * Авторизация по ецп
     *
     * @param driver
     * @param serviceName
     * @param loginName логин
     * @param passwordName пароль
     */
    public void ecpAuthorization(WebDriver driver, String serviceName, String loginName, String passwordName) { //Authorization on region(Dashboards)
        String windowHandler = driver.getWindowHandle();
        WebElement elementLogin = driver.findElement(By.name("login"));
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(elementLogin));
        elementLogin.click();
        elementLogin.clear();
        elementLogin.sendKeys(loginName);
        WebElement elementPassword = driver.findElement(By.name("password"));
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(elementPassword));
        elementPassword.click();
        elementPassword.clear();
        elementPassword.sendKeys(passwordName);
    }

    /**
     * Выполнение поиска заявки по sID_Order
     *
     * @param driver
     * @param serviceName
     * @param sID_Oreder
     * @throws Exception
     */
    public void setRegionFindOrder(WebDriver driver, String serviceName, String sID_Oreder) throws Exception { // поиск ID_Order

        WebElement searchForm = driver.findElement(By.cssSelector(".form-control.searched-text"));
        String sScript = "$('.form-control.searched-text').val('" + sID_Oreder + "');";
        ((JavascriptExecutor) driver).executeScript(sScript, searchForm);
        String sScript2 = "$('.btn.btn-default.idoc-search-button').click();";
        ((JavascriptExecutor) driver).executeScript(sScript2, searchForm);
    }

    /**
     * Получение sID_Order документа на дашборде с элемента h3
     *
     * @return
     * @throws Exception
     */
    public String getsID_OrderFromH3element() throws Exception {
        WebElement h3Element = driver.findElement(By.xpath("//h3[contains(.,'№ ')]"));
        String sID_OrderWithSymbolNumber = getText(driver, h3Element);
        String sID_OrderFromH3element = getSubString(sID_OrderWithSymbolNumber, 2, 13);
        return sID_OrderFromH3element;

    }

    /**
     * Получение sID_Order для документа
     *
     * @return
     * @throws Exception
     */
    public String getsID_OrderForDoc() throws Exception {
//        String sID_OrderWithSymbolNumber = getsID_OrderFromH3element();

        String sID_Order = getsID_OrderFromH3element().toString();
        System.out.println("Переданный sID_OrderForDoc с sID_OrderWithSymbolNumber: " + sID_Order);
        return sID_Order;

    }

    /**
     * Поиск по sID_Order для документа (айдок)
     *
     * @param driver
     * @param serviceName
     * @param sID_Oreder
     * @throws Exception
     */
    public void setRegionFindOrderForDocument(WebDriver driver, String serviceName, String sID_Oreder) throws Exception { // поиск ID_Order
        WebElement searchForm = driver.findElement(By.cssSelector(".form-control.searched-text"));
        String sScript = "$('.form-control.searched-text').val('" + sID_Oreder + "');";
        ((JavascriptExecutor) driver).executeScript(sScript, searchForm);
        String sScript2 = "$('.btn.btn-default.idoc-search-button').click();";
        ((JavascriptExecutor) driver).executeScript(sScript2, searchForm);
    }

    /**
     * Работа с поиском дашборда по переданному sID_Order с централа
     *
     * @param driver
     * @param serviceName
     * @throws Exception
     */
    public void setRegionFindOrder(WebDriver driver, String serviceName) throws Exception { // поиск ID_Order
        String sID_Order = getNumbersIdOrder();
        WebElement searchForm = driver.findElement(By.cssSelector(".form-control.searched-text"));
        String sScript = "$('.form-control.searched-text').val('" + sID_Order + "');";
        ((JavascriptExecutor) driver).executeScript(sScript, searchForm);
        String sScript2 = "$('.btn.btn-default.idoc-search-button').click();";
        ((JavascriptExecutor) driver).executeScript(sScript2, searchForm);

    }

    /**
     * Клик на элемент Button
     *
     * @param driver
     * @param serviceName
     * @param nameButton имя Button
     */
    public void clickButton(WebDriver driver, String serviceName, String nameButton) { // нажатие любой кнопки с указанным тескстом на ней
        WebElement button = driver.findElement(By.xpath("//button[contains(.,'" + nameButton + "')]")); ////button[contains(.,'Опрацювати')]
        new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(button));
        button.click();
        pause(1500);
    }

    /**
     * Клик на элемент ButtonCreate (type="submit")
     *
     * @param driver
     * @param serviceName
     */
    public void clickButtonCreate(WebDriver driver, String serviceName) { // нажатие любой кнопки с указанным тескстом на ней
        WebElement button = driver.findElement(By.xpath("//button[@ng-click='submitTask(form)']")); ////button[contains(.,'Опрацювати')]
        new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(button));
        button.click();
    }

    /**
     * клик по ссылке
     *
     * @param driver
     * @param serviceName
     * @param nameLink имя ссылки
     * @throws Exception
     */
    public void clickLink(WebDriver driver, String serviceName, String nameLink) throws Exception { // нажатие любой кнопки с указанным тескстом на ней
        WebElement link = driver.findElement(By.xpath("//a[contains(.,'" + nameLink + "')]")); ////button[contains(.,'Опрацювати')]
        new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(link));
        link.click();
        pause(3000);
    }

    /**
     * *
     * Клик на элемент ButtonECP (type="submit")
     *
     * @param driver
     * @param serviceName
     * @param nameButton
     */
    public void clickButtonECP(WebDriver driver, String serviceName, String nameButton) { // нажатие любой кнопки с указанным тескстом на ней
        WebElement button = driver.findElement(By.xpath("//button[@ng-disable='cantSubmit(form)']")); ////button[contains(.,'Опрацювати')]
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(button));
        button.click();
    }

    /**
     * Работа с поиском по переданному номеру заявки с централа
     *
     * @param driver
     * @param serviceName
     * @throws Exception
     */
    public void setRegionTask(WebDriver driver, String serviceName) throws Exception { // поиск ID_Order  в списке с заявками (согласно пребывания на конкретном табе дашборда)

        String sID_Order = getNumbersIdOrder();
        WebElement webElement = driver.findElement(By.xpath(".//*[@id='adv-search']/input"));
        webElement.click();
        webElement.sendKeys(sID_Order);
        WebElement wElement = driver.findElement(By.xpath("//span[contains(.,'" + sID_Order + "')]"));
        new WebDriverWait(driver, 70).until(ExpectedConditions.visibilityOf(wElement));
        WebElement weElement = driver.findElement(By.xpath("//button[@class='btn btn-default idoc-search-button']"));
        weElement.click();

    }

    public void setRegionTab(WebDriver driver, String serviceName, String enumRegionTab) throws Exception { // навигация по табам navbar в дашборде
        pause(5000);
        if (serviceName.contains("idoc")) {
            WebElement button = driver.findElement(By.cssSelector(".btn-group.menu-tabs.ng-scope"));
            String sScript = "$('a:contains(\"" + enumRegionTab + "\")').click()";
            ((JavascriptExecutor) driver).executeScript(sScript, button);
        } else {
            WebElement element = driver.findElement(By.cssSelector(".navbar.navbar-default.navbar-static-top.i-gov-navbar"));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            if (enumRegionTab.contains("Необроблені")) {
                js.executeScript("$(\"#unassigned\").click();", element);

            } else if (enumRegionTab.contains("В роботі ")) {
                js.executeScript("$(\"#selfAssigned\").click();", element);
//            driver.get("https://delta.test.region.igov.org.ua/tasks/selfAssigned");  
            } else if (enumRegionTab.contains("Документи")) {
                js.executeScript("$(\"#documents\").click();", element);
//          driver.get("https://delta.test.region.igov.org.ua/tasks/documents");  
            } else if (enumRegionTab.contains("ЕЦП")) {
                js.executeScript("$(\"#ecp\").click();", element);
//          driver.get("https://delta.test.region.igov.org.ua/tasks/ecp");  
            } else if (enumRegionTab.contains("Мій розклад")) {
                js.executeScript("$(\"#tickets\").click();", element);
//          driver.get("https://delta.test.region.igov.org.ua/tasks/tickets");  
            } else if (enumRegionTab.contains("Історія")) {
                js.executeScript("$(\"#finished\").click();", element);
//          driver.get("https://delta.test.region.igov.org.ua/tasks/finished");  
            }
        }

    }

    /**
     * получение данных по заявке с помощью сервиса
     * /wf/service/action/task/getTaskData. Сервис позволяет получить
     * Ассайнутость, значение в том или ином поле, найденность той или иной
     * заявки (например после поиска) и соответствие искомой. РАБОТАЕТ ТОЛЬКО
     * ПЕРЕХОД. НЕОБХОДИМА АВТОРИЗАЦИЯ (system/system)
     *
     * @throws Exception
     */
    public void getRegionOrderData() throws Exception { // 
        String sID_Order = getNumbersIdOrder();
        driver.get("https://delta.test.region.igov.org.ua/wf/service/action/task/getTaskData?sID_Order="
                + sID_Order + "&bIncludeStartForm=true&bIncludeGroups=true");

    }

    // Методы по работе с элементами дашборда
    /*Методы по работе с элементами дашборда имеют приставку "SetRegion" в названии метода*/
    /**
     * Работа с элементом типа String
     *
     * @param driver
     * @param serviceName
     * @param cssSelector
     * @param value
     */
    public void SetRegionFieldInputTypeString(WebDriver driver, String serviceName, String cssSelector, String value) {
        WebElement webElement = driver.findElement(By.cssSelector("input[name='" + cssSelector + "']"));
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(webElement));
//        webElement.click();
        webElement.clear();
        webElement.sendKeys(value);
    }

    /**
     * Работа с элементом типа TextArea
     *
     * @param driver
     * @param serviceName
     * @param cssSelector
     * @param value
     */
    public void SetRegionFieldInputTypeTextArea(WebDriver driver, String serviceName, String cssSelector, String value) {
        WebElement webElement = driver.findElement(By.cssSelector("textarea[name='" + cssSelector + "']"));
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(webElement));
//        webElement.click();
        webElement.clear();
        webElement.sendKeys(value);
    }

    /**
     * Работа с элементом типа Long
     *
     * @param driver
     * @param serviceName
     * @param cssSelector
     * @param value
     */
    public void SetRegionFieldInputTypeLong(WebDriver driver, String serviceName, String cssSelector, String value) {
        WebElement webElement = driver.findElement(By.cssSelector("input[name='" + cssSelector + "']"));
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(webElement));
//        webElement.click();
        webElement.clear();
        webElement.sendKeys(value);
    }

    /**
     * Работа с элементом типа Double
     *
     * @param driver
     * @param serviceName
     * @param cssSelector
     * @param value
     */
    public void SetRegionFieldInputTypeDouble(WebDriver driver, String serviceName, String cssSelector, String value) {
        WebElement webElement = driver.findElement(By.cssSelector("input[name='" + cssSelector + "']"));
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(webElement));
//        webElement.click();
        webElement.clear();
        webElement.sendKeys(value);
    }

    /**
     * Работа с элементом типа Date
     *
     * @param driver
     * @param serviceName
     * @param cssSelector
     * @param date
     */
    public void SetRegionFieldInputTypeDate(WebDriver driver, String serviceName, String cssSelector, String date) {
        WebElement webElement = driver.findElement(By.cssSelector("input[name='" + cssSelector + "']"));
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(webElement));
        ((JavascriptExecutor) driver).executeScript("angular.element(document.getElementsByName('" + cssSelector + "')[0]).removeAttr('readonly');");
//        webElement.click();
        webElement.clear();
        webElement.sendKeys(date);
    }

    /**
     * Работа с элементом типа File
     *
     * @param driver
     * @param serviceName
     * @param xpathSelector
     * @param sPathFile
     */
    public void SetRegionFieldInputTypeFile(WebDriver driver, String serviceName, String xpathSelector, String sPathFile) {
        WebElement oWebElement = driver.findElement(By.xpath(".//button[@ng-class=\"{'btn-igov':field && field.value, 'btn-link attach-btn':!field, 'btn-default':field && !field.value}\"]//input"));//ng-class="{'btn-igov':field && field.value, 'btn-link attach-btn':!field, 'btn-default':field && !field.value}"
        //        ng-class="{'btn-igov':field && field.value, 'btn-link attach-btn':!field, 'btn-default':field && !field.value}"
        String sScript = "var element = arguments[0];" + "element.style.display='inline';";
        ((JavascriptExecutor) driver).executeScript(sScript, oWebElement);

        File oFile = new File(sPathFile);
        oWebElement.sendKeys(oFile.getAbsolutePath());

    }

    /**
     * Работа с элементом тип ECPFile
     *
     * @param driver
     * @param serviceName
     * @param xpathSelector
     * @param sPathFile
     */
    public void SetFieldInputECPFile(WebDriver driver, String serviceName, String xpathSelector, String sPathFile) {
        WebElement oWebElement = driver.findElement(By.xpath(".//button[@ng-class=\"{'btn-igov':field && field.value, 'btn-link attach-btn':!field, 'btn-default':field && !field.value}\"]//input"));
        String sScript = "var element = arguments[0];" + "element.style.display='inline';";
        ((JavascriptExecutor) driver).executeScript(sScript, oWebElement);

        File oFile = new File(sPathFile);
        oWebElement.sendKeys(oFile.getAbsolutePath());

        while (!oWebElement.isEnabled()) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Работа с элементом тип Enum
     *
     * @param driver
     * @param serviceName
     * @param cssSelector
     * @param value
     */
    public void SetRegionFieldInputTypeEnum(WebDriver driver, String serviceName, String cssSelector, String value) {
        WebElement webEnum = driver.findElement(By.xpath("//option[@label='" + value + "']"));
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(webEnum));
        webEnum.click();
    }

    /**
     * Работа с элементом тип Checkbox
     *
     * @param driver
     * @param serviceName
     * @param cssSelector
     */
    public void SetRegionFieldInputTypeCheckbox(WebDriver driver, String serviceName, String cssSelector) {
        WebElement checkbox = driver.findElement(By.xpath("//input[@name='asEnumTypeCheckbox']")); // //*[@id="bFavorite11"] //*[@id="field-bWrite"]/div
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(checkbox));
        checkbox.click();

    }

    // Методы для заполнения таблиц на дашборде (регион). Приставка "setRegionTable" в названии методов 
    /**
     * Работа с ячейкой таблицы тип String
     *
     * @param driver
     * @param serviceName
     * @param tableName
     * @param cellName
     * @param NameRow
     * @param text
     */
    public void setRegionTableCellsInputTypeString(WebDriver driver, String serviceName, String tableName, String cellName, String NameRow, String text) {
        WebElement td = driver.findElement(By.xpath("//input[@name='" + cellName + NameRow + "']"));
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(td));
        td.click();
        td.clear();
        td.sendKeys(text);
    }

    /**
     * Работа с ячейкой таблицы тип EnumSelect (выбор с перечня EnumSelect)
     *
     * @param driver
     * @param serviceName
     * @param tableName
     * @param cellName
     * @param NameRow
     * @param text
     */
    public void setRegionTableCellsInputTypeEnumSelect(WebDriver driver, String serviceName, String tableName, String cellName, String NameRow, String text) {
        WebElement td = driver.findElement(By.xpath("//select[@name='" + cellName + NameRow + "']"));//ng-scope 
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(td));
        td.click();
        Select select = new Select(td);
        select.selectByVisibleText(text);
    }

    /**
     * Работа с ячейкой таблицы тип EnumSpan (выбор с перечня EnumSpan)
     *
     * @param driver
     * @param serviceName
     * @param tableName
     * @param cellName
     * @param NameRow
     * @param text
     */
    public void setRegionTableCellsInputTypeEnumSpan(WebDriver driver, String serviceName, String tableName, String cellName, String NameRow, String text) {

        WebElement element1 = driver.findElement(By.cssSelector(".ng-scope._doc_iTest_test_all_case_--_" + tableName + "_--_COL_" + cellName + "_--_ROW_" + NameRow + " .btn.btn-default.form-control.ui-select-toggle"));
        element1.click();
        WebElement listElement = driver.findElement(By.xpath("//a[contains(.,'" + text + "')]"));
        listElement.click();
    }

    /**
     * Получение подстроки у String
     *
     * @param text
     * @param beginIndex начало подстроки
     * @param endIndex конец подстроки
     * @return
     */
    public String getSubString(String text, int beginIndex, int endIndex) {
        String newtext = text.substring(beginIndex, endIndex);
        System.out.println(newtext);
        return newtext;
    }

    /**
     * Работа с ячейкой таблицы тип EnumInput
     *
     * @param driver
     * @param serviceName
     * @param tableName
     * @param cellName
     * @param NameRow
     * @param text
     * @throws Exception
     */
    public void setRegionTableCellsInputTypeEnumInput(WebDriver driver, String serviceName, String tableName, String cellName, String NameRow, String text) throws Exception {
        try {
            String textNew = getSubString(text, 0, 3);
            System.out.println(textNew);
            WebElement element1 = driver.findElement(By.cssSelector(".ng-scope." + serviceName + "_--_" + tableName + "_--_COL_" + cellName + "_--_ROW_" + NameRow + " .btn.btn-default.form-control.ui-select-toggle"));
            new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(element1));
            element1.click();

            WebElement element2 = driver.findElement(By.cssSelector(".form-control.ui-select-search.ng-pristine.ng-untouched.ng-valid.ng-empty"));
            new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(element2));
            element2.sendKeys(textNew);
            pause(2000);
            WebElement listElement = driver.findElement(By.xpath("//a[contains(.,'" + text + "')]"));
            new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOf(listElement));
            listElement.click();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Работа с ячейкой таблицы тип File
     *
     * @param driver
     * @param serviceName
     * @param tableName
     * @param cellName
     * @param nameRow
     * @param sPathFile
     * @throws InterruptedException
     */
    public void setRegionTableCellsInputTypeFile(WebDriver driver, String serviceName, String tableName, String cellName, String nameRow, String sPathFile) throws InterruptedException {
        WebElement oWebElement = driver.findElement(By.xpath("//td[contains(@class,'ng-scope _doc_iTest_test_all_case_--_" + tableName + "_--_COL_" + cellName + "_--_ROW_" + nameRow + "')]//*[@id=\"upload-button\"]//input")); //.upload-button
        String sScript = "var element = arguments[0];" + "element.style.display='inline';";
        ((JavascriptExecutor) driver).executeScript(sScript, oWebElement);

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
     * Работа с ячейкой таблицы тип Calendar
     *
     * @param driver
     * @param serviceName
     * @param tableName
     * @param cellName
     * @param NameRow
     * @param date
     * @throws InterruptedException
     */
    public void setRegionTableCellsInputTypeCalendar(WebDriver driver, String serviceName, String tableName, String cellName, String NameRow, String date) throws InterruptedException {

        WebElement td = driver.findElement(By.xpath("//td[@class='ng-scope _doc_iTest_test_all_case_--_" + tableName + "_--_COL_" + cellName + "_--_ROW_" + NameRow + "']//input"));
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(td));
        ((JavascriptExecutor) driver).executeScript("angular.element(document.getElementsByName('" + cellName + NameRow + "')[0]).removeAttr('readonly');");
        td.clear();
        td.sendKeys(date);
        Thread.sleep(1000);

    }

    /**
     * Подгрузка данных в буфер обмена
     *
     * @param string
     */
    public static void setClipboardData(String string) {
        StringSelection stringSelection = new StringSelection(string);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

    }

    /**
     * Загрузка ецп файла с помощью Robot
     *
     * @throws InterruptedException
     * @throws AWTException
     */
    public void uploadECPKeyFile() throws InterruptedException, AWTException {
        File file = new File("src/test/resources/files/Key-6.dat");
        //
        WebElement buttonECP = driver.findElement(By.xpath("//button[@id='selectDir']"));
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
     * Загрузка ецп файла с помощью Robot
     *
     * @param filePath
     * @throws InterruptedException
     * @throws AWTException
     */
    public void uploadECPKeyFile(String filePath) throws InterruptedException, AWTException {
        File file = new File(filePath);
        //
        WebElement buttonECP = driver.findElement(By.xpath("//button[@id='selectDir']"));
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
     * Ввод пароля к ключу-ецп
     */
    public void setPaswordForECPKey() {
        WebElement passwordECP = driver.findElement(By.xpath("//input[@id='password1']"));
        passwordECP.sendKeys("12345677");
        WebElement clickButtonSubmint = driver.findElement(By.xpath("//button[@id='open']"));
        clickButtonSubmint.click();
    }

    /**
     * /**
     * Ввод пароля к ключу-ецп
     *
     * @param password
     */
    public void setPaswordForECPKey(String password) {
        WebElement passwordECP = driver.findElement(By.xpath("//input[@id='password1']"));
        passwordECP.sendKeys(password);
        WebElement clickButtonSubmint = driver.findElement(By.xpath("//button[@id='open']")); //12345677
        clickButtonSubmint.click();
    }

    /**
     * Загрузка ецп файла с помощью AutoIT
     *
     * @throws Exception
     */
    public void uploadECPKeyAutoIT() throws Exception {

        WebElement buttonECP = driver.findElement(By.xpath("//button[@id='selectDir']"));
        buttonECP.click();
        Runtime.getRuntime().exec("src\\test\\resources\\files\\UploadKey.exe"); //Key-6.dat
        Thread.sleep(5000);
    }

    /*Методы для нового дизайна дашборда (АйДок)*/
    /**
     * Навигация по ToggleMenu
     */
    public void navigateToggleMenu() {
        WebElement webElement = driver.findElement(By.cssSelector(".igov-hamburger>a"));
        String sScript = "$('.igov-hamburger>a').click()";
        ((JavascriptExecutor) driver).executeScript(sScript, webElement);

    }

    /**
     * 
     * Навигация по MenuTabs
     * @param buttonName 
     */
    public void snapDrawerButtonMenuTabs(String buttonName) {
        WebElement button = driver.findElement(By.xpath(".//a[contains(.,'" + buttonName + "')]"));
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(button));
        button.click();

    }

    /**
     * Создание документа
     * @param nameDocumentOrTask 
     */
    public void createDocumentOrTask(String nameDocumentOrTask) { //*
        WebElement button = driver.findElement(By.cssSelector(".btn.btn-default.ng-scope"));
        String sScript = "$('.btn.btn-default.ng-scope').click()";
        ((JavascriptExecutor) driver).executeScript(sScript, button);
        WebElement Element = driver.findElement(By.xpath("//i[@ng-click='$select.toggle($event)']"));
        Element.click();
        WebElement listElement = driver.findElement(By.xpath("//span[contains(.,'" + nameDocumentOrTask + "')]"));
        listElement.click();

    }

    /**
     * Выбор шаблона документа/заявки
     * @param templateName 
     */
    public void templateSelect(String templateName) {
        String sScript = "$('span.btn.btn-default.form-control.ui-select-toggle').click()";
        ((JavascriptExecutor) driver).executeScript(sScript, "");
        WebElement element = driver.findElement(By.xpath("//a[contains(.,'" + templateName + "')]"));
        element.click();
    }

    /**
     * Навигация по MenuList (меню слева страницы Айдока)
     * @param buttonName 
     */
    public void choiceMenuList(String buttonName) {
        WebElement menuList = driver.findElement(By.cssSelector(".menu-list.ng-scope"));
        if (buttonName.contains("Необроблені")) {
            String sScript = "$('#unassigned').click();";
            ((JavascriptExecutor) driver).executeScript(sScript, menuList);
        } else if (buttonName.contains("В роботі")) {
            String sScript = "$('#selfAssigned').click();";
            ((JavascriptExecutor) driver).executeScript(sScript, menuList);
        } else if (buttonName.contains("Мій розклад")) {
            String sScript = "$('#tickets').click();";
            ((JavascriptExecutor) driver).executeScript(sScript, menuList);
        } else if (buttonName.contains("Усі")) {
            String sScript = "$('#all').click();";
            ((JavascriptExecutor) driver).executeScript(sScript, menuList);
        } else if (buttonName.contains("Історія")) {
            String sScript = "$('#finished').click();";
            ((JavascriptExecutor) driver).executeScript(sScript, menuList);
        } else if (buttonName.contains("На контролі")) {
            String sScript = "$('#control').click();";
            ((JavascriptExecutor) driver).executeScript(sScript, menuList);
        }
    }

    /**
     * Работа с поиском на дашборде (Айдок)
     * @param searchText 
     */
    public void searchBoxIdoc(String searchText) {
        WebElement search = driver.findElement(By.cssSelector(".menu-list.ng-scope"));
        String sScript = "$('.form-control.searched-text').val('" + searchText + "');";
        ((JavascriptExecutor) driver).executeScript(sScript, search);
        String sScript2 = "$('.btn.btn-default.idoc-search-button').click();";
        ((JavascriptExecutor) driver).executeScript(sScript2, search);

    }

    /**
     * TODO
     * @throws Exception 
     */
    public void searchBoxIdoc() throws Exception {
        String sID_Order = getNumbersIdOrder();
        WebElement search = driver.findElement(By.xpath(".//*[@id='adv-search']/input"));
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(search));
        search.click();
        search.sendKeys(sID_Order);
        search.sendKeys(Keys.ENTER);

    }

    
    /**
     * Скроллинг страницы вниз до элемента h3 с названием "Більше задач не знайдено"
     */
    public void scrollPageDown() {
        WebElement scrollPage = driver.findElement(By.xpath("//h3[contains(.,'Більше задач не знайдено')]"));
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView(true);", scrollPage);
    }

    /**
     * Навигация по меню юзера (меню верхний правый угол страницы)
     * @param driver
     * @param serviceName
     * @param userName
     * @param autoSignature
     * @param subMenuItems 
     */
    public void usersMenuNavBarRight(WebDriver driver, String serviceName, String userName, String autoSignature, String subMenuItems) {
        WebElement usersMenu = driver.findElement(By.xpath("//a[contains(.,'tester  ')]"));
//        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(usersMenu)); //$('a:contains("tester")').click()
        String sScript = "$('a:contains(" + userName + ")').click();";
        ((JavascriptExecutor) driver).executeScript(sScript);
        WebElement signature = driver.findElement(By.xpath("//label[contains(.,'  Авто пiдпис ЕЦП')]')]"));
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(signature));
        usersMenu.click();
        WebElement chooseSubMenuItems = driver.findElement(By.xpath("//a[contains(.,'" + subMenuItems + "')]"));
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(chooseSubMenuItems));
        usersMenu.click();

    }

    /**
     * Работа с формой по подгрузке ЕЦП для дашборда (Айдок)
     * @param driver
     * @param serviceName
     * @param sPathDocumentForECP 
     */
    public void uploadECPKeyFileIdoc(WebDriver driver, String serviceName, String sPathDocumentForECP) {
        WebElement oWebElement = driver.findElement(By.xpath("//input[@type='file']"));
        String sScript = "var element = arguments[0];" + "element.style.display='inline';";
        ((JavascriptExecutor) driver).executeScript(sScript, oWebElement);

        File oFile = new File(sPathDocumentForECP); //\\resources\\files\\testDocumentForECP.pdf
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

        WebElement pathECP = driver.findElement(By.xpath("//input[@name='eds']"));
        pathECP.sendKeys("\\resources\\files\\Key-6.dat");

        WebElement passwordForECP = driver.findElement(By.xpath("//input[@name='eds-password']"));
        pathECP.sendKeys("12345677");

    }

    /**
     * Окрытие заявки/документа по урлу зная sID_Order
     * @param driver
     * @param serviceName
     * @param serverName
     * @param sID_Order
     * @throws IOException
     * @throws InterruptedException 
     */
    public void openServiceByUrl(WebDriver driver, String serviceName, String serverName, String sID_Order) throws IOException, InterruptedException {
        driver.manage().window().maximize();
        driver.get("https://" + serverName + ".test.region.igov.org.ua/wf/service/action/task/getTaskData?sID_Order=" + sID_Order);
        Runtime.getRuntime().exec("src\\test\\resources\\files\\scriptForWindows PopupAuth.exe");
        Thread.sleep(5000);

    }

    /**
     * Получение sID_Order с текущего урла страницы
     * @return 
     */
    public String getOrderFromUrlCurrentPage() {
        String UrlCurrentPage = driver.getCurrentUrl();
        System.out.println("UrlCurrentPage: " + UrlCurrentPage);
        String sOrder = UrlCurrentPage.substring(UrlCurrentPage.indexOf("=") + 1, UrlCurrentPage.indexOf("#"));
        System.out.println("Полученный sID_Order: " + sOrder);
        return sOrder;

    }

    /**
     * Работа с поиском
     * @param driver
     * @param serviceName
     * @throws Exception 
     */
    public void setRegionFindOrderByNumberDocument(WebDriver driver, String serviceName) throws Exception { // поиск ID_Order

        String sID_Order = getOrderFromUrlCurrentPage();
        clickButtonCreate(driver, "");

        clickButton(driver, "", "Ok");
        clickLink(driver, "", "Співробітник2 підрозділу 1.1  ");
        clickLink(driver, "", "Вийти");

        AuthorizationBySetLoginPassword(driver, "", "iTest_User_0018", "iTest_User_0018");
        clickButton(driver, "", "Увійти");
        clickLink(driver, "", "Нерозглянутi");
        WebElement searchForm = driver.findElement(By.cssSelector("#adv-search input"));
        searchForm.click();
        System.out.println("Вставка sID_Order= " + sID_Order);
        String sScript = "$('#adv-search input').val('" + sID_Order + "')";
        ((JavascriptExecutor) driver).executeScript(sScript, driver.findElement(By.cssSelector("#adv-search input")));
        String sScript2 = "$('.btn.btn-default.idoc-search-button').click();";
        ((JavascriptExecutor) driver).executeScript(sScript2, searchForm);
        pause(5000);

    }

    /**
     * 
     * Получение подстроки (sID_Order) из урла текущей страницы
     * @param driver
     * @param beginIndex
     * @throws IOException
     * @throws InterruptedException
     * @throws AWTException 
     */
    public void getSubstringFromUrlCurrentPage(WebDriver driver, String beginIndex) throws IOException, InterruptedException, AWTException {
        String UrlCurrentPage = driver.getCurrentUrl();
        System.out.println("UrlCurrentPage: " + UrlCurrentPage);
        String SubstringFromUrlCurrentPage = UrlCurrentPage.substring(UrlCurrentPage.indexOf(beginIndex));
        System.out.println("NameDoc: " + SubstringFromUrlCurrentPage);
        clickButtonPrintAutoIT();
        Thread.sleep(5000);
        setClipboardData(SubstringFromUrlCurrentPage);
        System.out.println("Вставка: " + SubstringFromUrlCurrentPage);
        Robot robot = new Robot();
        robot.delay(1000);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.delay(300);
        robot.keyPress(KeyEvent.VK_V);
        clickButtonSaveAutoIT();
    }

    /**
     * открытие приватдока
     * @param driver
     * @param serviceName
     * @param docName 
     */
    public void openDocFromPrivatDoc(WebDriver driver, String serviceName, String docName) {
        openURLservice(driver, docName);
        pause(10000);

    }

    /**
     * Сканирование приватдока на наличие приложения
     * @param driver
     * @throws InterruptedException 
     */
    public void scanDocFromPrivatDoc(WebDriver driver) throws InterruptedException {
        List<WebElement> linkList = driver.findElements(By.xpath(".//a[@target='_blank']"));
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
     * Вывод на печать приватдока
     */
    public void printPrivatDoc() {
        WebElement webElement = driver.findElement(By.xpath(".//*[@id='docFull']"));
        pause(5000);
        webElement.sendKeys(Keys.CONTROL, "p");
    }

    /**
     * Печать доки
     * @throws IOException
     * @throws InterruptedException 
     */
    public void clickButtonPrintAutoIT() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("src\\test\\resources\\files\\clickButtonPrint.exe");
        Thread.sleep(5000);

    }

    /**
     * Сохранение документа
     * @throws IOException
     * @throws InterruptedException 
     */
    public void clickButtonSaveAutoIT() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("src\\test\\resources\\files\\clickButtonSave.exe");
        Thread.sleep(10000);

    }

//--------------Мметоды для работы с архивом-----------------------//
    public void getNameSavingFile(String pathDir) {
        File f = new File(pathDir);
        File[] listFile = f.listFiles();
        int nambersFiles = listFile.length;
        System.out.println("количество файлов: " + nambersFiles);
        for (File file : listFile) {
            System.out.println("Имя файла: " + file.getName());
            String newNameFile = file.getName().substring(0, file.getName().lastIndexOf("."));
            System.out.println("Имя файла без расширения: " + newNameFile);

        }

    }

    public void createDirByNamedSaveFile(String pathDir, String nameDir) {
        File dir = new File(pathDir, nameDir);
        boolean created = dir.mkdirs();
        if (created) {
            System.out.println("Папка с именем " + nameDir + " создана");

        } else {
            System.out.println("Что то пошло не так... Папка " + nameDir + " не создана");
        }
    }

}
