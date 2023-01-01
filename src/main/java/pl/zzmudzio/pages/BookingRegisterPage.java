package pl.zzmudzio.pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BookingRegisterPage {
    private final WebDriver driver;
    private final WebDriverWait driverWait;

    @FindBy(xpath = "//input[@id='username']")
    private WebElement mailFieldLocator;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement proceedButtonLocator;

    @FindBy(xpath = "//div[@id='username-note']")
    private WebElement userNameNoteLocator;

    public BookingRegisterPage(WebDriver driver) {
        this.driver = driver;
        this.driverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    public boolean fillEmailField(String userMail) {
        try {
            driverWait.until(ExpectedConditions.visibilityOf(mailFieldLocator));
            try {
                Actions actions = new Actions(driver);
                actions
                        .moveToElement(mailFieldLocator)
                        .click()
                        .sendKeys(userMail)
                        .moveToElement(proceedButtonLocator)
                        .click()
                        .perform();
                driverWait.until(ExpectedConditions.visibilityOf(userNameNoteLocator));
            } catch (TimeoutException te) {
                return false;
            }
        } catch (TimeoutException te) {
            return false;
        }
        return true;
    }


}
