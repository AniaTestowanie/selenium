import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ImageTest {

    /*
    W tym teście będziemy chcieli sprawdzić, czy nasz obrazek poprawnie został załadowany
    Aby to zrobić, pobierzemy jego atrybut (np. jego wysokość). Jeżeli naturalna wysokość będzie większa od zera,
    to oznacza że prawdopodobnie wyświetla się poprawnie

    Sprawdzimy to na dwóch stronach - dla jednej w której obrazek został poprawnie załadowany i dla drugiej dla której nie
    został on załadowany (błędnie została określona nazwa pliku)
    */

    @Test
    public void imageTest1() {

        String driverPath = "C:\\Users\\Saturn\\Desktop\\szkolenie Ania\\selenium\\src\\main\\resources\\executables\\drivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);

        WebDriver driver = new ChromeDriver();
        driver.get("https://testeroprogramowania.github.io/selenium/basics.html");

        WebElement image = driver.findElement(By.tagName("img"));
        System.out.println(image.getAttribute("naturalHeight"));

        driver.close();

    }

    @Test
    public void imageTest2() {

        String driverPath = "C:\\Users\\Saturn\\Desktop\\szkolenie Ania\\selenium\\src\\main\\resources\\executables\\drivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);

        WebDriver driver = new ChromeDriver();
        driver.get("https://testeroprogramowania.github.io/selenium/image.html");

        WebElement image = driver.findElement(By.tagName("img"));
        System.out.println(image.getAttribute("naturalHeight"));

        /*
        Możemy też skorzystać z asercji, czyli z pewnego rodzaju automatycznego sprawdzenia określonego warunku,
        ponieważ docelowo nie będziemy chcieli weryfikować wyniku testu w konsoli.

        Do tego musimy posłużyć się klasą Assert, która posiada w sobie wiele metod.
        Dla naszego przypadku najlepsza będzie assert.Equals. W nawiasie podajemy wartość oczekiwaną i rzeczywistą.
        Możemy to zapisać zarówno w formie Stringów jak i intów

         */

        String height = image.getAttribute("naturalHeight");
        Assert.assertEquals(height,"0");

    }
}
