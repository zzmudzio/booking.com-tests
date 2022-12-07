package pl.zzmudzio.pages;

public enum Pages {
    MAIN_PAGE("http://booking.com");
    private final String pageAddress;

    private Pages(String pageAddress) {
        this.pageAddress = pageAddress;
    }

    public String getPageAddress() {
        return this.pageAddress;
    }
}
