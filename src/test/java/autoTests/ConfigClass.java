package autoTests;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Oleksandr Belichenko
 */
public class ConfigClass {

    public static List<String> orderId = new ArrayList<>();

    private static String DELTA_CENTRAL = "https://delta.test.igov.org.ua";
    private static String DELTA_REGION = "https://delta.test.region.igov.org.ua";
    private static String GAMMA_CENTRAL = "https://gamma.test.igov.org.ua";
    private static String GAMMA_REGION = "https://gamma.test.idoc.com.ua";
    private static String ALPHA_CENTRAL = "https://alpha.test.igov.org.ua";
    private static String ALPHA_REGION = "https://alpha.test.idoc.com.ua";
    private static String BETA_REGION = "https://beta.test.idoc.com.ua";
    private static String COORG_GAMMA_REGION = "https://coorg-gamma.test.idoc.com.ua";
    private static String BETA_AUTOTEST = "https://beta-autotest.test.idoc.com.ua";

    public String getBaseUrl() {
        return DELTA_CENTRAL;
    }

    public String getRegionUrl() {
        return BETA_AUTOTEST;
    }

    public String getCollectiveURL() {
        return BETA_REGION;
    }
}
