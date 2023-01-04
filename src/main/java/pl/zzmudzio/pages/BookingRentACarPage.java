package pl.zzmudzio.pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BookingRentACarPage {
    private final WebDriver driver;
    private final WebDriverWait driverWait;

    public BookingRentACarPage(WebDriver driver) {
        this.driver = driver;
        this.driverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='ss_origin']")
    private WebElement carPickUpCityLocator;

    @FindBy(xpath = "//li[contains(@data-value, \"Chopin\")]")
    private WebElement carPickUpExactPlaceLocator;

    @FindBy(xpath = "//button[@data-sb-id=\"main\"]")
    private WebElement searchCarButtonLocator;

    public void fillPickUpCity(String cityName) {
        try {
            driverWait.until(ExpectedConditions.visibilityOf(carPickUpCityLocator));
            carPickUpCityLocator.sendKeys(cityName);
        } catch (TimeoutException te) {
            System.out.println("Error: elemenet has not been found.");
        }
    }

    public WebElement choosePickupLocation() {
        try {
            driverWait.until(ExpectedConditions.elementToBeClickable(carPickUpExactPlaceLocator));
            carPickUpExactPlaceLocator.click();
            return searchCarButtonLocator;
        } catch (TimeoutException te) {
            System.out.println("Error: element has not been found. ");
            return null;
        }
    }


}
