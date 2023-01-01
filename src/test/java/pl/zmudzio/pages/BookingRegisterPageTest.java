package pl.zmudzio.pages;

import com.opencsv.exceptions.CsvException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pl.zzmudzio.pages.BookingMainPage;
import pl.zzmudzio.pages.BookingRegisterPage;
import pl.zzmudzio.pages.Pages;
import pl.zzmudzio.tools.CsvFile;

import java.io.IOException;
import java.util.List;

public class BookingRegisterPageTest {
    private BookingRegisterPage bookingRegisterPage;
    private WebDriver driver;
    private final String CHROMEDRIVER_PATH = System.getenv("CHROMEDRIVER_PATH");

    @BeforeClass
    public void initializeChromeDriver() {
        System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_PATH);
        driver = new ChromeDriver();
        bookingRegisterPage = new BookingRegisterPage(driver);
        Pages.goToPageAndMaximize(Pages.REGISTER_PAGE.getPageAddress(), driver);
    }

    @Test(priority = 0)
    public void testRegisterPageOpeningProperly() {
        BookingMainPage bookingMainPage = new BookingMainPage(driver);
        bookingMainPage.acceptCookies();
    }

    @Test(priority = 1)
    public void testFillMailFieldWithIncorrectData() {
        try {
            List<String[]> testDataAttributeAndValue = CsvFile.readTestDataValue("IncorrectEmail");
            Assert.assertTrue(bookingRegisterPage.fillEmailField(testDataAttributeAndValue.get(0)[1]));
        } catch (CsvException | IOException ce) {
            System.out.println("Error during csv reading.");
        }
    }

    @Test(priority = 2)
    public void testFillMailFieldWithCorrectData() {
        try {
            List<String[]> testDataAttributeAndValue = CsvFile.readTestDataValue("CorrectEmail");
            Assert.assertFalse(bookingRegisterPage.fillEmailField(testDataAttributeAndValue.get(0)[1]));
        } catch (CsvException | IOException ce) {
            System.out.println("Error during csv reading.");
        }
    }

    @AfterClass
    public void quitDriver() {
        this.driver.quit();
    }

}
