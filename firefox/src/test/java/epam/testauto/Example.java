package epam.testauto;

/**
 * Created by Rita on 09.10.2016.
 */

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
public class Example {
    private FirefoxDriver firefox;

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
    public void authorization(String sUsername, String sPassword) {
        firefox.findElement(By.cssSelector(".profile-photo")).click();
        firefox.findElement(By.id("Login")).sendKeys(sUsername);
        firefox.findElement(By.id("Password")).sendKeys(sPassword + Keys.ENTER);
        
        try {
            Assert.assertTrue(firefox.findElement(By.cssSelector(".logout")).isEnabled());
            firefox.navigate().to("/tests/page1.htm");
            Assert.assertEquals(firefox.getTitle(), "Contact Form");
            System.out.println("Succesfully submitted");
        }
        catch (AssertionError e) {
            System.out.println("Login failed, invalid username or password");
        }
		
        /*Assert.assertTrue(firefox.findElement(By.id("Name")).isDisplayed());
        Assert.assertTrue(firefox.findElement(By.id("LastName")).isDisplayed());
        Assert.assertTrue(firefox.findElement(By.id("Description")).isDisplayed());*/
    }

    @Test(priority = 2)
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
