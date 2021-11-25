import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.Set;


/*
Obsłużenie nowego okna przeglądarki, która może nam się otworzyć podczas wykonywania testu

Przykładowo na stronie testowej kliknięcie przycisku "Click me" powoduje otworzenie nowego okna przeglądarki i strony google.com

*/


public class NewWindowTest {

    @Test
    public void performActionsTest() {

        String driverPath = "C:\\Users\\Saturn\\Desktop\\szkolenie Ania\\selenium\\src\\main\\resources\\executables\\drivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);

        WebDriver driver = new ChromeDriver();
        driver.get("https://testeroprogramowania.github.io/selenium/basics.html");



        /*
        Aby przepiąć się do nowego okna musimy skorzystać z metody:
        driver.switchTo().newWindow(); w ostatnich nawiasach musimy podać nazwę nowego okna
        Najpierw jednak musimy dowiedzieć, się jak się ono nazywa. W tym celu możemy się posłużyć metodą:
        driver.getWindowHandle();
        która zwraca nam string
        Zatem najpierw zdefiniujemy sobie nową zmienną String, dla nazwy bieżącego okna przeglądarki
        */

        String currentWindow = driver.getWindowHandle();
        driver.findElement(By.id("newPage")).click();

        /*
        Możemy też wykorzystać metodę:
        driver.getWindowHandles();
        która zwraca zbiór stringów

        Wówczas po porównaniu nazw okien, będziemy się mogli przepiąć do tego okna przeglądarki, które ma nazwę różną od currentWindow
         */

        Set<String> windowNames = driver.getWindowHandles();

        for(String window : windowNames) {
            if(!window.equals(currentWindow)){
                driver.switchTo().window(window);
            }
        }

        driver.findElement(By.id("L2AGLb")).click();
        driver.findElement(By.name("q")).sendKeys("Selenium");

        /*
        Teraz spróbujemy wrócić do naszego pierwszego okna i wykonać na nim określoną akcję
         */

        driver.switchTo().window(currentWindow);
        driver.findElement(By.id("fname")).sendKeys("Test");




    }
}
