package epam.testauto;

/**
 * Created by Rita on 09.10.2016.
 */

import epam.controls.Checkbox;
import epam.controls.Login;
import epam.controls.RadioButton;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class Example {
    private WebDriver firefox;

    @BeforeSuite
    public void setup() {
        System.setProperty("webdriver.gecko.driver","src/test/resources/geckodriver.exe");
        firefox = new FirefoxDriver();
    }

    @Test(priority = 0)
    public void loadPage() {
        String url = "https://jdi-framework.github.io/tests/";
        firefox.get(url);
        Assert.assertEquals(firefox.getTitle(), "Index Page");
    }

    @Test(priority = 1, dataProviderClass = Data.class, dataProvider = "login")
    public void authorization(boolean bool, String sUsername, String sPassword) {
        /*firefox.findElement(By.cssSelector(".profile-photo")).click();
        firefox.findElement(By.id("Login")).sendKeys(sUsername);
        firefox.findElement(By.id("Password")).sendKeys(sPassword);
        firefox.findElement(By.cssSelector("button.btn-login")).click();

        try {
            firefox.navigate().to("/tests/page1.htm");
            Assert.assertEquals(firefox.getTitle(), "Contact Form");
            if (bool == "Contact Form".equals(firefox.getTitle())) {
                System.out.println("Succesfully submitted");
            }
        }
        catch (AssertionError e) {
            System.out.println("Login failed, invalid username or password");
        }*/

        Login login = new Login(firefox, ".profile-photo");
        login.clickToStart();
        login.typeData(sUsername, sPassword);
        login.submit();
        login.loginCheck(bool);
    }

    @Test(priority = 2)
    public void workingWPageObject() throws InterruptedException {
        firefox.navigate().to("/tests/page8.htm");
        Assert.assertEquals(firefox.getTitle(), "Different Element");

        Checkbox water = new Checkbox(firefox, "Water");
        Checkbox earth = new Checkbox(firefox, "Earth");
        Checkbox wind = new Checkbox(firefox, "Wind");
        Checkbox fire = new Checkbox(firefox, "Fire");
        water.check();
        earth.check();
        wind.check();
        fire.check();
        Assert.assertTrue(water.isChecked());
        Assert.assertTrue(earth.isChecked());
        Assert.assertTrue(wind.isChecked());
        Assert.assertTrue(fire.isChecked());
        water.uncheck();
        earth.uncheck();
        wind.uncheck();
        fire.uncheck();
        Assert.assertFalse(water.isChecked());
        Assert.assertFalse(earth.isChecked());
        Assert.assertFalse(wind.isChecked());
        Assert.assertFalse(fire.isChecked());

        RadioButton gold = new RadioButton(firefox, "Gold");
        RadioButton silver = new RadioButton(firefox, "Silver");
        RadioButton bronze = new RadioButton(firefox, "Bronze");
        RadioButton selen = new RadioButton(firefox, "Selen");
        gold.check();
        Assert.assertTrue(gold.isChecked());
        silver.check();
        Assert.assertTrue(silver.isChecked());
        bronze.check();
        Assert.assertTrue(bronze.isChecked());
        selen.check();
        Assert.assertTrue(selen.isChecked());
    }

    @Test(priority = 3)
    //generate js alert and handle it
    public void handleAlert() throws WebDriverException{
        JavascriptExecutor js = (JavascriptExecutor) firefox;
        try {
            js.executeScript("alert('The execution is started');");
        }
        catch (WebDriverException e) {
            System.out.println(firefox.switchTo().alert().getText());
            firefox.switchTo().alert().accept();
        }
    }

    @AfterSuite
    public void shutDown() {
        firefox.quit();
    }
}
