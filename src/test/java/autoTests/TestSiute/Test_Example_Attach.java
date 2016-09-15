package autoTests.TestSiute;

import autoTests.SetupAndTeardown;
import autoTests.pages.main.TemplatePage;
import org.testng.annotations.Test;

/**
 * Created by Privat24 on 09.09.2016.
 */
public class Test_Example_Attach extends SetupAndTeardown {

    //<editor-fold desc="Тестовый пример загрузки файла">
    @Test(enabled = true, groups = {"Main", "Критический функционал"}, priority = 2)
    public void Test_Example_Attach() throws Exception {
        /*****************************************объявляем элементы страниц*******************************************/
        TemplatePage o = new TemplatePage(driver);
        //  Вносим в переменные название услуги и почту
        String sBP = "_test_mailer";

        _step("1. Вход по прямому URL на услугу");
        openURLservice(driver, CV.baseUrl + "/service/755/general");

        _step("2. Проверить, что открылась нужная услуга");
        assertThis(driver, o.usluga, "_test_mailer");

        _step("3. Авторизация Off AuthMock/BankID");
        o.mokAuthorization();

        _step("4. Заполняем форму услуги");
        setFieldValue(driver, sBP, "mail", "v-i-d-k@mail.ru");
        setFieldFile(driver, sBP, "nFileOne", "src/test/resources/files/test.jpg");
        //attachDocument(tp.attachDocumentButton, "src/test/resources/files/test.jpg", driver);

        _step("5. Отправка формы");
        click(driver, o.buttonSendingForm);

        _step("6. Проверка сообщения о успешной отправке");
        o.checkMessageSuccess("Шановний(-а) MockUser MockUser!\n" +
                "Ваше звернення х-хххххххх успішно зареєстровано");

        _step("7. Нажать кнопку Выйти ");
        click(driver, o.buttonLogOut);

    }
    //</editor-fold>
}
