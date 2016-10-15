package epam.testauto;

/**
 * Created by Rita on 09.10.2016.
 */
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
public class Example {
    public OperaDriver opera;

    @Test(priority = 1)
    public void test() {
        System.setProperty("webdriver.opera.driver", "src/test/resources/operadriver.exe");
        OperaDriver opera = new OperaDriver();
        opera.manage().window().maximize();

        String url = "https://jdi-framework.github.io/tests/";
        opera.get(url);
    }

    @Test(priority = 2, dataProviderClass = Data.class, dataProvider = "login")
    public void test2(String sUsername, String sPassword) {
        opera.findElement(By.cssSelector(".profile-photo")).click();
        opera.findElement(By.id("Login")).sendKeys(sUsername);
        opera.findElement(By.id("Password")).sendKeys(sPassword + Keys.ENTER);

        Assert.assertTrue(opera.findElement(By.cssSelector(".logout")).isEnabled());
        opera.navigate().to("https://jdi-framework.github.io/tests/page1.htm");
        Assert.assertEquals(opera.getTitle(), "Contact Form");

        JavascriptExecutor js = (JavascriptExecutor) opera;
        js.executeScript("alert('Something interesting');");
        System.out.println(opera.switchTo().alert().getText());
        opera.switchTo().alert().accept();

        opera.quit();
    }

}
