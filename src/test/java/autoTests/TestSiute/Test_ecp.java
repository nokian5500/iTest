/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoTests.TestSiute;

import autoTests.CustomMethods;
import autoTests.pages.main.TemplatePage;
import org.junit.Test;

/**
 *
 * @author User
 */
public class Test_ecp extends CustomMethods {

    @Test
    public void Test_ecp() throws Exception {
        /**
         * ***************************************объявляем элементы страниц******************************************
         */
        TemplatePage o = new TemplatePage();
        String sBP = "justice_0087_FOPclose";
        String email = "autotestbeta@gmail.com";

        openURLservice(getBaseUrl() + "/service/87/general");

        o.selectRegion("Донецька");

        o.testPrivat24Authorization();

        setFieldSelectByText("asSelectFIOCheck", "Так - все вірно");
        setFieldValue("phone", "+380623155533");
        setEmail(sBP, "email", email);

        clickButton("Замовити послугу");
        uploadECPKeyFile("src/test/resources/files/Key-6.dat");
//        uploadECPKey();
        setPaswordForECPKey();
        pause(10000);

    }
}
