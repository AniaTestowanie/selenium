
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class KonfiguracjaPrzegladarki {


    @Test
    public void openGooglePage() {

        String driverPathChrome = "C:\\Users\\Saturn\\Desktop\\szkolenie Ania\\selenium\\src\\main\\resources\\executables\\drivers\\chromedriver.exe";
        String driverPathFirefox = "C:\\Users\\Saturn\\Desktop\\szkolenie Ania\\selenium\\src\\main\\resources\\executables\\drivers\\geckodriver.exe";
        String driverPathIE = "C:\\Users\\Saturn\\Desktop\\szkolenie Ania\\selenium\\src\\main\\resources\\executables\\drivers\\IEDriverServer.exe";
        System.setProperty("webdriver.chrome.driver", driverPathChrome);
        System.setProperty("webdriver.gecko.driver", driverPathFirefox);
        System.setProperty("webdriver.ie.driver", driverPathIE);

        /*
        Pierwszą poznaną możliwością konfiguracji naszego drivera, była ta przedstawiona w przypadku przeglądarki Internet Explorer, tj.:
        InternetExplorerOptions options = new InternetExplorerOptions();
        options.withInitialBrowserUrl("http://www.google.com");
        WebDriver driver = new InternetExplorerDriver(options);


        Dla różnych przeglądarek dostępne są różne metody!

        Na przykład dla Chrome możemy ustawić tryb headless. Jeśli będzie ustawiony na false, to test wykona się "normalnie".
        Jeśli będzie ustawiony na true, to całe wykonanie testu od strony interfejsu będzie dla nas niewidoczne, tj. nie zobaczymy otwartej przeglądarki
         */

        ChromeOptions options = new ChromeOptions();
        options.setHeadless(false);
        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
        WebDriver driver = new ChromeDriver(options);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("alert('Hello')"); // za pomocą tej linijki kodu dodamy alert (okienko z tekstem Hello) i przyciskiem OK - bez dodania wiersza:
        // options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT); - spowodowałoby nam fail testu
        // za pomocą powyższego przykładu mogliśmy pokazać, że możemy zmodyfikować driver do obsługi tego typu niespodziewanych alertów

        driver.get("http://www.google.com");

    }
}