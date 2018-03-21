package autoTests;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Oleksandr Belichenko
 */
public class ConfigClass {

    public static List<String> orderId = new ArrayList<>();

    public String getBaseUrl() {
        String DeltaCentral = "https://delta.test.igov.org.ua";
        String DeltaRegion = "https://delta.test.region.igov.org.ua";
        String GammaCentral = "https://gamma.test.igov.org.ua";
        String GammaRegion = "https://gamma.test.idoc.com.ua";
        String AlphaCentral = "https://alpha.test.igov.org.ua";
        String AlphaRegion = "https://alpha.test.idoc.com.ua";
        return DeltaCentral;
    }

    public String getRegionUrl() {
        String DeltaCentral = "https://delta.test.igov.org.ua";
        String DeltaRegion = "https://delta.test.region.igov.org.ua";
        String GammaCentral = "https://gamma.test.igov.org.ua";
        String GammaRegion = "https://gamma.test.idoc.com.ua";
        String AlphaCentral = "https://alpha.test.igov.org.ua";
        String AlphaRegion = "https://alpha.test.idoc.com.ua";
        String BetaCentral = "https://beta.test.igov.org.ua";
        String BetaRegion = "https://beta.test.idoc.com.ua";
        return BetaRegion;
    }
}
