import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class WaitTest {

    WebDriver driver;

    /*
    Nasz kod z testem warto poszerzyć o polecenia związane z czekiwaniem na elementy. Po co nam będzie to potrzebne?
    Istnieje kilka powodów. Po pierwsze nasza strona mogłaby nie zdążyć się załadować. Po drugie po kliknięciu np.
    jakiegoś przycisku zostaje wysłane zapytanie do serwera i musimy poczekać na odpowiedź.

    W ramach przykładu możemy posłużyć się stroną:
    https://testeroprogramowania.github.io/selenium/wait.html

    Po naciśnięciu przycisku Kliknij mnie! po upływie określonego czasu paragraf który do tej pory był ukryty
    staje się widoczny (traci atrybut hidden)

    Możemy też wykorzystać drugą stronę:
    https://testeroprogramowania.github.io/selenium/wait2.html

    Po naciśnięciu przycisku Kliknij mnie! po upływie określonego czasu paragraf którego wcześniej na stronie nie było
    zostaje dodany do struktury strony html

    Pirwsza z metod - metoda sleep() nie jest polecana, gdyż bazuje na określonym czasie. Z jednej strony może powodować to
    spowolnienie naszych testów, a z drugiej strony zadany czas może być niewystarczający do załadowania strony / wykonania jakiejś akcji.

    */

    @Test
    public void waitTest() throws InterruptedException {

        String driverPath = "C:\\Users\\Saturn\\Desktop\\szkolenie Ania\\selenium\\src\\main\\resources\\executables\\drivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);

        driver = new ChromeDriver();
        driver.get("https://testeroprogramowania.github.io/selenium/wait2.html");

        driver.findElement(By.id("clickOnMe")).click();
        /*
        Gdybyśmy od razu zdecydowali się odszukać paragraf, otrzymalibyśmy wyjątek NoSuchElementException
        driver.findElement(By.cssSelector("p"));

        Zatem musimy zaczekać na ten element.
        Najpierw posłużymy się metodą sleep(), która zatrzyma nam wykonanie testu na określoną (zahardcodowaną, wprowadzoną
        na sztywno) ilość milisekund.
        Zastosujemy 5000 milisekund, czyli 5 sekund
        Metoda ta może wyrzucić wyjątek, zatem dodamy informację o wyjątku do sygnatury
        */
        Thread.sleep(5000);
        driver.findElement(By.cssSelector("p"));
        /*
        Problemem jest przede wszystkim trudność określenia czasu, na jaki wykonanie testu ma być zawieszone,
        dlatego stosowanie tej metody jest ostatecznością - są bardziej optymalne metody

        Kolejna metoda implicitly wait będzie się odnosić do wszystkich elementów, jakie wyszukujemy na stronie
        w całym skrypcie (co jest jej minusem), jednak rozwiązanie to przynosi też wiele korzyści

        Dla przejrzystości kodu umieszczam ją w kolejnym teście
         */

        driver.close();
    }
    @Test
    public void waitTest2() {

        String driverPath = "C:\\Users\\Saturn\\Desktop\\szkolenie Ania\\selenium\\src\\main\\resources\\executables\\drivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);

        driver = new ChromeDriver();
        /*
        W tym miejscu możemy zarządzać naszymi timeoutami
        Dodajemy metodę implicitlyWait, w nawiasie podając ile czasu chcemy poczekać oraz jednostkę czasu
        Jeśli element nie będzie mógł być znaleziony, to w zadanym przez nas czasie test będzie wielokrotnie odpytywał
        i sprawdzał, czy ten element się nie pojawił. Jeśli po upływie określonego czasu element się nie pojawi, to
        dopiero wówczas pojawi się wyjątek.
         */
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://testeroprogramowania.github.io/selenium/wait2.html");

        driver.findElement(By.id("clickOnMe")).click();
        driver.findElement(By.cssSelector("p"));

        driver.close();

    }

    @Test
    public void waitTest3() {

        String driverPath = "C:\\Users\\Saturn\\Desktop\\szkolenie Ania\\selenium\\src\\main\\resources\\executables\\drivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);

        driver = new ChromeDriver();
        driver.get("https://testeroprogramowania.github.io/selenium/wait2.html");

        driver.findElement(By.id("clickOnMe")).click();

        /*
        Kolejną metodą, bardziej elastyczną od implicitlyWait, jest explicitWait. Będzie ona odnosiła się do konkretnych elementów.
        Korzystamy z klasy WebDriverWait, dodajemy nową zmienną wait i tworzymy nowy obiekt klasy WebDriverWait
        Do konstruktora dodajemy nasz driver oraz timeout w sekundach
        Następnie za pomocą naszej zmiennej będziemy mogli nadać określony warunek (możemy skorzystać z warunków
        predefiniowanych ale także możemy stworzyć własny).
        Najpierw skorzystamy z gotowych możliwości (gdy wpiszemy ExpectedConditions po kropce zobaczymy, jakie mamy dostępne opcje)

         */
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p")));
        driver.findElement(By.cssSelector("p"));

        /*
        Istnieje też możliwość skorzystania z innej niż WebDriverWait klasy, a mianowicie FluentWait
        WebDriverWait jest dzieckiem FluentWaita, czyli rozszerza tę klasę.
        WebDriverWait ma zapisane w sobie domyślnie ignorowanie wyjątków w określonym z góry czasie, FluentWait nie ma takiego
        rozwiązania domyślnie zaimplementowanego (domyślnie nie ignoruje wyjątków), więc jeżeli chcemy żeby FluentWait
        ignorował jakiś wyjątek, to musimy to jawnie dodać (bez tego możemy dostać wyjątek i fail testu zanim element zostanie znaleziony).
        W tym celu korzystamy z metody ignoring
        W następnym teście wypróbujemy rozwiązanie właśnie z FluentWait
         */
    }

    @Test
    public void waitTest4() {

        String driverPath = "C:\\Users\\Saturn\\Desktop\\szkolenie Ania\\selenium\\src\\main\\resources\\executables\\drivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);

        driver = new ChromeDriver();
        driver.get("https://testeroprogramowania.github.io/selenium/wait2.html");

        driver.findElement(By.id("clickOnMe")).click();

        /*
        Konstrukcja tutaj będzie nieco inna, jednak możemy skorzystać z tej samej metody
        Przy ignorowaniu wyjątku ważne, żeby wybrać implementację z selenium
        Dodatkowo w poprzednim teście / klasie mogliśmy zdefiniować timeout w konstruktorze, w tym przypadku
        musimy oddzielnie dodatkowo skonfigurować taki timeout

        Dodatkowo, jeśli chcemy, możemy skonfigurować interwał co jaki czas nasz test będzie próbował odszukać element
        (nie jest to jednak wymagane) -> w tym celu korzystamy z wait.pollingEvery();
         */
        FluentWait<WebDriver> wait = new FluentWait<>(driver);
        wait.ignoring(NoSuchElementException.class);
        wait.withTimeout(Duration.ofSeconds(10));
        wait.pollingEvery(Duration.ofSeconds(1));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p")));
        driver.findElement(By.cssSelector("p"));


        /*
        Powyższe rozwiązanie oferuje nam taki sam rezultat jak w poprzednim teście, tj. dla danego elementu przez zadany czas
        (u nas 10 sekund), wielokrotnie podejmowane są próby znalezienia elementu. Jak tylko taki element zostanie znaleziony,
        test jest bez zwłoki kontynuowany. Jeśli nie zostanie znaleziony, to po tym czasie zostanie wyrzucony wyjątek
        */

    }

    @Test
    public void waitTest5() {

        String driverPath = "C:\\Users\\Saturn\\Desktop\\szkolenie Ania\\selenium\\src\\main\\resources\\executables\\drivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);

        driver = new ChromeDriver();
        driver.get("https://testeroprogramowania.github.io/selenium/wait2.html");

        driver.findElement(By.id("clickOnMe")).click();

        /*
        Przyjrzyjmy się teraz ExpectedConditions
        Są dwie metody: jedna która odpowiednia jest dla lokatora (dajemy tam obiekt klasy By)
        a druga odpowiednia jest dla WebElementu (zatem musimy podać nasz driver i zlokalizować element)
        Istnieje problem z implementacją dla WebElementu - w takim podejściu wyszukiwany jest element w strukturze strony
        i możemy otrzymać wyjątek jeszcze przed waitem

        Reasumując:
        implementacja z WebElementem - wyszukiwanie rozpoczyna się wcześniej - wyjątek może nie zostać zignorowany,
        bo dzieje się to przed naszym waitem
        implementacja z lokatorem - wyszukiwanie rozpoczyna się później - wyjątek zostanie zignorowany do upłynięcia
        określonego timeoutu
         */
        WebDriverWait wait = new WebDriverWait(driver,10);
        // wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p")));
        // wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("p"))));
        // wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("p")));

        /*
        Jeśli chodzi o dalsze metody z klasy ExpectedConditions to mamy:
        - visibilityOfElementLocated oraz visibilityOf - czeka sprawdzając widoczność elementu na stronie i jego dostępność dla użytkownika
        - presenceOfElementLocated - czeka sprawdzając obecność elementu w strukturze strony
        - alertIsPresent - czeka aż alert nam się wyświetli
        - możemy sprawdzać atrybuty, stan jakiegoś checkboxa, dostępność danego elementu do interakcji itd.
        - invisibilityOfElementLocated oraz invisibilityOf - czeka sprawdzając czy jakiś element zniknie

        Lista tych możliwości jest bardzo długa, jednak możemy też skorzystać z własnego warunku.
        Zostanie to przedstawione poniżej
         */


        driver.findElement(By.cssSelector("p"));

    }


    @Test
    public void waitTest6() {

        String driverPath = "C:\\Users\\Saturn\\Desktop\\szkolenie Ania\\selenium\\src\\main\\resources\\executables\\drivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);

        driver = new ChromeDriver();
        driver.get("https://testeroprogramowania.github.io/selenium/wait2.html");

        driver.findElement(By.id("clickOnMe")).click();

        waitForElementToExist(By.cssSelector("p"));
    }


    public void waitForElementToExist(By locator) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver);
        wait.ignoring(NoSuchElementException.class);
        wait.withTimeout(Duration.ofSeconds(10));
        wait.pollingEvery(Duration.ofSeconds(1));


        /*
        Następnie zdefiniujemy swój właśny warunek
        Skorzystamy z klasy anonimowej i interfejsu Funkction
        i mtody apply

        pierwszy parametr to jest typ parametru który przekazujemy do metody, a drugi to typ zwracanej wartości
        My będziemy chcieli zwracać wartość true lub false, stąd musimy podać Boolean
        dlatego też musimy o odpowiednim dostosowaniu metody apply
         */
/*        wait.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {

                List<WebElement> elements = driver.findElements(locator);
                if(elements.size()>0) {
                    System.out.println("Element jest na stronie");
                    return true;
                } else {
                    System.out.println("Elementu nie ma na stronie");
                    return false;
                }
            }
        });*/

            /*
            Klasę anonimową można też zastąpić wyrażeniem lambda
            Dlatego też wykomentuję górną część, żeby sprawdzić działanie
            alternatywnego zapisu
            */

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
