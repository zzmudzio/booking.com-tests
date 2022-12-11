package pl.zzmudzio.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BookingMainPage {
    private final WebDriver driver;
    private final WebDriverWait driverWait;

    @FindBy(xpath = "//span[@class='bui-button__text']/span[1]")
    private WebElement currencyButtonLocator;
    @FindBy(xpath = "//div[@class='bui-inline-container__main' and contains(text(), 'polski')]")
    private WebElement currencyPolishZlotyLocator;
    @FindBy(xpath = "//div[@class='bui-inline-container__main' and contains(text(), 'Euro')]")
    private WebElement currencyEurLocator;
    @FindBy(xpath = "//button[@id='onetrust-accept-btn-handler']")
    private WebElement acceptCookiesButtonLocator;
    @FindBy(xpath = "//button[@data-modal-id='language-selection']")
    private WebElement languageButtonLocator;
    @FindBy(xpath = "//div[@lang='en-gb']")
    private WebElement britishEnglishLanguageButtonLocator;
    @FindBy(xpath = "//div[@lang='pl']")
    private WebElement polishLanguageButtonLocator;
    @FindBy(tagName = "html")
    private WebElement htmlTagLocator;
    @FindBy(xpath = "//div[@data-testid=\"destination-container\"]//input[1]")
    private WebElement destinationLocator;
    @FindBy(xpath = "//input[@id='ss']")
    private WebElement alternativeDestinationLocator;
    @FindBy(xpath = "//button[@data-testid=\"date-display-field-start\"]")
    private WebElement checkInAndOutDateLocator;
    @FindBy(xpath = "//button[@data-testid=\"date-display-field-start\"]")
    private WebElement checkInStartDateButtonLocator;

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

    public String getActualCurrency() {
        return currencyButtonLocator.getText().trim();
    }

    public void changeCurrencyTo(String currency) {
        currencyButtonLocator.click();
        switch (currency) {
            case "EUR":
                try {
                    driverWait.until(ExpectedConditions.elementToBeClickable(currencyEurLocator));
                    currencyEurLocator.click();
                    break;
                } catch (TimeoutException ignored) {
                }
            case "PLN":
                try {
                    driverWait.until(ExpectedConditions.elementToBeClickable(currencyPolishZlotyLocator));
                    currencyPolishZlotyLocator.click();
                    break;
                } catch (TimeoutException ignored) {
                }
        }
    }

    public String changePageLanguageTo(String desiredLanguage) {
        languageButtonLocator.click();
        /* polish is my native language so the website is in polish by default */
        switch (desiredLanguage) {
            case "english":
                try {
                    driverWait.until(ExpectedConditions.elementToBeClickable(britishEnglishLanguageButtonLocator));
                    britishEnglishLanguageButtonLocator.click();
                } catch (TimeoutException ignored) {
                }
                break;
            case "polish":
                try {
                    driverWait.until(ExpectedConditions.elementToBeClickable(polishLanguageButtonLocator));
                    polishLanguageButtonLocator.click();
                } catch (TimeoutException ignored) {
                }
                break;
        }
        return htmlTagLocator.getAttribute("lang");
    }

    public void fillDestinationField(String destinationName) { // the locator seems to change randomly
        try {
            driverWait.until(ExpectedConditions.elementToBeClickable(destinationLocator));
            destinationLocator.click();
            destinationLocator.sendKeys(destinationName);
        } catch (TimeoutException te) {
            try {
                driverWait.until(ExpectedConditions.elementToBeClickable(alternativeDestinationLocator));
                alternativeDestinationLocator.click();
                alternativeDestinationLocator.sendKeys(destinationName);
            } catch (TimeoutException ignored) {
            }
        }
    }

    public String fillCheckInPeriod() {
        /*
           I come to this field always after filling up a destination city field, so there is no need for
           using e.g. XPath localization, Tab key press is enough. I am performing a bunch of actions, just for
           being more acquainted with this kind of operations.
         */
        try {
            driverWait.until(ExpectedConditions.elementToBeClickable(checkInStartDateButtonLocator));
            Actions actions = new Actions(driver);
            actions.keyDown(Keys.TAB)
                    .keyUp(Keys.TAB)
                    .keyDown(Keys.ARROW_DOWN) // to choose current month
                    .keyUp(Keys.ARROW_DOWN)
                    .keyDown(Keys.TAB)
                    .keyUp(Keys.TAB)
                    .keyDown(Keys.TAB)
                    .keyUp(Keys.TAB)
                    .keyDown(Keys.TAB)
                    .keyUp(Keys.TAB)
                    .perform();
            return checkInStartDateButtonLocator.getText();
        }
        catch(TimeoutException te) {
            return null;
        }
    }

}
