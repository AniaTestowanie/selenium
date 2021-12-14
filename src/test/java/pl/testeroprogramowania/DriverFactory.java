package pl.testeroprogramowania;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if(driver==null) {
            String driverPath = "C:\\Users\\Saturn\\Desktop\\szkolenie Ania\\selenium\\src\\main\\resources\\executables\\drivers\\chromedriver.exe";
            System.setProperty("webdriver.chrome.driver", driverPath);

            driver = new ChromeDriver();
        }
        return driver;
    }
}
