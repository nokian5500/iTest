package autoTests.TestSiute;

import autoTests.API.DeleteTask;
import autoTests.ConfigurationVariables;
import autoTests.CustomMethods;
import autoTests.pages.main.TemplatePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.selenium.factory.WebDriverFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Privat24 on 09.09.2016.
 */
public class Test_Example_Attach  extends CustomMethods {


    //<editor-fold desc="Тестовый пример загрузки файла">
    @Test(enabled = true, groups = {"Main", "Критический функционал"}, priority = 2)
    public void Test_Example_Attach() throws Exception {
        /*****************************************объявляем элементы страниц*******************************************/
        TemplatePage o = new TemplatePage(driver);
        //  Вносим в переменные название услуги и почту
        String sBP = "_test_mailer";

        _step("1. Вход по прямому URL на услугу");
        openURLservice(driver, CV.baseUrl + "/service/755/general");

        // 2016-11-23 Убираем проверку на открытие нужной услуги.
        // Считаем, что мы ее правильно по прямому пути выше 
        // _step("2. Проверить, что открылась нужная услуга");
        // assertThis(driver, o.usluga, "_test_mailer");

        _step("3. Авторизация Off AuthMock/BankID");
        o.mokAuthorization();
//        o.selectBank("Приватбанк");
        
        _step("4. Заполняем форму услуги");
        setFieldValue(driver, sBP, "mail", "v-i-d-k@mail.ru");
        setFieldFile(driver, sBP, "nFileOne", "src/test/resources/files/test.jpg");
        //attachDocument(tp.attachDocumentButton, "src/test/resources/files/test.jpg", driver);

        _step("5. Отправка формы");
        click(driver, o.buttonSendingForm);

        _step("6. Проверка сообщения о успешной отправке");
        o.checkMessageSuccess("Шановний(-а) MockUser MockUser!\n" +
                "Ваше звернення X-XXXXXXXX успішно зареєстровано");

        _step("7. Нажать кнопку Выйти ");
        click(driver, o.buttonLogOut);

    }
    //</editor-fold>
    
     @Test(enabled = false, groups = {"Main", "Критический функционал"}, priority = 2)
    public void testDeleteMethodForDEBUG() throws Exception {
        DeleteTask delete = new DeleteTask();

        ConfigurationVariables configVariables = ConfigurationVariables.getInstance();
        configVariables.orderId.add("");
//        delete.deleteAllOrderId();
        delete.deleteAllOrderId();
      
    }
}
