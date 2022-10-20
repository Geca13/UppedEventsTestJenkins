package microsites;

import io.github.bonigarcia.wdm.WebDriverManager;
import microsites.components.SignInModal;
import microsites.pages.EventInformationPage;
import microsites.pages.EventsPage;
import microsites.pages.TicketingShared;
import microsites.pages.Tickets;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class MicrositesTest {

    @BeforeAll
    public static void setup() {
        WebDriverManager.chromedriver().setup();

    }

    @Test
    public void loginWithEmailAndPasswordAndMakeDiscountedPurchaseWithWallet() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        TicketingShared shared = new TicketingShared(driver);
        EventsPage eventsPage = new EventsPage(driver);
        SignInModal signInModal = new SignInModal(driver);
        EventInformationPage event = new EventInformationPage(driver);
        Tickets tickets = new Tickets(driver);
        eventsPage.loadEventsPage();
        eventsPage.clickSignInLink();
        signInModal.signInModalIsDisplayed();
        signInModal.enterEmailIntoEmailInput();
        signInModal.enterPasswordIntoPasswordInput();
        signInModal.clickSignInButtonToSignIn();
        Thread.sleep(4000);
        eventsPage.clickEvent();
        Thread.sleep(2500);
        event.clickBuyTicketsButton();
        Thread.sleep(500);
        tickets.clickIncreaseQuantityButton(0);
        tickets.enterPromoCodeInDiscountField("TRIPLE");
        tickets.clickDiscountApplyButton();
        //driver.findElements(By.xpath("//*[text()=' + ']")).get(0).click();
        //List<WebElement> inputs = driver.findElements(By.tagName("input"));
       //driver.findElements(By.tagName("input")).get(inputs.size() - 1).sendKeys("PROMO1");
       // driver.findElement(By.xpath("//*[text()='Apply']")).click();
        Thread.sleep(500);
        WebElement grangTotal = driver.findElement(By.className("grand-total"));
        String total = grangTotal.findElements(By.xpath("./child::*")).get(1).getText();
        driver.findElement(By.xpath("//*[text()='Next']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[text()='Next']")).click();
        WebElement wallet = driver.findElement(By.xpath("//*[text()=' Pay with wallet ']"));
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(wallet)).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[text()='Pay with wallet']")).click();
        Thread.sleep(5000);
        String confirmTotal = driver.findElement(By.className("total-amt")).getText();
        String toCompare = confirmTotal.substring(7,confirmTotal.length());
        Assertions.assertEquals(total, toCompare);
        driver.quit();
    }

    @Test
    public void loginWithFacebookAndMakePurchaseOnTwoTicketsWithWallet() throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://events.dev.uppedevents.com/events/");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[text()='Sign In']")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[text()='Sign In with Facebook']")).click();
        Thread.sleep(2000);
        String parentWindow = driver.getWindowHandle();
        for(String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        driver.findElement(By.name("email")).sendKeys("javageca@gmail.com");
        driver.findElement(By.name("pass")).sendKeys("Vardar13Negotino");
        driver.findElement(By.name("login")).click();
        driver.switchTo().window(parentWindow);
        Thread.sleep(5000);
        //driver.findElement(By.xpath("//*[text()='Qa Purchase']")).click();
        driver.findElements(By.tagName("event-card")).get(1).click();
        Thread.sleep(2500);
        driver.findElement(By.xpath("//*[text()=' Buy Tickets ']")).click();
        Thread.sleep(2500);
        driver.findElements(By.xpath("//*[text()=' + ']")).get(0).click();
        driver.findElements(By.xpath("//*[text()=' + ']")).get(1).click();
        List<WebElement> inputs = driver.findElements(By.tagName("input"));
        driver.findElements(By.tagName("input")).get(inputs.size() - 1).sendKeys("PROMO1");
        driver.findElement(By.xpath("//*[text()='Apply']")).click();
        Thread.sleep(500);
        WebElement grangTotal = driver.findElement(By.className("grand-total"));
        String total = grangTotal.findElements(By.xpath("./child::*")).get(1).getText();
        driver.findElement(By.xpath("//*[text()='Next']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[text()='Next']")).click();
        WebElement wallet = driver.findElement(By.xpath("//*[text()=' Pay with wallet ']"));
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(wallet)).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[text()='Pay with wallet']")).click();
        Thread.sleep(5000);
        String confirmTotal = driver.findElement(By.className("total-amt")).getText();
        String toCompare = confirmTotal.substring(7,confirmTotal.length());
        Assertions.assertEquals(total, toCompare);
        driver.quit();
    }

    @Test
    public void checkTaxesTotalEqualsTaxesInTooltipOnMicrosites() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://events.dev.uppedevents.com/events/");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[text()='Sign In']")).click();
        driver.findElement(By.name("email")).sendKeys("parma15@parma.it");
        driver.findElement(By.name("password")).sendKeys("Pero1234");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(5000);
        driver.findElements(By.tagName("event-card")).get(1).click();
        Thread.sleep(2500);
        driver.findElement(By.xpath("//*[text()=' Buy Tickets ']")).click();
        Thread.sleep(2500);
        driver.findElements(By.xpath("//*[text()=' + ']")).get(0).click();
        Thread.sleep(500);
        driver.findElements(By.xpath("//*[text()=' + ']")).get(0).click();
        Thread.sleep(2500);
        WebElement taxIcon = driver.findElements(By.className("fa-info-circle")).get(0);
        Actions action = new Actions(driver);
        action.moveToElement(taxIcon).perform();

        Double totalTax = 0.00;
        List<WebElement> elements = driver.findElements(By.className("ml-3"));
        for(WebElement element : elements){
         Double tax = Double.parseDouble(element.getText().substring(1));
         totalTax = totalTax + tax;
        }
        BigDecimal bd = new BigDecimal(Double.toString(totalTax));
        bd = bd.setScale(2, RoundingMode.HALF_UP);

        WebElement providedTaxes = driver.findElements(By.className("mini-total")).get(1);
        String providedTaxesNumber = providedTaxes.findElements(By.xpath("./child::*")).get(2).getText();
        Double convertedTax = Double.parseDouble(providedTaxesNumber.substring(1));
        Assertions.assertEquals(convertedTax, bd.doubleValue());
        driver.quit();
    }

    @Test
    public void checkFeesTotalEqualsFeesInTooltipOnMicrosites() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://events.dev.uppedevents.com/events/");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[text()='Sign In']")).click();
        driver.findElement(By.name("email")).sendKeys("parma15@parma.it");
        Thread.sleep(500);
        driver.findElement(By.name("password")).sendKeys("Pero1234");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(5000);
        driver.findElements(By.tagName("event-card")).get(1).click();
        Thread.sleep(2500);
        driver.findElement(By.xpath("//*[text()=' Buy Tickets ']")).click();
        Thread.sleep(2500);
        driver.findElements(By.xpath("//*[text()=' + ']")).get(0).click();
        Thread.sleep(500);
        driver.findElements(By.xpath("//*[text()=' + ']")).get(0).click();
        Thread.sleep(2500);
        WebElement feeIcon = driver.findElements(By.className("fa-info-circle")).get(1);
        Actions action = new Actions(driver);
        action.moveToElement(feeIcon).perform();

        Double totalFee = 0.00;
        List<WebElement> elements = driver.findElements(By.className("ml-3"));
        for(WebElement element : elements){
            Double fee = Double.parseDouble(element.getText().substring(1));

            totalFee = totalFee + fee;
        }
        BigDecimal bd = new BigDecimal(Double.toString(totalFee));
        bd = bd.setScale(2, RoundingMode.HALF_UP);

        WebElement providedFees = driver.findElements(By.className("mini-total")).get(2);
        String providedFeesNumber = providedFees.findElements(By.xpath("./child::*")).get(2).getText();
        Double convertedTax = Double.parseDouble(providedFeesNumber.substring(1));
        Assertions.assertEquals(convertedTax, bd.doubleValue());
        driver.quit();
    }


}
