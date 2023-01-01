package pl.zzmudzio.pages;

import org.openqa.selenium.WebDriver;

public enum Pages {
    MAIN_PAGE("http://booking.com"),
    REGISTER_PAGE("https://account.booking.com/register");
    private final String pageAddress;

    private Pages(String pageAddress) {
        this.pageAddress = pageAddress;
    }

    public String getPageAddress() {
        return this.pageAddress;
    }
    public static void goToPageAndMaximize(String addressOfThePage, WebDriver driver) {
        driver.get(addressOfThePage);
        driver.manage().window().maximize();
    }
}
