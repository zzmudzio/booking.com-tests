package pl.zmudzio.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pl.zzmudzio.pages.BookingMainPage;

public class BookingMainPageTest {

    private BookingMainPage bookingMainPage;
    private WebDriver driver;
    private final String CHROMEDRIVER_PATH = System.getenv("CHROMEDRIVER_PATH"); /* this env. variable should
     be set before running the tests, it stores absolute path to chromedriver.exe file */

    @BeforeClass
    public void initializeChromeDriver() {
        System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_PATH);
        driver = new ChromeDriver();
        bookingMainPage = new BookingMainPage(driver);
    }

    @Test(priority = 0)
    public void testMainPageOpeningProperly() {
        Assert.assertTrue(bookingMainPage.getPageTitle().contains("Booking.com"));
        if (!bookingMainPage.acceptCookies()) {
            System.out.println("Cookies preferences window didn\'t appear.");
        } else {
            System.out.println("Cookies window appeared and was accepted.");
        }
    }

    @Test(priority = 1)
    public void testCurrencySwitching() {
        if (!(bookingMainPage.getActualCurrency().equals("PLN"))) {
            bookingMainPage.changeCurrencyTo("PLN");
            Assert.assertEquals(bookingMainPage.getActualCurrency(), "PLN");
        } else {
            bookingMainPage.changeCurrencyTo("EUR");
            Assert.assertEquals(bookingMainPage.getActualCurrency(), "EUR");
        }
    }

    @Test(priority = 2)
    public void testLanguageSwitching() {
        Assert.assertEquals(bookingMainPage.changePageLanguageTo("english"), "en-gb");
        Assert.assertEquals(bookingMainPage.changePageLanguageTo("polish"), "pl");
    }

    @AfterClass
    public void quitDriver() {
        driver.quit();
    }
}
