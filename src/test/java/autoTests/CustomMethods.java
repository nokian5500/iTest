package autoTests;


import autoTests.pages.main.Header;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;


public class CustomMethods
{

	ConfigurationVariables configVariables = ConfigurationVariables.getInstance();

	//Открыть новую вкладку
	public void openNewTab(WebDriver driver)
	{
		WebElement body = driver.findElement(By.tagName("body"));
		body.sendKeys(Keys.CONTROL + "t");
	}

	//Закрыть вкладку
	public void closeTab(WebDriver driver)
	{
		WebElement body = driver.findElement(By.tagName("body"));
		body.sendKeys(Keys.CONTROL + "w");

		ArrayList <String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(0));

	}

	public int getRandomNumber(int n)
	{
		Random random = new Random();
		int RandomNumber = random.nextInt(n);
		return RandomNumber;
	}

	public void waitForElementRemoved(WebDriver driver, By locator, int timeoutInSeconds, int pollingInterval)
			throws Exception
	{
		WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds, pollingInterval);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	public void waitForElementRemoved(WebDriver driver, WebElement webElement, int timeoutInSeconds, int pollingInterval)
			throws Exception
	{
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

		boolean flag = true;
		int counter = 0;
		while(flag)
		{
			if (counter> (int)(timeoutInSeconds*1000/pollingInterval))
			{
				flag = false;
				throw new Exception ("Ошибка во время выполнения теста. " +
						"В метод waitForElementRemoved передан WebElement " +
						webElement +
						" который не удаляется"
				);
			}
			try
			{
				Thread.sleep(pollingInterval);
				counter++;
				if (!webElement.isDisplayed()) flag = false;
			}
			catch (Exception e)
			{
				flag = false;
			}
		}
		driver.manage().timeouts().implicitlyWait(configVariables.implicitTimeWait, TimeUnit.SECONDS);
	}

	public void waitForElementPresent(WebDriver driver, By locator, int timeoutInSeconds, int pollingInterval)
			throws Exception
	{
		WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds, pollingInterval);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
	}

	public void waitForElementPresent(WebDriver driver, WebElement webElement, int timeoutInSeconds, int pollingInterval)
			throws Exception
	{
		WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds, pollingInterval);
		wait.until(ExpectedConditions.visibilityOf(webElement));
	}

	public Calendar getCurrentCalendar()
	{
		// http://docs.oracle.com/javase/6/docs/api/java/util/GregorianCalendar.html
		// get the supported ids for GMT+02:00 ("Среднеевропейское (Центральноевропейское) летнее время")

		String[] ids = TimeZone.getAvailableIDs(+2 * 60 * 60 * 1000);
		// if no ids were returned, something is wrong. get out.
		if (ids.length == 0) System.exit(0);
		// create a (CEST - Central Europe Summer Time Zone) UTC/GMT+2 time zone
		SimpleTimeZone GMT = new SimpleTimeZone(+2 * 60 * 60 * 1000, ids[0]);
		// create a GregorianCalendar with the current date and time
		Calendar calendar = new GregorianCalendar(GMT);
		Date trialTime = new Date();
		calendar.setTime(trialTime);
		//// print out a bunch of interesting things
		// System.out.println("YEAR: " + calendar.get(Calendar.YEAR));
		// System.out.println("MONTH: " + calendar.get(Calendar.MONTH));
		// System.out.println("DATE: " + calendar.get(Calendar.DATE));
		// System.out.println("HOUR_OF_DAY: " + calendar.get(Calendar.HOUR_OF_DAY));
		// System.out.println("MINUTE: " + calendar.get(Calendar.MINUTE));
		// System.out.println("SECOND: " + calendar.get(Calendar.SECOND));
		// System.out.println("MILLISECOND: " + calendar.get(Calendar.MILLISECOND));
		//
		// System.out.println("Current Time, with hour reset to 3");
		// calendar.clear(Calendar.HOUR_OF_DAY); // so doesn't override
		// calendar.set(Calendar.HOUR, 3);
		return calendar;

	}

	public static void delete(File file)  throws IOException
	{
		if(file.isDirectory())
		{
			//directory is empty, then delete it
			if(file.list().length==0)
			{
				file.delete();
				//System.out.println("Directory is deleted : " + file.getAbsolutePath());
			}
			else
			{
				//list all the directory contents
				String files[] = file.list();
				for (String temp : files)
				{
					//construct the file structure
					File fileDelete = new File(file, temp);
					//recursive delete
					delete(fileDelete);
				}
				//check the directory again, if empty then delete it
				if(file.list().length==0)
				{
					file.delete();
					//System.out.println("Directory is deleted : " + file.getAbsolutePath());
				}
			}

		}
		else
		{
			//if file, then delete it
			file.delete();
		}
	}

	public static void deleteFileOrDirectory(File directory)
	{
		//make sure directory exists
		if(!directory.exists())
		{
			//System.out.println("Directory "+directory+" does not exist.");
		}
		else
		{
			try
			{
				delete(directory);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	//convert from UTF-8 -> internal Java String format
	public String convertFromUTF8(String s)
	{
		String out = null;
		try
		{
			out = new String(s.getBytes("Windows-1251"), "UTF-8");
		}
		catch (java.io.UnsupportedEncodingException e)
		{
			return null;
		}
		return out;
	}

	//convert from internal Java String format -> UTF-8
	public String convertToUTF8(String s)
	{
		String out = null;
		try
		{
			out = new String(s.getBytes("UTF-8"), "Windows-1251");
		}
		catch (java.io.UnsupportedEncodingException e)
		{
			return null;
		}
		return out;
	}

	//Проверяем что элемент присутствует и видем
	public void CheckElementPresent(WebElement element) throws InterruptedException
	{
		Assert.assertEquals(true, element.isDisplayed());
		Assert.assertEquals(true, element.isEnabled());
	}

	public void addStepToTheReport(String stepName) throws Exception
	{
		Reporter.log("<b>" + stepName + "</b><br>");
	}

	public static void addTestNameToTheReport(String testName, String methodPath) throws Exception
	{
		methodPath = methodPath.substring(0, methodPath.indexOf("("));

		//получим id теста
		String testId = methodPath.substring(methodPath.lastIndexOf(".") + 1, methodPath.length());

		//отделим имя теста от имени класса символом '#'
		StringBuilder tempPath = new StringBuilder(methodPath);
		methodPath =
				tempPath.substring(0, methodPath.lastIndexOf(".")) +
				URLEncoder.encode("#", "UTF8") +
				tempPath.substring(methodPath.lastIndexOf(".") + 1, methodPath.length());

		Reporter.log(
			"<form id = \"" + testId + "form\" action= \"\" method=\"post\">\n" +
					"<font color=\"blue\" size=\"3\">" + testName + "</font>\n" +
					"<input type=\"Submit\" value=\"Выполнить\">\n" +
					"</form> \n" +
					"<script type=\"text/javascript\">\n" +
					"\tvar currentURL = document.URL;\n" +
					"\tcurrentURL = currentURL.substring(0,currentURL.indexOf(\"/HTML_Report/\"));\n" +
					"var jobNameStartIndex = currentURL.indexOf(\"AT.SELENIUM.\");\n" +
					"while(currentURL.lastIndexOf(\"/\") > jobNameStartIndex)\n" +
					"  currentURL = currentURL.substring(0,currentURL.lastIndexOf(\"/\")); "+
					"\tdocument.getElementById('" + testId +
					"form').action = currentURL + \"/buildWithParameters?suiteFile=testng.xml&test=" + methodPath + "\";\n" +
					"</script>"
		);
	}

	public static void addErrorToTheReport(String testName) throws Exception
	{
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

	public void pause(int timeout) {
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void openDocumentsPage(WebDriver driver) {
		if(!driver.getCurrentUrl().contentEquals(configVariables.baseUrl + "documents/user/bankid")) {
			Header header = new Header(driver);
			header.documentsLink.click();
			new WebDriverWait(driver, 2).until(visibilityOfElementLocated(By.xpath("//a[text()='Мої документи']")));
		}
	}

	public void openStatusesPage(WebDriver driver) {
		if(!driver.getCurrentUrl().contentEquals(configVariables.baseUrl + "order/search")) {
			Header header = new Header(driver);
			header.statusesLink.click();
			new WebDriverWait(driver, 2).until(visibilityOfElementLocated(By.xpath("//h1[text()='Заявки']")));
		}
	}

	public void openMyJournalPage(WebDriver driver) {
		if(!driver.getCurrentUrl().contentEquals(configVariables.baseUrl + "journal/bankid")) {
			Header header = new Header(driver);
			header.myJournalLink.click();
		}
	}

	public void openAboutPortalPage(WebDriver driver) {
		if(!driver.getCurrentUrl().contentEquals(configVariables.baseUrl + "about")) {
			Header header = new Header(driver);
			header.aboutPortalLink.click();
			new WebDriverWait(driver, 2).until(visibilityOfElementLocated(By.xpath("//p[1][contains(.,'На цьому порталі зібрано послуги')]")));
		}
	}
}
