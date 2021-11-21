import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OurFirstTest {


    @Test
    public void openGoogle() {


        WebDriver driver = getDriver("firefox");
        driver.manage().window().maximize();
        driver.get("https://www.google.com");

        // przechodzimy do okna z plikami cookie:
        driver.switchTo().frame(0);

        // znalezienie przycisku:
        WebElement agreeButton = driver.findElement(By.xpath("//span[contains(text(),'Zgadzam')]"));

        // kliknięcie przycisku - akceptacja plików cookie:
        agreeButton.click();

        // powrót do pierwotnego okna:
        driver.switchTo().defaultContent();

        // znalezienie paska wyszukiwania:
        WebElement searchField = driver.findElement(By.name("q"));

        // wprowadzenie wartości wyszkiwania:
        searchField.sendKeys("Selenium");

        // zasymulowania naciśnięcie przycisku enter:
        searchField.sendKeys(Keys.ENTER);

        // weryfikacja wyników wyszukiwania:
        WebElement result = driver.findElement(By.xpath("//a[contains(@href,'selenium.dev')]//span"));

        // weryfikacja czy powyższy element został znaleziony
        Assert.assertTrue(result.isDisplayed());

    }

    public WebDriver getDriver(String browser) {
        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "C:\\Users\\Saturn\\Desktop\\szkolenie Ania\\selenium\\src\\main\\resources\\executables\\drivers\\chromedriver.exe");
                return new ChromeDriver();
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "C:\\Users\\Saturn\\Desktop\\szkolenie Ania\\selenium\\src\\main\\resources\\executables\\drivers\\geckodriver.exe");
                return new FirefoxDriver();
            case "ie":
                System.setProperty("webdriver.ie.driver", "C:\\Users\\Saturn\\Desktop\\szkolenie Ania\\selenium\\src\\main\\resources\\executables\\drivers\\IEDriverServer.exe");
                return new InternetExplorerDriver();
            default:
                throw new InvalidArgumentException("Invalid browser name");


        }
    }
}