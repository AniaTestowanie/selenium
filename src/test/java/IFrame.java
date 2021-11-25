import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.Set;

public class IFrame {

    /*
    Znacznik iFrame to szczególny znacznik html, który umożliwia nam umieszczenie strony wewnątrz strony

    Możemy wykonywać różne akcje na elementach zagnieżdżonej strony, ale wówczas musimy użyć metody swichTo()
     */

    @Test
    public void iFrameTest() {

        String driverPath = "C:\\Users\\Saturn\\Desktop\\szkolenie Ania\\selenium\\src\\main\\resources\\executables\\drivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);

        WebDriver driver = new ChromeDriver();
        driver.get("https://testeroprogramowania.github.io/selenium/iframe.html");

        /*
        Aby przepiąć się do strony zagnieżdżonej, będziemy korzystać z metody:
        driver.switchTo().frame();
        Metoda frame() ma trzy implementacje - związaną z int index, Stringiem oraz WebElementem
        Spróbujemy każdej z nich
        */

        /*
        1. Przypadek dla frame() z int index:
        numerując od zera określamy do którego w kolejności na stronie iframe chcemy się przepiąć
        (w naszym przypadku mamy tylko jeden iframe, więc będzie to indeks 0)
        To rozwiązanie ma swoje ograniczenie - po zmianie struktury strony, nasz indeks może ulec zmianie, jeśli
        jakiś iframe będzie dodany / usunięty
         */

        driver.switchTo().frame(0);
        driver.findElement(By.id("fname")).sendKeys("Ania");

        /*
        Aby teraz móc wykonać jakąś akcję na stronie głównej, musimy się ponownie przepiąć do niej
         */

        driver.switchTo().defaultContent();
        // driver.switchTo().parentFrame(); ta metoda również nam zadziała zamiast powyższej
        System.out.println(driver.findElement(By.tagName("h1")).getText());

        /*
        2. Przypadek dla frame() z WebElementem:
        */
        WebElement iframe = driver.findElement(By.cssSelector("iframe[src='basics.html']"));
        driver.switchTo().frame(iframe);

        /*
        3. Przypadek dla frame() ze Stringiem
        Metoda ta wyszukuje iframe z określoną wartością atrybutu id lub name
        W naszej stronie testowej iframe nie posiada takich atrybutów, więc z tej metody nie możemy skorzystać
        Gdybyśmy jednak chcieli się tę metodą posłużyć w innym przypadków, wówczas mielibyśmy następujący zapis
        driver.switchTo().frame("wartość atrybutu id lub name");
         */







    }
}
