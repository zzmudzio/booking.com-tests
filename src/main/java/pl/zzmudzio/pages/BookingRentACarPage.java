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
    private WebElement carPickUpPlaceLocator;

    public void fillPickUpPlace(String cityName) {
        try {
            driverWait.until(ExpectedConditions.visibilityOf(carPickUpPlaceLocator));
            carPickUpPlaceLocator.sendKeys(cityName);
        } catch (TimeoutException te) {
            System.out.println("Error: elemenet has not been found.");
        }
    }

}
