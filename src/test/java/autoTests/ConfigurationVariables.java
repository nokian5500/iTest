package autoTests;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class ConfigurationVariables
{
	//используем Singleton
	private static final ConfigurationVariables instance;

	private static String configFilePath = "src/test/resources/config.properties";
	private static Properties configurationData = new Properties();

	public static void fillMyProperties(Properties properties, String filePath)
	{
		InputStreamReader input;
		FileInputStream fileInputStream;
		try {
			fileInputStream = new FileInputStream(filePath);
			input = new InputStreamReader(fileInputStream, "UTF8");

			// считываем свойства
			properties.load(input);
		}
		catch (java.io.FileNotFoundException e) {
			System.out.println("Ошибка. Файл config.properties не был найден.");
		}
		catch (java.io.IOException e) {
			System.out.println("IO ошибка в пользовательском методе.");
		}
	}

	private static String getProperty(Properties properties, String propertyKey)
	{
		// получаем значение свойства
		return properties.getProperty(propertyKey).toString();
	}

	public String currentBrowser;
	public int technicalPause;
	public int implicitTimeWait;
	public int waitPageForLoad;
	public int longPause;
	public String chromeDriverDirectory;
	public String firefoxDirectoryLocalMachine;
	public String chromeDirectoryLocalMachine;
	public String baseUrl;

	static
	{
		fillMyProperties(configurationData, configFilePath);
		instance = new ConfigurationVariables();
	}

	private ConfigurationVariables()
	{
		/********************************************** конфигурационные данные ***************************************/
		currentBrowser = getProperty(configurationData, "currentBrowser");
		firefoxDirectoryLocalMachine = getProperty(configurationData, "firefoxDirectoryLocalMachine");
		chromeDirectoryLocalMachine = getProperty(configurationData, "chromeDirectoryLocalMachine");
		chromeDriverDirectory = getProperty(configurationData, "chromeDriverDirectory");

		technicalPause = Integer.parseInt(getProperty(configurationData, "technicalPause"));
		implicitTimeWait = Integer.parseInt(getProperty(configurationData, "implicitTimeWait"));
		waitPageForLoad = Integer.parseInt(getProperty(configurationData, "waitPageForLoad"));
		longPause = Integer.parseInt(getProperty(configurationData, "longPause"));

		baseUrl = getProperty(configurationData, "baseurl");
	}

	//возвращаем инстанс объекта
	public static ConfigurationVariables getInstance()
	{
		return instance;
	}
}