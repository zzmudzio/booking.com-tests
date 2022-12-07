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
    private WebElement htmlTag;


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

        return htmlTag.getAttribute("lang");
    }
}
