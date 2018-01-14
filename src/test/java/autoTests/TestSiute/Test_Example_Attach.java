package autoTests.TestSiute;

import autoTests.API.DeleteTask;
import autoTests.CustomMethods;
import autoTests.pages.main.TemplatePage;
import org.junit.Test;

/**
 * Created by Privat24 on 09.09.2016.
 */
public class Test_Example_Attach extends CustomMethods {

    //<editor-fold desc="Тестовый пример загрузки файла">
    @Test
    public void Test_Example_Attach() throws Exception {
        /**
         * ***************************************объявляем элементы страниц******************************************
         */
        TemplatePage o = new TemplatePage();
        //  Вносим в переменные название услуги и почту
        String sBP = "_test_mailer";

        openURLservice(getBaseUrl() + "/service/755/general");

        // 2016-11-23 Убираем проверку на открытие нужной услуги.
        // Считаем, что мы ее правильно по прямому пути выше 
        // _step("2. Проверить, что открылась нужная услуга");
        // assertThis(driver, o.usluga, "_test_mailer");
        o.mokAuthorization();
//        o.selectBank("Приватбанк");

        setFieldValue("mail_test", "v-i-d-k@mail.ru");
        setFieldFile(sBP, "nFileOne", "src/test/resources/files/test.jpg");
        //attachDocument(tp.attachDocumentButton, "src/test/resources/files/test.jpg", driver);

        click(o.buttonSendingForm);

        o.checkMessageSuccess("Шановний(-а) MockUser MockUser!\n"
                + "Ваше звернення X-XXXXXXXX успішно зареєстровано");

        click(o.buttonLogOut);

    }
    //</editor-fold>

    @Test
    public void testDeleteMethodForDEBUG() throws Exception {
        DeleteTask delete = new DeleteTask();

        orderId.add("");
//        delete.deleteAllOrderId();
        delete.deleteAllOrderId();

    }
}
