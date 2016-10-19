package epam.testauto;

import org.testng.annotations.DataProvider;

/**
 * Created by Rita on 16.10.2016.
 */
public class Data {
    @DataProvider(name = "login")
    public static Object[][] logData() {
        return new Object[][] {
                {false, "", ""},
                {false, "epam", ""},
                {false, "", "1234"},
                {false, "user", "password"},
                {true, "epam", "1234"}
        };
    }
}
