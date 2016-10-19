package epam.controls;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Rita on 19.10.2016.
 */
public class Login {
    private WebDriver driver;
    private By locator;

    public Login(WebDriver driver, String locator) {
        this.driver = driver;
        this.locator = By.cssSelector(locator);
    }

    public void clickToStart() {
        driver.findElement(locator).click();
    }

    public void typeData(String usernameLocator, String sUsername, String passwordLocator, String sPassword) {
        driver.findElement(By.id(usernameLocator)).sendKeys(sUsername);
        driver.findElement(By.id(passwordLocator)).sendKeys(sPassword);
    }

    public void submit(String submitLocator) {
        driver.findElement(By.cssSelector(submitLocator)).click();
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
