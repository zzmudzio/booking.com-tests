package pl.zmudzio.pages;

import com.opencsv.exceptions.CsvException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pl.zzmudzio.pages.BookingMainPage;
import pl.zzmudzio.tools.CsvFile;
import pl.zzmudzio.tools.DateAndTime;
import pl.zzmudzio.tools.RegEx;

import java.io.IOException;
import java.util.List;

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

    @Test(priority = 3)
    public void testDestinationSearchField() {
        try {
            List<String[]> testDataAttributeAndValue = CsvFile.readTestDataValue("DestinationCity");
            if (testDataAttributeAndValue != null) {
                bookingMainPage.fillDestinationField(testDataAttributeAndValue.get(0)[1]);
            } /* TestData.csv consists of test data saved as Key,Par values, testDataAttributeAndValue returns
              a single-element list, where the only value is a two-element String array */
        } catch (IOException | CsvException exc) {
            System.out.println("An error has occurred during the csv file read!. Check for the DestinationCity " +
                    "value correctness.");
            exc.printStackTrace();
            Assert.fail();
        }
    }

    @Test(priority = 4)
    public void testCheckInPeriodFields() {
        Assert.assertEquals(RegEx.getDayOfMonthFromGivenString(bookingMainPage.fillCheckInPeriod()),
                DateAndTime.getCurrentDayOfMonth());
        /*
        The fillCheckInPeriod method returns a text retrieved from a check in start date locator - it is
        a String that consists of the day number, month name and day name, during the test
        execution I need only a day of the month, so I am using a regex method to get what I need.
        The check in start date should be the day that the test is being executed at, therefore to check whether
        the test has passed I only need to compare with current date(i.e. number of the day in a month).
         */
    }

    @Test(priority = 5)
    public void testSearchResult() {
        try {
            List<String[]> testDataAttributeAndValue = CsvFile.readTestDataValue("DestinationCity");
            if (testDataAttributeAndValue != null) {
                Assert.assertTrue(bookingMainPage.clickSearchButton()
                        .contains((testDataAttributeAndValue.get(0)[1]).trim()));
            }
        } catch (IOException | CsvException exc) {
            System.out.println("An error has occurred during the csv file read!. Check for the DestinationCity " +
                    "value correctness.");
            Assert.fail();
        }

    }

    @AfterClass
    public void quitDriver() {
        driver.quit();
    }
}
