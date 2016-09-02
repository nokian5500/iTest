package autoTests;

import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;

public class TestRunner extends SetupAndTeardown {

    TestSuite testSuite = new TestSuite();

    @Test(enabled = true, groups = {"Main", "Критический функционал"}, priority = 1)
    public void A1_Test_Example_Fill_Field() throws Exception {
        CustomMethods.addTestNameToTheReport(
                "Тестовый пример заполнения полей",
                Thread.currentThread().getStackTrace()[1].toString()
    );
    testSuite.A1_Test_Example_Fill_Field(driver);
}

    @Test(enabled = true, groups = {"Main", "Критический функционал"}, priority = 2)
    public void A2_Test_Example_Attach() throws Exception {
        CustomMethods.addTestNameToTheReport(
                "Тестовый пример attach",
                Thread.currentThread().getStackTrace()[1].toString()
        );
        testSuite.A2_Test_Example_Attach(driver);
    }

    @Test(enabled = true, groups = {"Main", "Критический функционал"}, priority = 3)
    public void A3_Test_Example_Select() throws Exception {
        CustomMethods.addTestNameToTheReport(
                "Тестовый пример работа с выпадающим списком",
                Thread.currentThread().getStackTrace()[1].toString()
        );
        testSuite.A3_Test_Example_Select(driver);
    }
}
