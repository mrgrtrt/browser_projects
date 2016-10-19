package epam.testauto;

/**
 * Created by Rita on 09.10.2016.
 */

import epam.controls.Login;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Example {
    public EdgeDriver edge;

    @BeforeSuite
    public void setup() {
        System.setProperty("webdriver.edge.driver","src/test/resources/MicrosoftWebDriver.exe");
        edge = new EdgeDriver();
    }

    @Test(priority = 0)
    public void loadPage() {
        String url = "https://jdi-framework.github.io/tests/";
        edge.get(url);
    }

    @Test(priority = 1, dataProviderClass = Data.class, dataProvider = "login")
    public void test(boolean bool, String sUsername, String sPassword){
        /*edge.findElement(By.cssSelector(".profile-photo")).click();
        edge.findElement(By.id("Login")).sendKeys(sUsername);
        edge.findElement(By.id("Password")).sendKeys(sPassword + Keys.ENTER);

        try {
            edge.navigate().to("https://jdi-framework.github.io/tests/page1.htm");
            Assert.assertEquals(edge.getTitle(), "Contact Form");
            if (bool == "Contact Form".equals(edge.getTitle())) {
                System.out.println("Successfully submitted");
            }
        }
        catch (AssertionError e) {
            System.out.println("Login failed, invalid username or password");
        }*/

        Login login = new Login(edge, ".profile-photo");
        login.clickToStart();
        login.typeData("Login", sUsername, "Password", sPassword);
        login.submit("button.btn-login");
        login.loginCheck(bool);
    }

    @Test(priority = 2)
    public void handleAlert() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) edge;
        js.executeScript("alert('This is an alert');");
        System.out.println(edge.switchTo().alert().getText());
        edge.switchTo().alert().accept();
    }

    @AfterSuite
    public void shutDown() {
        edge.quit();
    }
}
