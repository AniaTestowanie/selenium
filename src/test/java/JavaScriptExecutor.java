import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class JavaScriptExecutor {

/*
JavaScriptExecutor umożliwi nam wykonanie kodu javaScript w naszej przeglądarce
(np. możemy wykorzystać ten kod do klikania na elementy, jeśli podstawowa metoda click() nam nie działa


 */

    @Test
    public void executeJavaScript() {

        String driverPath = "C:\\Users\\Saturn\\Desktop\\szkolenie Ania\\selenium\\src\\main\\resources\\executables\\drivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);

        WebDriver driver = new ChromeDriver();
        driver.get("https://testeroprogramowania.github.io/selenium/");

        WebElement basicPageLink = driver.findElement(By.linkText("Podstawowa strona testowa"));

        /*
        Zamiast klikać na link, skorzystamy z JavaScriptExecutora
         */

        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", basicPageLink);

        /*
        Wprowadzanie wartości
         */

        WebElement firstInput = driver.findElement(By.name("fname"));
        executor.executeScript("arguments[0].setAttribute('value','Ania')",firstInput);
    }

}
