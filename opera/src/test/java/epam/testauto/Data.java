package epam.testauto;

import org.testng.annotations.DataProvider;

/**
 * Created by Rita on 16.10.2016.
 */
public class Data {

    @DataProvider(name = "login")
    public static Object[][] logData() {
        return new Object[][] {
                {"", ""},
                {"epam", ""},
                {"", "1234"},
                {"user", "password"},
                {"epam", "1234"}
        };
    }
}
