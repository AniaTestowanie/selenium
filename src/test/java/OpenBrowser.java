import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.annotations.Test;

public class OpenBrowser {


    @Test
    public void openBrowser() {

        /*
        Zadaniem jest utworzenie nowej metody getDriver, która w zależności od parametru browser będzie nam uruchamiała konkretną przeglądarkę
        W przypadku gdy wpiszemy nieprawidłową wartość powinniśmy otrzymać wyjątek InvalidArgumentException o treści: Invalid browser name
         */


        WebDriver driver = getDriver("xxx");


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
