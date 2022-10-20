package microsites.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class EventInformationPage {

    private final WebDriver driver;

    public EventInformationPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement modalTitleIsDisplayed(){
        WebElement title = driver.findElement(By.className("information-title"));
        return title;
    }
    public WebElement buyTicketsButton(){
        return driver.findElement(By.xpath("//*[text()=' Buy Tickets ']"));
    }
    public Boolean buyTicketsButtonIsDisplayed(){
        return buyTicketsButton().isDisplayed();
    }
    public void clickBuyTicketsButton(){
        buyTicketsButton().click();
    }
    public WebElement addToWishlistButton(){
        return driver.findElement(By.xpath("//*[text()=' Add to Wishlist ']"));
    }
    public Boolean addToWishlistButtonIsDisplayed(){
        return addToWishlistButton().isDisplayed();
    }
    public void clickAddToWishlistButton(){
        addToWishlistButton().click();
    }
    public WebElement detailsTab(){
        return driver.findElement(By.xpath("//*[text()=' Details ']"));
    }
    public Boolean detailsTabIsDisplayed(){
        return detailsTab().isDisplayed();
    }
    public void clickDetailsTab(){
        detailsTab().click();
    }
    public WebElement eventDescriptionTab(){
        return driver.findElement(By.xpath("//*[text()='Event Description']"));
    }
    public Boolean eventDescriptionTabIsDisplayed(){
        return eventDescriptionTab().isDisplayed();
    }
    public WebElement getEventName(){
        return driver.findElement(By.className("event-title"));
    }
    public String getEventNameInTheEventDescription(){
        return getEventName().getText();
    }
    public Boolean eventNameIsDisplayed(){
        return getEventName().isDisplayed();
    }
    public WebElement getEventDescription(){
        return driver.findElement(By.className("S"));
    }
    public String getEventDescriptionText(){
        return getEventDescription().getText();
    }
    public Boolean eventDescriptionIsDisplayed(){
        return getEventDescription().isDisplayed();
    }
    public Boolean detailsTabIsSelected(){
        WebElement selectedTab = detailsTab();
        String classes = selectedTab.getAttribute("class");
        for (String clas : classes.split(" ")) {
            if (clas.equals("active")) {
                return true;
            }
        }
        return false;
    }
    public WebElement photosTab(){
        return driver.findElement(By.xpath("//*[text()=' Photos ']"));
    }
    public Boolean photosTabIsDisplayed(){
        return photosTab().isDisplayed();
    }
    public void clickPhotosTab(){
        photosTab().click();
    }
    public WebElement photosInPhotosTab(){
        return driver.findElement(By.className("pictures-row"));
    }
    public Boolean photosAreDisplayed(){
        return photosInPhotosTab().isDisplayed();
    }
    public Integer getPhotosNumber(){
        return driver.findElements(By.className("photo")).size();
    }
    public void clickImageToOpenGallery(Integer index){
        driver.findElements(By.className("photo")).get(index).click();
    }
    public WebElement photoGallery(){
        return driver.findElement(By.tagName("app-event-gallery"));
    }
    public Boolean photoGalleryIsDisplayed(){
        return photoGallery().isDisplayed();
    }
    public WebElement backToGalleryButton(){
        return driver.findElement(By.xpath("//*[text()=' Back to Gallery ']"));
    }
    public Boolean backToGalleryButtonIsDisplayed(){
        return backToGalleryButton().isDisplayed();
    }
    public void clickBackToGalleryButton(){
        backToGalleryButton().click();
    }
    public WebElement leftButton(){
        return driver.findElement(By.className("fa-chevron-left"));
    }
    public Boolean leftButtonIsDisplayed(){
        return leftButton().isDisplayed();
    }
    public void clickLeftButton(){
        leftButton().click();
    }
    public WebElement rightButton(){
        return driver.findElement(By.className("fa-chevron-left"));
    }
    public Boolean rightButtonIsDisplayed(){
        return rightButton().isDisplayed();
    }
    public void clickRightButton(){
        rightButton().click();
    }
    public WebElement imageSlider(){
        return driver.findElement(By.className("images-sliding-container"));
    }
    public Boolean imageSliderIsDisplayed(){
        return imageSlider().isDisplayed();
    }
    public void clickImageInSlider(Integer index){
        WebElement slider = imageSlider();
        slider.findElements(By.xpath("./child::*")).get(index).click();
    }
    public WebElement getMainImageInGallery(){
      return driver.findElement(By.className("main-img"));
    }
    public Boolean mainImageIsDisplayed(){
       return getMainImageInGallery().isDisplayed();
    }
    public Boolean photosTabIsSelected(){
        WebElement selectedTab = photosTab();
        String classes = selectedTab.getAttribute("class");
        for (String clas : classes.split(" ")) {
            if (clas.equals("active")) {
                return true;
            }
        }
        return false;
    }
    public WebElement shopsTab(){
        return driver.findElement(By.xpath("//*[text()=' Shops ']"));
    }
    public Boolean shopsTabIsDisplayed(){
        return shopsTab().isDisplayed();
    }
    public void clickShopsTab(){
        shopsTab().click();
    }
    public WebElement shopsOverviewTab(){
        return driver.findElement(By.xpath("//*[text()='Overview']"));
    }
    public String shopsOverviewTabText(){
        return driver.findElement(By.xpath("//*[text()='Overview']")).getText();
    }
    public Boolean overviewTabIsDisplayed(){
        return shopsOverviewTab().isDisplayed();
    }
    public List<WebElement> getShopList(){
        WebElement container = driver.findElement(By.className("shops-container"));
        WebElement list = container.findElements(By.xpath("./child::*")).get(2);
        return list.findElements(By.xpath("./child::*"));
    }
    public List<String> allShopsNames(){
        List<String> names = new ArrayList<>();
        List<WebElement> list = getShopList();
        for(WebElement element: list){
            names.add(element.getText());
        }
        return names;
    }
    public String singleShopName(Integer index) {
        return getShopList().get(index).getText();
    }
    public Boolean shopsTabIsSelected(){
        WebElement selectedTab = shopsTab();
        String classes = selectedTab.getAttribute("class");
        for (String clas : classes.split(" ")) {
            if (clas.equals("active")) {
                return true;
            }
        }
        return false;
    }
    public WebElement locationTab(){
        return driver.findElement(By.xpath("//*[text()=' Location ']"));
    }
    public Boolean locationTabIsDisplayed(){
        return locationTab().isDisplayed();
    }
    public void clickLocationTab(){
        locationTab().click();
    }
    public WebElement openInMapsButton(){
        return driver.findElement(By.className("maps-btn"));
    }
    public Boolean openInMapsButtonIsVisible(){
        return openInMapsButton().isDisplayed();
    }
    public void clickOpenInMapsButton(){
        openInMapsButton().click();
    }
    public WebElement getLocation(){
        return driver.findElement(By.className("location"));
    }
    public String getStreetName(){
        WebElement locationContainer = getLocation();
        return locationContainer.findElements(By.xpath("./child::*")).get(0).getText();
    }
    public String getLocationCity(){
        WebElement locationContainer = getLocation();
        String full = locationContainer.findElements(By.xpath("./child::*")).get(0).getText();
        return full.split(" ")[0];
    }
    public String getLocationState(){
        WebElement locationContainer = getLocation();
        String full = locationContainer.findElements(By.xpath("./child::*")).get(0).getText();
        return full.split(" ")[1];
    }
    public String getLocationZip(){
        WebElement locationContainer = getLocation();
        String full = locationContainer.findElements(By.xpath("./child::*")).get(0).getText();
        return full.split(" ")[2];
    }
    public Boolean locationsTabIsSelected(){
        WebElement selectedTab = locationTab();
        String classes = selectedTab.getAttribute("class");
        for (String clas : classes.split(" ")) {
            if (clas.equals("active")) {
                return true;
            }
        }
        return false;
    }
    public Boolean googleMapIsDisplayed(){
        return driver.findElement(By.tagName("google-map")).isDisplayed();
    }

}
