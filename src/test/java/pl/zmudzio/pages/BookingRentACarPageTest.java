package pl.zmudzio.pages;

import com.opencsv.exceptions.CsvException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pl.zzmudzio.pages.BookingMainPage;
import pl.zzmudzio.pages.BookingRentACarPage;
import pl.zzmudzio.pages.Pages;
import pl.zzmudzio.tools.CsvFile;

import java.io.IOException;
import java.util.List;

public class BookingRentACarPageTest {
    private BookingRentACarPage bookingRentACarPage;
    private WebDriver driver;
    private final String CHROMEDRIVER_PATH = System.getenv("CHROMEDRIVER_PATH");

    @BeforeClass
    public void initializeChromeDriver() {
        System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_PATH);
        driver = new ChromeDriver();
        bookingRentACarPage = new BookingRentACarPage(driver);
        Pages.goToPageAndMaximize(Pages.RENT_A_CAR_PAGE.getPageAddress(), driver);
    }

    @Test(priority = 0)
    public void testFillingUpCarPickUpLocation() {
        try {
            List<String[]> testDataAttributeAndValue = CsvFile.readTestDataValue("DestinationCity");
            BookingMainPage bookingMainPage = new BookingMainPage(driver);
            bookingMainPage.acceptCookies();
            bookingRentACarPage.fillPickUpPlace(testDataAttributeAndValue.get(0)[1]);
        } catch (CsvException | IOException ce) {
            System.out.println("Error during csv reading.");
        }
    }

    @AfterClass
    public void quitDriver() {
        this.driver.quit();
    }

}
