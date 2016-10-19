package epam.controls;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

/**
 * Created by Rita on 19.10.2016.
 */
public class Login {
    private WebDriver driver;
    private By locator;
    private By usernameLocator = By.id("Login");
    private By passwordLocator = By.id("Password");
    private By submitLocator = By.cssSelector("button.btn-login");

    public Login(WebDriver driver, String locator) {
        this.driver = driver;
        this.locator = By.cssSelector(locator);
    }

    public void clickToStart() {
        driver.findElement(locator).click();
    }

    public void typeData(String sUsername, String sPassword) {
        driver.findElement(usernameLocator).sendKeys(sUsername);
        driver.findElement(passwordLocator).sendKeys(sPassword);
    }

    public void submit() {
        driver.findElement(submitLocator).click();
    }

    public void loginCheck(boolean bool) {
        try {
            driver.navigate().to("/tests/page1.htm");
            Assert.assertEquals(driver.getTitle(), "Contact Form");
            if (bool == "Contact Form".equals(driver.getTitle())) {
                System.out.println("Succesfully submitted");
            }
        }
        catch (AssertionError e) {
            System.out.println("Login failed, invalid username or password");
        }
    }
}
