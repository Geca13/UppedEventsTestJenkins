package microsites.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInModal {
    private final WebDriver driver;

    public SignInModal(WebDriver driver) {
        this.driver = driver;
    }
    public Boolean signInModalIsDisplayed(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.elementToBeClickable(getSignInButton()));
        return driver.findElement(By.tagName("app-log-in")).isDisplayed();
    }
    public Boolean titleNameIsDisplayed(){
        return driver.findElement(By.className("title")).isDisplayed();
    }
    public Boolean verifyModalTitle(){
        String title = "Sign In";
        if(driver.findElement(By.className("title")).equals(title)){
            return true;
        }
        return false;
    }
    public WebElement getSignInButton(){
       return driver.findElement(By.xpath("//button[@type='submit']"));
    }
    public void enterEmailIntoEmailInput(){
        driver.findElement(By.name("email")).sendKeys("parma15@parma.it");
    }
    public void enterPasswordIntoPasswordInput(){
        driver.findElement(By.name("password")).sendKeys("Pero1234");
    }
    public void clickSignInButtonToSignIn(){
       WebElement signIn = getSignInButton();
       signIn.click();
    }
    public WebElement googleSignInButton(){
        return driver.findElement(By.xpath("//*[text()='Sign In with Google']"));
    }
    public WebElement facebookSignInButton(){
        return driver.findElement(By.xpath("//*[text()='Sign In with Facebook']"));
    }
    public Boolean verifyFacebookButtonIsDisplayed(){
        return facebookSignInButton().isDisplayed();
    }
    public Boolean verifyGoogleButtonIsDisplayed(){
        return googleSignInButton().isDisplayed();
    }
    public void clickTheGoogleSignInButton(){
        googleSignInButton().click();
    }
    public void clickTheFacebookSignInButton(){
        facebookSignInButton().click();
    }
    public WebElement findCreateAccountLink(){
        return driver.findElement(By.xpath("//*[text()='Create an account']"));
    }
    public Boolean createAnAccountLinkIsDisplayed(){
        return findCreateAccountLink().isDisplayed();
    }
    public void clickCreateAccountLink(){
        findCreateAccountLink().click();
    }
    public WebElement getForgetPasswordLink(){
        return driver.findElement(By.xpath("//*[text()=' Forgot Password? ']"));
    }
    public Boolean getForgetPasswordLinkIsDisplayed(){
        return getForgetPasswordLink().isDisplayed();
    }
    public void clickForgetPasswordLink(){
        getForgetPasswordLink().click();
    }
    public WebElement rememberMeCheckbox(){
        return driver.findElement(By.name("rememberMe"));
    }
    public Boolean rememberMeCheckboxIsDisplayed(){
        return rememberMeCheckbox().isDisplayed();
    }
    public void clickRememberMeCheckbox(){
        rememberMeCheckbox().click();
    }

}
