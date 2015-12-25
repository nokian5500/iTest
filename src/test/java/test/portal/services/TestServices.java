package test.portal.services;

import common.Constants;
import org.testng.annotations.Test;
import pages.service.test.*;
import test.TestBase;

import java.awt.*;

import static org.testng.Assert.assertEquals;

public class TestServices extends TestBase {

    //TODO https://github.com/e-government-ua/iTest/issues/1

    @Test(priority = 10)
    public void test_dependence_form1() throws AWTException {
        // ------------------- Тестовые данные -------------------//
        String server = Constants.Server.VERSION_SERVER;
        String service = Constants.TestService.TEST_DEPENDENCE_FORM;
        String serviceName = "_test_dependence_form";
        String client = "отримувач особисто";
        String info = "test";
        String document = "src/test/resources/test.jpg";
        String email = "test@gmail.com";

        // --------------------- Тест-кейс----------------------//
        app.mainPage.goToTestServices(server, service);
        app.authorizationPage.privatBankAuthorization();
        assertEquals(app.testDependenceFormPage.getServiceName(), serviceName);
        app.testDependenceFormPage
                .selectClient(client)
                .typeInInfo1Field(info);
        app.attachDocument(app.testDependenceFormPage.attachDocumentButton, document);
        app.testDependenceFormPage
                .typeInEmailField(email)
                .clickConfirmButton()
                .verifyServiceSuccessCreated(email)
                .saveReferenceNumber();
        app.mainPage.goToStatus();
        app.statusPage.enterReferenceNumber(TestDependenceFormPage.referenceNumber)
                .clickViewStatusButton()
                .verifyStatus(Constants.Status.SUCCESS_STATUS);
    }

    @Test(priority = 20)
    public void test_dependence_form2() throws AWTException {
        // ------------------- Тестовые данные -------------------//
        String server = Constants.Server.VERSION_SERVER;
        String service = Constants.TestService.TEST_DEPENDENCE_FORM;
        String serviceName = "_test_dependence_form";
        String client = "представник отримувача";
        String info = "test";
        String document = "src/test/resources/test.jpg";
        String email = "test@gmail.com";

        // --------------------- Тест-кейс----------------------//
        app.mainPage.goToTestServices(server, service);
        app.authorizationPage.privatBankAuthorization();
        assertEquals(app.testDependenceFormPage.getServiceName(), serviceName);
        app.testDependenceFormPage
                .selectClient(client)
                .typeInInfo2Field(info);
        app.attachDocument(app.testDependenceFormPage.attachDocumentButton, document);
        app.testDependenceFormPage
                .typeInEmailField(email)
                .clickConfirmButton()
                .verifyServiceSuccessCreated(email)
                .saveReferenceNumber();
        app.mainPage.goToStatus();
        app.statusPage.enterReferenceNumber(TestDependenceFormPage.referenceNumber)
                .clickViewStatusButton()
                .verifyStatus(Constants.Status.SUCCESS_STATUS);
    }

    @Test(priority = 30)
    public void test_fields_bankid() throws AWTException {
        // ------------------- Тестовые данные -------------------//
        String server = Constants.Server.VERSION_SERVER;
        String service = Constants.TestService.TEST_FIELDS_BANKID;
        String serviceName = "_test_fields_bankid";
        String region = Constants.Areas.Region.DNIPROPETROVSKA;
        String city = Constants.Areas.City.DNIPROPETROVSK;
        String country = "country";
        String address = "address";
        String document = "src/test/resources/test.jpg";

        // --------------------- Тест-кейс----------------------//
        app.mainPage.goToTestServices(server, service);
        app.selectAreaPage.selectRegion(region);
        app.selectAreaPage.selectCity(city);
        app.authorizationPage.privatBankAuthorization();
        assertEquals(app.testFieldsBankidPage.getServiceName(), serviceName);
        app.testFieldsBankidPage
                .typeInCountryField(country)
                .typeInAddressField(address);
        app.attachDocument(app.testFieldsBankidPage.attachDocumentButton, document);
        app.testFieldsBankidPage
                .clickConfirmButton()
                .verifyServiceSuccessCreated()
                .saveReferenceNumber();
        app.mainPage.goToStatus();
        app.statusPage.enterReferenceNumber(TestFieldsBankidPage.referenceNumber)
                .clickViewStatusButton()
                .verifyStatus(Constants.Status.SUCCESS_STATUS);
    }

