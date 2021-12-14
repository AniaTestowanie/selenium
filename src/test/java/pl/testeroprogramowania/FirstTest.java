package pl.testeroprogramowania;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Listeners(value = {SampleTestListener.class})
public class FirstTest extends BaseTest {

    /*
    Rozszerzamy naszą klasę pl.testeroprogramowania.FirstTest i wskazujemy jako rodzica klasę pl.testeroprogramowania.BaseTest
    Dzięki temu klasa pl.testeroprogramowania.FirstTest będzie korzystała z metod dostępnych w klasie rodzica (pl.testeroprogramowania.BaseTest)
     */

    WebDriver driver;



    @Test
    public void firstTest() {

        String driverPath = "C:\\Users\\Saturn\\Desktop\\szkolenie Ania\\selenium\\src\\main\\resources\\executables\\drivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);

        driver = new ChromeDriver();
        driver.get("https://testeroprogramowania.github.io/selenium/wait2.html");

        driver.findElement(By.id("clickOnMe")).click();

        waitForElementToExist(By.cssSelector("p"));

        String paraText = driver.findElement(By.cssSelector("p")).getText();
        //Assert.assertEquals("",paraText);
        Assert.assertEquals(paraText,"Dopiero się pojawiłem!");

        /*
        Asercje służą nam do sprawdzenia, czy nasza aplikacja działa poprawnie - czy aktualny rezultat jest zgodny z oczekiwanym.

        Asercje mają wiele implementacji. W ramach Assert.assertEquals można porównywać wiele różnych rzeczy. Możemy też dodać
        informację, która w przypadku niepowodzenia testu wyświetli nam się na ekranie. Dodajemy taką informację po przecinku

        Jeżeli asercja z zapisu powyżej zakończyłaby się niepowodzeniem, to przerwane zostanie wykonywanie testu.
        Nie zawsze jest to dla nas pożądana sytuacja.
        W tym wypadku przykład linijki, która wywoła nam taką sytuację został poniżej wykomentowany.
        W tej metodzie firstTest() są przedstawione asercje twarde, które zawsze będą działać w ten sposób.
        Przykłady asercji (asercji miękkich, soft), które nie będą powodowały zatrzymywania wykonania testu w przypadku
        otrzymania błędu, znajdują się w drugiej metodzie tej klasy secondTest()
         */

        // Assert.assertEquals(paraText,"Dopiero się","teksty nie są sobie równe");

        /*
        Takiego samego sprawdzenia możemy dokonać również poprzez zupełnie inny zapis i dwukrotną asercję -
        sprawdzenie czy element wyświetlił się na stronie i sprawdzenie czy pobrany tekst jest równy oczekiwanemu
        Przykład poniżej (dla czytelności wprowadziliśmy nową zmienną)
         */

        WebElement para = driver.findElement(By.cssSelector("p"));
        Assert.assertEquals(para.isDisplayed(),true);
        Assert.assertEquals(para.getText(), "Dopiero się pojawiłem!");

        /*
        Powyższy zapis można jeszcze zoptymalizować - dlatego w drugim wierszu assertEquals podświetla nam się na szaro
        Lepsza alternatywa poniżej:
        Jeśli warunek w assertTrue będzie spełniony, wówczas test jest zaliczany
         */

        Assert.assertTrue(para.isDisplayed(), "Element się nie pojawił");
        Assert.assertEquals(para.getText(), "Dopiero się pojawiłem!");

        /*
        Mamy też wiele innych możliwości:
         */

        Assert.assertTrue(para.getText().startsWith("Dopiero"));
        Assert.assertFalse(para.getText().contains("takitest"));
        Assert.assertNotEquals(paraText,"Dopiero się");

        System.out.println("Wszystko ok ;)");

        driver.quit();
    }


    @Test
    public void secondTest() {

        String driverPath = "C:\\Users\\Saturn\\Desktop\\szkolenie Ania\\selenium\\src\\main\\resources\\executables\\drivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);

        driver = new ChromeDriver();
        driver.get("https://testeroprogramowania.github.io/selenium/wait2.html");

        driver.findElement(By.id("clickOnMe")).click();

        waitForElementToExist(By.cssSelector("p"));

        String paraText = driver.findElement(By.cssSelector("p")).getText();
        //Assert.assertEquals("",paraText);
        Assert.assertEquals(paraText,"Dopiero się pojawiłem!");

        /*
        Pierwszym krokiem do stworzenia miękkiej asercji, jest stworzenie obiektu klasy SoftAssert
        Następnie zamiast korzystać z klasy Assert będziemy korzystać z obiektu klasy SoftAssert softAssert
        Oznacza to, że możemy podmienić klasę Assert z poprzedniego testu na softAssert
        Możemy też odkomentować linijkę powodującą błąd
         */

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(paraText,"Dopiero się","teksty nie są sobie równe"); // tu pojawi się błąd


        WebElement para = driver.findElement(By.cssSelector("p"));
        softAssert.assertEquals(para.isDisplayed(),true);
        softAssert.assertEquals(para.getText(), "Dopiero się pojawiłem!");



        softAssert.assertTrue(para.isDisplayed(), "Element się nie pojawił");
        softAssert.assertEquals(para.getText(), "Dopiero się pojawiłem!");


        softAssert.assertTrue(para.getText().startsWith("Dopiero"));
        softAssert.assertFalse(para.getText().contains("takitest"));
        softAssert.assertNotEquals(paraText,"Dopiero się pojawiłem!", "teksty się zgadzają choć nie powinny"); // tu pojawi się błąd

        /*
        Powyżej pokazaliśmy tylko co chcemy sprawdzić
        Aby teraz wykonać ocenę, asercję, musimy na obiekcie wykonać metodę assertAll();
        Dopiero ona wykona nam faktycznie ten test
        Jeśli na którejś z asercji wykryty zostanie błąd, to dalej kontynuowane są pozostałe asercje, ale pozostałe
        linijki kodu jak np. driver.quit() nie zostaną już wykonane
        Na konsoli będziemy mogli odczytać, które asercje zawiodły

         */

        System.out.println("Wszystko ok ;)");

        softAssert.assertAll();

        driver.quit();
    }


    public void waitForElementToExist(By locator) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver);
        wait.ignoring(NoSuchElementException.class);
        wait.withTimeout(Duration.ofSeconds(10));
        wait.pollingEvery(Duration.ofSeconds(1));

        wait.until((driver) -> {
            List<WebElement> elements = driver.findElements(locator);
                if(elements.size()>0) {
                    System.out.println("Element jest na stronie");
                    return true;
                } else {
                    System.out.println("Elementu nie ma na stronie");
                    return false;
                }
        });
    }

}
