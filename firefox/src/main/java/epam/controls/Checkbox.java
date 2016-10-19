package epam.controls;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Rita on 17.10.2016.
 */
public class Checkbox extends Element {

    public Checkbox(WebDriver driver, String text) {
        super(driver, By.xpath("//label[contains(., '" + text + "')]/input"));
    }

    public boolean isChecked() {
        return getElement().isSelected();
    }

    public void check() {
        if (!isChecked()) {
            getElement().click();
        }
    }

    public void uncheck() {
        if (isChecked()) {
            getElement().click();
        }
    }

}
