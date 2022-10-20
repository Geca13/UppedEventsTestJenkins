package embed;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class TicketingOnlyTest {

    @BeforeAll
    public static void setup() {
       WebDriverManager.chromedriver().setup();
    }



    private WebDriver getDriver() {
        return new ChromeDriver();
    }


    @Test
    public void loginWithEmailAndPasswordAndMakePurchase() throws InterruptedException {

        WebDriver driver = getDriver();
        DirectEmbedTicketingOnlyPage embed = new DirectEmbedTicketingOnlyPage(driver);
        embed.maximizeWindow();
        embed.open();
        Thread.sleep(2000);
        driver.switchTo().frame("uwWidget");
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(driver.findElements(By.tagName("select")).get(0))).click();
        WebElement selectOne = driver.findElements(By.tagName("select")).get(0);
        //Select firstTicket = new Select(selectOne);
        selectOne.sendKeys("1");
        WebElement nextButton = driver.findElement(By.xpath("//*[text()='Next']"));
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(nextButton)).click();
        WebElement emailInput = driver.findElements(By.tagName("input")).get(0);
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(emailInput)).sendKeys("parma15@parma.it");
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(driver.findElements(By.tagName("input")).get(1))).sendKeys("Pero1234");
        driver.findElement(By.xpath("//*[text()='Login Now']")).click();
        Thread.sleep(1000);
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.className("next-btn")))).click();
        WebElement nextBtn2 = driver.findElement(By.className("next-btn"));
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(nextBtn2)).click();
        WebElement wallet = driver.findElement(By.xpath("//*[text()=' Pay with wallet ']"));
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(wallet)).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[text()='Pay with wallet']")).click();
        Thread.sleep(5000);
        driver.findElement(By.className("ticket-title")).isDisplayed();
        System.out.println(driver.findElement(By.className("ticket-title")).getText());
        driver.quit();
    }

    @Test
    public void loginWithFacebookAndMakeDiscountedPurchase() throws InterruptedException {
        WebDriver driver = getDriver();
        DirectEmbedTicketingOnlyPage embed = new DirectEmbedTicketingOnlyPage(driver);
        embed.maximizeWindow();
        embed.open();
        embed.switchToIframe();
        Thread.sleep(2000);
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(driver.findElements(By.tagName("select")).get(0))).click();
        WebElement selectOne = driver.findElements(By.tagName("select")).get(0);
        selectOne.sendKeys("1");
        WebElement nextButton = driver.findElement(By.xpath("//*[text()='Next']"));
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(nextButton)).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[text()=' Sign In with Facebook ']")).click();
        Thread.sleep(2000);
        String parentWindow = driver.getWindowHandle();
        for(String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        driver.findElement(By.name("email")).sendKeys("javageca@gmail.com");
        driver.findElement(By.name("pass")).sendKeys("Vardar13Negotino");
        driver.findElement(By.name("login")).click();
        driver.switchTo().window(parentWindow);
        embed.switchToIframe();
        embed.waitForLoadByCssClass("discount");
        driver.findElement(By.className("discount")).sendKeys("DOUBLE");
        driver.findElement(By.xpath("//*[text()='Apply']")).click();
        Thread.sleep(500);
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()='Next']")))).click();
        WebElement nextBtn2 = driver.findElement(By.className("next-btn"));
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(nextBtn2)).click();
        WebElement wallet = driver.findElement(By.xpath("//*[text()=' Pay with wallet ']"));
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(wallet)).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[text()='Pay with wallet']")).click();
        Thread.sleep(5000);
        driver.findElement(By.className("ticket-title")).isDisplayed();
        System.out.println(driver.findElement(By.className("ticket-title")).getText());
        driver.quit();
    }

    @Test
    public void validationErrorReturnedWhenLoginFieldsAreEmpty() throws InterruptedException {
        WebDriver driver = getDriver();
        DirectEmbedTicketingOnlyPage embed = new DirectEmbedTicketingOnlyPage(driver);
        embed.maximizeWindow();
        embed.open();
        Thread.sleep(2000);
        embed.switchToIframe();
        driver.findElement(By.className("login-link")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[text()='Login Now']")).click();
        Thread.sleep(1000);
        WebElement toast = driver.findElement(By.id("toast-container"));
        WebElement total = toast.findElement(By.xpath("./child::*"));
        String title = total.findElements(By.xpath("./child::*")).get(0).getText();
        String expectedTitle = "Error";
        String message = total.findElements(By.xpath("./child::*")).get(1).getText();
        String expectedMessage = "Please enter valid Email ID";
        Assertions.assertEquals(title, expectedTitle);
        Assertions.assertEquals(message, expectedMessage);
    }

    @Test
    public void validationErrorReturnedWhenPasswordIsIncorrect() throws InterruptedException {
        WebDriver driver = getDriver();
        DirectEmbedTicketingOnlyPage embed = new DirectEmbedTicketingOnlyPage(driver);
        embed.maximizeWindow();
        embed.open();
        Thread.sleep(2000);
        embed.switchToIframe();
        driver.findElement(By.className("login-link")).click();
        Thread.sleep(1000);
        WebElement emailInput = driver.findElements(By.tagName("input")).get(0);
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(emailInput)).sendKeys("parma15@parma.it");
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(driver.findElements(By.tagName("input")).get(1))).sendKeys("Pero12345");
        driver.findElement(By.xpath("//*[text()='Login Now']")).click();
        Thread.sleep(1000);
        WebElement toast = driver.findElement(By.id("toast-container"));
        WebElement total = toast.findElement(By.xpath("./child::*"));
        String title = total.findElements(By.xpath("./child::*")).get(0).getText();
        String expectedTitle = "Error";
        String message = total.findElements(By.xpath("./child::*")).get(1).getText();
        String expectedMessage = "Email/Password not match";
        Assertions.assertEquals(title, expectedTitle);
        Assertions.assertEquals(message, expectedMessage);
        driver.quit();
    }

    @Test
    public void forgotPasswordWhenProvidedEmailDoesNotExistInDatabaseErrorMessage() throws InterruptedException {

        WebDriver driver = getDriver();
        DirectEmbedTicketingOnlyPage embed = new DirectEmbedTicketingOnlyPage(driver);
        embed.maximizeWindow();
        embed.open();
        Thread.sleep(2000);
        embed.switchToIframe();
        driver.findElement(By.className("login-link")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[text()='Forgot Password?']")).click();
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(driver.findElements(By.tagName("input")).get(0))).sendKeys("Perop12345@oper.mk");
        driver.findElement(By.xpath("//*[text()='Submit']")).click();
        Thread.sleep(1000);
        WebElement toast = driver.findElement(By.id("toast-container"));
        WebElement total = toast.findElement(By.xpath("./child::*"));
        String title = total.findElements(By.xpath("./child::*")).get(0).getText();
        String expectedTitle = "Failed";
        String message = total.findElements(By.xpath("./child::*")).get(1).getText();
        String expectedMessage = "Email not found";
        Assertions.assertEquals(title, expectedTitle);
        Assertions.assertEquals(message, expectedMessage);
        driver.quit();
    }

    @Test
    public void loginAndReceiveSuccessMessage() throws InterruptedException {

        WebDriver driver = getDriver();
        DirectEmbedTicketingOnlyPage embed = new DirectEmbedTicketingOnlyPage(driver);
        embed.maximizeWindow();
        embed.open();
        Thread.sleep(2000);
        embed.switchToIframe();
        driver.findElement(By.className("login-link")).click();
        Thread.sleep(1000);
        WebElement emailInput = driver.findElements(By.tagName("input")).get(0);
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(emailInput)).sendKeys("parma15@parma.it");
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(driver.findElements(By.tagName("input")).get(1))).sendKeys("Pero1234");
        driver.findElement(By.xpath("//*[text()='Login Now']")).click();
        Thread.sleep(1000);
        WebElement toast = driver.findElement(By.id("toast-container"));
        WebElement total = toast.findElement(By.xpath("./child::*"));
        String title = total.findElements(By.xpath("./child::*")).get(0).getText();
        String expectedTitle = "Success";
        String message = total.findElements(By.xpath("./child::*")).get(1).getText();
        String expectedMessage = "Successfully logged in";
        Assertions.assertEquals(title, expectedTitle);
        Assertions.assertEquals(message, expectedMessage);
        driver.quit();
    }

    @Test
    public void forgotPasswordWhenProvidedEmailExistsInDatabase() throws InterruptedException {

        WebDriver driver = getDriver();
        DirectEmbedTicketingOnlyPage embed = new DirectEmbedTicketingOnlyPage(driver);
        embed.maximizeWindow();
        embed.open();
        Thread.sleep(2000);
        embed.switchToIframe();
        driver.findElement(By.className("login-link")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[text()='Forgot Password?']")).click();
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(driver.findElements(By.tagName("input")).get(0))).sendKeys("Parma5@parma.it");
        driver.findElement(By.xpath("//*[text()='Submit']")).click();
        Thread.sleep(1000);
        WebElement toast = driver.findElement(By.id("toast-container"));
        WebElement total = toast.findElement(By.xpath("./child::*"));
        String title = total.findElements(By.xpath("./child::*")).get(0).getText();
        String expectedTitle = "Success";
        String message = total.findElements(By.xpath("./child::*")).get(1).getText();
        String expectedMessage = "Reset link sent on email address";
        Assertions.assertEquals(title, expectedTitle);
        Assertions.assertEquals(message, expectedMessage);
        driver.quit();
    }

    @Test
    public void selectTwoTicketsAndCheckSubtotal() throws InterruptedException {

        WebDriver driver = getDriver();
        DirectEmbedTicketingOnlyPage embed = new DirectEmbedTicketingOnlyPage(driver);
        embed.maximizeWindow();
        embed.open();
        Thread.sleep(2000);
        embed.switchToIframe();
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(driver.findElements(By.tagName("select")).get(0)));

        WebElement selectOne = driver.findElements(By.tagName("select")).get(0);
        Select firstTicket = new Select(selectOne);
        selectOne.sendKeys("1");

        WebElement selectTwo = driver.findElements(By.tagName("select")).get(1);
        Select secondTicket = new Select(selectTwo);
        selectTwo.sendKeys("1");

        Thread.sleep(1500);
        String ticketOne = driver.findElements(By.className("ticket-price")).get(0).getText();
        String ticketTwo = driver.findElements(By.className("ticket-price")).get(1).getText();

        Double ticketOnePrice = Double.parseDouble(ticketOne.substring(2,4));
        Double ticketTwoPrice = Double.parseDouble(ticketTwo.substring(2,3));

        WebElement subtotal = driver.findElement(By.className("mini-total"));
        String subTotal = subtotal.findElements(By.xpath("./child::*")).get(1).getText();
        Double statedSubtotal = Double.parseDouble(subTotal.substring(1,5));
        Double ticketsTotal = ticketOnePrice + ticketTwoPrice;
        Assertions.assertEquals(ticketsTotal, statedSubtotal);
        driver.quit();
    }

    @Test
    public void makePaymentWithNewCardOnEmbeddedComponent() throws InterruptedException {
        WebDriver driver = getDriver();
        DirectEmbedTicketingOnlyPage embed = new DirectEmbedTicketingOnlyPage(driver);
        embed.maximizeWindow();
        embed.open();
        Thread.sleep(2000);
        embed.switchToIframe();
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(driver.findElements(By.tagName("select")).get(0)));

        WebElement selectOne = driver.findElements(By.tagName("select")).get(0);
        Select firstTicket = new Select(selectOne);
        selectOne.sendKeys("1");
        WebElement nextButton = driver.findElement(By.xpath("//*[text()='Next']"));
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(nextButton)).click();
        WebElement emailInput = driver.findElements(By.tagName("input")).get(0);
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(emailInput)).sendKeys("parma15@parma.it");
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(driver.findElements(By.tagName("input")).get(1))).sendKeys("Pero1234");
        driver.findElement(By.xpath("//*[text()='Login Now']")).click();
        embed.waitForLoadByCssClass("discount");
        driver.findElement(By.className("discount")).sendKeys("TRIPLE");
        driver.findElement(By.xpath("//*[text()='Apply']")).click();
        Thread.sleep(500);
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.className("next-btn")))).click();
        WebElement nextBtn2 = driver.findElement(By.className("next-btn"));
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(nextBtn2)).click();
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()='Pay with New Card']")))).click();
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("name")))).sendKeys("Marjan Geca");
        driver.findElement(By.xpath("//input[@type='tel']")).sendKeys("4111111111111111");
        driver.findElements(By.tagName("input")).get(3).sendKeys("900");
        driver.findElements(By.tagName("input")).get(4).sendKeys("Main Street 1 Minneapolis");
        driver.findElements(By.tagName("input")).get(5).sendKeys("10001");


        List<WebElement> selects = driver.findElements(By.tagName("select"));
        System.out.println("Sel " + selects.size());

        WebElement selectMonth= driver.findElements(By.tagName("select")).get(3);
        Select month = new Select(selectMonth);
        month.selectByVisibleText("10");

        WebElement selectYear = driver.findElements(By.tagName("select")).get(4);
        Select year = new Select(selectYear);
        year.selectByVisibleText("2028");


        WebElement selectState = driver.findElements(By.tagName("select")).get(6);
        Select state = new Select(selectState);
        state.selectByVisibleText("Minnesota");

        driver.findElement(By.xpath("//input[@type='checkbox']")).click();
        driver.findElement(By.xpath("//*[text()='Pay with card']")).click();
        Thread.sleep(5000); // Da se proveri
        driver.findElement(By.className("ticket-title")).isDisplayed();
        driver.quit();
    }

    @Test
    public void checkTaxesAndFeesTooltip() throws InterruptedException {

        WebDriver driver = getDriver();
        DirectEmbedTicketingOnlyPage embed = new DirectEmbedTicketingOnlyPage(driver);
        embed.maximizeWindow();
        embed.open();
        Thread.sleep(2000);
        embed.switchToIframe();
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(driver.findElements(By.tagName("select")).get(0)));

        WebElement selectOne = driver.findElements(By.tagName("select")).get(0);
        selectOne.sendKeys("1");

        List<WebElement> elements = driver.findElements(By.className("ml-3"));
        System.out.println("le " + elements.size());

    }

    @Test
    public void openInbox() throws InterruptedException {
        WebDriver driver = getDriver();
        driver.get("https://mail.dev.uppedevents.com/");
        Thread.sleep(5000);
        driver.switchTo().alert();

    }


}
