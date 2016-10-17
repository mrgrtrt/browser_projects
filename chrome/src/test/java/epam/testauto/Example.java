package epam.testauto;

/**
 * Created by Rita on 09.10.2016.
 */
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;

public class Example {
    private ChromeDriver chrome;

    @BeforeSuite
    public void setup() {
        System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver.exe");

        //to remove '--ignore-certificate-errors--' message when launching chromedriver
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");
        capabilities.setCapability("chrome.binary","src/test/resources/chromedriver.exe");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        chrome = new ChromeDriver(capabilities);
        //maximize bc default launching has the other size
        //and there is no login element on the website in that size
        chrome.manage().window().maximize();
    }

    @Test(priority = 0)
    public void loadPage() {
        String url = "https://jdi-framework.github.io/tests/";
        chrome.get(url);
        Assert.assertEquals(chrome.getTitle(), "Index Page");
    }

    @Test(priority = 1, dataProviderClass = Data.class, dataProvider = "login")
    public void authorization(String sUsername, String sPassword) {
        chrome.findElement(By.cssSelector(".profile-photo")).click();
        chrome.findElement(By.id("Login")).sendKeys(sUsername);
        chrome.findElement(By.id("Password")).sendKeys(sPassword + Keys.ENTER);

        try {
            Assert.assertTrue(chrome.findElement(By.cssSelector(".logout")).isEnabled());
            chrome.navigate().to("https://jdi-framework.github.io/tests/page1.htm");
            Assert.assertEquals(chrome.getTitle(), "Contact Form");
            System.out.println("Successfully submitted");
        }
        catch (AssertionError e) {
            System.out.println("Login failed, invalid username or password");
        }
    }

    @Test(priority = 2)
    public void handleAlert() {
        JavascriptExecutor js = (JavascriptExecutor) chrome;
        js.executeScript("alert('This is autotest');");
        System.out.println(chrome.switchTo().alert().getText());
        chrome.switchTo().alert().accept();
    }

    @AfterSuite
    public void shutDown() {
        chrome.quit();
    }
}