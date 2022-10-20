package microsites.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.summingDouble;

public class TicketingShared {

    private final WebDriver driver;

    public TicketingShared(WebDriver driver) {
        this.driver = driver;
    }

    public void hasHeaderTicketing(){
        driver.findElement(By.className("information-title")).getText();
    }
    public Boolean hasButtonBackToEventInfo(){
        return driver.findElement(By.xpath("//*[text()=' Back to Event Info ']")).isDisplayed();
    }
    public Boolean hasCloseButton(){
       return driver.findElement(By.className("close-btn")).isDisplayed();
    }
    public void clickCloseButton(){
        driver.findElement(By.className("close-btn")).click();
    }
    public Integer hasFourTabsIfLoggedIn(){
        WebElement listOfTabsWrapper = driver.findElement(By.id("pills-tab"));
        return listOfTabsWrapper.findElements(By.xpath("./child::*")).size();
    }
    public Integer hasFiveTabsIfNotLoggedIn(){
        WebElement listOfTabsWrapper = driver.findElement(By.id("pills-tab"));
        return listOfTabsWrapper.findElements(By.xpath("./child::*")).size();
    }
    public Boolean getTicketsTabName(){
        return driver.findElement(By.xpath("//*[text()='Tickets']")).isDisplayed();
    }
    public Boolean getExtrasTabName(){
        return driver.findElement(By.xpath("//*[text()='Extras']")).isDisplayed();
    }
    public Boolean getLoginTabName(){
        return driver.findElement(By.xpath("//*[text()='Login']")).isDisplayed();
    }
    public Boolean getPayTabName(){
        return driver.findElement(By.xpath("//*[text()='Pay']")).isDisplayed();
    }
    public Boolean getConfirmTabName(){
        return driver.findElement(By.xpath("//*[text()='Confirm']")).isDisplayed();
    }
    public Boolean tabHasActiveClass() {
        WebElement selectedTab = driver.findElement(By.xpath("//*[text()='Tickets']"));
        String classes = selectedTab.getAttribute("class");
        for (String clas : classes.split(" ")) {
            if (clas.equals("active")) {
                return true;
            }
        }
        return false;
    }
    public Boolean confirmSummarySectionNames(){
        WebElement subtotalElement = driver.findElements(By.className("mini-total")).get(0);
        String subtotal = subtotalElement.findElements(By.xpath("./child::*")).get(1).getText();

        WebElement taxes = driver.findElements(By.className("mini-total")).get(1);
        String taxesToSplit = taxes.findElements(By.xpath("./child::*")).get(1).getText();
        String tax = taxesToSplit.substring(0,5);

        WebElement fees = driver.findElements(By.className("mini-total")).get(2);
        String fee = fees.findElements(By.xpath("./child::*")).get(1).getText();

        WebElement total = driver.findElement(By.className("grand-total"));
        String grand = total.findElements(By.xpath("./child::*")).get(1).getText();

        if (subtotal.equals("Subtotal") && tax.equals("Taxes") && fee.equals("Fees") && grand.equals("Total Due")){
            return true;
        }else{
            return false;
        }
    }
    public Double summaryTotal(){
        Tickets tickets = new Tickets(driver);
        List<Double> amounts = new ArrayList<>();
        List<WebElement> elements = driver.findElements(By.className("mini-total"));
        for(WebElement element : elements){
          amounts.add(Double.parseDouble(element.findElements(By.xpath("./child::*")).get(2).getText().substring(1)));
         }
        return tickets.calculateTotalOfAmounts(amounts);
    }
    public Double getGrandTotal(){
        WebElement total = driver.findElement(By.className("grand-total"));
        return Double.parseDouble(total.findElements(By.xpath("./child::*")).get(2).getText().substring(1));
    }
    public Boolean confirmSummaryEqualsGrandTotal(){
        Double summary = summaryTotal();
        Double grandTotal = getGrandTotal();
        if(summary == grandTotal){
            return true;
        }else {
            return false;
        }
    }
    public Boolean confirmInformationIconNextToTaxesIsDisplayed(){
        WebElement taxes = driver.findElements(By.className("mini-total")).get(1);
        WebElement tax = taxes.findElements(By.xpath("./child::*")).get(1);
        return tax.findElements(By.xpath("./child::*")).get(0).isDisplayed();
    }
    public Boolean confirmInformationIconNextToFeesIsDisplayed(){
        WebElement fees = driver.findElements(By.className("mini-total")).get(2);
        WebElement fee = fees.findElements(By.xpath("./child::*")).get(1);
        return fee.findElements(By.xpath("./child::*")).get(0).isDisplayed();
    }
    public Boolean confirmTooltipIsDisplayedWhenMouseOverTaxesIcon(){
        WebElement feeIcon = driver.findElements(By.className("fa-info-circle")).get(0);
        Actions action = new Actions(driver);
        action.moveToElement(feeIcon).perform();
        return driver.findElement(By.id("ngb-tooltip-6")).isDisplayed();
    }
    public List<String> hoverOverTaxIconAndGetStringsOfPriceValuesFromTooltip(){
        List<String> prices = new ArrayList<>();
        WebElement feeIcon = driver.findElements(By.className("fa-info-circle")).get(0);
        Actions action = new Actions(driver);
        action.moveToElement(feeIcon).perform();
        List<WebElement> elements = driver.findElements(By.className("ml-3"));
        for (WebElement element : elements){
            prices.add(element.getText());
        }
        return prices;
    }
    public List<String> hoverOverFeeIconAndGetStringsOfPriceValuesFromTooltip(){
        List<String> prices = new ArrayList<>();
        WebElement feeIcon = driver.findElements(By.className("fa-info-circle")).get(1);
        Actions action = new Actions(driver);
        action.moveToElement(feeIcon).perform();
        List<WebElement> elements = driver.findElements(By.className("ml-3"));
        for (WebElement element : elements){
            prices.add(element.getText());
        }
        return prices;
    }

