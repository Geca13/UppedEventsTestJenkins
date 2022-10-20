package microsites.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateAccountModal {
    private final WebDriver driver;

    public CreateAccountModal(WebDriver driver) {
        this.driver = driver;
    }
    public Boolean createAccountModal(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.elementToBeClickable(signUpWithGoogle()));
      return driver.findElement(By.tagName("app-sign-up")).isDisplayed();
    }
    public WebElement signUpWithGoogle(){
        return driver.findElement(By.xpath("//*[text()='Sign Up with Google']"));
    }
    public Boolean signUpWithGoogleButtonIsDisplayed(){
       return signUpWithGoogle().isDisplayed();
    }
    public void clickSignUpWithGoogleButton(){
        signUpWithGoogle().click();
    }
    public WebElement signUpWithFacebook(){
        return driver.findElement(By.xpath("//*[text()='Sign Up with Facebook']"));
    }
    public Boolean signUpWithFacebookButtonIsDisplayed(){
        return signUpWithFacebook().isDisplayed();
    }
    public void clickSignUpWithFacebookButton(){
        signUpWithFacebook().click();
    }
    public WebElement signUpWithEmail(){
        return driver.findElement(By.xpath("//*[text()='Sign Up with Email']"));
    }
    public Boolean signUpWithEmailButtonIsDisplayed(){
        return signUpWithEmail().isDisplayed();
    }
    public void clickSignUpWithEmailButton(){
        signUpWithEmail().click();
    }
    public WebElement signInLink(){
        return driver.findElement(By.xpath("//*[text()='Sign In']"));
    }
    public Boolean signInLinkIsDisplayed(){
        return signInLink().isDisplayed();
    }
    public void clickSignInLink(){
        signInLink().click();
    }
}
