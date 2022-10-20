package Service;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FindByMethods {

    private final WebDriver driver;

    public FindByMethods(WebDriver driver) {
        this.driver = driver;
    }

    public void findById(WebDriver driver, String id){

        driver.findElement(By.id(id)).click();
    }

}
