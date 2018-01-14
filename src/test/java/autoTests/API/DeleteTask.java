package autoTests.API;

import autoTests.ConfigClass;

/**
 * Created by Privat24 on 15.11.2016.
 */
public class DeleteTask extends ConfigClass {

    public void deleteAllOrderId() throws Exception {
        for (String object : orderId) {
            System.out.println("object: " + object);
        }
    }
}
