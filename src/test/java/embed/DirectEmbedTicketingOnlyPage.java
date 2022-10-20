package embed;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DirectEmbedTicketingOnlyPage {
    private final WebDriver driver;

    public DirectEmbedTicketingOnlyPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://www.uppedevents.com/embed-testing-direct/");
    }

    public void maximizeWindow() {
        driver.manage().window().maximize();
    }

    public void switchToIframe(){
        driver.switchTo().frame("uwWidget");
    }

    public boolean waitForLoadByCssClass(String className) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(className))).isDisplayed();
    }
}