    public String onStartValueIs$0_00(){
        return "$0.00";
    }
    public Boolean confirmStartingValuesOnFeesInTooltip(){
        List<String> prices = hoverOverFeeIconAndGetStringsOfPriceValuesFromTooltip();
        for (String string : prices){
            if(string.equals(onStartValueIs$0_00())){
                return true;
            }
        }
        return false;
    }
    public Boolean confirmStartingValuesOnTaxesInTooltip(){
        List<String> prices = hoverOverTaxIconAndGetStringsOfPriceValuesFromTooltip();
        for (String string : prices){
            if(string.equals(onStartValueIs$0_00())){
                return true;
            }
        }
        return false;
    }
    public Boolean confirmSummaryPriceValuesOnStartAre$0_00(){
        List<WebElement> summaryValues = driver.findElements(By.className("mini-total"));
        for(WebElement element : summaryValues){
           if (element.findElements(By.xpath("./child::*")).get(2).getText().equals(onStartValueIs$0_00())){
               return true;
           }
        }
        return false;
    }

    public Boolean confirmTooltipIsDisplayedWhenMouseOverFeesIcon(){
        WebElement feeIcon = driver.findElements(By.className("fa-info-circle")).get(1);
        Actions action = new Actions(driver);
        action.moveToElement(feeIcon).perform();
        return driver.findElement(By.id("ngb-tooltip-7")).isDisplayed();
    }
    public Boolean hasNextButton(){
        return driver.findElement(By.xpath("//*[text()=' Back to Event Info ']")).isDisplayed();
    }
    public void clickNextButton(){
        driver.findElement(By.xpath("//*[text()='Next']")).click();
    }
    public Boolean hasPreviousButton(){
        return driver.findElement(By.xpath("//*[text()=' Previous ']")).isDisplayed();
    }
    public void clickPreviousButton(){
        driver.findElement(By.xpath("//*[text()=' Previous ']")).click();
    }


}
