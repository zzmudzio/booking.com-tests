package pl.zmudzio.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pl.zzmudzio.pages.BookingMainPage;

public class BookingMainPageTest {
    private WebDriver driver;
    private final String CHROMEDRIVER_PATH = System.getenv("CHROMEDRIVER_PATH"); /* this env. variable should
     be set before running the tests, it stores absolute path to chromedriver.exe file */

    @BeforeClass
    public void initializeChromeDriver() {
        System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_PATH);
        driver = new ChromeDriver();
    }

    @Test(priority = 0)
    public void testMainPageOpeningProperly() {
        BookingMainPage bookingMainPage = new BookingMainPage(driver);
        Assert.assertTrue(bookingMainPage.getPageTitle().contains("Booking.com"));
        if (!bookingMainPage.acceptCookies()) {
            System.out.println("Cookies preferences window didn\'t appear.");
        } else {
            System.out.println("Cookies has been thoroughly accepted.");
        }
    }

    @AfterClass
    public void quitDriver() {
        driver.quit();
    }

}
