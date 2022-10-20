package microsites.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EventsPage {

    private final WebDriver driver;

    public EventsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void loadEventsPage(){
        driver.manage().window().maximize();
        driver.get("https://events.dev.uppedevents.com/events/");
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.elementToBeClickable(getSignInLink()));
    }
    public WebElement getSignInLink(){
      return driver.findElement(By.xpath("//*[text()='Sign In']"));
    }
    public void clickSignInLink(){
        WebElement signIn = getSignInLink();
        signIn.click();
    }
    public WebElement getSignUpLink(){
        return driver.findElement(By.xpath("//*[text()='Sign Up']"));
    }
    public void clickSignUpLink(){
        WebElement signUp = getSignInLink();
        signUp.click();
    }
    public void clickEvent(){
        Actions action = new Actions(driver);
        WebElement eventName = driver.findElement(By.xpath("//*[text()='Refactored']"));
        action.moveToElement(eventName).perform();
        WebElement eventCard = eventName.findElement(By.xpath("./.."));
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.elementToBeClickable(eventCard));
        eventCard.click();
    }


}
