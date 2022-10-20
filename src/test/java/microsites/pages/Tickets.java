package microsites.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static java.util.stream.Collectors.summingDouble;

public class Tickets {

    private final WebDriver driver;

    public Tickets(WebDriver driver) {
        this.driver = driver;
    }
    public void hasTicketsHeaderChooseYourTickets(){
        driver.findElement(By.className("title")).getText();
    }
    public Integer numberOfTickets(){
        return driver.findElements(By.className("name")).size();
    }
    public String getTicketName(Integer index){
        return driver.findElements(By.className("name")).get(index).getText();
    }
    public String getTicketDescription(Integer index){
        return driver.findElements(By.className("info")).get(index).getText();
    }
    public Integer numberOfPrices(){
        return driver.findElements(By.className("ticket-price")).size();
    }
    public Boolean confirmPriceIsInBrackets(Integer index){
        String shownPrice = driver.findElements(By.className("ticket-price")).get(index).getText();
        Character first = shownPrice.charAt(0);
        Character last = shownPrice.charAt(shownPrice.length()-1);
        if (first.equals("(") && last.equals(")")){
            return true;
        }else {
            return false;
        }
    }
    public Boolean confirm$SignIsPresentBeforeThePrice(Integer index){
        String shownPrice = driver.findElements(By.className("ticket-price")).get(index).getText();
        Character dollar = shownPrice.charAt(1);
        if (dollar.equals("$") ){
            return true;
        }else {
            return false;
        }
    }
    public String getPriceAsString(Integer index){
        String shownPrice = driver.findElements(By.className("ticket-price")).get(index).getText();
        return shownPrice.substring(2, shownPrice.length()-1);
    }
    public Double convertPriceFromStringToDouble(Integer index){
        String toConvert = getPriceAsString(index);
        return Double.parseDouble(toConvert);
    }
    public Double calculateTotalOfAmounts(List<Double> amounts){
        return amounts.stream().collect(summingDouble(f -> f));
    }
    public Integer numberOfIncreaseQuantityButtons(){
        return driver.findElements(By.xpath("//*[text()=' + ']")).size();
    }

    public Integer numberOfDecreaseQuantityButtons(){
        return driver.findElements(By.xpath("//*[text()=' - ']")).size();
    }
    public Integer numberOfQuantityInputs(){
        return driver.findElements(By.xpath("//input[@type='number']")).size();
    }
    public void clickIncreaseQuantityButton(Integer index){
        driver.findElements(By.xpath("//*[text()=' + ']")).get(index).click();
    }
    public void clickDecreaseQuantityButton(Integer index){
        driver.findElements(By.xpath("//*[text()=' - ']")).get(index).click();
    }
    public void typeNumberIntoQuantityInput(Integer index, String quantity){
        driver.findElements(By.xpath("//input[@type='number']")).get(index).sendKeys(quantity);
    }
    public String getDiscountCodeFormLabel(){
        WebElement form = driver.findElement(By.className("form-group"));
        return form.findElements(By.xpath("./child::*")).get(0).getText();
    }
    public Boolean getDiscountCodeFormInput(){
        WebElement form = driver.findElement(By.className("form-group"));
        return form.findElements(By.xpath("./child::*")).get(1).isDisplayed();
    }
    public void enterPromoCodeInDiscountField(String promo){
        List<WebElement> inputs = driver.findElements(By.tagName("input"));
        driver.findElements(By.tagName("input")).get(inputs.size() - 1).sendKeys(promo);
    }
    public Boolean getDiscountCodeApplyButton(){
        return driver.findElement(By.xpath("//*[text()='Apply']")).isDisplayed();
    }
    public void clickDiscountApplyButton(){
        driver.findElement(By.xpath("//*[text()='Apply']")).click();
    }

}
