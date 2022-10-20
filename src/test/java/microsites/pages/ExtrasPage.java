package microsites.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ExtrasPage {
    private final WebDriver driver;

    public ExtrasPage(WebDriver driver) {
        this.driver = driver;
    }
    public WebElement extrasContainer(){
        return driver.findElement(By.className("extras-container"));
    }

}
