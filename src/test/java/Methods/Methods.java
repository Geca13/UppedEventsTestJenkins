package Methods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Methods {
    private final WebDriver driver;

    public Methods(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement findElementById(String id){
       return driver.findElement(By.id(id));
    }
    public List<WebElement> findElementsById(String id){
        return driver.findElements(By.id(id));
    }
    public WebElement findElementByCssClass(String cssClass){
       return driver.findElement(By.className(cssClass));
    }
    public List<WebElement> findElementsByCssClass(String cssClass){
        return driver.findElements(By.className(cssClass));
    }
    public WebElement findElementByName(String name){
       return driver.findElement(By.name(name));
    }
    public List<WebElement> findElementsByName(String name){
        return driver.findElements(By.name(name));
    }
    public WebElement findElementByTag(String tag){
       return driver.findElement(By.tagName(tag));
    }
    public List<WebElement> findElementsByTag(String tag){
        return driver.findElements(By.tagName(tag));
    }
    public WebElement findElementByXpathAndText(String text){
       return driver.findElement(By.xpath("//*[text()='"+text+"']"));
    }
    public List<WebElement> findElementsByXpathAndText(String text){
        return driver.findElements(By.xpath("//*[text()='"+text+"']"));
    }
    public WebElement findElementByXpathAndType(String tag, String type){
        return driver.findElement(By.xpath("//"+ tag +"[@type='"+type+"']"));
    }
    public List<WebElement> findElementsByXpathAndType(String tag, String type){
        return driver.findElements(By.xpath("//"+ tag +"[@type='"+type+"']"));
    }
    public Boolean elementIsDisplayed(WebElement element){
        return element.isDisplayed();
    }
    public Boolean elementIsNotDisplayed(WebElement element){
        return !element.isDisplayed();
    }
    public WebElement findChildOfElementByIndex(WebElement parent, Integer index){
        return parent.findElements(By.xpath("./child::*")).get(index);
    }
    public List<WebElement> findAllChildrenOfElement(WebElement parent){
        return parent.findElements(By.xpath("./child::*"));
    }
    public WebElement findParentOfAChildElement(WebElement child){
        return child.findElement(By.xpath("./.."));
    }

}