    @Test(priority = 40)
    public void test_liqpay() {
        // ------------------- Тестовые данные -------------------//
        String server = Constants.Server.VERSION_SERVER;
        String service = Constants.TestService.TEST_LIQPAY;
        String serviceName = "_test_liqpay";
        String region = Constants.Areas.Region.DNIPROPETROVSKA;
        String city = Constants.Areas.City.DNIPROPETROVSK;
        String bankIdAddressField = Constants.TestData.PersonalInfo.BIRTH_LOCAL;
        String vin = "12345678912345678";
        String brand = "brand";
        String model = "model";
        String number = "number";
        String invoiceNumber = "invoiceNumber";
        String phone = "380931234567";
        String email = Constants.TestData.PersonalInfo.E_MAIL;

        // --------------------- Тест-кейс----------------------//
        app.mainPage.goToTestServices(server, service);
        app.authorizationPage.privatBankAuthorization();
        assertEquals(app.testLiqpayPage.getServiceName(), serviceName);
        app.testLiqpayPage
                .typeInBankIdAddressField(bankIdAddressField)
                .typeInVinField(vin)
                .typeInBrandField(brand)
                .typeInModelField(model)
                .typeInNumberField(number)
                .typeInInvoiceNumberField(invoiceNumber)
                .selectDate()
                .typeInPhoneField(phone)
                .typeInEmailField(email)
                .clickConfirmButton()
                .verifyServiceSuccessCreated()
                .saveReferenceNumber();
        app.mainPage.goToStatus();
        app.statusPage.enterReferenceNumber(TestLiqpayPage.referenceNumber)
                .clickViewStatusButton()
                .verifyStatus(Constants.Status.SUCCESS_STATUS);
    }

    @Test(priority = 50)
    public void test_mailer() throws AWTException {
        // ------------------- Тестовые данные -------------------//
        String server = Constants.Server.VERSION_SERVER;
        String service = Constants.TestService.TEST_MAILER;
        String serviceName = "_test_mailer";
        String email = Constants.TestData.PersonalInfo.E_MAIL;
        String document = "src/test/resources/test.jpg";

        // --------------------- Тест-кейс----------------------//
        app.mainPage.goToTestServices(server, service);
        app.authorizationPage.privatBankAuthorization();
        assertEquals(app.testMailerPage.getServiceName(), serviceName);
        app.testMailerPage
                .typeInEmailField(email);
        app.attachDocument(app.testMailerPage.attachDocumentButton, document);
        app.testMailerPage
                .clickConfirmButton()
                .verifyServiceSuccessCreated()
                .saveReferenceNumber();
        app.mainPage.goToStatus();
        app.statusPage.enterReferenceNumber(TestMailerPage.referenceNumber)
                .clickViewStatusButton()
                .verifyStatus(Constants.Status.SUCCESS_STATUS);
    }

    //TODO: implement test
    @Test(enabled = false, priority = 60)
    public void test_print_form() {
        // ------------------- Тестовые данные -------------------//
        String server = Constants.Server.VERSION_SERVER;
        String service = Constants.TestService.TEST_PRINT_FORM;

        // --------------------- Тест-кейс----------------------//
        app.mainPage.goToTestServices(server, service);
    }

    //TODO: implement test
    @Test(enabled = false, priority = 70)
    public void test_queue_cancel() {
        // ------------------- Тестовые данные -------------------//
        String server = Constants.Server.VERSION_SERVER;
        String service = Constants.TestService.TEST_QUEUE_CANCEL;

        // --------------------- Тест-кейс----------------------//
        app.mainPage.goToTestServices(server, service);
    }

    @Test(priority = 80)
    public void test_ZP_cnap_mailer() throws AWTException {
        // ------------------- Тестовые данные -------------------//
        String server = Constants.Server.VERSION_SERVER;
        String service = Constants.TestService.TEST_ZP_CNAP_MAILER;
        String region = Constants.Areas.Region.ZAPORIZHSKA;
        String serviceName = "_test_ZP_cnap_mailer";
        String email = Constants.TestData.PersonalInfo.E_MAIL;
        String phone = Constants.TestData.PersonalInfo.PHONE;
        String document = "src/test/resources/test.jpg";

        // --------------------- Тест-кейс----------------------//
        app.mainPage.goToTestServices(server, service);
        app.selectAreaPage.selectRegion(region);
        app.authorizationPage.privatBankAuthorization();
        assertEquals(app.testZPCnapMailerPage.getServiceName(), serviceName);
        app.testZPCnapMailerPage
                .typeInPhoneField(phone)
                .typeInEmailField(email);
        app.attachDocument(app.testZPCnapMailerPage.attachDocumentButton, document);
        app.testZPCnapMailerPage
                .clickConfirmButton()
                .verifyServiceSuccessCreated()
                .saveReferenceNumber();
        app.mainPage.goToStatus();
        app.statusPage.enterReferenceNumber(TestZPCnapMailerPage.referenceNumber)
                .clickViewStatusButton()
                .verifyStatus(Constants.Status.SUCCESS_STATUS);
    }
}