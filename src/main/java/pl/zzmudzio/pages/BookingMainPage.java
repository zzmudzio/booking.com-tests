package pl.zzmudzio.pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BookingMainPage {
    private final WebDriver driver;
    private final WebDriverWait driverWait;

    @FindBy(xpath = "//span[@class='bui-button__text']")
    private WebElement currencyButtonLocator;
    @FindBy(xpath = "//span[contains(text(),'PLN')]")
    private WebElement currencyPolishZlotyLocator;
    @FindBy(xpath = "//span[contains(text(),'EUR')]")
    private WebElement currencyEurLocator;
    @FindBy(xpath = "//button[@id='onetrust-accept-btn-handler']")
    private WebElement acceptCookiesButtonLocator;


    public BookingMainPage(WebDriver driver) {
        this.driver = driver;
        this.driverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
        driver.get(Pages.MAIN_PAGE.getPageAddress());
        driver.manage().window().maximize();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public boolean acceptCookies() {
        try {
            driverWait.until(ExpectedConditions.elementToBeClickable(acceptCookiesButtonLocator));
            acceptCookiesButtonLocator.click();
            return true;
        } catch (TimeoutException te) {
            return false;
        }
    }

    public String changeCurrencyTo(String currency) {
        currencyButtonLocator.click();
        switch (currency) {
            case "EUR":
                currencyEurLocator.click();
                break;
            case "PLN":
                currencyPolishZlotyLocator.click();
                break;
            default:
                return "undefined";
        }
        return currencyButtonLocator.getText();
    }
}
