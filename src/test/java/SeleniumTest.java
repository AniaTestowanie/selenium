import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SeleniumTest {

    @Test
    public void openGooglePage() {
        String driverPath = "C:\\Users\\Saturn\\Desktop\\szkolenie Ania\\selenium\\src\\main\\resources\\executables\\drivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://www.google.com");

        // przechodzimy do okienka z plikami cookies
//        driver.switchTo().frame(0);
        // znalezienie przycisku
 //       WebElement agreeButton = driver.findElement(By.id("L2AGLb"));
        WebElement agreeButton = driver.findElement(By.xpath("//div[contains(text(),'Zgadzam')]"));
        // kliknięcie przycisku
        agreeButton.click();
        // powrót do pierwotnego okna
//        driver.switchTo().defaultContent();
        // znajdź pole wyszukiwania
        WebElement searchField = driver.findElement(By.name("q"));
        // wprowadź wartość Selenium do pola
        searchField.sendKeys("Selenium");
        // zasymuluj naciśnięcie przycisku Enter
        searchField.sendKeys(Keys.ENTER);
        // znajdź rezultat
        WebElement result = driver.findElement(By.xpath("//a[contains(@href,'selenium.dev')]"));



        Assert.assertTrue(result.isDisplayed());

    }
}
